from pathlib import Path
from django.shortcuts import render
from rdflib import Graph as RDFGraph
from rdflib import Literal, Namespace, URIRef
from rdflib.extras.external_graph_libs import rdflib_to_networkx_graph
from pyvis.network import Network

# Create your views here.
import os

# We set the directory to static/rdf manually to avoid errors
mainrdfpath = Path().absolute().parent.joinpath(
    "static").joinpath('rdf').joinpath("main.ttl")
# temprdfpath = Path().absolute().parent.joinpath(
#     "static").joinpath('rdf').joinpath("temp.ttl")

ex = Namespace("http://example.org/")

def Load_graph():
    g = RDFGraph()
    g.parse("static\\rdf\\main.ttl")
    g.bind("ex", ex)
    return g

def validator(S):
  if S == "" or S == "None":
      S = None 
  if S is not None:
      return URIRef(ex+S)
    
def object_validator(O):
  if O == "" or O == "None":
      O = None
  if O is not None:
    return Literal(O)
    
    
def graph_exist(S=None, P=None, O=None):
    """
    returns a boolean value, check if a Subject, Predicat, Object or a combination of those exists
    """
    g = Load_graph()
    S=validator(S)
    P=validator(P)
    O=object_validator(O)
    
    return ((S, P, O) in g)


def get_textual_rdf(S,P,O):
    S=validator(S); P=validator(P); O=object_validator(O)
    g = Load_graph()
    if S is None and O is None and P is None:
        return g.serialize(format="ttl")
    
    temp = RDFGraph()
    temp.bind('ex', ex)
    temp += g.triples((S, P, O))
    return temp.serialize(format="ttl")



def get_graph(S=None, P=None, O=None):
   temp = RDFGraph()
   temp.bind('ex', ex)
   temp.parse(data=get_textual_rdf(S,P,O))
   G = rdflib_to_networkx_graph(temp)
   net = Network(height="750px", width="100%", font_color="black")
   net.from_nx(G)
   net.write_html("templates\\generatedgraphs\\temp.html", local=False )
   return get_textual_rdf(S,P,O)
    


def delete_graph(S, P, O): 
    S=validator(S); P=validator(P); O=object_validator(O)
    g= Load_graph()
    g.remove((S, P, O))
    g.serialize(destination='static\\rdf\\main.ttl', format="turtle")
    print("Deletion successful!")


def add_triple(S, P, O):
    g = Load_graph()
    g.add((S, P, O))
    g.serialize(destination='static\\rdf\\main.ttl', format="turtle")
    print("Addition successful!")

def add_graph(rdf_text:str, rdf_format:str):
    g=Load_graph()
    try:
        g.parse(data=rdf_text, format="turtle")
        g.serialize(destination='static\\rdf\\main.ttl', format=rdf_format)
        return g.serialize(format=rdf_format)
    except Exception as e: print(e)

def modify_triple(S, P, O):
    S=validator(S); P=validator(P); O=object_validator(O)
    g= Load_graph()
    
    if  (S,P,None) in g:
        g.set((S, P, O))
        g.serialize(destination='static\\rdf\\main.ttl', format="turtle")
        print("Modification successful!")
        
    else:
        print("Cannot find Triple with such subject and predicat")


#print(graph_exist("11", None, None))                       
# get_graph(None, "SourceIP", None)                                         
# delete_graph()                                                                        WORKS!
# add_graph(URIRef(ex+"10"), URIRef(ex+"TimeStamp"), Literal("10/03/2023 23:40"))       WORKS!
# modify_graph(URIRef(ex+"10"), URIRef(ex+"TimeStamp"),Literal("10/03/2023 23:57"))      WORKS!


# data ='''
# @prefix ex: <http://example.org/> .

# ex:36 ex:DestinationIP "192.168.1.1" ;
#     ex:DestinationPort "65230" ;
#     ex:DoH "True" .
# '''
# add_graph(data)

# modify_triple("356", "DoH", "True")
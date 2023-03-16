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

g = RDFGraph()
g.parse("static\\rdf\\main.ttl")
ex = Namespace("http://example.org/")
g.bind("ex", ex)

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
    
    
def graph_exist(S, P, O):
    """
    returns a boolean value, check if a Subject, Predicat, Object or a combination of those exists
    """
    
    S=validator(S)
    P=validator(P)
    O=object_validator(O)
    
    return ((S, P, O) in g)


def get_graph(S=None, P=None, O=None):
    """
    to get the desired tuples make sure to use this function as follows: 
    get_grapth(URIRef(ex+"Subject"), URIRef(ex+"Predicat"), Literal(Object)) if anyone the three as not specified, pass ONLY 'None' instead
    """
    temp = RDFGraph()
    ex = Namespace("http://example.org/")
    temp.bind("ex", ex)
    
    
    S=validator(S)
    P=validator(P)
    O=object_validator(O)
    
    temp += g.triples((S, P, O))
    temp.serialize(
        destination='static\\rdf\\temp.ttl', format="turtle")
    G = rdflib_to_networkx_graph(temp)
    net = Network(height="750px", width="100%", font_color="black")
    net.from_nx(G)
    #net.show_buttons()
    net.write_html("templates\\generatedgraphs\\temp.html", local=False )
    
    return temp.serialize(format="turtle")
    


def delete_graph(S, P, O):
    """
    beware not to put all values as empty or else it will remove the whole graph
    don't worry if you do so, you can regenerate it with the anycsvtordf.py file
    """
    
    S=validator(S)
    P=validator(P)
    O=object_validator(O)
    
    g.remove((S, P, O))
    g.serialize(destination='static\\rdf\\temp.ttl', format="turtle")
    print("Deletion successful!")


def add_graph(S, P, O):
    """
    ALL elements must be specified
    if triple already exists, it will just be ignored and won't be duplicated
    """
    
    S=validator(S)
    P=validator(P)
    O=object_validator(O)
    
    g.add((S, P, O))
    g.serialize(destination=mainrdfpath, format="turtle")
    print("Addition successful!")


def modify_graph(S, P, O):
    """
    ALL elements must be specified
    """
    
    S=validator(S)
    P=validator(P)
    O=object_validator(O)
    
    g.remove((S, P, None))
    g.add((S, P, O))
    g.serialize(destination=mainrdfpath, format="turtle")
    print("Modification successful!")


#print(graph_exist("11", None, None))                       
get_graph(None, "SourceIP", None)                                         
# delete_graph()                                                                        WORKS!
# add_graph(URIRef(ex+"10"), URIRef(ex+"TimeStamp"), Literal("10/03/2023 23:40"))       WORKS!
# modify_graph(URIRef(ex+"10"), URIRef(ex+"TimeStamp"),Literal("10/03/2023 23:57"))      WORKS!

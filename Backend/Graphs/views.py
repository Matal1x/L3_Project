from pathlib import Path
from django.shortcuts import render
from rdflib import Graph as RDFGraph
from rdflib import Literal, Namespace, URIRef
from rdflib.extras.external_graph_libs import rdflib_to_networkx_graph
# Create your views here.
import os

# We set the directory to static/rdf manually to avoid errors
rdfpath = Path().absolute().parent.joinpath(
    "static").joinpath('rdf').joinpath("temp.ttl")

g = RDFGraph()
g.parse("static\\rdf\\test.ttl")
ex = Namespace("http://example.org/")
g.bind("ex", ex)


def graph_exist(S=None, P=None, O=None):
    return ((S, P, O) in g)


def get_graph(S, P, O):
    """
    to get the desired tuples make sure to use this function as follows: 
    get_grapth(URIRef(ex+"Subject"), URIRef(ex+"Predicat"), Literal(Object)) if anyone the three as not specified, pass ONLY 'None' instead
    """
    temp = RDFGraph()
    ex = Namespace("http://example.org/")
    temp.bind("ex", ex)
    temp += g.triples((S, P, O))
    temp.serialize(
        destination=rdfpath, format="turtle")


def delete_graph(S=None, P=None, O=None):
    for ((S, P, O)) in g:
        g.remove((S, P, O))


def add_graph(S=None, P=None, O=None):
    g.add((S, P, O))


get_graph(None, URIRef(ex+"SourceIP"), None)

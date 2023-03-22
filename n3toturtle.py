from rdflib import Graph as rdfgrpah
from rdflib import Namespace

g = rdfgrpah()
cys = Namespace("http://cybersecurity.org/")
g.bind("cys", cys)
g.parse("generatedN3.n3")
g.serialize(destination="generatedrdf.ttl", format="ttl")
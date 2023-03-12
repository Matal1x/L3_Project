# We import all the necessary packages and libraries
from rdflib import Literal, Namespace, URIRef
import pandas as pd
from pyvis.network import Network
from rdflib import Graph as RDFGraph
from rdflib.extras.external_graph_libs import rdflib_to_networkx_graph


# We load the csv file as a panda dataframe and we drop all rows with null values
csvpath = input("Enter your csv file (ending with .csv): \n")

if (not csvpath.endswith(".csv")):
    csvpath = csvpath + ".csv"

sample = pd.read_csv(f'static\csv\{csvpath}', low_memory=False)

# Here we deal with spaces (" ") in the data. I replace them with "_" so that URI's become valid.
sample = sample.replace(to_replace=" ", value="_", regex=True)

# We mark all missing/empty data as "unknown". This makes it easy to delete triples containing this later.
sample = sample.fillna("unknown")

# We initialise an RDF graph variable
g = RDFGraph()
ex = Namespace("http://example.org/")
g.bind("ex", ex)


# We begin creating RDFs
for i in sample.index:                      # We iterate through the rows

    for predicat in sample.columns:             # We iterate through the columns

        # We save rdf triple as the following:
        g.add((URIRef(ex + f"{i}"), URIRef(ex + f"{predicat}"),
              Literal(str(sample.loc[i][predicat]))))
        # (name of the row, name of the column, value)

# We remove triples that we marked as unknown earlier.
g.remove((None, None, Literal("unknown")))

# We save the graph in the turtle format

rdfpath = input("Give your rdf file a name (ending with .ttl): \n")
if (not rdfpath.endswith(".ttl")):
    rdfpath = rdfpath + ".ttl"
g.serialize(format="turtle", destination=f'static\\rdf\\{rdfpath}')
print(g.serialize(format="turtle"))


# We use pyvis library to show our graph
G = rdflib_to_networkx_graph(g)
print("\nnetworkx Graph loaded successfully with length {}".format(len(G)))
net = Network('1920px', '1080px')
net.from_nx(G)
net.show("templates\generatedgraphs\default.html", notebook=False)

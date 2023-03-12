from rdflib import Graph, Literal, Namespace, URIRef
from rdflib.namespace import RDF, FOAF, RDFS, OWL
import pandas as pd
from pyvis.network import Network

from rdflib import Graph as RDFGraph
from rdflib.extras.external_graph_libs import rdflib_to_networkx_graph
import networkx as nx
from networkx import Graph as NXGraph
import matplotlib.pyplot as plt
import statistics
import collections


g = Graph()
ex = Namespace("http://example.org/")
g.bind("ex", ex)

# Load the CSV data as a pandas Dataframe.
#csv_data = pd.read_csv("task1.csv")
csv_data = pd.read_csv("test.csv")

# Here I deal with spaces (" ") in the data. I replace them with "_" so that URI's become valid.
csv_data = csv_data.replace(to_replace=" ", value="_", regex=True)

# Here I mark all missing/empty data as "unknown". This makes it easy to delete triples containing this later.
csv_data = csv_data.fillna("unknown")
day = "Friday"
# Loop through the CSV data, and then make RDF triples.
for index, row in csv_data.iterrows():
    # The names of the people act as subjects.
   # Create triples: e.g. "Attaker - attack - Attack0"
    attacker = "Attacker" + str(day)
    subject = "Attack"+str(index)
    g.add((URIRef(ex + attacker), URIRef(ex + "doesAttack"), URIRef(ex + subject)))
    # Create triples: e.g. "Cade_Tracey - age - 27"
    g.add((URIRef(ex + subject), URIRef(ex + "hasDestinationPort"), Literal(row[0])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFlowDuration"), Literal(row[1])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasTotalFwdPackets"), Literal(row[2])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasTotalBackwardPackets"), Literal(row[3])))
    
    g.add((URIRef(ex + subject), URIRef(ex + "hasTotalLengthOfFwdPackets"), Literal(row[4])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasTotalLengthOfBwdPackets"), Literal(row[5])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdPacketLengthMax"), Literal(row[6])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdPacketLengthMin"), Literal(row[7])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdPacketLengthMean"), Literal(row[8])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdPacketLengthStd"), Literal(row[9])))
    
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdPacketLengthMax"), Literal(row[10])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdPacketLengthMin"), Literal(row[11])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdPacketLengthMean"), Literal(row[12])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdPacketLengthStd"), Literal(row[13])))
       
       
    g.add((URIRef(ex + subject), URIRef(ex + "hasFlowBytes/s"), Literal(row[14])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFlowPackets/s"), Literal(row[15])))
                 
    g.add((URIRef(ex + subject), URIRef(ex + "hasFlowIATMean"), Literal(row[16])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFlowIATStd"), Literal(row[17])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFlowIATMax"), Literal(row[18])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFlowIATMin"), Literal(row[19])))
    
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdIATTotal"), Literal(row[20])))               
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdIATMean"), Literal(row[21])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdIATStd"), Literal(row[22])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdIATMax"), Literal(row[23])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdIATMin"), Literal(row[24])))
                                    
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdIATTotal"), Literal(row[25])))               
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdIATMean"), Literal(row[26])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdIATStd"), Literal(row[27])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdIATMax"), Literal(row[28])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdIATMin"), Literal(row[29])))
                                                                 
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdPSHFlags"), Literal(row[30])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdPSHFlags"), Literal(row[31])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdURGFlags"), Literal(row[32])))
    g.add((URIRef(ex + subject), URIRef(ex + "hasBwdURGFlags"), Literal(row[33])))
    
    g.add((URIRef(ex + subject), URIRef(ex + "hasFwdHeaderLength"), Literal(row[34])))
    
    # If We want can add additional RDF/RDFS/OWL information e.g
    #g.add((URIRef(ex + subject), RDF.type, FOAF.Person))

# I remove triples that I marked as unknown earlier.
#g.remove((None, None, URIRef("http://example.org/unknown")))

# Clean printing of the graph.
print(g.serialize(format="turtle"))


# RDF graph loading
#path = input("Path or URI of the RDF graph to load: ")

# Conversion of rdflib.Graph to networkx.Graph
G = rdflib_to_networkx_graph(g)
print("networkx Graph loaded successfully with length {}".format(len(G)))
net = Network('1000px', '1000px')
net.from_nx(G)
net.show("net.html",notebook=False)










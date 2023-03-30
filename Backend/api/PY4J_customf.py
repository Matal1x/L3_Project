from rdflib import Namespace, Literal, URIRef, Graph as rdf
from py4j.java_gateway import JavaGateway
import json

endpoint = "http://localhost:5000"


def None_affirmer(X):
    if X == "" or str(X).upper() == "NONE" or str(X).upper() == "ALL":
        return None
    else:
        return X
    
def URI_Applyer_String(X, graph):
    cy = Namespace(f"http://cybersecurity.org/DataSet/{graph}/")
    if X is not None:
        return f"<{URIRef(cy+X)}>"
    
def object_adapter(X):
    if str(X).upper() == "TRUE":
        X = True
    if str(X).upper() == "FALSE":
        X = False

    if X is not None:
        return Literal(X)
    

def triple_adapter( s,p,o , cy):
    if s is None:
        s = "?s"
    else:
        s = URI_Applyer_String(s, cy)

    if p is None:
       p = "?p"
    else:
       p = URI_Applyer_String(p, cy)

    if o is None:
       o = "?o"
    else:
       o = object_adapter(o)
       
    return (s,p,o)



# GET QUERIES*******************************************************************************************************************************

def SELECT_QUERY_JS(graph,s,p,o):
    gateway = JavaGateway()
    HandleStore=gateway.entry_point.getHandleStore()
    
    tempS=""; tempP=""; tempO=""; 
    
    if s=="?s":
        s=""
    else:
        tempS="?s"
        
    if p=="?p":
        p=""
    else:
        tempP="?p"
        
    if o=="?o":
        o=""
    else:
        tempO="?o"    
    
    VALUES=f"VALUES ({tempS} {tempP} {tempO}) {{ ( {s} {p} {o}) }}"
    
    
    
    
    resSelect = HandleStore.get(f"{endpoint}/Cybersecurity/DataSet/{graph}",
                                
                                f""" 
                                
                                SELECT DISTINCT ?s ?p ?o 
                                WHERE {{
                                    {VALUES}
                                    ?s ?p ?o.
                                }}
                                ORDER BY ?s ?p
                                """)
    rs = resSelect.getResultSet()
    
    somelist = HandleStore.PythonResultSetToJSON(rs)
    LI=list()
    for s in somelist:
        LI.append(json.loads(s))
        
    return LI



def ASK_QUERY(graph,s,p,o):
    gateway = JavaGateway()
    HandleStore=gateway.entry_point.getHandleStore()
    
    
    resAsk = HandleStore.get(f"{endpoint}/Cybersecurity/DataSet/{graph}",
                             
                             f""" 
                             
                             ASK {{ {s} {p} {o} }}
                             
                             """)
    return resAsk.getBooleanResult()



def CONSTRUCT_QUERY(graph,s,p,o):
    gateway = JavaGateway()
    HandleStore=gateway.entry_point.getHandleStore()
    resConstruct = HandleStore.get(f"{endpoint}/Cybersecurity/DataSet/{graph}",
                                   
                                   f"""
                                   
                                   CONSTRUCT {{ {s} {p} {o} }} 
                                   WHERE {{ {s} {p} {o}. }}
                                   """)
    
    md1 = resConstruct.getModel()
    return HandleStore.PythonModelToTurtle(md1)



# INSERT  DELETE DELETE_INSERT   ***********************************************************

def INSERT_QUERY(graph,s,p,o):
    gateway = JavaGateway()
    HandleStore=gateway.entry_point.getHandleStore()
    
    resInsert = HandleStore.put(f"{endpoint}/Cybersecurity/DataSet/{graph}", 
                                
                                f"""
                                
                                INSERT DATA {{
                                    {s} {p} {o} .   
                                }}
                                """)
    return resInsert.getBooleanResult()
    
  
    
def DELETE_QUERY(graph,s,p,o):
    gateway = JavaGateway()
    HandleStore=gateway.entry_point.getHandleStore()
    
    resDelete = HandleStore.put(f"{endpoint}/Cybersecurity/DataSet/{graph}",
                                
                                f"""
                                
                                DELETE
                                WHERE
                                    {{ 
                                    {s} {p} {o} .
                                    }}
                                
                                """)
    return resDelete.getBooleanResult()
    
    
def DELETE_INSERT_QUERY(graph,s,p,o):
    gateway = JavaGateway()
    HandleStore=gateway.entry_point.getHandleStore()
    
    resDelIns= HandleStore.put(f"{endpoint}/Cybersecurity/DataSet/{graph}",
                               
                               f"""
                               
                               DELETE {{
                                   {s} {p} ?someobject .
                               }}
                               INSERT {{
                                   {s} {p} {o} .
                               }}
                               WHERE {{
                                   {s} {p} ?someobject .
                               }}
                               """
                               )
    
    return resDelIns.getBooleanResult()

package test;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.sparql.core.DatasetImpl;
import org.openjena.riot.Lang;
import oracle.rdf.kv.client.jena.*;

public class DatasetExample{
  
  public static void main(String[] args) throws Exception
  {
    
String szStoreName  = "airlio";
String szHostName   = "localhost";
String szHostPort   = "5000";
int iBatchSize      = 5;
int iDOP            = 5;

System.out.println("Create Oracle NoSQL connection");
OracleNoSqlConnection conn 
= OracleNoSqlConnection.createInstance(szStoreName,
                                       szHostName, 
                                       szHostPort);
     
System.out.println("Create Oracle NoSQL datasetgraph");
OracleGraphNoSql graph = new OracleGraphNoSql(conn);
DatasetGraphNoSql datasetGraph = DatasetGraphNoSql.createFrom(graph);
   
// Close graph, as it is no longer needed
graph.close();
    
// Clear datasetgraph
datasetGraph.clearRepository();
    
// Load data from file into the Oracle NoSQL Database
DatasetGraphNoSql.load("C:\\Users\\memus\\Desktop\\examples\\example3.ttl", Lang.NTRIPLES, conn, 
                        "http://cybersecurity/",
                        iBatchSize, // batch size
                        iDOP); // degree of parallelism
    
// Create dataset from Oracle NoSQL datasetgraph to execute
Dataset ds = DatasetImpl.wrap(datasetGraph);
   
String szQuery = "select * where { graph ?g { ?s ?p ?o }  }";
System.out.println("Execute query " + szQuery);
    
Query query = QueryFactory.create(szQuery);
QueryExecution qexec = QueryExecutionFactory.create(query, ds);
    
try {
      ResultSet results = qexec.execSelect();
      ResultSetFormatter.out(System.out, results, query);
    }
    
finally {
      qexec.close();
    }

ds.close();
conn.dispose();
 }
}
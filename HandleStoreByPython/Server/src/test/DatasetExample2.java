package test;
import com.hp.hpl.jena.graph.*;
import com.hp.hpl.jena.sparql.core.*;
import com.hp.hpl.jena.query.*;
import oracle.rdf.kv.client.jena.*;

public class DatasetExample2
{
  
  public static void main(String[] args) throws Exception
  {
  
String szStoreName  = "airlio";
String szHostName   = "localhost";
String szHostPort   = "5000";
    
// Create Oracle NoSQL connection 
OracleNoSqlConnection conn = OracleNoSqlConnection.createInstance(szStoreName, szHostName, szHostPort);
    
// Create Oracle NoSQL graph and dataset 
OracleGraphNoSql graph = new OracleGraphNoSql(conn);
DatasetGraphNoSql datasetGraph = DatasetGraphNoSql.createFrom(graph);

// Close graph, as it is no longer needed
graph.close(); 
    
// Clear dataset
//datasetGraph.clearRepository();
  System.out.println("datasetgraph : " +datasetGraph.getGraph(Node.createURI("http://cybersecurity/")));  
// add data to the default graph
datasetGraph.add(new Quad(
        Node.createURI("http://cybersecurity/"), //Quad.defaultGraphIRI, specifies default graph
        Node.createURI("http://cybersecurity/bob"),
        Node.createURI("http://purl.org/dc/elements/1.1/publisher"),
        Node.createLiteral("Bob Hacker")));
    
datasetGraph.add(new Quad(
        Node.createURI("http://cybersecurity/"),//Quad.defaultGraphIRI, // specifies default graph
        Node.createURI("http://cybersecurity/alice"),
        Node.createURI("http://purl.org/dc/elements/1.1/publisher"),
        Node.createLiteral("alice Hacker")));
    
// add data to the bob named graph
datasetGraph.add(new Quad(
        Node.createURI("http://cybersecurity/bob"), // graph name
        Node.createURI("urn:bob"),
        Node.createURI("http://xmlns.com/foaf/0.1/name"),
        Node.createLiteral("Bob")));
    
datasetGraph.add(new Quad(
        Node.createURI("http://cybersecurity/bob"), // graph name
        Node.createURI("urn:bob"),
        Node.createURI("http://xmlns.com/foaf/0.1/mbox"),
        Node.createURI("mailto:bob@example")));

// add data to the alice named graph
datasetGraph.add(new Quad(
        Node.createURI("http://cybersecurity/alice"), // graph name
        Node.createURI("urn:alice"),
        Node.createURI("http://xmlns.com/foaf/0.1/name"),
        Node.createLiteral("Alice")));
    
datasetGraph.add(new Quad(
        Node.createURI("http://cybersecurity/alice"), // graph name
        Node.createURI("urn:alice"),
        Node.createURI("http://xmlns.com/foaf/0.1/mbox"),
        Node.createURI("mailto:alice@example")));
    
Dataset ds = DatasetImpl.wrap(datasetGraph);
    
String szQuery = " PREFIX foaf: <http://xmlns.com/foaf/0.1/>"    +
" PREFIX dc: <http://purl.org/dc/elements/1.1/> "    +
" SELECT ?who ?graph ?mbox "                         +
" FROM NAMED <http://cybersecurity/alice>"            +
" FROM NAMED <http://cybersecurity/bob>"              +
" WHERE "                                            +
" { "                                                +
" ?graph dc:publisher ?who . "                       +
" GRAPH ?graph { ?x foaf:mbox ?mbox } "              +
" } ";
    
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

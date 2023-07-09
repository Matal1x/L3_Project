package test;

import com.hp.hpl.jena.graph.*;
import com.hp.hpl.jena.query.*;
import oracle.rdf.kv.client.jena.*;

public class PoolConnectionExample
{
  
  public static void main(String[] args) throws Exception
  {
    
String szStoreName  = "airlio";
String szHostName   = "localhost";
String szHostPort   = "5000";
String szModelName  = "http://cybersecurity/";
int iPoolSize = 5;
    
// Property of the pool: wait if no connection is available 
// at request.
boolean bWaitIfBusy = true;
    
System.out.println("Creating OracleNoSQL pool");
OracleNoSqlPool pool = OracleNoSqlPool.createInstance(szStoreName, szHostName,  szHostPort, iPoolSize, bWaitIfBusy, true); //lazyInit
        
System.out.println("Done creating OracleNoSql pool");
    
// grab an Oracle NoSQL connection and do something
System.out.println("Get a connection from the pool");
OracleNoSqlConnection conn = pool.getResource();
    
OracleModelNoSql model = OracleModelNoSql.createOracleModelNoSql(szModelName, conn);
    
System.out.println("Clear model");
//model.removeAll();
    
model.getGraph().add(Triple.create(Node.createURI("u:John"), Node.createURI("u:cousinOf"), Node.createURI("u:Jackie")));
    
model.close();
    
//return connection back to the pool 
conn.dispose();
    
// grab another Oracle NoSQL connection and do something
System.out.println("Get a connection from the pool");
conn = pool.getResource();
model = OracleModelNoSql.createOracleModelNoSql(szModelName, conn);
String queryString = "select ?x ?y ?z WHERE {?x ?y ?z}";
    
System.out.println("Execute query " + queryString);
    
Query query = QueryFactory.create(queryString) ;
QueryExecution qexec = QueryExecutionFactory.create(query, model);
    
try {
      ResultSet results = qexec.execSelect();
      ResultSetFormatter.out(System.out, results, query);
    }
    
finally {
      qexec.close();
    } 
    
model.close();
    
//return connection back to the pool 
conn.dispose();
    
// Close pool. 
// This will close all resources even if they have not been freed up
System.out.println("Close pool, this will close all resources");
pool.close();    
  }
}
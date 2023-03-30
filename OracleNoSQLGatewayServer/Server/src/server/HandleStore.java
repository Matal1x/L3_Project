package server;



import com.hp.hpl.jena.graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import oracle.kv.Consistency;
import oracle.kv.Durability;
import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import oracle.kv.Key;
import oracle.kv.RequestTimeoutException;
import oracle.kv.lob.InputStreamVersion;
import oracle.kv.lob.PartialLOBException;


import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.graph.GraphFactory;
import com.hp.hpl.jena.update.*;
import com.hp.hpl.jena.sparql.resultset.SPARQLResult;


import java.io.StringWriter;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import oracle.rdf.kv.client.jena.OracleNoSqlConnection;


import oracle.rdf.kv.client.jena.OracleGraphNoSql;
import oracle.rdf.kv.client.jena.OracleModelNoSql;
import oracle.rdf.kv.client.jena.OracleNamedGraphNoSql;



public class HandleStore {

    private static OracleNoSqlSecureConnection secureConn;
    KVStoreConfig kconfig;
    KVStore kvstore;
    String hostName, storeName, hostPort, userName, password;
    private String blobPfx ;
    private String imgSfx ;
    public static final String NOSQLDB_ERROR_EXCEPTION_MESSAGE = "An error occured within the Oracle NoSQL DataBase datastore";
    private OracleNoSqlConnection conn;
   
    HandleStore(String hostName, String storeName, String hostPort) {
        this.storeName = storeName;
        this.hostName = hostName;
        this.hostPort = hostPort;
        this.blobPfx = storeName+"/Lob";                    
        try {
            this.kconfig = new KVStoreConfig(storeName, hostName + ":" + hostPort);
            this.kvstore = KVStoreFactory.getStore(kconfig);
                        } catch (Exception e) {
                    throw new RuntimeException(NOSQLDB_ERROR_EXCEPTION_MESSAGE);
             } 
           this.conn = OracleNoSqlConnection.createInstance(storeName, hostName, hostPort);
       }
    
    HandleStore(String hostName, String storeName, String hostPort, String userName, String password) {
        this.storeName = storeName;
        this.hostName = hostName;
        this.hostPort = hostPort;
        this.userName = userName;
        this.password = password;
        this.blobPfx = storeName+"/Lob";
        this.secureConn= OracleNoSqlSecureConnection.createInstance(storeName, hostName, hostPort, userName, password) ;            
        
        //  this.conn = onsc.createInstance(storeName, hostName, hostPort, userName, password);
      }

    public void putLob(String filename) {
       System.out.println("inside putLob for file "+filename);
       this.imgSfx=filename;
       final Key key = Key.createKey( Arrays.asList(blobPfx, filename, imgSfx));
       File lobFile = new File(filename);
       try {
          FileInputStream fis  = new FileInputStream(lobFile);
          System.out.println("getting input stream of the file "); 
          kvstore.putLOB(key, fis, Durability.COMMIT_WRITE_NO_SYNC, 5, TimeUnit.SECONDS);
          System.out.println("file inserted in store"); 
       } catch (FileNotFoundException fnf) {
                System.err.println("Input file not found.");
                System.err.println("FileNotFoundException: " + fnf.toString());
                fnf.printStackTrace();
                System.exit(-1);
       } catch (RequestTimeoutException rte) {
                System.err.println("A LOB chunk was either not read or");
                System.err.println("not written in the alloted time.");
                System.err.println("RequestTimeoutException: " + rte.toString());
                rte.printStackTrace();
                System.exit(-1);
      } catch (IOException e) {
                System.err.println("IO Exception: " + e.toString());
                e.printStackTrace();
                System.exit(-1);
            }
  }
    public void deleteLob(String filename) {
        this.imgSfx=filename;
       final Key key = Key.createKey( Arrays.asList(blobPfx, filename, imgSfx));
       
       try {
          
          kvstore.deleteLOB(key, Durability.COMMIT_WRITE_NO_SYNC, 5, TimeUnit.SECONDS);
          System.out.println("lob deleted from store"); 
       } catch (RequestTimeoutException rte) {
                System.err.println("A LOB chunk was either not read or");
                System.err.println("not written in the alloted time.");
                System.err.println("RequestTimeoutException: " + rte.toString());
                rte.printStackTrace();
                System.exit(-1);
      }
    }
    
    public void getLob(String filename) {
       this.imgSfx=filename;     
       final Key key = Key.createKey( Arrays.asList(blobPfx, filename, imgSfx));
       
       try {
           InputStreamVersion istreamVersion = kvstore.getLOB(key, Consistency.NONE_REQUIRED, 5, TimeUnit.SECONDS);
           InputStream stream = istreamVersion.getInputStream();
           FileOutputStream outputStream = new FileOutputStream(filename);
           int b = 0;
           while ((b = stream.read()) != -1) {
                     outputStream.write(b);
                     outputStream.flush();}
                 
      } catch (RequestTimeoutException rte) {
                System.err.println("A LOB chunk was either not read or");
                System.err.println("not written in the alloted time.");
                System.err.println("RequestTimeoutException: " + rte.toString());
                rte.printStackTrace();
                System.exit(-1);
            } catch (PartialLOBException ple) {
                System.err.println("Retrieval (getLOB()) only retrieved");
                System.err.println("a portion of the requested object.");

                System.err.println("PartialLOBException: " + ple.toString());
                ple.printStackTrace();
                System.exit(-1);
            } catch (IOException e) {
                System.err.println("IO Exception: " + e.toString());
                e.printStackTrace();
                System.exit(-1);
            }
        }
    public SPARQLResult get(String modelName, String szquery) throws CloneNotSupportedException {
        System.out.println("Going to the Universal Store   "+ storeName);
        OracleNoSqlConnection conn = OracleNoSqlConnection.createInstance(storeName, hostName, hostPort);
         Model model = OracleModelNoSql.createOracleModelNoSql(modelName, conn);
         System.out.println("Creating connection with  :   " + modelName);
         System.out.println("Size: " +model.size());
         System.out.println("ModelIsEmpty: "+model.isEmpty());
         System.out.println(szquery);
         
        Query query =  QueryFactory.create(szquery, Syntax.syntaxSPARQL_11);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        
        if (query.isSelectType()) {  System.out.println("type is SELECT ");  ResultSet results =  qexec.execSelect(); results = ResultSetFactory.copyResults( results); return new SPARQLResult(results);}
        
        if (query.isConstructType()) { System.out.println("type is CONSTRUCT "); Model model1  =  qexec.execConstruct(); return new SPARQLResult(model1) ; }
        
        if (query.isDescribeType()) {  System.out.println("type is DESCRIBE "); Model model1  =  qexec.execDescribe(); return new SPARQLResult(model1) ;}
        
        if (query.isAskType()){System.out.println("type is ASK "); Boolean b= qexec.execAsk(); return new SPARQLResult(b) ;}
        
   //      results = ResultSetFactory.copyResults( results);
        
    //    qexec.close();
   //     model.close();
   //     conn.dispose();

        System.out.println("Leaving the store   :  " + storeName);
        return new SPARQLResult (false);
  
     }
 
    public SPARQLResult put(String modelName, String szquery) throws CloneNotSupportedException {
        System.out.println("Create Connection Instance " + modelName);
        OracleNoSqlConnection conn = OracleNoSqlConnection.createInstance(storeName, hostName, hostPort);
        System.out.println("create model from onnection  " + conn.getStoreName());
        Model model = OracleModelNoSql.createOracleModelNoSql(modelName, conn);

        System.out.println("Executing query " + szquery);
        UpdateAction.parseExecute(szquery, model);
        model.close();
        conn.dispose();
       
       
        return new SPARQLResult(true) ; 
    }

   
    public SPARQLResult getModel(String modelName) {
        System.out.println("Going to the Universal Store   "+ storeName);
        OracleNoSqlConnection conn = OracleNoSqlConnection.createInstance(storeName, hostName, hostPort);
        System.out.println("Creating Connection with  :   " + modelName);
        Model model = OracleModelNoSql.createOracleModelNoSql(modelName, conn);
         
         return new SPARQLResult (model);
     }
  
    
   
   
    public SPARQLResult putModel(String modelName, String modelToPut) throws CloneNotSupportedException {
        
        OracleNoSqlConnection conn = OracleNoSqlConnection.createInstance(storeName, hostName, hostPort);
        Model model =  OracleModelNoSql.createOracleModelNoSql(modelName, conn);
        Model modelToBePut = ModelFactory.createDefaultModel();
        StringReader  sr =new StringReader(modelToPut);
        
        //  RDFDataMgr.read(modelToBePut, in,null, RDFLanguages.TURTLE) ;
        modelToBePut.read(sr, null,"TURTLE");
        model.add(modelToBePut);
        model.close();
        conn.dispose();
        return new SPARQLResult(true) ;
    }
    
    
    public Graph createGraph(String graphName){
            OracleNoSqlConnection conn = OracleNoSqlConnection.createInstance(storeName, hostName, hostPort);
            OracleGraphNoSql graph = new OracleNamedGraphNoSql(graphName, conn);
            return graph;
        }
    public Graph createGraphMem(String graphName){
           Graph innerGraph=GraphFactory.createGraphMem();
             return innerGraph;
        }
    
    public Model createModel(String ModelName){
             OracleNoSqlConnection conn = OracleNoSqlConnection.createInstance(storeName,  hostName, hostPort);
               // Create model from named graph
                Model model = OracleModelNoSql.createOracleModelNoSql(ModelName, conn);
              // Clear model
                model.removeAll();
            return model;
         }
    
    
    
    //FOR PYTHON
    public String PythonResultSetFormatterText(ResultSet rs){
        String resultsAsString = ResultSetFormatter.asText(rs);
        return resultsAsString;
        }


    public List<QuerySolution> PythonResultSetToList(ResultSet rs){
        return ResultSetFormatter.toList(rs);
    }
    
    public void PrintSolutions(List<QuerySolution>  querys){
        for (QuerySolution qs : querys) {
        System.out.println("subject: "+qs.get("s").toString());
        System.out.println("predicat: "+qs.get("p").toString());
        System.out.println("object: "+qs.get("o").toString()+ "\n");
    }
    }
    public ArrayList<String> PythonResultSetToJSON(ResultSet rs){
        List<QuerySolution> querys= ResultSetFormatter.toList(rs);
        ArrayList<String> jsonList = new ArrayList<String>();
            for (QuerySolution qs : querys) {
                qs.
                System.out.println(qs.);
                String smol = String.format("{\"s\": \"%s\", \"p\": \"%s\", \"o\": \"%s\"}" , qs.get("s").toString(),qs.get("p").toString(),qs.get("o").toString());
                System.out.println(smol);
                jsonList.add(smol);
            }
        return jsonList;
    }
    
    public String PythonModelToTurtle(Model md){
        StringWriter stringWriter = new StringWriter();
         md.write(stringWriter, "TURTLE");
         System.out.println(stringWriter.toString());
         return stringWriter.toString();
    }
     
}


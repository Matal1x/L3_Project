package server;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.GraphUtil;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.resultset.SPARQLResult;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import java.io.StringWriter;


public class Test {
    public Test() {
        super();
    }

    public static void main(String[] args) {
        
     //   String modelName = "<http://localhost:5000/Birth>";
        String select    = "SELECT ?s ?p ?o WHERE { GRAPH <http://somewhere/SarahJones/> {?s ?p ?o}}";  
        String construct = "CONSTRUCT { ?s ?p ?o } WHERE { ?s ?p ?o}";   
        String describe  = "DESCRIBE ?s ?p ?o WHERE { ?s ?p ?o}";    
        String ask       = " ASK WHERE { ?s ?p ?o }";
        String query1 = "prefix vCard: <http://www.w3.org/2001/vcard-rdf/3.0#> " +//53 
                        "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +//57 
                        "INSERT DATA { <http://somewhere/MattJones/> <vCard:FN> \"Matt Jones\"; <vCard:N> <_:b0> ." +//89
                                                                            "<_:b0> <vCard:Family> \"Jones\"; <vCard:Given> \"Matthew\". }" ; 
        String query2 = "prefix vCard: <http://www.w3.org/2001/vcard-rdf/3.0#> " +//53
                       "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +//57                
                       "INSERT DATA { <http://somewhere/RebeccaSmith/> <vCard:FN> \"Becky Smith\" ; <vCard:N> <_:b1> ." +//80
                                                                              "<_:b1> <vCard:Family> \"Smith\"; <vCard:Given> \"Rebecca\" .}" ;//60
        String query3 = "prefix vCard: <http://www.w3.org/2001/vcard-rdf/3.0#> " +//53
                       "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +//57                                                                                         
                       "INSERT DATA { <http://somewhere/JohnSmith/> <vCard:FN> \"John Smith\"; <vCard:N> <_:b2> . " +//75
                                                                             "<_:b2> <vCard:Family> \"Smith\"; <vCard:Given> \"John\".}" ;//63
        String query4 = "prefix vCard: <http://www.w3.org/2001/vcard-rdf/3.0#> " +//53
                       "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +//57                             
                       "INSERT DATA { <http://somewhere/SarahJones/> <vCard:FN> \"Sarah Jones\" ; <vCard:N> <_:b3> ." +//78
                                                                                 "<_:b3> <vCard:Family>  \"Jones\"; <vCard:Given> \"Sarah\" .}" ;//63
             
        String graphName= "<http://g2>";
        String create = "CREATE GRAPH "+ graphName;
        String insert1 = "INSERT DATA { <u:r> <u:p> 700 }";
        String insert2 = "INSERT DATA { <u:r> <u:p> 700 }";
       
        
        String storeName  = "airlio";
        String hostName   = "localhost";
        String hostPort   = "5000";
        String modelName  = "http://localhost:5000/Birth";
        String query =" SELECT ?s ?p ?o WHERE { ?s ?p ?o } ORDER BY ?s ?p";
        
        String modelToPut= null;
        HandleStore hs = new HandleStore(hostName, storeName, hostPort);
        
        String kquery = "SELECT ?s ?p ?o WHERE { <http://somewhere/JohnSmith/> ?p ?o ." +
            "?s ?p ?o.}";
        
   /**************************************get with NoSQL Connection*****************************************************/  
          
         String somequery = "SELECT ?s ?p ?o WHERE { <http://cybersecurity.org/DNS-over-HTTPS/0> ?p ?o ." +
            " ?s <http://cybersecurity.org/DNS-over-HTTPS/DoH> ?o.}";
          
        SPARQLResult resSelect;
        try {
            resSelect = hs.get("http://localhost:5000/Cybersecurity/DoH", somequery);
       
        System.out.println("**********************************select ***************************************************");
       
        if (resSelect.isModel()) {System.out.println("resultSet of Select is a model");
                                  Model model5 = resSelect.getModel();
                                  StmtIterator is=  model5.listStatements();
                                      while (is.hasNext()){
                                          Statement st = is.next();
                                          System.out.println("subject :" +st.getSubject().toString()+"   predicate :" +st.getPredicate().toString()+"  object :" +st.getObject().toString() );
                                      }}
        if (resSelect.isResultSet()){System.out.println("resultSet of Select is a resultSet");
                                     ResultSet rs  = resSelect.getResultSet();
                                     rs = ResultSetFactory.copyResults(rs) ;
                                     
                                     System.out.println(" ********as Formatter out ");
                                     ResultSetFormatter.out(System.out, rs);
                                     System.out.println(" ********as QuerySolution out "+"  "+rs.getRowNumber());
                                     ResultSetFormatter.asText(rs);
                                     System.out.println("Select Result :"+modelToPut);
                                    }
        if (resSelect.isBoolean()){System.out.println("resultSet of Select is a boolean");
                                   Boolean b = resSelect.getBooleanResult();
                                   System.out.println("Select Boolean Result :"+b);}
        if (resSelect.isGraph() ){System.out.println("resultSet of Select is a graph");
                                  Graph graph = resSelect.getModel().getGraph();
                                  ExtendedIterator<Triple> it = GraphUtil.findAll(graph);
                                         while (it.hasNext()){
                                             Triple t = it.next();
                                             System.out.println("subject :" +t.getSubject().toString()+"   predicate :" +t.getPredicate().toString()+"  object :" +t.getObject().toString() );
                                         };}
        } catch (CloneNotSupportedException e) {
        }
        
        /*
        System.out.println("**********************************construct ***************************************************");
        
        SPARQLResult resConstruct;
        try {
            resConstruct = hs.get(modelName, construct);
        
        
        if (resConstruct.isModel()) {System.out.println("\nresultSet of Construct is a model");
                                      Model model6 = resConstruct.getModel();
                                      StmtIterator is=  model6.listStatements();
                                      while (is.hasNext()){
                                          Statement st = is.next();
                                          System.out.println("subject :" +st.getSubject().toString()+"   predicate :" +st.getPredicate().toString()+"  object :" +st.getObject().toString() );
                                      }}
        if (resConstruct.isResultSet()){ System.out.println("\nresultSet of Construct is a resultset");
                                         ResultSet res = resConstruct.getResultSet();
                                         modelToPut = ResultSetFormatter.asText(res);
                                         System.out.println("Construct Result :"+modelToPut);}
        if (resConstruct.isBoolean()){System.out.println("\nresultSet of Construct is a bool");
            Boolean b = resConstruct.getBooleanResult();
                                   System.out.println("Construct Boolean Result :"+b);}
        if (resConstruct.isGraph() ){System.out.println("\nresultSet of Construct is a graph");
                                      Graph graph = resConstruct.getModel().getGraph();
                                      ExtendedIterator<Triple> it = GraphUtil.findAll(graph);
                                         while (it.hasNext()){
                                             Triple t = it.next();
                                             System.out.println("subject :" +t.getSubject().toString()+"   predicate :" +t.getPredicate().toString()+"  object :" +t.getObject().toString() );
                                         }}
        } catch (CloneNotSupportedException e) {
        }
        
       
        System.out.println("**********************************describe ***************************************************");
            SPARQLResult resDescribe;
            try {
                resDescribe = hs.get(modelName, describe);
        if (resDescribe.isModel()) {System.out.println("resultSet of Describe is a model");
                                    Model model5 = resDescribe.getModel();
                                  StmtIterator is=  model5.listStatements();
                                      while (is.hasNext()){
                                          Statement st = is.next();
                                          System.out.println("subject :" +st.getSubject().toString()+"   predicate :" +st.getPredicate().toString()+"  object :" +st.getObject().toString() );
                                      }}
        if (resDescribe.isResultSet()){System.out.println("resultSet of Describe is a reseultset");
                                      ResultSet res1 = resDescribe.getResultSet();
                                     modelToPut = ResultSetFormatter.asText(res1);
                                     System.out.println("Select Result :"+modelToPut);}
        if (resDescribe.isBoolean()){Boolean b = resDescribe.getBooleanResult();
                                   System.out.println("Select Boolean Result :"+b);}
        if (resDescribe.isGraph() ){System.out.println("resultSet of Describe is a graph");
                                     Graph graph = resDescribe.getModel().getGraph();
                                     ExtendedIterator<Triple> it = GraphUtil.findAll(graph);
                                         while (it.hasNext()){
                                             Triple t = it.next();
                                             System.out.println("subject :" +t.getSubject().toString()+"   predicate :" +t.getPredicate().toString()+"  object :" +t.getObject().toString() );
                                         }}
        } catch (CloneNotSupportedException e) {
        }
        */
        /*
        System.out.println("**********************************Ask***************************************************");
        
        SPARQLResult resAsk;
        try {
            resAsk = hs.get(modelName, ask);
        
        
        Boolean b = resAsk.isBoolean();
        System.out.println("Ask Result :"+b);
        if (resAsk.isModel()) {System.out.println("resultSet of ASk is a model");
                                        Model model5 = resAsk.getModel();
                                      StmtIterator is=  model5.listStatements();
                                          while (is.hasNext()){
                                              Statement st = is.next();
                                              System.out.println("subject :" +st.getSubject().toString()+"   predicate :" +st.getPredicate().toString()+"  object :" +st.getObject().toString() );
                                          }}
        if (resAsk.isResultSet()){System.out.println("resAsk of ASK is a reseultset");
                                          ResultSet res1 = resAsk.getResultSet();
                                         modelToPut = ResultSetFormatter.asText(res1);
                                         System.out.println("Select Result :"+modelToPut);}
        if (resAsk.isBoolean()){ System.out.println("resAsk of ASK is a booleanresult");
            Boolean bo = resAsk.getBooleanResult();
                                       System.out.println("Select Boolean Result :"+bo);}
        if (resAsk.isGraph() ){System.out.println("resAsk of ASK is a graph");
                                         Graph graph = resAsk.getModel().getGraph();
                                         ExtendedIterator<Triple> it = GraphUtil.findAll(graph);
                                             while (it.hasNext()){
                                                 Triple t = it.next();
                                                 System.out.println("subject :" +t.getSubject().toString()+"   predicate :" +t.getPredicate().toString()+"  object :" +t.getObject().toString() );
                                             }}
            } catch (CloneNotSupportedException e) {
            }
        }
        
        /*
        System.out.println("**********************************get model***************************************************");
        SPARQLResult model = hs.getModel(modelName);
        if (model.isModel()) {System.out.println("resultSet of getModel is a model");
                                      Model model6 = model.getModel();
                                      StmtIterator is=  model6.listStatements();
                                      while (is.hasNext()){
                                          Statement st = is.next();
                                          System.out.println("subject :" +st.getSubject().toString()+"   predicate :" +st.getPredicate().toString()+"  object :" +st.getObject().toString() );
                                      }}
        if (model.isResultSet()){ System.out.println("resultSet of getModel is a resultset");
                                         ResultSet res = model.getResultSet();
                                         modelToPut = ResultSetFormatter.asText(res);
                                         System.out.println("Construct Result :"+modelToPut);}
        if (model.isBoolean()){  Boolean  b = model.getBooleanResult();
                                   System.out.println("getModel Boolean Result :"+b);}
        if (model.isGraph() ){System.out.println("resultSet of getModel is a graph");
                                      Graph graph = model.getModel().getGraph();
                                      ExtendedIterator<Triple> it = GraphUtil.findAll(graph);
                                         while (it.hasNext()){
                                             Triple t = it.next();
                                             System.out.println("subject :" +t.getSubject().toString()+"   predicate :" +t.getPredicate().toString()+"  object :" +t.getObject().toString() );
                                         }}
        */
        /*
        System.out.println("**********************************update ***************************************************");
        SPARQLResult insertRes;
        try {
            insertRes = hs.put(modelName, query1);
            System.out.println("insert result "+insertRes.getBooleanResult());
        } catch (CloneNotSupportedException e) {
        }
        
        try {
            insertRes = hs.put(modelName, query2);
            System.out.println("insert result "+insertRes.getBooleanResult());
        } catch (CloneNotSupportedException e) {
        }
        try {
            insertRes = hs.put(modelName, query3);
            System.out.println("insert result "+insertRes.getBooleanResult());
        } catch (CloneNotSupportedException e) {
        } 
        
        try {
            insertRes = hs.put(modelName, query4);
            System.out.println("insert result "+insertRes.getBooleanResult());
        } catch (CloneNotSupportedException e) {
        }  
        try {
            SPARQLResult createRes = hs.put( graphName, create);
            System.out.println("create result "+createRes.getBooleanResult());
        } catch (CloneNotSupportedException e) {
        }  
        
       
        
     
     
     */
        
    /***********************************NoSQL connection****************************************************************/
    /*
    System.out.println("**********************************get model with connection ***************************************************");
    
       SPARQLResult model2 = hs.getModel(graphName);
       System.out.println(model2.isModel());
        StmtIterator  is=  model2.getModel().listStatements();
        System.out.println(is.hasNext());
                while (is.hasNext()){
                    Statement st = is.next();
                   System.out.println("subject :" +st.getSubject().toString()+"   predicate :" +st.getPredicate().toString()+"  object :" +st.getObject().toString() );
                }

        try {
            SPARQLResult res1 = hs.put(graphName, insert1);
            System.out.println("insert1 "+insert1);
           
            System.out.println(res1.getBooleanResult());
       
            
        } catch (CloneNotSupportedException e) {
        }

        try {
            SPARQLResult res1 = hs.put(graphName, insert2);
            System.out.println("insert2 "+insert2);
            System.out.println(res1.getBooleanResult());
            
        } catch (CloneNotSupportedException e) {
        }
        SPARQLResult model3 = hs.getModel( graphName);
        StringWriter out = new StringWriter();
        String syntax = "RDF/XML-ABBREV"; // also try "N-TRIPLE" and "TURTLE"
        model3.getModel().write(out, syntax);
        String result = out.toString();
        
        SPARQLResult res4;
        try {
            res4 = hs.putModel(modelName, result);
            System.out.println(res4.isBoolean());
        } catch (CloneNotSupportedException e) {
        }
        */
        
    }
    }       
    

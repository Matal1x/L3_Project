package test;
/*
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.sparql.core.DatasetImpl;
import com.hp.hpl.jena.util.FileManager;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;

import oracle.rdf.kv.client.jena.DatasetGraphNoSql;
import oracle.rdf.kv.client.jena.OracleGraphNoSql;
import oracle.rdf.kv.client.jena.OracleModelNoSql;
import oracle.rdf.kv.client.jena.OracleNoSqlConnection;

import org.openjena.riot.Lang;


public class ContactStore {
    String szStoreName = "semep";
    String szHostName = "localhost";
    String szHostPort = "5000";
    String szModelName = "http://ContactStore";
    public void writeTripleInRepository(InsertTriple triple) {
        HandleStore testHandleStore = new HandleStore();
        testHandleStore.removeAll(szHostName, szStoreName, szHostPort, szModelName);
        testHandleStore.writeStringTripleInStore(szHostName, szStoreName, szHostPort, szModelName, triple);
    }
    
    public ResultSet ReadFromRepository(  Select select){
        HandleStore testHandleStore = new HandleStore();
        ResultSet res = testHandleStore.findAllBySelect(szHostName, szStoreName, szHostPort, szModelName, select);
        return res;
     }
    public static void main(String[] args) {
        String szStoreName = "semep";
        String szHostName = "localhost";
        String szHostPort = "5000";
        String szModelName = "http://ContactStore";
        String resource = "C:\\JDeveloper\\VisualizationTemporalNetApp\\Model\\src\\model\\model1.ttl";
        Model model = FileManager.get().loadModel(resource);
     
       System.out.println("Create Oracle NoSQL connection");
       OracleNoSqlConnection conn    = OracleNoSqlConnection.createInstance(szStoreName, szHostName, szHostPort);
       Model model1 = OracleModelNoSql.createOracleModelNoSql(szModelName, conn);
               
       model1.add(model);
       String szQuery = " PREFIX fn: <http://www.w3.org/2005/xpath-functions#> "    +
                         " SELECT ?s ?p ?o"+
                         " WHERE { ?s ?p ?o} ";
                System.out.println("Execute query " + szQuery);
               
                Query query = QueryFactory.create(szQuery, Syntax.syntaxARQ);
                QueryExecution qexec = QueryExecutionFactory.create(query, model1);
                try {
                  ResultSet results = qexec.execSelect();
                  ResultSetFormatter.out(System.out, results, query);
                }
                
                finally {
                  qexec.close();
                }
  
  
         

     
 
       String prefixe = " PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> ";
       
        String triple =  
                                     "       <u:Address> <u:hasAddressIndex> <u:AddressIndex> . " +
                                     "        <u:AddressIndex> <u:hasValue> <u:adr01> ; " +
                                     "                         <u:hasValue> <u:adr02> ; " +
                                     "                         <u:hasValue> <u:adr03> ; " +
                                     "                         <u:hasValue> <u:adr04> ; " +
                                     "                         <u:hasValue> <u:adr05> . " +
                                                        
                                     "       <u:adr01> <u:hasNumber> 45 ; " +
                                     "                 <u:hasFloor> 1 ; " +
                                     "                 <u:hasStreet> '45,Rue Larbi Ben Mhidi' ; " +
                                     "                 <u:hasCodePostal> 31000 ; " +
                                     "                 <u:hasRoomNumber> 4 ; " +
                                     "                 <u:hasPesronsNumber> 5 ; " +
                                     "                 <u:hasLongitude> 0.622222 ; " + 
                                     "                 <u:hasLattitude> 35.6 ; " +
                                     "                 <u:hasHumidity> '58%' ; " + 
                                     "                 <u:hasTemperature> 18 ; " +
                                     "                 <u:hasClimate> 'mediterraneen' . " +
                                     "      <u:adr02> <u:hasNumber> 14 ; " +
                                     "                 <u:hasFloor> 3 ; " +
                                     "                 <u:hasStreet> '03,Rue La khdar Mehdi' ; " +
                                     "                 <u:hasCodePostal> 27000 ; " +
                                     "                 <u:hasRoomNumber> 3 ; " +
                                     "                 <u:hasPesronsNumber> 5 ; " +
                                     "                 <u:hasLongitude> 0.644444 ; " + 
                                     "                 <u:hasLattitude> 35.4 ; " +
                                     "                 <u:hasHumidity> '57%' ; " + 
                                     "                 <u:hasTemperature> 17 ; " +
                                     "                 <u:hasClimate> 'mediterraneen' . " +
                                     "      <u:adr03> <u:hasNumber> 4 ; " +
                                     "                <u:hasFloor> 2 ; " +
                                     "                <u:hasStreet> '20,Rue Houha Mohamed' ; " +
                                     "                <u:hasCodePostal> 31000 ; " +
                                     "                <u:hasRoomNumber> 4 ; " +
                                     "                <u:hasPesronsNumber> 3 ; " +
                                     "                <u:hasLongitude> 0.622222 ; " + 
                                     "                <u:hasLattitude> 35.6 ; " +
                                     "                <u:hasHumidity> '58%' ; " + 
                                     "                <u:hasTemperature> 18 ; " +
                                     "                <u:hasClimate> 'mediterraneen' . " +
                                    
                                     "     <u:adr04> <u:hasNumber> 31 ; " +
                                     "               <u:hasFloor> 1 ; " +
                                     "               <u:hasStreet> '45,Rue Ahmed Blaha' ; " +
                                     "               <u:hasPostalCode> 27000 ; " +  
                                     "               <u:hasRoomNumber> 3 ; " +
                                     "               <u:hasPersonsNumber> 5 ; " +
                                     "               <u:hasLongitude> 0.0822222 ; " +
                                     "               <u:hasLattitude> 35.922222 ; " + 
                                     "               <u:hasHumidity> '63%' ; " +
                                     "               <u:hasTemperature> 24 ; " + 
                                     "               <u:hasClimate> 'clair' . " +
                                     "    <u:adr05> <u:hasNumber> 29 ; " +
                                     "              <u:hasFloor> 2 ; " +
                                     "              <u:hasStreet> '19,Rue Yveton' ; " +
                                     "              <u:hasPostalCode> 31000 ; " +
                                     "              <u:hasRoomNumber> 5 ; " +
                                     "              <u:hasPersonsNumber> 4 ; "+
                                     "              <u:hasLongitude> 0.644444 ; " + 
                                     "              <u:hasLattitude> 35.7 ; " +
                                     "              <u:hasHumidity> '60%' ; " + 
                                     "              <u:hasTemperature> 20 ; " +
                                     "              <u:hasClimate> 'mediterraneen' . " +
                                    
                                     "    <u:wilaya> <u:hasWilayaIndex> <u:WilayaIndex> . " +
                                     "    <u:WilayaIndex> <u:hasValue> <u:wilaya_27> ; " +
                                     "                    <u:hasValue> <u:wilaya_31> . " +
                                     "    <u:wilaya_31> <u:hasName> 'Oran' ; " +
                                     "               <u:hasPopulation> 1584607 ; " + 
                                     "              <u:hasSurface> 2114 ; " +
                                     "              <u:hasLongitude> 0.633333 ; " + 
                                     "              <u:hasLattitude> 35.7 ; " +
                                     "              <u:hasHumidity> '60%' ; " + 
                                     "              <u:hasTemperature> 17 ; " +
                                     "              <u:hasClimate> 'mediterraneen' ; " +
                                     "              <u:hasURL> 'localhost:7001' . " +
                                     "    <u:wilaya_27> <u:hasName> 'Mosta'; " +
                                     "                  <u:hasPopulation> 737118 ; " + 
                                     "                  <u:hasSurface> 2269 ; " +
                                     "                  <u:hasLongitude> 0.0833333 ; " +
                                     "                  <u:hasLattitude> 35.933333 ; " + 
                                     "                  <u:hasHumidity> '65%' ; " +
                                     "                  <u:hasTemperature> 23 ; " + 
                                     "                  <u:hasClimate> 'clair' ; " +
                                     "                  <u:hasURL> 'localhost:7001' . " +
                                     "       <u:daira> <u:hasDairaIndex> <u:DairaIndex> . " +
                                     "       <u:DairaIndex> <u:hasValue> <u:daira_31100> ; " +
                                     "                      <u:hasValue> <u:daira_31200> ; " +
                                     "                      <u:hasValue> <u:daira_31300> ; " +
                                     "                      <u:hasValue> <u:daira_27100> ; " +
                                     "                      <u:hasValue> <u:daira_27200> . " + 
                                     "       <u:daira_31100> <u:hasName> 'Oran' ; " +
                                     "                       <u:hasPopulation> 668890 ; " +
                                     "                       <u:hasSurface> 64 ; " +
                                     "                       <u:hasLongitude> 0.602617 ; " +
                                     "                       <u:hasLattitude> 35.699525 ; " +
                                     "                       <u:hasHumidity> '62%' ; " +
                                     "                       <u:hasTemperature> 18 ; " +
                                     "                       <u:hasClimate> 'mediterraneen' ; " +
                                     "                       <u:hasURL> 'localhost:7001' ; " + 
                                     "                       <u:hasWilayaIndex> <u:wilaya_31> . " +
                                     "       <u:daira_31200> <u:hasName> 'Es Senia' ; " +
                                     "                       <u:hasPopulation> 262263 ; " +
                                     "                       <u:hasSurface> 181.56 ; " +
                                     "                       <u:hasLongitude> 0.616666 ; " +
                                     "                       <u:hasLattitude> 35.649545 ; " +
                                     "                       <u:hasHumidity> '58%' ; " +
                                     "                       <u:hasTemperature> 17 ; " +
                                     "                       <u:hasClimate> 'sec' ; " +
                                     "                       <u:hasURL> 'localhost:7001' ; "  +
                                     "                       <u:hasWilayaIndex> <u:wilaya_31> . " +
                                     "       <u:daira_31300> <u:hasName> 'Boutlélis' ; " +
                                     "                       <u:hasPopulation> 61308 ; " +
                                     "                       <u:hasSurface> 672.17 ; " +
                                     "                       <u:hasLongitude> 0.897360 ; " +
                                     "                       <u:hasLattitude> 35.572546 ; " +
                                     "                       <u:hasHumidity> '55%' ; " +
                                     "                       <u:hasTemperature> 20 ; " +
                                     "                       <u:hasClimate> 'tempéré_chaud' ; " +
                                     "                       <u:hasURL> 'localhost:7001' ; " +
                                     "                       <u:hasWilayaIndex> <u:wilaya_31> . " +
                                     "       <u:daira_27100> <u:hasName> 'Mostaganem' ; " +
                                     "                       <u:hasPopulation> 165571 ; " +
                                     "                       <u:hasSurface> 50 ; " +
                                     "                       <u:hasLongitude> 0.074296 ; " +
                                     "                       <u:hasLattitude> 35.924921 ; " +
                                     "                       <u:hasHumidity> '64%' ; " +
                                     "                       <u:hasTemperature> 22 ; " +
                                     "                       <u:hasClimate> 'Clair' ; " +
                                     "                       <u:hasURL> 'localhost:7001' ; " +
                                     "                       <u:hasWilayaIndex> <u:wilaya_27>  . " +
                                     "       <u:daira_27200> <u:hasName> 'Mameche' ; " +
                                     "                       <u:hasPopulation> 61115 ; " +
                                     "                       <u:hasSurface> 138 ; " +
                                     "                       <u:hasLongitude> 0.111494 ; " +
                                     "                       <u:hasLattitude> 35.858846 ; " +
                                     "                       <u:hasHumidity> '63%' ; " +
                                     "                       <u:hasTemperature> 23 ; " +
                                     "                       <u:hasClimate> 'Clair' ; " +
                                     "                       <u:hasURL> 'localhost:7001' ; " +
                                     "                       <u:hasWilayaIndex> <u:wilaya_27> . " +
                                     "       <u:commune> <u:hasCommuneIndex> <u:CommuneIndex> . " +
                                     "        <u:CommuneIndex> <u:hasValue> <u:commune_31110> ; " +
                                     "                         <u:hasValue> <u:commune_31210> ; " +
                                     "                         <u:hasValue> <u:commune_31310> ; " +
                                     "                         <u:hasValue> <u:commune_27210> ; " +
                                     "                         <u:hasValue> <u:commune_27220> ; " +
                                     "                         <u:hasValue> <u:commune_27230> . " +
                                     "        <u:commune_31110> <u:hasName> 'Oran' ; " +
                                     "                          <u:hasPopulation> 674273 ; " +
                                     "                          <u:hasSurface> 64 ; " +
                                     "                          <u:hasLongitude> 0.649256 ; " +
                                     "                          <u:hasLattitude> 35.702788 ; " +
                                     "                          <u:hasHumidity> '59%' ; " +
                                     "                          <u:hasTemperature> 20 ; " +
                                     "                          <u:hasClimate> 'mediterraneen' ; " +
                                     "                          <u:hasURL> 'localhost:7001' ; "  +
                                     "                          <u:hasDairaIndex> <u:daira_31100> . " +
                                     "       <u:commune_31210> <u:hasName> 'Es_Senia' ; " +
                                     "                         <u:hasPopulation> 97500 ; " +
                                     "                         <u:hasSurface> 48.51 ; " +
                                     "                         <u:hasLongitude> 0.616667 ; " +
                                     "                         <u:hasLattitude> 35.65 ; " +
                                     "                         <u:hasHumidity> '48%' ; " +
                                     "                         <u:hasTemperature> 21 ; " +
                                     "                         <u:hasClimate> 'sec_froid' ; " +
                                     "                         <u:hasURL> \"localhost:7001\"^^xsd:string ; "  +
                                     "                         <u:hasDairaIndex> <u:daira_31200> . " +
                                     "       <u:commune_31310> <u:hasName> 'Missreghin' ; " +
                                     "                         <u:hasPopulation> 26554 ; " +
                                     "                         <u:hasSurface> 428.28 ; " +
                                     "                         <u:hasLongitude> 0.729912 ; " +
                                     "                         <u:hasLattitude> 35.619939 ; " +
                                     "                         <u:hasHumidity> '60%' ; " +
                                     "                         <u:hasTemperature> 19 ; " +
                                     "                         <u:hasClimate> 'humide' ; " +
                                     "                         <u:hasURL> \"localhost:7001\"^^xsd:string ; "  +
                                     "                         <u:hasDairaIndex> <u:daira_31300> . " +
                                     "       <u:commune_27210> <u:hasName> 'Stidia' ; " +
                                     "                         <u:hasPopulation> 11965 ; " +
                                     "                         <u:hasSurface> 55 ; " +
                                     "                         <u:hasLongitude> 0 ; " +
                                     "                         <u:hasLattitude> 35.833333 ; " +
                                     "                         <u:hasHumidity> '65%' ; " +
                                     "                         <u:hasTemperature> 23 ; " +
                                     "                         <u:hasClimate> 'tempéré' ; " +
                                     "                         <u:hasURL> \"localhost:7001\"^^xsd:string ; "  +
                                     "                         <u:hasDairaIndex> <u:daira_27200> . " +
                                     "       <u:commune_27220> <u:hasName> 'Mazagran' ; " +
                                     "                         <u:hasPopulation> 22016 ; " +
                                     "                         <u:hasSurface> 20 ; " +
                                     "                         <u:hasLongitude> 0.071433 ; " +
                                     "                         <u:hasLattitude> 35.895621 ; " +
                                     "                         <u:hasHumidity> '59%' ; " +
                                     "                         <u:hasTemperature> 21 ; " +
                                     "                         <u:hasClimate> 'clair' ; " +
                                     "                         <u:hasURL> \"localhost:7001\"^^xsd:string ; "  +
                                     "                         <u:hasDairaIndex> <u:daira_27200> . " +
                                     "       <u:commune_27230> <u:hasName> 'Mameche' ; " +
                                     "                         <u:hasPopulation> 28790 ; " +
                                     "                         <u:hasSurface> 63 ; " +
                                     "                         <u:hasLongitude> 0.073171 ; " +
                                     "                         <u:hasLattitude> 35.860222 ; " +
                                     "                         <u:hasHumidity> '61%' ; " +
                                     "                         <u:hasTemperature> 19 ; " +
                                     "                         <u:hasClimate> 'humide' ; " +
                                     "                         <u:hasURL> \"localhost:7001\"^^xsd:string ; "  +
                                     "                         <u:hasDairaIndex> <u:daira_27200> . " +
                                    
                                     "       <u:residence> <u:hasResidenceIndex> <u:ResidenceIndex> . " +
                                     "       <u:ResidenceIndex> <u:hasValue> <u:res1> ; " +
                                     "                          <u:hasValue> <u:res2> ; " +
                                     "                          <u:hasValue> <u:res3> ; " +
                                     "                          <u:hasValue> <u:res4> ; " +
                                     "                          <u:hasValue> <u:res5> . " +
                                     "       <u:res1> <u:hasBirthIndex> <u:Birth_27000100> ; " +
                                     "                <u:hasAddressIndex> <u:adr02> ; " + 
                                     "                <u:hasFirstDate> \"02-11-1998\"^^xsd:date ; " +
                                     "                <u:hasLastDate> '\' . " +
                                     "       <u:res2> <u:hasBirthIndex> <u:Birth_27000500>; " +
                                     "                <u:hasAddressIndex> <u:adr03> ; " + 
                                     "                <u:hasFirstDate>  \"12-10-2005\"^^xsd:date; " +
                                     "                <u:hasLastDate> \"11-05-2014\"^^xsd:date . " +
                                     "       <u:res3> <u:hasBirthIndex> <u:Birth_31000100> ; " +
                                     "                <u:hasAddressIndex> <u:adr01> ; " + 
                                     "                <u:hasFirstDate>  \"11-07-2000\"^^xsd:date; " +
                                     "                <u:hasLastDate> '\' . " +
                                     "       <u:res4> <u:hasBirthIndex> <u:Birth_31000500> ; " +
                                     "                <u:hasAddressIndex> <u:adr05> ; " + 
                                     "                <u:hasFirstDate>  \"11-01-2011\"^^xsd:date; " +
                                     "                <u:hasLastDate> '\' . " +
                                     "       <u:Birth> <u:hasBirthIndex> <u:BirthIndex> . " +
                                     "        <u:BirthIndex> <u:hasValue> <u:Birth_27000100> ; " +
                                     "                           <u:hasValue> <u:Birth_27000200> ; " +
                                     "                           <u:hasValue> <u:Birth_27000300> ; " +
                                     "                           <u:hasValue> <u:Birth_27000400> ; " +
                                     "                           <u:hasValue> <u:Birth_27000500> ; " +
                                     "                           <u:hasValue> <u:Birth_27000600> ; " +
                                     "                           <u:hasValue> <u:Birth_27000700> ; " +
                                     "                           <u:hasValue> <u:Birth_27000800> ; " +
                                     "                           <u:hasValue> <u:Birth_27000900> ; " +
                                     "                           <u:hasValue> <u:Birth_31000100> ; " +
                                     "                           <u:hasValue> <u:Birth_31000200> ; " +
                                     "                           <u:hasValue> <u:Birth_31000300> ; " +
                                     "                           <u:hasValue> <u:Birth_31000400> ; " +
                                     "                           <u:hasValue> <u:Birth_31000500> ; " +
                                     "                           <u:hasValue> <u:Birth_31000600> ; " +
                                     "                           <u:hasValue> <u:Birth_31000700> ; " +
                                     "                           <u:hasValue> <u:Birth_31000800> ; " +
                                     "                           <u:hasValue> <u:Birth_31000900> . " +
                                    
                                     "         <u:Birth_27000100> <u:hasFirstName> 'Belarbi' ; " +
                                     "                            <u:hasLastName> 'Imene' ; " +
                                     "                            <u:hasBirthDate> \"2011-05-05\"^^xsd:date ; " +
                                     "                             <u:hasSex> 'F' ; " +
                                     "                             <u:hasImmunityDegree>  0.54 ; " +
                                     "                            <u:hasContaminationDegree> 0.46 ; " +
                                     "                             <u:hasState> 'S' ; " +
                                     "                             <u:hasFatherIndex> <u:Birth_271111>  ; " +
                                     "                             <u:hasMotherIndex> <u:Birth_271110> ; " +
                                     "                             <u:hasDeseaseChronique> 'GNA' ; " +
                                     "                             <u:hasMail> 'Imene@hotMail.fr'^^xsd:mail; " +
                                     "                             <u:hasAddressIndex> <u:adr04> ; " +
                                     "                             <u:hasCommuneIndex> <u:commune_27210> . "+ // la commune <u:hasCommuneIndex> <u:27000>
                                 
                                     "       <u:Birth_27000200> <u:hasFirstName> 'Mokhtari' ; " +
                                     "                          <u:hasLastName> 'Akram' ; " +
                                     "                          <u:hasBirthDate>  \"2014-04-04\"^^xsd:date; " +
                                     "                          <u:hasSex> 'M' ; " +
                                     "                          <u:hasImmunityDegree>  0.74 ; " +
                                     "                          <u:hasContaminationDegree> 0.36 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                          <u:hasFatherIndex> <u:Birth_271122> ; " +
                                     "                          <u:hasMotherIndex> <u:Birth_271119> ; " +
                                     "                          <u:hasDeseaseChronique> 'PITC' ; " +
                                     "                          <u:hasMail> 'Akram@gmail.fr' ; " +
                                     "                          <u:hasAddressIndex> <u:adr02> ; " +
                                     "                          <u:hasCommuneIndex> <u:commune_27100> . " +
                                     "       <u:Birth_27000300> <u:hasFirstName> 'Hamidi' ; " +
                                     "                          <u:hasLastName> 'Nabil' ; " +
                                     "                          <u:hasBirthDate>  \"24-02-2013\"^^xsd:date; " +
                                     "                          <u:hasSex> 'M' ; " +
                                     "                          <u:hasImmunityDegree>  0.24 ; " +
                                     "                          <u:hasContaminationDegree> 0.66 ; " +
                                     "                          <u:hasState> 'I' ; " +
                                     "                          <u:hasFatherIndex> <u:Birth_271112> ; " +
                                     "                          <u:hasMotherIndex> <u:Birth_271101> ; " +
                                     "                          <u:hasDesease> 'Encephalopathies' ; " +
                                     "                          <u:hasMail> 'Nabil@gmail.fr' ; " +
                                     "                          <u:hasAddressIndex> <u:adr02> ; " +
                                     "                          <u:CommuneIndex> <u:commune_27230> . " +
                                     "       <u:Birth_27000400> <u:hasFirstName> 'Amrani' ; " +
                                     "                          <u:hasLastName> 'Amel' ; " +
                                     "                          <u:hasBirthDate> \"11-09-2012\"^^xsd:date ; " +
                                     "                          <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.82 ; " +
                                     "                          <u:hasContaminationDegree> 0.16 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                           <u:hasFatherIndex> <u:Birth_271115> ; " +
                                     "                           <u:hasMotherIndex> <u:Birth_271116> ; " +
                                     "                           <u:hasMail> 'Amel@gmail.com' ; " +
                                     "                           <u:hasAddressIndex> <u:adr04> ; " +
                                     "                           <u:hasCommuneIndex> <u:commune_27210> . " +
                                     "       <u:Birth_27000500> <u:hasFirstName> 'Kihel' ; " +
                                     "                          <u:hasLastName> 'Farah' ; " +
                                     "                          <u:hasBirthDate> \"03-05-2014\"^^xsd:date ; " +
                                     "                          <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.74 ; " +
                                     "                          <u:hasContaminationDegree> 0.36 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                          <u:hasFatherIndex> <u:Birth_271109> ; " +
                                     "                          <u:hasMotherIndex> <u:Birth_271105> ; " +
                                     "                          <u:hasDesease> 'lucimie' ; " +
                                     "                          <u:hasMail> 'Farah@gmail.fr' ; " +
                                     "                          <u:hasAddressIndex> <u:adr02> ; " +
                                     "                          <u:hasCommuneIndex> <u:commune_27230> . " +             
                                     "       <u:Birth_27000600> <u:hasFirstName> 'gharbi' ; " +
                                     "                          <u:hasLastName> 'lamia' ; " +
                                     "                          <u:hasBirthDate> \"03-01-2015\"^^xsd:date ; " +
                                     "                           <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.62 ; " +
                                     "                          <u:hasContaminationDegree> 0.26 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                          <u:hasFatherIndex> <u:Birth_271114> ; " +
                                     "                           <u:hasMotherIndex> <u:Birth_271102> ; " +
                                     "                           <u:hasDesease> 'MALADIE CHRONIQUE EVOLUTIF PITC' ; " +
                                     "                           <u:hasMail> 'lamia@hotMail.com' ; " +
                                     "                           <u:hasAddressIndex> <u:adr02> ; " +
                                     "                           <u:hasCommuneIndex> <u:commune_27210> . " +
                                     "        <u:Birth_27000700> <u:hasFirstName> 'arbaoui' ; " +
                                     "                           <u:hasLastName> 'zaki' ; " +
                                     "                           <u:hasBirthDate> '03-01-2014' ; " +
                                     "                           <u:hasSex> 'M' ; " +
                                     "                          <u:hasImmunityDegree>  0.29 ; " +
                                     "                          <u:hasContaminationDegree> 0.64 ; " +
                                     "                          <u:hasState> 'I' ; " +
                                     "                           <u:hasFatherIndex> <u:Birth_271103> ; " +
                                     "                           <u:hasMotherIndex> <u:Birth_271104> ; " +
                                     "                           <u:hasMail> 'zaki@gmail.fr' ; " +
                                     "                           <u:hasAddressIndex> <u:adr04> ; " +
                                     "                           <u:hasCommuneIndex> <u:commune_27220> . " +
                                     "       <u:Birth_27000800> <u:hasFirstName> 'mokhtari' ; " +
                                     "                          <u:hasLastName> 'asma' ; " +
                                     "                          <u:hasBirthDate> '15-02-2015' ; " +
                                     "                           <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.68 ; " +
                                     "                          <u:hasContaminationDegree> 0.22 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                           <u:hasFatherIndex> <u:Birth_271106> ; " +
                                     "                           <u:hasMotherIndex> <u:Birth_271107> ; " +
                                     "                           <u:hasMail> 'asma@gmail.com' ; " +
                                     "                           <u:hasAddressIndex> <u:adr02> ; " +
                                     "                           <u:hasCommuneIndex> <u:commune_27230> . " +
                                     "       <u:Birth_27000900> <u:hasFirstName> 'baroudi' ; " +
                                     "                          <u:hasLastName> 'amine' ; " +
                                     "                           <u:hasBirthDate> '23-11-2014' ; " +
                                     "                           <u:hasSex> 'M' ; " +
                                     "                          <u:hasImmunityDegree>  0.74 ; " +
                                     "                          <u:hasContaminationDegree> 0.36 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                           <u:hasFatherIndex> <u:Birth_271108> ; " +
                                     "                           <u:hasMotherIndex> <u:Birth_271113> ; " +
                                     "                           <u:hasMail> 'amine@gmail.fr' ; " +
                                     "                           <u:hasAddressIndex> <u:adr04> ; " +
                                     "                           <u:hasCommuneIndex> <u:commune_27210> . " +
                                     
                                    
                                     "         <u:Birth_31000100> <u:hasFirstName> 'Hamed'; " +
                                     "                            <u:hasLastName> 'Imene' ; " +
                                     "                            <u:hasBirthDate> '06-07-2013' ; " +
                                     "                            <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.32 ; " +
                                     "                          <u:hasContaminationDegree> 0.72 ; " +
                                     "                          <u:hasState> 'I' ; " +
                                     "                            <u:hasFatherIndex> <u:Birth_311101> ; " +
                                     "                            <u:hasMotherIndex> <u:Birth_311103> ; " +
                                     "                            <u:hasDesease> 'injection recente de gammaglobulines ' ; " +
                                     "                            <u:hasMail> 'Imeno@hotMail.fr' ; " +
                                     "                            <u:hasAddressIndex> <u:adr05> ; " +
                                     "                            <u:hasCommuneIndex> <u:commune_31310> . " +
                                     "       <u:Birth_31000200> <u:hasFirstName> 'Boukhari' ; " +
                                     "                          <u:hasLastName> 'Amine'; " +
                                     "                          <u:hasBirthDate> '02-12-2012' ; " +
                                     "                          <u:hasSex> 'M' ; " +
                                     "                          <u:hasImmunityDegree>  0.58 ; " +
                                     "                          <u:hasContaminationDegree> 0.46 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                          <u:hasFatherIndex> <u:Birth_311119> ; " +
                                     "                          <u:hasMotherIndex> <u:Birth_31110> ; " +
                                     "                          <u:hasMalade> 'deficits immunitaire importante ' ; " +
                                     "                          <u:hasMail> 'Imeno@live.fr' ; " +
                                     "                          <u:hasAddressIndex> <u:adr01> ; " +
                                     "                          <u:hasCommuneIndex> <u:commune_31110> . " +
                                     "       <u:Birth_31000300> <u:hasFirstName> 'Maida' ; " +
                                     "                          <u:hasLastName> 'Hanene' ; " +
                                     "                          <u:hasBirthDate> '20-04-2014' ; " +
                                     "                          <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.91 ; " +
                                     "                          <u:hasContaminationDegree> 0.16 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                          <u:hasFatherIndex> <u:Birth_311111> ; " +
                                     "                          <u:hasMotherIndex> <u:Birth_311110> ; " +
                                     "                          <u:hasMail> 'Hanene@gmail.fr' ; " +
                                     "                          <u:hasAddressIndex> <u:adr05> ; " +
                                     "                          <u:hasCommuneIndex> <u:commune_31310> . " +
                                     "       <u:Birth_31000400> <u:hasFirstName> 'Larbi' ; " +
                                     "                           <u:hasLastName> 'Karim' ; " +
                                     "                           <u:hasBirthDate> '02-06-2012' ; " +
                                     "                           <u:hasSex> 'M' ; " +
                                     "                          <u:hasImmunityDegree>  0.35 ; " +
                                     "                          <u:hasContaminationDegree> 0.65 ; " +
                                     "                          <u:hasState> 'I' ; " +
                                     "                           <u:hasFatherIndex> <u:Birth_311116> ; " +
                                     "                           <u:hasMotherIndex> <u:Birth_311107> ; " +
                                     "                           <u:hasMail> 'Karim@gmail.com' ; " +
                                     "                           <u:hasAddressIndex> <u:adr03> ; " +
                                     "                           <u:hasCommuneIndex> <u:commune_31210> . " +
                                     "       <u:Birth_31000500> <u:hasFirstName> 'Dadi' ; " +
                                     "                          <u:hasLastName> 'Asmaa' ; " +
                                     "                          <u:hasBirthDate> '02-04-2013' ; " +
                                     "                          <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.74 ; " +
                                     "                          <u:hasContaminationDegree> 0.36 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                          <u:hasFatherIndex> <u:Birth_311111> ; " +
                                     "                          <u:hasMotherIndex> <u:Birth_311110> ; " +
                                     "                          <u:hasMail> 'Asmaa@gmail.fr' ; " +
                                     "                          <u:hasAddressIndex> <u:adr01> ; " +
                                     "                          <u:hasCommuneIndex> <u:commune_31110> . "  +
                                     "    <u:Birth_31000600> <u:hasFirstName> 'ait'; " +
                                     "                       <u:hasLastName> 'amina' ; " +
                                     "                       <u:hasBirthDate> '06-11-2014' ; " +
                                     "                       <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.70 ; " +
                                     "                          <u:hasContaminationDegree> 0.30 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                       <u:hasFatherIndex> <u:Birth_311102> ; " +
                                     "                       <u:hasMotherIndex> <u:Birth_311104> ; " +
                                     "                       <u:hasDesease> 'maladie chronique evolutif GNA ' ; " +
                                     "                       <u:hasMail> 'amina@live.fr' ; " +
                                     "                       <u:hasAddressIndex> <u:adr05> ; " +
                                     "                       <u:hasCommuneIndex> <u:commune_31310> . " +
                                     "     <u:Birth_31000700> <u:hasFirstName> 'zitouni'; " +
                                     "                       <u:hasLastName> 'yamina' ; " +
                                     "                       <u:hasBirthDate> '06-11-2014' ; " +
                                     "                       <u:hasSex> 'F' ; " +
                                     "                          <u:hasImmunityDegree>  0.74 ; " +
                                     "                          <u:hasContaminationDegree> 0.36 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                       <u:hasFatherIndex> <u:Birth_311105> ; " +
                                     "                       <u:hasMotherIndex> <u:Birth_311106> ; " +
                                     "                       <u:hasDesease> 'Encéphalophatie ' ; " +
                                     "                       <u:hasMail> 'yamina@hotMail.fr' ; " +
                                     "                       <u:hasAddressIndex> <u:adr01>; " +
                                     "                       <u:hasCommuneIndex> <u:commune_31110> . " +
                                     "    <u:Birth_31000800> <u:hasFirstName> 'moufek'; " +
                                     "                       <u:hasLastName> 'mohamed' ; " +
                                     "                       <u:hasBirthDate> '26-01-2012' ; " +
                                     "                       <u:hasSex> 'M' ; " +
                                     "                          <u:hasImmunityDegree>  0.58 ; " +
                                     "                          <u:hasContaminationDegree> 0.42 ; " +
                                     "                          <u:hasState> 'S' ; " +
                                     "                       <u:hasFatherIndex> <u:Birth_311113> ; " +
                                     "                       <u:hasMotherIndex> <u:Birth_311108> ; " +
                                     "                       <u:hasDesease> 'anaphylaxie ' ; " +
                                     "                       <u:hasMail> 'mohamed@gmail.fr' ; " +
                                     "                       <u:hasAddressIndex> <u:adr03> ; " +
                                     "                       <u:hasCommuneIndex> <u:commune_31210> . " +
                                     "    <u:Birth_31000900> <u:hasFirstName> 'boukhari'; " +
                                     "                       <u:hasLastName> 'riad' ; " +
                                     "                       <u:hasBirthDate> '16-02-2014' ; " +
                                     "                       <u:hasSex> 'M' ; " +
                                     "                          <u:hasImmunityDegree>  0.44 ; " +
                                     "                          <u:hasContaminationDegree> 0.66 ; " +
                                     "                          <u:hasState> 'I' ; " +
                                     "                       <u:hasFatherIndex> <u:Birth_311109> ; " +
                                     "                       <u:hasMotherIndex> <u:Birth_311112> ; " +
                                     "                       <u:hasDesease> 'VIH' ; " +
                                     "                       <u:hasMail> 'riad@gmail.com' ; " +
                                     "                       <u:hasAddressIndex> <u:adr05> ; " +
                                     "                       <u:hasCommuneIndex> <u:commune_31310> . " +
                                     "     <u:Mariage> <u:hasMariageIndex> <u:MariageIndex> . " +
                                     "     <u:MariageIndex> <u:hasValue> <u:Mariage_001> ; " +
                                     "                      <u:hasValue> <u:Mariage_002> ; " +
                                     "                      <u:hasValue> <u:Mariage_003> ; " +
                                     "                      <u:hasValue> <u:Mariage_004> ; " +
                                     "                      <u:hasValue> <u:Mariage_005> ; " +
                                     "                      <u:hasValue> <u:Mariage_006> ; " +
                                     "                      <u:hasValue> <u:Mariage_007> ; " +
                                     "                      <u:hasValue> <u:Mariage_008> ; " +
                                     "                      <u:hasValue> <u:Mariage_009> ; " + 
                                     "                      <u:hasValue> <u:Mariage_010> ; " + 
                                     "                      <u:hasValue> <u:Mariage_011> ; " +
                                     "                      <u:hasValue> <u:Mariage_012> ; " +
                                     "                      <u:hasValue> <u:Mariage_013> ; " +
                                     "                      <u:hasValue> <u:Mariage_014> ; " +
                                     "                      <u:hasValue> <u:Mariage_015> ; " +
                                     "                      <u:hasValue> <u:Mariage_016> ; " +
                                     "                      <u:hasValue> <u:Mariage_017> ; " +
                                     "                      <u:hasValue> <u:Mariage_018> . " +
                                     "       <u:Mariage_001> <u:hasManBirth> <u:Birth_271111> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271110> ; " +
                                     "                       <u:hasMariageDate> '02-07-2011' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 1 . " +
                                     "       <u:Mariage_002> <u:hasManBirth> <u:Birth_271110> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271110> ; " +
                                     "                       <u:hasMariageDate> '02-07-2006' ; " +
                                     "                       <u:hasSeparationDate> '12-02-2012' ; " +
                                     "                       <u:hasChildrenNumber> 3 . " +
                                     "       <u:Mariage_003> <u:hasManBirth> <u:Birth_271109> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271105> ; " +
                                     "                       <u:hasMariageDate> '30-06-2008' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 3 . " +
                                     "       <u:Mariage_004> <u:hasManBirth> <u:Birth_271114> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271102> ; " +
                                     "                       <u:hasMariageDate> '02-07-2010' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 1 . " +
                                     "       <u:Mariage_005> <u:hasManBirth> <u:Birth_271103> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271104> ; " +
                                     "                       <u:hasMariageDate> '15-12-2011' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 2 . " +
                                     "       <u:Mariage_006> <u:hasManBirth> <u:Birth_271106> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271107> ; " +
                                     "                       <u:hasMariageDate> '12-07-2012' ; " +
                                     "                       <u:hasSeparationDate> '21-02-2015' ; " +
                                     "                       <u:hasChildrenNumber> 3 . " +
                                     "       <u:Mariage_007> <u:hasManBirth> <u:Birth_271108> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271113> ; " +
                                     "                       <u:hasMariageDate> '03-08-2010' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 2 . " +
                                     "       <u:Mariage_008> <u:hasManBirth> <u:Birth_311102> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_311104> ; " +
                                     "                       <u:hasMariageDate> '13-09-2011' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 2 . " +
                                     "       <u:Mariage_009> <u:hasManBirth> <u:Birth_311105> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_311106> ; " +
                                     "                       <u:hasMariageDate> '01-11-2007' ; " +
                                     "                       <u:hasSeparationDate> '14-01-2014' ; " +
                                     "                       <u:hasChildrenNumber> 2 . " +
                                     "       <u:Mariage_010> <u:hasManBirth> <u:Birth_311113> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_311108> ; " +
                                     "                       <u:hasMariageDate> '03-07-2012' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 2 . " +
                                     "       <u:Mariage_011> <u:hasManBirth> <u:Birth_311109> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_311112> ; " +
                                     "                       <u:hasMariageDate> '13-05-2010' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 2 . " +
                                     "       <u:Mariage_012> <u:hasManBirth> <u:Birth_271112> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271101> ; " +
                                     "                       <u:hasMariageDate> '11-06-2011' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 2 . " +
                                     "       <u:Mariage_013> <u:hasManBirth> <u:Birth_271122> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271119> ; " +
                                     "                       <u:hasMariageDate> '01-04-2010' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 1 . " +
                                     "       <u:Mariage_014> <u:hasManBirth> <u:Birth_271115> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_271116> ; " +
                                     "                       <u:hasMariageDate> '12-12-2010' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 2 . " +
                                     "       <u:Mariage_015> <u:hasManBirth> <u:Birth_311101> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_311103> ; " +
                                     "                       <u:hasMariageDate> '15-02-2009' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 1 . " +
                                     "       <u:Mariage_016> <u:hasManBirth> <u:Birth_311119> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_311120> ; " +
                                     "                       <u:hasMariageDate> '19-05-2010' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 1 . " +
                                     "       <u:Mariage_017> <u:hasManBirth> <u:Birth_311116> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_311107> ; " +
                                     "                       <u:hasMariageDate> '17-08-2011' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 1 . " +
                                     "       <u:Mariage_018> <u:hasManBirth> <u:Birth_311101> ; " +
                                     "                       <u:hasWomanBirth> <u:Birth_311103> ; " +
                                     "                       <u:hasMariageDate> '12-02-2010' ; " +
                                     "                       <u:hasSeparationDate> '\' ; " +
                                     "                       <u:hasChildrenNumber> 1 . " +
                                    
                                     "   <u:BirthIndex>  <u:hasValue> <u:Birth_271111> ; " + 
                                     "                   <u:hasValue> <u:Birth_271110> ; " + 
                                     "                   <u:hasValue> <u:Birth_271114> ; "+
                                     "                   <u:hasValue> <u:Birth_271101> ; " +
                                     "                   <u:hasValue> <u:Birth_271102> ; " +
                                     "                   <u:hasValue> <u:Birth_271103> ; " + 
                                     "                   <u:hasValue> <u:Birth_271104> ; " + 
                                     "                   <u:hasValue> <u:Birth_271106> ; " +
                                     "                   <u:hasValue> <u:Birth_271107> ; " +
                                     "                   <u:hasValue> <u:Birth_271108> ; " +
                                     "                   <u:hasValue> <u:Birth_271113> ; " + 
                                     "                   <u:hasValue> <u:Birth_271109> ; " +
                                     "                   <u:hasValue> <u:Birth_271122> ; " + 
                                     "                   <u:hasValue> <u:Birth_271119> ; " + 
                                     "                   <u:hasValue> <u:Birth_271112> ; " + 
                                     "                   <u:hasValue> <u:Birth_271115> ; " + 
                                     "                   <u:hasValue> <u:Birth_271116> ; " +  
                                     "                   <u:hasValue> <u:Birth_271105> ; " +   
                                     "                   <u:hasValue> <u:Birth_311101> ; " + 
                                     "                   <u:hasValue> <u:Birth_311102> ; " + 
                                     "                   <u:hasValue> <u:Birth_311104> ; " + 
                                     "                   <u:hasValue> <u:Birth_311105> ; " + 
                                     "                   <u:hasValue> <u:Birth_311106> ; " +
                                     "                   <u:hasValue> <u:Birth_311107> ; " +
                                     "                   <u:hasValue> <u:Birth_311108> ; " + 
                                     "                   <u:hasValue> <u:Birth_311109> ; " +
                                     "                   <u:hasValue> <u:Birth_311112> ; " +
                                     "                   <u:hasValue> <u:Birth_311103> ; " +
                                     "                   <u:hasValue> <u:Birth_311119> ; " +
                                     "                   <u:hasValue> <u:Birth_311120> ; " +
                                     "                   <u:hasValue> <u:Birth_271110> ; " +
                                     "                   <u:hasValue> <u:Birth_271110> ; " +
                                     "                   <u:hasValue> <u:Birth_311116> ; " +
                                     "                   <u:hasValue> <u:Birth_311113> . " +
                                     "      <u:Birth_271111> <u:hasFirstName> 'Belarbi' ; " +
                                     "                <u:hasLastName> 'Mohamed' ; " +
                                     "                <u:hasSex> 'M' ; " +
                                     "                <u:hasAddressIndex> <u:adr04> ; " +
                                     "                <u:hasNumber>   '0551 01 45 07' ; " +   
                                     "                <u:hasState>  'R'  ; " +
                                     "                <u:hasLongitude> '0.082222' ; " +
                                     "                <u:hasLattitude> '35.922222' ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27310> ; " +
                                     "                <u:hasWork> <u:job1> ; " +
                                     "                <u:hasMail> 'mohamed@gmail.com' . " +
                                     "      <u:Birth_271110> <u:hasFirstName> 'Mahdi' ; " +
                                     "                <u:hasLastName> 'meriam' ; " +
                                     "                <u:hasWork> <u:job2> ; " +
                                     "                <u:hasSex> 'F' ; " +
                                     "                <u:hasNumber>   '0771 52 51 50' ; " +   
                                     "                <u:hasState>  'I'  ; " +
                                     "                <u:hasLongitude> '0.081111' ; " +
                                     "                <u:hasLattitude> '35.81111' ; " +
                                     "                <u:hasAddressIndex> <u:adr04> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'mimi@live.fr' . " +
                                     "     <u:Birth_271112>  <u:hasFirstName> 'Hamidi' ; " +
                                     "                <u:hasLastName> 'Othmane' ; " +
                                     "                <u:hasSex> 'M' ; " +
                                     "                <u:hasMail> 'othmane@gmail.com' ; "+
                                     "                <u:hasNumber>   '0555 56 58 57' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasAddressIndex> <u:adr04> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27210> ; " +
                                     "                <u:hasWork> <u:job5> . " +
                                     "     <u:Birth_271101> <u:hasFirstName> 'Arbi' ; " +
                                     "                <u:hasLastName> 'Hanane' ; " +
                                     "                <u:hasNumber>   '0541 56 58 57' ; " +   
                                     "                <u:hasState>   'I'  ; " +
                                     "                <u:hasLongitude> '0.080000' ; " +
                                     "                <u:hasLattitude> '35.733333' ; " +
                                     "                <u:hasWork> <u:job6> ; " +
                                     "                <u:hasSex> 'F' ; " +
                                     "                <u:hasAddressIndex><u:adr02> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'hn@live.fr' . " +
                                     "    <u:Birth_271115> <u:hasFirstName> 'amrani' ; " +
                                     "                <u:hasLastName> 'moh' ; " +
                                     "                <u:hasSex> 'M' ; " +
                                     "                <u:hasNumber>   '0775 12 18 17' ; " +   
                                     "                <u:hasState>   'S'  ; " +
                                     "                <u:hasLongitude> '0.0811111' ; " +
                                     "                <u:hasLattitude> '35.633333' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr04> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27100> ; " +
                                     "                <u:hasMail> 'moh@live.fr' . " +
                                     "      <u:Birth_271122> <u:hasFirstName> 'Mokhtari' ; " +
                                     "                <u:hasLastName> 'Amine' ; " +
                                     "                <u:hasSex> 'M' ; " +
                                     "                <u:hasNumber>   '0555 56 58 57' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.081111' ; " +
                                     "                <u:hasLattitude> '35.711111' ; " +
                                     "                <u:hasAddressIndex><u:adr02> ; "+
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasWork> <u:job3> ; " +
                                     "                <u:hasMail> 'Amine@gmail.com' . " +
                                     "     <u:Birth_271119>  <u:hasFirstName> 'slimani' ; " +
                                     "                <u:hasLastName> 'Aicha' ; " +
                                     "                <u:hasNumber>   '0661 61 62 62' ; " +   
                                     "                <u:hasState>  'I'  ; " +
                                     "                <u:hasLongitude> '0.082222' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job4> ; " +
                                     "                <u:hasSex> 'F' ; " +
                                     "                <u:hasAddressIndex><u:adr02> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'Aicha@live.fr' . " +
                                     "    <u:Birth_271116> <u:hasFirstName> 'amrani' ; " +
                                     "                <u:hasLastName> 'Hanane' ; " +
                                     "                <u:hasSex> 'F' ; " +
                                     "                <u:hasNumber>   '0662 56 58 57' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.0833333' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job8> ; " +
                                     "                <u:hasAddressIndex> <u:adr04> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'hn31@live.fr' . " +
                                     "    <u:Birth_271109> <u:hasFirstName> 'kaheli' ; " +
                                     "                <u:hasLastName> 'boumediane' ; " +
                                     "                <u:hasSex> 'M' ; " +
                                     "                <u:hasNumber>   '0555 56 58 57' ; " +   
                                     "                <u:hasState>  'R'  ; " +
                                     "                <u:hasLongitude> '0.080000' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr02> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110>; " +
                                     "                <u:hasMail> 'bo@live.fr' . " +
                                     "    <u:Birth_271105> <u:hasFirstName> 'kehli' ; " +
                                     "                <u:hasLastName> 'leila' ; " +
                                     "                <u:hasSex> 'F' ; " +
                                     "                <u:hasNumber>   '0555 56 58 57' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.0811111' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job8> ; " +
                                     "                <u:hasAddressIndex> <u:adr02> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'leila@live.fr' . " +
                                     "    <u:Birth_271114> <u:hasFirstName> 'gharbi' ; " +
                                     "                <u:hasLastName> 'tofik' ; " +
                                     "                <u:hasSex> 'M' ; " +
                                     "                <u:hasNumber>   '0775 06 08 07' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.083333' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr02> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'tfk@live.fr' . " +
                                     "    <u:Birth_271104> <u:hasFirstName> 'arbi' ; " +
                                     "                <u:hasLastName> 'fati' ; " +
                                     "                <u:hasWork> <u:job8> ; " +
                                     "                <u:hasNumber>   '0770 50 52 53' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.0833333' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasAddressIndex> <u:adr02> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'fati@live.fr' . " +
                                     "    <u:Birth_271103> <u:hasFirstName> 'arbaoui' ; " +
                                     "                <u:hasLastName> 'sofian' ; " +
                                     "                <u:hasNumber>   '0551 56 58 57' ; " +   
                                     "                <u:hasState>  'I'  ; " +
                                     "                <u:hasLongitude> '0.083333' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr01> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'tfk@live.fr' . " +
                                     "    <u:Birth_271120> <u:hasFirstName> 'mokhtari' ; " +
                                     "                <u:hasLastName> 'fatiha' ; " +
                                     "                <u:hasNumber>   '0775 01 08 06' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.083333' ; " +
                                     "                <u:hasLattitude> '35,833333' ; " +
                                     "                <u:hasWork> <u:job8> ; " +
                                     "                <u:hasAddressIndex> <u:adr01> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'fati@live.fr' . " +
                                     "    <u:Birth_271106> <u:hasFirstName> 'mokhtari' ; " +
                                     "                <u:hasLastName> 'sofian' ; " +
                                     "                <u:hasNumber>   '0551 51 53 58' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.0822222' ; " +
                                     "                <u:hasLattitude> '35.822222' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr01> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'tfk@live.fr' . " +
                                     "    <u:Birth_271107> <u:hasFirstName> 'youcefi' ; " +
                                     "                <u:hasLastName> 'nadia' ; " +
                                     "                <u:hasNumber>   '0770 16 38 27' ; " +   
                                     "                <u:hasState>  'I'  ; " +
                                     "                <u:hasLongitude> '0.083333' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr01> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'tfk@live.fr' . " +
                                     "    <u:Birth_271113> <u:hasFirstName> 'ben yasine' ; " +
                                     "                <u:hasLastName> 'fadwa' ; " +
                                     "                <u:hasNumber>   '0778 76 78 87' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.083333' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr01> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'tfk@live.fr' . " +
                                     "    <u:Birth_271108> <u:hasFirstName> 'touhami' ; " +
                                     "                <u:hasLastName> 'salah' ; " +
                                     "                <u:hasNumber>   '0550 76 68 17' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.0833333' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr01> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'tfk@live.fr' . " +
                                     "    <u:Birth_271102> <u:hasFirstName> 'mokhtari' ; " +
                                     "                <u:hasLastName> 'fatiha' ; " +
                                     "                <u:hasNumber>   '0661 00 18 52' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0' ; " +
                                     "                <u:hasLattitude> '35.833333' ; " +
                                     "                <u:hasWork> <u:job8> ; " +
                                     "                <u:hasAddressIndex> <u:adr01> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'fati@live.fr' . " +
                                     "    <u:Birth_271110> <u:hasFirstName> 'maida' ; " +
                                     "                <u:hasLastName> 'oussama' ; " +
                                     "                <u:hasNumber>   '0550 50 51 51' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.633333' ; " +
                                     "                <u:hasLattitude> '35.7' ; " +
                                     "                <u:hasWork> <u:job7> ; " +
                                     "                <u:hasAddressIndex> <u:adr05> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'ouss@live.fr' . " +
                                     "    <u:Birth_271110> <u:hasFirstName> 'maida' ; " +
                                     "                <u:hasLastName> 'majda' ; " +
                                     "                <u:hasNumber>   '0541 41 00 00' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.633333' ; " +
                                     "                <u:hasLattitude> '35.7' ; " +
                                     "                <u:hasWork> <u:job8> ; " +
                                     "                <u:hasAddressIndex> <u:adr05> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_27110> ; " +
                                     "                <u:hasMail> 'maj@live.fr' . " +
                                     "    <u:Birth_311116> <u:hasFirstName> 'larbi' ; " +
                                     "               <u:hasLastName> 'youcef' ; " +
                                     "               <u:hasNumber>   '0555 56 58 57' ; " +   
                                     "               <u:hasState>  'S'  ; " +
                                     "               <u:hasLongitude> '0.633333' ; " +
                                     "               <u:hasLattitude> '35.7' ; " +
                                     "               <u:hasWork> <u:job7> ; " +
                                     "               <u:hasAddressIndex> <u:adr05> ; " +
                                     "               <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "               <u:hasMail> 'yus@live.fr' . " +
                                     "    <u:Birth_311107> <u:hasFirstName> 'larbi' ; " +
                                     "                <u:hasLastName> 'marwa' ; " +
                                     "                <u:hasNumber>   '0553 53 50 56' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.633333' ; " +
                                     "                <u:hasLattitude> '35.7' ; " +
                                     "                <u:hasWork> <u:job8> ; " +
                                     "                <u:hasAddressIndex> <u:adr05> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "                <u:hasMail> 'mrw@live.fr' . " +            
                                     "    <u:Birth_311113> <u:hasFirstName> 'moufek' ; " +
                                     "                <u:hasLastName> 'yassin' ; " +
                                     "                <u:hasNumber>   '0775 01 58 00' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.633333' ; " +
                                     "                <u:hasLattitude> '35,7' ; " +
                                     "                <u:hasWork> <u:job1> ; " +
                                     "                <u:hasAddressIndex> <u:adr05> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "                <u:hasMail> 'mfys@live.fr' . " +
                                     "    <u:Birth_311108> <u:hasFirstName> 'moufek' ; " +
                                     "                <u:hasLastName> 'abir' ; " +
                                     "                <u:hasNumber>   '0555 56 58 57' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.633333' ; " +
                                     "                <u:hasLattitude> '35.7' ; " +
                                     "                <u:hasWork> <u:job2> ; " +
                                     "                <u:hasAddressIndex> <u:adr05> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "                <u:hasMail> 'mfab@live.fr' . " +
                                     "    <u:Birth_311102> <u:hasFirstName> 'ait' ; " +
                                     "               <u:hasLastName> 'omar' ; " +
                                     "               <u:hasNumber>   '0558 86 58 87' ; " +   
                                     "               <u:hasState>  'S'  ; " +
                                     "               <u:hasLongitude> '0.36' ; " +
                                     "               <u:hasLattitude> '35.7' ; " +
                                     "               <u:hasWork> <u:job3> ; " +
                                     "               <u:hasAddressIndex> <u:adr05> ; " +
                                     "               <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "               <u:hasMail> 'ait@live.fr' . " +
                                     "    <u:Birth_311104> <u:hasFirstName> 'khatir' ; " +
                                     "               <u:hasLastName> 'roumaissa' ; " +
                                     "               <u:hasNumber>   '0550 46 70 08 ' ; " +   
                                     "               <u:hasState>  'I'  ; " +
                                     "               <u:hasLongitude> '0.63' ; " +
                                     "               <u:hasLattitude> '35.7' ; " +
                                     "               <u:hasWork> <u:job4> ; " +
                                     "               <u:hasAddressIndex> <u:adr05> ; " +
                                     "               <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "               <u:hasMail> 'khr@live.fr' . " +
                                     "    <u:Birth_311101> <u:hasFirstName> 'mahdi' ; " +
                                     "               <u:hasLastName> 'moussa' ; " +
                                     "               <u:hasNumber>   '0771 71 75 78' ; " +   
                                     "               <u:hasState>  'R'  ; " +
                                     "               <u:hasLongitude> '0.63' ; " +
                                     "               <u:hasLattitude> '35.7' ; " +
                                     "               <u:hasWork> <u:job5> ; " +
                                     "               <u:hasAddressIndex> <u:adr05> ; " +
                                     "               <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "               <u:hasMail> 'moussa@live.fr' . " +
                                     "    <u:Birth_311103> <u:hasFirstName> 'khalidi' ; " +
                                     "               <u:hasLastName> 'salima' ; " +
                                     "               <u:hasNumber>   '0555 00 58 57' ; " +   
                                     "               <u:hasState>  'S'  ; " +
                                     "               <u:hasLongitude> '0.63' ; " +
                                     "               <u:hasLattitude> '35.7' ; " +
                                     "               <u:hasWork> <u:job6> ; " +
                                     "               <u:hasAddressIndex> <u:adr05> ; " +
                                     "               <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "               <u:hasMail> 'khld@live.fr' . " +
                                     "    <u:Birth_311109> <u:hasFirstName> 'boukhari' ; " +
                                     "               <u:hasLastName> 'houssin' ; " +
                                     "               <u:hasNumber>   '0781 56 58 57' ; " +   
                                     "               <u:hasState>  'S'  ; " +
                                     "               <u:hasLongitude> '0.63' ; " +
                                     "               <u:hasLattitude> '35.7' ; " +
                                     "               <u:hasWork> <u:job1> ; " +
                                     "               <u:hasAddressIndex> <u:adr05> ; " +
                                     "               <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "               <u:hasMail> 'boukhari@gmail.com' . " +
                                     "    <u:Birth_311112> <u:hasFirstName> 'ben ali' ; " +
                                     "              <u:hasLastName> 'badra' ; " +
                                     "              <u:hasNumber>   '0661 56 58 57' ; " +   
                                     "              <u:hasState>  'S'  ; " +
                                     "              <u:hasLongitude> '0.63' ; " +
                                     "              <u:hasLattitude> '35.7' ; " +
                                     "              <u:hasWork> <u:job8> ; " +
                                     "              <u:hasAddressIndex> <u:adr05> ; " +
                                     "              <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "              <u:hasMail> 'ben@live.fr' . " +
                                     "   <u:Birth_311105> <u:hasFirstName> 'zitouni' ; " +
                                     "              <u:hasLastName> 'said' ; " +
                                     "              <u:hasNumber>   '0550 54 57 57' ; " +   
                                     "              <u:hasState>  'S'  ; " +
                                     "              <u:hasLongitude> '0.63' ; " +
                                     "              <u:hasLattitude> '35.7' ; " +
                                     "              <u:hasWork> <u:job7> ; " +
                                     "              <u:hasAddressIndex> <u:adr05> ; " +
                                     "              <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "              <u:hasMail> 'zitoun@gmail.fr' . " +
                                     "   <u:Birth_311106> <u:hasFirstName> 'lakhder' ; " +
                                     "              <u:hasLastName> 'khaira' ; " +
                                     "              <u:hasNumber>   '0777 84 56 54' ; " +   
                                     "              <u:hasState>  'S'  ; " +
                                     "              <u:hasLongitude> '0' ; " +
                                     "              <u:hasLattitude> '35.833333' ; " +
                                     "              <u:hasWork> <u:job8> ; " +
                                     "              <u:hasAddressIndex> <u:adr05> ; " +
                                     "              <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "              <u:hasMail> 'lakh@hotMail.fr' . " +
                                     "   <u:Birth_311119> <u:hasFirstName> 'fouatih' ; " +
                                     "               <u:hasLastName> 'djamel' ; " +
                                     "               <u:hasNumber>   '0791 56 10 57' ; " +   
                                     "               <u:hasState>  'I'  ; " +
                                     "               <u:hasLongitude> '0.63' ; " +
                                     "               <u:hasLattitude> '35.7' ; " +
                                     "               <u:hasWork> <u:job1> ; " +
                                     "               <u:hasAddressIndex> <u:adr05> ; " +
                                     "               <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "               <u:hasMail> 'foud@gmail.fr' . " +
                                     "    <u:Birth_311120> <u:hasFirstName> 'yamani' ; " +
                                     "                <u:hasLastName> 'rekia' ; " +
                                     "                <u:hasNumber>   '0798 56 58 57' ; " +   
                                     "                <u:hasState>  'S'  ; " +
                                     "                <u:hasLongitude> '0.63' ; " +
                                     "                <u:hasLattitude> '35.7' ; " +
                                     "                <u:hasWork> <u:job8> ; " +
                                     "                <u:hasAddressIndex> <u:adr05> ; " +
                                     "                <u:hasCommuneIndex> <u:commune_31310> ; " +
                                     "                <u:hasMail> 'yamani@live.fr' . " +
                                     "     <u:Job> <u:hasJobIndex> <u:JobIndex> . " +
                                     "      <u:JobIndex> <u:hasValue> <u:job1> ; " +
                                     "                   <u:hasValue> <u:job2> ; " +
                                     "                   <u:hasValue> <u:job3> ; " +
                                     "                   <u:hasValue> <u:job4> ; " +
                                     "                   <u:hasValue> <u:job5> ; " +
                                     "                   <u:hasValue> <u:job6> ; " +
                                     "                   <u:hasValue> <u:job7> ; " +
                                     "                   <u:hasValue> <u:job8> . " +
                                     "    <u:job1> <u:hasJob> 'dentist' ; " +
                                     "           <u:hasSalary> '70.000da' ; " +
                                     "           <u:hasExperience> '10ans' . " +
                                     "    <u:job2> <u:hasJob> 'enseignant' ; " +
                                     "           <u:hasSalary> '70.000da' ; " +
                                     "           <u:hasExperience> '10ans' . " +
                                     "    <u:job3> <u:hasJob> 'pilote' ; " +
                                     "           <u:hasSalary> '100.000da' ; " +
                                     "           <u:hasExperience> '10ans' . " +
                                     "   <u:job4> <u:hasJob> 'architecte' ; " +
                                     "           <u:hasSalary> '40.000da' ; " +
                                     "           <u:hasExperience> '3ans' . " +
                                     "   <u:job5> <u:hasJob> 'ingenieur' ; " +
                                     "           <u:hasSalary> '70.000da' ; " +
                                     "           <u:hasExperience> '10ans' . " +
                                     "   <u:job6> <u:hasJob> 'medcin' ; " +
                                     "           <u:hasSalary> '50.000da' ; " +
                                     "           <u:hasExperience> '10ans' . " +
                                     "   <u:job7> <u:hasJob> 'secritaire' ; " +
                                     "           <u:hasSalary> '30.000da' ; " +
                                     "           <u:hasExperience> '10ans' . " +
                                     "   <u:job8> <u:hasJob> 'directreur' ; " +
                                     "           <u:hasSalary> '70.000da' ; " +
                                     "           <u:hasExperience> '10ans' . " ;

        
        
 String szQuery1 = "select * where { { ?s ?p ?o }  }"; 
 System.out.println("Execute query " + szQuery);
     
 Query query1 = QueryFactory.create(szQuery1);
 QueryExecution qexec1 = QueryExecutionFactory.create(query1, model1);
     
 try {
       ResultSet results = qexec1.execSelect();
       ResultSetFormatter.out(System.out, results, query);
     }
     
 finally {
       qexec.close();
     }

 //ds.close();
// conn.dispose(); 
        
 /*       
        System.out.println("insert prepare \n");                  
        InsertTriple insert = new InsertTriple();
        insert.setPrefixe(prefixe);
        insert.setTriple(triple);
        ContactStore contactStore = new ContactStore();
        contactStore.writeTripleInRepository(insert);
      
        System.out.println("prepare du select");
        Select select = new Select();
        select.setPrefixe(prefixe);
        select.setSelect("?s ?p ?o");
        select.setWhere("<u:Birth_27000100> ?p ?o");
     
              
               HandleStore testHandleStore = new HandleStore();
               ResultSet results = testHandleStore.findAllBySelect(szHostName, szStoreName, szHostPort, szModelName, select);
               while (results.hasNext()) {
                       QuerySolution result=results.next();
                               RDFNode s=result.get("s");
                               RDFNode p=result.get("p");
                               RDFNode o=result.get("o");
                               System.out.println(" { " + s + " "+ p+ " "+ o+ " . }");


               
                      
                   }
               
               
           }
}
*/
package server;
import oracle.kv.FaultException;
import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import oracle.rdf.kv.client.jena.ConnectionCreationException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import oracle.kv.AuthenticationFailureException;
import oracle.kv.KVSecurityConstants;
import oracle.kv.PasswordCredentials;
import oracle.kv.ReauthenticateHandler;
import oracle.rdf.kv.client.jena.OracleNoSqlPool;
import oracle.rdf.kv.client.jena.SimpleLog;

public class OracleNoSqlConnection {
  oracle.rdf.kv.client.jena.OracleNoSqlConnection conn; 
  KVStore m_kvstore;
  KVStoreConfig configuration;
  String m_szHostName = "localhost";
  String m_szStoreName = "mystore";
  String m_szHostPort = "5000";
  static String m_szUserName;
  static String m_szPassword;
  String m_szConnId = null;
  
  static final String PERMUTATION_PREFIX;
  
  static {
    String szPermutationCode = System.getProperty("oracle.rdf.kv.client.jena.PCP");
    if (szPermutationCode == null)
      szPermutationCode = "`"; 
    PERMUTATION_PREFIX = szPermutationCode;
  }
  
  private boolean m_bClosed = false;
  private OracleNoSqlPool m_pool = null;
 static SimpleLog ms_log = SimpleLog.getLog(OracleNoSqlConnection.class);
  
  public OracleNoSqlConnection(String storeName, String hostName, String hostPort) {
    ms_log.debug("constructor: started");
    this.m_szStoreName = storeName;
    this.m_szHostName = hostName;
    this.m_szHostPort = hostPort;
    long lStartTime = System.currentTimeMillis();
    try {
      ms_log.debug("constructor: create KV store connection");
      this.configuration = new KVStoreConfig(this.m_szStoreName, new String[] { this.m_szHostName + ":" + this.m_szHostPort });
      this.m_kvstore = KVStoreFactory.getStore(configuration);
      this.conn = (oracle.rdf.kv.client.jena.OracleNoSqlConnection) this.m_kvstore;  
      if (ms_log.isDebugEnabled())
        ms_log.debug("constructor: done in ", (System.currentTimeMillis() - lStartTime) + " ms"); 
    } catch (IllegalArgumentException e) {
      ms_log.error("constructor:error, Illegal arguments. Exception is ", e);
      throw e;
    } catch (FaultException e) {
      ms_log.error("constructor: error, Jena adapter cannot connect to the NoSQL Database. Exception is ", (Throwable)e);
      throw new ConnectionCreationException("Oracle NoSQL connection cannot be created");
    } 
  }
  public OracleNoSqlConnection(String storeName, String hostName, String hostPort, long timeout) {
    ms_log.debug("constructor: started");
    this.m_szStoreName = storeName;
    this.m_szHostName = hostName;
    this.m_szHostPort = hostPort;
    long lStartTime = System.currentTimeMillis();
    try {
      ms_log.debug("constructor: create KV store connection");
      this.configuration = new KVStoreConfig(this.m_szStoreName, new String[] { this.m_szHostName + ":" + this.m_szHostPort });
      if (timeout > 0L && timeout < Long.MAX_VALUE)
        configuration.setRequestTimeout(timeout, TimeUnit.MILLISECONDS); 
      this.m_kvstore = KVStoreFactory.getStore(configuration);
      this.conn = (oracle.rdf.kv.client.jena.OracleNoSqlConnection) this.m_kvstore;
      if (ms_log.isDebugEnabled())
        ms_log.debug("constructor: done in ", (System.currentTimeMillis() - lStartTime) + " ms"); 
    } catch (IllegalArgumentException e) {
      ms_log.error("constructor:error, Illegal arguments. Exception is ", e);
      throw e;
    } catch (FaultException e) {
      ms_log.error("constructor: error, Jena adapter cannot connect to the NoSQL Database. Exception is ", (Throwable)e);
      throw new ConnectionCreationException("Oracle NoSQL connection cannot be created");
    } 
  }
  public OracleNoSqlConnection(String storeName, String hostName, String hostPort, String userName, String password) {
      ms_log.debug("constructor: started");
      this.m_szStoreName = storeName;
      this.m_szHostName = hostName;
      this.m_szHostPort = hostPort;
      this.m_szUserName = userName;
      this.m_szPassword = password;
      long lStartTime = System.currentTimeMillis();
        System.out.println("inside it...."); 
          Properties secProps = new Properties();
          secProps.setProperty(KVSecurityConstants.TRANSPORT_PROPERTY, KVSecurityConstants.SSL_TRANSPORT_NAME);
          secProps.setProperty(KVSecurityConstants.SSL_TRUSTSTORE_FILE_PROPERTY, "client.trust");
          PasswordCredentials loginCreds =  new PasswordCredentials(this.m_szUserName, this.m_szPassword.toCharArray());
          this.configuration.setSecurityProperties(secProps);
        System.out.println("inside it again...."); 
      try {
        ms_log.debug("constructor: create KV store connection");
          System.out.println("inside it again and again...."); 
        this.m_kvstore = KVStoreFactory.getStore(configuration, loginCreds, new ReauthenticateHandler() {
        
             @Override
             public void reauthenticate(KVStore kstore) {
                 try {
                     kstore.login(loginCreds);
                 } catch (AuthenticationFailureException afe) {
                     usage("Authentication failed!");
                 }
             }

             private void usage(String message) {
                    System.out.println("\n" + message + "\n");
                    System.out.println("usage: " + getClass().getName());
                    System.out.println("\t-store <instance name> (default: kvstore) " +
                                       "-host <host name> (default: localhost) " +
                                       "-port <port number> (default: 5000) " +
                                       "-trust <trust store> (required) " +
                                       "-user <login user> (require) " +
                                       "-pwd <password> (required)");
                    System.exit(1);
                }

          });
           
          this.conn = (oracle.rdf.kv.client.jena.OracleNoSqlConnection) this.m_kvstore;
         
        if (ms_log.isDebugEnabled())
          ms_log.debug("constructor: done in ", (System.currentTimeMillis() - lStartTime) + " ms"); 
      } catch (IllegalArgumentException e) {
        ms_log.error("constructor:error, Illegal arguments. Exception is ", e);
        throw e;
      } catch (FaultException e) {
        ms_log.error("constructor: error, Jena adapter cannot connect to the NoSQL Database. Exception is ", (Throwable)e);
        throw new ConnectionCreationException("Oracle NoSQL connection cannot be created");
      } 
    }
  
  
  public static OracleNoSqlConnection createInstance(String storeName, String hostName, String hostPort) {
    ms_log.debug("createInstance: executed");
    return new OracleNoSqlConnection(storeName, hostName, hostPort);
  }
  
  public static OracleNoSqlConnection createInstance(String storeName, String hostName, String hostPort, long timeout) {
    ms_log.debug("createInstance: executed");
    return new OracleNoSqlConnection(storeName, hostName, hostPort, timeout);
  }
    public static OracleNoSqlConnection createInstance(String storeName, String hostName, String hostPort, String userName, String password) {
      ms_log.debug("createInstance: executed");
      System.out.println("here...");
      return new OracleNoSqlConnection(storeName, hostName, hostPort, userName, password);
    }
  
 
}

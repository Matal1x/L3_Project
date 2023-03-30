package server;

import java.util.Scanner;
import py4j.GatewayServer;

public class HandleStoreEntryPoint {
    public HandleStoreEntryPoint() {  }    
    private static HandleStoreEntryPoint handleStoreEntryPoint;
    HandleStore handleStore;
    String hostName; 
    String storeName;
    String hostPort;
    String userName;
    String password;
    
    public HandleStoreEntryPoint(String hostName, String storeName, String hostPort) {
        System.out.println("entry point of store "+storeName +"  at "+hostName+ ":"+hostPort);
      this.handleStore = new HandleStore(hostName, storeName, hostPort);
     }
    
    public HandleStoreEntryPoint(String hostName, String storeName, String hostPort, String userName, String password) {
     System.out.println("entry point of secured store "+storeName +"  at "+hostName+ ":"+hostPort+" with username and password "+userName+ " "+password);   
      this.handleStore = new HandleStore(hostName, storeName, hostPort, userName, password);
    } 
    public HandleStore getHandleStore() {
      System.out.println("returning Handle Store"+handleStore.hostName); 
      return handleStore;
    }

    public static void main(String[] args) {
        String hostName; 
        String storeName;
        String hostPort;
        String userName;
        String password;
         Scanner scanner = new Scanner(System.in);
              
         System.out.println("Host Name : ");
         hostName = scanner.nextLine();
         System.out.println("Host Port : ");
         hostPort = scanner.nextLine();
         System.out.println("endpoint :"+hostName+":"+hostPort);
         System.out.println("Store Name : ");
         storeName = scanner.nextLine();
         System.out.println(storeName);
         System.out.println("secure store (yes/Yes/y/Y or no/No/n/N)");
         String response = scanner.nextLine(); 
         System.out.println(response);
         if (response == "yes" || response == "y" || response == "Yes" || response == "Y" ){
            System.out.println("User Name : ");
            userName = scanner.nextLine();
            System.out.println("password : ");
            password = scanner.nextLine();
            handleStoreEntryPoint = new HandleStoreEntryPoint(hostName, storeName, hostPort, userName, password); 
           } else {  handleStoreEntryPoint = new HandleStoreEntryPoint(hostName, storeName, hostPort); }
         
         System.out.println("accessing gateway");
         GatewayServer gatewayServer = new GatewayServer(handleStoreEntryPoint);
         gatewayServer.start();
         System.out.println("Gateway Server Started");
    }

}
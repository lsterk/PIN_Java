
/* APIController defines the actions that the kiosk makes over IP
Currently just tests connection to API server


*/
package kiosk;

import java.net.InetAddress;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class APIController {
  private final String API_SERVER_NAME = "localhost";
  private final int API_SERVER_PORT = 5000;


  public static void main(String[] args){
    System.out.println("Executing tests");
    APIController c = new APIController();
    System.out.println("The server connection will be tested:");
    if (c.testConnection()){
      System.out.println("Connection passed!");
    }
    else {
      System.out.println("Connection is currently down");
    }

  }

  public APIController(){
    // nothing needs to be intialized...
  }

  public boolean testConnection(){
    return testConnection(API_SERVER_NAME, API_SERVER_PORT);

  }

  public boolean testConnection(String hostname, int portNum){
    try{
      URL u = new URL("http", hostname, portNum, "test");
      HttpURLConnection conn = (HttpURLConnection) u.openConnection();
      System.out.println("Server response message: " + conn.getResponseMessage());
      System.out.println("System received response code: " + conn.getResponseCode());
    }
    catch (MalformedURLException m){
      System.err.println("A URL exception occurred:");
      System.err.print(m);
      return false;
    }
    catch (IOException i){
      System.err.println("An error occurred in reading the connection:");
      System.err.println(i);
      return false;
    }
    return true;
  }

  @FXML
  private void handleTestPress(ActionEvent event){
    System.out.println("The server connection will be tested:");
    if (testConnection()){
      System.out.println("Connection passed!");
    }
    else {
      System.out.println("Connection is currently down");
    }
  }
}

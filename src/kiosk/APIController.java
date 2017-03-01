/*
Controller for managing connections to the API server
@author: Landon
@created : February 27, 2017
@version : 0.1
@updated : February 27, 2017
Note that the FXML components are not live yet, and the API Server is still localhost
 */

package kiosk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.ConnectException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class APIController implements Initializable{
    static final String API_SERVER_NAME = "localhost";  // address of API server (convert to hostname or IP later)
    static final int API_SERVER_PORT = 5000;  // port on which the API server is listening for requests
    static final String API_TEST_ADDRESS = "/test";  // path for testing connection status


    public static void main(String[] args){
        /*
        Method for conducting class test alone, without using another application
        All errors are handled, so there should only be text printed.
         */
        System.out.println("Executing tests");
        // Need to create Controller to use non-static methods
        //APIController c = new APIController();
        System.out.println("The server connection will be tested:");
        if (testConnection()){
            System.out.println("Connection passed!");
        }
        else {
            System.err.println("Connection is currently down");
        }

    }

    public APIController(){
        // nothing needs to be intialized...
    }

    public static boolean testConnection(){
        /*
        Default testConnection method takes static variables and sets verbosity to true
        Follow the logic in testConnection(String,int,String,boolean) below
         */
        return testConnection(API_SERVER_NAME, API_SERVER_PORT, API_TEST_ADDRESS, true);

    }

    public static boolean testConnection(String hostname, int portNum, String filePath, boolean verbose){
        /*
        testConnection method will generate HTTP GET request to test the given API server
        @param1: String hostname, the hostname of the web server. E.g. `localhost`, `153.116.110.42`, etc.
        @param2: int portNum, the port on which the HTTP server is listening
        @param3: String filePath, the path to test. For kiosk, the path is /test
        @param4: boolean verbose, if enabled, will have print statements to track test process
         */
        try{
            if (verbose){
                System.out.format("Sending a message to http://%s:%d%s \n", hostname, portNum, filePath);
            }
            URL u = new URL("http", hostname, portNum, filePath);
            // read the message contents
            // yes, this is TOTAL BS that Java connection objects don't have better reading methods
            BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
            String strTemp;
            while (null != (strTemp = br.readLine())) {
                if (verbose) {
                    System.out.println(strTemp);
                }
            }
        }
        catch (ConnectException c){
            // Error generated if a connection to the API server cannot be made
            if (verbose) {
                System.err.println("The connection was refused by the server");
            }
            return false;
        }
        catch (Exception e){
            // Other exceptions, e.g. IOException
            System.err.print("Exception encountered:\n" + e.getLocalizedMessage());
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // empty

        // include some code to do the test?
        // color the button according to the test results?
    }


    @FXML
    private void handleTestPress(ActionEvent event){
        /*
        Start of a method to allow the testConnection to be run from an FXML button
         */
        System.out.println("The server connection will be tested:");
        if (testConnection()){
            System.out.println("Connection passed!");
        }
        else {
            System.out.println("Connection is currently down");
        }
    }
}

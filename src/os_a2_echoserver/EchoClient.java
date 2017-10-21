/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Group Members: Joshua Mallari, Jaekuk Song
package os_a2_echoserver;
import java.io.*;
import java.net.*;

/**
 *
 * @author Jack
 */
public class EchoClient {
    public static void main(String[] args) throws IOException {
        String str = null; //string variable for holding data
        
        //this loop checks for the right number of command line inputs, both the host name and port number
            //if one of them or neither of them is inputted the error will be displayed
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        
        String host = args[0]; //host name/ip of the client, command line input
        int portNum = Integer.parseInt(args[1]); //port number that will be used by client, command line input

        try {            
            //creating a socket of the client, requiring the host name/ip, and port number
            Socket echoSoc = new Socket(host, portNum);
            //Gets the socket's output stream and opens a PrintWriter on it, this allows communication between server and client
            PrintWriter w_out = new PrintWriter(echoSoc.getOutputStream(), true);
            //Gets the socket's input stream and opens a BufferedReader on it
            BufferedReader r_in = new BufferedReader(new InputStreamReader(echoSoc.getInputStream()));
            //object for standard input
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connection successful with " + host + ". (Type 'End' to end connection)");
            
            //this is the first data to be sent to the socket
            str = "first";
            //the str variable holding the data will send to the client's socket, which the will be transferred to the server's socket
            w_out.println(str);
                do{ //the loop will keep sending and receiving data between client and server
                    str = r_in.readLine(); //when data comes back from server, str will hold it
                    if (str != null){
                        System.out.println("Server: " + str); //data from server will be displayed
                    }
                    System.out.print("Send data: "); //here, it will ask for the next data
                    str = stdIn.readLine(); //will read the text, in this case the command line
                    System.out.println("Client: " + str); //will print out what user inputted
                    w_out.println(str); //data is sent again to the client's socket, which the will be transferred to the server's socket
                } while (!str.equals("End")); //the loop will only end once the client enters the string "End"
            //System.out.println("\nDisconnecting....\nExiting Program.");
            } catch (UnknownHostException e) {
                System.err.println(e.getMessage() + "\nDon't know about host '" + host + "'");
                System.exit(1);
            } catch (IOException e) {
                System.err.println(e.getMessage() + "\nCouldn't get I/O for the connection to " +
                    host + " and " + portNum);
                System.exit(1);
            }
    }
}

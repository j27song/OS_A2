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

/*To start the entire process of this program, MAKE SURE TO BE WITHIN THE PACKAGENAME DIRECTORY!!
When using the command line command "java -cp . os_a2_echoserver.EchoServer 9999" for example, this does not work WITHIN the
directory that contains EchoServer, EchoClient files, ONLY OUTSIDE of that directory. So what I'm saying is be in the directory
"C:\Users\Username\.....\OS_A2_EchoServer_TEST\src" which is where the packagename directory is in. 
From here, then use "java -cp . os_a2_echoserver.EchoServer 9999" and so on to start the program.
*/

public class EchoServer {

    public static void main(String[] args) throws Exception{
        
        //this loop checks for right number of command line inputs, the port number
            //if port number is not stated, the error will be displayed
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        
        int portNum = Integer.parseInt(args[0]); //port number of the client
        
        System.out.println("Server has started, and ready to connect");
        
        try (
            //creating a socket on the server side, using the port number stated above
            ServerSocket ser_Soc = new ServerSocket(portNum);
            //creating a socket of the client
                //This will listen to any incoming connection requests through the named port
            Socket cli_Soc = ser_Soc.accept(); /*if connection is successful, accept method returns a new Socket object
                                                which is bound to local port, remote address and port set to that client.
                                                Any incoming connection requests will be listend to on the original ServerSocket.*/
            //Gets the socket's input stream and opens a BufferedReader on it
            BufferedReader r_in = new BufferedReader(new InputStreamReader(cli_Soc.getInputStream()));
            //Gets the socket's output stream and opens a PrintWriter on it
            PrintWriter w_out = new PrintWriter(cli_Soc.getOutputStream(), true);
            ){
            System.out.println("Client request confirmed.\nClient successfully connected.");
            String userInput; //variable to hold incoming string/data
            
            do{ //loop for receiving from and sending data to client
                if ((userInput = r_in.readLine()) != null){ //reads data from socket, places in variable, and checks for null
                    w_out.println("echo: " + userInput); //if not null, it sends echos back the data
                }
            } while (!userInput.equals("End")); //loop only ends when the data received is "End"
           
            cli_Soc.close(); //this closes the client socket object, which ends the connection
            
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage()); //general I/O error message
        }
    }
    
}




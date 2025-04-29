package partTwo;

import java.io.*;
import java.net.*;

public class server_part1 {
    public static void main(String args[]) {
        DatagramSocket socket = null; //clear socket for safe purpose
        try {
            // create variable to get specified port number to open for client
            int port = Integer.parseInt(args[0]);

            socket = new DatagramSocket(port); // create socket on port
            
            //notify 
            System.out.println("Health Monitoring Server running on port " + port);
            
            //preparing to receive the reply
            byte[] buffer = new byte[1000]; // creating buffer of max size

            while(true) { //to keep running

                //Receive packet in the buffer, and record it to `packet` variable
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet); //pull the packet from the buffer

                // Now from the pulled packet, capture data and length
                String data = new String(packet.getData(), 0, packet.getLength());
                
                // 👉 UPDATED LINE: show the port number in output (for Q2)
                System.out.println("Received Health Data on port " + port + ": " + data);
            }
            
        } catch (SocketException e) { 
            System.out.println("Socket error: " + e.getMessage());
        } catch (IOException e) { 
            System.out.println("IO error: " + e.getMessage());
        } finally { //closing
            if (socket != null) socket.close();
        }
    }
}


/*
  STEPS ==============================
    1. Open 4 terminals (1 for client, 3 for server)
    2. Compile code `javac client_part1.java server_part1.java`
    3. In each terminal `java server_part1 9000` specifying which port will receive data
    4. Run client terminal `java client_part1`
 */



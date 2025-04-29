package partTwo;
import java.io.*;
import java.net.*;

public class client_part1 {
    public static void main(String args[]) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(); // Create UDP socket 
            InetAddress serverAddress = InetAddress.getByName("localhost"); 
            
            // Load balancer port
            int loadBalancerPort = 8000;
            
            // send fake data
            String[] healthData = {
                "Heart Rate: 85 bpm, Temperature: 37.5°C",
                "Heart Rate: 72 bpm, Temperature: 36.8°C",
                "Heart Rate: 190 bpm, Temperature: 37.2°C",
                "Heart Rate: 20 bpm, Temperature: 34.2°C",
                "Heart Rate: 90 bpm, Temperature: 39.2°C",
                "Heart Rate: 50 bpm, Temperature: 32.2°C",
                "Heart Rate: 100 bpm, Temperature: 31.2°C"
            };
            
            // Send data to load balancer
            for (String data : healthData) {
                // preparing buffer 
                byte[] buffer = data.getBytes();

                DatagramPacket request = new DatagramPacket(
                    buffer, 
                    buffer.length, 
                    serverAddress, 
                    loadBalancerPort
                );
                
                // Send packet
                socket.send(request);

                // Receive response from load balancer
                byte[] responseBuffer = new byte[1000];

                // Receiving acknowledgement from loadbalancer 
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                socket.receive(responsePacket);

                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());

                // Print output
                System.out.println(data);
                System.out.println(response + "\n");

            }
            
        } catch (SocketException e) { 
            System.out.println("Socket error: " + e.getMessage());
        } catch (IOException e) { 
            System.out.println("IO error: " + e.getMessage());
        } finally { // closing
            if (socket != null) socket.close();
        }
    }
}

package partTwo;

import java.net.*;

public class load_balancer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8000);
            System.out.println("Load Balancer running on port 8000");

            byte[] buffer = new byte[1024];
            int[] serverPorts = {9000, 9001, 9002};
            int index = 0;

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String data = new String(request.getData(), 0, request.getLength());

                int targetPort = serverPorts[index];
                InetAddress serverAddress = InetAddress.getByName("localhost");

                // Forward data to server as a reply
                DatagramPacket forwardPacket = new DatagramPacket(
                    data.getBytes(), data.length(), serverAddress, targetPort);
                
                socket.send(forwardPacket);

                // Reply back to client with info
                String ack = "Forwarded to port " + targetPort;
                DatagramPacket ackPacket = new DatagramPacket(
                    ack.getBytes(), ack.length(), request.getAddress(), request.getPort());
                
                socket.send(ackPacket);

                // Print info in Load Balancer console
                System.out.println("Forwarded health data to: localhost on port " + targetPort);

                index = (index + 1) % serverPorts.length;
            }

        } catch (Exception e) {
            System.out.println("Load Balancer Error: " + e.getMessage());
        }
    }
}

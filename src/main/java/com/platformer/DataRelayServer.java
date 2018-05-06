package com.platformer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Nathan Pell on 7/14/2017.
 */
public class DataRelayServer extends Thread{
    private int portNumber;
    private volatile boolean stopCalled = false;

    public DataRelayServer(int newPortNumber) {
        portNumber = newPortNumber;
    }

    @Override
    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                OutputStream out = clientSocket.getOutputStream();
        ) {
            System.out.println("Server socket created. Client Connected.");
            // Initiate conversation with client
            out.write(DeviceManager.RemoveFromSensorQueue().getBytes("UTF-8"));

            while (stopCalled == false) {
                if (clientSocket.isConnected()) {
                    // Initiate conversation with client
                    //Thread.sleep(1);
                    out.write( DeviceManager.RemoveFromSensorQueue().getBytes("UTF-8"));
                } else {
                    System.out.println("Client not connected during relay sequence.");
                }
            }

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } //catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public void StopThread() {
        stopCalled = true;
    }
}

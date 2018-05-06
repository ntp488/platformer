package com.platformer;

import com.fazecast.jSerialComm.SerialPort;
import arduino.*;
import java.util.*;

/**
 * Created by Owner on 7/12/2017.
 */
public class DeviceManager {
    private static int baudRate = 9600;
    private volatile static Arduino platformer = null;
    private static SerialPort port;
    private static Deque<String> sensorQueue = new ArrayDeque();
    private static SerialInputThread sensorThread;
    private static DataRelayServer relayServer;
    public static boolean deviceCurrentlyConnected = false;

    static {
        if (!AttemptDeviceDiscoveryAndConnection()) {
            //device discovered and connected. begin reading data cyclically
            //sensorQueue.addFirst(platformer.serialRead());
            //System.out.println(sensorQueue.peekLast());
            System.out.println("Could not establish device connection. Please re-attempt.");
        }
    }

    public static Arduino GetDevice() {
        return platformer;
    }

    public static void CloseConnection() {
        if (platformer != null) {
            relayServer.StopThread();
            sensorThread.StopThread();
            platformer.closeConnection();
            System.out.println("Connection Closed, Threads Halted.");
        } else {
            System.out.println("Connection was null.");
        }
    }

    private static boolean AttemptDeviceDiscovery(){
        for (SerialPort tempPort:SerialPort.getCommPorts()) {
            System.out.println(
                    "Descriptive Port Name: " + tempPort.getDescriptivePortName() +
                    "\nSystem Port Name: " + tempPort.getSystemPortName()
            );
            if (tempPort.getDescriptivePortName().contains("Arduino Uno")) {
                port = tempPort;
                return true;
            }
        }
        return false;
    }

    private static boolean CreateConnection() {
        if (port != null) {
            platformer = new Arduino(port.getSystemPortName(), baudRate);
            if (platformer.openConnection()) {
                System.out.println("Opening connection successful.");
                return true;
            } else {
                System.out.println("Opening connection failed.");
                return false;
            }

//                int sentinel = 5;
//                while (sentinel > 0) {
//                    sentinel--;
//                    System.out.println(platformer.serialRead());
//                }
        } else {
            System.out.println("Connection failed because Serial Port was null.");
            return false;
        }
    }

    public static boolean AttemptDeviceDiscoveryAndConnection() {
        if (AttemptDeviceDiscovery()) {
            if (CreateConnection()) {
                deviceCurrentlyConnected = true;
                sensorThread = new SerialInputThread();
                sensorThread.start();
                relayServer = new DataRelayServer(21025);
                relayServer.start();
                System.out.println("Established device connection.");
                return true;
            }
        }
        return false;
    }

    public static synchronized Deque<String> GetSensorQueue() {
        return sensorQueue;
    }

    public static synchronized void AddToSensorQueue(String newItem) {
        sensorQueue.addFirst(newItem);
        //System.out.println(sensorQueue.peekFirst());
        //System.out.println(sensorQueue.size());
    }

    public static synchronized String RemoveFromSensorQueue() {
        if (!sensorQueue.isEmpty()) {
            return sensorQueue.removeLast();
        } else {
            return "";
        }
    }

}

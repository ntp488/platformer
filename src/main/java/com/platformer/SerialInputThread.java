package com.platformer;

/**
 * Created by Owner on 7/14/2017.
 */
public class SerialInputThread extends Thread{
    private volatile boolean stopCalled = false;
    private StringBuilder serialBuffer = new StringBuilder();
    private String terminationSequence = "EOL"; // Anything that can't be part of a message

    public SerialInputThread() {
    }

    @Override
    public void run() {

        while(stopCalled == false) {
            //System.out.println(" looping ...");

            // Sleep for a while
//            try {
                serialBuffer.append(DeviceManager.GetDevice().serialRead());
                String bufferString = serialBuffer.toString();

                if (bufferString.contains(terminationSequence)) {
                    int indexOfTermination = bufferString.indexOf(terminationSequence);
                    String message = bufferString.substring(0, indexOfTermination);
                    bufferString = bufferString.substring(message.length() + terminationSequence.length());

                    DeviceManager.AddToSensorQueue(message.replace("\n", ""));
                    System.out.println("Added " + message.replace("\n", "") + " to queue.");
                }
                serialBuffer = new StringBuilder(bufferString);


                //Thread.sleep(1);
//            } catch (InterruptedException e) {
//                // Interrupted exception will occur if
//                // the Worker object's interrupt() method
//                // is called. interrupt() is inherited
//                // from the Thread class.
//                break;
//            }
        }
    }

    public void StopThread() {
        stopCalled = true;
    }
}
//    public void Receiver(object sender, SerialDataReceivedEventArgs e)
//    {
//        string data = COMPort.ReadExisting();
//        serialBuffer.Append(data);
//
//        string bufferString = serialBuffer.ToString();
//
//        int index = -1;
//        do
//        {
//            index = bufferString.IndexOf(terminationSequence);
//            if (index > -1)
//            {
//                string message = bufferString.Substring(0, index);
//                bufferString = bufferString.Remove(0, index + terminationSequence.Length);
//
//                HandleMessage(message);
//            }
//        }
//        while (index > -1)
//
//        serialBuffer = new StringBuilder(bufferString);
//    }

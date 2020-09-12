package bean;

import java.io.IOException;
import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

import util.SerialPortArduino;

public class Main {

	private static SerialPort port;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*SerialPortArduino arduino = new SerialPortArduino();
		arduino.initialize();
		Thread t=new Thread() {
			 public void run() {
				 try {
				 //Messy implementation but it works for this demo purpose
				    while(true){
				      //optional sleep  
				      Thread.sleep(500);

						
				    }
				 } catch (InterruptedException ie) {}
			 }
		 };
		 t.start();
		 System.out.println("Started");
		 }*/
		 SerialPort[] portNames = SerialPort.getCommPorts();
		 
		 for(SerialPort portName : portNames) {
			 System.out.println(portName.getSystemPortName());
		 }
		 
		 connect();
	}
	
	private static void connect() {
		port = SerialPort.getCommPort("COM3");
		
		if(port.openPort()) {
			System.out.println("Conectado!");
			readSerial();
		}
		else {
			System.out.println("Desconectado!");
		}
	}
	
	private static void readSerial() {
		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		InputStream in = port.getInputStream();
		
		try
		{
		   for (int j = 0; j < 1000; ++j)
		      System.out.print((char)in.read());
		   in.close();
		} catch (Exception e) { e.printStackTrace(); }
		port.closePort();
	}
}

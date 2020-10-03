package util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;

public class ManagerSerialPort {
	
	private SerialPort port; 
	private boolean isConnected = false;;
	
	public ManagerSerialPort() {}
	
	public void connect(String portName) {
		port = SerialPort.getCommPort(portName);
		
		if(port.openPort()) {
			isConnected = true;
		}
		else {
			isConnected = false;
		}
	}
	
	public String readSerial() {
		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		InputStream in = port.getInputStream();
		StringBuilder output = new StringBuilder();
		
		try
		{
		   for (int j = 0; j < 1000; ++j) {
			  char chracter = (char) in.read();
			  output.append(chracter);
		   }
		   in.close();
		} catch (Exception e) { e.printStackTrace(); }
		
		port.closePort();
		
		return output.toString();
	}
	
	public List<String> getAvailablePorts() {
		SerialPort[] ports = SerialPort.getCommPorts();
		List<String> portsName = new ArrayList<String>(); 
		
		for(SerialPort portName : ports) {
			 portsName.add(portName.getSystemPortName());
			 System.out.println(portName.getSystemPortName());
		}
		
		return portsName;
	}

	public SerialPort getPort() {
		return port;
	}

	public void setPort(SerialPort port) {
		this.port = port;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

}

package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Sensor;
import util.ManagerSerialPort;

public class Main {

	private static String portName =  "COM3";
	private static ManagerSerialPort managerPort;
	private static List<Sensor> sensors;
	
	public static void main(String[] args) throws IOException {
		managerPort = new ManagerSerialPort();
		
		//managerPort.getAvalaiblePorts();
		
		managerPort.connect(portName);
		
		if(!managerPort.isConnected())
			return;
		
		String response = managerPort.readSerial();
		
		sensors = transformSerialToSensor(response);
		for(Sensor sensor : sensors) {
			System.out.println(sensor.getSensorName() + ", " + sensor.getValueReference() + ", " + sensor.getResultReference());
		}
	}
	
	private static List<Sensor> transformSerialToSensor(String responseSerial) {
		sensors = new ArrayList<Sensor>();
		
		String serialSplit[] = new String[10];
		serialSplit = responseSerial.split("\n");
		
		 String variables[] = serialSplit[5].split(";");
		
		for(int i = 0; i < 4; i++) {
			String valueReference = variables[i].replaceAll("[\\[\\]\"]", "");

			String valueParsed[] = valueReference.split(",");
			
			Sensor sensor = new Sensor();
			sensor.setSensorName(valueParsed[0]);
			sensor.setValueReference(valueParsed[1]);
			sensor.setResultReference(valueParsed[2] == "1" ? true : false);
			
			sensors.add(sensor);
		}
		
		return sensors;		
	}
}

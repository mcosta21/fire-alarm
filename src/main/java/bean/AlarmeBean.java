package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;

import com.fazecast.jSerialComm.SerialPort;

import model.Sensor;
import util.ManagerSerialPort;

@ManagedBean
public class AlarmeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String temperature;
	private String gas;
	private String light;
	private String presence;
	
	private String statusConnection;
	
	private String portName =  "COM3";
	private ManagerSerialPort managerPort;
	private List<Sensor> sensors;
	
	private boolean showAlert = false;
	private String phraseAlert = "Normal";
	
	public AlarmeBean() {
		System.out.println("init");
		managerPort = new ManagerSerialPort();
		
		//managerPort.getAvalaiblePorts();
		sensors = new ArrayList<Sensor>();
		for(int i = 0; i < 4; i++) {
			Sensor sensorTemp = new Sensor();
			sensorTemp.setValueReference("...");
			sensors.add(sensorTemp);
		}
	}
	
	public String convertSensorToName(String sensorName) {
		switch(sensorName) {
			case "gas": return "Gás"; 
			case "light": return "Luz";
			case "temp": return "Temperatura";
			case "presence": return "Presença";
			default: return "Carregando";
			}
	}
	
	public String getValueReferenceWithUnitMeasurement(String sensorName, String valueReference) {
		switch(sensorName) {
			case "gas": return valueReference + " ppm"; 
			case "light": return valueReference + " Ω";
			case "temp": return valueReference + " C°";
			case "presence": return valueReference.equals("1") ? "ON" : "OFF";
			default: return "0.00";
		}
	}
	
		public void getValuesFromSerial() {
		managerPort.connect(portName);
		
		if(!managerPort.isConnected())
			return;
		String response = managerPort.readSerial();
		
		sensors = transformSerialToSensor(response);
	}
	
	private List<Sensor> transformSerialToSensor(String responseSerial) {
		sensors = new ArrayList<Sensor>();
		
		System.out.println(responseSerial);
		
		String serialSplit[] = new String[8];
		serialSplit = responseSerial.split("\n");
		
		 String variables[] = serialSplit[3].split(";");
		 
		 StringBuilder concatResponse = new StringBuilder();
		 
		 for(int i = 0; i < 4; i++) {
			String valueReference = variables[i].replaceAll("[\\[\\]\"]", "");

			String valueParsed[] = valueReference.split(",");
			
			Sensor sensor = new Sensor();
			sensor.setSensorName(valueParsed[0]);
			sensor.setValuePercentage(valueParsed[1]);
			sensor.setValueReference(valueParsed[2]);
			sensor.setResultReference(valueParsed[3].equals("1") ? true : false);

			concatResponse.append(valueParsed[3]);
			
			sensors.add(sensor);
		 }
		 
		  // Normal = gas -> 0, luz -> 0, temp -> 0
		  // Em risco = gas -> 0, luz -> 0, temp -> 1
		  // Risco eminente = gas -> 1, luz -> 0, temp -> 1
		  // Incendio Nivel 1 = gas -> 0, luz -> 1, temp -> 1
		  // Incendio Nivel 2 = gas -> 1, luz -> 1, temp -> 1
		  // Alerta Vermelho = gas -> 0/1, luz -> 1, temp -> 1, presenca -> 1
		
		 // gas light temp presence
		 
		 switch(concatResponse.toString()) {
		 case "0010": phraseAlert = "Em risco"; showAlert = true; break;
		 
		 case "1010": phraseAlert = "Risco eminente"; showAlert = true; break;
		 case "1011": phraseAlert = "Risco eminente"; showAlert = true; break;
		 
		 case "0110": phraseAlert = "Incendio Nivel 1"; showAlert = true; break;
		 case "1110": phraseAlert = "Incendio Nivel 2"; showAlert = true; break;
		 
		 case "0111": phraseAlert = "Alerta Vermelho"; showAlert = true; break;
		 case "1111": phraseAlert = "Alerta Vermelho"; showAlert = true; break;
		 default: showAlert = false; break;
		 }
		 
		 if(showAlert) {
			 showDialogAlert();
		 }
		 else {
			 hideDialogAlert();
		 }
		 
		return sensors;		
	}
	
	private void showDialogAlert() {
		PrimeFaces.current().executeScript("PF('dialogAlert').show()");
	}
	
	private void hideDialogAlert() {
		PrimeFaces.current().executeScript("PF('dialogAlert').hide()");
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getPresence() {
		return presence;
	}

	public void setPresence(String presence) {
		this.presence = presence;
	}

	public String getStatusConnection() {
		return statusConnection;
	}

	public void setStatusConnection(String statusConnection) {
		this.statusConnection = statusConnection;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getPhraseAlert() {
		return phraseAlert;
	}

	public void setPhraseAlert(String phraseAlert) {
		this.phraseAlert = phraseAlert;
	}
	
	
	
}

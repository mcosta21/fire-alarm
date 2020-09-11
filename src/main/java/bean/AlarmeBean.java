package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import util.ArduinoSerial;
import util.ControlePorta;

@ManagedBean
public class AlarmeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String title = "teste";
	
	public AlarmeBean() {
		
		ControlePorta arduino = new ControlePorta("COM3", 9600);
		arduino.enviaDados(1);
		/*
		ArduinoSerial arduino = new ArduinoSerial("COM3");
		//SerialPortArduino arduino = new SerialPortArduino();
		Thread t = new Thread() {
			@Override
			public void run() {

				arduino.initialize();
				System.out.println(arduino.read());
			}
		};
		t.start();
		*/
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}

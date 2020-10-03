package model;

public class Sensor {
	
	private String sensorName;
	
	private String valuePercentage; 
	
	private String valueReference;
	
	private boolean resultReference;

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getValueReference() {
		return valueReference;
	}

	public void setValueReference(String valueReference) {
		this.valueReference = valueReference;
	}

	public boolean getResultReference() {
		return resultReference;
	}

	public void setResultReference(boolean resultReference) {
		this.resultReference = resultReference;
	}

	public String getValuePercentage() {
		return valuePercentage;
	}

	public void setValuePercentage(String valuePercentage) {
		this.valuePercentage = valuePercentage;
	}
	
}

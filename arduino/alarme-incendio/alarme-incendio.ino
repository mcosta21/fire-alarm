/* 
  Faculdade Católica Salesiana Macaé
  Marcio da Silva Costa
    2020/2
  Laboratório de Sistemas Embarcados
*/

#include <LiquidCrystal.h>

const int rs = 12;
const int en = 11;
const int d4 = 5;
const int d5 = 4;
const int d6 = 3;
const int d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

#define pinGas A0
const int MIN_GAS = 35;
const int MAX_GAS = 290;

#define pinLight A1
const int MIN_LIGHT = 0;
const int MAX_LIGHT = 255;

#define pinTemperature A2
const int MIN_TEMP = 15;
const int MAX_TEMP = 35;

#define pinPresence 13

#define pinPiezo 8

void setup()
{
  Serial.begin(9600);
  pinMode(pinPresence, INPUT);
  pinMode(pinPiezo, OUTPUT);
  
  lcd.begin(16, 2);
  lcd.print("ALARME INCENDIO");
}

void loop()
{
  // fisico -> min = 35  e max = 290; tinkercad -> min = 85 e max = 370 - gas >= 30%
  bool paramGas = gasAlarm(MIN_GAS, MAX_GAS, 30);
  
  // fisico -> min = 0  e max = 255; tinkercad -> min = 0 e max = 1010 - luz >= 60%
  bool paramLight = lightAlarm(MIN_LIGHT, MAX_LIGHT, 60);
  
  // fisico -> min = X  e max = X; tinkercad -> min = 15 e max = 35 - temp >= 20%
  bool paramTemperature = temperatureAlarm(MIN_TEMP, MAX_TEMP, 20);
  
  bool paramPresence = presenceAlarm();
  
  lcd.setCursor(0, 1);
  
  // Normal = gas -> 0, luz -> 0, temp -> 0
  // Em risco = gas -> 0, luz -> 0, temp -> 1
  // Risco eminente = gas -> 1, luz -> 0, temp -> 1
  // Incendio Nivel 1 = gas -> 0, luz -> 1, temp -> 1
  // Incendio Nivel 2 = gas -> 1, luz -> 1, temp -> 1
  // Alerta Vermelho = gas -> 0/1, luz -> 1, temp -> 1, presenca -> 1
  
  if (paramLight == true && paramTemperature == true && paramPresence == true){
      //Serial.println("Alerta Vermelho");
      lcd.print("Alerta Vermelho ");
      playPiezo(true);
  }
  else if (paramGas == false && paramLight == false && paramTemperature == true){
      //Serial.println("Em Risco");
      lcd.print("Em Risco       ");
  } 
  else if (paramGas == true && paramLight == false && paramTemperature == true){
      //Serial.println("Risco eminente");
      lcd.print("Risco eminente ");
  } 
  else if (paramGas == false && paramLight == true && paramTemperature == true){
      //Serial.println("Incendio Nivel 1");
      lcd.print("Incendio Nivel 1");
  }  
  else if (paramGas == true && paramLight == true){
      //Serial.println("Incendio Nivel 2");
      lcd.print("Incendio Nivel 2");
  }  
  else {
      //Serial.println("Normal");  
      lcd.print("Normal          ");
  }
  
  delay(1000);
  playPiezo(false);
}

bool presenceAlarm(){
    bool value = digitalRead(pinPresence);
    serialPrint("[presence,", value == 1 ? "Movimento local" : "Sem movimento local", String(value), value, 1);
    return value;
}

bool temperatureAlarm(int min, int max, int percentage){
    float decimal = analogRead(pinTemperature);
    float voltTemperature = convertDecToVolt(decimal);
    float value = convertVoltToCelsius(voltTemperature);
    float cutValue = calculeCutValue(min, max, percentage);
    bool response = (value >= cutValue || value > max) ? true : false;
    serialPrint("[temp,", String(getPercentageOfValue(min, max, value)) + '%', String(value), response, 0);
    return response;
}

bool lightAlarm(int min, int max, int percentage){
    int value = analogRead(pinLight);
    float cutValue = calculeCutValue(min, max, percentage);
    bool response = value >= cutValue ? true : false;
    serialPrint("[light,", String(getPercentageOfValue(min, max, value)) + '%', String(value), response, 0);
    return response;
}

bool gasAlarm(int min, int max, int percentage){
    int value = analogRead(pinGas);
    float cutValue = calculeCutValue(min, max, percentage);
    bool response = value >= cutValue ? true : false;
    serialPrint("[gas,", String(getPercentageOfValue(min, max, value)) + '%', String(value), response, 0);
    return response;
}

float calculeCutValue(float min, float max, float percentage){
    float range = max - min;
    float result = range / 100 * percentage + min;
    return result;
}

float getPercentageOfValue(float min, float max, float value){
    float range = max - min;
    float percentage = (value-min) / (range / 100);
    if(percentage > 100) return 100;
    if(percentage < 0) return 0;
    return percentage;
}

float convertDecToVolt(float decimal){
    return (decimal / 1024) * 5;
}

float convertVoltToCelsius(float volt){
    return (volt - 0.5) * 100;
}

float convertCelsiusToFahrenheit(float celsius){
    return (celsius * 9 / 5) + 32;
}

void playPiezo(bool state){
    digitalWrite(pinPiezo, state == true ? HIGH : LOW);
    delay(100);
}

void serialPrint(String title, String valuePercentage, String valueReference, bool response, bool separator){
  Serial.print(title);
    Serial.print(valuePercentage);
    Serial.print(","); 
    Serial.print(valueReference);
    Serial.print(","); 
    Serial.print(response);
    Serial.print("];");
  if(separator) Serial.println();
}

#include <SoftwareSerial.h>
SoftwareSerial mySerial(2,3);
//lux
#define ldm A4
#define ldmVcc  13
#define luxCalcScalar           12518931
#define luxCalcExpoent         -1.405
#define refResistance            5030
#define maxAdcReading           1023
#define adcRefVoltage           5.0
//temp/humi
#include <DHT.h>
#define dhtPin A2
#define dhtType DHT11
DHT dht(dhtPin, dhtType);
//som
#define ldrPin A5
const int sampleWindow = 50;
float db = 1;
float dbAverage;
float sumOfDb;
int sample;
int counter = 0;

void setup() {
  pinMode(12,OUTPUT);
  digitalWrite(12,HIGH);
  pinMode(ldmVcc, OUTPUT);
  pinMode(ldrPin, INPUT);
  digitalWrite(ldmVcc, HIGH);
  Serial.begin(9600);
  dht.begin();
}

void loop() {
  float h = dht.readHumidity();
  float t = dht.readTemperature();
  if (isnan(h) || isnan(t)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }
  /* Sensacao termica
  float hic = dht.computeHeatIndex(t, h, false);
  */
  
  Serial.print("temp#");
  Serial.print(t);
  Serial.print("#luz#");
  Serial.print(readLdm());
  Serial.print("#humi#");
  Serial.print(h);
  Serial.print("#som#");
  Serial.print(round(readLdr()));
  //Serial.println(round(readLdr()));
  delay(1000);
}

float readLdm(){
  int   ldrRawData;
  float resistorVoltage, ldrVoltage;
  float ldrResistance;
  float ldrLux;
  ldrRawData = analogRead(ldm);
  resistorVoltage = (float)ldrRawData / maxAdcReading * adcRefVoltage;
  ldrVoltage = adcRefVoltage - resistorVoltage;
  ldrResistance = ldrVoltage / resistorVoltage * refResistance;
  ldrLux = luxCalcScalar * pow(ldrResistance, luxCalcExpoent);
  return ldrLux;
}

float readLdr(){
  unsigned long startMillis = millis();
  unsigned int peakToPeak = 0;
  unsigned int signalMax = 0;
  unsigned int signalMin = 1024;
  // collect data for 50 mS
  while (millis() - startMillis < sampleWindow)
  {
    sample = analogRead(A3);
    if (sample < 1024)
    {
      if (sample > signalMax)
      {
        signalMax = sample;
      }
      else if (sample < signalMin)
      {
        signalMin = sample;
      }
    }
  }
  peakToPeak = signalMax - signalMin;
  double volts = (peakToPeak * 3.3) / 1024;
  db = 20 * log10(volts / 0.0003);
  if (db < 1) {
    db = 0;
  }
  counter++;
  if (counter < 0) {
    counter = 1;
    sumOfDb = 0;
  }
  sumOfDb += db;
  dbAverage = sumOfDb / counter;
  return dbAverage;
}

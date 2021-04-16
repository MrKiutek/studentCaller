#include <ESP8266WiFi.h>
#include <WiFiClient.h>

const char* ssid = "SFR-kiutek";
const char* pass = "louna150802";
const char* host = "192.168.3.11";
const int   port = 3333;

int valeur = 0;

void setup() {
  delay(500);
  Serial.begin(38400);
  //Serial.println();
  //Serial.print("Connecté à ");
  //Serial.println(ssid);
  
  //Serial.println("Essai de connection à ");
  //Serial.println(host);
  
  WiFi.mode(WIFI_STA); 
  delay(1000);
  WiFi.begin(ssid, pass);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    //Serial.print(".");
  }
//Serial.println("IP : ");
//Serial.print(WiFi.localIP());
}

void loop() {
  

  if(Serial.available()){
    DataToServer(Serial.readString());

}

}

  //DataToServer();

void DataToServer(String data){

  WiFiClient client;
  
  if (!client.connect(host, port)) {
    Serial.println(".");
    return;
  }
  

  client.println(data);
  delay(100);

  client.flush();
  client.stop();

}
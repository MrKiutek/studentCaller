/* ----------------Programme ESP8266------------------ */
/* Ce programme a pour but de récuperer les données    */
/* lues par l'ATMEGA, et de les envoyer au serveur     */
/* via l'utilisation du réseau et de sockets.          */
/* --------------------------------------------------- */


#include <ESP8266WiFi.h>
#include <WiFiClient.h>

const char* ssid = "tulaurapa"; //SSID de point d'accès
const char* pass = "kiutek1234"; //mot de passe du point d'accès
const char* host = "192.168.43.97"; //Addresse IP de la machine serveur
const int   port = 3333; // Port utilisé par le serveur


void setup() {
  delay(500);
  Serial.begin(38400); //Démarage de la laison série (avec l'ATMEGA)

  WiFi.mode(WIFI_STA); //Définition du wifi en mode Station
  delay(1000);
  WiFi.begin(ssid, pass); //Connexion au point d'accès
  
  while (WiFi.status() != WL_CONNECTED) { //Attente de la connexion au point d'accès
    delay(1000);
  }

}

void loop() {
  
  if(Serial.available()){ //Attente de données sur la liaison série
    DataToServer(Serial.readString()); //Appel de la fonction DataToServer avec les données lues sur la liaison série
  }
  
}

  //DataToServer();

void DataToServer(String data){ //Fonction d'envoie des données au serveur

  WiFiClient client; //Création de l'objet client
  
  if (!client.connect(host, port)) { // Essaie de connexion au serveur
    //Serial.println(".");
    return;
  }
  

  client.println(data); //On envoie la donnée data, qui contient l'UID et le nom de la salle
  delay(100);

  client.flush(); // On attend que le buffer soit vide
  client.stop(); // On termine la communication

}
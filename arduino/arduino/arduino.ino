/* ---------------Programme ATMEGA328P---------------- */
/* Ce programme a pour but de faire fonctionner les    */
/* différents modules tels que le RFID, l'écran OLED,  */
/* le buzzer et la liaison série vers le module        */
/* ESP8266.                                            */
/* --------------------------------------------------- */

#include <SPI.h> // SPI
#include <MFRC522.h> // RFID
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <SoftwareSerial.h>

#define SS_PIN 10
#define RST_PIN 9
// Largeur en pixel de l'écran OLED
#define SCREEN_WIDTH 128 
// Hauteur en pixel de l'écran OLED
#define SCREEN_HEIGHT 64 

// Pin utilisée pour le Buzzer
#define BUZZER 4 

//Salle associée au dispositif (modifiable)
#define SALLE_ECE "P445" 

//Déclaration de la liaison série logiciel (Rx,Tx)
SoftwareSerial mySerial(2,3);

// Déclaration de l'écran OLED de type SSD1306 utilisant l'I2C (pins SDA et SCL)
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, -1);

// Déclaration 
MFRC522 rfid(SS_PIN, RST_PIN); 





void setup() 
{ 
  // Initialisation de la liaison série
  Serial.begin(9600);

  // Démarrage de l'écran OLED, a l'addresse 0x3C 
  if(!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) { 
    Serial.println(F("SSD1306 allocation failed"));
    for(;;);
  }

  //Démarrage de la liaison série logicielle
  mySerial.begin(38400);


  //On efface tout ce qu'il y a sur l'écran
  display.clearDisplay();

  // Initialisation du bus SPI
  SPI.begin(); 

  // Initialisation du module RFID MFRC522
  rfid.PCD_Init();


  //Définition du message d'attente sur l'OLED
  display.setTextSize(1);
  display.setTextColor(WHITE);
  display.setCursor(0, 10);
  display.println("Module ready");
  display.setCursor(0, 30);
  display.println("Wait for card");
  display.display(); 

  //Message indiquant que l'ATMEGA a bien démarré
  Serial.println("Started !");

}
 
void loop() 
{
  // Appel de la fonction de détection de badge RFID
  rfidDetect();
}


void rfidDetect(){

  // Tableau d'octets contentent l'ID
  byte nuidPICC[4];

    // Initialise la boucle si aucun badge n'est présent 
  if ( !rfid.PICC_IsNewCardPresent())
    return;

  // Vérifier la présence d'un nouveau badge 
  if ( !rfid.PICC_ReadCardSerial())
    return;

  // Enregistrer l'ID du badge lu (4 octets) 
  for (byte i = 0; i < 4; i++) 
  {
    nuidPICC[i] = rfid.uid.uidByte[i]; //Enregistrement de l'octet i dans la case i du tableau
  }

  SendToEsp(nuidPICC); // Appel de la fonction permettant d'envoyer l'UID lu a l'ESP8266
  oledRFIDdetected(nuidPICC); //Appel de la fonction permettant d'afficher qu'un badge a été lu ainsi que son UID

  // Re-Init RFID
  rfid.PICC_HaltA(); // Halt PICC
  rfid.PCD_StopCrypto1(); // Stop encryption on PCD

}

void oledRFIDdetected(byte toDisp[]){ //fonction permettant d'afficher qu'un badge a été lu ainsi que son UID

  display.clearDisplay(); //On efface tout ce qu'il y a sur l'écran
  display.setCursor(0,10); //On défini le placement du curseur, a partir de ou on voudra écrire
  display.println("Badge Detected !"); //On ecrit une phrase au niveau du curseur défini précédement
  display.setCursor(0,30); //On change la position du curseur
  display.println("Badge UID :"); //On ecrit une phrase au niveau du curseur
  display.setCursor(0,50); //On change la position du curseur
  for (byte i = 0; i < 4; i++)  //Affichage des 4 octets de l'UID au format hexadecimal
  {
    display.print(toDisp[i],HEX);

    display.print(" ");
  }
  
  display.display(); //On affiche tout les changements que nous avons effectués auparavant
 
  delay(5000); //On attend 5 secondes


  // On réaffiche le texte d'attente d'un badge

  display.clearDisplay();
  display.setCursor(0, 10);
  display.println("Module ready");
  display.setCursor(0, 30);
  display.println("Wait for card");
  display.display(); 

  
}

void SendToEsp(byte toSend[]){ // fonction permettant d'envoyer l'UID lu a l'ESP8266

  char str[32] = ""; //Instanciation de notre chaine de caractères vide
  array_to_string(toSend,4,str); //Appel de la fonction qui va retourner les octets et les mettre dans notre chaine de caractères
  mySerial.print(str); //Envoie de l'UID sur la liaison série logicielle vers l'ESP8266
  mySerial.print(SALLE_ECE); //Envoie de la salle ou se trouve le dispositif sur la liaison série logicielle vers l'ESP8266

}


void array_to_string(byte array[], unsigned int len, char buffer[]) //Fonction permettant la conversion d'un tableau d'octet en String
{
    for (unsigned int i = 0; i < len; i++)
    {
        byte nib1 = (array[i] >> 4) & 0x0F;
        byte nib2 = (array[i] >> 0) & 0x0F;
        buffer[i*2+0] = nib1  < 0xA ? '0' + nib1  : 'A' + nib1  - 0xA;
        buffer[i*2+1] = nib2  < 0xA ? '0' + nib2  : 'A' + nib2  - 0xA;
    }
    buffer[len*2] = '\0';
}
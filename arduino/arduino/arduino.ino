

#include <SPI.h> // SPI
#include <MFRC522.h> // RFID
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <SoftwareSerial.h>

#define SS_PIN 10
#define RST_PIN 9
#define SCREEN_WIDTH 128 // OLED display width, in pixels
#define SCREEN_HEIGHT 64 // OLED display height, in pixels

#define BUZZER 4   

SoftwareSerial mySerial(2,3);

// Declaration for an SSD1306 display connected to I2C (SDA, SCL pins)
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, -1);

// Déclaration 
MFRC522 rfid(SS_PIN, RST_PIN); 





void setup() 
{ 
  // Init RS232
  Serial.begin(9600);

   if(!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) { // Address 0x3D for 128x64
    Serial.println(F("SSD1306 allocation failed"));
    for(;;);
  }
  mySerial.begin(38400);
  display.clearDisplay();

  // Init SPI bus
  SPI.begin(); 

  // Init MFRC522 
  rfid.PCD_Init();

  display.setTextSize(1);
  display.setTextColor(WHITE);
  display.setCursor(0, 10);
  // Display static text
  display.println("Module ready");
  display.setCursor(0, 30);
  display.println("Wait for card");
  display.display(); 

  Serial.println("INIT");

}
 
void loop() 
{
  rfidDetect();
}




void rfidDetect(){

  // Tableau contentent l'ID
  byte nuidPICC[4];

    // Initialisé la boucle si aucun badge n'est présent 
  if ( !rfid.PICC_IsNewCardPresent())
    return;

  // Vérifier la présence d'un nouveau badge 
  if ( !rfid.PICC_ReadCardSerial())
    return;

  tone(BUZZER,440,100);
  String myString;
  // Enregistrer l'ID du badge (4 octets) 
  for (byte i = 0; i < 4; i++) 
  {
    nuidPICC[i] = rfid.uid.uidByte[i];
  }
  
  
  /*
  // Affichage de l'ID 
  Serial.println("Un badge est passé");
  Serial.println(" L'UID du tag est:");
  for (byte i = 0; i < 4; i++) 
  {
    Serial.print(nuidPICC[i], HEX);
    oledRFIDdetected(nuidPICC[i],i);

    Serial.print(" ");
  }
  Serial.println();*/

  
  SendToEsp(nuidPICC);
  oledRFIDdetected(nuidPICC);

  // Re-Init RFID
  rfid.PICC_HaltA(); // Halt PICC
  rfid.PCD_StopCrypto1(); // Stop encryption on PCD

}

void oledRFIDdetected(byte toDisp[]){

  display.clearDisplay();
  display.setCursor(0,10);
  display.println("Badge Detected !");
  display.setCursor(0,30);
  display.println("Badge UID :");
  display.setCursor(0,50);
  for (byte i = 0; i < 4; i++) 
  {
    display.print(toDisp[i],HEX);

    display.print(" ");
  }
  display.display();
  delay(5000);

  display.clearDisplay();
  display.setCursor(0, 10);
  // Display static text
  display.println("Module ready");
  display.setCursor(0, 30);
  display.println("Wait for card");
  display.display(); 

  
}

void SendToEsp(byte toSend[]){


  char str[32] = "";
  array_to_string(toSend,4,str);
  mySerial.print(str);

  

}


void array_to_string(byte array[], unsigned int len, char buffer[])
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
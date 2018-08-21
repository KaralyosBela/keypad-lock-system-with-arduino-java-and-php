#include <Keypad.h>
#include <LiquidCrystal_I2C.h>
#include <Wire.h>
#include <Ethernet.h>
#include <SPI.h>
#include <Servo.h>
#include <NewPing.h>
#include <TimerFreeTone.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
byte ip[] = {192, 168, 0, 103};
byte szerver[] = {192, 168, 0, 105};

const byte sor = 3;
const byte oszlop = 3;
const int PIROS = A0;
const int KEK = A1;
const int BUZZER = A3;

int db = 0;
int db2 = 0;
int db3 = 0;
int poz = 5;
int i = 0;
int j = 0;
int futasokszama = 0;

String uzenet = "";

boolean on = true;
boolean belepes = false;
boolean letezik = false;

char eredmeny[50] = "";
char leutes;


LiquidCrystal_I2C lcd(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE);
EthernetClient client;
Servo szervo;
NewPing szenzor(5, 5, 200);

char billkiosztas[sor][oszlop] =
{
  {'1', '2', '3'},
  {'4', '5', '6'},
  {'7', '8', '9'}
};
byte sorpinek[sor] = {8, 7, 6};
byte oszloppinek[oszlop] = {4, 3, 2};
String kod = "";

Keypad myKeypad = Keypad(makeKeymap(billkiosztas), sorpinek, oszloppinek, sor, oszlop);


int kozepre(int h)
{
  int hossz = 0;
  hossz = (16 - h) / 2;
  return hossz;
}

void setup()
{
  Serial.begin(9600);
  Ethernet.begin(mac, ip);
  SPI.begin();
  szervo.attach(A2);
  szervo.write(90);
  lcd.begin(16, 2);
  lcd.setCursor(5, 0);
  pinMode(PIROS, OUTPUT);
  pinMode(KEK, OUTPUT);
  pinMode(BUZZER, OUTPUT);
  lcd.setCursor(1, 0);
  lcd.print("Kerem a kodot!");
  lcd.setCursor(4, 1);
  lcd.print("[");
  lcd.setCursor(11, 1);
  lcd.print("]");
  //client.connect(szerver, 80);
}

void loop()
{


  /*  int x = szenzor.ping_cm();
    Serial.println(x);

      if (x > 20)
      {
      szervo.write(180);
      delay(2000);
      szervo.write(90);
      }

  */

  leutes = myKeypad.getKey();
  if (leutes)
  {
    Serial.print(leutes);
    kod.concat(leutes);
    digitalWrite(KEK, HIGH);
    TimerFreeTone(BUZZER, 2500, 100);
    delay(100);
    lcd.setCursor(poz, 1);
    poz++;
    lcd.print(leutes);
    digitalWrite(KEK, LOW);
    db++;
    if (db == 6)
    {
      belepes = true;;
      on = true;
    }
  }

  if (belepes == true) {
    Serial.println();
    delay(2000);
    if (client.connect(szerver, 80)) {
      Serial.println("lekerdezes indul");
      client.print("GET /minden/select.php?kod=");
      client.print(kod);
      client.println(" HTTP/1.0");
      client.println();
      while (on) {
        if (client.available())
        {
          char c = client.read();
          Serial.print(c);
          if (c == '\n')
          {
            i++;
            if (i == 8)
            {
              while (client.available()) {
                c = client.read();
                Serial.print(c);
                eredmeny[db3] = c;
                db3++;
              }
              eredmeny[db3 - 1] = '\0';
            }
          }
        }
        if (!client.connected()) {
          client.stop();
          on = false;
        }
      }


      if (db3 != 0)
      {
        letezik = true;
      }
      String eredmeny2(eredmeny);
      Serial.println(kod);
      Serial.println(eredmeny);
      Serial.println(db3);

      if (letezik)
      {
        lcd.clear();
        szervo.write(180);
        uzenet = "Udvozollek,";
        lcd.setCursor(kozepre(uzenet.length()), 0);
        lcd.print(uzenet);
        lcd.setCursor(0, 1);
        lcd.print(eredmeny2);
        digitalWrite(KEK, HIGH);
        digitalWrite(PIROS, HIGH);
        TimerFreeTone(BUZZER, 2500, 1000);
        delay(2000);
        digitalWrite(KEK, LOW);
        digitalWrite(PIROS, LOW);
        szervo.write(90);
        if (client.connect(szerver, 80)) {
          Serial.println("beilesztes indul");
          client.print("GET /minden/insert.php?kod=");
          client.print(futasokszama);
          client.print("&nev=");
          client.print(eredmeny2);
          client.println(" HTTP/1.0");
          client.println();
          Serial.print("GET /minden/insert.php?kod=");
          Serial.print(futasokszama);
          Serial.print("&nev=");
          Serial.print(eredmeny2);
          client.stop();
        }

      }
      else
      {
        uzenet = "Hibas belepokod!";
        lcd.setCursor(kozepre(uzenet.length()), 0);
        lcd.print(uzenet);
        uzenet = "Belepes megtagadva!";
        lcd.setCursor(0, 1);
        lcd.print(uzenet);
        for (j = 0; j < 2; j++) {
          digitalWrite(PIROS, HIGH);
          TimerFreeTone(BUZZER, 2500, 200);
          szervo.write(180);
          delay(100);
          digitalWrite(PIROS, LOW);
          delay(100);
        }
        delay(2000);

      }

    }
    db = 0;
    db2 = 0;
    db3 = 0;
    kod = "";
    poz = 5;
    lcd.clear();
    belepes = false;
    on = true;
    i = 0;
    letezik = false;
    futasokszama++;

    lcd.setCursor(1, 0);
    lcd.print("Kerem a kodot!");
    lcd.setCursor(4, 1);
    lcd.print("[");
    lcd.setCursor(11, 1);
    lcd.print("]");
  }
}

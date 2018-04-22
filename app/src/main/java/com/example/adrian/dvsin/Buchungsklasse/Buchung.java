package com.example.adrian.dvsin.Buchungsklasse;

// Pakete für Parcelable 

import android.os.Parcel;
import android.os.Parcelable;


// Klassendeklaration

public class Buchung implements Parcelable {

    // Instanzvariable

	
	public int buchungsID;
	public String schifftyp;  // Schiff als Klasse sein !!!
	public int containerZahlKlein; 
	public int containerZahlGross;

	// über Containerart wird zwischen kleinen und großen Containern unterschieden, 1 = kleiner Container; 2 = großer Conatiner

	public int containerart;



    // Konstruktor

  public Buchung (int buchungsID, String schifftyp, int containerZahlKlein, int containerZahlGross, int containerart) {

  	this.buchungsID = buchungsID;
  	this.schifftyp = schifftyp;
  	this.containerZahlKlein = containerZahlKlein;
  	this.containerZahlGross = containerZahlGross;
  	this.containerart = containerart;
	  	
  }

	// METHODEN
	
    // Get-Methoden
	
	
	// BuchungsID

	public int getBuchungsID() {
			return buchungsID;
		}


		// Schifftyp
		
		public String getSchifftyp() {
			return schifftyp;
		}
	
	
		// kleiner Container, Anzahl 
		
		public int getContainerZahlKlein() {
			return containerZahlKlein;
		}
	
		
		// großer Container, Anzahl 
		
		public int getContainerZahlGross() {
			return containerZahlGross;
		}


		// Containerart, Wert, Wertzuordnung siehe oben

		public int getContainerart() {
		return containerart;
	}


    // Set-Methoden
	
	
		// BuchungsID
	
	    public void setBuchungsID(int buchungsID){
			this.buchungsID = buchungsID;
		}
	
		// Schifftyp
	
	    public void setSchifftyp(String schifftyp){
			this.schifftyp = schifftyp;
		}

		
		// Containeranzahl kleiner Container
	
		public void setContainerZahlKlein(int containerZahlKlein) {
			this.containerZahlKlein = containerZahlKlein;
		}
		
		// Containeranzahl großer Container
		
		public void setContainerZahlGross(int containerZahlGross){
				this.containerZahlGross = containerZahlGross;
			}

		// Containeranzahl großer Container

		public void setContainerart(int containerart){
				this.containerart = containerart;
			}

	// Parcelable-Teil
	
	 @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
		
        dest.writeInt(this.buchungsID);
        dest.writeString(this.schifftyp);
        dest.writeInt(this.containerZahlKlein);
		dest.writeInt(this.containerZahlGross);
		dest.writeInt(this.containerart);
    }

    protected Buchung(Parcel in) {
		
        this.buchungsID = in.readInt();
        this.schifftyp = in.readString();
        this.containerZahlKlein = in.readInt();
		this.containerZahlGross = in.readInt();
		this.containerart = in.readInt();
    }

    public static final Creator<Buchung> CREATOR = new Creator<Buchung>() {
		
        @Override
        public Buchung createFromParcel(Parcel source) {
            return new Buchung(source);
        }

        @Override
        public Buchung[] newArray(int size) {
            return new Buchung[size];
        }
    };
}




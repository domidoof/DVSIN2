package com.example.adrian.dvsin.Schiffsklassen;

// Pakete für Parcelable

import android.os.Parcel;
import android.os.Parcelable;

// Klassendeklaration

public class Schifftyp implements Parcelable {

    // Instanzvariablen

    int schiffID;

    String typbezeichnung;
    String typbeschreibung;

    int stapelhoehe;
    int stellplaetze;


    // Konstruktor

    public Schifftyp(int schiffID, String typbezeichnung, String typbeschreibung, int stapelhoehe, int stellplaetze) {

        this.schiffID = schiffID;
        this.typbezeichnung = typbezeichnung;
        this.typbeschreibung = typbeschreibung;
        this.stapelhoehe = stapelhoehe;
        this.stellplaetze = stellplaetze;

    }

    // METHODEN

    // Get-Methoden

    // SchiffID

    public int getSchiffID() {
        return schiffID;
    }

    // Typbezeichnung

    public String getTypbezeichnung() {
        return typbezeichnung;
    }

    // Typbeschreibung

    public String getTypbeschreibung() {
        return typbeschreibung;
    }

    // Stapelhoehe

    public int getStapelhoehe() {
        return stapelhoehe;
    }

    // Stellplaetze

    public int getStellplaetze() {
        return stellplaetze;
    }


    // Set-Methoden

    // SchiffID

    public void setSchiffID(int schiffID){
        this.schiffID = schiffID;
    }

    // Typbezeichnung

    public void setTypbezeichnung(String typbezeichnung) {
        this.typbezeichnung = typbezeichnung;
    }

    // Typbeschreibung

    public void setTypbeschreibung(String typbeschreibung) {
        this.typbeschreibung = typbeschreibung;
    }

    // Stapelhoehe

    public void settStapelhoehe(int stapelhoehe) {
        this.stapelhoehe = stapelhoehe;
    }

    // Stellplaetze

    public void setStellplaetze(int stellplaetze) {
        this.stellplaetze = stellplaetze;
    }

    // Parcelable-Teil

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.schiffID);
        dest.writeString(this.typbezeichnung);
        dest.writeString(this.typbeschreibung);
        dest.writeInt(this.stapelhoehe);
        dest.writeInt(this.stellplaetze);
    }

    protected Schifftyp(Parcel in) {

        this.schiffID = in.readInt();
        this.typbezeichnung = in.readString();
        this.typbeschreibung = in.readString();
        this.stapelhoehe = in.readInt();
        this.stellplaetze = in.readInt();
    }

    public static final Creator<Schifftyp> CREATOR = new Creator<Schifftyp>() {

        @Override
        public Schifftyp createFromParcel(Parcel source) {
            return new Schifftyp(source);
        }

        @Override
        public Schifftyp[] newArray(int size) {
            return new Schifftyp[size];
        }
    };
}


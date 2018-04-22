package com.example.adrian.dvsin.Schiffsklassen;

// Klassendeklaration

public class Ebene {

    // Instanzvariablen

    public int ebenenanzahl;
    public int aktuelleEbene;

    // Konstruktor

    public Ebene(int ebenenanzahl) {
            this.ebenenanzahl = ebenenanzahl;
            aktuelleEbene = 1;
    }

    // METHODEN

    // Get-Methoden

    public int getEbenenanzahl(){
        return ebenenanzahl;
    }

    public int getAktuelleEbene(){
        return aktuelleEbene;
    }

    // Set-Methoden

    public void setAktuelleEbene(int aktuelleEbene){
        this.aktuelleEbene = aktuelleEbene;
    }

}


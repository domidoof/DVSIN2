package com.example.adrian.dvsin.Schiffsklassen;

// Pakete für Parcelable


// Klassendeklaration

public class SchiffTyp extends Ebene {

    // Instanzvariablen

    int stapelhoeheMax;

    public SchiffTyp(int ebenenanzahl, int stapelhoeheMax) {

        super(ebenenanzahl);
        this.stapelhoeheMax = stapelhoeheMax;

    }
}

package com.example.adrian.dvsin.Schiffsklassen;

// Klassendeklaration

import java.util.ArrayList;

public class Ebene {

    // Instanzvariablen

    public int ebenenanzahl;
    public int aktuelleEbene;

    public ArrayList<String> ebeneK1 = new ArrayList<>();
    public ArrayList<String> ebeneK2 = new ArrayList<>();
    public ArrayList<String> ebeneK3 = new ArrayList<>();

    public ArrayList<String> ebeneG1 = new ArrayList<>();
    public ArrayList<String> ebeneG2 = new ArrayList<>();
    public ArrayList<String> ebeneG3 = new ArrayList<>();

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

    public ArrayList<String> getEbeneK1() {
        return ebeneK1;
    }

    public ArrayList<String> getEbeneK2() {
        return ebeneK2;
    }

    public ArrayList<String> getEbeneK3() {
        return ebeneK3;
    }

    public ArrayList<String> getEbeneG1() {
        return ebeneG1;
    }

    public ArrayList<String> getEbeneG2() {
        return ebeneG2;
    }

    public ArrayList<String> getEbeneG3() {
        return ebeneG3;
    }

    // Set-Methoden

    public void setAktuelleEbene(int aktuelleEbene){
        this.aktuelleEbene = aktuelleEbene;
    }

    public void setEbeneG1(final String eG1) {
        this.ebeneG1.add(eG1);
    }

    public void setEbeneG2(final String eG2) {
        this.ebeneG2.add(eG2);
    }

    public void setEbeneG3(final String eG3) {
        this.ebeneG3.add(eG3);
    }

    public void setEbeneK1(final String eK1) {
        this.ebeneK1.add(eK1);
    }

    public void setEbeneK2(final String eK2) {
        this.ebeneK2.add(eK2);
    }

    public void setEbeneK3(final String eK3) {
        this.ebeneK3.add(eK3);
    }
}


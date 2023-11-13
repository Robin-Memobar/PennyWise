package com.example.pennywise;

class Ausgabe {
    int betrag;
    String kategorie;

    Ausgabe(int betrag, String kategorie) {
        this.betrag = betrag;
        this.kategorie = kategorie;
    }

    @Override
    public String toString() {
        return kategorie + ": " + betrag;
    }
}

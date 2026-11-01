package com.example.app3;

import java.util.ArrayList;
import java.util.List;

/** Datenhaltung gemäß Aufgabenblatt 11, einschließlich der vorgegebenen Datensätze. */
class Album {
    static List<Album> albums = new ArrayList<>();
    static void addAlbumsRow(int index, String title, String artist, boolean classical, String composer) {
        albums.set(index, new Album(title, artist, classical, composer));
    }
    static { for (int i=0; i<4; i++) albums.add(null); albumDataSet(); }
    static String[] gibAlleNamen() {
        String[] alleTitel = new String[albums.size()]; int i=0;
        for (Album album : albums) alleTitel[i++] = album.getTitle(); return alleTitel;
    }
    static void albumDataSet() {
        addAlbumsRow(0, "HQ", "Roy Harper", false, "");
        addAlbumsRow(1, "The Rough Dancer and Cyclical Night", "Astor Piazzola", false, "");
        addAlbumsRow(2, "The Black Light", "Calexico", false, "");
        addAlbumsRow(3, "Symphony No.5", "CBSO", true, "Sibelius");
    }
    static Album gib(int albumNummer) { return albums.get(albumNummer); }
    static int size() { return albums.size(); }
    private String title, artist;
    private String composer = null;
    private boolean classical = false;
    Album(String title, String artist, boolean classical, String composer) {
        this.title=title; this.artist=artist; this.classical=classical; this.composer=composer;
    }
    void setTitle(String value) { title=value; }
    String getTitle() { return title; }
    void setArtist(String value) { artist=value; }
    String getArtist() { return artist; }
    void setComposer(String value) { composer=value; }
    String getComposer() { return composer; }
    Boolean isClassical() { return classical; }
    void setClassical(boolean isClassical) { classical=isClassical; }
}

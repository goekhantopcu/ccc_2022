package de.algebros;

import java.util.NoSuchElementException;

public class Binaerbaum<T> {
    protected BaumEl wurzel;

    public class BaumEl { // innere Klasse für einzelene Baumelemente
        T data;
        BaumEl links; // linker Teilbaum
        BaumEl rechts; // rechter Teilbaum

        public BaumEl(T data) {
            this.data = data;
        }
    }

    public Binaerbaum() { // Konstruktur ohne Argumente
        this(null, null, null);
    }

    public Binaerbaum(T e, BaumEl l, BaumEl r) {
        if (e == null && (l != null || r != null)) {
            throw new NoSuchElementException("dumm");
        }
        wurzel = new BaumEl(e);
        this.wurzel.links = l;
        this.wurzel.rechts = r;
    }

    /**
     * 
     * @return Wahrheitswert, ob Baum leer ist
     */
    public boolean isEmpty() { // falls Baum leer ist
        return (wurzel == null);
    }

    public int hoehe() { // gibt die Höhe des Baum zurück
        if (wurzel == null) {
            return 0;
        }
        // Falls die wurzel nicth null ist:
        return hoeheHilf(wurzel);
    }

    private int hoeheHilf(BaumEl element) { // rekursive Hilfsmethode

        if (element.links == null && element.rechts == null) { // abbruchbedingung falls wir am letzten angekommen sind
            return 1;
            // Ende
        }
        if (element.links == null && element.rechts != null) { // nur links sind noch ELemente

            return hoeheHilf(element.rechts) + 1; // rekursiver Aufruf mit erhöhter Höhe
        }
        if (wurzel.links != null && wurzel.rechts == null) { // nur rechts sind noch Elemente
            return hoeheHilf(element.links) + 1;
        }
        // es gibt links UND rechts noch welche
        int hoeheDesHoechstenTeilbaums = 0;
        int links = hoeheHilf(element.links);
        int rechts = hoeheHilf(element.rechts);
        hoeheDesHoechstenTeilbaums = Math.max(links, rechts); // speichert die max von den längsten Pfanden des rechten
                                                              // und linken Teilbaums
        return 1 + hoeheDesHoechstenTeilbaums; // + Wurzel
    }

    public int size() { // gibt Anzahl der Werte im Baum zurück
        if (wurzel == null) { // wenn Baum leer
            return 0;
        }
        return sizeHilf(this.wurzel); // aufruf der rekursiven Hilfsmethode
    }

    private int sizeHilf(BaumEl element) {
        if (element.links == null && element.rechts == null) { // beide null
            return 1;
        }
        if (element.links == null && element.rechts != null) { // rechts geht es weiter
            return sizeHilf(element.rechts) + 1; // +1, da wir das erste element rechts gefunden haben
        }
        if (element.links != null && element.rechts == null) { // links geht es weiter
            return sizeHilf(element.links) + 1;
        }
        // rechts und links geht es weiter
        int links = sizeHilf(element.links); // muss hier bei beiden +1 gerechnet werden?
        int rechts = sizeHilf(element.rechts);
        return links + rechts + 1; // + der obere "mamaknoten"
    }

    public String toString() { // gibt en gegeben Baum als String zurück in gewünschter Form
        if (this.wurzel == null) { // wenn Baum leer ist
            return " ";
        }
        return toString(this.wurzel); // aufruf der rekusiven Methode

    }

    public String toString(BaumEl element) {
        if (element.links == null && element.rechts == null) { // es geht weder links noch rechts weiter
            return "<" + element.data.toString() + ">";
        }
        if (element.links != null && element.rechts == null) { // nur links geht es weiter
            return ("<" + toString(element.links) + "> <" + element.data.toString() + ">");
        }
        if (element.links == null && element.rechts != null) { // nur rechts geht es weiter
            return ("<" + element.data.toString() + "> <" + toString(element.rechts) + ">");
        }
        // es kommt sowohl links als auch rechts noch etwas
        String links = toString(element.links);
        String rechts = toString(element.rechts);
        return "<" + links + "> <" + element.data.toString() + "> <" + rechts + ">";
    }

    public String preorder() {
        return preorder(this.wurzel);

    }

    public String preorder(BaumEl element) {
        if (element.links == null && element.rechts == null) {
            return element.data.toString();
        }
        if (element.links == null && element.rechts != null) {
            return ("<" + element.data.toString() + "> <" + preorder(element.rechts) + ">");
        }
        if (element.links != null && element.rechts == null) {
            return ("<" + element.data.toString() + "> <" + preorder(element.links) + ">");
        }
        // else
        String links = preorder(element.links);
        String rechts = preorder(element.rechts);
        return "<" + element.data.toString() + "> <" + links + "> <" + rechts + ">";

    }

}

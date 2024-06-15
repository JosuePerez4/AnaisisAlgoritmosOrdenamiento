package Arbol_binario;

public class Nodo {
    private int dato;
    private Nodo derecha, izquierda;

    public Nodo(int dato) {
        this.dato = dato;
    }

    public Nodo(int dato, Nodo derecha, Nodo izquierda) {
        this.dato = dato;
        this.derecha = derecha;
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return "Nodo: dato=" + dato + ", derecha=" + getDerecha() + ", izquierda=" + getIzquierda();
    }
}

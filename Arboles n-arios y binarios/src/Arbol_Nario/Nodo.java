package Arbol_Nario;

import java.util.ArrayList;

public class Nodo {
    private int dato;
    private ArrayList<Nodo> hijos;

    public Nodo(int dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public void agregarHijo (Nodo hijo) {
        hijos.add(hijo);
    }

    @Override
    public String toString() {
        return "Nodo [dato=" + getDato() + ", hijos=" + getHijos() + "]";
    }
}

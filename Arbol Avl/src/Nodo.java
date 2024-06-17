public class Nodo {
    private int clave;
    private int altura;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(int clave) {
        this.clave = clave;
        this.altura = 1;
        this.izquierda = null;
        this.derecha = null;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}

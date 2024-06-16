package Arbol_Nario;

public class ArbolNArio {
    private Nodo raiz;

    public ArbolNArio() {
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    // Métodos
    public void agregar(int dato) {
        if (raiz == null) {
            raiz = new Nodo(dato);
        } else {

        }
    }

    public boolean vacio() {
        return raiz == null;
    }

    public int altura(Nodo nodo) {
        if (raiz == null) {
            return 0;
        } else {
            return altura(nodo.getHijos().getFirst()) + 1;
        }
    }

    public boolean esHoja(Nodo nodo) {
        return nodo.getHijos() == null;
    }

    public int cantidadNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            while (nodo.getHijos() != null) {
                int i = 0;
                return 1 + cantidadNodos(nodo.getHijos().get(i));
            }
        }
    }
}

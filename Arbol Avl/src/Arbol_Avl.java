public class Arbol_Avl {
    private Nodo raiz;

    public Arbol_Avl() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void insertar(int clave) {

    }

    private Nodo insertar(int clave, Nodo nodo) {
        if (nodo == null) {
            return new Nodo(clave);
        }

        if (clave < nodo.getClave()) {
            return insertar(clave, nodo.getIzquierda());
        } else if (clave > nodo.getClave()) {
            return insertar(clave, nodo.getDerecha());
        } else {
            return nodo;
        }
    }

    public int altura(Nodo nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    public int balance(Nodo nodo) {
        return nodo == null ? 0 : balance(nodo.getIzquierda()) - balance(nodo.getDerecha());
    }

}

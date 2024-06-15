package Arbol_binario;

import org.w3c.dom.Node;

public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo crearArbol(int dato) {
        if (raiz == null) {
            return raiz = new Nodo(dato, null, null);
        }
        return null;
    }

    public Nodo agregar(int dato) {
        if (raiz == null) {
            return raiz = new Nodo(dato, null, null);
        } else {
            return agregarNodoRecursivo(raiz, dato);
        }
    }

    private Nodo agregarNodoRecursivo(Nodo nodoActual, int dato) {
        if (dato < nodoActual.getDato()) {
            return agregarNodoRecursivo(nodoActual.getIzquierda(), dato);
        } else {
            return agregarNodoRecursivo(nodoActual.getDerecha(), dato);
        }
    }

    public Nodo buscar(int dato) {
        if (raiz == null) {
            return null;
        }
        return buscarNodoRecursivo(raiz, dato);
    }

    private Nodo buscarNodoRecursivo(Nodo nodoActual, int dato) {
        if (nodoActual == null) {
            return null;
        }

        if (nodoActual.getDato() == dato) {
            return nodoActual;
        }

        if (dato < nodoActual.getDato()) {
            return buscarNodoRecursivo(nodoActual.getIzquierda(), dato);
        } else {
            return buscarNodoRecursivo(nodoActual.getDerecha(), dato);
        }
    }

    public Nodo eliminar(int dato) {
        return eliminarNodoRecursivo(raiz, dato);
    }

    private Nodo eliminarNodoRecursivo(Nodo nodoActual, int dato) {
        if (nodoActual == null) {
            return null;
        }

        if (dato < nodoActual.getDato()) {
            nodoActual.setIzquierda(eliminarNodoRecursivo(nodoActual.getIzquierda(), dato));
        } else if (dato > nodoActual.getDato()) {
            nodoActual.setDerecha(eliminarNodoRecursivo(nodoActual.getDerecha(), dato));
        } else {
            // Nodo encontrado
            // Caso 1: Nodo sin hijos (hoja)
            if (nodoActual.getIzquierda() == null && nodoActual.getDerecha() == null) {
                return null;
            }

            // Caso 2: Nodo con un solo hijo
            if (nodoActual.getIzquierda() == null) {
                return nodoActual.getDerecha();
            }

            if (nodoActual.getDerecha() == null) {
                return nodoActual.getIzquierda();
            }

            // Caso 3: Nodo con dos hijos
            // Buscar el sucesor en el inorden (mínimo en el subárbol derecho)
            int minValor = sucesorEnOrden(nodoActual.getDerecha()).getDato();
            nodoActual.setDato(minValor);

            // Eliminar el sucesor en el inorden
            nodoActual.setDerecha(eliminarNodoRecursivo(nodoActual.getDerecha(), minValor));
        }

        return nodoActual;
    }

    public boolean vacio () {
        return raiz == null;
    }

    public String imprimirPreOrden (Nodo n) {
        if (n == null) {
            return "";
        }
        return n.getDato() + " " + imprimirPreOrden(n.getIzquierda()) + imprimirPreOrden(n.getDerecha());
    }

    public String imprimirOrden (Nodo n) {
        if (n == null) {
            return "";
        }
        return imprimirPreOrden(n.getIzquierda()) + " " + n.getDato() + imprimirPreOrden(n.getDerecha());
    }

    public String imprimirPostOrden (Nodo n) {
        if (n == null) {
            return "";
        }
        return imprimirPreOrden(n.getIzquierda()) + " " + imprimirPreOrden(n.getDerecha()) + n.getDato();
    }

    private Nodo sucesorEnOrden(Nodo nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
    }
}

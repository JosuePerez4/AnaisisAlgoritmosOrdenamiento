package Arbol_binario;

import org.w3c.dom.Node;

public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
    }

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
            if (nodoActual.getIzquierda() == null) {
                nodoActual.setIzquierda(new Nodo(dato, null, null));
                return nodoActual.getIzquierda();
            } else {
                return agregarNodoRecursivo(nodoActual.getIzquierda(), dato);
            }
        } else {
            if (nodoActual.getDerecha() == null) {
                nodoActual.setDerecha(new Nodo(dato, null, null));
                return nodoActual.getDerecha();
            } else {
                return agregarNodoRecursivo(nodoActual.getDerecha(), dato);
            }
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

    public boolean vacio() {
        return raiz == null;
    }

    public boolean esHoja(Nodo nodo) {
        Nodo n = buscar(nodo.getDato());
        return n.getIzquierda() == null && n.getDerecha() == null;
    }

    // Altura o níveles del árbol
    public int altura(Nodo nodo) {
        if (raiz == null) {
            return -1;
        } else {
            int alturaDerecha = altura(nodo.getDerecha());
            int alturaIzquierda = altura(nodo.getIzquierda());
            return Math.max(alturaDerecha, alturaIzquierda) + 1;
        }
    }

    public int minimo(Nodo raiz) {
        while (raiz.getIzquierda() != null) {
            raiz = raiz.getIzquierda();
        }
        return raiz.getDato();
    }

    public int maximo(Nodo raiz) {
        while (raiz.getDerecha() != null) {
            raiz = raiz.getDerecha();
        }
        return raiz.getDato();

    }

    private int cantidadHojas(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            if (esHoja(nodo)) {
                return 1;
            } else {
                return cantidadHojas(nodo.getIzquierda()) + cantidadHojas(nodo.getDerecha());
            }
        }
    }

    public int cantidadNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            return 1 + cantidadNodos(nodo.getIzquierda()) + cantidadNodos(nodo.getDerecha());
        }
    }

    private Nodo sucesorEnOrden(Nodo nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
    }

    public String imprimirPreOrden(Nodo n) {
        if (n == null) {
            return "";
        }
        String resultado = n.getDato() + " ";
        resultado += imprimirPreOrden(n.getIzquierda());
        resultado += imprimirPreOrden(n.getDerecha());
        return resultado;
    }

    public String imprimirOrden(Nodo n) {
        if (n == null) {
            return "";
        }
        String resultado = imprimirOrden(n.getIzquierda());
        resultado += n.getDato() + " ";
        resultado += imprimirOrden(n.getDerecha());
        return resultado;
    }

    public String imprimirPostOrden(Nodo n) {
        if (n == null) {
            return "";
        }
        String resultado = imprimirPostOrden(n.getIzquierda());
        resultado += imprimirPostOrden(n.getDerecha());
        resultado += n.getDato() + " ";
        return resultado;
    }

}

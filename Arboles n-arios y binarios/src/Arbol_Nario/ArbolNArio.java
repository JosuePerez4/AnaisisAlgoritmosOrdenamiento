package Arbol_Nario;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
            Queue <Nodo> colita = new LinkedList<Nodo>();
        }
    }

    public Nodo buscar(int buscado) {
        return buscarRecursivo(raiz, buscado);
    }

    private Nodo buscarRecursivo(Nodo nodo, int buscado) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getDato() == buscado) {
            return nodo;
        }

        for (Nodo n : nodo.getHijos()) {
            Nodo encontrado = buscarRecursivo(n, buscado);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }

    public int grado(int dato) {
        Nodo n = buscar(dato);
        return n == null ? 0 : n.getHijos().size();
    }

    public List<Nodo> obtenerHermanos(Nodo nodo) {
        if (nodo == null || nodo == raiz) {
            return new ArrayList<>(); // La raíz no tiene hermanos
        }

        List<Nodo> hermanos = new ArrayList<>();
        Nodo padre = obtenerPadre(raiz, nodo);

        if (padre != null) {
            for (Nodo hijo : padre.getHijos()) {
                if (hijo != nodo) { // Añadir todos los hijos del padre excepto el nodo actual
                    hermanos.add(hijo);
                }
            }
        }

        return hermanos;
    }

    private Nodo obtenerPadre(Nodo actual, Nodo nodoBuscado) {
        if (actual == null) {
            return null;
        }

        for (Nodo hijo : actual.getHijos()) {
            if (hijo == nodoBuscado) {
                return actual; // El nodo actual es el padre del nodo buscado
            }

            Nodo padre = obtenerPadre(hijo, nodoBuscado);
            if (padre != null) {
                return padre;
            }
        }

        return null;
    }

    public boolean eliminarSubarbol(int dato) {
        if (raiz == null) {
            return false;
        }

        // Buscar el nodo a eliminar
        Nodo n = buscar(dato);
        if (n == null) {
            return false;
        }

        // Si el nodo a eliminar es la raíz
        if (n == raiz) {
            raiz = null; // Eliminar todo el árbol
            return true;
        }

        // Obtener el nodo padre del nodo a eliminar
        Nodo padre = obtenerPadre(raiz, n);
        if (padre != null) {
            padre.getHijos().remove(n); // Eliminar el subárbol
            return true;
        }

        return false;
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

    public int cantidadHojas(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            int cantidaHojas = 0;
            if (esHoja(nodo)) {
                return cantidaHojas;
            } else {
                for (Nodo n : nodo.getHijos()) {
                    cantidaHojas += cantidadHojas(n);
                }
            }
            return cantidaHojas;
        }
    }

    public int cantidadNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int cantidaNodos = 1; // Cuenta el nodo actual

        for (Nodo n : nodo.getHijos()) {
            cantidaNodos += cantidadNodos(n);
        }

        return cantidaNodos;
    }
}

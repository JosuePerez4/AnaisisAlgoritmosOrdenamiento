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
        raiz = insertar(clave, raiz);
    }

    private Nodo insertar(int clave, Nodo nodo) {
        if (nodo == null) {
            return new Nodo(clave);
        }

        if (clave < nodo.getClave()) {
            nodo.setIzquierda(insertar(clave, nodo.getIzquierda()));
        } else if (clave > nodo.getClave()) {
            nodo.setDerecha(insertar(clave, nodo.getDerecha()));
        } else {
            return nodo;
        }

        // Revisa el balanceo
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));

        int balance = balance(nodo);

        // Casos de rotaciones
        // Caso LL
        if (balance > 1 && clave < nodo.getIzquierda().getClave()) {
            return rotacionDerecha(nodo);
        }

        // Caso RR
        if (balance < -1 && clave > nodo.getDerecha().getClave()) {
            return rotacionIzquierda(nodo);
        }

        // Caso LR
        if (balance > 1 && clave > nodo.getIzquierda().getClave()) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }

        // Caso RL
        if (balance < -1 && clave < nodo.getDerecha().getClave()) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public Nodo eliminar(int clave) {
        return eliminar(this.raiz, clave);
    }

    private Nodo eliminar(Nodo raiz, int clave) {
        if (raiz == null)
            return raiz;

        if (clave < raiz.getClave())
            raiz.setIzquierda(eliminar(raiz.getIzquierda(), clave));
        else if (clave > raiz.getClave())
            raiz.setDerecha(eliminar(raiz.getDerecha(), clave));
        else {
            if ((raiz.getIzquierda() == null) || (raiz.getDerecha() == null)) {
                Nodo temp = null;
                if (temp == raiz.getIzquierda())
                    temp = raiz.getDerecha();
                else
                    temp = raiz.getIzquierda();

                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else {
                    raiz = temp;
                }
            } else {
                Nodo temp = obtenerMinimo(raiz.getDerecha());
                raiz.setClave(temp.getClave());
                raiz.setDerecha(eliminar(raiz.getDerecha(), temp.getClave()));
            }
        }

        if (raiz == null)
            return raiz;

        raiz.setAltura(Math.max(altura(raiz.getIzquierda()), altura(raiz.getDerecha())) + 1);

        int balance = balance(raiz);

        if (balance > 1 && balance(raiz.getIzquierda()) >= 0)
            return rotacionDerecha(raiz);

        if (balance > 1 && balance(raiz.getIzquierda()) < 0) {
            raiz.setIzquierda(rotacionIzquierda(raiz.getIzquierda()));
            return rotacionDerecha(raiz);
        }

        if (balance < -1 && balance(raiz.getDerecha()) <= 0)
            return rotacionIzquierda(raiz);

        if (balance < -1 && balance(raiz.getDerecha()) > 0) {
            raiz.setDerecha(rotacionDerecha(raiz.getDerecha()));
            return rotacionIzquierda(raiz);
        }

        return raiz;
    }

    private Nodo obtenerMinimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.getIzquierda() != null)
            actual = actual.getIzquierda();
        return actual;
    }

    private Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.getIzquierda();
        Nodo aux = x.getDerecha();

        // Realizo la rotación
        x.setDerecha(y);
        y.setIzquierda(aux);

        // Actualizar las alturas
        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);
        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);

        // Retornamos la nueva raíz
        return x;
    }

    private Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.getDerecha();
        Nodo aux = y.getIzquierda();

        // Realizo la rotación
        y.setIzquierda(x);
        x.setDerecha(aux);

        // Actualizar las alturas
        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);
        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);

        // Retornamos la nueva raíz
        return y;
    }

    public Nodo rotacionDerechaIzquierda(Nodo x) {
        x.setDerecha(rotacionDerecha(x.getDerecha()));
        return rotacionIzquierda(x);
    }

    public Nodo rotacionIzquierdaDerecha(Nodo x) {
        x.setIzquierda(rotacionIzquierda(x.getIzquierda()));
        return rotacionDerecha(x);
    }

    public int altura(Nodo nodo) {
        return nodo == null ? -1 : nodo.getAltura();
    }

    public int balance(Nodo nodo) {
        return nodo == null ? 0 : balance(nodo.getIzquierda()) - balance(nodo.getDerecha());
    }

    // Métodos de impresión
    public void imprimirArbol() {
        imprimirArbol(raiz, "", true);
    }

    private void imprimirArbol(Nodo nodo, String prefix, boolean isTail) {
        if (nodo != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + nodo.getClave());
            imprimirArbol(nodo.getDerecha(), prefix + (isTail ? "    " : "│   "), false);
            imprimirArbol(nodo.getIzquierda(), prefix + (isTail ? "    " : "│   "), true);
        }
    }
}

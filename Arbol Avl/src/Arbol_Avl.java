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

    public int altura(Nodo nodo) { // *! Complejidad: O(1)
        return (nodo == null) ? -1 : nodo.getAltura();
    }

    public int balance(Nodo nodo) { // *! Complejidad: O(1)
        return (nodo == null) ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

    public void insertar(int clave) {
        raiz = insertar(clave, raiz);
    }

    private Nodo insertar(int clave, Nodo nodo) { // *! Complejidad: O(Log n)
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

        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));

        int balance = balance(nodo);

        if (balance > 1 && clave < nodo.getIzquierda().getClave()) {
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && clave > nodo.getDerecha().getClave()) {
            return rotacionIzquierda(nodo);
        }

        if (balance > 1 && clave > nodo.getIzquierda().getClave()) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && clave < nodo.getDerecha().getClave()) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void eliminar(int clave) {
        raiz = eliminar(this.raiz, clave);
    }

    private Nodo eliminar(Nodo nodo, int clave) { // *! Complejidad: O(Log n)
        if (nodo == null) {
            return nodo;
        }

        if (clave < nodo.getClave()) {
            nodo.setIzquierda(eliminar(nodo.getIzquierda(), clave));
        } else if (clave > nodo.getClave()) {
            nodo.setDerecha(eliminar(nodo.getDerecha(), clave));
        } else {
            if ((nodo.getIzquierda() == null) || (nodo.getDerecha() == null)) {
                Nodo temp = (nodo.getIzquierda() != null) ? nodo.getIzquierda() : nodo.getDerecha();

                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                Nodo temp = encontrarMinimo(nodo.getDerecha());
                nodo.setClave(temp.getClave());
                nodo.setDerecha(eliminar(nodo.getDerecha(), temp.getClave()));
            }
        }

        if (nodo == null) {
            return nodo;
        }

        nodo.setAltura(Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())) + 1);

        int balance = balance(nodo);

        if (balance > 1 && balance(nodo.getIzquierda()) >= 0) {
            return rotacionDerecha(nodo);
        }

        if (balance > 1 && balance(nodo.getIzquierda()) < 0) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && balance(nodo.getDerecha()) <= 0) {
            return rotacionIzquierda(nodo);
        }

        if (balance < -1 && balance(nodo.getDerecha()) > 0) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public Nodo buscar(int clave) {
        return buscar(raiz, clave);
    }

    private Nodo buscar(Nodo nodo, int clave) { // *! Complejidad: O(Log n)
        if (nodo == null || nodo.getClave() == clave) {
            return nodo;
        }
        if (clave < nodo.getClave()) {
            return buscar(nodo.getIzquierda(), clave);
        } else {
            return buscar(nodo.getDerecha(), clave);
        }
    }

    private Nodo rotacionDerecha(Nodo y) { // *! Complejidad: O(1)
        Nodo x = y.getIzquierda();
        Nodo T2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(T2);

        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);
        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);

        return x;
    }

    private Nodo rotacionIzquierda(Nodo x) { // *! Complejidad: O(1)
        Nodo y = x.getDerecha();
        Nodo T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);
        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);

        return y;
    }

    public Nodo rotacionDerechaIzquierda(Nodo x) { // *! Complejidad: O(1)
        x.setDerecha(rotacionDerecha(x.getDerecha()));
        return rotacionIzquierda(x);
    }

    public Nodo rotacionIzquierdaDerecha(Nodo x) { // *! Complejidad: O(1)
        x.setIzquierda(rotacionIzquierda(x.getIzquierda()));
        return rotacionDerecha(x);
    }

    public Nodo obtenerMinimo() {
        return encontrarMinimo(raiz);
    }

    private Nodo encontrarMinimo(Nodo nodo) { // *! Complejidad: O(Log n)
        if (nodo == null) {
            return null;
        }
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
    }

    public Nodo encontrarMaximo() {
        return encontrarMaximo(raiz);
    }

    private Nodo encontrarMaximo(Nodo nodo) { // *! Complejidad: O(Log n)
        if (nodo == null) {
            return null;
        }
        while (nodo.getDerecha() != null) {
            nodo = nodo.getDerecha();
        }
        return nodo;
    }

    public void inorden() {
        inorden(raiz);
        System.out.println();
    }

    private void inorden(Nodo nodo) { // *! Complejidad: O(n)
        if (nodo != null) {
            inorden(nodo.getIzquierda());
            System.out.print(nodo.getClave() + " ");
            inorden(nodo.getDerecha());
        }
    }

    public void preorden() {
        preorden(raiz);
        System.out.println();
    }

    private void preorden(Nodo nodo) { // *! Complejidad: O(n)
        if (nodo != null) {
            System.out.print(nodo.getClave() + " ");
            preorden(nodo.getIzquierda());
            preorden(nodo.getDerecha());
        }
    }

    public void postorden() {
        postorden(raiz);
        System.out.println();
    }

    private void postorden(Nodo nodo) { // *! Complejidad: O(n)
        if (nodo != null) {
            postorden(nodo.getIzquierda());
            postorden(nodo.getDerecha());
            System.out.print(nodo.getClave() + " ");
        }
    }
}

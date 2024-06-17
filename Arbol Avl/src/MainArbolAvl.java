public class MainArbolAvl {
    public static void main(String[] args) throws Exception {
        Arbol_Avl avl = new Arbol_Avl();
        avl.insertar(10);
        avl.insertar(15);
        avl.insertar(20);
        avl.insertar(2);
        avl.insertar(6);
        avl.insertar(80);
        avl.insertar(99);
        avl.insertar(75);
        avl.insertar(17);
        avl.insertar(3);
        avl.insertar(1);

        avl.imprimirArbol();
    }
}

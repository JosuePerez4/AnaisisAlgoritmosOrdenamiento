package Arbol_binario;

public class MainArbolBinario {
    public static void main(String[] args) throws Exception {
        ArbolBinario arbol = new ArbolBinario();
        arbol.crearArbol(0);
        int [] nodos = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // rellenar el árbol
        for (int i=0; i<nodos.length;i++) {
            arbol.agregar(nodos[i]);
        }
        System.out.println("Cantidad de nodos: " + arbol.cantidadNodos(arbol.getRaiz()));
        System.out.println("Recorrido en PreOrden");
        System.out.println(arbol.imprimirPreOrden(arbol.getRaiz()));
        System.out.println("Recorrido en orden");
        System.out.println(arbol.imprimirOrden(arbol.getRaiz()));
        System.out.println("Recorrido en PostOrden");
        System.out.println(arbol.imprimirPostOrden(arbol.getRaiz()));
    }
}

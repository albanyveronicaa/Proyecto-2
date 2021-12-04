package Clases;

/**
 * Clase: Se utilizará para guardar los archivos cargados por el usuario
 *
 * @author Albany Avila
 */
public class Archivero {

    //Atributos de la clase
    NodoArchivero primero;
    NodoArchivero ultimo;
    public int tamano;

    //Constructor de la clase
    public Archivero() {
        primero = null;
        ultimo = null;
        tamano = 0;
    }

    /**
     * Funcion: Se utilizará para comprobar si la lista está vacía o no
     *
     * @author Albany Avila
     */
    public boolean estaVacia() {
        if (primero == null) {
            return true;
        }
        return false;
    }

    /**
     * Función: Se utilizará para recorrer la lista y buscar un nodo con el
     * mismo titulo al que está definido en el parámetro
     *
     * @author Albany Avila
     */
    public NodoArchivero buscar(String titulo) {
        NodoArchivero temp = this.primero;
        while (temp != null) {
            if (temp.titulo.equals(titulo)) {
                return temp;
            } else {
                temp = temp.siguiente;
            }
        }
        return null;
    }

    /**
     * Función: Se utilizará para recorrer la lista y almacenar los titulos de
     * los textos
     *
     * @author Albany Avila
     */
    public String titulos() {
        NodoArchivero temp = this.primero;

        String lista = "";

        while (temp != null) {
            if (temp.siguiente != null) {
                lista += temp.titulo + ",";
                temp = temp.siguiente;
            } else {
                lista += temp.titulo;
                temp = temp.siguiente;
            }
        }
        return lista;
    }

    /**
     * Método: Se utilizará para insertar un nodo al final de la lista
     *
     * @author Albany Avila
     */
    public void insertarAlFinal(String texto, String titulo) {

        NodoArchivero nuevoNodo = new NodoArchivero();
        nuevoNodo.titulo = titulo;
        nuevoNodo.texto = texto;
        if (primero == null) {
            primero = nuevoNodo;
            primero.siguiente = null;
            ultimo = primero;
        } else {
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
            ultimo.siguiente = null;
        }
        tamano++;

    }

}

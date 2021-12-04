package Clases;

/**
 * Clase: Se utilizará para guardar las 5 palabras más usadas. Se implementará
 * una lista simplemente enlazada
 *
 * @author Albany Avila
 */
public class Top5 {

    //Atributos de la clase
    private NodoHash primero;
    private int maximoPalabras;
    private int tamano;
    private NodoHash ultimo;

    /**
     * Constructor: No tiene parámetros
     *
     * @author Albany Avila
     */
    public Top5() {
        this.primero = null;
        this.maximoPalabras = 5;
        this.tamano = 0;
        this.ultimo = null;
    }

    /**
     * Función: Se utilizará para verificar si la lista está vacía
     *
     * @author Albany Avila
     */
    public boolean estaVacio() {
        return getPrimero() == null;
    }

    /**
     * Método: Se utilizará para añadir un nodo a la lista vacía
     *
     * @author Albany Avila
     */
    public void agregarVacio(NodoHash nuevoNodo) {
        if (estaVacio()) {
            setPrimero(nuevoNodo);
            setUltimo(nuevoNodo);
            setTamano(getTamano() + 1);
        }
    }

    /**
     * Función: Se utilizará para buscar nodos en una posición especifica
     *
     * @author Albany Avila
     */
    public NodoHash getNodo(int posicion) {
        NodoHash temp = this.getPrimero();
        for (int i = 0; i < this.getTamano(); i++) {
            if (i == posicion) {
                return temp;
            } else {
                temp = temp.getSiguienteLista();
            }
        }
        return null;
    }

    /**
     * Función: Se utilizará para verificar si una palabra ya está en la lista
     *
     * @author Albany Avila
     */
    public boolean verificar(String palabra) {
        boolean flag = false;
        NodoHash temp = this.getPrimero();
        for (int i = 0; i < this.getTamano(); i++) {
            if (temp.getPalabra().equals(palabra)) {
                flag = true;
                break;
            } else {
                temp = temp.getSiguienteLista();
            }
        }
        return flag;
    }

    /**
     * Método: Se utilizará para ir cambiando de posiciones las palabras con más
     * repeticiones y colocarla de primero
     *
     * @author Albany Avila
     */
    public void posiciones(NodoHash aux, int posicion) {
        if (posicion == this.tamano - 1) {
            NodoHash x = this.getNodo(posicion - 1);
            this.ultimo.setSiguienteLista(this.primero);
            this.setPrimero(this.ultimo);
            this.setUltimo(x);
        } else if (posicion > 0 && posicion < this.tamano - 1) {
            NodoHash x = this.getNodo(posicion - 1);
            NodoHash temp = new NodoHash();
            temp.setSiguienteLista(aux.getSiguienteLista());
            aux.setSiguienteLista(this.primero);
            this.setPrimero(aux);
            x.setSiguienteLista(temp.getSiguienteLista());
            temp = null;
        }
    }

    /**
     * Método: Se utilizará para agregar un nuevo nodo
     *
     * @author Albany Avila
     */
    public void agregarNodo(NodoHash nuevoNodo, int posicion) {
        if (estaVacio()) {
            agregarVacio(nuevoNodo);
        } else {
            if (posicion == 0) {
                agregarPrimero(nuevoNodo);
            } else if (posicion == getTamano()) {
                agregarUltimo(nuevoNodo);
            } else {
                NodoHash aux = this.getNodo(posicion - 1);
                nuevoNodo.setSiguienteLista(aux.getSiguienteLista());
                aux.setSiguienteLista(nuevoNodo);
                setTamano(getTamano() + 1);
            }
        }
    }

    /**
     * Método: Se utilizará para agregar un nodo de manera ordenada a la lista.
     *
     * @author Albany Avila
     */
    public void agregarOrdenado(NodoHash nuevoNodo) {
        NodoHash temp = primero;
        boolean flag = false;
        if (estaVacio()) {
            agregarPrimero(nuevoNodo);
        } else {
            if (this.verificar(nuevoNodo.getPalabra())) {
                for (int i = 0; i < this.tamano; i++) {
                    if (temp.getPalabra().equals(nuevoNodo.getPalabra())) {
                        this.posiciones(temp, i);
                        break;
                    } else {
                        temp = temp.getSiguienteLista();
                    }
                }
            } else {
                for (int i = 0; i < tamano; i++) {
                    if (temp.getContarPalabras() < nuevoNodo.getContarPalabras()) {
                        agregarNodo(nuevoNodo, i);
                        if (this.tamano >= this.maximoPalabras) {
                            this.eliminarUltimo();
                        }
                        flag = true;
                        break;
                    } else {
                        temp = temp.getSiguienteLista();
                    }
                }
                if (!flag && this.tamano < this.maximoPalabras) {
                    agregarUltimo(nuevoNodo);
                }
            }
        }
    }

    /**
     * Método: Se utilizará para agregar un nuevo nodo de primero a la lista
     *
     * @author Albany Avila
     */
    public void agregarPrimero(NodoHash nuevoNodo) {
        if (estaVacio()) {
            this.agregarVacio(nuevoNodo);
        } else {
            nuevoNodo.setSiguienteLista(getPrimero());
            setPrimero(nuevoNodo);
            setTamano(getTamano() + 1);
        }
    }

    /**
     * Método: Se utilizará para agregar un nuevo nodo de último a la lista
     *
     * @author Albany Avila
     */
    public void agregarUltimo(NodoHash nuevoNodo) {
        if (estaVacio()) {
            this.agregarVacio(nuevoNodo);
        } else {
            getUltimo().setSiguienteLista(nuevoNodo);
            setUltimo(nuevoNodo);
            setTamano(getTamano() + 1);
        }
    }

    /**
     * Método: Se utilizará para eliminar el último nodo de la lista
     *
     * @author Albany Avila
     */
    public void eliminarUltimo() {
        if (estaVacio()) {
        } else {
            setUltimo(getNodo(getTamano() - 2));
            getUltimo().setSiguienteLista(null);
            setTamano(getTamano() - 1);
        }
    }

    /**
     * Función: Se utilizará para verificar si la lista está ordenada
     *
     * @author Albany Avila
     */
    public boolean verificarOrden() {
        boolean flag = true;
        NodoHash temp = this.getPrimero();
        for (int i = 0; i < this.getTamano() - 1; i++) {
            if (temp.getContarPalabras() < temp.getSiguienteLista().getContarPalabras()) {
                flag = false;
                break;
            } else {
                temp = temp.getSiguienteLista();
            }
        }

        return flag;
    }

    /**
     * Método: Se utilizará para ordenar que ordena los nodos de la lista de
     * mayor a menor
     *
     * @author Albany Avila
     */
    public void sortMaxMin() {
        if (tamano > 1) {
            boolean cambiar;
            do {
                cambiar = false;
                NodoHash actual = primero;
                NodoHash siguiente = primero.getSiguienteLista();
                NodoHash ultimo = null;

                for (int i = 0; i < this.getTamano() - 1; i++) {
                    if (actual.getContarPalabras() < siguiente.getContarPalabras()) {
                        cambiar = true;
                        if (ultimo != null) {
                            NodoHash aux = siguiente.getSiguienteLista();
                            ultimo.setSiguienteLista(siguiente);
                            siguiente.setSiguienteLista(actual);
                            actual.setSiguienteLista(aux);
                        } else {
                            NodoHash aux = siguiente.getSiguienteLista();
                            primero = siguiente;
                            siguiente.setSiguienteLista(actual);
                            actual.setSiguienteLista(aux);
                        }
                        ultimo = siguiente;
                        siguiente = actual.getSiguienteLista();
                    } else {
                        ultimo = actual;
                        actual = siguiente;
                        siguiente = siguiente.getSiguienteLista();
                    }
                }
            } while (cambiar);
        }
    }

    /**
     * Función: Se utilizará para mostrar todos las palabras y las repeticiones
     * de estas
     *
     * @author Albany Avila
     */
    public String mostrar() {
        String top5 = "";
        NodoHash temp = this.primero;
        for (int i = 0; i < this.getTamano(); i++) {
            top5 += "Palabra: " + temp.getPalabra()
                    + ". Contar Palabras: " + temp.getContarPalabras() + "\n";
            temp = temp.getSiguienteLista();
        }

        return top5;
    }

    //Getters y Setters
    public NodoHash getPrimero() {
        return primero;
    }

    public void setPrimero(NodoHash primero) {
        this.primero = primero;
    }

    public int getMaximoPalabras() {
        return maximoPalabras;
    }

    public void setMaximoPalabras(int maximoPalabras) {
        this.maximoPalabras = maximoPalabras;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public NodoHash getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoHash ultimo) {
        this.ultimo = ultimo;
    }

}

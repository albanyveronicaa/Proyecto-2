package Clases;

/**
 * Clase: Se utilizará para implementar la Tabla Hash y la lista de las 5
 * palabras más usadas
 *
 * @author Albany Avila
 */
public class NodoHash {

    //Atributos
    private NodoHash siguiente;
    private int contarPalabras;
    private String palabra;
    private NodoHash siguienteLista;

    /**
     * Constructor: Tiene como parámetro la palabra que se va a almacenar
     *
     * @author Albany Avila
     */
    public NodoHash(String palabra) {
        this.siguiente = null;
        this.contarPalabras = 1;
        this.palabra = palabra;
        this.siguienteLista = null;
    }

    /**
     * Constructor: Tiene como parámetro la palabra que se va a almacenar y la
     * cantidad de veces que esta se repite
     *
     * @author Albany Avila
     */
    public NodoHash(String palabra, int contarPalabras) {
        this.siguiente = null;
        this.contarPalabras = contarPalabras;
        this.palabra = palabra;
        this.siguienteLista = null;
    }

    /**
     * Constructor: No tiene parámetros
     *
     * @author Albany Avila
     */
    public NodoHash() {
        this.siguiente = null;
        this.contarPalabras = 0;
        this.palabra = "";
        this.siguienteLista = null;
    }

    //Getters y Setters
    public NodoHash getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHash siguiente) {
        this.siguiente = siguiente;
    }
    
    public int getContarPalabras() {
        return contarPalabras;
    }

    public void setContarPalabras(int contarPalabras) {
        this.contarPalabras = contarPalabras;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public NodoHash getSiguienteLista() {
        return siguienteLista;
    }

    public void setSiguienteLista(NodoHash siguienteList) {
        this.siguienteLista = siguienteList;
    }

}

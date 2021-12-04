package Clases;

import java.util.ArrayList;

/**
 *
 * @author Albany Avila
 */
public class Palabra {

    //Atributos de la clase
    private String palabra;
    private int frecuencia;
    private ArrayList<Integer> indiceDoc;

    /**
     * Constructor: Se pasa por parámetro la palabra que se almacenará en la
     * Tabla Hash
     *
     * @author Albany Avila
     */
    public Palabra(String palabra) {
        this.palabra = palabra;
        this.frecuencia = 0;
        this.indiceDoc = new ArrayList();
    }

    /**
     * Método: Se utilizará para que cada vez que se termine de analizar un
     * documento, se vuelvan a colocar los valores originales de la frecuencia
     *
     * @author Albany Avila
     */
    public static void reiniciarFrecuencia(Palabra[] palabras) {

        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i] != null) {
                palabras[i].setFrecuencia(0); //Se settea la frecuencia de cada palabra
            }
        }
    }


    @Override
    public String toString() {

        return palabra + ": " + frecuencia;

    }

    //Getters y setters
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

}

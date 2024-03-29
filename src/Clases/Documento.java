package Clases;

import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Albany Avila
 */
public class Documento {

    //Atributos de la clase
    private String titulo;
    private ArrayList<String> autores;
    private String resumen;

    //Constructor de la clase
    public Documento() {
    }

    public Documento(String titulo, ArrayList autores, String resumen) {
        this.titulo = titulo;
        this.autores = autores;
        this.resumen = resumen;
    }

    /**
     * Método: Se utiliza para separarar el cuerpo del resumen en palabra por
     * palabra
     *
     * @author Albany Avila
     */
    public String[] separarCuerpo() {

        //Se eliminan los caracteres especiales indeseados
        resumen = resumen.replaceAll("[\\,“”;:/.¿?¡!]", " ");
        resumen = resumen.replaceAll("\\[]", " ");
        resumen = resumen.replaceAll("\\{}", " ");
        resumen = resumen.replaceAll("[\\()]", " ");
        resumen = resumen.replaceAll("[\"]", " ");
        resumen = resumen.replaceAll("[^\\w]", " "); //Se eliminan todos los caracteres especiales, sustituyendolos por espacios
        resumen = resumen.toLowerCase(); //Se transforma todo a minusculas para tener consistencia
        String[] cuerpo = resumen.split("\\s+"); //Se separan las palabras por los espacios

        return cuerpo;

    }

    /**
     * Método: Se utiliza para mostrar el documento en un textArea
     *
     * @author Albany Avila
     */
    public void mostrarContenido(JTextArea textArea) {

        String mostrar = this.titulo + "\nAutores: ";

        for (int i = 0; i < autores.size(); i++) {
            mostrar = mostrar + "\n" + autores.get(i);
        }

        mostrar += "\nResumen: \n";
        String[] cuerpo = this.resumen.split(" ");

        for (int i = 1; i <= cuerpo.length; i++) {
            if (i % 11 == 0) {
                mostrar += cuerpo[i - 1] + "\n"; //Se escribe la ultima palabra de la linea y se pasa a la siguiente
            } else {
                mostrar += cuerpo[i - 1] + " "; //Se va escribiendo cada palabra
            }
        }
        textArea.setText(mostrar);
    }

    @Override
    public String toString() {

        return titulo;

    }

    //Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<String> autores) {
        this.autores = autores;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

}

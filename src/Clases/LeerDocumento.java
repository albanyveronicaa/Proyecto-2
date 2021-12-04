package Clases;

import Clases.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Clase: Se utilizará para leer los archivos txt cargados por el usuario
 *
 * @author Albany Avila
 */
public class LeerDocumento {

    /**
     * Constructor: Tiene como parámetros txt, HashTable, Archivero y Top5.
     *
     * @author Albany Avila
     */
    public Tabla openFile(String txt, HashTable hashTable, Archivero archivero, Top5 top5) {
        String texto = "";
        String line;
        File file = new File(txt);
        try {
            if (file.exists()) {
                FileReader files = new FileReader(file);
                BufferedReader read = new BufferedReader(files);

                while ((line = read.readLine()) != null) {
                    if (!line.isEmpty()) {
                        texto += line + " ";
                    }
                }

                String titulo = JOptionPane.showInputDialog("Ingrese el titulo: ");

                archivero.insertarAlFinal(texto.substring(0, texto.length() - 1), titulo);

                if (texto != null) {
                    texto = texto.replaceAll("[\\[\\](){\\.,*?!}]", "");

                    texto = texto.toLowerCase();

                    String[] wordList = texto.split(" ");

                    for (int i = 0; i < wordList.length; i++) {
                        NodoHash nodo = hashTable.hashFunction(wordList[i]);
                        top5.agregarOrdenado(nodo);
                    }
                }

            }
            top5.sortMaxMin();
            Tabla table = new Tabla(hashTable, top5, archivero);
            return table;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se encontró el archivo o no tiene el formato correspondiente. Se iniciará la aplicación sin información previa.",
                    "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
}

package Clases;

import javax.swing.JOptionPane;

/**
 * @author Albany Avila
 */
public class HashTable {

    private int tamanoTabla;
    private NodoHash tabla[];
    private Palabra[] palabrasClaves = new Palabra[400];
    private Documento[] documentos = new Documento[50];

    //Constructor de la clase
    public HashTable() {
    }

    /**
     * Constructor: Tiene como parámetro el tamano de la Tabla Hash
     *
     * @author Albany Avila
     */
    public HashTable(int tamano) {
        this.tamanoTabla = tamano;
        this.tabla = new NodoHash[this.tamanoTabla];
        for (int i = 0; i < tamanoTabla; i++) {
            this.tabla[i] = null;
        }
    }

    /**
     * Método: Se utiliza para vaciar la Tabla Hash
     *
     * @author Albany Avila
     */
    public void vaciarTabla() {
        for (int i = 0; i < this.tamanoTabla; i++) {
            this.tabla[i] = null;
        }
    }

    /**
     * Método: Se utilizará en caso de que se necesite una Tabla Hash más grande
     *
     * @author Albany Avila
     */
    public void expandSizePalabra() {

        Palabra[] aux = new Palabra[this.palabrasClaves.length * 2];

        for (int i = 0; i < this.palabrasClaves.length; i++) {
            if (this.palabrasClaves[i] != null) {
                aux[i] = this.palabrasClaves[i];
            }
        }
        setPalabrasClaves(aux);
    }

    /**
     * Método: Se utilizará en caso de que se necesite una Tabla Hash más grande
     *
     * @author Albany Avila
     */
    public void expandSizeDocumento() {

        Documento[] aux = new Documento[this.documentos.length * 2];

        for (int i = 0; i < this.documentos.length; i++) {
            if (this.documentos[i] != null) {
                aux[i] = this.documentos[i];
            }
        }
        setDocumentos(aux);
    }

    /**
     * Método: Se utilizará para insertar un documento
     *
     * @author Albany Avila
     */
    public void insertarDocumento(int indice, Documento doc) {

        //Se valida si el indice es valido
        if (this.esValidoDoc(indice)) {

            if (ProyectoHash.hash.getDocumentos()[indice] != null) {

                //Se verifica si ese indice ya se encuentra ocupado
                if (ProyectoHash.hash.getDocumentos()[indice].getTitulo().equalsIgnoreCase(doc.getTitulo())) {

                    //Se verifica si ya existe el documento
                    JOptionPane.showMessageDialog(null, "ERROR: Ya existe el documento.", "ERROR", JOptionPane.WARNING_MESSAGE);

                } else {
                    //Se hace el manejo de colisiones
                    do {
                        indice++;
                    } while (ProyectoHash.hash.getDocumentos()[indice] != null && (indice < ProyectoHash.hash.getDocumentos().length));
                    insertarDocumento(indice, doc);
                }
            } else {

                //Si no, se agrega el documento a la hashtable
                ProyectoHash.hash.getDocumentos()[indice] = doc;
                JOptionPane.showMessageDialog(null, "Se ha agregado el documento de manera exitosa.", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //Si no, se expande el tamano de la hashtable y se intenta insertar otra vez
            expandSizeDocumento();
            insertarDocumento(indice, doc);
        }
    }

    /**
     * Función: Se utilizará para convertir una palabra en un valor entero y
     * retornar la posición en la que se ubicará en la tabla Hash
     *
     * @author Albany Avila
     */
    public int hashing(String palabra) {
        int valor = 0;
        int posicion = 1;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.codePointAt(i) == 32) {
                valor += 0;
            } else if (palabra.codePointAt(i) >= 48 && palabra.codePointAt(i) <= 57) {
                valor += ((palabra.codePointAt(i) - 47) * posicion);
            } else if (palabra.codePointAt(i) >= 65 && palabra.codePointAt(i) <= 90) {
                valor += ((palabra.codePointAt(i) - 54) * posicion);
            } else if (palabra.codePointAt(i) >= 97 && palabra.codePointAt(i) <= 122) {
                valor += ((palabra.codePointAt(i) - 60) * posicion);
            }
            posicion++;
        }

        return (valor % this.tamanoTabla);
    }

    /**
     * Función: Se utilizará para retornar todas las palabras y la cantidad que
     * se repiten estas
     *
     * @author Albany Avila
     */
    public String mostrar() {
        String tabla = "";
        for (int i = 0; i < this.tamanoTabla; i++) {
            NodoHash aux = this.tabla[i];
            if (aux != null) {
                while (aux != null) {
                    tabla += "Palabra: " + aux.getPalabra()
                            + ". Contar palabra: " + aux.getContarPalabras() + "\n";
                    aux = aux.getSiguiente();
                }
            }
        }
        return tabla;
    }

    /**
     * Método: Se utilizará para insertar una palabra
     *
     * @author Albany Avila
     */
    public void insertarPalabra(int indice, Palabra pal) {

        //Se valida si el indice es valido
        if (this.esValidoPalabra(indice)) {

            if (ProyectoHash.hash.getPalabrasClaves()[indice] != null) {  //Se verifica si ese indice ya se encuentra ocupado

                if (ProyectoHash.hash.getPalabrasClaves()[indice].getPalabra().equalsIgnoreCase(pal.getPalabra())) {

                    //Se verifica si ya existe la palabra o no 
                    JOptionPane.showMessageDialog(null, "ERROR: Ya existe la palabra clave.", "ERROR", JOptionPane.WARNING_MESSAGE);

                } else {

                    //Se hace el manejo de colisiones en caso de que no es palabra repetida
                    do {
                        indice++;
                    } while (ProyectoHash.hash.getPalabrasClaves()[indice] != null && (indice < ProyectoHash.hash.getPalabrasClaves().length));
                    insertarPalabra(indice, pal);
                }
            } else {
                //Si no, se agrega la palabra
                ProyectoHash.hash.getPalabrasClaves()[indice] = pal;
                JOptionPane.showMessageDialog(null, "Se ha agregado la palabra de manera exitosa.", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //Si no, se expande el tamano de la hashtable y se intenta insertar otra vez
            expandSizePalabra();
            insertarPalabra(indice, pal);
        }
    }

    //Funciones hash
    /**
     * Función: Se utilizará para convertir la palabra a un entero y agregarlo
     * a la Tabla Hash.
     *
     * @author Albany Avila
     */
    public NodoHash hashFunction(String palabra) {
        int posicion = this.hashing(palabra);
        boolean existe = false;
        
        if (this.tabla[posicion] != null) {
            NodoHash aux = this.tabla[posicion];
            boolean flag = false;
            while (!flag) {
                if (aux.getPalabra().equals(palabra)) {
                    flag = true;
                    existe = true;
                } else {
                    if (aux.getSiguiente() != null) {
                        aux = aux.getSiguiente();
                    } else {
                        flag = true;
                    }
                }
            }
            if (!existe) {
                NodoHash nuevaPalabra = new NodoHash(palabra);
                aux.setSiguiente(nuevaPalabra);
            } else {
                aux.setContarPalabras(aux.getContarPalabras() + 1);
            }
            return aux;
        } else {
            NodoHash nuevaPalabra = new NodoHash(palabra);
            this.tabla[posicion] = nuevaPalabra;
            return nuevaPalabra;
        }
    }

    /**
     * Método: Se utilizará para asignar a cada palabra su indice
     *
     * @author Albany Avila
     */
    public static int hashPalabra(String palabra) {

        palabra = palabra.toLowerCase(); //Se transforma todo a minusculas para tener consistencia
        int hash = 7; //Este sera el "codigo" que diferenciara la palabra

        for (int j = 1; j <= palabra.length(); j++) {
            hash = hash + (palabra.charAt(j - 1) * j);
        }
        hash = hash % 379;
        return hash;

    }

    /**
     * Función: Se utilizará para buscar en la Tabla Hash el nodo (la palabra),
     * si no la encuentra, retorna null.
     *
     * @author Albany Avila
     */
    public NodoHash buscarPalabra(String palabra) {

        boolean existe = false;
        int posicion = this.hashing(palabra);
        NodoHash temp = this.tabla[posicion];

        if (temp != null) {
            boolean aux = false;
            while (!aux) {
                if (temp.getPalabra().equals(palabra)) {
                    aux = true;
                    existe = true;
                } else {
                    if (temp.getSiguiente() != null) {
                        temp = temp.getSiguiente();
                    } else {
                        aux = true;
                        temp = null;
                    }
                }
            }
        }
        return temp;
    }

    /**
     * Método: Se utilizará para asignar a cada titulo su indice
     *
     * @author Albany Avila
     */
    public static int hashDocumento(String titulo) {

        titulo = titulo.toLowerCase(); //Se transforma todo a minusculas para tener consistencia
        titulo = titulo.replaceAll("\\s+", "");

        int hash = 7; //Este sera el "codigo" que diferenciara la palabra

        for (int j = 1; j <= titulo.length(); j++) {
            hash = hash + (titulo.charAt(j - 1) * j);
        }

        hash = hash % 31;
        return hash;

    }

    /**
     * Método: Se utilizará para verificar si el indice es válido o no
     *
     * @author Albany Avila
     */
    public boolean esValidoDoc(int indice) {
        return (indice >= 0 && indice < this.documentos.length);

    }

    /**
     * Método: Se utilizará para verificar si el indice es válido o no
     *
     * @author Albany Avila
     */
    public boolean esValidoPalabra(int indice) {
        return (indice >= 0 && indice < this.palabrasClaves.length);

    }

    /**
     * Método: Se utilizará para convertir un caracter a código ascii
     *
     * @author Albany Avila
     */
    public int charToAscii(char letter) {
        int asciiValue = letter;
        return asciiValue;
    }

    /**
     * Método: Se utilizará para convertir una palabra a codigo Ascii
     *
     * @param word
     * @return asciiValueWord
     */
    public int wordToAscii(String word) {
        int acum = 0;
        for (int i = 0; i < word.length(); i++) {
            if ((64 < charToAscii(word.charAt(i)) && charToAscii(word.charAt(i)) < 91) || (96 < charToAscii(word.charAt(i)) && charToAscii(word.charAt(i)) < 123)) {
                acum = acum + charToAscii(word.charAt(i));
            }
        }
        return acum;
    }

    /**
     * Método: Se utilizará para asignar el indice de la palabra clave donde se
     * agregará en la Tabla Hash
     *
     * @param word
     * @param vector
     * @return index
     */
    public int newPosition(String word, int tam) {
        int index = wordToAscii(word) % tam;
        return index;
    }

    //Getters y Setters
    public void setPalabrasClaves(Palabra[] palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public void setDocumentos(Documento[] documentos) {
        this.documentos = documentos;
    }

    public Palabra[] getPalabrasClaves() {
        return palabrasClaves;
    }

    public Documento[] getDocumentos() {
        return documentos;
    }

}

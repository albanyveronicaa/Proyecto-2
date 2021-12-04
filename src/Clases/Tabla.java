package Clases;

/**
 * Clase: Se utilizará para retonar la Tabla Hash, el Top 5 de las palabras más
 * usadas y el algoritmo de Rabin-Karp de una vez
 *
 * @author Albany Avila
 */
public class Tabla {

    private HashTable table;
    private Archivero rabinkarp;
    private Top5 top5;
    

    /**
     * Constructor: Tiene como parámetros Hash Table y las listas
     *
     * @author Albany Avila
     */
    public Tabla(HashTable table, Top5 top5, Archivero rabinkarp) {
        this.table = table;
        this.rabinkarp = rabinkarp;
        this.top5 = top5;
        
    }

    //Getters y Setters
    public HashTable getTable() {
        return table;
    }

    public void setTable(HashTable table) {
        this.table = table;
    }

    public Top5 getTop5() {
        return top5;
    }

    public void setTop5(Top5 top5) {
        this.top5 = top5;
    }

    public Archivero getKarpList() {
        return rabinkarp;
    }

    public void setKarpList(Archivero rabinkarp) {
        this.rabinkarp = rabinkarp;
    }

}

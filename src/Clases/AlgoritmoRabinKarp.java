package Clases;

/**
 * Clase: Se utilizará para implementar el algoritmo de Rabin-Karp y verificar
 * si los documentos o textos contienen plagio
 *
 * @author Albany Avila
 */
public class AlgoritmoRabinKarp {

    /**
     * Función: Se utilizará para buscar un segmento dentro del texto
     *
     * @author Albany Avila
     */
    public String algoritmoRabinKarp(String texto, String segmento) {
        int q = 101;
        return buscar(segmento, texto, q);

    }

    /**
     * Función: Se utilizará para recorrer el texto y comprobar si el
     * segmento de texto se encuentra incluido en otro texto
     *
     * @author Albany Avila
     */
    public final static int d = 256;

    static String buscar(String segmento, String texto, int q) {
        int M = segmento.length();
        int N = texto.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;
        String modificado = texto;

        for (i = 0; i < M - 1; i++) {
            h = (h * d) % q;
        }

        for (i = 0; i < M; i++) {
            p = (d * p + segmento.charAt(i)) % q;
            t = (d * t + texto.charAt(i)) % q;
        }

        for (i = 0; i <= N - M; i++) {

            if (p == t) {
                for (j = 0; j < M; j++) {
                    if (texto.charAt(i + j) != segmento.charAt(j)) {
                        break;
                    }
                }

                if (j == M) {
                    modificado = modificado.subSequence(0, i) + "" + modificado.subSequence(i, (i + M)).toString().toUpperCase() + "" + modificado.subSequence((i + M), modificado.length());
                }
            }

            if (i < N - M) {
                t = (d * (t - texto.charAt(i) * h) + texto.charAt(i + M)) % q;

                if (t < 0) {
                    t = (t + q);
                }
            }
        }
        return modificado;
    }
}

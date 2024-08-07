package Domini;
import java.util.*;

public class QAP_cota_GL {

    /**
     * Dada una solucion parcial la completa usando la cota Gilmore-Lawler
     * @param sol   Solucion parcial, -1 en las teclas no usadas
     * @param Dist  Matriz de distancias
     * @param Freq  Matriz de Frequencias
     * @param curr_val Valor actual
     * @return  Solucion parcial completa
     * 
     * @author Alex
     */
    public static float calc(int[] sol, float[][] Dist, float[][] Freq, float curr_val){

        //Contar teclas no usadas
        int m = 0;
        int n = sol.length;
        for(int i = 0; i < sol.length; i++){
            if(sol[i] == -1) m++;
        }
        if(m == 0) return curr_val; //Solucion completa, no es necesario calcular

        //Guardar teclas no usadas
        int[] unused_k = new int[m];
        int i_k = 0;
        //obtener la lestras no usadas
        boolean[] letter_list = new boolean[sol.length];
        Arrays.fill(letter_list,false);
        for(int i = 0; i < sol.length; i++){
            if(sol[i] == -1){
                unused_k[i_k] = i;
                i_k++;
            }else{
                letter_list[sol[i]] = true;
            }
        }
        int[] unused_l = new int[m];
        int i_l = 0;
        for(int i = 0; i < sol.length; ++i){
            if(!letter_list[i]){
                unused_l[i_l] = i;
                i_l++;
            }
        }

        float[][] M = new float[m][m];
        float[][] M2 = new float[m][m];

        for (int i = 0; i < m; ++i) {
            for (int k = 0; k < m; k++) {
                //calc C1
                float C1 = 0.0f;
                for (int j = 0; j < n; ++j) {
                    if(sol[j] != -1) C1 += Dist[j][unused_k[k]] * (Freq[sol[j]][unused_l[i]] + Freq[unused_l[i]][sol[j]]);
                }

                float[] T = new float[m-1];
                float[] D = new float[m-1];
                for(int j = 0; j < m; j++){
                    //No se ha de incluir uno mismo en la matriz
                    if (j != i) T[j>i?j-1:j] = Freq[unused_l[j]][unused_l[i]] + Freq[unused_l[i]][unused_l[j]];
                    if (j != k) D[j>k?j-1:j] = Dist[unused_k[j]][unused_k[k]];
                }
                Arrays.sort(T);
                Arrays.sort(D);

                float C2 = 0.f;
                for(int j = 0; j < m-1; j++) C2 += T[j]*D[m-j-2];

                M[i][k] = C1 + C2;
                M2[i][k] = C1 + C2/2.f;
            }
        }

        int[] asig = min_asig(M);

        float val = 0.f;
        for(int i = 0; i < m; i++){
            val += M2[i][asig[i]];
        }


        return val + curr_val;

    }


    /**
     * Devuelve la minima asignacion fila col de una matriz dada
     * @param M2 matriz que buscar minima asignacion
     * @return matriz de asignacion dende cada elemento represanta la fila y el contenido la columna
     * 
     * @author Alex
     */
    public static int[] min_asig(float M2[][]){
        int n = M2.length;

        float[][] M = new float[n][n];//clonar para no modificar el original
        for(int i = 0; i < n; ++i) M[i] = M2[i].clone();

        //Restar el minimo valor de cada fila
        for(int i = 0; i < n; i++){
            float min = M[i][0];
            for(int k = 1; k < n; k++) if(M[i][k] < min) min = M[i][k];
            for(int k = 0; k < n; k++) M[i][k] -= min;
        }
        //Restar el minimo valor a cada columna
        for(int k = 0; k < n; k++){
            float min = M[0][k];
            for(int i = 1; i < n; i++) if(M[i][k] < min) min = M[i][k];
            for(int i = 0; i < n; i++) M[i][k] -= min;
        }

        boolean comp = false;
        int[] Zassig = new int[n];
        do{
            boolean[] c = new boolean[1];
            Zassig = asignarZeros(M,c);
            comp = c[0];


            if(!comp){
                //Calcular cubrimiento de lineas
                boolean[][] L = cubrirZeros(M,Zassig);

                //Buscar min de no cubierto
                float min = Float.MAX_VALUE;
                for (int i = 0; i < n; ++i) {
                    for (int k = 0; k < n; ++k) {
                        if (!L[0][i] && !L[1][k]) {
                            if (M[i][k] < min) min = M[i][k];
                        }
                    }
                }

                //sumar a las marcadas
                for (int i = 0; i < n; ++i) {
                    for (int k = 0; k < n; ++k) {
                        if (L[0][i]) M[i][k] += min;
                        if (L[1][k]) M[i][k] += min;
                    }
                }
                //buscar min y restarlo
                min = Float.MAX_VALUE;
                for (int i = 0; i < n; ++i) {
                    for (int k = 0; k < n; ++k) {
                        if (M[i][k] < min) min = M[i][k];
                    }
                }
                for (int i = 0; i < n; ++i) {
                    for (int k = 0; k < n; ++k) {
                        M[i][k] -= min;
                    }
                }

            }
        }while(!comp);

        return Zassig;
    }

    /**
     * Dado una matriz de costes y una asignacion de zeros devuelve un cubrimiento de linias que es minimo y cubre todos los zeros
     * @param M Matriz de costes de letras a teclas sin usar
     * @param Z Asignacion maxima de zeros, no hay dos zeros asignados en la misma columna o fila
     * @return  Devuelve que filas return[0][] y columnas return[1][] forman el cubirmiento minimo
     * 
     * @author Alex
     */
    public static  boolean[][] cubrirZeros(float[][] M, int[] Z) {
        //Recubrimiento de lineas
        int m = M.length;
        boolean[] filas = new boolean[m];
        boolean[] colum = new boolean[m];
        Arrays.fill(filas, false);
        Arrays.fill(colum, false);

        //marcar filas sin asignacion
        for (int i = 0; i < m; ++i) {
            filas[i] = (Z[i] == -1);
        }

        boolean cambio;
        do{

            cambio = false;

            //marcar columnas que hay zero en fila marcada
            for (int j = 0; j < m; ++j) {
                if(!colum[j]) {
                    Boolean z = false;
                    for (int i = 0; i < m && !z; ++i) {
                        if (filas[i] && M[i][j] == 0.0f) z = true;
                    }
                    colum[j] = z;
                    if(z) cambio = true;
                }
            }

            //marcar filas que tengan su zero en una columna marcada
            for (int i = 0; i < m; ++i) {
                if (!filas[i]) {
                    filas[i] = colum[Z[i]];
                }
            }
        }while (cambio);

        boolean[][] L = new boolean[2][m];
        Arrays.fill(L[0],false);
        Arrays.fill(L[1],false);

        for(int i = 0; i < m; ++i){
            //fila
            L[0][i] = !filas[i];
            //Columna
            L[1][i] = colum[i];
        }
        return L;
    }

    /**
     * Devuelve la asignacion de zeros maxima de manera que 2 zeros no esten asignados en la misma fila o columna
     * @param M Matriz de costes que se busca la asignación de zeros
     * @param complet Matriz de un solo valor que devuleve si es completa o no la asignacion
     * @return  Matriz con cierto en los zeros asignados
     * 
     * @author Alex
     */
    public static int[] asignarZeros(float[][] M, boolean[] complet){
        int m = M.length;


        //Codigo basado en https://www.geeksforgeeks.org/maximum-bipartite-matching/

        //Marcamos los zeros
        boolean[][] Z = new boolean[m][m];
        for(int i = 0; i < m; ++i){
            for(int k = 0; k <m; ++k){
                Z[i][k] = M[i][k] == 0.0;
            }
        }

        //Asignacion vacia (=-1)
        int matchR[] = new int[m];
        for(int i = 0; i < m; ++i) matchR[i] = -1;

        //Por cada letra
        int count = 0;
        for(int i = 0; i < m; ++i){
            //No se ha explorado
            boolean[] seen = new boolean[m];
            for(int j = 0; j < m; ++j) seen[j] = false;

            //Comprovar si es assignable a alguna tecla
            if(search(Z,i,seen,matchR))
                count++;
        }


        complet[0] = count == m;

        //rotar la asignacion
        int[] asig = new int[m];
        Arrays.fill(asig,-1);
        for(int i = 0; i < m; i++){
            if(matchR[i] != -1) asig[matchR[i]] = i;
        }

        //return matchR;
        return asig;
    }

    private static boolean search(boolean[][] Z, int i, boolean[] seen, int matchR[]){
        int m = Z.length;

        //Por cada tecla
        for(int k = 0; k < m; ++k){
            //Si es asignable y no esta visiada
            if(Z[i][k] && !seen[k]){

                seen[k] = true;//marcar como visitada

                //Si no ha sido asignada, o se puede cambiar la asignacion(recursivamente)
                if(matchR[k] < 0 || search(Z,matchR[k],seen,matchR)){
                    matchR[k] = i;
                    return true;
                }
            }
        }
        return false;
    }

}
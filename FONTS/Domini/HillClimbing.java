package Domini;

import java.util.stream.IntStream;

public class HillClimbing implements EstrategiaAlgoritmo{

    /**
     * Donada una possible solucio, calcula el seu valor
     * @param sol possible solucio on cada posicio és una tecla y el valor la lletra
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return valor de la disposicion actual
     * 
     * @author Alex
     */
    public static float calcVal(int[] sol, float[][] Dist, float[][] Freq){
        float val = 0.0f;

        for(int i = 0; i < sol.length; ++i){
            for (int j = 0; j < i; ++j) {
                val += Dist[i][j] * (Freq[sol[j]][sol[i]] + Freq[sol[i]][sol[j]]);
            }
        }

        return val;
    }

    /**
     * Calcula la diferencia de valor de intencambiar la letra en la tecla i por la letra en la tecla j con respecto a una solucion
     * @param sol solucion actual
     * @param i primera posicion que intercanviar
     * @param j segunda posicion que intercanviar
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return diferencia de valor de intercanviar las letras de las teclas i y j
     * 
     * @author Alex
     */
    private static float calcValDiff(int[] sol,int i, int j, float[][] Dist, float[][] Freq){
        float val = 0.0f;

        int[] solAux = sol.clone();
        solAux[i] = sol[j];
        solAux[j] = sol[i];
        val = calcVal(solAux,Dist,Freq)-calcVal(sol,Dist,Freq);

        return val;
    }

    /**
     * Calcula el algoritmo de hill climbing para encontrar una solucion a QAP
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return Mejor asignacion minima encontrada
     * 
     * @author Alex
     */
    public static int[] calc(float[][] Dist, float[][] Freq){
        return calc(Dist,Freq,Dist.length);
    }

    /**
     * Calcula el algoritmo de hill climbing para encontrar una solucion a QAP
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @param iters numero de hill climbings que provar para coger luego la mejor solucion
     * @return Mejor asignacion minima encontrada
     * 
     * @author Alex
     */
    public static int[] calc(float[][] Dist, float[][] Freq, int iters){
        int N = Dist.length;

        int [] minSol = new int[N];
        float minVal = Float.MAX_VALUE;

        for(int j = 0; j < iters; ++j) {
            int[] sol = IntStream.range(0, N).toArray();

            //unsort solution
            for (int i = N - 1; i >= 0; i--) {
                int r = (int) (Math.random() * i);
                int aux = sol[i];
                sol[i] = sol[r];
                sol[r] = aux;
            }
            
            int[] prev = {-2,-2};

            int n = 9999999;
            while (optimize(sol, Dist, Freq, prev) && n-- > 0) ;
            float val = calcVal(sol,Dist,Freq);
            if(val  < minVal){
                minVal = val;
                minSol = sol.clone();
            }
        }

        return minSol;
    }

    /**
     * Prueva todas las permutaciones de letras en la solucion sol y aplica la que reduce mas el valor
     * @param sol solucion actual
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return si ha habido intercanvio
     * 
     * @author Alex
     */
    public static boolean optimize(int[] sol, float[][] Dist, float[][] Freq){
        int N = sol.length;

        boolean change = false;
        float minVal = 0f;
        int mini = -1;
        int minj = -1;
        for(int i = 0; i < N; ++i){
            for(int j = i+1; j < N; ++j){
                float val = calcValDiff(sol,i,j,Dist,Freq);
                if(val < minVal){
                    minVal = val;
                    mini = i;
                    minj = j;
                    change = true;
                }
            }
        }

        if(change){
            int aux = sol[mini];
            sol[mini] = sol[minj];
            sol[minj] = aux;
        }


        return change;
    }

    /**
     * Prueva todas las permutaciones de letras en la solucion sol y aplica la que reduce mas el valor
     * @param sol solucion actual
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @param prevChange previo intercanvio de teclas, ha de ser [0] es menor que [1]
     * @return si ha habido intercanvio, o si se han intercanviado las mismas teclas false
     * 
     * @author Alex
     */
    public static boolean optimize(int[] sol, float[][] Dist, float[][] Freq , int[] prevChange){
        int N = sol.length;

        boolean change = false;
        float minVal = 0f;
        int mini = -1;
        int minj = -1;
        for(int i = 0; i < N; ++i){
            for(int j = i+1; j < N; ++j){
                float val = calcValDiff(sol,i,j,Dist,Freq);
                if(val < minVal){
                    minVal = val;
                    mini = i;
                    minj = j;
                    change = true;
                }
            }
        }

        //Si cambiamos las mismas teclas salimos porque sino entramos en bucle
        if(mini == prevChange[0] && minj == prevChange[1]){
            return false;
        }

        if(change){
            int aux = sol[mini];
            sol[mini] = sol[minj];
            sol[minj] = aux;
            prevChange[0] = mini;
            prevChange[1] = minj;
        }


        return change;
    }

    /**
     * Calcula el algoritmo de hill climbing para encontrar una solucion a QAP
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return Mejor asignacion minima encontrada
     * 
     * @author Alex
     */
    @Override
    public int[] calcDisposicion(float[][] Dist, float[][] Freq) {
        return calc(Dist,Freq);
    }
}
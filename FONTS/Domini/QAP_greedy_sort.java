package Domini;

import java.util.Arrays;
import java.util.stream.IntStream;

public class QAP_greedy_sort extends QAP{

    /**
     * Aproxima QAP usando un algortimo Greedy, donde se colocan las letras mas freqüentes mas centricas
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return Lista donde cada posicion representa una tecla y el contenido la letra asignada
     * 
     * @author Alex
     */
    public static int[] calc(float[][] Dist, float[][] Freq){
        int n = Dist.length;

        float[] D = new float[n];
        float[] F = new float[n];

        for(int i = 0; i < n; ++i){
            float fi = 0;
            float di = 0;
            for(int j = 0; j <n; ++j){
                fi += Freq[i][j] + Freq[j][i];
                if(di < Dist[i][j]) di = Dist[i][j];
            }
            D[i] = di;
            F[i] = fi;
        }

        //Ordena indices de D i F en respecto a sus valores
        int[] Dsort = IntStream.range(0, n).boxed().sorted(new valComp(D)).mapToInt(i -> i).toArray();
        int[] Fsort = IntStream.range(0, n).boxed().sorted(new valComp(F)).mapToInt(i -> i).toArray();


        int[] sol = new int[n];

        for(int i = 0; i < n; ++i){
            sol[Dsort[i]] = Fsort[n-i-1];
        }

        return sol;
    }

    /**
     * Aproxima QAP usando un algortimo Greedy, donde añade siempre la letra que menos valor añada a la aproximacion parcial
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return Lista donde cada posicion representa una tecla y el contenido la letra asignada
     * 
     * @author Alex
     */
    public static int[] calc2(float[][] Dist, float[][] Freq) {
        int n = Dist.length;
        //Provar todas las parejas y posiciones asta encontrar la optima
        int[] min_sol = new int[n];
        Arrays.fill(min_sol,-1);
        float min_val = Float.MAX_VALUE;
        boolean used[] = new boolean[n];
        Arrays.fill(used,false);
        //letra 1 : i
        for(int i = 0; i < n; i++){
            //tecla 1 : k
            for(int k = 0; k < n; k++){
                int[] sol = new int[n];
                Arrays.fill(sol,-1);
                sol[k] = i;
                //letra 2 : j
                for(int j = i+1; j < n; j++){
                    //tecla 2 : w
                    for(int w = 0; w < n; w++){
                        if(w != k){
                            float val = calcAddedVal(sol,Dist,Freq,j,w);
                            if(val < min_val){
                                min_sol = sol.clone();
                                min_sol[w] = j;
                                min_val = val;
                                Arrays.fill(used,false);
                                used[i] = true;
                                used[j] = true;
                            }
                        }
                    }
                }

            }
        }

        //Ordenar por frequencia las letras
        float[] F = new float[n];

        for(int i = 0; i < n; ++i){
            float fi = 0;
            for(int j = 0; j <n; ++j){
                fi += Freq[i][j] + Freq[j][i];
            }
            F[i] = fi;
        }

        int[] Fsort = IntStream.range(0, n).boxed().sorted(new valComp(F).reversed()).mapToInt(i -> i).toArray();

        //Añadir el resto de letras en la posicion que añada menos
        for(int i = 0; i < n; i++){
            int ip = Fsort[i];
            if(!used[ip]){
                int min_pos = -1;
                float min_pos_val = Float.MAX_VALUE;
                for(int k = 0; k < n; k++){
                    if(min_sol[k] == -1){
                        float val = calcAddedVal(min_sol,Dist,Freq,ip,k);
                        if(val < min_pos_val){
                            min_pos = k;
                            min_pos_val = val;
                        }
                    }
                }

                min_sol[min_pos] = ip;
            }
        }

        return min_sol;
    }

    /**
     * Aproxima QAP usando un algortimo Greedy, donde añade siempre la letra que menos valor añada a la aproximacion parcial
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return Lista donde cada posicion representa una tecla y el contenido la letra asignada
     * 
     * @author Alex
     */
    @Override
    public int[] calcDisposicion(float[][] Dist, float[][] Freq) {
        return calc(Dist,Freq);
    }
}
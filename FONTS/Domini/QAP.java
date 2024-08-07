package Domini;
import java.util.*;
import java.util.stream.IntStream;

public class QAP implements EstrategiaAlgoritmo{

    //Cuantos milisegundos tiene para buscar la mejor solucion
    //-1 si ilimitado
    static public long SEARCHTIME = 30000;

    /**
     * Optimiza QAP a partir de las matrizes de distancias entre teclas y frequencia entre letras.
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * @return Lista donde cada posicion representa una tecla y el contenido la letra asignada
     * 
     * @author Alex
     */
    public static int[] calc(float[][] Dist, float[][] Freq){
        int n = Dist.length;
        //diagonal de distancias a 0(por si acaso)
        for(int i = 0; i < n; ++i) Dist[i][i] = 0;

        float min_val = Float.MAX_VALUE;
        int[] min_sol = new int[n];

        //Greedy para encontrar una aproximacion
        min_sol = QAP_greedy_sort.calc2(Dist,Freq);
        min_val = calcVal(min_sol,Dist,Freq);

        solution min_solution = new solution(min_sol,min_val,0,new boolean[n]);

        //Ordenar teclas por max distancia
        //Buscar la maxima distancia de cada tecla
        float[] Dvals = new float[n];
        for(int i = 0; i < n; ++i){
            float di = 0;
            for(int j = 0; j <n; ++j){
                if(i != j){
                    if(di < Dist[i][j]) di = Dist[i][j];
                }
            }
            Dvals[i] = di;
        }
        //Ordena indices segun la maxima distancia
        Integer[] D = new Integer[n];
        for(int i = 0; i < n; i++) D[i] = i;
        Arrays.sort(D, new valComp(Dvals).reversed());

        //Creamos una cola para guardar las soluciones minimas y que no haya conflicto entre threads
        PriorityQueue<solution> mins = new PriorityQueue<solution>((s1,s2)->{
           float v = s1.val-s2.val;
           if(v == 0) return 0;
           return (v > 0)?1:-1;
        });
        mins.add(min_solution);

        long iniTime = System.currentTimeMillis();
        //Paralelizacion
        IntStream.range(0,n).parallel().
                forEach(i->search(mins,i,D,iniTime,Dist,Freq));

        //get min
        min_sol = mins.peek().sol;

        return min_sol;
    }

    /**
     * Busca dada una letra inicial en una posicion determinada por el primer elemento de D, la mejor assignacion posible, esta pensada para ser paralelizada
     * @param mins cola de prioridad de minimos para que no haya conflicto entre threads
     * @param ini_l letra inicial que intenta colocar
     * @param D Indica en que orden se han de colocar las letras en las teclas
     * @param iniTime Tiempo inicial de calculo, para limitar el tiempo maximo
     * @param Dist matriz de distancias
     * @param Freq matriz de frecuencias
     * 
     * @author Alex
     */
    private static void search(PriorityQueue<solution> mins, int ini_l, Integer[] D,long iniTime, float[][] Dist, float[][] Freq){
        int n = Dist.length;
        Stack<solution> stack = new Stack<>();
        solution ini = new solution(n);
        ini = new solution(ini,0,ini_l,D[0]);
        stack.push(ini);
        while(!stack.isEmpty() && (SEARCHTIME == -1 || SEARCHTIME > System.currentTimeMillis()-iniTime)){
            solution cur_sol = stack.pop();
            if(cur_sol.level >= n){
                if(mins.peek().val > cur_sol.val){
                    mins.add(cur_sol);
                }
                continue;
            }

            //Provar a continuar con todas las letras
            for(int i = 0; i < n; i++){
                if(cur_sol.using[i]) continue;

                //Add to the stack the new solution
                int k = D[cur_sol.level];
                float val = cur_sol.val + calcAddedVal(cur_sol.sol, Dist, Freq, i, k);
                solution new_sol = new solution(cur_sol,val,i,k);
                float cota = 0;
                if(cur_sol.level > 0)cota = QAP_cota_GL.calc(new_sol.sol,Dist,Freq,val);
                if(cota < mins.peek().val) {
                    stack.push(new_sol);
                }
            }

        }
    }

    /**
     * Clase solucion, contiene los datos de una solucion parcial
     */
    private static class solution{
        int[] sol;
        float val;
        int level;

        boolean[] using;

        public solution(int[] sol, float val, int level, boolean[] using){
            this.sol = sol;
            this.val = val;
            this.level = level;
            this.using = using;
        }

        public solution(solution cur_sol, float newval, int new_letter, int new_pos){
            sol = cur_sol.sol.clone();
            sol[new_pos] = new_letter;
            val = newval;
            level = cur_sol.level+1;
            using = cur_sol.using.clone();
            using[new_letter] = true;

        }

        public solution(int n){
            sol = new int[n];
            Arrays.fill(sol,-1);
            val = 0;
            level = 0;
            using = new boolean[n];
            Arrays.fill(using,false);
        }
    }

    /**
     * Optimiza QAP a partir de las matrizes de distancias entre teclas y frequencia entre letras.
     * @return Lista donde cada posicion representa una tecla y el contenido la letra asignada
     */
    @Override
    public int[] calcDisposicion(float[][] Dist, float[][] Freq) {
        return calc(Dist,Freq);
    }

    /**
     * Calcula el sumatorio de distancias*freqüencias de la solucion proupesta
     * @param sol asignacion incompleta
     * @param Dist Matriz de distancias entre teclas
     * @param Freq Matriz de freqüencias entre letras
     * @return El coste de la solucion
     * 
     * @author Alex
     */
    public static float calcVal(int[] sol,float[][] Dist, float[][] Freq){
        int n = sol.length;
        float val = 0;
        for(int i = 0; i < n; ++i){
            if(sol[i] != -1)
            for(int j = i+1; j < n; ++j) {
                if(sol[j] != -1 && sol[i] != sol[j])
                    val += Dist[i][j] * (Freq[sol[i]][sol[j]] + Freq[sol[j]][sol[i]]);
            }
        }
        return val;
    }

    /**
     * Calcula el valor que incrementa una asignacion incompleta al asignar una nueva letra a una tecla sin usar
     * @param sol asignacion incompleta
     * @param Dist Matriz de distancias entre teclas
     * @param Freq Matriz de freqüencias entre letras
     * @param letter Letra que se asigna
     * @param pos Posicion en que se asigna
     * @return Devuelve el valor añadido
     * 
     * @author Alex
     */
    public static float calcAddedVal(int[] sol,float[][] Dist, float[][] Freq, int letter, int pos){
        float val = 0;
        for(int i = 0; i < sol.length; ++i){
            if(sol[i] != -1){
                val += Dist[i][pos]*(Freq[sol[i]][letter] + Freq[letter][sol[i]]);
            }
        }
        return val;
    }

}

//A partir de una lista ordenar enteros que representan posiciones en esta
class valComp implements java.util.Comparator<Integer>{

    float[] M;
    int N;

    public valComp(float[] M){
        this.M = M;
        N = M.length;
    }
    @Override
    public int compare(Integer a, Integer b){
        float s = M[a]-M[b];
        if(s == 0) return 0;
        return (s<0)?-1:1;
    }
}
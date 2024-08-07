package Domini;

public interface EstrategiaAlgoritmo {


    /**
     * Dado la matriz de distancias y de frequencias, devuelve la posicion optima de letras enumeradas
     * 
     * @param Dist matriz de distancias entre posiciones
     * @param Freq matriz de frecuencias entre caracteres
     * @return lista de tamaño n
     */
    public abstract int[] calcDisposicion(float[][] Dist, float[][] Freq);

}

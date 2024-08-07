package ControladorsDeDomini;

import Domini.*;

public class CtrlTeclado {

    /**
     * Funcio que s'encarrega de la creacio del teclat a partir de l'alfabet i la
     * matriu de frequencies pasades com a paramentres.
     * Calculant el layout optim i fent una crida a un dels dos algoritmes, calcula
     * a quina posicio ha d'anar cada lletra i a partir d'aqui obtenim el nou
     * teclat.
     *
     * @param nameTec            String que correspon al nom del teclat
     * @param alfabeto           String que conte les lletres de l'alfabet per crear
     *                           el teclat
     * @param matriz_frecuencias Matriu que conte les frequenies de les lletres
     * @param algoritmo1         boolea que indica quin algoritme s'utilitza per
     *                           crear el teclat
     * @return Teclat creat a partir dels parametres
     *
     * @author Ona
     */
    public static Teclado crearTeclado(String nameTec, String alfabeto, float[][] matriz_frecuencias,
            boolean algoritmo1) {

        Layout layout = new Layout(matriz_frecuencias.length);
        Teclado tec = new Teclado(nameTec, alfabeto, layout);

        if (algoritmo1)
            tec.posiciones = QAP.calc(layout.getMatrizDistancias(), matriz_frecuencias);
        else
            tec.posiciones = HillClimbing.calc(layout.getMatrizDistancias(), matriz_frecuencias);

        return tec;
    }

}

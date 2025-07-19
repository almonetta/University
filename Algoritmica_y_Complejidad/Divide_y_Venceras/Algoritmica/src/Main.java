
public class Main {
    public static void main(String[] args) {
        int[] vector_1 = {10, 15, 23, 32, 1, 2, 3, 4, 7};
        //int[] vector_2 = {-4, -2, 0, 1, 1, 5, 7, 10, -6};
        int indice, pos, valor, max;
        for(int i = 0; i< vector_1.length; i++){
            System.out.println(vector_1[i]);
        }
        System.out.println("\n");
        //int min = minArrayRotado(vector_1);
        /*for(int i = 0; i< vector_1.length; i++){
            System.out.println(vector_2[i]);
        }*/
        //indice = elementoEspecial(vector_1);
        //pos = parImpar(vector_1);
        //valor = elementoSolitario(vector_1);
        //max = k_esimoValor2(vector_1, 5);
        //System.out.println("\n" + "El minimo es: " + min);
    }
    //28/09/2023
    public static int elementoEspecial(int[] vector){
        return elementoEspecialAux(vector, 0, vector.length-1);
    }
    public static int elementoEspecialAux(int[] vector, int i0, int iN){
        if(i0 == iN){
            if(vector[i0] == i0){ return i0;}
            else{ return -1;}
        }else{
            int k = (i0+iN)/2;
            if(vector[k] > k) {
                return elementoEspecialAux(vector, i0, k);
            }else if(vector[k] < k){
                return elementoEspecialAux(vector, k+1, iN);
            }else{
                return k;
            }
        }
    }

    public static int arrayPolar(int[] vector){
        return arrayPolarAux(vector, 0, vector.length-1);
    }
    public static int arrayPolarAux(int[] vector, int i0, int iN){
        if(i0 == iN) {
            if (vector[i0] > 0) {
                return i0;
            } else {
                return -1;
            }
        }else{
            int k = (i0+iN)/2;
            if(vector[k] > 0){
                return arrayPolarAux(vector, i0,k);
            }else {          //if(vector[k] < 0)
                return arrayPolarAux(vector, k+1, iN);
            }
        }
    }

    //03/10/2023
    public void ordenPivote(int[] vector) {
        int pivote = vector[vector.length - 1];
        int iz = 0, de = vector.length - 2;

        for (int i = 0; i < vector.length - 1 && vector[i] <= pivote; i++) {
            //aux[i] = vector[i];
        }
    }

    //05/10/2023
    public static int elementoSolitario(int[] vector){
        return elementoSolitarioAux(vector, 0, vector.length-1);
    }
    public static int elementoSolitarioAux(int[] vector, int i0, int iN){
        if(i0 == iN) {
            return vector[i0];
        }else{
            int k = (i0+iN)/2;
            if(vector[k-1] == vector[k]) {
                if ((k - 2 - i0 + 1) % 2 == 0) {
                    return elementoSolitarioAux(vector, k + 1, iN);
                } else {
                    return elementoSolitarioAux(vector, i0, k - 2);
                }
            }else if(vector[k] == vector[k+1]){
                if((k-1-i0+1)%2 == 0){
                    return elementoSolitarioAux(vector, i0, k+2);
                }else{
                    return elementoSolitarioAux(vector, i0, k-1);
                }
            }else{
                return vector[k];
            }
        }
    }

    public static int oneSubArray(int[] vector){
        return oneSubArrayAux(vector, 0, vector.length-1);
    }
    public static int oneSubArrayAux(int[] vector, int i0, int iN){
        if(i0 == iN){
            return vector[i0];
        }else{
            int k = (i0+iN)/2;
            int lizq = oneSubArrayAux(vector, i0, k);
            int lder = oneSubArrayAux(vector, k+1, iN);
            int cruzada = oneSubCruzada(vector, i0, k, iN);
            return Math.max(lizq, Math.max(lder, cruzada));
        }
    }
    public static int oneSubCruzada(int[] vector, int i0, int k, int iN){
        int len_izq = 0, len_der = 0, len_total = 0;
        int i = k, j = k+1;
        while(i>= i0 && vector[i] == 1){
            len_izq++; i--;
        }
        while(j<=iN && vector[j] == 1){
            len_der++; j++;
        }
        len_total = len_der + len_izq;
        return len_total;
    }

    //07/10/2023

    /*public static int minArrayRotado(int[] vector){
        return minArrayRotadoAux(vector, 0, vector.length-1);
    }
    public static int minArrayRotadoAux(int[] vector, int i0, int iN){
        if(i0 == iN){
            return i0;
        }else{
            int k = (i0+iN)/2;
            if((vector[i0] <= vector[k]) && (vector[k] < vector[iN]))
                return i0;
            else if(vector[i0] > vector[k])
                return minArrayRotadoAux(vector, i0, k);
            else           //vector[iN] < vector[k]
                return minArrayRotadoAux(vector, k+1, iN);
        }
    }*/

    //08/10/2023
    public static int maxArrayColina(int[] vector){
        return maxArrayColinaAux(vector, 0, vector.length-1);
    }
    public static int maxArrayColinaAux(int[] vector, int i0, int iN){
        if(i0 == iN) {
            return vector[i0];
        }else if(iN == i0+1){
            return Math.max(vector[i0], vector[iN]);
        }else{
            int k = (i0+iN)/2;
            if(vector[k] < vector[k+1]){
                return maxArrayColinaAux(vector, k+1, iN);
            }else if(vector[k-1] < vector[k]){
                return vector[k];
            }else{
                return maxArrayColinaAux(vector, i0, k);
            }
        }
    }

    //09/10/2023
    public static int posDif(int[] vector1, int[] vector2){
        return posDifAux(vector1, vector2, 0, vector1.length-1);
    }
    public static int posDifAux(int[] v1, int[] v2, int i0, int iN){
        if(i0 == iN){
            if(v1[i0] != v2[i0]){
                return i0;
            }else{
                return -1;
            }
        }else{
            int k = (i0+iN)/2;
            if(v1[k] == v2[k]){
                return posDifAux(v1, v2, k+1, iN);
            }else{ //if(v1[k] != v2[k]){
                return posDifAux(v1, v2, i0, k);
            }
        }
    }

    public static int longMaxSubArrayOrdenado(int[] vector){
        return longMaxSubArrayOrdenadoAux(vector, 0, vector.length-1);
    }
    public static int longMaxSubArrayOrdenadoAux(int[] v, int i0, int iN){
        if(i0 == iN){
            return 1;
        }else{
            int k = (i0+iN)/2;
            int izq = longMaxSubArrayOrdenadoAux(v, i0, k);
            int der = longMaxSubArrayOrdenadoAux(v, k+1, iN);
            int cruzada = longMaxSubCruzada(v, i0, k, iN);
            return Math.max(izq, Math.max(der, cruzada));
        }
    }
    public static int longMaxSubCruzada(int[] vector, int i0, int k, int iN){
        int i = k;
        while(i>i0 && vector[i-1] <= vector[i]){
            i--;
        }
        int j = k;
        while(j<iN && vector[j+1] >= vector[j]){
            j++;
        }
        return j-i+1;
    }

    public static int parImpar(int[] vector){
        return parImparAux(vector, 0, vector.length-1);
    }
    public static int parImparAux(int[] vector, int i0, int iN){
        if(i0 == iN){
            return i0;
        }else{
            int k = (i0+iN)/2;
            if(esPar(vector[k]) && esPar(k) || !esPar(vector[k]) && !esPar(k)){
                return parImparAux(vector, k+1, iN);
            }else{
                return parImparAux(vector, i0, k);
            }
        }
    }
    public static boolean esPar(int n){
        return n % 2 == 0;
    }

    public static int maxSubArrayPositivos(int[] vector){
        return maxSubArrayPositivosAux(vector, 0, vector.length-1);
    }
    public static int maxSubArrayPositivosAux(int[] vector, int i0, int iN){
        if(i0 == iN){
            if(vector[i0] < 0){
                return 0;
            }else{
                return vector[i0];
            }
        }else{
            int k = (i0+iN)/2;
            int izq = maxSubArrayPositivosAux(vector, i0, k);
            int der = maxSubArrayPositivosAux(vector, k+1, iN);
            int cruz = maxSubArrayPositivosCruzada(vector, i0, k, iN);
            return Math.max(izq, Math.max(der, cruz));
        }
    }
    public static int maxSubArrayPositivosCruzada(int[] vector, int i0, int k, int iN){
        int i = k;
        int sum1 = 0, sum2 = 0;
        while(i>i0 && vector[i] > 0){
            sum1+= vector[i]; i--;
        }
        int j = k+1;
        while(j<iN && vector[j] > 0){
            sum2+= vector[j]; j++;
        }
        return sum1+sum2;
    }

    //10/10/2023
    public static int k_esimoValor(int[] vector, int k){
        int max = Integer.MIN_VALUE;
        for(int j = 0; j<k; j++){
            max = vector[j];
            int j_max = j;
            for(int i=j; i< vector.length; i++){
                if(vector[i] > max){
                    j_max = i; max = vector[i];
                }
            int aux = vector[j];
            vector[j] = vector[j_max];
            vector[j_max] = aux;
            }
        }
        return max;
    }

    public static int k_esimoValorAux(int[] vector, int i0, int iN, int k){
        if(i0 == iN){
            return vector[i0];
        }else{
            int m = ordenarPivote(vector, i0, iN);
            if(k<= iN-m)
                return k_esimoValorAux(vector, m+1, iN, k);
            else if(k == iN-m+1)
                return vector[m];
            else
                return k_esimoValorAux(vector, i0, m-1, k-(iN-m+1));
        }
    }
    public static int k_esimoValor2(int[] vector, int k){
    return k_esimoValorAux(vector, 0, vector.length-1, k);
    }
    public static int ordenarPivote(int[] vector, int i0, int iN){
        int pivote = vector[iN];
        int i = i0;
        int j = iN-1;
        while(i<j){
            while(vector[i] <= pivote && i<j){i++;}
            while(vector[j] > pivote && i<j){ j--;}
            int aux = vector[i]; vector[i] = vector[j]; vector[j] = aux;
        }
        if(vector[i] > pivote){
            vector[iN] = vector[i]; vector[i] = pivote;
            return i;
        }else{
            return iN;
        }
    }


}

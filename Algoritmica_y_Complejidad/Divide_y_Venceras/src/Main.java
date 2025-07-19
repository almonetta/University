public class Main {
    public static void main(String[] args) {
        int[] vector1 = {2, 5, 8, 9, 22, 34, 45, 98, 101};
        int[] vector2 = {4, 7, 23, 34, 45, 56, 67, 78, 89};
        int pos = parImpar(vector2);
        System.out.println(pos);

    }

    public static int parImpar(int[] vector){
        return parImparAux(vector, 0, vector.length-1);
    }
    public static int parImparAux(int[] vector, int i0, int iN){
        if(i0 == iN) {
            return i0;
        }else{
            int k = (i0+iN)/2;
            if(vector[k]%2 == 0 && k%2 == 0 || vector[k]%2 == 1 && k%2 == 1){
                return parImparAux(vector, k+1, iN);
            } else{
                return parImparAux(vector, i0, k);
            }
        }
    }


    public static int posDiferente(int[] vector1, int[] vector2){
        return posDiferenteAux(vector1, vector2, 0, vector1.length-1);
    }
    public static int posDiferenteAux(int[] v1, int[] v2, int i0, int iN){
        if(i0 == iN){
            if(v1[i0] != v2[i0]) return i0;
            else return -1;
        }else{
            int k = (i0+iN)/2;
            if(v1[k] == v2[k])
                return posDiferenteAux(v1, v2, k+1, iN);
            else
                return posDiferenteAux(v1, v2, i0, k);
        }
    }
    /*
    public static int longMaxSubArrayOrdenado(int[] vector){
        return longMaxSubArrayOrdenadoAux(vector, 0, vector.length-1);
    }
    public static int longMaxSubArrayOrdenadoAux(int[] vector, int i0, int iN){
        if(i0 == iN){
            return 1;
        }else{
            int k = (i0+iN)/2;
            int izq = longMaxSubArrayOrdenadoAux(vector, i0, k);
            int der = longMaxSubArrayOrdenadoAux(vector, k+1, iN);
            int cruz = longMaxSubArrayOrdenadoCruzada(vector, i0, k, iN);
            return Math.max(izq, Math.max(der, cruz));
        }
    }
    public static int longMaxSubArrayOrdenadoCruzada(int[] vector, int i0, int k, int iN){
        int i = k, j = k;
        while(i >= i0 && vector[i-1] < vector[i]){ i--;}
        while(j <= iN && vector[j+1] > vector[j]){ j++;}
        return j-i+1;
    }*/

    public static int maxSubArrayPositivos(int[] vector){
        return maxSubArrayPositivosAux(vector, 0, vector.length-1);
    }
    public static int maxSubArrayPositivosAux(int[] vector, int i0, int iN){
        if(i0 == iN) {
            if (vector[i0] > 0) return vector[i0];
            else return 0;
        }else{
            int k = (i0+iN)/2;
            int izq = maxSubArrayPositivosAux(vector, i0, k);
            int der = maxSubArrayPositivosAux(vector, k+1, iN);
            int cruz = maxSubArrayPosCruzada(vector, i0, k, iN);
            return Math.max(izq, Math.max(der, cruz));
        }
    }
    public static int maxSubArrayPosCruzada(int[] vector, int i0, int k, int iN){
        int suma = 0, i = k, j = k+1;
        while(i >= i0 && vector[i] > 0){ suma += vector[i]; i--;}
        while(j <= iN && vector[j] > 0){ suma += vector[j]; j++;}
        return suma;
    }

    public static int oneSubArray(int[] vector){
        return oneSubArrayAux(vector, 0, vector.length-1);
    }
    public static int oneSubArrayAux(int[] vector, int i0, int iN){
        if(i0 == iN){
            return vector[i0];
        }else{
            int k = (i0+iN)/2;
            int izq = oneSubArrayAux(vector, i0, k);
            int der = oneSubArrayAux(vector, k+1, iN);
            int cruz = oneSubArrayCruzada(vector, i0, k, iN);
            return Math.max(izq, Math.max(der, cruz));
        }
    }
    public static int oneSubArrayCruzada(int[] vector, int i0, int k, int iN){
        int len_i = 0, len_j = 0, i = k, j = k+1;
        while(i >= i0 && vector[i] == 1){ len_i++; i--;}
        while(j <= iN && vector[j] == 1){ len_j++; j++;}
        return len_i+len_j;
    }

    public static int encontrarIndice(int[] vector){
        return encontrarIndiceAux(vector, 0, vector.length-1);
    }
    public static int encontrarIndiceAux(int[] vector, int i0, int iN){
        if(i0 == iN){
            if(vector[i0] == i0) return i0;
            else return -1;
        }else{
            int k = (i0+iN)/2;
            if(vector[k] > k)
                return encontrarIndiceAux(vector, i0, k);
            else if (vector[k] == k)
                return k;
            else
                return encontrarIndiceAux(vector, k+1, iN);
        }
    }

    public static int primerPositivoEnPolarizado(int[] vector){
        return primerPositivoEnPolarizadoAux(vector, 0, vector.length-1);
    }
    public static int primerPositivoEnPolarizadoAux(int[] vector, int i0, int iN){
        if(i0 == iN)
            if(vector[i0] > 0) return i0;
            else return -1;
        else {
            int k = (i0 + iN) / 2;
            if (vector[k] > 0)
                if(vector[k-1] > 0)
                    return primerPositivoEnPolarizadoAux(vector, i0, k-1);
                else
                    return k;
            else{ //if(vector[k] < 0)
                return primerPositivoEnPolarizadoAux(vector, k+1, iN);
            }
        }
    }
}

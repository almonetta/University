import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTracking {
    //17/10/2023
    /*Suma enteros - Arbol Binario (version 1)*/
    void subconjuntoSumaBack(int[] vector, int num, int[] solucion, int nivel, Booleano exito){
        if(nivel == vector.length){
            if(suma(solucion, vector, nivel)==num) exito.setValor(true);
        }else{
            int c = 0;
            while (!exito.getValor() && (c < 2)) {
                if(c==0 || suma(solucion, vector, nivel)+vector[nivel]<= num){
                    solucion[nivel] = c;
                    nivel = nivel + 1;
                    subconjuntoSumaBack(vector, num, solucion, nivel, exito);
                    if(!exito.getValor()){
                        nivel = nivel - 1;
                        solucion[nivel] = 0;
                    }
                }
            }
        }
    }
    int suma(int[] solucion, int[] vector, int nivel){
        int suma = 0;
        for(int i=0; i<nivel; i++)
            if(solucion[i]==1) suma = suma + vector[i];
        return suma;
    }

    /*Suma enteros - Arbol Combinatorio (version 2)*/
    void subconjuntoSumaBack2(int[] vector, int num, int[] solucion, int id, int suma, Booleano exito){
        if(suma == num) exito.setValor(true);
        else{
            int c = id;
            while(!exito.getValor() && (c < vector.length)){
                if((suma + vector[c])<= num){
                    solucion[c] = 1;
                    suma = suma + (vector[c]);
                    c = c + 1;
                    subconjuntoSumaBack2(vector, num, solucion, c, suma, exito);
                    if(!exito.getValor()){
                        c = c - 1;
                        solucion[c] = 0;
                        suma = suma - (vector[c]);
                    }
                }
                c++;
            }
        }
    }

    //19/10/2023
    //Los tres nietos de Carlomagno (Junio 2018)
    boolean hayRapertoEquitativo1(int[] bienes){
        int suma = 0;
        for(int i=0; i<bienes.length; i++){
            suma += bienes[i];
        }
        if(suma%3 != 0) return false;
        int x = suma/3;
        int[] n ={x, x, x};
        int[] solucion = new int[bienes.length];
        for(int i=0; i< bienes.length; i++){
            solucion[i] = 0;
        }
        Booleano e = new Booleano(false);
        backtracking1(bienes, n, solucion, 0, e); //??
        return e.getValor();
    }
    void backtracking1(int[] v, int[] h, int[] solucion, int k, Booleano e){
        if(h[0]==0 && h[1]==0 && h[2]==0 && v.length==k){
            e.setValor(true);
        }else{
            int c = 0;
            while(!e.getValor() && c < 3){
                if(h[c] - v[k] >= 0){
                    solucion[k] = c;
                    h[c] -= v[k];
                    k++;
                    backtracking1(v, h, solucion, k, e);
                    if(!e.getValor()){
                        k--;
                        h[c] = h[c] + v[k];
                        solucion[k] = 0;
                    }
                }
                c++;
            }
        }
    }
    //Problema parecido
    //suma --> h[0, 1, 2]
    //0 --> no incluir, 1 --> entregarlo al subconjunto 1, 2 --> subconjunto 2
    boolean hayRapertoEquitativo2(int[] bienes, int x){
        int suma = 0;
        int[] h = {x, x};
        int[] sol = new int[bienes.length];
        Booleano e = new Booleano(false);
        backtracking2(bienes, h, sol, 0, e);
        return e.getValor();
    }
    void backtracking2(int[] v, int[] h, int[] solucion, int k, Booleano e){
        if(h[0]==0 && h[1]==0 && v.length==k){
            e.setValor(true);
        }else{
            int c = 0;
            while(!e.getValor() && c < 3){
                if(c == 0 || h[c-1] - v[k] >= 0){
                //if(c == 0 || h[c-1] >= v[k])
                    solucion[k] = c;
                    if(c > 0){
                    h[c-1] -= v[k];}
                    k++;
                    backtracking2(v, h, solucion, k, e);
                    if(!e.getValor()){
                        k--;
                        if(c > 0){
                        h[c-1] = h[c-1] + v[k];}
                        solucion[k] = 0;
                    }
                }
                c++;
            }
        }
    }

    //21/10/2023
    //Problema del "Laberinto" 4x4
    /*boolean solveMaze(int[][] laberinto, int x, int y, int destX, int destY, int moveCount){
        if(x == destX && y == destY){
            return moveCount == 16;
        }
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        for(int i=0; i<4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(isValidMove(newX, newY, laberinto)){
                laberinto[newX][newY] = moveCount;
                if(solveMaze(laberinto, newX, newY, destX, destY, moveCount+1)){
                    return  true;
                }
                laberinto[newX][newY] = 0;
            }
        }
        return false;
    }
    boolean isValidMove(int x, int y, int[][]laberinto){
        return x >= 0 && x < 4 && y >= 0 && y < 4 && laberinto[x][y] == 0;
    }
    void printLaberinto(int[][] laberinto){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.println(laberinto[i][j] + " ");
            }
            System.out.println();
        }
    }*/
    List<int[]> resolverLaberinto(int[][] laberinto, int inicioX, int inicioY, int finX, int finY){
        List<int[]> camino = new ArrayList<>();
        int[][] movimientos = {{0,1}, {1,0}, {0, -1}, {-1,0}};
        boolean[][] visitados = new boolean[laberinto.length][laberinto[0].length];
        camino.add(new int[]{inicioX, inicioY});
        if(backtrackingMaze(laberinto, movimientos, visitados, inicioX, inicioY, finX, finY, camino)){
            return camino;
        }else{
            return null;
        }
    }
    private boolean backtrackingMaze(int[][] laberinto, int[][] movimientos, boolean[][] visitados, int x, int y, int finX, int finY, List<int[]> camino){
        if(x == finX && y == finY){
            return true;
        }
        for(int[] movimiento : movimientos){
            int nuevoX = x + movimiento[0];
            int nuevoY = y + movimiento[1];
            if(nuevoX >= 0 && nuevoX < laberinto.length && nuevoY >= 0 && nuevoY < laberinto[0].length && !visitados[nuevoX][nuevoY] && laberinto[nuevoX][nuevoY] == 0){
            visitados[nuevoX][nuevoY] = true;
            camino.add(new int[]{nuevoX, nuevoY});
            if(backtrackingMaze(laberinto, movimientos, visitados, nuevoX, nuevoY, finX, finY, camino)){
                return true;
            }
            visitados[nuevoX][nuevoY] = false;
            camino.remove(camino.size()-1);
        }
    }
        return false;
    }

    //24/10/2023
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    void backtrackingLaberinto(int[][] t, int x_j, int y_j, int x_m, int y_m, int[][] s, int moves, Booleano[] e){
        if(x_m == x_j && y_m == y_j){ e[0] = true;}
        else{
            int c = 0; int u, v;
            while(!e[0] && c < 4){
                u = x_j + dx[c];
                v = y_j + dy[c];
                if ((u >= 0) && (u < t.length) && (v >= 0) && (v < t.length) && t[u][v] == 0 && s[u][v] == 0) {
                    s[u][v] = moves; moves++;
                    backtrackingLaberinto(t, u, v, x_m, y_m, s, moves, e);
                    if(!e[0]){
                        moves--;
                    } s[u][v] = 0;
                }c++;
            }
        }
    }

    //26/10/2023
    //Enunciado 1.3 - 8 noviembre de 2019
    //Distribución de tareas (solución de árbol combinatorio)
    void backtrackingTareas(int[] ori, int[] fin, int id, int duracion, boolean[] sol, Integer mejorValor, boolean[] mejorSol){
        if(mejorValor.getValor() < duracion){
            mejorValor.setValor(duracion);
            for(int i=0; i< sol.length; i++){ mejorSol[i] = sol[i];}
        }
        for(int c = id; c < sol.length; c++){
            if(noColision(c, ori, fin, sol)){
                sol[c] = true; id++; duracion += (fin[c] - ori[c]);
                backtrackingTareas(ori, fin, c+1, duracion, sol, mejorValor, mejorSol);
                duracion -= (fin[c] + ori[c]); id--; sol[c] = false;
            }
        }
    }
    boolean noColision(int candidato, int[] ori, int[] fin, boolean[] sol){
        boolean ok = true;
        int i = 0;
        while(ok && i<candidato){
            if(sol[candidato]){
                ok = (fin[candidato] <= ori[i]) || (fin[i] <= ori[candidato]);
            }
            i++;
        }
        return ok;
    }
    boolean[] maxUsoRecurso(int[] origen, int[] fin){
        boolean[] solucion = new boolean[origen.length];
        Integer mejorValor = new Integer(0);
        boolean[] mejorSolucion = new boolean[origen.length];
        backtrackingTareas(origen, fin, 0, 0, solucion, mejorValor, mejorSolucion);
        return mejorSolucion;
    }

    //Enunciado 1.4 - 11 noviembre de 2022
    //Dos contenedores de peso máximo de 10
    //Solución con arbol combinatorio
    void backtrackingContenedores(int[] peso, int pesoMax, int id, int peso1, int peso2,
                   int[] solucion, Integer mejorValor, int[] mejorSol){
        if(peso2 <= pesoMax){
            if(mejorValor.getValor() > Math.abs(peso1-peso2)){
                mejorValor.setValor(Math.abs(peso1-peso2));
                for(int i=0; i<peso.length; i++){ mejorSol[i] = solucion[i];}
            }
        }
        for(int c=id; c < solucion.length; c++){
            if(peso[c] + peso1 <= pesoMax){
                //ANOTAR
                solucion[c] = 1;
                peso1 += peso[c];
                peso2 -= peso[c];
                backtrackingContenedores(peso, pesoMax, c+1, peso1, peso2, solucion, mejorValor, mejorSol);
                //DESANOTAR
                peso2 += peso[c];
                peso1 -= peso[c];
                solucion[c] = 2;
            }
        }
    }
    int[] distribucionCarga2(int[] pesos, int pesoMax){
        int total = 0;
        int[] mejorSolucion = new int[pesos.length];
        int[] solucion = new int[pesos.length];
        for(int i=0; i<pesos.length; i++){
            total += pesos[i];
            solucion[i] = 2;    //inicializamos todo a 2, porq asumimos q todos van al segundo contenedor
        }
        backtrackingContenedores(pesos, pesoMax, 0, 0, total, solucion,
                new Integer(Integer.MAX_VALUE), mejorSolucion);
        return mejorSolucion;
    }

    //Suma igual a 0 - 26 noviembre de 2021
    //solucion arbol combinatorio
    void backtrackingSumaEquals0(int[] v, boolean[] s, int k, int l, int suma,
                                 Integer mejorValor, boolean[] mejorSol){
        if(k == v.length && suma==0){
            if(mejorValor.getValor() < l){
                mejorValor.setValor(l);
                for(int i=0; i<s.length;i++){mejorSol[i] = s[i];}
            }
        }else if(k<v.length){
            for(int c=0; c < 2; c++){
            s[k] = (c==1);
            l += c;
            suma += v[k]*c;
            k++;
            backtrackingSumaEquals0(v, k, l, suma, mejorValor, mejorSol);
            k--;
            suma -= v[k]*c;
            l -= c;
            s[k] = false;
            }
        }
    }
}

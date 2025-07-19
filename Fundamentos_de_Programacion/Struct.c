/*
#include <stdio.h>
#include <stdlib.h>

struct Tjugadores{
    char nombre[50];
    int max_punt;
};

int main()
{
    struct Tjugadores player[6];
    char jmayor[50], jmenor[50];
    int n, mayor, menor;
    mayor = menor = 0;
    for(n = 0; n<=5; n++){
        printf("\nEscribe nombre de jugador d%: ", n+1);
        gets(player[n].nombre);

        printf("\nEscribe la puntuacion maximo de jugador d%: ", n+1);
        scanf("%d", &player[n].max_punt);
        fflush(stdin);
    }

    mayor = player[0].max_punt;
    strcpy(jmayor, player[0].nombre);
    menor = player[0].max_puntos;
    strcpy(jmenor, player[0].nombre);

    for(n = 0; n<=5; n++){
        if(player[n].max_punt > mayor){
            mayor = player[n].max_punt;
            strcpy(jmayor, player[n].nombre);
        }
        if(player[n].max_punt < menor){
            menor = player[n].max_punt;
            strcpy(jmenor, player[n].nombre);
        }
    }

    printf("\nEl jugador con mayor numero de puntos es s% (con d% puntos)", jmayor, mayor);
    printf("\nEl jugador con menor numero de puntos es s% (con d% puntos)", jmenor, menor);
}
*/

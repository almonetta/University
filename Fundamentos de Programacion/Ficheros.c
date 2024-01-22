/*
//fwrite(direccion variable, tamaño, veces, fichero);
//fwrite(&nombre_vble, strlen(nombre)/sizeof(nombre), 1, fichero);
#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE * f;
    char caracter = 'A';

    f = fopen("prueba.txt", "w");

    if (f == NULL){
        printf("Error: no se ha podido crear el fichero prueba.txt");
    } else {
        fwrite(&caracter, sizeof(caracter), 1, f);
        fclose(f);
    }

    fflush(stdin);
    return 0;
}
//--------------------------------------------------------------------
//ejemplo de fwrite(); con strlen()
#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE * f;
    char frase[100];

    f = fopen("prueba.txt", "w");

    if (f == NULL){
        printf("Error: no se ha podido crear el fichero prueba.txt");
    } else {
        printf("Escriba una frase: ");
        gets(frase);
        fwrite(&frase, strlen(frase), 1, f);
        fclose(f);
    }

    fflush(stdin);
    return 0;
}
//--------------------------------------------------------------------
//ejemplo de fwrite(); con un fichero binario
#include <stdio.h>
#include <stdlib.h>

struct Tficha{
    char nombre[30];
    int edad;
};

int main()
{
    FILE * f;
    struct Tficha persona;
    int n;

    f = fopen("datos.dat", "wb");

    if (f == NULL){
        printf("Error: no se ha podido crear el fichero datos.dat");
    } else {
        for(n=1; n<=3; n++){
            printf("\n\nNombre: ");
            gets(persona.nombre);
            printf("\nEdad: ");
            scanf("%d", &persona.edad);
            fwrite(&persona, sizeof(persona), 1, f);
        }
        fclose(f);
    }

    fflush(stdin);
    return 0;
}
//--------------------------------------------------------------------
//getc(fichero);
#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE * f;
    char caracter;

    f = fopen("prueba.txt", "r");

    if (f == NULL){
        printf("Error: no se ha podido crear el fichero prueba.txt");
    } else {
        caracter = getc(f);
        printf("El caracter leido es %c", caracter);
        fclose(f);
    }

    fflush(stdin);
    return 0;
}
//--------------------------------------------------------------------
//fgets(cadena, tamaño, fichero);
#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE * f;
    char cadena[256];
    char *resultado;

    f = fopen("prueba.txt", "r");

    if (f == NULL){
        printf("Error: no se ha podido crear el fichero prueba.txt");
    } else {
        resultado = fgets(cadena, 256, f);
        while(resultado != NULL){
            printf("%s", cadena);
            resultado = fgets(cadena, 256, f);
        }
        fclose(f);
    }

    fflush(stdin);
    return 0;
}
//--------------------------------------------------------------------
//fread(direccion_vble, tamaño, veces, fichero);
#include <stdio.h>
#include <stdlib.h>

struct Tficha{
    char nombre[30];
    int edad;
};

int main()
{
    FILE * f;
    struct Tficha persona;

    f = fopen("datos.dat", "rb");

    if (f == NULL){
        printf("Error: no se ha podido crear el fichero prueba.txt");
    } else {
        fread(&persona, sizeof(persona), 1, f);
        while(feof(f)==0){
            printf("\n\nNombre: %s", persona.nombre);
            printf("\nEdad: %d", persona.edad);
            fread(&persona, sizeof(persona), 1, f);
        }
        fclose(f);
    }

    fflush(stdin);
    return 0;
}
//--------------------------------------------------------------------
//fread(direccion_vble, tamaño, veces, fichero);
#include <stdio.h>
#include <stdlib.h>

struct Tficha{
    char nombre[30];
    int edad;
};

int main()
{
    FILE * f;
    struct Tficha persona;
    int edadbuscada;
    int existeedad = 0;

    printf("\nEscriba una edad: ", edadbuscada);
    scanf("%d", &edadbuscada);

    f = fopen("datos.dat", "rb");

    if (f == NULL){
        printf("Error: no se ha podido crear el fichero prueba.txt");
    } else {
        fread(&persona, sizeof(persona), 1, f);
        while(feof(f)==0){
            if(persona.edad == edadbuscada){
                printf("\n\nNombre: %s", persona.nombre);
                existeedad = 1;
            }
            fread(&persona, sizeof(persona), 1, f);
        }
        fclose(f);

        if(existeedad == 0)
            printf("\nNo exite ninguna persona con esa edad");
    }

    fflush(stdin);
    return 0;
}
//--------------------------------------------------------------------

*/

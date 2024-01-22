/*
//Reserva y liberacion de memoria;
#include <stdio.h>
#include <stdlib.h>

struct Tficha{
    int numero;
    char palabra[20];
};

int main()
{
   struct Tficha *mi_ficha;

   mi_ficha = (struct Tficha *) malloc (sizeof(struct Tficha));

   printf("Escriba un numero: ");
   scanf("%d", &mi_ficha->numero);
   fflush(stdin);
   printf("Escriba una palabra: ");
   gets(mi_ficha->palabra);

   printf("\nNumero: %d", mi_ficha->numero);
   printf("\nPalabra: %s", mi_ficha->palabra);

   free(mi_ficha);

   fflush(stdin);
   return 0;
}
//------------------------------------------------------------------
//Implementacion de una lista
struct TNodo{
    int numero;
    struct TNodo *siguiente;
};

struct TlistaNodos{
    struct TNodo *inicio;
};
struct TlistaNodos listaNodos;
//inicializar lista
void iniciarLista(struct TlistaNodos *listaNodos){
    (*listaNodos).inicio = NULL;
}
//------------------------------------------------------------------
//Insertar un nodo al principio
void insertarNodo(struct TNodo *nodo, struct TlistaNodos *listaNodos){
    (*nodo).siguiente = (*listaNodos).inicio;
    (*listaNodos).inicio = nodo;
}
//------------------------------------------------------------------
//Insertar al principio, creando un nodo
void insertarCreandoNodo(int n, struct TlistaNodos * listaNodos){
    struct TNodo * nodo;
    nodo = malloc(sizeof(struct TNodo));
    (*nodo).numero = n;
    (*nodo).siguiente = (*listaNodos).inicio;
    (*listaNodos).inicio = nodo;
}
//------------------------------------------------------------------
//eliminar el primer nodo de una lista
void eliminarPrimerNodo(struct TlistaNodos * listaNodos){
    struct TNodo * nodo;
    if((*listaNodos).inicio != NULL){
        nodo = (*listaNodos).inicio;
        (*listaNodos).inicio = (*nodo).siguiente;
        free(nodo);
    }
}
//------------------------------------------------------------------
//eliminar todos los nodos de una lista
void eliminarListaNodos(struct TlistaNodos * listaNodos){
    struct TNodo * nodo;
    nodo = (*listaNodos).inicio;
    while(nodo = NULL){
        (*listaNodos).inicio = (*nodo).siguiente;
        free(nodo);
        nodo = (*listaNodos).inicio;
    }
}
//------------------------------------------------------------------
//recorrer y printar los nodos de una lista
void printarListaNodos(struct TlistaNodos * listaNodos){
    struct TNodo * nodo;
    nodo = (*listaNodos).inicio;
    while(nodo != NULL){
        printf("%d", (*nodo).numero);
        nodo = (*nodo).siguiente;
    }
    printf("NULL\n");
}
//------------------------------------------------------------------
main(){
    struct TlistaNodos listaNodos;
    iniciarLista(&listaNodos);

    insertarCreandoNodo(3, &listaNodos);
    insertarCreandoNodo(2, &listaNodos);
    insertarCreandoNodo(1, &listaNodos);
    printListaNodos(listaNodos);

    eliminarPrimerNodo(&listaNodos);
    printListaNodos(listaNodos);

    eliminarListaNodos(&listaNodos);
    printListaNodos(listaNodos);
}
*/


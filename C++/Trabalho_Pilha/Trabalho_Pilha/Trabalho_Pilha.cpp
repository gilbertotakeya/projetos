// Trabalho_Pilha.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

struct no{
    int info;
    struct no *prox;
};
typedef struct no Beco;

int cria(Beco **b){
    *b = NULL;
    return 0;
}

int Ins_Carro(Beco **b,int a){
    Beco *p = (Beco *) calloc (1,sizeof(Beco));
    p->info = a;
    p->prox = *b;
    *b = p;
    return 0;
}

int Rem_Carro(Beco **aux, Beco **b,int l){
    Beco *p, *q = *b;
    while(q->info != l){
                  Beco *d = (Beco *) calloc (1,sizeof(Beco));
                  d->info = q->info;
                  d->prox = *aux;
                  *aux = d;
                  q = q->prox;                  
    }
    while((*b)->info != l){
                  q = *b;
                  *b = (*b)->prox;
                  free(q);
    }
    q = *b;
    *b = (*b)->prox;
    free(q);
    p = *aux;
    while(p != NULL){ 
            Beco *d = (Beco *) calloc(1,sizeof(Beco));
            d->info = p->info;
            d->prox = *b;
            *b = d;
            p = p->prox;
    }
    while((*aux) != NULL){
            p = *aux;
            (*aux) = (*aux)->prox;
            free(p);
    }
	return 0;
}
    
                  
int Listar(Beco *b){
    
    while(b!=NULL){
        printf("Carro %d->",b->info);
        b = b->prox;
    }
    printf("NULL \n"); 
    system("pause");
	return 0;
}
int _tmain(int argc, _TCHAR* argv[])
{
    Beco *a,*b;
    int op,var = 0;
	int rem;
    cria(&b);
    cria(&a);

    do{
       system("cls");
        puts("----Menu----\n\n");
        puts("1 - Inserir carro.\n");
        puts("2 - Remover carro.\n");
        puts("3 - Listar carros.\n");
        puts("0 - Sair.\n");
        printf("Digite a opcao -->");
        scanf("%d",&op);
        switch(op){
            case 1: var++;
                    Ins_Carro(&b,var);
                    puts("Carro inserido com sucesso!\n");
                    system("pause");
                    break;
            case 2: printf("Digite o numero do carro que deseja remover: ");
                    scanf("%d", &rem);
                    Rem_Carro(&a,&b,rem);
                    puts("Carro removido com sucesso!\n");
                    system("pause");
                    break;

            case 3:Listar(b);
                   break;
        }
    }while(op!=0);
}

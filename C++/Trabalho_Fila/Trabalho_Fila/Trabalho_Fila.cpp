// Trabalho_Fila.cpp : Defines the entry point for the console application.
//
#include "stdafx.h"
#include "stdio.h"
#include "stdlib.h"
#include "conio.h"
#include "string.h"

struct no{
    struct no *esq;
    char pessoa;
    int numero;
    struct no *dir;
};
typedef struct no Fila;

void cria(Fila **Inicio,Fila **Fim)
{
    *Inicio = NULL;
    *Fim = NULL;
}

void Fila_Vazia(Fila *Inicio, Fila *Fim)
{
    if(Inicio == NULL && Fim == NULL)
        puts("Lista vazia!");
    else
        puts("Lista não vazia!");
}

void Ins_Fila(Fila **Inicio, Fila **Fim, char q, int n)
{
	Fila * p = (Fila *)calloc (1, sizeof(Fila)) ;
	p->numero = n;
	p->pessoa = q;
	if( ( p->pessoa == 'a' ) || ( p->pessoa == 'A' ) )
	{
		p->esq = *Fim;
		p->dir = NULL;
		if (*Inicio==NULL)
		   *Inicio =p;
		else
		   (*Fim)->dir=p;
		 *Fim = p;
	}
	else if( ( p->pessoa == 'e' ) || ( p->pessoa == 'E' ) )
	{
		p->esq = NULL;
		p->dir = NULL;
		if (*Inicio==NULL)
		{
		   *Fim = p;
		   *Inicio = p;
		}
		else if ((*Inicio)->pessoa == 'A')
		{     
			  p->dir = *Inicio;
			  (*Inicio)->esq = p;
			  *Inicio = p;
		}
		else if ((*Fim)->pessoa == 'E')
		{
				(*Fim)->dir=p;
				p->esq = *Fim; 
				*Fim= p;	                         
		}
		else
		{
			Fila *f;
			f = *Inicio;      
			while( (f->pessoa == 'E') || (f->pessoa == 'e') )
			{
				f = f->dir;
			}

		Fila *e = f->esq;
		p->esq	= e;
		p->dir	= e->dir;
		e->dir	= p;
		f->esq	= p;      
		}  
	}
}
void Rem_Fila(Fila **Inicio)
{
    Fila *i = *Inicio;
    *Inicio = i->dir;
    (*Inicio)->esq = NULL;
    free(i);
}

void Listar(Fila *Inicio, Fila *Fim)
{
    printf("Inicio ->");
    while(Inicio != NULL)
    {
        printf("<- %c%d ->",Inicio->pessoa,Inicio->numero);
        Inicio = Inicio->dir;
    }
    printf("<- Fim\n");
    system("pause");
}

int main()
{
    Fila *Inicio;
    Fila *Fim;
    char pessoa,op;
    int numa = 0;
    int nume = 0;

    cria(&Inicio,&Fim);
    do
    {
       system("cls");
        puts("   *----Menu----*   \n\n");
        puts("      1 - Retirar senha.\n");
        puts("      2 - Remover pessoa da fila.\n");
        puts("      3 - Listar pessoas da fila.\n");
        puts("      0 - Sair.\n");
        printf("Digite a opcao -->");
        fflush(stdin);
		op = getchar();
        switch(op)
        {
            case '1':
			{
					printf("Digite o tipo de pessoa(A,E):");
                    fflush(stdin);
                    pessoa = getchar();
                    strupr(&pessoa);
                    if(pessoa == 'A')
                    {
                        numa++;
                        Ins_Fila(&Inicio, &Fim, pessoa, numa);
                    }
                    else
                    {
                        nume++;
                        Ins_Fila(&Inicio, &Fim, pessoa, nume);
                    }
                    puts("\nSenha retirada com sucesso!\n");
                    system("pause");
                    break;
			}
            case '2':
			{
					Rem_Fila(&Inicio);
                    puts("Pessoa atendida!");
                    system("pause");
                    break;
			}
            case '3':
			{
					Listar(Inicio,Fim);
                    break;
			}
        }
    }while(op != '0');
}

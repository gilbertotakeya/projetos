// Hashing_Otimizado.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "stdlib.h"
struct Valores_Lista_Hashing
{
	int ID;
	struct Valores_Lista_Hashing * Proximo;
};
struct Lista_Hashing
{
	int ID;
	struct Valores_Lista_Hashing  * Valores_Hashing;
	struct Lista_Hashing * Proximo;
};

typedef Lista_Hashing LHash;
typedef Valores_Lista_Hashing ValHash;

int calculo_hash = 0;

void Inserir_Listas( LHash ** Inicio, LHash ** Fim, int ID )
{
	LHash * LLista = (LHash *) calloc(1, sizeof(LHash));
	LLista->ID				= ID;
	LLista->Proximo			= NULL;
	LLista->Valores_Hashing	= NULL;

	if (*Inicio==NULL)
	     *Inicio=LLista;
	else
		(*Fim)->Proximo=LLista;
	
    *Fim = LLista;
}
void Inserir_ValorHashing( ValHash ** Lista, int ID )
{
	ValHash * LLista = (ValHash *) calloc(1, sizeof(ValHash));
	LLista->ID				= ID;
	LLista->Proximo			= NULL;

	if( (*Lista) == NULL )
	{
		(*Lista) = LLista;
	}
	else
		Inserir_ValorHashing( &(*Lista)->Proximo, ID );
}
void Inserir_Valores( LHash ** InicioVert, LHash ** FimVert, int IDAresta, int IDVertices )
{
	LHash * LLista = *InicioVert;
	while( LLista != NULL ) 
	{		
		if( LLista->ID == IDVertices )
		{
			ValHash * LHashing = LLista->Valores_Hashing;			
			Inserir_ValorHashing( &LHashing, IDAresta );
			LLista->Valores_Hashing = LHashing;
		}
		LLista = LLista->Proximo;
	}
//	*FimVert = LLista;
}
int  calcular_chave( int valor )
{
	return( valor % calculo_hash );
}
void Buscar_Valor( LHash * Lista, int ID, int Chave )
{
	int Achou = 0;
	while( ( Lista != NULL ) && ( Achou == 0 ) )
	{
		if( Lista->ID == Chave )
		{
			ValHash * Valores = Lista->Valores_Hashing;
			while( ( Valores != NULL ) && ( Achou == 0 ) )
			{
				if( Valores->ID == ID )
				{
					Achou = 1;
					printf("\nValor encontrado !Esta na lista: %i",Lista->ID);
				}
				Valores = Valores->Proximo;
			}
		}
		Lista= Lista->Proximo;
	}
	if( Achou == 0 )
		printf("\nValor não encontrado!");
}
int _tmain(int argc, _TCHAR* argv[])
{
	LHash * Inicio = NULL, * Fim = NULL;
	printf("\nQuantas listas serão criadas ?\n");
	scanf("%i",&calculo_hash);
	for( int i = 0 ; i < calculo_hash ; i++ )
	{
		Inserir_Listas( &Inicio, &Fim, i );
	}
	int op = 1, valor = 0;
	while( op != 0 )
	{
		valor = 0;
		system("cls");
		printf("\nC a r d a p i o  G e r a l ");
		printf("\n1 - Inserir valor");
		printf("\n2 - Buscar valor ");
		printf("\n3 - Listar tudo  ");
		printf("\n0 - Sair         ");
		printf("\nOpcao desejada : ");
		scanf("%i",&op);
		switch( op )
		{
			case 1:
			{
				printf("\nInforme o valor a ser inserido: ");
				scanf("%i",&valor);
				Inserir_Valores( &Inicio, &Fim, valor, calcular_chave( valor ) );
				system("pause");
				break;
			}
			case 2:
			{
				printf("\nInforme o valor a ser procurado: ");
				scanf("%i",&valor);
				Buscar_Valor( Inicio, valor, calcular_chave(valor) );
				system("pause");
				break;
			}
			case 3:
			{

				LHash * Lista = Inicio;
				while( Lista != NULL )
				{
					ValHash * Valores = Lista->Valores_Hashing;
					printf("\nVertice %i : ", Lista->ID);
					while( Valores != NULL )
					{
						printf("%i->",Valores->ID);
						Valores = Valores->Proximo;
					}
					printf("NULL");
					Lista = Lista->Proximo;
				}
				system("pause");
				break;
			}
		}
	}
	return 0;
}
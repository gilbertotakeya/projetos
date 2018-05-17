import java.util.*;
public class SistemaGerencial 
{
	// Atributos
	public static void main(String[] args) 
	{
		int opmenu = 0;	
		//Instanciei a classe que chama teclado e a chamo como se fosse um objeto.
		Scanner 		Teclado 	  	= new Scanner		( System.in );
		CCliente 		iCliente 		= new CCliente		(			);
		CFuncionario 	iFuncionario	= new CFuncionario	(			);
		CMenu 			iMenu 			= new CMenu			(			);
		
		while( opmenu != 5 )
		{
			iMenu.CMenu();
			opmenu = Teclado.nextInt();
			if( opmenu == 1 )
			{
				iCliente.CadastroFuncionario();
			}
			else if( opmenu == 2 )
			{
				iFuncionario.CadastroFuncionario();
			}
			else if( opmenu == 3 )
			{
				iCliente.apresentarFuncionario();
			}
			else if( opmenu == 4 )
			{
				iFuncionario.apresentarFuncionario();
			}			
		}
	} 
}
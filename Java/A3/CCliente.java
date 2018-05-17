import java.util.*;
public class CCliente 
{
	// Atributos
	public long   ID		= 0;
	public String Nome	 	= "";
	public String Endereco	= "";
	public String EstCiv 	= "";
	public String TipoCli 	= "";
	public char   Sexo	 	= 'F';
	
	Scanner 		Teclado 	  	= new Scanner		( System.in );	
	
	public void apresentarFuncionario()
	{
		System.out.println("Codigo...........:" + ID);
		System.out.println("Nome.............:" + Nome);
		System.out.println("Endereco.........:" + Endereco);
		System.out.println("Estado Civil.....:" + EstCiv);
		System.out.println("Sexo.............:" + Sexo);
		System.out.println("Tipo de Cliente..:" + TipoCli);
	}
	
	
	//Metodo
	public void CadastroFuncionario()
	{	
		ID = ID + 1;
		System.out.println("Informe o nome.............:" );	
		Nome = Teclado.nextLine();
		System.out.println("Informe o Endereco.........:" );
		Endereco = Teclado.nextLine();
		System.out.println("Informe o Estado Civil.....:" );
		EstCiv = Teclado.nextLine();		
		System.out.println("Informe o Tipo do Cliente..:" );
		TipoCli = Teclado.nextLine();

		
		System.out.println("Cadastrou!" );
	}
}

import java.util.*;
public class CFuncionario 
{
	// Atributos
	public long ID 			= 0;
	public String Nome	 	= "";
	public String Endereco	= "";
	public String Funcao 	= "";
	public double Salario	=  0;
	public String TipoFunc  = "C"; // C - Somente Cadastra  |  V- Somente Visualiza | M - Mista

	Scanner 		Teclado 	  	= new Scanner		( System.in );	
	public void apresentarFuncionario()
	{
		System.out.println("Código.....:" + ID);
		System.out.println("Nome.......:" + Nome);
		System.out.println("Endereco...:" + Endereco);
		System.out.println("Funcao.....:" + Funcao);
		System.out.println("Salario....:" + Salario);
		
		if( TipoFunc.equals("C") )
			System.out.println("Esse funcionario(a) somente cadastra");
		else if( TipoFunc.equals("V") )
			System.out.println("Esse funcionario(a) somente Visualiza");
		else if( TipoFunc.equals("M") )
			System.out.println("Esse funcionario(a) Cadastra e Visualiza");
		else
			System.out.println("Não há definição para esse funcionario...");
	}
	
	
	//Metodo
	public void CadastroFuncionario()
	{	
		ID = ID + 1;
		System.out.println("Informe o Nome.....:" );	
		Nome = Teclado.nextLine();
		System.out.println("Informe o Endereco.:" );
		Endereco = Teclado.nextLine();
		System.out.println("Informe o Funcao...:" );
		Funcao = Teclado.nextLine();
		System.out.println("Informe o Salario..:" );
		Salario = Teclado.nextDouble();

		System.out.println("Esse funcionario ele apenas..:" );
		System.out.println("C - Somente Cadastra  " );
		System.out.println("V - Somente Visualiza " );
		System.out.println("M - Ambos( C / V )    " );
		TipoFunc = Teclado.nextLine();

		System.out.println("Cadastrou!" );
	}
}

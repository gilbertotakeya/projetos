//Declaro que vou usar os componentes de tela
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
//Declaro que vou usar o componente de sql
import java.sql.*;
//Declaro que vou usar uma lista de array
import java.util.ArrayList;  
//Declaro que vou usar arrays
import java.util.Arrays;  
//Declaro que vou usar Coleções
import java.util.Collection;  
import java.util.Collections;  
//Declaro que vou usar Iterator
import java.util.Iterator;
//Declaro que vou usar listas
import javax.swing.JList;  

public class Main extends JFrame
{
	///////// D E C L A R A N D O    O B J E T O S    N A    T E L A
    JFrame      jfFrame   	= new JFrame(">>> Cadastro Cliente <<<");
    JButton     btInserir 	= new JButton("Inserir");
    JButton     btAlterar 	= new JButton("Alterar");	
    JButton     btExcluir 	= new JButton("Excluir");	
    JButton     btSalvar 	= new JButton("Salvar");	
    JButton     btCancelar 	= new JButton("Cancelar");		
    JTextField  TxtCod    	= new JTextField("");   
    JTextField  TxtNome   	= new JTextField("");
	JTextField  TxtIdade  	= new JTextField("");
	JLabel      lblCod      = new JLabel("Codigo");
	JLabel      lblNome     = new JLabel("Nome");
	JLabel      lblIdade    = new JLabel("Idade");
	DefaultListModel model  = new DefaultListModel();	
	JList 		Lista		= new JList(model);	
	JScrollPane MeuScroll;

	///////// D E C L A R A N D O   O B J E T O S   D E   C O N E X Õ E S
	Connection  Con;
	Statement   stmt;
	ResultSet 	Rs;
	///////// D E C L A R A N D O   V A R I A V E I S 
	String codigo = "", nome = "", idade = "";
	
	int IndiceLista 		= -1;
	int OperacaoSelecionada	= -1;
	
	public static void main( String[] args )
	{
		new Main();
	}
	public Main()
	{		
		// Instancio as ações ao clicar no botões - INCLUIR, ALTERAR, EXCLUIR
		AbtIncluir AcaoIncluir = new AbtIncluir();  AbtAlterar AcaoAlterar = new AbtAlterar();  AbtExcluir AcaoExcluir = new AbtExcluir();
		AbtSalvar AcaoSalvar   = new AbtSalvar();   AbtCancelar AcaoCancelar = new AbtCancelar();
		
		btInserir.addActionListener(AcaoIncluir);   btAlterar.addActionListener(AcaoAlterar);   btExcluir.addActionListener(AcaoExcluir);
		btSalvar.addActionListener(AcaoSalvar);   btCancelar.addActionListener(AcaoCancelar);
		// Determino a posição dos textos nos botões
        btInserir.setVerticalTextPosition(AbstractButton.CENTER); btAlterar.setVerticalTextPosition(AbstractButton.CENTER);
		btExcluir.setVerticalTextPosition(AbstractButton.CENTER); btSalvar.setVerticalTextPosition(AbstractButton.CENTER);
		btCancelar.setVerticalTextPosition(AbstractButton.CENTER);
					
        btInserir.setHorizontalTextPosition(AbstractButton.LEADING); btAlterar.setHorizontalTextPosition(AbstractButton.LEADING); 
		btExcluir.setHorizontalTextPosition(AbstractButton.LEADING); btSalvar.setHorizontalTextPosition(AbstractButton.LEADING);
		btCancelar.setHorizontalTextPosition(AbstractButton.LEADING);
		ClickLista();

		// Adiciono os botões na tela
		jfFrame.add( btInserir );		jfFrame.add( btAlterar );		jfFrame.add( btExcluir );
		jfFrame.add( btSalvar  );		jfFrame.add( btCancelar);
		// Adiciono a lista na tela
		MeuScroll = new JScrollPane(Lista);
		jfFrame.add(MeuScroll);
		//jfFrame.add(Lista);
		// Adiciono os componentes texto na tela
		jfFrame.add( TxtCod   );		jfFrame.add( TxtNome  ); 		jfFrame.add( TxtIdade ); 
		// Adiciono os labels
		jfFrame.add( lblCod ); 			jfFrame.add( lblNome );			jfFrame.add( lblIdade );
		// D E F I N I N D O   L A Y O U T				FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		jfFrame.setLayout(null);        
        jfFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Determino que a lista é de: Linhas visiveis, seleciono o indice no -1 e a seleção simples
		Lista.setVisibleRowCount(6);		Lista.setSelectedIndex(-1);  	Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		// Determino o tamanho e a posição da lista
		MeuScroll.setBounds(1,1,400,80);
		// Determino o tamanho e a posição dos labels
		lblCod.setBounds(1,90,100,20);		lblNome.setBounds(100,90,100,20);		lblIdade.setBounds(200,90,100,20);
		
		// Determino o tamanho e a posição dos campos Text
		TxtCod.setBounds(1,110,100,20);		TxtNome.setBounds(100,110,100,20);		TxtIdade.setBounds(200,110,100,20);
		
		// Determino o tamanho e a posição dos botões
		btInserir.setBounds(1,130,100,20);	btAlterar.setBounds(100,130,100,20);	btExcluir.setBounds(200,130,100,20);
		
		btSalvar.setBounds(100,130,100,20);	btCancelar.setBounds(200,130,100,20);
		// Determino os campos que serão invisiveis
		btSalvar.setVisible(false); btCancelar.setVisible(false);
		// Determino que os campos texts não serão editaveis
		TxtCod.setEditable(false);			TxtNome.setEditable(false); 			TxtIdade.setEditable(false);
		// Determino a posição da tela
        jfFrame.setSize(510,480);
		// Determino que a tela é visivel
        jfFrame.setVisible(true);		
		Lista.setVisible(true);
		AtualizarLista();
/*
===> Erro! 
JScrollPane scroller = new JScrollPane(Lista);
scroller.setPreferredSize(new Dimension(250, 80));
getContentPane().add(scroller);		
		
*/
	}	
	protected void IniciarConexao() throws Exception// Metodo que inicia a conexão
	{
		boolean IniciaConexao = true;
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Con = DriverManager.getConnection( "jdbc:odbc:teste","root","univem" );	
		stmt = Con.createStatement(); //Objeto que contem todos os metodos do sql
		
		
		
		
	}	
	protected boolean FecharConexao() // Metodo que fecha a conexão
	{
		boolean FechaConexao = true;
		try
		{
			stmt.close();
			Con.close();	
		}
		catch( Exception e )
		{
			JOptionPane.showMessageDialog( null, "Não foi possivel fechar as conexões! ", "A T E N Ç Ã O ", JOptionPane.ERROR_MESSAGE );
			FechaConexao = false;
		}
		return( FechaConexao ); 
	}
	protected void AtualizarLista()
	{		
		if( true )
		{
			// Cria os itens da lista
			model.clear();
			try
			{
			    IniciarConexao();
				System.out.println("Passo1");
				// -> "select * from cliente " <- Seleciona todos os campos da tabela cliente
				// -> "select nome, idade from cliente " <- Seleciona os campos nome,idade da tabela cliente
				// -> "select nome, idade from cliente " <- Seleciona os campos nome,idade da tabela cliente
				// -> "select * from cliente where idade >= 20 and idade <= 30" <- Seleciona tudo da tabela cliente se a idade for >= 20 e <= 30
				// -> "select * from cliente order by nome " <- Seleciona todos os campos da tabela cliente ordenando pelo nome
				// -> Select max(codigo) as maior from cliente <-;
				//    Rs.getInt("maior");
				String sql = "select * from cliente;";
				String str;
				ResultSet Rs   = stmt.executeQuery(sql);
				int i = 0;
				System.out.println("Passo2");
				int cod;
				while( Rs.next() )
				{
					System.out.println("Passo3");
					cod = Rs.getInt("codigo");
					if( cod != 0 )
					{
						System.out.println("Passo4");
						System.out.println(cod + " - " + Rs.getString("nome") + " - " + Rs.getInt("idade"));
						model.add( i, cod + " - " + Rs.getString("nome") + " - " + Rs.getInt("idade") );
						i++;
					}
				}			
			}
			catch( Exception e )
			{
				JOptionPane.showMessageDialog( null, "Erro ao atualizar a lista!" + e, "A T E N Ç Ã O ", JOptionPane.ERROR_MESSAGE );
			}
			FecharConexao();
		}
	}
    private class AbtIncluir implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane jpJanela = new JOptionPane();
            if( e.getSource() == btInserir )
            {
				CamposHabilitados( true );
				LimpaCamposTelas();
				MostraSC();
				OperacaoSelecionada = 0;
            }
        }
    }
    private class AbtAlterar implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane jpJanela = new JOptionPane();
            if( e.getSource() == btAlterar )
            {
				nome 	   = TxtCod.getText();
				String id  = TxtIdade.getText();			
				if( ( nome.equals("") ) && ( id.equals("") ) )
				{
					return;
				}
				CamposHabilitados( true );				
				MostraSC();	
				OperacaoSelecionada = 1;
            }
        }
    }
    private class AbtExcluir implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane jpJanela = new JOptionPane();
            if( e.getSource() == btExcluir )
            {
				OperacaoSelecionada = 2;
				OperacaoBD();
				AtualizarLista();
            }
        }
    }
    private class AbtSalvar implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane jpJanela = new JOptionPane();
            if( e.getSource() == btSalvar )
            {
				Salvar();
            }
        }
    }
    private class AbtCancelar implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane jpJanela = new JOptionPane();
            if( e.getSource() == btCancelar )
            {
				MostraIAE();
				CamposHabilitados(false);
            }
        }
    }
	protected void ClickLista()
	{
	    Lista.addMouseListener
		(
			new MouseAdapter()
			{
			  public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 1)
				{
					char caract1;
					int j = 0;
					codigo= ""; nome = ""; idade = "";
					
					// Pega o local que ta selecionado na lista --> int indice = Lista.locationToIndex(e.getPoint());
					String vString  = (String)( Lista.getSelectedValue() );					
					IndiceLista 	= Lista.locationToIndex( e.getPoint() );
					for( int i = 0 ; i < vString.length(); i++ )
					{
						caract1 = vString.charAt(i);
						if( caract1 == '-' )
						   j ++;
						else
						{
							if( j == 0 )
								codigo += caract1;
							else if( j == 1 )
								nome  += caract1;
							else
								idade += caract1;
						}
					}			
					codigo = codigo.replaceAll(" ", "");				
					idade  = idade.replaceAll(" ", "");
					MostrarValorNosCampos();					
				}
			  }
			} 
		);    	
	}
	protected void MostraIAE() // Mostra botões Incluir, Alterar e Excluir
	{
		btSalvar.setVisible(false); btCancelar.setVisible(false);
		btInserir.setVisible(true); btAlterar.setVisible(true); btExcluir.setVisible(true);	
	}
	protected void MostraSC() // Mostra botões Salvar e Cancelar
	{
		btSalvar.setVisible(true); btCancelar.setVisible(true);
		btInserir.setVisible(false); btAlterar.setVisible(false); btExcluir.setVisible(false);
	}
	protected void LimpaCamposTelas()
	{
		TxtCod.setText("");
		TxtNome.setText("");
		TxtIdade.setText("");				
	}
	protected void CamposHabilitados( boolean var )
	{
		TxtNome.setEditable(var); 
		TxtIdade.setEditable(var);
	}
	protected void MostrarValorNosCampos()
	{
		TxtCod.setText( codigo );
		TxtNome.setText( nome );
		TxtIdade.setText( idade );	
	}
	public void PegarMaiorCodigo()
	{
		String sql = "Select max(codigo) as maior from cliente";
		//            selecione o maior( campo tabela  ) sera chamado como maior da tabela cliente
		try
		{
			Rs = stmt.executeQuery(sql);
			Rs.next();
			int maior = Rs.getInt("maior");
			codigo = String.valueOf( maior + 1 );
			TxtCod.setText( codigo );
		}
		catch( Exception e )
		{
			JOptionPane.showMessageDialog( null, "Erro ao pegar o maior codigo =  "+ e , "A T E N Ç Ã O ", JOptionPane.ERROR_MESSAGE );	
			System.out.println("Erro: "+ e );
		}
	}	
	public void Salvar()
	{
		if( ValidarCampos() )
		{
			OperacaoBD();
			MostraIAE();
			CamposHabilitados(false);
			AtualizarLista();
			Lista.setSelectedIndex(0);
		}
	}
	public boolean ValidarCampos()
	{
		String auxNome = TxtNome.getText();
	
		if( auxNome.equals("") )
		{
			JOptionPane.showMessageDialog( null, "Informe o nome!" , "A T E N Ç Ã O ", JOptionPane.ERROR_MESSAGE );	
			TxtNome.setText("");
			return false;
		}		
		String auxId = TxtIdade.getText();
		auxId.replaceAll(" ","");
		if( auxId.equals("") )
		{
			JOptionPane.showMessageDialog( null, "Informe a idade!" , "A T E N Ç Ã O ", JOptionPane.ERROR_MESSAGE );	
			TxtIdade.setText("");
			return false;
		}
		
		if( FiltraCaracter( auxId ) == false )
		{
			JOptionPane.showMessageDialog( null, "Informe somente números!" , "A T E N Ç Ã O ", JOptionPane.ERROR_MESSAGE );	
			TxtIdade.setText("");
			return false;
		}
		return true;
	}
	public void OperacaoBD()
	{
		try
		{
			IniciarConexao();
			String sql = "";
			if( OperacaoSelecionada == 0 )
				PegarMaiorCodigo();
				
			int codigo = Integer.parseInt( TxtCod.getText() );
			nome 	   = TxtNome.getText();
			int id     = Integer.parseInt( TxtIdade.getText() );
			if( OperacaoSelecionada == 0 ) // Incluir
			{
				sql = "insert into cliente values (" + codigo +", ' "+nome+" ',"+id+")";
//				sql = "insert into cliente (codigo,nome,idade) values (" + codigo +", ' "+nome+" ',"+id+")";
			}
			else if( OperacaoSelecionada == 1 ) // Alterar
			{
				sql = "update cliente set nome = ' " + nome +" ' , idade = "+ id +" where codigo = "+ codigo;
			}
			else if( OperacaoSelecionada == 2 ) // Excluir
				sql = "delete from cliente  where codigo = " + codigo;
			
			JOptionPane.showMessageDialog( null, sql , "A T E N Ç Ã O ", JOptionPane.ERROR_MESSAGE );	
			
			stmt.executeUpdate(sql);				
		}
		catch( Exception e )
		{
			JOptionPane.showMessageDialog( null, "Erro = "+ e, "A T E N Ç Ã O ", JOptionPane.ERROR_MESSAGE );			
		}
		FecharConexao();
	}
	public boolean FiltraCaracter(String vString)
	{
		int tam = vString.length();
		boolean achou = true;		
		String vOriginal= vString;
		String Alfabeto = "";
		char   caract1 ;
		vString = vString.toUpperCase();		
		Alfabeto = "0123456789";	
		for(int i = 0 ; ( ( i < tam ) && ( achou ) ) ; i++)
		{
			caract1 = vString.charAt(i);
			if( Alfabeto.indexOf(caract1) == -1 )
				achou = false;			
		}		
		return( achou ); // Se achar caracter retorna False senao retorna true
	}	
}

/*

Conversions

// int to String
int x = 123;
String y = Integer.toString(x);  // y is "123"

// String to int
y = "456"; 
x = Integer.parseInt(y);   // x is 456

// double to int
double z = 3.5;
x = (int) z;   // x is 3  (truncates decimal)


- Tipos de MessageBox
	*  DEFAULT_OPTION
    * YES_NO_OPTION
    * YES_NO_CANCEL_OPTION
    * OK_CANCEL_OPTION 
    * ERROR_MESSAGE
    * INFORMATION_MESSAGE
    * WARNING_MESSAGE
    * QUESTION_MESSAGE
    * PLAIN_MESSAGE 	
*/
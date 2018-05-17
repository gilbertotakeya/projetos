package com.br.representanetmobile;

import com.br.representanetmobileclasses.cDadosUsuarioLogado;
import com.br.representanetmobileclasses.cPedidoAtual;
import com.br.representanetmobileclasses.cPedidoItem;
import com.br.representanetmobileclasses.cPedidoItemSelecionado;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

public class pedidocabecalho extends Activity 
{
	Spinner cmbCliente, cmbVendedor, cmbPreposto,cmbTabelaPreco,cmbTipoCobranca,cmbFGFrete;
	EditText dataPedido;
	private TextView nroPedido,nroPedidoCliente;
		
    public void onCreate(Bundle savedInstanceState) 
    {    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidocabecalho);
        
        vincularObjetosTela();
        
        inicializacaoTela();
        
        carregarTabelaPreco();
        carregarFreteBanco();
        carregarTipoCobrancaBanco();
        carregarVendedor();
        carregarPreposto();
        carregarCliente();

		cmbCliente.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long posicao)
            {
            	cPedidoAtual.id_Cliente = (int)posicao;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
		
		cmbVendedor.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long posicao)
            {            	
            	cPedidoAtual.id_Vendedor = (int)posicao;
            	cDadosUsuarioLogado.id_Vendedor = cPedidoAtual.id_Vendedor;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
		
		cmbPreposto.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long posicao)
            {                
            	cPedidoAtual.id_Preposto = (int)posicao;
            	cDadosUsuarioLogado.id_Preposto = cPedidoAtual.id_Preposto;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        
		cmbTabelaPreco.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long posicao)
            {                
                setarTabelaSelecionada(posicao);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
		
		cmbFGFrete.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long posicao)
            {                
            	setarFreteSelecionado(posicao);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

		cmbTipoCobranca.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long posicao)
            {                
            	setarTipoCobrancaSelecionado(posicao);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
			
		carregarPedidoItensBanco();
		
		dataPedido.setOnFocusChangeListener(new OnFocusChangeListener()
		{
	        public void onFocusChange(View v, boolean hasFocus) 
	        {
	            if(!hasFocus)
	            {
	            	cPedidoAtual.dt_Pedido = Util.converterStringData(dataPedido.getText().toString());
	            	dataPedido.setText(Util.converterDataStringTela(cPedidoAtual.dt_Pedido));
	            }
	        }
	    });
    }
    
    private void setarDadosPedido()
    {
		((TextView)findViewById(R.id.tbDSFrete)).setText(cPedidoAtual.ds_Frete);		
		((TextView)findViewById(R.id.tbDSPagamento)).setText(cPedidoAtual.ds_Pagamento);

		String tempDataPedido = Util.converterDataStringTela(cPedidoAtual.dt_Pedido);
		dataPedido.setText(tempDataPedido);
						
		nroPedido.setText(String.valueOf(cPedidoAtual.nr_Pedido));
		nroPedidoCliente.setText(cPedidoAtual.nr_Pedido_Cliente);
    }
    
	private void vincularObjetosTela()
	{
		nroPedido = (EditText)findViewById(R.id.tbNumeroPedido);
		nroPedidoCliente = (EditText)findViewById(R.id.tbNumeroPedidoCliente);
		
		cmbTabelaPreco = (Spinner)findViewById(R.id.cmbTabelaPreco);
		cmbFGFrete= (Spinner)findViewById(R.id.cmbFGFrete);
		cmbTipoCobranca= (Spinner)findViewById(R.id.cmbTipoCobranca);
		cmbVendedor= (Spinner)findViewById(R.id.pedidocabecalhocmbvendedor);
		cmbPreposto= (Spinner)findViewById(R.id.pedidocabecalhocmbPreposto);
		cmbCliente= (Spinner)findViewById(R.id.pedidocabecalhocmbcliente);		
		
		dataPedido= (EditText)findViewById(R.id.dtPedido);
	}
    
	private void inicializacaoTela()
	{
		try
		{
			if (cPedidoAtual.id_Pedido <= 0)
			{
				String sql = "SELECT REPRESENTANTE.*,ROWID as _id FROM REPRESENTANTE WHERE ID_REPRESENTANTE = " + cDadosUsuarioLogado.id_Representante;
				Cursor cursorTemp = abrirBanco().rawQuery(sql, null);
				while (cursorTemp.moveToNext())
				{
					cDadosUsuarioLogado.ultimo_nro_pedido = cursorTemp.getInt(cursorTemp.getColumnIndex("ULTIMONUMERO"));
				}
				
				cursorTemp.close();
				
				nroPedido.setText(String.valueOf(cDadosUsuarioLogado.ultimo_nro_pedido + 1));
				dataPedido.setText(Util.converterDataStringTela(Util.dataHoraAtual()));			
			}
			else
			{
				setarDadosPedido();
			}
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Inicializar Tela Pedido", ex.getMessage(), pedidocabecalho.this);
		}
	}
	
	private SQLiteDatabase abrirBanco()
	{
		return  openOrCreateDatabase(Util.nomeBanco, MODE_WORLD_READABLE, null);
	}
	
	private void carregarVendedor()
	{		
		SQLiteDatabase BancoDados = abrirBanco();
		String sql = "SELECT  '<- SELECIONE ->' as NM_VENDEDOR,\n" +
		        	 "							-1 as _id\n" +
		        	 "UNION\n"+			                
					 "SELECT 	VENDEDOR.NM_VENDEDOR,\n" + 
					 "			VENDEDOR.ID_VENDEDOR AS _id\n" +
					 "FROM 		VENDEDOR\n" +
					 "WHERE		VENDEDOR.ID_REPRESENTANTE = "+ cDadosUsuarioLogado.id_Representante + "\n" +
					 "ORDER BY 1";
			
		Cursor cursor = BancoDados.rawQuery(sql, null);
		try
		{
			String[] coluna = new String[]{"NM_VENDEDOR"};
			
			int[] campoDestino = new int[] {android.R.id.text1};
					
			if (cursor.getCount() > 0)
			{
				//TODO: Montar o combobox do android. O Android possui um próprio layout para o spinner.
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, coluna, campoDestino);
				AdapterLista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);				
				cmbVendedor.setAdapter(AdapterLista);
							
				for(int pos = AdapterLista.getCount(); pos >= 0; pos--) 
				{
					  if(cPedidoAtual.id_Vendedor > 0 && AdapterLista.getItemId(pos) == cPedidoAtual.id_Vendedor) 
					  {
						  cmbVendedor.setSelection(pos);
						  break;
					  }
					  else if (AdapterLista.getItemId(pos) == -1)
					  {
						  cmbVendedor.setSelection(pos);
						  break;
					  }
				}
			}
			else
				Util.mensagemAlerta("Carregar Vendedor", "Sem tabelas de preço cadastradas!", pedidocabecalho.this);			
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Carregar Vendedor", "Erro: " + ex.getMessage(), pedidocabecalho.this);
		}
		BancoDados.close();
	}

	private void carregarPreposto()
	{		
		SQLiteDatabase BancoDados = abrirBanco();
		String sql = "SELECT  '<- SELECIONE ->' as NM_PREPOSTO,\n" +
		        	 "							-1 as _id\n" +
		        	 "UNION\n"+			                
					 "SELECT 	PREPOSTO.NM_PREPOSTO,\n" + 
					 "			PREPOSTO.ID_PREPOSTO AS _id\n" +
					 "FROM 		PREPOSTO\n" +
					 "WHERE		PREPOSTO.ID_REPRESENTANTE = "+ cDadosUsuarioLogado.id_Representante + "\n" +
					 "ORDER BY 1";
			
		Cursor cursor = BancoDados.rawQuery(sql, null);
		try
		{
			String[] coluna = new String[]{"NM_PREPOSTO"};
			
			int[] campoDestino = new int[] {android.R.id.text1};
					
			if (cursor.getCount() > 0)
			{
				//TODO: Montar o combobox do android. O Android possui um próprio layout para o spinner.
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, coluna, campoDestino);
				AdapterLista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);				
				cmbPreposto.setAdapter(AdapterLista);
							
				for(int pos = AdapterLista.getCount(); pos >= 0; pos--) 
				{
					  if(cPedidoAtual.id_Preposto > 0 && AdapterLista.getItemId(pos) == cPedidoAtual.id_Preposto) 
					  {
						  cmbPreposto.setSelection(pos);
						  break;
					  }
					  else if (AdapterLista.getItemId(pos) == -1)
					  {
						  cmbPreposto.setSelection(pos);
						  break;
					  }
				}
			}
			else
				Util.mensagemAlerta("Carregar Preposto", "Sem tabela de preposto cadastradas!", pedidocabecalho.this);			
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Carregar Preposto", "Erro: " + ex.getMessage(), pedidocabecalho.this);
		}
		BancoDados.close();
	}

	private void carregarCliente()
	{		
		SQLiteDatabase BancoDados = abrirBanco();
		String sql = "SELECT 	CLIENTE.NM_CLIENTE,\n" + 
					 "			CLIENTE.ID_CLIENTE AS _id\n" +
					 "FROM 		CLIENTE\n" +
					 "WHERE		CLIENTE.ID_REPRESENTANTE = "+ cDadosUsuarioLogado.id_Representante + "\n" +
					 "ORDER BY  CLIENTE.NM_CLIENTE";
			
		Cursor cursor = BancoDados.rawQuery(sql, null);
		try
		{
			String[] coluna = new String[]{"NM_CLIENTE"};
			
			int[] campoDestino = new int[] {android.R.id.text1};
					
			if (cursor.getCount() > 0)
			{
				//TODO: Montar o combobox do android. O Android possui um próprio layout para o spinner.
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, coluna, campoDestino);
				AdapterLista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);				
				cmbCliente.setAdapter(AdapterLista);
							
				for(int pos = AdapterLista.getCount(); pos >= 0; pos--) 
				{
					  if(cPedidoAtual.id_Cliente > 0 && AdapterLista.getItemId(pos) == cPedidoAtual.id_Cliente) 
					  {
						  cmbCliente.setSelection(pos);
						  break;
					  }					  
				}
			}
			else
				Util.mensagemAlerta("Carregar Cliente", "Sem tabela de cliente cadastradas!", pedidocabecalho.this);			
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Carregar Cliente", "Erro: " + ex.getMessage(), pedidocabecalho.this);
		}
		BancoDados.close();
	}
	
	private void carregarTabelaPreco()
	{		
		SQLiteDatabase BancoDados = abrirBanco();
		String sql = "SELECT  '<- SELECIONE ->' as DESCRICAO,\n" +
		        	 "							-1 as _id\n" +
		        	 "UNION\n"+			                
					 "SELECT 	TABELAPRECO.DESCRICAO,\n" + 
					 "			TABELAPRECO.IDTABELA AS _id\n" +
					 "FROM 		TABELAPRECO\n" +
					 "WHERE		TABELAPRECO.IDREPRESENTADA = "+ cDadosUsuarioLogado.id_Representada + "\n" +
					 "  AND		TABELAPRECO.IDREPRESENTANTE = "+ cDadosUsuarioLogado.id_Representante + "\n";
			
		Cursor cursor = BancoDados.rawQuery(sql, null);
		try
		{
			String[] coluna = new String[]{"DESCRICAO"};
			
			int[] campoDestino = new int[] {android.R.id.text1};
					
			if (cursor.getCount() > 0)
			{
				//TODO: Montar o combobox do android. O Android possui um próprio layout para o spinner.
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, coluna, campoDestino);
				AdapterLista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);				
				cmbTabelaPreco.setAdapter(AdapterLista);
							
				for(int pos = AdapterLista.getCount(); pos >= 0; pos--) 
				{
					  if(cPedidoAtual.id_Tabela_Preco > 0 && AdapterLista.getItemId(pos) == cPedidoAtual.id_Tabela_Preco) 
					  {
						  cmbTabelaPreco.setSelection(pos);
						  break;
					  }
					  else if (AdapterLista.getItemId(pos) == -1)
					  {
						  cmbTabelaPreco.setSelection(pos);
						  break;
					  }
				}
			}
			else
				Util.mensagemAlerta("Carregar Tabela de preço", "Sem tabelas de preço cadastradas!", pedidocabecalho.this);			
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Carregar Tabela de preço", "Erro: " + ex.getMessage(), pedidocabecalho.this);
		}
		BancoDados.close();
	}
	
	private void setarTabelaSelecionada(long posicao)
	{
		try
		{
        	cPedidoAtual.id_Tabela_Preco = (int)posicao;
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Setar Tabela Selecionada", "erro: " + ex.getMessage(), pedidocabecalho.this);
		}		
	}	

	private void carregarPedidoItensBanco()
	{			
		Cursor cursor = null;
		SQLiteDatabase BancoDados = null;
		try
		{
			BancoDados = abrirBanco();
			
			int idRepresentante = cPedidoAtual.id_Pedido > 0 ? cPedidoAtual.id_Representante : cDadosUsuarioLogado.id_Representante;
			int idRepresentada = cPedidoAtual.id_Pedido > 0 ? cPedidoAtual.id_Representada : cDadosUsuarioLogado.id_Representada;
			
			
			String sql = "SELECT ITEM_PEDIDO.*,\n" + 
						 "		 PRODUTO.DS_PRODUTO,\n" + 
						 "		 ITEM_PEDIDO.ID AS _id\n" +
						 "FROM ITEM_PEDIDO\n" +
						 "INNER JOIN PRODUTO ON PRODUTO.CD_PRODUTO = ITEM_PEDIDO.CD_PRODUTO\n" +
						 "WHERE ITEM_PEDIDO.ID_PEDIDO = " + cPedidoAtual.id_Pedido + "\n" + 
						 "  AND PRODUTO.FG_ATIVO = 'SIM'" +
						 "  AND PRODUTO.ID_REPRESENTANTE = " + idRepresentante + "\n" +
						 "  AND PRODUTO.ID_REPRESENTADA  = " + idRepresentada + "\n";
			
			cursor = BancoDados.rawQuery(sql, null);
			
			cPedidoAtual.itensVendidos = "";
			if (cursor.getCount() > 0)
			{
				while(cursor.moveToNext())
				{
					cPedidoItem n = new cPedidoItem();
					n.setarPedidoItem(cursor);
					cPedidoAtual.itensPedido.add(n);

	        		//remove a string, necessário em caso de alteração
	        		cPedidoAtual.itensVendidos += "_'" + n.cd_produto + "'-";					
				}
			}
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("carregarPedidoItensBanco", "erro: " + ex.getMessage(), pedidocabecalho.this);
			finish();
		}
		finally
		{
			cursor.close();
			BancoDados.close();			
		}
	}

	private void setarFreteSelecionado(long posicao)
	{
		try
		{
			if (posicao > 0)
			{
				SQLiteDatabase BancoDados = abrirBanco();
				String sql ="SELECT FRETE.DESCRICAO,\n" + 
						 	"		 FRETE.ID_FRETE AS _id\n" +
						 	"FROM FRETE\n";

				Cursor cursor = BancoDados.rawQuery(sql + " WHERE _id = " + String.valueOf(posicao), null);
				if (cursor.getCount() > 0)
				{
					while(cursor.moveToNext())
					{
						cPedidoAtual.fg_Frete = cursor.getString(cursor.getColumnIndex("DESCRICAO"));
					}
				}
			}
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Setar Frete Selecionado", "erro: " + ex.getMessage(), pedidocabecalho.this);
		}		
	}	

	private void setarFreteSelecionadoString(SimpleCursorAdapter AdapterLista)
	{
		Cursor cursor = null;
		SQLiteDatabase BancoDados = null;
		try
		{
			BancoDados = abrirBanco();
			String sql = "SELECT FRETE.DESCRICAO,\n" + 
						 "		 FRETE.ID_FRETE AS _id\n" +
						 "FROM FRETE\n" +
						 "WHERE FRETE.DESCRICAO = " + Util.apostrofo(cPedidoAtual.fg_Frete);

			cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				int ind = -1;
				//Busca o id na tabela
				while(cursor.moveToNext())
				{
					ind =cursor.getInt(cursor.getColumnIndex("_id"));
					break;
				}
				
				//Joga no combo
				for(int pos = AdapterLista.getCount(); pos >= 0; pos--) 
				{						
					if (AdapterLista.getItemId(pos) == ind)
					{
						cmbFGFrete.setSelection(pos);
						break;
					}
				}							
			}
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Setar Frete Selecionado", "erro: " + ex.getMessage(), pedidocabecalho.this);
		}	
		finally
		{
			//cursor.close();
			BancoDados.close();
		}
	}	

	private void carregarFreteBanco()
	{		
		Cursor cursor = null;
		SQLiteDatabase BancoDados = null;
		try
		{
			String[] coluna = new String[]{"DESCRICAO"};
			
			int[] campoDestino = new int[] {android.R.id.text1};
			
			BancoDados = abrirBanco();
			
			String sql = "SELECT FRETE.DESCRICAO,\n" + 
						 "		 FRETE.ID_FRETE AS _id\n" +
						 "FROM FRETE\n";
			
			cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				//TODO: Montar o combobox do android. O Android possui um próprio layout para o spinner.
				@SuppressWarnings("deprecation")
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, coluna, campoDestino);
				AdapterLista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);				
				cmbFGFrete.setAdapter(AdapterLista);

				if(cPedidoAtual.fg_Frete.length() > 0) 
				{
					setarFreteSelecionadoString(AdapterLista);
				}
				else
				{
					for(int pos = AdapterLista.getCount(); pos >= 0; pos--) 
					{
						if (AdapterLista.getItemId(pos) == -1)
						{
							cmbFGFrete.setSelection(pos);
							break;
						}
					}
				}
			}
			else
				Util.mensagemAlerta("Carregar Frete", "Sem frete cadastrado!", pedidocabecalho.this);			
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Carregar Frete", "erro: " + ex.getMessage(), pedidocabecalho.this);
			finish();
		}
		finally
		{
			//cursor.close();
			BancoDados.close();
		}
	}

	private void setarTipoCobrancaSelecionado(long posicao)
	{
		try
		{
			if (posicao > 0)
			{
				SQLiteDatabase BancoDados = abrirBanco();
				String sql ="SELECT TIPO_COBRANCA.DESCRICAO,\n" + 
						 	"		TIPO_COBRANCA.ID_TIPO_COBRANCA AS _id\n" +
						 	"FROM TIPO_COBRANCA\n";

				Cursor cursor = BancoDados.rawQuery(sql + " WHERE _id = " + String.valueOf(posicao), null);
				if (cursor.getCount() > 0)
				{
					while(cursor.moveToNext())
					{
						cPedidoAtual.tp_Cobranca = cursor.getString(cursor.getColumnIndex("DESCRICAO"));
					}
				}
			}
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Setar Tipo Cobrança Selecionado", "erro: " + ex.getMessage(), pedidocabecalho.this);
		}		
	}	

	private void setarTipoCobrancaSelecionadoString(SimpleCursorAdapter AdapterLista)
	{
		Cursor cursor = null;
		SQLiteDatabase BancoDados = null;
		try
		{
			BancoDados = abrirBanco();
			String sql = "SELECT TIPO_COBRANCA.DESCRICAO,\n" + 
						 "		 TIPO_COBRANCA.ID_TIPO_COBRANCA AS _id\n" +
						 "FROM TIPO_COBRANCA\n" +
						 "WHERE TIPO_COBRANCA.DESCRICAO = " + Util.apostrofo(cPedidoAtual.tp_Cobranca);

			cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				int ind = -1;
				//Busca o id na tabela
				while(cursor.moveToNext())
				{
					ind =cursor.getInt(cursor.getColumnIndex("_id"));
					break;
				}
				
				//Joga no combo
				for(int pos = AdapterLista.getCount(); pos >= 0; pos--) 
				{						
					if (AdapterLista.getItemId(pos) == ind)
					{
						cmbTipoCobranca.setSelection(pos);
						break;
					}
				}							
			}
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Setar Tipo Cobrança Selecionado", "erro: " + ex.getMessage(), pedidocabecalho.this);
		}	
		finally
		{
			//cursor.close();
			BancoDados.close();
		}
	}	

	private void carregarTipoCobrancaBanco()
	{		
		Cursor cursor = null;
		SQLiteDatabase BancoDados = null;
		try
		{
			String[] coluna = new String[]{"DESCRICAO"};
			
			int[] campoDestino = new int[] {android.R.id.text1};
			
			BancoDados = abrirBanco();
			
			String sql = "SELECT TIPO_COBRANCA.DESCRICAO,\n" + 
						 "		 TIPO_COBRANCA.ID_TIPO_COBRANCA AS _id\n" +
						 "FROM TIPO_COBRANCA\n";
			
			cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				//TODO: Montar o combobox do android. O Android possui um próprio layout para o spinner.
				@SuppressWarnings("deprecation")
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, coluna, campoDestino);
				AdapterLista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);				
				cmbTipoCobranca.setAdapter(AdapterLista);

				if(cPedidoAtual.tp_Cobranca.length() > 0) 
				{
					setarTipoCobrancaSelecionadoString(AdapterLista);
				}
			}
			else
				Util.mensagemAlerta("Carregar Tipo Cobrança", "Sem tipo cobrança cadastrado!", pedidocabecalho.this);			
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Carregar Tipo Cobrança", "erro: " + ex.getMessage(), pedidocabecalho.this);
			finish();
		}
		finally
		{
			//cursor.close();
			BancoDados.close();
		}
	}
	
    @Override
    protected void onResume()
    {
    	super.onResume();
    	
    	if (cPedidoAtual.itensPedido.size() > 0)
    		cmbTabelaPreco.setEnabled(false);
    	else
    		cmbTabelaPreco.setEnabled(true);    		
    }    

    @Override
    public void onPause()
    {
		super.onPause();
		setarDadosObjetosPedido();
    }
    
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sair?");
        builder.setMessage("Deseja realmente sair?");        
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog alerta = builder.create();
        alerta.show();
    }
    
    private void setarDadosObjetosPedido()
    {
		cPedidoAtual.ds_Pagamento = ((TextView)findViewById(R.id.tbDSPagamento)).getText().toString();
		cPedidoAtual.ds_Frete = ((TextView)findViewById(R.id.tbDSFrete)).getText().toString();
		
		String dataPedido = ((TextView)findViewById(R.id.dtPedido)).getText().toString();
		
		cPedidoAtual.dt_Assistencia = null;
		cPedidoAtual.dt_Entrega = null;
		cPedidoAtual.dt_Envio_Cliente = null;
		cPedidoAtual.dt_Envio_Representada = null;
		cPedidoAtual.dt_Pedido = Util.converterStringData(dataPedido);
		cPedidoAtual.fg_Frete = ((TextView)cmbFGFrete.getSelectedView()).getText().toString();
		cPedidoAtual.nr_Pedido=Util.intNull(((TextView)findViewById(R.id.tbNumeroPedido)).getText().toString());
		cPedidoAtual.nr_Pedido_Cliente =((TextView)findViewById(R.id.tbNumeroPedidoCliente)).getText().toString();
		cPedidoAtual.tp_Cobranca = ((TextView)cmbTipoCobranca.getSelectedView()).getText().toString();    	
    }
}
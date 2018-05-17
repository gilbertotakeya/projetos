package com.br.representanetmobile;

import com.br.representanetmobileclasses.cDadosUsuarioLogado;
import com.br.representanetmobileclasses.cPedidoAtual;
import com.br.representanetmobileclasses.cPedidoItem;
import com.br.representanetmobileclasses.cPedidoItemSelecionado;
import com.br.representanetmobileclasses.cProdutoSelecionado;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;


public class Selecaoproduto extends Activity 
{
	EditText tbPesquisa;

	String sqlPadraoBuscarProdutos ="SELECT PRODUTO.ID_REPRESENTANTE,\n" +
									"       PRODUTO.ID_REPRESENTADA,\n" + 
									"       PRODUTO.CD_PRODUTO,\n" + 
									"       PRODUTO.DS_PRODUTO,\n" + 
									"       PRODUTO.DT_HORA_IMPORTACAO,\n" + 
									"       PRODUTO.ID_PRODUTO,\n" + 
									"       PRODUTO.ID_PRODUTO as _id,\n" + 
									"       PRODUTOTABELADO.IDTABELA,\n" + 
									"       PRODUTOTABELADO.VRPRODUTO\n" + 
									"FROM  PRODUTO\n" + 
									"INNER JOIN PRODUTOTABELADO ON PRODUTOTABELADO.IDREPRESENTANTE = PRODUTO.ID_REPRESENTANTE\n" + 
									"                          AND PRODUTOTABELADO.IDREPRESENTADA    = PRODUTO.ID_REPRESENTADA\n" + 
									"                          AND PRODUTOTABELADO.CDPRODUTO         = PRODUTO.CD_PRODUTO\n" + 
									"WHERE PRODUTO.FG_ATIVO = 'SIM'\n" +
									"  AND PRODUTOTABELADO.VRPRODUTO > 0\n"+
									"  AND PRODUTOTABELADO.IDTABELA =";
	
	String sqlPadraoBuscarProdutosST="SELECT PRODUTO.ID_REPRESENTANTE,\n" +
									 "       PRODUTO.ID_REPRESENTADA,\n" + 
									 "       PRODUTO.CD_PRODUTO,\n" + 
									 "       PRODUTO.DS_PRODUTO,\n" + 
									 "       PRODUTO.DT_HORA_IMPORTACAO,\n" + 
									 "       PRODUTO.ID_PRODUTO,\n" + 
									 "       PRODUTO.ID_PRODUTO as _id,\n" + 
									 "       -1 AS IDTABELA,\n" + 
									 "       0.00 AS VRPRODUTO\n" + 
									 "FROM  PRODUTO\n" + 
									 "WHERE PRODUTO.FG_ATIVO = 'SIM'\n";
	
	ListView mostrarDados;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecaoproduto);
        
        tbPesquisa   = (EditText)findViewById(R.id.selecaoprodutotbdescricao);
        
        mostrarDados = (ListView)findViewById(R.id.selecaoprodutolista);
        
		mostrarDados.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long posicao) 
			{
				// TODO Seta o cliente clicado na lista
				setarProdutoSelecionado(posicao);
			}
		});
		
		tbPesquisa.addTextChangedListener(new TextWatcher() {
			  
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
                  
            }  
  
            public void onTextChanged(CharSequence s, int start, int before, int count) {  
            	carregarListaProdutos();
            }  
  
            @Override  
            public void afterTextChanged(Editable arg0) {  
            }  
        });   
	}
	
	private SQLiteDatabase abrirBanco()
	{
		return  openOrCreateDatabase(Util.nomeBanco, MODE_WORLD_READABLE, null);
	}
	
	private void carregarListaProdutos()
	{			
		String[] coluna = new String[]{"CD_PRODUTO",
									   "DS_PRODUTO",
									   "VRPRODUTO"};
		
		int[] campoDestino = new int[] { R.id.viewselecaoprodutocodproduto, 
										 R.id.viewselecaoprodutodescproduto,
										 R.id.viewselecaoprodutopreco};

		try
		{
			SQLiteDatabase BancoDados = abrirBanco();
									
			String sqlProdutos = sqlPadraoBuscarProdutos + cPedidoAtual.id_Tabela_Preco;
						
			if (cPedidoAtual.id_Tabela_Preco <= 0)
				sqlProdutos = sqlPadraoBuscarProdutosST;
			
			int idRepresentante = cPedidoAtual.id_Pedido > 0 ? cPedidoAtual.id_Representante : cDadosUsuarioLogado.id_Representante;
			int idRepresentada = cPedidoAtual.id_Pedido > 0 ? cPedidoAtual.id_Representada : cDadosUsuarioLogado.id_Representada;

			
			sqlProdutos += 	" AND PRODUTO.ID_REPRESENTANTE = " + idRepresentante + "\n" +
							" AND PRODUTO.ID_REPRESENTADA  = " + idRepresentada + "\n";
			
			if (tbPesquisa.length() > 0)
				sqlProdutos += " AND (PRODUTO.CD_PRODUTO LIKE '%" + tbPesquisa.getText().toString() + "%' OR\n" +
							   "      PRODUTO.DS_PRODUTO LIKE '%" + tbPesquisa.getText().toString() + "%')\n";
			
			Cursor cursor = BancoDados.rawQuery(sqlProdutos, null);
			if (cursor.getCount() > 0)
			{
				//cursor.moveToFirst();
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, R.layout.viewselecaoproduto, cursor, coluna, campoDestino );
				mostrarDados.setAdapter(AdapterLista);
			}
			else
			{
				Util.mensagemAlerta("Produtos", "Sem produtos cadastrados para a tabela de preço selecionada!", Selecaoproduto.this);
				finish();
			}
			BancoDados.close();
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Produtos", "erro: " + ex.getMessage(), Selecaoproduto.this);
			finish();
		}
	}

	private void setarProdutoSelecionado(long posicao)
	{
		try
		{
			SQLiteDatabase BancoDados = abrirBanco();
			
			String sql = sqlPadraoBuscarProdutos + cPedidoAtual.id_Tabela_Preco + " AND _id = " + String.valueOf(posicao);
			
			if (cPedidoAtual.id_Tabela_Preco <= 0)
				sql = sqlPadraoBuscarProdutosST + " AND _id = " + String.valueOf(posicao);
			
			Cursor cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				while(cursor.moveToNext())
				{
					cPedidoItem novoItem = new cPedidoItem();
					novoItem.id_representante = cursor.getInt(cursor.getColumnIndex("ID_REPRESENTANTE"));
					novoItem.id_pedido= cPedidoAtual.id_Pedido;
					novoItem.cd_produto= cursor.getString(cursor.getColumnIndex("CD_PRODUTO"));
					novoItem.id_representada = cursor.getInt(cursor.getColumnIndex("ID_REPRESENTADA"));
					novoItem.vr_unitario= cursor.getDouble(cursor.getColumnIndex("VRPRODUTO"));
					novoItem.descricao= cursor.getString(cursor.getColumnIndex("DS_PRODUTO"));
					novoItem.ds_produto= cursor.getString(cursor.getColumnIndex("DS_PRODUTO"));

					cPedidoItemSelecionado.itemSelecionado = novoItem;
				}

				Intent i = new Intent(Selecaoproduto.this, Pedidoitensmanut.class);
							
				startActivity(i);
			}
			
			BancoDados.close();
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Falha ao setar produto selecionado!", "erro: " + ex.getMessage(), Selecaoproduto.this);
		}		
	}

	protected void onResume()
    {
    	super.onResume();
    	carregarListaProdutos();
    }
}
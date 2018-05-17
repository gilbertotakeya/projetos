package com.br.representanetmobile;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.br.representanetmobileclasses.cClienteSelecionado;
import com.br.representanetmobileclasses.cDadosUsuarioLogado;
import com.br.representanetmobileclasses.cPedidoAtual;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;


public class clientes extends Activity {
	Spinner cmbRepresentada;
	ListView mostrarDados;
	EditText tbPesquisa;
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);			
		setContentView(R.layout.clientes);	
						
		mostrarDados = (ListView)findViewById(R.id.listaclienteselecao);		
		mostrarDados.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long posicao) {
				// TODO Seta o cliente clicado na lista
				setarClienteSelecionado(posicao);
			}
		});
		
		cmbRepresentada = (Spinner)findViewById(R.id.clientesCmbRepresentada);
		tbPesquisa = (EditText)findViewById(R.id.clientesTbPesquisa);
		
		carregarRepresentada();
		cmbRepresentada.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long posicao)
            {                
            	cDadosUsuarioLogado.id_Representada = (int)posicao;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

		tbPesquisa.addTextChangedListener(new TextWatcher() {
			  
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
                  
            }  
  
            public void onTextChanged(CharSequence s, int start, int before, int count) {  
                carregarListaClientes();
            }  
  
            @Override  
            public void afterTextChanged(Editable arg0) {  
            }  
        });    		
		
		carregarListaClientes();
	}
	
	private SQLiteDatabase abrirBanco()
	{
		return  openOrCreateDatabase(Util.nomeBanco, MODE_WORLD_READABLE, null);
	}
	
	private void carregarRepresentada()
	{		
		try
		{
			String[] coluna = new String[]{"NM_REPRESENTADA"};
			
			int[] campoDestino = new int[] {android.R.id.text1};
			
			SQLiteDatabase BancoDados = abrirBanco();
			
			String sql = "SELECT REPRESENTADA.NM_REPRESENTADA,\n" +
						 "		 REPRESENTADA.ID_REPRESENTADA AS _id\n" +
						 "FROM REPRESENTADA\n" +
						 "WHERE REPRESENTADA.ID_REPRESENTANTE = " + cDadosUsuarioLogado.id_Representante + "\n" +
						 "ORDER BY 1";
			Cursor cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				//TODO: Montar o combobox do android. O Android possui um próprio layout para o spinner.
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, coluna, campoDestino);
				AdapterLista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				cmbRepresentada.setAdapter(AdapterLista);
			}
			else
				Util.mensagemAlerta("Representada", "Sem Representada cadastrada!\nCaso a situação persista, encerre o software e abra-o novamente!", clientes.this);
			
			BancoDados.close();
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Carregar Representada", "Erro: " + ex.getMessage(), clientes.this);
		}
	}	
	
	private void carregarListaClientes()
	{			
		String[] coluna = new String[]{"NM_CLIENTE",
									   "DS_ENDERECO",
									   "NM_CIDADE",
									   "SG_ESTADO",
									   "NR_TELEFONE", 
									   "NR_CELULAR"};
		
		int[] campoDestino = new int[] { R.id.viewclientenomcliente, 
										 R.id.viewclienteendereco,
										 R.id.viewclientemunicipio,
										 R.id.viewclienteuf,
										 R.id.viewclientetelefone, 
										 R.id.viewclientecelular};

		try
		{
			SQLiteDatabase BancoDados = abrirBanco();
			
			String sql = "SELECT CLIENTE.*,\n" +
						 "		 CLIENTE.ID AS _id\n" +
						 "FROM CLIENTE ";
			
			if (tbPesquisa.length() > 0)
				sql += " WHERE UPPER(CLIENTE.NM_CLIENTE) like '%" + tbPesquisa.getText().toString().toUpperCase() + "%'\n";
			
			sql += "ORDER BY CLIENTE.NM_CLIENTE";
			
			Cursor cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				cursor.moveToFirst();
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, R.layout.viewcliente, cursor, coluna, campoDestino );
				mostrarDados.setAdapter(AdapterLista);
			}
			else
				Util.mensagemAlerta("Clientes", "Sem clientes no banco", clientes.this);
			
			BancoDados.close();
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Clientes", "erro: " + ex.getMessage(), clientes.this);
		}
	}

	private void setarClienteSelecionado(long posicao)
	{
		try
		{
			SQLiteDatabase BancoDados = abrirBanco();
			Cursor cursor = BancoDados.rawQuery("SELECT CLIENTE.*,CLIENTE.ID AS _id FROM CLIENTE WHERE _id = " + String.valueOf(posicao), null);
			if (cursor.getCount() > 0)
			{
				while(cursor.moveToNext())
				{
					cClienteSelecionado.setarClienteSelecionado(cursor);
				}				
				
				cPedidoAtual.limparDadosPedido();
				cPedidoAtual.id_Cliente = cClienteSelecionado.ID_CLIENTE;
				
				
				Intent i = new Intent(clientes.this, Pedidos.class);
							
				startActivity(i);
			}
			else
				Util.mensagemAlerta("Clientes", "Sem clientes cadastrados para este usuário!", clientes.this);
			
			BancoDados.close();
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Clientes", "erro: " + ex.getMessage(), clientes.this);
		}		
	}

}

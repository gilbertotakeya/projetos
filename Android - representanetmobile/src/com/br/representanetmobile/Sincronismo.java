package com.br.representanetmobile;


import java.util.ArrayList;

import com.br.representanetmobileclasses.cDadosUsuarioLogado;
import com.br.representanetmobileclasses.cPedidoAtual;
import com.br.representanetmobileclasses.cPedidoItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Sincronismo extends Activity
{
	String usuario = "";
	String senha = "";
	String resultado = "";
	//ProgressDialog dialog;
	String retornoEnvioPedido = "";
	private ProgressDialog mprogressDialog;
	private Handler mhandler;
	Button btnSincronismo;
	TextView sincronismoLblTitulo;
	EditText tbStatusSincronismo;
	CheckBox ckRepresentada, ckRepresentante, ckPreposto, ckUsuario, ckVendedor, ckProduto, ckTabelaPreco, ckTabelaProduto;
	CheckBox ckCidades, ckUF, ckCliente, ckPedido;

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);			
		setContentView(R.layout.sincronismo);
		mhandler = new Handler();
		try
		{
			Intent intent = getIntent();
			  
			Bundle params = intent.getExtras();  
			   
			if(params!=null)
			{
				usuario = params.getString("usuario");
				senha = params.getString("senha");
			}
			else
			{
				usuario = cDadosUsuarioLogado.usuario_logado;
				senha	= cDadosUsuarioLogado.senha_logado;
			}
			
			sincronismoLblTitulo = (TextView)findViewById(R.id.sincronismoLblStatusSincronismo);
			tbStatusSincronismo = (EditText)findViewById(R.id.sincronismoTbStatus);
			btnSincronismo = (Button)findViewById(R.id.sincronismoBtnSincronizarDados);
			
			ckRepresentante = (CheckBox)findViewById(R.id.sincronismoCkRepresentante);
			ckRepresentada = (CheckBox)findViewById(R.id.sincronismoCkRepresentada);			
			ckPreposto = (CheckBox)findViewById(R.id.sincronismoCkPreposto);
			ckUsuario = (CheckBox)findViewById(R.id.sincronismoCkUsuario);
			ckVendedor = (CheckBox)findViewById(R.id.sincronismoCkVendedor);
			ckProduto = (CheckBox)findViewById(R.id.sincronismoCkProduto); 
			ckTabelaPreco = (CheckBox)findViewById(R.id.sincronismoCkTabelaPreco);
			ckTabelaProduto = (CheckBox)findViewById(R.id.sincronismoCkTabelaXProduto);
			ckCidades = (CheckBox)findViewById(R.id.sincronismoCkCidades);
			ckUF = (CheckBox)findViewById(R.id.sincronismoCkEstados);
			ckCliente = (CheckBox)findViewById(R.id.sincronismoCkClientes);
			ckPedido = (CheckBox)findViewById(R.id.sincronismoCkPedido);	
			
			btnSincronismo.setOnClickListener(new View.OnClickListener() 
			{		
				@Override
				public void onClick(View arg0) 
				{
					//cria o progress dialog
			        mprogressDialog = ProgressDialog.show(Sincronismo.this, "Aguarde", "Sincronizando tabelas...");
			 			         
			        new Thread() 
			        {
			            public void run() 
			            {								
							//dialog = ProgressDialog.show(Sincronismo.this, "", "Aguarde atualizando Representada...", true);
							if (ckRepresentante.isChecked())
							{						
								atualizarTabelaFull("REPRESENTANTE","retornarRepresentanteFull");
							}
							
							if (ckRepresentada.isChecked())
							{
								atualizarTabelaFull("REPRESENTADA","retornarRepresentadaFull");
							}
							
							if (ckPreposto.isChecked())
							{
								atualizarTabelaFull("PREPOSTO","retornarPrepostoFull");
							}
							
							if (ckUsuario.isChecked())
							{
								atualizarTabelaFull("USUARIO","retornarUsuarioFull");
							}
							if (ckVendedor.isChecked())
							{
								atualizarTabelaFull("VENDEDOR","retornarVendedorFull");
							}
							if (ckProduto.isChecked())
							{
								atualizarTabelaFull("PRODUTO","retornarProdutoFull");
							} 
							if (ckTabelaPreco.isChecked())
							{
								atualizarTabelaFull("TABELAPRECO","retornarTabelaPrecoFull");
							}
							if (ckTabelaProduto.isChecked())
							{
								atualizarTabelaFull("PRODUTOTABELADO","retornarProdutoTabeladoFull");
							}
							if (ckCidades.isChecked())
							{
								atualizarTabelaFull("CIDADES","retornarCidadesFull");
							}
							if (ckUF.isChecked())
							{
								atualizarTabelaFull("ESTADOS","retornarEstadosFull");
							}
							if (ckCliente.isChecked())
							{
								atualizarTabelaFull("CLIENTE","retornarClientesFull");
							}
							
							
							if (ckPedido.isChecked())
							{
								String ret = enviarPedido();
								if (ret.equalsIgnoreCase("OK"))
									retornoEnvioPedido = "";
								else
									retornoEnvioPedido = ret;									
							}
							
							
							//Exibe mensagem apenas informando o fim da execução da thread
			                mhandler.post(new Runnable() 
			                {
			                    public void run() 
			                    {
			                    	if (retornoEnvioPedido.length() == 0)
			                    		retornoEnvioPedido = "Sincronismo concluído com sucesso!";
			                    		
			                    	tbStatusSincronismo.setText(retornoEnvioPedido);
			                    	//Util.mensagemAlerta("Sincronismo!", retornoEnvioPedido, Sincronismo.this);
			                    }
			                }); 
							
							mprogressDialog.dismiss();
			            }
			        }.start();
				}
			});
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Carregar Tela de Sincronismo!", "Não foi possível carregar tela de sincronismo!\n" + ex.getMessage(), Sincronismo.this);
		}
	}

	private void atualizarTabelaFull(String tabela, String metodoWS)
	{
		//sincronismoLblTitulo.setText(tabela);
		SQLiteDatabase BancoDados = abrirBanco();
		try
		{			
			BancoDados.beginTransaction();
			//Atualização FULL!!! Apaga e carrega tudo
			BancoDados.execSQL("DELETE FROM " + tabela + ";");
						
			ArrayList<String> lista = UtilWebService.carregarDadosArrayList(usuario, senha, metodoWS);
			for (String v : lista)
			{
				incluir(v, BancoDados);
			}
			BancoDados.setTransactionSuccessful();
			BancoDados.endTransaction();			
		}
		catch(Exception ex)
		{
			tbStatusSincronismo.setText(tbStatusSincronismo.getText().toString() + "\nNão foi possível atualizar " + tabela + " "+ ex.getMessage());
		}
		finally
		{
			BancoDados.close();
		}		
	}

	private SQLiteDatabase abrirBanco()
	{
		return  openOrCreateDatabase(Util.nomeBanco, MODE_WORLD_READABLE, null);
	}
	
	private void incluir(String sqlString, SQLiteDatabase BancoDados)
	{		
		BancoDados.execSQL(sqlString);				
	}
	
	private String enviarPedido()
	{
		String retorno = "";
		SQLiteDatabase banco = abrirBanco();
		String sql = "SELECT * FROM PEDIDO\n";
		Cursor cursor = banco.rawQuery(sql, null);
		if (cursor.getCount() > 0)
		{
			cPedidoAtual.limparDadosPedido();
			
			while(cursor.moveToNext())
			{
				cPedidoAtual.setarPedido(cursor);
				
				String dataEntrega = cursor.getString(cursor.getColumnIndex("DT_ENTREGA"));				
				
				String dataAssistencia = cursor.getString(cursor.getColumnIndex("DT_ASSISTENCIA"));
				
				String sqlWSCab = Util.sqlCabecalho(dataEntrega, dataAssistencia, false);
				
				//sqlWSCab = sqlWSCab.replace("-1", "NULL");
				sqlWSCab = sqlWSCab.replace("'<- SELECIONE ->'", "NULL");
				
				String sqlItens = "SELECT ITEM_PEDIDO.*,\n"+
								 "		 ITEM_PEDIDO.ID AS _id\n" +
								 "FROM ITEM_PEDIDO\n"+
								 "WHERE ITEM_PEDIDO.ID_PEDIDO = " + cPedidoAtual.id_Pedido;
				
				String retornoPedido = "";
				Cursor cursorItem = banco.rawQuery(sqlItens, null); 
				if (cursorItem.getCount() > 0)
				{
					String sqlWSItens = ""; 
					
					try
					{
						while(cursorItem.moveToNext())
						{ 
							cPedidoItem n = new cPedidoItem();
							n.setarPedidoItem(cursorItem);
							sqlWSItens += "insert into item_pedido\n" +
					                "(id_representante,\n" + 
					                "id_item,\n" +                 
					                "cd_produto,\n" + 
					                //"ds_produto,\n"+
					                "id_representada,\n" + 
					                "ds_complemento,\n" + 
					                "qt_produto,\n" + 
					                "vr_unitario,\n" + 
					                "vr_total,\n" +
					                "id_pedido) values(\n"+ 
									n.id_representante + "," +
									n.id_item + "," +
									Util.apostrofo(n.cd_produto) + "," +
									//Util.apostrofo(n.ds_produto) + "," +
									n.id_representada + "," +											
									(n.ds_complemento != null && n.ds_complemento.length() > 0? Util.apostrofo(n.ds_complemento) : "NULL") + "," +
									n.qt_produto + "," +
									n.vr_unitario  + "," +
									n.vr_total + ", _@IDPEDIDO@_);";
						}
					}
					catch(Exception ex)
					{
						tbStatusSincronismo.setText("Geração de query de itens do pedido - Situação: " + ex.getMessage());
					}
					retornoPedido = UtilWebService.enviarPedido(usuario,senha, sqlWSCab, sqlWSItens);
				}
				
				if (retornoPedido.equalsIgnoreCase("OK"))
				{
					banco.beginTransaction();
					sqlItens = "delete from item_pedido where id_pedido = " + cPedidoAtual.id_Pedido;					
					banco.execSQL(sqlItens);
					
					String sqlPed = "delete from pedido where id_pedido = " + cPedidoAtual.id_Pedido;
					banco.execSQL(sqlPed);
					banco.setTransactionSuccessful();
					banco.endTransaction();
				}
				else
				{
					retorno += cPedidoAtual.nr_Pedido + " - " + retornoPedido + "\n";				
				}				
			}
		}
		return retorno;
	}	
}

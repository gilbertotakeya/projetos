package com.br.representanetmobile;

import java.util.ArrayList;

import representanetmobileadapter.pedidoitensadapter;

import com.br.representanetmobile.R;
import com.br.representanetmobileclasses.cClienteSelecionado;
import com.br.representanetmobileclasses.cDadosUsuarioLogado;
import com.br.representanetmobileclasses.cPedidoAtual;
import com.br.representanetmobileclasses.cPedidoItem;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class carteirapedidos extends Activity 
{
	String sqlCab =	"SELECT   	PEDIDO.NR_PEDIDO,\n" +
					"			strftime('%d/%m/%Y',PEDIDO.DT_PEDIDO) as DT_PEDIDO,\n" +
					"			PEDIDO.VR_LIQUIDO AS VR_LIQUIDO,\n" +
					"			PEDIDO.VR_TOTAL AS VR_TOTAL,\n" +
					"         	CLIENTE.NM_CLIENTE,\n" + 
					"			REPRESENTADA.NM_REPRESENTADA,\n"+	
					"         	PEDIDO.ID_PEDIDO as _id\n" + 
					"FROM PEDIDO\n" + 
					"INNER JOIN CLIENTE ON CLIENTE.ID_CLIENTE = PEDIDO.ID_CLIENTE\n" +
					"INNER JOIN REPRESENTADA ON REPRESENTADA.ID_REPRESENTADA = PEDIDO.ID_REPRESENTADA";
	
	
	ListView listaCabecalho;
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carteirapedidos);
		
		listaCabecalho = (ListView)findViewById(R.id.carteirapedidoListaCab);
		
		final Button btnIncluirPedido = (Button)findViewById(R.id.btnIncluir);
		
		btnIncluirPedido.setOnClickListener(new View.OnClickListener() 
		{			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent i = new Intent(carteirapedidos.this, clientes.class);
				//i.setClass(this, menu.class);
				startActivity(i);
				//Toast.makeText(MainActivity.this, login.getText().toString(), Toast.LENGTH_LONG).show();
			}
		});
		
		final Button sincronizar= (Button)findViewById(R.id.btnCarteiraPedidoSincronismo);
		
		sincronizar.setOnClickListener(new View.OnClickListener() 
		{			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent i = new Intent(carteirapedidos.this, Sincronismo.class);
				//i.setClass(this, menu.class);
				startActivity(i);
				//Toast.makeText(MainActivity.this, login.getText().toString(), Toast.LENGTH_LONG).show();
			}
		});
		carregarCabecalhoPedido();
		
		listaCabecalho.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long posicao) {
				// TODO Seta o cliente clicado na lista
				setarCabecalhoPedidoSelecionado(posicao, false);
			}
		});
		
		listaCabecalho.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                    int index, long posicao) {
                // TODO Auto-generated method stub
            	setarCabecalhoPedidoSelecionado(posicao, true);
                return true;
            }
		}); 		
	}

    @Override
    protected void onResume() 
    {
    	super.onResume();
    	
    	carregarCabecalhoPedido();
    }	
	
	private SQLiteDatabase abrirBanco()
	{
		return  openOrCreateDatabase(Util.nomeBanco, MODE_WORLD_READABLE, null);
	}
	
	private void carregarCabecalhoPedido()
	{			
		String[] coluna = new String[]{"NR_PEDIDO",
									   "DT_PEDIDO",
									   "NM_CLIENTE",
									   "NM_REPRESENTADA",
									   "VR_LIQUIDO",
									   "VR_TOTAL"};
		
		int[] campoDestino = new int[] { R.id.viewpedidocabecalhoNumeroPedido, 
										 R.id.viewpedidocabecalhoDataPedido,
										 R.id.viewpedidocabecalhoNomeCliente,
										 R.id.viewpedidocabecalhorepresentada,
										 R.id.viewpedidocabecalhototalpedido,
										 R.id.viewpedidocabecalhototalpedidobruto};

		try
		{
			SQLiteDatabase BancoDados = abrirBanco();
			String sql = sqlCab;

			Cursor cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				cursor.moveToFirst();
				SimpleCursorAdapter AdapterLista = new SimpleCursorAdapter(this, R.layout.viewpedidocabecalho, cursor, coluna, campoDestino );
				listaCabecalho.setAdapter(AdapterLista);
			}
			else
				listaCabecalho.setAdapter(null);
			
			BancoDados.close();
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Clientes", "erro: " + ex.getMessage(), carteirapedidos.this);
		}
	}

	private void setarCabecalhoPedidoSelecionado(long posicao, boolean deletar)
	{
		try
		{
			SQLiteDatabase BancoDados = abrirBanco();
						
			String sql = "SELECT PEDIDO.*, PEDIDO.ID_PEDIDO as _id FROM PEDIDO WHERE _id = " + String.valueOf(posicao);
			Cursor cursor = BancoDados.rawQuery(sql, null);
			if (cursor.getCount() > 0)
			{
				cPedidoAtual.limparDadosPedido();
				
				while(cursor.moveToNext())
				{
					cPedidoAtual.setarPedido(cursor);
				}
				
				if (deletar)
				{
					BancoDados.beginTransaction();

					String  whereClause = "ID_PEDIDO=?";
					String[] whereArgs = new String[] {String.valueOf(cPedidoAtual.id_Pedido)};					
					BancoDados.delete("ITEM_PEDIDO", whereClause, whereArgs);			

					whereClause = "ID_PEDIDO=?";
					whereArgs = new String[] {String.valueOf(cPedidoAtual.id_Pedido)};					
					BancoDados.delete("PEDIDO", whereClause, whereArgs);
					
					//String sqlPed = "DELETE FROM PEDIDO WHERE ID_PEDIDO = " + cPedidoAtual.id_Pedido;
					//BancoDados.execSQL(sqlPed);
					BancoDados.setTransactionSuccessful();
					BancoDados.endTransaction();
				}
				else
				{
					cursor = BancoDados.rawQuery("SELECT CLIENTE.*,CLIENTE.ID AS _id FROM CLIENTE WHERE _id = " + cPedidoAtual.id_Cliente, null);
					if (cursor.getCount() > 0)
					{
						while(cursor.moveToNext())
						{
							cClienteSelecionado.setarClienteSelecionado(cursor);
						}									
					}
					Intent i = new Intent(carteirapedidos.this, Pedidos.class);						
					startActivity(i);
				}
			}
			
			BancoDados.close();
			
			carregarCabecalhoPedido();
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Clientes", "erro: " + ex.getMessage(), carteirapedidos.this);
		}		
	}

}

package com.br.representanetmobile;

import org.xml.sax.DTDHandler;

import com.br.representanetmobile.R;
import com.br.representanetmobileclasses.cClienteSelecionado;
import com.br.representanetmobileclasses.cDadosUsuarioLogado;
import com.br.representanetmobileclasses.cPedidoAtual;
import com.br.representanetmobileclasses.cPedidoItem;

import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class Pedidos extends TabActivity 
{	
	private TextView NomeCliente;

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pedidos);		

		adicionarAbas();			
	}
	
	private SQLiteDatabase abrirBanco()
	{
		return  openOrCreateDatabase(Util.nomeBanco, MODE_WORLD_READABLE, null);
	}
	
	private void adicionarAbas()
	{
		TabHost tabHost = getTabHost();
		NomeCliente = (TextView)findViewById(R.id.lblNomeCliente);
		NomeCliente.setText(cClienteSelecionado.NM_CLIENTE);

		String sql = "SELECT REPRESENTADA.NM_REPRESENTADA,\n" +
					 "		 REPRESENTADA.NM_FANTASIA,\n" +	
					 "		 REPRESENTADA.ID_REPRESENTADA AS _id\n" +
					 "FROM REPRESENTADA\n" +
					 "WHERE REPRESENTADA.ID_REPRESENTANTE = " + cDadosUsuarioLogado.id_Representante + "\n" +
					 "  AND REPRESENTADA.ID_REPRESENTADA = " + cPedidoAtual.id_Representada + "\n" +
					 "ORDER BY 1";
		
		Cursor cursorTemp = abrirBanco().rawQuery(sql, null);
		while (cursorTemp.moveToNext())
		{
			String representada = cursorTemp.getString(cursorTemp.getColumnIndex("NM_REPRESENTADA"));			
			
			NomeCliente.setText(representada);
		}
		
		cursorTemp.close();					
		
        TabSpec cabecalho = tabHost.newTabSpec("Cabecalho");
        cabecalho.setIndicator("Cabecalho", getResources().getDrawable(R.drawable.imgpapellapis));
        Intent cabecalhoIntent = new Intent(this, pedidocabecalho.class);
        cabecalho.setContent(cabecalhoIntent);
        
        TabSpec itens = tabHost.newTabSpec("Itens");
        // setting Title and Icon for the Tab
        itens.setIndicator("Itens", getResources().getDrawable(R.drawable.imgcarrinho));
        Intent itensIntent = new Intent(this, pedidoitens.class);
        itens.setContent(itensIntent);
        
        TabSpec finalizar = tabHost.newTabSpec("Finalizar");
        // setting Title and Icon for the Tab
        finalizar.setIndicator("Finalizar", getResources().getDrawable(R.drawable.imgconfirmar));
        Intent finalizarIntent = new Intent(this, pedidofinalizar.class);
        finalizar.setContent(finalizarIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(cabecalho);
        tabHost.addTab(itens);   
        tabHost.addTab(finalizar);
	}
}

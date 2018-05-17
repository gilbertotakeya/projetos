package com.br.representanetmobile;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import representanetmobileadapter.pedidoitensadapter;

import com.br.representanetmobileclasses.cClienteSelecionado;
import com.br.representanetmobileclasses.cPedidoAtual;
import com.br.representanetmobileclasses.cPedidoItem;
import com.br.representanetmobileclasses.cPedidoItemSelecionado;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class pedidoitens extends Activity 
{	
	ListView ItensPedido;
	TextView lblTotalPedido;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidoitens);
        
        final Button entrar = (Button)findViewById(R.id.pedidoitensBtnNovo);
        ItensPedido = (ListView)findViewById(R.id.pedidoitensLvItensIncluidos);
        
        lblTotalPedido = (TextView)findViewById(R.id.pedidoitensLblTotalPedido);
        
		entrar.setOnClickListener(new View.OnClickListener() 
		{			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent i = new Intent(pedidoitens.this, Selecaoproduto.class);
				startActivity(i);
			}
		});
						
		ItensPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int posicao,
					long arg3) 
			{
				cPedidoItemSelecionado.itemSelecionado = (cPedidoItem) adapter.getAdapter().getItem(posicao);
				
				Intent i = new Intent(pedidoitens.this, Pedidoitensmanut.class);
				
				startActivity(i);
			}
		});
		
		ItensPedido.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() 
		{
            public boolean onItemLongClick(AdapterView<?> adapter, View v,
                    int index, long posicao) 
            {
                // TODO Auto-generated method stub
            	cPedidoItemSelecionado.itemSelecionado = (cPedidoItem) adapter.getAdapter().getItem((int)index);
            	cPedidoAtual.itensPedido.remove(cPedidoItemSelecionado.itemSelecionado);
            	atualizarListaItensPedido();
            	return true;
            }
		}); 			
    }

    @Override
    protected void onResume()
    {
    	super.onResume();
    	atualizarListaItensPedido();
    }       
    
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sair");
        builder.setMessage("Deseja realmente sair?");        
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() 
        {
            public void onClick(DialogInterface arg0, int arg1) 
            {
                finish();
            }
        });
        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() 
        {
            public void onClick(DialogInterface arg0, int arg1) 
            {
            }
        });
        
        AlertDialog alerta = builder.create();
        alerta.show();
    }

	private void atualizarListaItensPedido()
	{
		try
		{		
			pedidoitensadapter adapter = new pedidoitensadapter(pedidoitens.this, cPedidoAtual.itensPedido);
			ItensPedido.setAdapter(adapter);
			
			cPedidoAtual.vr_Total = 0;
			cPedidoAtual.vr_Liquido=0;
			for (cPedidoItem n : cPedidoAtual.itensPedido) 
			{				
				double valorLiquido = n.vr_total;
				if (cPedidoAtual.pc_Desc1 > 0)
					valorLiquido = valorLiquido - (valorLiquido * (cPedidoAtual.pc_Desc1 /100));

				if (cPedidoAtual.pc_Desc2 > 0)
					valorLiquido = valorLiquido - (valorLiquido * (cPedidoAtual.pc_Desc2/100));
				
				if (cPedidoAtual.pc_Desc3 > 0)
					valorLiquido = valorLiquido - (valorLiquido * (cPedidoAtual.pc_Desc3/100));
				
				if (cPedidoAtual.pc_Desc4 > 0)
					valorLiquido = valorLiquido - (valorLiquido * (cPedidoAtual.pc_Desc4/100));
				
				if (cPedidoAtual.pc_Desc5 > 0)
					valorLiquido = valorLiquido - (valorLiquido * (cPedidoAtual.pc_Desc5/100));
				
				if (cPedidoAtual.pc_Desc6 > 0)			
					valorLiquido = valorLiquido - (valorLiquido * (cPedidoAtual.pc_Desc6/100));
				
				if (cPedidoAtual.pc_Desc7 > 0)
					valorLiquido = valorLiquido - (valorLiquido * (cPedidoAtual.pc_Desc7/100));
											
				cPedidoAtual.vr_Total += n.vr_total;
				cPedidoAtual.vr_Liquido += valorLiquido;
			}
			
			cPedidoAtual.vr_Desconto = cPedidoAtual.vr_Total - cPedidoAtual.vr_Liquido;
			      			
			cPedidoAtual.vr_Desconto = Util.retornarValorDoubleFormatado(cPedidoAtual.vr_Desconto);
			cPedidoAtual.vr_Total= Util.retornarValorDoubleFormatado(cPedidoAtual.vr_Total);
			cPedidoAtual.vr_Liquido= Util.retornarValorDoubleFormatado(cPedidoAtual.vr_Liquido);
			
			lblTotalPedido.setText(Util.retornarValorStringFormatoBrasil(cPedidoAtual.vr_Total));
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("ATUALIZAR LISTA", "erro: " + ex.getMessage(), pedidoitens.this);
		}
	}
}
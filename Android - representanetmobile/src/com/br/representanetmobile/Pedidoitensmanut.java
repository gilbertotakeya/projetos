package com.br.representanetmobile;

import java.text.DecimalFormat;

import com.br.representanetmobileclasses.cPedidoAtual;
import com.br.representanetmobileclasses.cPedidoItemSelecionado;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Pedidoitensmanut extends Activity implements OnClickListener
{
	TextView lblProduto;
	EditText quantidade;
	EditText valor;
	EditText sequencia;	
	EditText complemento;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidoitensmanut);
        
        lblProduto = (TextView)findViewById(R.id.pedidoitensmanutlbProdutoSelecionado);
        quantidade = (EditText)findViewById(R.id.pedidoitensmanutTbQuantidade);
        valor = (EditText)findViewById(R.id.pedidoitensmanutTbValor);
        sequencia = (EditText)findViewById(R.id.pedidoitensmanutTbSequenciaItem);
        complemento = (EditText)findViewById(R.id.pedidoitensmanutTbComplemento);
        
        if (cPedidoItemSelecionado.itemSelecionado.vr_unitario > 0.0)
        	valor.setText(String.valueOf(cPedidoItemSelecionado.itemSelecionado.vr_unitario));
        
        lblProduto.setText(cPedidoItemSelecionado.itemSelecionado.descricao); 
        
        if (cPedidoItemSelecionado.itemSelecionado.qt_produto > 0)
        	quantidade.setText(String.valueOf(cPedidoItemSelecionado.itemSelecionado.qt_produto));
        else
        	quantidade.setText("1");
        
        if (cPedidoItemSelecionado.itemSelecionado.id_item > 0)
        	sequencia.setText(String.valueOf(cPedidoItemSelecionado.itemSelecionado.id_item));
        else
        	sequencia.setText(String.valueOf(cPedidoAtual.itensPedido.size() + 1));
        
        complemento.setText(cPedidoItemSelecionado.itemSelecionado.ds_complemento);
        
        complemento.requestFocus();
                       
        final Button entrar = (Button)findViewById(R.id.pedidoitensmanutBtnGravar);
		
		entrar.setOnClickListener(this);		
    }
    
    public void onClick(View v) 
    {    	
    	try
    	{
    		//DecimalFormat df = new DecimalFormat("#.00");
	        switch (v.getId()) 
	        {
		        case R.id.pedidoitensmanutBtnGravar:
		        {	      	
		        	String x = valor.getText().toString();
		        	Double valor = Util.doubleNull(x);
		        	if (valor != 0)
		        	{
		        		cPedidoAtual.itensPedido.remove(cPedidoItemSelecionado.itemSelecionado);
			        	cPedidoItemSelecionado.itemSelecionado.vr_unitario = Util.doubleNull(x);
			        	cPedidoItemSelecionado.itemSelecionado.qt_produto = Util.intNull(quantidade.getText().toString());
			        	cPedidoItemSelecionado.itemSelecionado.vr_total	= cPedidoItemSelecionado.itemSelecionado.qt_produto * cPedidoItemSelecionado.itemSelecionado.vr_unitario;		        	
			        	
			        	cPedidoItemSelecionado.itemSelecionado.ds_complemento = complemento.getText().toString();
			        	
			        	if (sequencia.getText().toString() == "")
			        		cPedidoItemSelecionado.itemSelecionado.id_item = 0;
			        	else
			        		cPedidoItemSelecionado.itemSelecionado.id_item = Util.intNull(sequencia.getText().toString());
			        	
			        	cPedidoAtual.itensPedido.add(cPedidoItemSelecionado.itemSelecionado);
			        	
			        	//monta a string
		        		String codigoItensVendidos = "_'" + cPedidoItemSelecionado.itemSelecionado.cd_produto + "'-";
		        		//remove a string, necessário em caso de alteração
		        		cPedidoAtual.itensVendidos = cPedidoAtual.itensVendidos.replace(codigoItensVendidos,"");
		        		//inclui a string
			        	cPedidoAtual.itensVendidos += codigoItensVendidos;
			        	//encerra o pedido
			        	finish();
		        	}
		        	else
		        		Util.mensagemAlerta("Item pedido!", "Informe o valor para o Item!", Pedidoitensmanut.this);
		        }
	        }
	    }
		catch(Exception ex)
		{
			Util.mensagemAlerta("Item do pedido", "Não foi possível incluir!\nSituação: " + ex.getMessage(), Pedidoitensmanut.this);
		}	
    }
}
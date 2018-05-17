package com.br.representanetmobile;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.br.representanetmobileclasses.cDadosUsuarioLogado;
import com.br.representanetmobileclasses.cPedidoAtual;
import com.br.representanetmobileclasses.cPedidoItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

public class pedidofinalizar extends Activity 
{
	private TextView vrBruto;
	private TextView pcDesc1;
	private TextView pcDesc2;
	private TextView pcDesc3;
	private TextView pcDesc4;
	private TextView pcDesc5;
	private TextView pcDesc6;
	private TextView pcDesc7;
	private TextView quantidade_Parcela;
	private TextView vrDesconto;
	private TextView vrLiquido;
		
    public void onCreate(Bundle savedInstanceState) 
    {    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidofinalizar);

        vincularObjetosTela();
        inicializacaoTela();
        
		final Button finalizar = (Button)findViewById(R.id.pedidofinalizarBtnFecharPedido);		
		finalizar.setOnClickListener(new View.OnClickListener() 
		{			
			@Override
			public void onClick(View arg0) 
			{

				try
				{
					String retorno = incluirPedido();
					if (retorno == null || (retorno != null && retorno.length() == 0))
						finish();
					else
						Util.mensagemAlerta("Pedido", "Falha ao gravar pedido!", pedidofinalizar.this);
				}			
				catch(Exception e)
				{
					Util.mensagemAlerta("Gravação do pedido", "Erro:\n" + e.getMessage(), pedidofinalizar.this);
				}
			}
		});
		
		pcDesc1.addTextChangedListener(new TextWatcher() {
			
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count)
	        {
				String x = pcDesc1.getText().toString();
				if (x.length() == 0)
					cPedidoAtual.pc_Desc1 = 0;
				else 
					cPedidoAtual.pc_Desc1 = Double.valueOf(x);
				
				atualizarTotal();
	        }
			
			@Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after)
	        {
	
	        }
	
	        @Override
	        public void afterTextChanged(Editable s)
	        {               
	        }
	    });

		pcDesc2.addTextChangedListener(new TextWatcher() {
			
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count)
	        {
				String x = pcDesc2.getText().toString();
				if (x.length() == 0)
					cPedidoAtual.pc_Desc2 = 0;
				else 
					cPedidoAtual.pc_Desc2 = Double.valueOf(x);
				
				atualizarTotal();
	        }
			
			@Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after)
	        {
	
	        }
	
	        @Override
	        public void afterTextChanged(Editable s)
	        {               
	        }
	    });
		
		pcDesc3.addTextChangedListener(new TextWatcher() {
			
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count)
	        {
				String x = pcDesc3.getText().toString();
				if (x.length() == 0)
					cPedidoAtual.pc_Desc3 = 0;
				else 
					cPedidoAtual.pc_Desc3 = Double.valueOf(x);
				
				atualizarTotal();
	        }
			
			@Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after)
	        {
	
	        }
	
	        @Override
	        public void afterTextChanged(Editable s)
	        {               
	        }
	    });
		
		pcDesc4.addTextChangedListener(new TextWatcher() {
			
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count)
	        {
				String x = pcDesc4.getText().toString();
				if (x.length() == 0)
					cPedidoAtual.pc_Desc4 = 0;
				else 
					cPedidoAtual.pc_Desc4 = Double.valueOf(x);
				
				atualizarTotal();
	        }
			
			@Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after)
	        {
	
	        }
	
	        @Override
	        public void afterTextChanged(Editable s)
	        {               
	        }
	    });
		
		pcDesc5.addTextChangedListener(new TextWatcher() {
			
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count)
	        {
				String x = pcDesc5.getText().toString();
				if (x.length() == 0)
					cPedidoAtual.pc_Desc5 = 0;
				else 
					cPedidoAtual.pc_Desc5 = Double.valueOf(x);
				
				atualizarTotal();
	        }
			
			@Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after)
	        {
	
	        }
	
	        @Override
	        public void afterTextChanged(Editable s)
	        {               
	        }
	    });
		
		pcDesc6.addTextChangedListener(new TextWatcher() {
			
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count)
	        {
				String x = pcDesc6.getText().toString();
				if (x.length() == 0)
					cPedidoAtual.pc_Desc6 = 0;
				else 
					cPedidoAtual.pc_Desc6 = Double.valueOf(x);
				
				atualizarTotal();
	        }
			
			@Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after)
	        {
	
	        }
	
	        @Override
	        public void afterTextChanged(Editable s)
	        {               
	        }
	    });
		
		pcDesc7.addTextChangedListener(new TextWatcher() {
			
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count)
	        {
				String x = pcDesc7.getText().toString();
				if (x.length() == 0)
					cPedidoAtual.pc_Desc7 = 0;
				else 
					cPedidoAtual.pc_Desc7 = Double.valueOf(x);
				
				atualizarTotal();
	        }
			
			@Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after)
	        {
	
	        }
	
	        @Override
	        public void afterTextChanged(Editable s)
	        {               
	        }
	    });
    }   
    
    private void atualizarTotal()
    {
    	try
    	{
			cPedidoAtual.vr_Total = 0;
			cPedidoAtual.vr_Liquido=0;
			
			for (cPedidoItem n : cPedidoAtual.itensPedido) 
			{
				double valorLiquido = n.vr_total;
				
				if (cPedidoAtual.pc_Desc1 > 0)
					valorLiquido = valorLiquido - (valorLiquido* (cPedidoAtual.pc_Desc1 /100));
	
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
			cPedidoAtual.vr_Liquido = cPedidoAtual.vr_Total - cPedidoAtual.vr_Desconto;
	
			//DecimalFormat df = new DecimalFormat("####,####,####,####.###");
			//String pattern = "#,##0.00";
			//DecimalFormat df = new DecimalFormat(pattern);
			//df.setGroupingSize(4);	
			
			//cPedidoAtual.vr_Desconto = Double.valueOf(df.format(cPedidoAtual.vr_Desconto));
			cPedidoAtual.vr_Desconto = Util.retornarValorDoubleFormatado(cPedidoAtual.vr_Desconto);
			cPedidoAtual.vr_Liquido = Util.retornarValorDoubleFormatado(cPedidoAtual.vr_Liquido);
			
			vrBruto.setText(String.valueOf(cPedidoAtual.vr_Total));
			vrDesconto.setText(String.valueOf(cPedidoAtual.vr_Desconto));
			vrLiquido.setText(String.valueOf(cPedidoAtual.vr_Liquido));
	    }
		catch(Exception ex)
		{
			Util.mensagemAlerta("Pedidos Finalizar", "AtualizarTotal() - Erro: " + ex.getMessage(), pedidofinalizar.this);
		}
    }
    
    private void setarDadosPedido()
    {
    	try
    	{
	    	((TextView)findViewById(R.id.tbDSAssistencia)).setText(cPedidoAtual.ds_Assistencia);
			((TextView)findViewById(R.id.tbDSObservacao)).setText(cPedidoAtual.ds_Observacao);
		
			cPedidoAtual.vr_Desconto = Util.doubleNull(vrDesconto.getText().toString());
			
			if (cPedidoAtual.pc_Desc1 > 0)		
				pcDesc1.setText(String.valueOf(cPedidoAtual.pc_Desc1));
			else
				pcDesc1.setText("");
			
			if (cPedidoAtual.pc_Desc2 > 0)
				pcDesc2.setText(String.valueOf(cPedidoAtual.pc_Desc2));
			else
				pcDesc2.setText("");
			
			if (cPedidoAtual.pc_Desc3 > 0)
				pcDesc3.setText(String.valueOf(cPedidoAtual.pc_Desc3));
			else
				pcDesc3.setText("");
			
			if (cPedidoAtual.pc_Desc4 > 0)
				pcDesc4.setText(String.valueOf(cPedidoAtual.pc_Desc4));
			else
				pcDesc4.setText("");
			
			if (cPedidoAtual.pc_Desc5 > 0)
				pcDesc5.setText(String.valueOf(cPedidoAtual.pc_Desc5));
			else
				pcDesc5.setText("");
			
			if (cPedidoAtual.pc_Desc6 > 0)
				pcDesc6.setText(String.valueOf(cPedidoAtual.pc_Desc6));
			else
				pcDesc6.setText("");
			
			if (cPedidoAtual.pc_Desc7 > 0)
				pcDesc7.setText(String.valueOf(cPedidoAtual.pc_Desc7));
			else
				pcDesc7.setText("");
			
			vrLiquido.setText(String.valueOf(cPedidoAtual.vr_Liquido));
			vrDesconto.setText(String.valueOf(cPedidoAtual.vr_Desconto));
			vrBruto.setText(String.valueOf(cPedidoAtual.vr_Total));
					
			quantidade_Parcela.setText(String.valueOf(cPedidoAtual.quantidade_Parcela));
    	}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Pedidos Finalizar", "setarDadosPedido() - Erro: " + ex.getMessage(), pedidofinalizar.this);
		}
    }
    
    private String incluirPedido()
    {
    	String mensagemErro = "";
		SQLiteDatabase BancoDados = abrirBanco();
		try
		{	    	
			cPedidoAtual.ds_Assistencia = ((TextView)findViewById(R.id.tbDSAssistencia)).getText().toString();
			cPedidoAtual.ds_Observacao = ((TextView)findViewById(R.id.tbDSObservacao)).getText().toString();
			
			cPedidoAtual.pc_Desc1= Util.doubleNull(pcDesc1.getText().toString());			
			cPedidoAtual.pc_Desc2=Util.doubleNull(pcDesc2.getText().toString());
			cPedidoAtual.pc_Desc3=Util.doubleNull(pcDesc3.getText().toString());
			cPedidoAtual.pc_Desc4=Util.doubleNull(pcDesc4.getText().toString());
			cPedidoAtual.pc_Desc5=Util.doubleNull(pcDesc5.getText().toString());
			cPedidoAtual.pc_Desc6=Util.doubleNull(pcDesc6.getText().toString());
			cPedidoAtual.pc_Desc7=Util.doubleNull(pcDesc7.getText().toString());
			cPedidoAtual.quantidade_Parcela = Util.intNull(quantidade_Parcela.getText().toString());		
			
			atualizarTotal();
			
			BancoDados.beginTransaction();
	
			if (cPedidoAtual.id_Pedido <= 0)
			{
				String dataEntrega = Util.converterDataString(cPedidoAtual.dt_Entrega);
				String dataAssistencia = Util.converterDataString(cPedidoAtual.dt_Assistencia);
				
				String sql = Util.sqlCabecalho(dataEntrega, dataAssistencia, true);
				
				BancoDados.execSQL(sql);
				
				Cursor cursor = BancoDados.rawQuery("SELECT MAX(ID_PEDIDO) AS ID_PEDIDO, ROWID as _id FROM PEDIDO WHERE ID_REPRESENTANTE = "+ cPedidoAtual.id_Representante, null);
				while(cursor.moveToNext())
				{
					cPedidoAtual.id_Pedido = cursor.getInt(cursor.getColumnIndex("ID_PEDIDO"));
				}					
			}
			else
			{
				String sql ="UPDATE PEDIDO SET\n" +
							"		PC_DESC1 = " + cPedidoAtual.pc_Desc1 + ",\n" + 
							"		PC_DESC2 = " + cPedidoAtual.pc_Desc2 +",\n" + 
							"		PC_DESC3 = " + cPedidoAtual.pc_Desc3 +",\n" + 
							"		PC_DESC4 = " + cPedidoAtual.pc_Desc4 +",\n" + 
							"		PC_DESC5 = " + cPedidoAtual.pc_Desc5 +",\n" + 
							"		PC_DESC6 = " + cPedidoAtual.pc_Desc6 +",\n" + 
							"		PC_DESC7 = " + cPedidoAtual.pc_Desc7 +",\n" + 
							"		VR_DESCONTO = " + cPedidoAtual.vr_Desconto + ",\n" + 
							"		VR_TOTAL = " + cPedidoAtual.vr_Total + ",\n" + 
							"		VR_LIQUIDO = " + cPedidoAtual.vr_Liquido + ",\n" + 
							"		DT_PEDIDO = " + Util.converterDataStringMySql(cPedidoAtual.dt_Pedido) + ",\n" + 
							"		DT_ENTREGA = " + Util.converterDataStringMySql(cPedidoAtual.dt_Entrega) +",\n" + 
							"		DT_ASSISTENCIA = " + Util.converterDataStringMySql(cPedidoAtual.dt_Assistencia) + ",\n" + 
							"		FG_FRETE = " + Util.apostrofo(cPedidoAtual.fg_Frete) +",\n" + 
							"		DS_FRETE = " + Util.apostrofo(cPedidoAtual.ds_Frete) +",\n" + 
							"		DS_PAGAMENTO = " + Util.apostrofo(cPedidoAtual.ds_Pagamento) +",\n" + 
							"		DS_OBSERVACAO = " + Util.apostrofo(cPedidoAtual.ds_Observacao) +",\n" + 
							"		DS_ASSISTENCIA = " + Util.apostrofo(cPedidoAtual.ds_Assistencia) +",\n" + 
							"		IDTABELAPRECO = " + String.valueOf(cPedidoAtual.id_Tabela_Preco) + ",\n" +
							"		NR_PEDIDO = " + cPedidoAtual.nr_Pedido + ",\n" +
							"		NR_PEDIDO_CLIENTE = " + Util.apostrofo(cPedidoAtual.nr_Pedido_Cliente) + ",\n" + 
							"		TP_COBRANCA = " + Util.apostrofo(cPedidoAtual.tp_Cobranca) + ",\n" + 
							"		ID_CLIENTE = " + String.valueOf(cPedidoAtual.id_Cliente) + ",\n" +
							"		ID_PREPOSTO = " + String.valueOf(cPedidoAtual.id_Preposto) + ",\n" +
							" 		ID_VENDEDOR = " + 	String.valueOf(cPedidoAtual.id_Vendedor) + ",\n" +
							"		QUANTIDADEPARCELA = " + String.valueOf(cPedidoAtual.quantidade_Parcela) + "\n" +	
							"WHERE 	ID_REPRESENTANTE = " + cPedidoAtual.id_Representante + "\n" +  
							"  AND 	ID_PEDIDO = " + cPedidoAtual.id_Pedido;

				BancoDados.execSQL(sql);
			}
			
			String sqlItens = "DELETE FROM ITEM_PEDIDO WHERE ID_PEDIDO = " + cPedidoAtual.id_Pedido;
			BancoDados.execSQL(sqlItens);
			for (cPedidoItem n : cPedidoAtual.itensPedido) 
			{
				String sqlInsert = "insert into item_pedido(" +
								   "id_representante, id_item, id_pedido," +
								   "cd_produto, ds_produto, id_representada, ds_complemento," +
								   "qt_produto, vr_unitario, vr_total) values(" +
								   cPedidoAtual.id_Representante + "," +
								   n.id_item + "," +
								   cPedidoAtual.id_Pedido + "," +
								   Util.apostrofo(n.cd_produto) + "," +
								   Util.apostrofo(n.ds_produto) + "," +
								   cPedidoAtual.id_Representada + "," +
								   Util.apostrofo(n.ds_complemento) + "," +
								   n.qt_produto + "," +
								   n.vr_unitario + "," +
								   n.vr_total + ")";
				
				BancoDados.execSQL(sqlInsert);
			}
			
			String sqlVendedor = "UPDATE REPRESENTANTE SET ULTIMONUMERO = " + (cDadosUsuarioLogado.ultimo_nro_pedido + 1) + " WHERE ID_REPRESENTANTE = " + cDadosUsuarioLogado.id_Representante;
			BancoDados.execSQL(sqlVendedor);
						
			BancoDados.setTransactionSuccessful();
		}
		catch(Exception e)
		{
			Util.mensagemAlerta("Pedido gravação", "Erro:\n" + e.getMessage(), pedidofinalizar.this);
			BancoDados.endTransaction();
			mensagemErro = e.getMessage();
		}
		finally 
		{
			BancoDados.endTransaction();
		}		
		
		if (mensagemErro != null && mensagemErro.length() > 0)
			return mensagemErro;	
		
		return null;
    }

	private void vincularObjetosTela()
	{
		pcDesc1= (TextView)findViewById(R.id.tbDesconto1);
		pcDesc2=(TextView)findViewById(R.id.tbDesconto2);
		pcDesc3=(TextView)findViewById(R.id.tbDesconto3);
		pcDesc4=(TextView)findViewById(R.id.tbDesconto4);
		pcDesc5=(TextView)findViewById(R.id.tbDesconto5);
		pcDesc6=(TextView)findViewById(R.id.tbDesconto6);
		pcDesc7=(TextView)findViewById(R.id.tbDesconto7);
		quantidade_Parcela = (TextView)findViewById(R.id.tbQtdParcela);	
		vrDesconto = (TextView)findViewById(R.id.tbValorDesconto);
		vrBruto= (TextView)findViewById(R.id.pedidofinalizarTbValorBruto);
		vrLiquido= (TextView)findViewById(R.id.pedidofinalizarTbValorLiquido);
	}
    
	private void inicializacaoTela()
	{
		try
		{
			String sql = "SELECT REPRESENTADA.TIPOCOMISSAO,ROWID as _id FROM REPRESENTADA WHERE ID_REPRESENTADA = " + cDadosUsuarioLogado.id_Representada;
			Cursor cursorTemp = abrirBanco().rawQuery(sql, null);
			while (cursorTemp.moveToNext())
			{
				String tipoComissao = cursorTemp.getString(cursorTemp.getColumnIndex("TIPOCOMISSAO"));
				if (tipoComissao.equals("F"))
				{
					quantidade_Parcela.setText("1");
					quantidade_Parcela.setEnabled(false);
				}
			}
			
			cursorTemp.close();				
			
			if (cPedidoAtual.id_Pedido <= 0)
			{
				vrDesconto.setText("");
				pcDesc1.setText("");
				pcDesc2.setText("");
				pcDesc3.setText("");
				pcDesc4.setText("");
				pcDesc5.setText("");
				pcDesc6.setText("");
				pcDesc7.setText("");
			}
			else
			{
				setarDadosPedido();
			}
		}
		catch(Exception ex)
		{
			Util.mensagemAlerta("Inicializar a tela finalização do pedido!", ex.getMessage(), pedidofinalizar.this);
		}
	}
	
	private SQLiteDatabase abrirBanco()
	{
		return  openOrCreateDatabase(Util.nomeBanco, MODE_WORLD_READABLE, null);
	}

    @Override
    protected void onResume()
    {
    	super.onResume();
    	atualizarTotal();
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
}
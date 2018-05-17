package com.br.representanetmobile;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.br.representanetmobileclasses.cPedidoAtual;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Util {

	public static String NAMESPACE = "http://tempuri.org/";
	public static String URL = "http://www.representanet.com.br/repserv/service.asmx";	
	public static String nomeBanco = "RepresentaNetTesteV07.db"; 

	public static String retornarValorStringFormatoBrasil(double valor)
	{
		NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return currency.format(valor);
	}
	
	public static Double retornarValorDoubleFormatado(double valor)
	{
		return Double.valueOf(retornarValorStringFormatoBrasil(valor).toUpperCase().replace("R$", "").replace(".", "").replace(",", "."));
	}
	
	public static String converterDataStringMySql(Date data)
	{
		if (data == null)
			return "NULL";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return apostrofo(sdf.format(data));
	}	
	
	public static String converterDataString(Date data)
	{
		if (data == null)
			return "NULL";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return apostrofo(sdf.format(data));
	}
	
	public static String converterDataStringTela(Date data)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	
	public static Date converterStringData(String strData)
	{
		if (strData == null || strData.length() == 0 || strData.equals("NULL") )
			return null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//2015-01-31 dd/MM/yyyy");
	    Date d=new Date();
        try 
        {
			d= dateFormat.parse(strData);
		} 
        catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    d=new Date();
	        try 
	        {
				d= dateFormat.parse(strData);
			} 
	        catch (ParseException ex) 
			{
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}	    
	    return d;		 
	}
	
	public static void mensagemAlerta(String tituloAlerta, String mensagemAlerta, Context contexto)
	{
		AlertDialog.Builder mensagem = new AlertDialog.Builder(contexto);
		mensagem.setTitle(tituloAlerta);
		mensagem.setMessage(mensagemAlerta);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}
	
	public static String apostrofo(String original)
	{
		if (original == null || original.equals("null") || (original.length() == 0))
			return "NULL";
		
		return "'" + original + "'";
	}
	
	public static Date dataHoraAtual()
	{
		return new Date();		
	}
	
	public static String sqlCabecalho(String dataEntrega, String dataAssistencia, Boolean bBancoLocal)
	{
		String sql ="insert into pedido\n" +
		          "(id_representante,\n" +
		          "id_representada,\n" +                
		          "id_vendedor,\n" + 
		          "nr_pedido,\n" + 
		          "id_cliente,\n" + 
		          "id_preposto,\n" + 
		          "pc_desc1,\n" + 
		          "pc_desc2,\n" + 
		          "pc_desc3,\n" + 
		          "pc_desc4,\n" + 
		          "pc_desc5,\n" + 
		          "pc_desc6,\n" + 
		          "pc_desc7,\n" + 
		          "vr_desconto,\n" + 
		          "vr_total,\n" + 
		          "vr_liquido,\n" + 
		          "dt_pedido,\n" +  
		          "dt_entrega,\n" + 
		          "dt_assistencia,\n" + 
		          "fg_frete,\n" + 
		          "ds_frete,\n" + 
		          "ds_pagamento,\n" + 
		          "ds_observacao,\n" + 
		          "ds_assistencia,\n" + 
		          "idtabelapreco,\n" + 
		          "nr_pedido_cliente,\n" + 
		          "tp_cobranca,\n" + 
		          "quantidadeparcela,\n" +
		          "dt_inclusao)\n" + 
		          "values (";
		
		if (bBancoLocal)
			sql = sql.toUpperCase();
		
		sql += 		cPedidoAtual.id_Representante + "," +
					cPedidoAtual.id_Representada +"," +
					cPedidoAtual.id_Vendedor + "," +
					cPedidoAtual.nr_Pedido +"," +
					cPedidoAtual.id_Cliente +"," +
					cPedidoAtual.id_Preposto +"," +
					cPedidoAtual.pc_Desc1 +"," +
					cPedidoAtual.pc_Desc2 +"," +
					cPedidoAtual.pc_Desc3 +"," +
					cPedidoAtual.pc_Desc4 +"," +
					cPedidoAtual.pc_Desc5 +"," +
					cPedidoAtual.pc_Desc6 +"," +
					cPedidoAtual.pc_Desc7 +"," +
					cPedidoAtual.vr_Desconto +"," +
					cPedidoAtual.vr_Total +"," +
					cPedidoAtual.vr_Liquido +"," +
					Util.converterDataStringMySql(cPedidoAtual.dt_Pedido) +"," +
					(dataEntrega != null && dataEntrega.length() > 0 ?  Util.converterDataStringMySql(cPedidoAtual.dt_Entrega) : "NULL") +"," +
					(dataAssistencia != null && dataAssistencia.length() > 0 ?  Util.converterDataStringMySql(cPedidoAtual.dt_Assistencia): "NULL") +"," +
					Util.apostrofo(cPedidoAtual.fg_Frete) +"," +
					Util.apostrofo(cPedidoAtual.ds_Frete) +"," +
					Util.apostrofo(cPedidoAtual.ds_Pagamento) +"," +
					Util.apostrofo(cPedidoAtual.ds_Observacao) +"," +
					Util.apostrofo(cPedidoAtual.ds_Assistencia) +"," +
					String.valueOf(cPedidoAtual.id_Tabela_Preco) +"," +
					Util.apostrofo(cPedidoAtual.nr_Pedido_Cliente) +"," +
					Util.apostrofo(cPedidoAtual.tp_Cobranca) +"," +
					String.valueOf(cPedidoAtual.quantidade_Parcela) + "," +
					"CURRENT_TIMESTAMP);";		
		return sql;
	}

	public static Double doubleNull(String valor)
	{
		try
		{
			return Double.parseDouble(valor);
		}
		catch(Exception ex)
		{
		}
		
		return 0.0;
	}
	
	public static int intNull(String valor)
	{
		try
		{
			return Integer.parseInt(valor);
		}
		catch(Exception ex)
		{
		}
		
		return 0;
	}	
}

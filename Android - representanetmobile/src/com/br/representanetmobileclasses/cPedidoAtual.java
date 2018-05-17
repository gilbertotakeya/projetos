package com.br.representanetmobileclasses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.br.representanetmobile.Util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

public class cPedidoAtual 
{
	public static int id_Representante;
	public static int id_Pedido;
	public static int id_Representada;
	public static int id_Vendedor;
	public static int nr_Pedido;
	public static int id_Cliente;
	public static int id_Preposto;
	public static double pc_Desc1;
	public static double pc_Desc2;
	public static double pc_Desc3;
	public static double pc_Desc4;
	public static double pc_Desc5;
	public static double pc_Desc6;
	public static double pc_Desc7;
	public static double vr_Desconto;
	public static double vr_Total;
	public static double vr_Liquido;
	public static Date dt_Pedido;
	public static Date dt_Envio_Representada;
	public static Date dt_Envio_Cliente;
	public static Date dt_Entrega;
	public static Date dt_Assistencia;
	public static String fg_Frete;
	public static String ds_Frete;
	public static String ds_Pagamento;
	public static String ds_Observacao;
	public static String ds_Assistencia;
	public static int id_Tabela_Preco;
	public static String nr_Pedido_Cliente;
	public static String tp_Cobranca;
	public static int quantidade_Parcela;
	public static Timestamp dt_Inclusao;
	public static String itensVendidos;
	
	public static ArrayList <cPedidoItem> itensPedido = new ArrayList<cPedidoItem>();
	
	public static void setarPedido(Cursor cursor)
	{
		cPedidoAtual.id_Representante = cursor.getInt(cursor.getColumnIndex("ID_REPRESENTANTE"));
		cPedidoAtual.id_Pedido =cursor.getInt(cursor.getColumnIndex("ID_PEDIDO"));
		cPedidoAtual.id_Representada =cursor.getInt(cursor.getColumnIndex("ID_REPRESENTADA"));
		cPedidoAtual.id_Vendedor =cursor.getInt(cursor.getColumnIndex("ID_VENDEDOR"));
		cPedidoAtual.nr_Pedido =cursor.getInt(cursor.getColumnIndex("NR_PEDIDO"));
		cPedidoAtual.id_Cliente =cursor.getInt(cursor.getColumnIndex("ID_CLIENTE"));
		cPedidoAtual.id_Preposto=cursor.getInt(cursor.getColumnIndex("ID_PREPOSTO"));
		cPedidoAtual.pc_Desc1 = cursor.getDouble(cursor.getColumnIndex("PC_DESC1"));
		cPedidoAtual.pc_Desc2 = cursor.getDouble(cursor.getColumnIndex("PC_DESC2"));
		cPedidoAtual.pc_Desc3 =cursor.getDouble(cursor.getColumnIndex("PC_DESC3"));
		cPedidoAtual.pc_Desc4 =cursor.getDouble(cursor.getColumnIndex("PC_DESC4"));
		cPedidoAtual.pc_Desc5 =cursor.getDouble(cursor.getColumnIndex("PC_DESC5"));
		cPedidoAtual.pc_Desc6 =cursor.getDouble(cursor.getColumnIndex("PC_DESC6"));
		cPedidoAtual.pc_Desc7 =cursor.getDouble(cursor.getColumnIndex("PC_DESC7"));
		cPedidoAtual.vr_Desconto =cursor.getDouble(cursor.getColumnIndex("VR_DESCONTO"));
		cPedidoAtual.vr_Total =cursor.getDouble(cursor.getColumnIndex("VR_TOTAL"));
		cPedidoAtual.vr_Liquido =cursor.getDouble(cursor.getColumnIndex("VR_LIQUIDO"));
		cPedidoAtual.dt_Pedido = Util.converterStringData(cursor.getString(cursor.getColumnIndex("DT_PEDIDO")));
		
		String dataEnvioRepresentada = cursor.getString(cursor.getColumnIndex("DT_ENVIO_REPRESENTADA"));
		if (dataEnvioRepresentada != null && !dataEnvioRepresentada.equals("NULL"))			
			cPedidoAtual.dt_Envio_Representada = Util.converterStringData(cursor.getString(cursor.getColumnIndex("DT_ENVIO_REPRESENTADA")));
		
		String dataEnvioCliente = cursor.getString(cursor.getColumnIndex("DT_ENVIO_CLIENTE"));
		if (dataEnvioCliente != null && !dataEnvioCliente.equals("NULL"))
			cPedidoAtual.dt_Envio_Cliente = Util.converterStringData(cursor.getString(cursor.getColumnIndex("DT_ENVIO_CLIENTE")));
		
		String dataEntrega = cursor.getString(cursor.getColumnIndex("DT_ENTREGA"));
		if (dataEntrega != null && !dataEntrega.equals("NULL"))
			cPedidoAtual.dt_Entrega = Util.converterStringData(dataEntrega);
		
		String dataAssistencia = cursor.getString(cursor.getColumnIndex("DT_ASSISTENCIA"));
		if (dataAssistencia != null && !dataAssistencia.equals("NULL"))			
			cPedidoAtual.dt_Assistencia = Util.converterStringData(cursor.getString(cursor.getColumnIndex("DT_ASSISTENCIA")));
		
		cPedidoAtual.fg_Frete = cursor.getString(cursor.getColumnIndex("FG_FRETE"));		

		cPedidoAtual.ds_Frete = cursor.getString(cursor.getColumnIndex("DS_FRETE"));
		cPedidoAtual.ds_Pagamento = cursor.getString(cursor.getColumnIndex("DS_PAGAMENTO"));
		cPedidoAtual.ds_Observacao = cursor.getString(cursor.getColumnIndex("DS_OBSERVACAO"));
		cPedidoAtual.ds_Assistencia = cursor.getString(cursor.getColumnIndex("DS_ASSISTENCIA"));
		cPedidoAtual.id_Tabela_Preco = cursor.getInt(cursor.getColumnIndex("IDTABELAPRECO"));
		cPedidoAtual.nr_Pedido_Cliente = cursor.getString(cursor.getColumnIndex("NR_PEDIDO_CLIENTE"));
		cPedidoAtual.tp_Cobranca = cursor.getString(cursor.getColumnIndex("TP_COBRANCA"));
		cPedidoAtual.quantidade_Parcela= cursor.getInt(cursor.getColumnIndex("QUANTIDADEPARCELA"));
	}			

	public static void limparDadosPedido()
	{
		cPedidoAtual.id_Representante = cDadosUsuarioLogado.id_Representante;
		cPedidoAtual.id_Pedido =-1;
		cPedidoAtual.id_Representada = cDadosUsuarioLogado.id_Representada;
		cPedidoAtual.id_Vendedor = cDadosUsuarioLogado.id_Vendedor;		
		cPedidoAtual.nr_Pedido =-1;
		cPedidoAtual.id_Cliente =-1;
		cPedidoAtual.id_Preposto = cDadosUsuarioLogado.id_Preposto;
		cPedidoAtual.pc_Desc1 = 0;
		cPedidoAtual.pc_Desc2 = 0;
		cPedidoAtual.pc_Desc3 =0;
		cPedidoAtual.pc_Desc4 =0;
		cPedidoAtual.pc_Desc5 =0;
		cPedidoAtual.pc_Desc6 =0;
		cPedidoAtual.pc_Desc7 =0;
		cPedidoAtual.vr_Desconto =0;
		cPedidoAtual.vr_Total =0;
		cPedidoAtual.vr_Liquido =0;
		cPedidoAtual.dt_Pedido = Util.dataHoraAtual();
		cPedidoAtual.dt_Envio_Representada = null;
		cPedidoAtual.dt_Envio_Cliente = null;
		cPedidoAtual.dt_Entrega = null;
		cPedidoAtual.dt_Assistencia = null;
		cPedidoAtual.fg_Frete = "";
		cPedidoAtual.ds_Frete = "";
		cPedidoAtual.ds_Pagamento = "";
		cPedidoAtual.ds_Observacao = "";
		cPedidoAtual.ds_Assistencia = "";
		cPedidoAtual.id_Tabela_Preco = -1;
		cPedidoAtual.nr_Pedido_Cliente = "";
		cPedidoAtual.tp_Cobranca = "";
		cPedidoAtual.quantidade_Parcela= 1;
		cPedidoAtual.dt_Inclusao = null;	
		cPedidoAtual.itensPedido.clear();
		cPedidoAtual.itensVendidos = "";
	}
}

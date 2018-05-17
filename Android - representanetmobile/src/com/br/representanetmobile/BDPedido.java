package com.br.representanetmobile;

import java.util.ArrayList;
import java.util.List;

import com.br.representanetmobileclasses.cPedidoAtual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BDPedido 
{
	private SQLiteDatabase bd;
	
	public BDPedido(Context context){
		BDCore auxBd = new BDCore(context);
		bd = auxBd.getWritableDatabase();
	}
	
	
	public void inserir(cPedidoAtual pedido){		
		ContentValues valores = new ContentValues();
		
		valores.put("id_representante",pedido.id_Representante);
		valores.put("id_pedido",pedido.id_Pedido);
		valores.put("id_representada",pedido.id_Representada);
		valores.put("id_vendedor",pedido.id_Vendedor);
		valores.put("nr_pedido",pedido.nr_Pedido);
		valores.put("id_cliente",pedido.id_Cliente);
		valores.put("id_Preposto",pedido.id_Preposto);
		valores.put("pc_Desc1",pedido.pc_Desc1);
		valores.put("pc_Desc2",pedido.pc_Desc2);
		valores.put("pc_Desc3",pedido.pc_Desc3);
		valores.put("pc_Desc4",pedido.pc_Desc4);
		valores.put("pc_Desc5",pedido.pc_Desc5);
		valores.put("pc_Desc6",pedido.pc_Desc6);
		valores.put("pc_Desc7",pedido.pc_Desc7);
		valores.put("vr_Desconto",pedido.vr_Desconto);
		valores.put("vr_Total",pedido.vr_Total);
		valores.put("vr_Liquido",pedido.vr_Liquido);
		valores.put("dt_Pedido", Util.converterDataString(pedido.dt_Pedido));
		valores.put("dt_Envio_Representada",Util.converterDataString(pedido.dt_Envio_Representada));
		valores.put("dt_Envio_Cliente",Util.converterDataString(pedido.dt_Envio_Cliente));
		valores.put("dt_Entrega",Util.converterDataString(pedido.dt_Entrega));
		valores.put("dt_Assistencia",Util.converterDataString(pedido.dt_Assistencia));
		valores.put("fg_Frete",pedido.fg_Frete);
		valores.put("ds_Frete",pedido.ds_Frete);
		valores.put("ds_Pagamento",pedido.ds_Pagamento);
		valores.put("ds_Observacao",pedido.ds_Observacao);
		valores.put("ds_Assistencia",pedido.ds_Assistencia);
		valores.put("id_Tabela_Preco",pedido.id_Tabela_Preco);
		valores.put("nr_Pedido_Cliente",pedido.nr_Pedido_Cliente);
		valores.put("tp_Cobranca",pedido.tp_Cobranca);
		valores.put("quantidade_Parcela",pedido.quantidade_Parcela);
		valores.put("dt_Inclusao", "time('now')");
		
		
		bd.insert("pedido", null, valores);
	}
	
	
	public void atualizar(cPedidoAtual pedido){
		ContentValues valores = new ContentValues();
		valores.put("id_representada",pedido.id_Representada);
		valores.put("id_vendedor",pedido.id_Vendedor);
		valores.put("nr_pedido",pedido.nr_Pedido);
		valores.put("id_cliente",pedido.id_Cliente);
		valores.put("id_Preposto",pedido.id_Preposto);
		valores.put("pc_Desc1",pedido.pc_Desc1);
		valores.put("pc_Desc2",pedido.pc_Desc2);
		valores.put("pc_Desc3",pedido.pc_Desc3);
		valores.put("pc_Desc4",pedido.pc_Desc4);
		valores.put("pc_Desc5",pedido.pc_Desc5);
		valores.put("pc_Desc6",pedido.pc_Desc6);
		valores.put("pc_Desc7",pedido.pc_Desc7);
		valores.put("vr_Desconto",pedido.vr_Desconto);
		valores.put("vr_Total",pedido.vr_Total);
		valores.put("vr_Liquido",pedido.vr_Liquido);
		valores.put("dt_Pedido", Util.converterDataString(pedido.dt_Pedido));
		valores.put("dt_Envio_Representada",Util.converterDataString(pedido.dt_Envio_Representada));
		valores.put("dt_Envio_Cliente",Util.converterDataString(pedido.dt_Envio_Cliente));
		valores.put("dt_Entrega",Util.converterDataString(pedido.dt_Entrega));
		valores.put("dt_Assistencia",Util.converterDataString(pedido.dt_Assistencia));
		valores.put("fg_Frete",pedido.fg_Frete);
		valores.put("ds_Frete",pedido.ds_Frete);
		valores.put("ds_Pagamento",pedido.ds_Pagamento);
		valores.put("ds_Observacao",pedido.ds_Observacao);
		valores.put("ds_Assistencia",pedido.ds_Assistencia);
		valores.put("id_Tabela_Preco",pedido.id_Tabela_Preco);
		valores.put("nr_Pedido_Cliente",pedido.nr_Pedido_Cliente);
		valores.put("tp_Cobranca",pedido.tp_Cobranca);
		valores.put("quantidade_Parcela",pedido.quantidade_Parcela);
		
		bd.update("pedido", valores, "id_representante" + pedido.id_Representante + " and id_pedido = " + pedido.id_Pedido, null);
	}
	
	
	public void deletar(cPedidoAtual pedido){
		bd.delete("pedido", "id_representante" + pedido.id_Representante + " and id_pedido = " + pedido.id_Pedido, null);
	}
	
	
	public List<cPedidoAtual>buscar()
	{
		List<cPedidoAtual> list = new ArrayList<cPedidoAtual>();
		String[] colunas = new String[]{"nr_pedido", "dt_Pedido", "id_cliente"};
		
		Cursor cursor = bd.query("pedido", colunas, null, null, null, null, "dt_Pedido DESC");
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			
			do{
				
				cPedidoAtual u = new cPedidoAtual();
				
				list.add(u);
				
			}while(cursor.moveToNext());
		}
		
		return(list);
	}
}

package com.br.representanetmobileclasses;

import android.database.Cursor;

public class cPedidoItem 
{
	public int id_representante;
	public int id_item;
	public int id_pedido;
	public String cd_produto;
	public String ds_produto;
	public int id_representada;	
	public String descricao;
	public String ds_complemento;
	public int qt_produto;
	public double vr_unitario;
	public double vr_total;
	public int id;
	
	
	public void setarPedidoItem(Cursor cursor)
	{
		this.id_representante = cursor.getInt(cursor.getColumnIndex("ID_REPRESENTANTE"));
		this.id_item = cursor.getInt(cursor.getColumnIndex("ID_ITEM"));
		this.id_pedido= cursor.getInt(cursor.getColumnIndex("ID_PEDIDO"));
		this.cd_produto= cursor.getString(cursor.getColumnIndex("CD_PRODUTO"));
		this.id_representada = cursor.getInt(cursor.getColumnIndex("ID_REPRESENTADA"));
		this.ds_produto= cursor.getString(cursor.getColumnIndex("DS_PRODUTO"));
		this.descricao = cursor.getString(cursor.getColumnIndex("DS_PRODUTO"));
		this.ds_complemento = cursor.getString(cursor.getColumnIndex("DS_COMPLEMENTO"));
		this.qt_produto = cursor.getInt(cursor.getColumnIndex("QT_PRODUTO"));
		this.vr_unitario= cursor.getDouble(cursor.getColumnIndex("VR_UNITARIO"));
		this.vr_total= cursor.getDouble(cursor.getColumnIndex("VR_TOTAL"));
		this.id= cursor.getInt(cursor.getColumnIndex("ID"));
	}
	
	public int getPedidoItem()
	{
		return this.id;
	}
}

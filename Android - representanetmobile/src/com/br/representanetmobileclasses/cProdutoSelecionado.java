package com.br.representanetmobileclasses;

import java.sql.Date;

import android.database.Cursor;

public class cProdutoSelecionado 
{
	public static int id_representante;
	public static int id_representada;
	public static String cd_produto;
	public static String ds_produto;
	public static int id_produto;
	
	public static void setarProdutoSelecionado(Cursor cursor)
	{		
		id_representante = cursor.getInt(cursor.getColumnIndex("ID_REPRESENTANTE"));
		id_representada = cursor.getInt(cursor.getColumnIndex("ID_REPRESENTADA"));
		cd_produto		= cursor.getString(cursor.getColumnIndex("CD_PRODUTO"));
		ds_produto		= cursor.getString(cursor.getColumnIndex("DS_PRODUTO"));
		id_produto		= cursor.getInt(cursor.getColumnIndex("ID_PRODUTO"));
	}
}

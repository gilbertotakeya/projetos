package com.br.representanetmobileclasses;

import android.database.Cursor;

public class cClienteSelecionado 
{
	public static int ID_REPRESENTANTE;
	public static int ID_CLIENTE;
	public static String NM_CLIENTE;
	public static String NM_FANTASIA;
	public static int ID_VENDEDOR;
	public static int ID_PREPOSTO;
	public static String NR_IE;
	public static String NR_CNPJ;
	public static String DS_ENDERECO;
	public static String NM_BAIRRO;
	public static String NM_CIDADE;
	public static String SG_ESTADO;
	public static String NR_CEP;
	public static String NR_TELEFONE;
	public static String NR_FAX;
	public static String NR_CELULAR;
	public static String DS_CONTATO;
	public static String DS_ENDERECO_COB;
	public static String NM_BAIRRO_COB;
	public static String NM_CIDADE_COB;
	public static String SG_ESTADO_COB;
	public static String NR_CEP_COB;
	public static String NR_TELEFONE_COB;
	public static String DS_ENDERECO_ENT;
	public static String NM_BAIRRO_ENT;
	public static String NM_CIDADE_ENT;
	public static String SG_ESTADO_ENT;
	public static String NR_CEP_ENT;
	public static String NR_TELEFONE_ENT;
	public static String NR_CNPJ_ENTREGA;
	public static String DS_EMAIL;
	public static String CD_CLIENTE_REPRESENTANTE;
	public static String DS_OBSERVACAO;
	public static int ID;
	public static String FG_ATIVO;
	
	public static void setarClienteSelecionado(Cursor cursor)
	{
		cClienteSelecionado.ID_REPRESENTANTE= cursor.getInt(cursor.getColumnIndex("ID_REPRESENTANTE"));
		cClienteSelecionado.ID_CLIENTE=cursor.getInt(cursor.getColumnIndex("ID_CLIENTE"));
		cClienteSelecionado.NM_CLIENTE=cursor.getString(cursor.getColumnIndex("NM_CLIENTE"));
		cClienteSelecionado.NM_FANTASIA=cursor.getString(cursor.getColumnIndex("NM_FANTASIA"));
		cClienteSelecionado.ID_VENDEDOR=cursor.getInt(cursor.getColumnIndex("ID_VENDEDOR"));
		cClienteSelecionado.ID_PREPOSTO=cursor.getInt(cursor.getColumnIndex("ID_PREPOSTO"));
		cClienteSelecionado.NR_IE=cursor.getString(cursor.getColumnIndex("NR_IE"));
		cClienteSelecionado.NR_CNPJ=cursor.getString(cursor.getColumnIndex("NR_CNPJ"));
		cClienteSelecionado.DS_ENDERECO=cursor.getString(cursor.getColumnIndex("DS_ENDERECO"));
		cClienteSelecionado.NM_BAIRRO=cursor.getString(cursor.getColumnIndex("NM_BAIRRO"));
		cClienteSelecionado.NM_CIDADE=cursor.getString(cursor.getColumnIndex("NM_CIDADE"));
		cClienteSelecionado.SG_ESTADO=cursor.getString(cursor.getColumnIndex("SG_ESTADO"));
		cClienteSelecionado.NR_CEP=cursor.getString(cursor.getColumnIndex("NR_CEP"));
		cClienteSelecionado.NR_TELEFONE=cursor.getString(cursor.getColumnIndex("NR_TELEFONE"));
		cClienteSelecionado.NR_FAX=cursor.getString(cursor.getColumnIndex("NR_FAX"));
		cClienteSelecionado.NR_CELULAR=cursor.getString(cursor.getColumnIndex("NR_CELULAR"));
		cClienteSelecionado.DS_CONTATO=cursor.getString(cursor.getColumnIndex("DS_CONTATO"));
		cClienteSelecionado.DS_ENDERECO_COB=cursor.getString(cursor.getColumnIndex("DS_ENDERECO_COB"));
		cClienteSelecionado.NM_BAIRRO_COB=cursor.getString(cursor.getColumnIndex("NM_BAIRRO_COB"));
		cClienteSelecionado.NM_CIDADE_COB=cursor.getString(cursor.getColumnIndex("NM_CIDADE_COB"));
		cClienteSelecionado.SG_ESTADO_COB=cursor.getString(cursor.getColumnIndex("SG_ESTADO_COB"));
		cClienteSelecionado.NR_CEP_COB=cursor.getString(cursor.getColumnIndex("NR_CEP_COB"));
		cClienteSelecionado.NR_TELEFONE_COB=cursor.getString(cursor.getColumnIndex("NR_TELEFONE_COB"));
		cClienteSelecionado.DS_ENDERECO_ENT=cursor.getString(cursor.getColumnIndex("DS_ENDERECO_ENT"));
		cClienteSelecionado.NM_BAIRRO_ENT=cursor.getString(cursor.getColumnIndex("NM_BAIRRO_ENT"));
		cClienteSelecionado.NM_CIDADE_ENT=cursor.getString(cursor.getColumnIndex("NM_CIDADE_ENT"));
		cClienteSelecionado.SG_ESTADO_ENT=cursor.getString(cursor.getColumnIndex("SG_ESTADO_ENT"));
		cClienteSelecionado.NR_CEP_ENT=cursor.getString(cursor.getColumnIndex("NR_CEP_ENT"));
		cClienteSelecionado.NR_TELEFONE_ENT=cursor.getString(cursor.getColumnIndex("NR_TELEFONE_ENT"));
		cClienteSelecionado.NR_CNPJ_ENTREGA=cursor.getString(cursor.getColumnIndex("NR_CNPJ_ENTREGA"));
		cClienteSelecionado.DS_EMAIL=cursor.getString(cursor.getColumnIndex("DS_EMAIL"));
		cClienteSelecionado.CD_CLIENTE_REPRESENTANTE=cursor.getString(cursor.getColumnIndex("CD_CLIENTE_REPRESENTANTE"));
		cClienteSelecionado.DS_OBSERVACAO=cursor.getString(cursor.getColumnIndex("DS_OBSERVACAO"));
		cClienteSelecionado.ID=cursor.getInt(cursor.getColumnIndex("ID"));
		cClienteSelecionado.FG_ATIVO=cursor.getString(cursor.getColumnIndex("FG_ATIVO"));		
	}
}

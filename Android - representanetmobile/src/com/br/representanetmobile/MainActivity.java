package com.br.representanetmobile;

import java.io.IOException;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.br.representanetmobile.R;
import com.br.representanetmobileclasses.cDadosUsuarioLogado;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity 
{
	private EditText login;
	private EditText senha;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		login = (EditText)findViewById(R.id.tbLogin);
		senha = (EditText)findViewById(R.id.tbSenha);
		
		final Button entrar = (Button)findViewById(R.id.btnLogar);
		
		try
		{
			CriarBanco();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}				
				
		entrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				try
				{
					String vazio = "";
					if (login.length() == 0)
					{
						Util.mensagemAlerta("Acesso ao sistema", "Informe o Login!", MainActivity.this);
						return;
					}
					
					if (senha.length() == 0)
					{
						Util.mensagemAlerta("Acesso ao sistema", "Informe a senha!", MainActivity.this);
						return;
					}
					 
					EditText enderecoDestino = (EditText)findViewById(R.id.menuprincipalEnderecoIntegracao);
					Util.URL = enderecoDestino.getText().toString();
					
					
					if (Util.dataHoraAtual().after(Util.converterStringData("2015-02-28")))
					{
						Util.mensagemAlerta("Versão expirada!", "versão expirou no dia 28/02/2015", MainActivity.this);
						return;
					}
					//TODO Se tiver dados não faz sincronismo e abre o menu direto
					boolean buscarDadosServer = true;
					if (verificarRegistro() && loginOk()) 
					{
						buscarDadosServer = false;
						Intent i = new Intent(MainActivity.this, carteirapedidos.class);
						
						Bundle params = new Bundle();
		                
		                String resposta = login.getText().toString();
		                params.putString("usuario", resposta);
		                i.putExtras(params);							

						startActivity(i);
					}
					
					if(buscarDadosServer)
					{						
						if (!UtilWebService.loginRemotoOK(login.getText().toString(), senha.getText().toString()))
						{							
							Util.mensagemAlerta("Usuário ou senha inválido!\nImpossível realizar sincronismo full!", 
												"Sincronismo necessário", 
												MainActivity.this);
						}
						else
						{
							Intent i = new Intent(MainActivity.this, Sincronismo.class);
							Bundle params = new Bundle();
			                
			                String resposta = login.getText().toString();
			                params.putString("usuario", resposta);
			                params.putString("senha", senha.getText().toString());
			                i.putExtras(params);		
							startActivity(i);
						}
					}
				}
				catch(Exception ex)
				{
					Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
				}								
			}
		});
	}
		
	private SQLiteDatabase abrirBanco()
	{
		return  openOrCreateDatabase(Util.nomeBanco, MODE_WORLD_READABLE, null);
	}
	
	private void criarTabelaCidade()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS CIDADES (\n" +
							"    ID_CIDADE  INTEGER         PRIMARY KEY AUTOINCREMENT\n" + 
							"                               NOT NULL,\n" + 
							"    DSC_CIDADE VARCHAR( 100 )  NOT NULL\n" + 
							"                               COLLATE 'BINARY',\n" + 
							"    COD_ESTADO VARCHAR( 2 )    NOT NULL\n" + 
							");";

		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}
	
	private void criarTabelaFrete()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS FRETE (\n" +
							"    ID_FRETE  INTEGER         PRIMARY KEY AUTOINCREMENT\n" + 
							"                               NOT NULL,\n" + 
							"    DESCRICAO VARCHAR( 100 )  NOT NULL\n" +  
							");";

		
		BancoDados.execSQL(sqlString);
		
		sqlString = "DELETE FROM FRETE;";
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO FRETE (DESCRICAO) VALUES('CIF');";
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO FRETE(DESCRICAO) VALUES('FOB');";		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaTipoCobranca()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS TIPO_COBRANCA (\n" +
							"	ID_TIPO_COBRANCA  INTEGER       PRIMARY KEY AUTOINCREMENT\n" + 
							"									NOT NULL,\n" + 
							"	DESCRICAO VARCHAR( 100 )  NOT NULL\n" +  
							");";

		
		BancoDados.execSQL(sqlString);
		
		sqlString = "DELETE FROM TIPO_COBRANCA;";
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO TIPO_COBRANCA (DESCRICAO) VALUES('NENHUMA');";
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO TIPO_COBRANCA(DESCRICAO) VALUES('CARTEIRA');";		
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO TIPO_COBRANCA(DESCRICAO) VALUES('BOLETO');";		
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO TIPO_COBRANCA(DESCRICAO) VALUES('CHEQUE');";		
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO TIPO_COBRANCA(DESCRICAO) VALUES('DUPLICATA');";		
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO TIPO_COBRANCA(DESCRICAO) VALUES('ORDEM PAGTO');";		
		BancoDados.execSQL(sqlString);
		
		sqlString = "INSERT INTO TIPO_COBRANCA(DESCRICAO) VALUES('DINHEIRO');";		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}
		
	private void criarTabelaCliente()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = "CREATE TABLE IF NOT EXISTS CLIENTE (\n" +
						"    ID_REPRESENTANTE         INT             NOT NULL,\n" + 
						"    ID_CLIENTE               INT             NOT NULL,\n" + 
						"    NM_CLIENTE               VARCHAR( 40 )   NOT NULL\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NM_FANTASIA              VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    ID_VENDEDOR              INT             DEFAULT 'NULL',\n" + 
						"    ID_PREPOSTO              INT             DEFAULT 'NULL',\n" + 
						"    NR_IE                    VARCHAR( 18 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_CNPJ                  VARCHAR( 18 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    DS_ENDERECO              VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NM_BAIRRO                VARCHAR( 25 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NM_CIDADE                VARCHAR( 25 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    SG_ESTADO                CHAR( 2 )       DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_CEP                   VARCHAR( 10 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_TELEFONE              VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_FAX                   VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_CELULAR               VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    DS_CONTATO               VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    DS_ENDERECO_COB          VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NM_BAIRRO_COB            VARCHAR( 25 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NM_CIDADE_COB            VARCHAR( 25 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    SG_ESTADO_COB            CHAR( 2 )       DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_CEP_COB               VARCHAR( 10 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_TELEFONE_COB          VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    DS_ENDERECO_ENT          VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NM_BAIRRO_ENT            VARCHAR( 25 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NM_CIDADE_ENT            VARCHAR( 25 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    SG_ESTADO_ENT            CHAR( 2 )       DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_CEP_ENT               VARCHAR( 10 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_TELEFONE_ENT          VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    NR_CNPJ_ENTREGA          VARCHAR( 18 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    DS_EMAIL                 VARCHAR( 60 )   DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    CD_CLIENTE_REPRESENTANTE CHAR( 10 )      DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    DS_OBSERVACAO            VARCHAR( 100 )  DEFAULT 'NULL'\n" + 
						"                                             COLLATE 'BINARY',\n" + 
						"    ID                       INTEGER         PRIMARY KEY AUTOINCREMENT,\n" + 
						"    FG_ATIVO                 VARCHAR( 1 )    NOT NULL\n" + 
						"                                             DEFAULT 'S'\n" + 
						"                                             COLLATE 'BINARY'\n" + 
						");";
		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaComissao()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = "CREATE TABLE IF NOT EXISTS COMISSAO (\n" +
						"    ID                INTEGER        PRIMARY KEY AUTOINCREMENT\n" + 
						"                                     NOT NULL,\n" + 
						"    ID_PEDIDO         INT            DEFAULT '0',\n" + 
						"    ID_REPRESENTANTE  INT            NOT NULL,\n" + 
						"    ID_REPRESENTADA   INT            NOT NULL,\n" + 
						"    NRDOCUMENTO       CHAR( 20 )     DEFAULT 'NULL'\n" + 
						"                                     COLLATE 'BINARY',\n" + 
						"    DATAFATURA        DATE           DEFAULT 'NULL',\n" + 
						"    VALORFATURA       FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    DATAVENCIMENTO    DATE           DEFAULT 'NULL',\n" + 
						"    DATABAIXA         DATE           DEFAULT 'NULL',\n" + 
						"    PCCOMVENDEDOR     FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    VALORCOMVENDEDOR  FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PCCOMPREPOSTO     FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    VALORCOMPREPOSTO  FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PCIMPOSTORENDA    FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    VALORIMPOSTORENDA FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    NUMEROPARCELA     INT            DEFAULT 'NULL',\n" + 
						"    ID_CLIENTE        INT            NOT NULL\n" + 
						");";

		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaEstados()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS ESTADOS (\n" +
							"    ID_ESTADO   INTEGER         PRIMARY KEY AUTOINCREMENT\n" + 
							"                                NOT NULL,\n" + 
							"    DSC_ESTADO  VARCHAR( 100 )  NOT NULL\n" + 
							"                                COLLATE 'BINARY',\n" + 
							"    SIGL_ESTADO VARCHAR( 2 )    NOT NULL\n" + 
							"                                COLLATE 'BINARY'\n" + 
							");";
		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaImplementacao()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS IMPLEMENTACAO (\n" +
							"    ID              INTEGER         PRIMARY KEY AUTOINCREMENT\n" + 
							"                                    NOT NULL,\n" + 
							"    DATASOLICITACAO DATE            DEFAULT 'NULL',\n" + 
							"    TITULO          VARCHAR( 200 )  DEFAULT 'NULL'\n" + 
							"                                    COLLATE 'BINARY',\n" + 
							"    DESCRICAO       TEXT            COLLATE 'BINARY',\n" + 
							"    FONTES          VARCHAR( 256 )  DEFAULT 'NULL'\n" + 
							"                                    COLLATE 'BINARY',\n" + 
							"    DESENVOLVEDOR   VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
							"                                    COLLATE 'BINARY',\n" + 
							"    DATACONCLUSAO   DATE            DEFAULT 'NULL',\n" + 
							"    OBSERVACAO      VARCHAR( 256 )  DEFAULT 'NULL'\n" + 
							"                                    COLLATE 'BINARY'\n" + 
							");";

		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaMensalidade()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS MENSALIDADE (\n" +
							"    ID               INTEGER        PRIMARY KEY ASC AUTOINCREMENT,\n" + 
							"    IDREPRESENTANTE  INT            NOT NULL\n" + 
							"                                    COLLATE 'BINARY',\n" + 
							"    MESANO           INT            DEFAULT 'NULL',\n" + 
							"    DATAPAGAMENTO    DATE           DEFAULT 'NULL',\n" + 
							"    VALORMENSALIDADE FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
							"    PRIMICIA         CHAR( 1 )      DEFAULT 'NULL'\n" + 
							"                                    COLLATE 'BINARY',\n" + 
							"    OBSERVACAO       TEXT           COLLATE 'BINARY',\n" + 
							"    PAGOALLAN        CHAR( 1 )      DEFAULT 'NULL'\n" + 
							"                                    COLLATE 'BINARY',\n" + 
							"    PAGOWAGNER       CHAR( 1 )      DEFAULT 'NULL'\n" + 
							"                                    COLLATE 'BINARY',\n" + 
							"    DATAVENCIMENTO   DATE           DEFAULT 'NULL',\n" + 
							"    PAGOPRIMICIA     CHAR( 1 )      DEFAULT 'NULL'\n" + 
							"                                    COLLATE 'BINARY',\n" + 
							"    VALORPAGO        FLOAT( 5, 2 )  DEFAULT 'NULL'\n" + 
							");";

		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaPedido()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = "CREATE TABLE IF NOT EXISTS PEDIDO (\n" +
						"    ID_REPRESENTANTE      INT            NOT NULL,\n" + 
						"    ID_PEDIDO             INTEGER        PRIMARY KEY AUTOINCREMENT,\n" + 
						"    ID_REPRESENTADA       INT            NOT NULL,\n" + 
						"    ID_VENDEDOR           INT            NOT NULL,\n" + 
						"    NR_PEDIDO             INT            DEFAULT 'NULL',\n" + 
						"    ID_CLIENTE            INT            NOT NULL,\n" + 
						"    ID_PREPOSTO           INT            DEFAULT 'NULL',\n" + 
						"    PC_DESC1              FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PC_DESC2              FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PC_DESC3              FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PC_DESC4              FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PC_DESC5              FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PC_DESC6              FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PC_DESC7              FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    VR_DESCONTO           FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    VR_TOTAL              FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    VR_LIQUIDO            FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    DT_PEDIDO             DATE           DEFAULT 'NULL',\n" + 
						"    DT_ENVIO_REPRESENTADA DATE           DEFAULT 'NULL',\n" + 
						"    DT_ENVIO_CLIENTE      DATE           DEFAULT 'NULL',\n" + 
						"    DT_ENTREGA            DATE           DEFAULT 'NULL',\n" + 
						"    DT_ASSISTENCIA        DATE           DEFAULT 'NULL',\n" + 
						"    FG_FRETE              VARCHAR( 3 )   DEFAULT 'NULL'\n" + 
						"                                         COLLATE 'BINARY',\n" + 
						"    DS_FRETE              VARCHAR( 60 )  DEFAULT 'NULL'\n" + 
						"                                         COLLATE 'BINARY',\n" + 
						"    DS_PAGAMENTO          VARCHAR( 50 )  DEFAULT 'NULL'\n" + 
						"                                         COLLATE 'BINARY',\n" + 
						"    DS_OBSERVACAO         TEXT           COLLATE 'BINARY',\n" + 
						"    DS_ASSISTENCIA        TEXT           COLLATE 'BINARY',\n" + 
						"    IDTABELAPRECO         INT            DEFAULT 'NULL',\n" + 
						"    NR_PEDIDO_CLIENTE     VARCHAR( 20 )  DEFAULT 'NULL'\n" + 
						"                                         COLLATE 'BINARY',\n" + 
						"    TP_COBRANCA           VARCHAR( 20 )  DEFAULT 'NULL'\n" + 
						"                                         COLLATE 'BINARY',\n" + 
						"    QUANTIDADEPARCELA     INT            DEFAULT '1',\n" + 
						"    DT_INCLUSAO           TIMESTAMP      NOT NULL\n" + 
						");";
	
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaItemPedido()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS ITEM_PEDIDO (\n" +
							"    ID_REPRESENTANTE INT     NOT NULL,\n" + 
							"    ID_ITEM          INT,\n" + 
							"    ID_PEDIDO        INT     NOT NULL,\n" + 
							"    CD_PRODUTO       VARCHAR NOT NULL,\n" + 
							"	 DS_PRODUTO 	  VARCHAR NOT NULL,\n" +
							"    ID_REPRESENTADA  INT     NOT NULL,\n" + 							
							"    DS_COMPLEMENTO   TEXT,\n" + 
							"    QT_PRODUTO       INT     NOT NULL,\n" + 
							"    VR_UNITARIO      NUMERIC NOT NULL,\n" + 
							"    VR_TOTAL         NUMERIC,\n" + 
							"    ID               INTEGER PRIMARY KEY AUTOINCREMENT\n" + 
							");";

		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}
	
	private void criarTabelaPreposto()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = "CREATE TABLE IF NOT EXISTS PREPOSTO (\n" +
						"    ID_REPRESENTANTE INT            NOT NULL,\n" + 
						"    ID_PREPOSTO      INT            NOT NULL,\n" + 
						"    NM_PREPOSTO      VARCHAR( 40 )  NOT NULL\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    NR_RG            VARCHAR( 18 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    NR_CPF           VARCHAR( 18 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    DS_ENDERECO      VARCHAR( 40 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    NM_BAIRRO        VARCHAR( 25 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    NM_CIDADE        VARCHAR( 25 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    SG_ESTADO        CHAR( 2 )      DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    NR_CEP           VARCHAR( 10 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    NR_TELEFONE      VARCHAR( 30 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    NR_FAX           VARCHAR( 30 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    NR_CELULAR       VARCHAR( 30 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    DS_EMAIL         VARCHAR( 60 )  DEFAULT 'NULL'\n" + 
						"                                    COLLATE 'BINARY',\n" + 
						"    PCCOMISSAO       FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
						"    PRIMARY KEY ( ID_REPRESENTANTE, ID_PREPOSTO )\n" + 
						");";
		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaProduto()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS PRODUTO (\n" +
							"    ID_REPRESENTANTE   INT            NOT NULL,\n" + 
							"    ID_REPRESENTADA    INT            NOT NULL,\n" + 
							"    CD_PRODUTO         VARCHAR( 20 )  NOT NULL\n" + 
							"                                      COLLATE 'BINARY',\n" + 
							"    DS_PRODUTO         VARCHAR( 60 )  NOT NULL\n" + 
							"                                      COLLATE 'BINARY',\n" + 
							"    DT_HORA_IMPORTACAO DATETIME       DEFAULT 'NULL',\n" + 
							"    FG_ATIVO           CHAR( 3 )      NOT NULL\n" + 
							"                                      DEFAULT 'SIM'\n" + 
							"                                      COLLATE 'BINARY',\n" + 
							"    ID_PRODUTO         INTEGER        PRIMARY KEY AUTOINCREMENT\n" + 
							");";

		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaProdutoTabelado()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS PRODUTOTABELADO (\n" +
							"    IDREPRESENTANTE INT            NOT NULL,\n" + 
							"    IDREPRESENTADA  INT            NOT NULL,\n" + 
							"    CDPRODUTO       VARCHAR( 20 )  NOT NULL,\n" + 
							"    IDTABELA        INT            NOT NULL,\n" + 
							"    VRPRODUTO       FLOAT( 5, 2 )  DEFAULT 'NULL',\n" + 
							"    PRIMARY KEY ( IDREPRESENTANTE, IDREPRESENTADA, CDPRODUTO, IDTABELA )\n" + 
							");";

		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaRepresentada()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = "CREATE TABLE IF NOT EXISTS REPRESENTADA (\n" +
						"    ID_REPRESENTANTE    INT             NOT NULL,\n" + 
						"    ID_REPRESENTADA     INT             NOT NULL,\n" + 
						"    NM_REPRESENTADA     VARCHAR( 40 )   NOT NULL,\n" + 
						"    NM_FANTASIA         VARCHAR( 40 )   DEFAULT 'NULL',\n" + 
						"    NR_IE               VARCHAR( 18 )   DEFAULT 'NULL',\n" + 
						"    NR_CNPJ             VARCHAR( 18 )   DEFAULT 'NULL',\n" + 
						"    DS_ENDERECO         VARCHAR( 40 )   DEFAULT 'NULL',\n" + 
						"    NM_BAIRRO           VARCHAR( 25 )   DEFAULT 'NULL',\n" + 
						"    SG_ESTADO           CHAR( 2 )       DEFAULT 'NULL',\n" + 
						"    NM_CIDADE           VARCHAR( 25 )   DEFAULT 'NULL',\n" + 
						"    NR_CEP              VARCHAR( 10 )   DEFAULT 'NULL',\n" + 
						"    NR_TELEFONE         VARCHAR( 30 )   DEFAULT 'NULL',\n" + 
						"    NR_FAX              VARCHAR( 30 )   DEFAULT 'NULL',\n" + 
						"    NR_CELULAR          VARCHAR( 30 )   DEFAULT 'NULL',\n" + 
						"    DS_EMAIL            VARCHAR( 60 )   DEFAULT 'NULL',\n" + 
						"    LOGOTIPO            BLOB,\n" + 
						"    DS_LOCAL_EXPORTACAO VARCHAR( 60 )   DEFAULT 'NULL',\n" + 
						"    DS_NOME_ARQUIVO     VARCHAR( 60 )   DEFAULT 'NULL',\n" + 
						"    DS_OBSERVACAO       VARCHAR( 100 )  DEFAULT 'NULL',\n" + 
						"    NUMERACAOAUTOMATICA VARCHAR( 1 )    DEFAULT 'NULL',\n" + 
						"    ULTIMONUMERO        INT             DEFAULT 'NULL',\n" + 
						"    PCCOMISSAO          FLOAT( 0, 0 )   DEFAULT '0',\n" + 
						"    TIPOCOMISSAO        CHAR( 1 )       DEFAULT 'NULL',\n" + 
						"    FG_ATIVO            CHAR( 1 )       NOT NULL,\n" + 
						"    PRIMARY KEY ( ID_REPRESENTANTE, ID_REPRESENTADA )\n" + 
						");";

		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaRepresentante()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS REPRESENTANTE (\n" +
							"    ID_REPRESENTANTE       INTEGER         PRIMARY KEY AUTOINCREMENT\n" + 
							"                                           NOT NULL,\n" + 
							"    NM_REPRESENTANTE       VARCHAR( 40 )   NOT NULL\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NM_FANTASIA            VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NR_IE                  VARCHAR( 18 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NR_CNPJ                VARCHAR( 18 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    DS_ENDERECO            VARCHAR( 80 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NM_BAIRRO              VARCHAR( 25 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NM_CIDADE              VARCHAR( 25 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    SG_ESTADO              CHAR( 2 )       DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NR_CEP                 VARCHAR( 10 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NR_TELEFONE            VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NR_FAX                 VARCHAR( 30 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    NR_CELULAR             VARCHAR( 60 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    DS_EMAIL               VARCHAR( 60 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    LOGOTIPO               BLOB,\n" + 
							"    DS_OBSERVACAO          VARCHAR( 100 )  DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    DIAPAGAMENTO           INT             DEFAULT '1',\n" + 
							"    VALORMENSALIDADE       FLOAT( 0, 0 )   DEFAULT '30',\n" + 
							"    NUMERACAOAUTOMATICA    VARCHAR( 1 )    DEFAULT 'N'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    ULTIMONUMERO           INT             DEFAULT '0',\n" + 
							"    SMTPSERVER             VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    SMTPUSUARIO            VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    SMTPSENHA              VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    SMTPSSL                CHAR( 1 )       DEFAULT 'S'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    SMTPAUTENTICA          CHAR( 1 )       DEFAULT 'S'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    SMTPPORTA              INT             DEFAULT '0',\n" + 
							"    ARQUIVOPEDIDO          VARCHAR( 40 )   DEFAULT 'NULL'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    TIPOPLANO              INT             DEFAULT 'NULL',\n" + 
							"    CONFIRMACAOEMAILPEDIDO CHAR( 1 )       NOT NULL\n" + 
							"                                           DEFAULT 'S'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    COPIAEMAILPEDIDO       CHAR( 1 )       NOT NULL\n" + 
							"                                           DEFAULT 'N'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    ADICIONALPEDIDO        VARCHAR( 500 )  NOT NULL\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    USAMENSAGEMPADRAO      CHAR( 1 )       NOT NULL\n" + 
							"                                           DEFAULT 'S'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    ENVIAEMAILCLIENTE      CHAR( 1 )       NOT NULL\n" + 
							"                                           DEFAULT 'N'\n" + 
							"                                           COLLATE 'BINARY',\n" + 
							"    FG_LOGOTIPO            CHAR( 1 )       DEFAULT 'N'\n" + 
							"                                           COLLATE 'BINARY'\n" + 
							");";

		
		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}

	private void criarTabelaPreco()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS TABELAPRECO (\n" +
							"    IDTABELA        INTEGER        PRIMARY KEY AUTOINCREMENT\n" + 
							"                                   NOT NULL,\n" + 
							"    IDREPRESENTADA  INT            NOT NULL,\n" + 
							"    IDREPRESENTANTE INT            NOT NULL,\n" + 
							"    DESCRICAO       VARCHAR( 50 )  DEFAULT 'NULL'\n" + 
							");";

		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}
		
	private void criarTabelaUsuario()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	"CREATE TABLE IF NOT EXISTS USUARIO (\n" +
							"    NM_USUARIO       VARCHAR( 100 )  PRIMARY KEY\n" + 
							"                                     NOT NULL\n" + 
							"                                     COLLATE 'BINARY',\n" + 
							"    DS_SENHA         VARCHAR( 20 )   NOT NULL\n" + 
							"                                     COLLATE 'BINARY',\n" + 
							"    ID_REPRESENTANTE INT             NOT NULL,\n" + 
							"    NM_COMPLETO      VARCHAR( 40 )   NOT NULL\n" + 
							"                                     COLLATE 'BINARY',\n" + 
							"    FG_LIBERADO      CHAR( 1 )       DEFAULT 'NULL'\n" + 
							"                                     COLLATE 'BINARY',\n" + 
							"	 ENDERECOWEBSERVICE TEXT\n" + 
							");";


		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}		
		
	private void criarTabelaVendedor()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		
		String sqlString = 	
				"CREATE TABLE IF NOT EXISTS VENDEDOR (\n" +
						"    ID_REPRESENTANTE INT            NOT NULL,\n" + 
						"    ID_VENDEDOR      INT            NOT NULL,\n" + 
						"    NM_VENDEDOR      VARCHAR( 40 )  NOT NULL,\n" + 
						"    NR_RG            VARCHAR( 14 )  DEFAULT 'NULL',\n" + 
						"    NR_CPF           VARCHAR( 14 )  DEFAULT 'NULL',\n" + 
						"    DS_ENDERECO      VARCHAR( 40 )  DEFAULT 'NULL',\n" + 
						"    NM_BAIRRO        VARCHAR( 25 )  DEFAULT 'NULL',\n" + 
						"    NM_CIDADE        VARCHAR( 25 )  DEFAULT 'NULL',\n" + 
						"    SG_ESTADO        CHAR( 2 )      DEFAULT 'NULL',\n" + 
						"    NR_CEP           VARCHAR( 10 )  DEFAULT 'NULL',\n" + 
						"    NR_TELEFONE      VARCHAR( 30 )  DEFAULT 'NULL',\n" + 
						"    NR_FAX           VARCHAR( 30 )  DEFAULT 'NULL',\n" + 
						"    NR_CELULAR       VARCHAR( 30 )  DEFAULT 'NULL',\n" + 
						"    DS_EMAIL         VARCHAR( 60 )  DEFAULT 'NULL',\n" + 
						"    ID               INTEGER        NOT NULL,\n" + 
						"    PRIMARY KEY ( ID_REPRESENTANTE, ID_VENDEDOR )\n" + 
						");";

		BancoDados.execSQL(sqlString);
		
		BancoDados.close();
	}		

	public void CriarBanco()
	{
		//BancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE, null);
		try
		{
			criarTabelaCidade();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}		
		try
		{
			criarTabelaFrete();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaCliente();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}	
		try
		{
			criarTabelaComissao();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaEstados();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaImplementacao();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaMensalidade();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaPedido();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaPreposto();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaProduto();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaProdutoTabelado();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaRepresentada();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaRepresentante();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}

		try
		{
			criarTabelaPreco();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}	
		try
		{
			criarTabelaUsuario();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}	
		try
		{
			criarTabelaVendedor();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}
		try
		{
			criarTabelaItemPedido();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}	
		
		try
		{
			criarTabelaTipoCobranca();
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();		
		}		
	}
	
	public boolean loginOk()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		String sql = "SELECT USUARIO.*,ROWID as _id FROM USUARIO WHERE NM_USUARIO = " + Util.apostrofo(login.getText().toString()) + " AND DS_SENHA = " + Util.apostrofo(senha.getText().toString());
		Cursor cursorTemp = BancoDados.rawQuery(sql, null);
		try
		{
			if (cursorTemp.getCount() > 0)
			{
				cDadosUsuarioLogado.usuario_logado = login.getText().toString();
				cDadosUsuarioLogado.senha_logado = senha.getText().toString();
				while (cursorTemp.moveToNext())
				{
					cDadosUsuarioLogado.id_Representante = cursorTemp.getInt(cursorTemp.getColumnIndex("ID_REPRESENTANTE"));
					
				}
				
				sql = "SELECT REPRESENTADA.*,ROWID as _id FROM REPRESENTADA WHERE ID_REPRESENTANTE = " + cDadosUsuarioLogado.id_Representante;
				cursorTemp = BancoDados.rawQuery(sql, null);
				while (cursorTemp.moveToNext())
				{
					cDadosUsuarioLogado.id_Representada = cursorTemp.getInt(cursorTemp.getColumnIndex("ID_REPRESENTADA"));
				}
				
				sql = "SELECT REPRESENTANTE.*,ROWID as _id FROM REPRESENTANTE WHERE ID_REPRESENTANTE = " + cDadosUsuarioLogado.id_Representante;
				cursorTemp = BancoDados.rawQuery(sql, null);
				while (cursorTemp.moveToNext())
				{
					cDadosUsuarioLogado.ultimo_nro_pedido = cursorTemp.getInt(cursorTemp.getColumnIndex("ULTIMONUMERO"));
				}
				
				if (cursorTemp.getCount() > 0)
				{			
					sql = "SELECT VENDEDOR.*,ROWID as _id FROM VENDEDOR WHERE ID_REPRESENTANTE = " + cDadosUsuarioLogado.id_Representante;
					cursorTemp = BancoDados.rawQuery(sql, null);
					while (cursorTemp.moveToNext())
					{
						cDadosUsuarioLogado.id_Vendedor = cursorTemp.getInt(cursorTemp.getColumnIndex("ID_VENDEDOR"));					
					}
				}
				sql = "SELECT PREPOSTO.*,ROWID as _id FROM PREPOSTO WHERE ID_REPRESENTANTE = " + cDadosUsuarioLogado.id_Representante;
				cursorTemp = BancoDados.rawQuery(sql, null);
				if (cursorTemp.getCount() > 0)
				{
					while (cursorTemp.moveToNext())
					{
						cDadosUsuarioLogado.id_Preposto = cursorTemp.getInt(cursorTemp.getColumnIndex("ID_PREPOSTO"));
					}		
				}
				BancoDados.close();
				return true;
			}
		}
		catch(Exception ex)
		{
			Toast.makeText(MainActivity.this, "loginOk - " + ex.getMessage().toString(), Toast.LENGTH_LONG).show();
		}
		finally
		{
			BancoDados.close();
			cursorTemp.deactivate();
		}
		return false;		
	}	
				
	private Boolean verificarRegistro()
	{
		SQLiteDatabase BancoDados = abrirBanco();
		Cursor cursor = BancoDados.rawQuery("select * from usuario", null);
		
		if (cursor.getCount() != 0)
		{
			cursor.moveToFirst();
			BancoDados.close();
			return true;
		}
		BancoDados.close();
		cursor.deactivate();
		return false;
	}	
}

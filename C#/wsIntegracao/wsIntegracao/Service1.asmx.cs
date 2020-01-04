using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace wsIntegracao
{
    /// <summary>
    /// Summary description for Service1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Service1 : System.Web.Services.WebService
    {

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }

        [WebMethod]
        public string usuarioOk(String usuario, String senha)
        {
            String sql = "select count(*)\n" +
                         "from representanet1.usuario\n" +
                         "where nm_usuario = '" + usuario + "'\n" +
                         "  and ds_senha = '" + senha + "'\n";

            DataTable dtTemp = bancoDados.getDataTable(sql);
            if (dtTemp != null && dtTemp.Rows.Count > 0) //Usuário e senha OK
                return "OK!";            

            return "Usuário bloqueado!";
        }

        #region Dados de cadastros do representante / preposto / usuario
        [WebMethod]
        public string[] retornarRepresentanteFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);


                string sqlString =  "SELECT  REPRESENTANTE.ID_REPRESENTANTE,\n" +
                                    "        REPRESENTANTE.NM_REPRESENTANTE,\n" +
                                    "        REPRESENTANTE.NM_FANTASIA,\n" +
                                    "        REPRESENTANTE.NR_IE,\n" +
                                    "        REPRESENTANTE.NR_CNPJ,\n" +
                                    "        REPRESENTANTE.DS_ENDERECO,\n" +
                                    "        REPRESENTANTE.NM_BAIRRO,\n" +
                                    "        REPRESENTANTE.NM_CIDADE,\n" +
                                    "        REPRESENTANTE.SG_ESTADO,\n" +
                                    "        REPRESENTANTE.NR_CEP,\n" +
                                    "        REPRESENTANTE.NR_TELEFONE,\n" +
                                    "        REPRESENTANTE.NR_FAX,\n" +
                                    "        REPRESENTANTE.NR_CELULAR,\n" +
                                    "        REPRESENTANTE.DS_EMAIL,\n" +
                                    "        REPRESENTANTE.LOGOTIPO,\n" +
                                    "        REPRESENTANTE.DS_OBSERVACAO,\n" +
                                    "        REPRESENTANTE.DIAPAGAMENTO,\n" +
                                    "        REPRESENTANTE.VALORMENSALIDADE,\n" +
                                    "        REPRESENTANTE.NUMERACAOAUTOMATICA,\n" +
                                    "        REPRESENTANTE.ULTIMONUMERO,\n" +
                                    "        REPRESENTANTE.SMTPSERVER,\n" +
                                    "        REPRESENTANTE.SMTPUSUARIO,\n" +
                                    "        REPRESENTANTE.SMTPSENHA,\n" +
                                    "        REPRESENTANTE.SMTPSSL,\n" +
                                    "        REPRESENTANTE.SMTPAUTENTICA,\n" +
                                    "        REPRESENTANTE.SMTPPORTA,\n" +
                                    "        REPRESENTANTE.ARQUIVOPEDIDO,\n" +
                                    "        REPRESENTANTE.TIPOPLANO,\n" +
                                    "        REPRESENTANTE.CONFIRMACAOEMAILPEDIDO,\n" +
                                    "        REPRESENTANTE.COPIAEMAILPEDIDO,\n" +
                                    "        REPRESENTANTE.ADICIONALPEDIDO,\n" +
                                    "        REPRESENTANTE.USAMENSAGEMPADRAO,\n" +
                                    "        REPRESENTANTE.ENVIAEMAILCLIENTE,\n" +
                                    "        REPRESENTANTE.FG_LOGOTIPO\n" +
                                    "FROM REPRESENTANTE\n" +
                                    "WHERE REPRESENTANTE.ID_REPRESENTANTE ="+Convert.ToString(idRepresentante);

                DataTable dtConsultaRepresentante = bancoDados.getDataTable(sqlString);
                String[] insertRepresentante = new String[dtConsultaRepresentante.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaRepresentante.Rows.Count; indice++)
                {
                    insertRepresentante[indice] =   "INSERT INTO REPRESENTANTE\n" +
                                                    "(ID_REPRESENTANTE,\n" + 
                                                    "NM_REPRESENTANTE,\n" + 
                                                    "NM_FANTASIA,\n" + 
                                                    "NR_IE,\n" + 
                                                    "NR_CNPJ,\n" + 
                                                    "DS_ENDERECO,\n" + 
                                                    "NM_BAIRRO,\n" + 
                                                    "NM_CIDADE,\n" + 
                                                    "SG_ESTADO,\n" + 
                                                    "NR_CEP,\n" + 
                                                    "NR_TELEFONE,\n" + 
                                                    "NR_FAX,\n" + 
                                                    "NR_CELULAR,\n" + 
                                                    "DS_EMAIL,\n" + 
                                                    "LOGOTIPO,\n" + 
                                                    "DS_OBSERVACAO,\n" + 
                                                    "DIAPAGAMENTO,\n" + 
                                                    "VALORMENSALIDADE,\n" + 
                                                    "NUMERACAOAUTOMATICA,\n" + 
                                                    "ULTIMONUMERO,\n" + 
                                                    "SMTPSERVER,\n" + 
                                                    "SMTPUSUARIO,\n" + 
                                                    "SMTPSENHA,\n" + 
                                                    "SMTPSSL,\n" + 
                                                    "SMTPAUTENTICA,\n" + 
                                                    "SMTPPORTA,\n" + 
                                                    "ARQUIVOPEDIDO,\n" + 
                                                    "TIPOPLANO,\n" + 
                                                    "CONFIRMACAOEMAILPEDIDO,\n" + 
                                                    "COPIAEMAILPEDIDO,\n" + 
                                                    "ADICIONALPEDIDO,\n" + 
                                                    "USAMENSAGEMPADRAO,\n" + 
                                                    "ENVIAEMAILCLIENTE,\n" + 
                                                    "FG_LOGOTIPO)\n" + 
                                                    "VALUES\n" + 
                                                    "("+
                                                    Convert.ToString(dtConsultaRepresentante.Rows[indice]["ID_REPRESENTANTE"]) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NM_REPRESENTANTE"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NM_FANTASIA"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NR_IE"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NR_CNPJ"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["DS_ENDERECO"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NM_BAIRRO"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NM_CIDADE"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["SG_ESTADO"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NR_CEP"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NR_TELEFONE"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NR_FAX"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NR_CELULAR"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["DS_EMAIL"])) + "," +
                                                    "NULL," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["DS_OBSERVACAO"])) + "," +
                                                    Convert.ToString(dtConsultaRepresentante.Rows[indice]["DIAPAGAMENTO"]) + "," +
                                                    Convert.ToString(dtConsultaRepresentante.Rows[indice]["VALORMENSALIDADE"]) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["NUMERACAOAUTOMATICA"])) + "," +
                                                    Convert.ToString(dtConsultaRepresentante.Rows[indice]["ULTIMONUMERO"]) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["SMTPSERVER"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["SMTPUSUARIO"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["SMTPSENHA"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["SMTPSSL"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["SMTPAUTENTICA"])) + "," +
                                                    Convert.ToString(dtConsultaRepresentante.Rows[indice]["SMTPPORTA"]) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["ARQUIVOPEDIDO"])) + "," +
                                                    Convert.ToString(dtConsultaRepresentante.Rows[indice]["TIPOPLANO"]) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["CONFIRMACAOEMAILPEDIDO"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["COPIAEMAILPEDIDO"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["ADICIONALPEDIDO"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["USAMENSAGEMPADRAO"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["ENVIAEMAILCLIENTE"])) + "," +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentante.Rows[indice]["FG_LOGOTIPO"])) + ")";

                }
                return insertRepresentante;
            }
            return null;
        }

        [WebMethod]
        public string[] retornarRepresentadaFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));

            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                 Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                 string sqlConsulta ="SELECT  REPRESENTADA.ID_REPRESENTANTE,\n" +
                                     "        REPRESENTADA.ID_REPRESENTADA,\n" +
                                     "        REPRESENTADA.NM_REPRESENTADA,\n" +
                                     "        REPRESENTADA.NM_FANTASIA,\n" +
                                     "        REPRESENTADA.NR_IE,\n" +
                                     "        REPRESENTADA.NR_CNPJ,\n" +
                                     "        REPRESENTADA.DS_ENDERECO,\n" +
                                     "        REPRESENTADA.NM_BAIRRO,\n" +
                                     "        REPRESENTADA.SG_ESTADO,\n" +
                                     "        REPRESENTADA.NM_CIDADE,\n" +
                                     "        REPRESENTADA.NR_CEP,\n" +
                                     "        REPRESENTADA.NR_TELEFONE,\n" +
                                     "        REPRESENTADA.NR_FAX,\n" +
                                     "        REPRESENTADA.NR_CELULAR,\n" +
                                     "        REPRESENTADA.DS_EMAIL,\n" +
                                     "        REPRESENTADA.LOGOTIPO,\n" +
                                     "        REPRESENTADA.DS_LOCAL_EXPORTACAO,\n" +
                                     "        REPRESENTADA.DS_NOME_ARQUIVO,\n" +
                                     "        REPRESENTADA.DS_OBSERVACAO,\n" +
                                     "        REPRESENTADA.NUMERACAOAUTOMATICA,\n" +
                                     "        REPRESENTADA.ULTIMONUMERO,\n" +
                                     "        REPRESENTADA.PCCOMISSAO,\n" +
                                     "        REPRESENTADA.TIPOCOMISSAO,\n" +
                                     "        REPRESENTADA.FG_ATIVO\n" +
                                     "FROM REPRESENTADA\n" +
                                     "WHERE REPRESENTADA.FG_ATIVO = 'S' AND REPRESENTADA.ID_REPRESENTANTE =" + Convert.ToString(idRepresentante);

                 DataTable dtConsultaRepresentada = bancoDados.getDataTable(sqlConsulta);
                 String[] insertRepresentada = new String[dtConsultaRepresentada.Rows.Count];
                 for (Int32 indice = 0; indice < dtConsultaRepresentada.Rows.Count; indice++)
                 {
                     insertRepresentada[indice] =   "INSERT INTO REPRESENTADA\n" +
                                                    "(ID_REPRESENTANTE,\n" +
                                                    "ID_REPRESENTADA,\n" +
                                                    "NM_REPRESENTADA,\n" +
                                                    "NM_FANTASIA,\n" +
                                                    "NR_IE,\n" +
                                                    "NR_CNPJ,\n" +
                                                    "DS_ENDERECO,\n" +
                                                    "NM_BAIRRO,\n" +
                                                    "SG_ESTADO,\n" +
                                                    "NM_CIDADE,\n" +
                                                    "NR_CEP,\n" +
                                                    "NR_TELEFONE,\n" +
                                                    "NR_FAX,\n" +
                                                    "NR_CELULAR,\n" +
                                                    "DS_EMAIL,\n" +
                                                    "LOGOTIPO,\n" +
                                                    "DS_LOCAL_EXPORTACAO,\n" +
                                                    "DS_NOME_ARQUIVO,\n" +
                                                    "DS_OBSERVACAO,\n" +
                                                    "NUMERACAOAUTOMATICA,\n" +
                                                    "ULTIMONUMERO,\n" +
                                                    "PCCOMISSAO,\n" +
                                                    "TIPOCOMISSAO,\n" +
                                                    "FG_ATIVO)\n" +
                                                    "VALUES\n" +
                                                    "(" +
                                                    Convert.ToString(dtConsultaRepresentada.Rows[indice]["ID_REPRESENTANTE"]) + ",\n" +
                                                    Convert.ToString(dtConsultaRepresentada.Rows[indice]["ID_REPRESENTADA"]) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NM_REPRESENTADA"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NM_FANTASIA"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NR_IE"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NR_CNPJ"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["DS_ENDERECO"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NM_BAIRRO"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["SG_ESTADO"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NM_CIDADE"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NR_CEP"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NR_TELEFONE"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NR_FAX"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NR_CELULAR"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["DS_EMAIL"])) + ",\n" +
                                                    /*Convert.ToString(dtConsultaRepresentada.Rows[indice]["LOGOTIPO"]) +*/ "NULL,\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["DS_LOCAL_EXPORTACAO"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["DS_NOME_ARQUIVO"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["DS_OBSERVACAO"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["NUMERACAOAUTOMATICA"])) + ",\n" +
                                                    Convert.ToString(dtConsultaRepresentada.Rows[indice]["ULTIMONUMERO"]) + ",\n" +
                                                    bancoDados.trocarVirgulaPonto(Convert.ToString(dtConsultaRepresentada.Rows[indice]["PCCOMISSAO"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["TIPOCOMISSAO"])) + ",\n" +
                                                    bancoDados.apostrofo(Convert.ToString(dtConsultaRepresentada.Rows[indice]["FG_ATIVO"])) + ");";
                 }
                 return insertRepresentada;
             }
            return null;
        }

        [WebMethod]
        public string[] retornarUsuarioFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario,senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                String[] insert = new String[dtUsuario.Rows.Count];
                for (Int32 indice = 0; indice < dtUsuario.Rows.Count; indice++)
                {
                    insert[indice] =    "INSERT INTO USUARIO\n" +
                                        "(NM_USUARIO,\n" + 
                                        "DS_SENHA,\n" + 
                                        "ID_REPRESENTANTE,\n" + 
                                        "NM_COMPLETO,\n" + 
                                        "FG_LIBERADO)\n" + 
                                        "VALUES\n" + 
                                        "("+
                                        bancoDados.apostrofo(Convert.ToString(dtUsuario.Rows[indice]["NM_USUARIO"])) + "," +
                                        bancoDados.apostrofo(Convert.ToString(dtUsuario.Rows[indice]["DS_SENHA"])) + "," +
                                        Convert.ToString(dtUsuario.Rows[indice]["ID_REPRESENTANTE"]) + "," +
                                        bancoDados.apostrofo(Convert.ToString(dtUsuario.Rows[indice]["NM_COMPLETO"])) + "," +
                                        bancoDados.apostrofo(Convert.ToString(dtUsuario.Rows[indice]["FG_LIBERADO"])) + ")";

                }
                return insert;
            }
            return null;
        }

        [WebMethod]
        public string[] retornarVendedorFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                String sqlString =  "SELECT  VENDEDOR.ID_REPRESENTANTE,\n" +
                                    "        VENDEDOR.ID_VENDEDOR,\n" +
                                    "        VENDEDOR.NM_VENDEDOR,\n" +
                                    "        VENDEDOR.NR_RG,\n" +
                                    "        VENDEDOR.NR_CPF,\n" +
                                    "        VENDEDOR.DS_ENDERECO,\n" +
                                    "        VENDEDOR.NM_BAIRRO,\n" +
                                    "        VENDEDOR.NM_CIDADE,\n" +
                                    "        VENDEDOR.SG_ESTADO,\n" +
                                    "        VENDEDOR.NR_CEP,\n" +
                                    "        VENDEDOR.NR_TELEFONE,\n" +
                                    "        VENDEDOR.NR_FAX,\n" +
                                    "        VENDEDOR.NR_CELULAR,\n" +
                                    "        VENDEDOR.DS_EMAIL,\n" +
                                    "        VENDEDOR.ID\n" +
                                    "FROM    VENDEDOR\n" +
                                    "WHERE   VENDEDOR.ID_REPRESENTANTE =" + Convert.ToString(idRepresentante);

                DataTable dtConsultaTabela = bancoDados.getDataTable(sqlString);
                String[] insert = new String[dtConsultaTabela.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaTabela.Rows.Count; indice++)
                {
                    insert[indice] = "INSERT INTO VENDEDOR\n" +
                                    "(ID_REPRESENTANTE,\n" +
                                    "ID_VENDEDOR,\n" +
                                    "NM_VENDEDOR,\n" +
                                    "NR_RG,\n" +
                                    "NR_CPF,\n" +
                                    "DS_ENDERECO,\n" +
                                    "NM_BAIRRO,\n" +
                                    "NM_CIDADE,\n" +
                                    "SG_ESTADO,\n" +
                                    "NR_CEP,\n" +
                                    "NR_TELEFONE,\n" +
                                    "NR_FAX,\n" +
                                    "NR_CELULAR,\n" +
                                    "DS_EMAIL,\n" +
                                    "ID)\n" +
                                    "VALUES\n" +
                                    "(" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_REPRESENTANTE"]) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_VENDEDOR"]) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_VENDEDOR"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_RG"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CPF"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_ENDERECO"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_BAIRRO"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_CIDADE"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["SG_ESTADO"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CEP"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_TELEFONE"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_FAX"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CELULAR"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_EMAIL"])) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID"]) + 
                                    ");";
                }
                return insert;
            }
            return null;
        }

        [WebMethod]
        public string[] retornarPrepostoFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                String sqlString = "SELECT  PREPOSTO.ID_REPRESENTANTE,\n" +
                                    "       PREPOSTO.ID_PREPOSTO,\n" +
                                    "       PREPOSTO.NM_PREPOSTO,\n" +
                                    "       PREPOSTO.NR_RG,\n" +
                                    "       PREPOSTO.NR_CPF,\n" +
                                    "       PREPOSTO.DS_ENDERECO,\n" +
                                    "       PREPOSTO.NM_BAIRRO,\n" +
                                    "       PREPOSTO.NM_CIDADE,\n" +
                                    "       PREPOSTO.SG_ESTADO,\n" +
                                    "       PREPOSTO.NR_CEP,\n" +
                                    "       PREPOSTO.NR_TELEFONE,\n" +
                                    "       PREPOSTO.NR_FAX,\n" +
                                    "       PREPOSTO.NR_CELULAR,\n" +
                                    "       PREPOSTO.DS_EMAIL,\n" +
                                    "       PREPOSTO.PCCOMISSAO\n" +
                                    "FROM   PREPOSTO\n" +
                                    "WHERE  PREPOSTO.ID_REPRESENTANTE =" + Convert.ToString(idRepresentante);

                DataTable dtConsultaTabela = bancoDados.getDataTable(sqlString);
                String[] insert = new String[dtConsultaTabela.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaTabela.Rows.Count; indice++)
                {
                    String pcComissao = bancoDados.trocarVirgulaPonto(Convert.ToString(dtConsultaTabela.Rows[indice]["pcComissao"]));
                    if (String.IsNullOrEmpty(pcComissao))
                        pcComissao = "NULL";
                    insert[indice] ="INSERT INTO PREPOSTO\n" +
                                    "(ID_REPRESENTANTE,\n" +
                                    "ID_PREPOSTO,\n" +
                                    "NM_PREPOSTO,\n" +
                                    "NR_RG,\n" +
                                    "NR_CPF,\n" +
                                    "DS_ENDERECO,\n" +
                                    "NM_BAIRRO,\n" +
                                    "NM_CIDADE,\n" +
                                    "SG_ESTADO,\n" +
                                    "NR_CEP,\n" +
                                    "NR_TELEFONE,\n" +
                                    "NR_FAX,\n" +
                                    "NR_CELULAR,\n" +
                                    "DS_EMAIL,\n" +
                                    "PCCOMISSAO)\n" +
                                    "VALUES\n" +
                                    "(" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_REPRESENTANTE"]) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_PREPOSTO"])+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_PREPOSTO"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_RG"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CPF"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_ENDERECO"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_BAIRRO"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_CIDADE"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["SG_ESTADO"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CEP"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_TELEFONE"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_FAX"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CELULAR"]))+ "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_EMAIL"]))+ "," +
                                    pcComissao +
                                    ");";
                }
                return insert;
            }
            return null;
        }

        #endregion

        #region Dados regionais - Cidades e Estados
        [WebMethod]
        public string[] retornarEstadosFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                String sqlString = "SELECT ESTADOS.ID_ESTADO,\n" +
                                    "       ESTADOS.DSC_ESTADO,\n" +
                                    "       ESTADOS.SIGL_ESTADO\n" +
                                    "FROM   ESTADOS\n";

                DataTable dtConsultaTabela = bancoDados.getDataTable(sqlString);
                String[] insert = new String[dtConsultaTabela.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaTabela.Rows.Count; indice++)
                {
                    insert[indice] = "INSERT INTO ESTADOS\n" +
                                    "(ID_ESTADO,\n" +
                                    "DSC_ESTADO,\n" +
                                    "SIGL_ESTADO)\n" +
                                    "VALUES\n" +
                                    "(" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_ESTADO"]) + ",\n" +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DSC_ESTADO"])) + ",\n" +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["SIGL_ESTADO"])) +
                                    ");";
                }
                return insert;
            }
            return null;
        }

        [WebMethod]
        public string[] retornarCidadesFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                String sqlString = "SELECT  CIDADES.ID_CIDADE,\n" +
                                    "       CIDADES.DSC_CIDADE,\n" +
                                    "       CIDADES.COD_ESTADO\n" +
                                    "FROM   CIDADES\n";

                DataTable dtConsultaTabela = bancoDados.getDataTable(sqlString);
                String[] insert = new String[dtConsultaTabela.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaTabela.Rows.Count; indice++)
                {
                    insert[indice] = "INSERT INTO CIDADES\n" +
                                    "(ID_CIDADE,\n" +
                                    "DSC_CIDADE,\n" +
                                    "COD_ESTADO)\n" +
                                    "VALUES\n" +
                                    "(" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_CIDADE"]) + ",\n" +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DSC_CIDADE"])) + ",\n" +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["COD_ESTADO"])) +
                                    ");";
                }
                return insert;
            }
            return null;
        }

        #endregion

        [WebMethod]
        public string[] retornarClientesFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                String sqlString =  "SELECT    CLIENTE.ID_REPRESENTANTE,\n" +
                                    "          CLIENTE.ID_CLIENTE,\n" +
                                    "          CLIENTE.NM_CLIENTE,\n" +
                                    "          CLIENTE.NM_FANTASIA,\n" +
                                    "          CLIENTE.ID_VENDEDOR,\n" +
                                    "          CLIENTE.ID_PREPOSTO,\n" +
                                    "          CLIENTE.NR_IE,\n" +
                                    "          CLIENTE.NR_CNPJ,\n" +
                                    "          CLIENTE.DS_ENDERECO,\n" +
                                    "          CLIENTE.NM_BAIRRO,\n" +
                                    "          CLIENTE.NM_CIDADE,\n" +
                                    "          CLIENTE.SG_ESTADO,\n" +
                                    "          CLIENTE.NR_CEP,\n" +
                                    "          CLIENTE.NR_TELEFONE,\n" +
                                    "          CLIENTE.NR_FAX,\n" +
                                    "          CLIENTE.NR_CELULAR,\n" +
                                    "          CLIENTE.DS_CONTATO,\n" +
                                    "          CLIENTE.DS_ENDERECO_COB,\n" +
                                    "          CLIENTE.NM_BAIRRO_COB,\n" +
                                    "          CLIENTE.NM_CIDADE_COB,\n" +
                                    "          CLIENTE.SG_ESTADO_COB,\n" +
                                    "          CLIENTE.NR_CEP_COB,\n" +
                                    "          CLIENTE.NR_TELEFONE_COB,\n" +
                                    "          CLIENTE.DS_ENDERECO_ENT,\n" +
                                    "          CLIENTE.NM_BAIRRO_ENT,\n" +
                                    "          CLIENTE.NM_CIDADE_ENT,\n" +
                                    "          CLIENTE.SG_ESTADO_ENT,\n" +
                                    "          CLIENTE.NR_CEP_ENT,\n" +
                                    "          CLIENTE.NR_TELEFONE_ENT,\n" +
                                    "          CLIENTE.NR_CNPJ_ENTREGA,\n" +
                                    "          CLIENTE.DS_EMAIL,\n" +
                                    "          CLIENTE.CD_CLIENTE_REPRESENTANTE,\n" +
                                    "          CLIENTE.DS_OBSERVACAO,\n" +
                                    "          CLIENTE.ID,\n" +
                                    "          CLIENTE.FG_ATIVO\n" +
                                    "FROM CLIENTE\n" +
                                    "WHERE CLIENTE.FG_ATIVO = 'S' AND CLIENTE.ID_REPRESENTANTE =" + Convert.ToString(idRepresentante) + "\n";

                DataTable dtConsultaTabela = bancoDados.getDataTable(sqlString);
                String[] insert = new String[dtConsultaTabela.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaTabela.Rows.Count; indice++)
                {
                    insert[indice] ="INSERT INTO CLIENTE\n" +
                                    "(ID_REPRESENTANTE,\n" +
                                    "ID_CLIENTE,\n" +
                                    "NM_CLIENTE,\n" +
                                    "NM_FANTASIA,\n" +
                                    "ID_VENDEDOR,\n" +
                                    "ID_PREPOSTO,\n" +
                                    "NR_IE,\n" +
                                    "NR_CNPJ,\n" +
                                    "DS_ENDERECO,\n" +
                                    "NM_BAIRRO,\n" +
                                    "NM_CIDADE,\n" +
                                    "SG_ESTADO,\n" +
                                    "NR_CEP,\n" +
                                    "NR_TELEFONE,\n" +
                                    "NR_FAX,\n" +
                                    "NR_CELULAR,\n" +
                                    "DS_CONTATO,\n" +
                                    "DS_ENDERECO_COB,\n" +
                                    "NM_BAIRRO_COB,\n" +
                                    "NM_CIDADE_COB,\n" +
                                    "SG_ESTADO_COB,\n" +
                                    "NR_CEP_COB,\n" +
                                    "NR_TELEFONE_COB,\n" +
                                    "DS_ENDERECO_ENT,\n" +
                                    "NM_BAIRRO_ENT,\n" +
                                    "NM_CIDADE_ENT,\n" +
                                    "SG_ESTADO_ENT,\n" +
                                    "NR_CEP_ENT,\n" +
                                    "NR_TELEFONE_ENT,\n" +
                                    "NR_CNPJ_ENTREGA,\n" +
                                    "DS_EMAIL,\n" +
                                    "CD_CLIENTE_REPRESENTANTE,\n" +
                                    "DS_OBSERVACAO,\n" +
                                    "ID,\n" +
                                    "FG_ATIVO)\n" +
                                    "VALUES\n" +
                                    "(" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_REPRESENTANTE"]) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_CLIENTE"]) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_CLIENTE"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_FANTASIA"])) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_VENDEDOR"]) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_PREPOSTO"]) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_IE"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CNPJ"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_ENDERECO"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_BAIRRO"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_CIDADE"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["SG_ESTADO"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CEP"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_TELEFONE"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_FAX"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CELULAR"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_CONTATO"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_ENDERECO_COB"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_BAIRRO_COB"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_CIDADE_COB"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["SG_ESTADO_COB"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CEP_COB"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_TELEFONE_COB"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_ENDERECO_ENT"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_BAIRRO_ENT"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NM_CIDADE_ENT"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["SG_ESTADO_ENT"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CEP_ENT"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_TELEFONE_ENT"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["NR_CNPJ_ENTREGA"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_EMAIL"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["CD_CLIENTE_REPRESENTANTE"])) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_OBSERVACAO"])) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID"]) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["FG_ATIVO"])) +
                                    ");";

                }
                return insert;
            }
            return null;
        }

        #region produto e tabela de preço
        [WebMethod]
        public string[] retornarTabelaPrecoFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                String sqlString =  "SELECT  TABELAPRECO.IDTABELA,\n" +
                                    "        TABELAPRECO.IDREPRESENTADA,\n" +
                                    "        TABELAPRECO.IDREPRESENTANTE,\n" +
                                    "        TABELAPRECO.DESCRICAO\n" +
                                    "FROM    TABELAPRECO\n" +
                                    "WHERE   TABELAPRECO.IDREPRESENTANTE =" + Convert.ToString(idRepresentante);

                DataTable dtConsultaTabela = bancoDados.getDataTable(sqlString);
                String[] insert = new String[dtConsultaTabela.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaTabela.Rows.Count; indice++)
                {
                    insert[indice] = "INSERT INTO TABELAPRECO\n" +
                                    "(IDTABELA,\n" +
                                    "IDREPRESENTADA,\n" +
                                    "IDREPRESENTANTE,\n" +
                                    "DESCRICAO)\n" +
                                    "VALUES\n" +
                                    "(" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["IDTABELA"]) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["IDREPRESENTADA"]) + "," +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["IDREPRESENTANTE"]) + "," +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DESCRICAO"])) + "\n" +
                                    ");";
                }
                return insert;
            }
            return null;
        }

        [WebMethod]
        public string[] retornarProdutoFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                String sqlString =  "SELECT PRODUTO.ID_REPRESENTANTE,\n" +
                                    "       PRODUTO.ID_REPRESENTADA,\n" +
                                    "       PRODUTO.CD_PRODUTO,\n" +
                                    "       PRODUTO.DS_PRODUTO,\n" +
                                    "       PRODUTO.DT_HORA_IMPORTACAO,\n" +
                                    "       PRODUTO.FG_ATIVO,\n" +
                                    "       PRODUTO.ID_PRODUTO\n" +
                                    "FROM   PRODUTO\n" +
                                    "WHERE  PRODUTO.ID_REPRESENTANTE =" + Convert.ToString(idRepresentante);

                DataTable dtConsultaTabela = bancoDados.getDataTable(sqlString);
                String[] insert = new String[dtConsultaTabela.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaTabela.Rows.Count; indice++)
                {
                    insert[indice] = "INSERT INTO PRODUTO\n" +
                                    "(ID_REPRESENTANTE,\n" +
                                    "ID_REPRESENTADA,\n" +
                                    "CD_PRODUTO,\n" +
                                    "DS_PRODUTO,\n" + 
                                    "DT_HORA_IMPORTACAO,\n" +
                                    "FG_ATIVO,\n" +
                                    "ID_PRODUTO)\n" +
                                    "VALUES\n" +
                                    "(" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_REPRESENTANTE"]) + ",\n" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_REPRESENTADA"]) + ",\n" +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["CD_PRODUTO"])) + ",\n" +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["DS_PRODUTO"])) + ",\n" +
                                    "DATETIME('NOW'),\n" +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["FG_ATIVO"])) + ",\n" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["ID_PRODUTO"]) +
                                    ");";
                }
                return insert;
            }
            return null;
        }

        [WebMethod]
        public string[] retornarProdutoTabeladoFull(String usuario, String senha)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);

                String sqlString = "SELECT  PRODUTOTABELADO.IDREPRESENTANTE,\n" +
                                    "       PRODUTOTABELADO.IDREPRESENTADA,\n" +
                                    "       PRODUTOTABELADO.CDPRODUTO,\n" +
                                    "       PRODUTOTABELADO.IDTABELA,\n" +
                                    "       PRODUTOTABELADO.VRPRODUTO\n" +
                                    "FROM   PRODUTOTABELADO\n" +
                                    "WHERE  PRODUTOTABELADO.IDREPRESENTANTE =" + Convert.ToString(idRepresentante);

                DataTable dtConsultaTabela = bancoDados.getDataTable(sqlString);
                String[] insert = new String[dtConsultaTabela.Rows.Count];
                for (Int32 indice = 0; indice < dtConsultaTabela.Rows.Count; indice++)
                {
                    insert[indice] = "INSERT INTO PRODUTOTABELADO\n" +
                                    "(IDREPRESENTANTE,\n" +
                                    "IDREPRESENTADA,\n" +
                                    "CDPRODUTO,\n" +
                                    "IDTABELA,\n" +
                                    "VRPRODUTO)\n" +
                                    "VALUES\n" +
                                    "(" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["IDREPRESENTANTE"]) + ",\n" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["IDREPRESENTADA"]) + ",\n" +
                                    bancoDados.apostrofo(Convert.ToString(dtConsultaTabela.Rows[indice]["CDPRODUTO"])) + ",\n" +
                                    Convert.ToString(dtConsultaTabela.Rows[indice]["IDTABELA"]) + ",\n" +
                                    bancoDados.trocarVirgulaPonto(Convert.ToString(dtConsultaTabela.Rows[indice]["VRPRODUTO"])) +
                                    ");";
                }
                return insert;
            }
            return null;
        }

        #endregion

        [WebMethod]
        public string incluirPedido(String usuario, String senha, String sqlCab, String sqlItens)
        {
            DataTable dtUsuario = bancoDados.getDataTable(bancoDados.sqlVerificarUsuarioSenhaBanco(usuario, senha));
            if (dtUsuario != null && dtUsuario.Rows.Count > 0) //Usuário e senha OK
            {
                Int32 idRepresentante = Convert.ToInt32(dtUsuario.Rows[0]["id_representante"]);
                return bancoDados.executarComandoPedido(sqlCab, sqlItens);
                
            }
            return "Usuário não encontrado!";
        }
    }
}
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace wsIntegracao
{
    public class bancoDados
    {
        private static String conexao = "Server=localhost;Port=3306;Database=representanet1;Uid=root;Pwd=root;pooling=false;";

        //public static DataTable getDataTable(String query)
        //{
        //    DataTable dt = new DataTable();
        //    MySqlConnection conMySQL = new MySqlConnection("uid=root; password=root; database=representanet1");
        //    try
        //    {
        //        conMySQL.Open();
        //        MySqlCommand cmd = new MySqlCommand(query, conMySQL);
        //        MySqlDataAdapter returnVal = new MySqlDataAdapter(query, conMySQL);
        //        returnVal.Fill(dt);
        //    }
        //    catch
        //    {
        //    }
        //    finally
        //    {                
        //    }

        //    return dt;
        //}
        public static String trocarVirgulaPonto(String str)
        {
            //1.000,00
            return str.Replace(".", "").Replace(",", ".");
        }

        public static String sqlVerificarUsuarioSenhaBanco(String usuario, String senha)
        {
            String sql = "SELECT NM_USUARIO, DS_SENHA, ID_REPRESENTANTE, NM_COMPLETO, FG_LIBERADO\n" +
                         "FROM REPRESENTANET1.USUARIO\n" +
                         "WHERE NM_USUARIO = '" + usuario + "'\n" +
                         "  AND DS_SENHA = '" + senha + "'\n";
            return sql;
        }

        public static DataTable getDataTable(String comandoSQL)
        {
            MySqlConnection conn = new MySqlConnection(conexao);
            conn.Open();
            //MySqlCommand SqlCmd = new MySqlCommand("select * from pedidos where idstatuspedido=1 and impresso='N'", conn);
            MySqlCommand SqlCmd = new MySqlCommand(comandoSQL, conn);
            MySqlDataAdapter da = new MySqlDataAdapter(SqlCmd);

            DataTable dtTemp = new DataTable();
            da.Fill(dtTemp);

            conn.Close();

            return dtTemp;
        }

        public static String executarComandoPedido(String comandoSQL, String comandoSQLItem)
        {
            MySqlConnection conn = new MySqlConnection(conexao);
            conn.Open();
            String retorno = "";
            //cria a transação
            MySqlTransaction tn = conn.BeginTransaction(IsolationLevel.Serializable);
            try
            {
                //MySqlCommand SqlCmd = new MySqlCommand("select * from pedidos where idstatuspedido=1 and impresso='N'", conn);
                MySqlCommand SqlCmd = new MySqlCommand(comandoSQL, conn);
                SqlCmd.Transaction = tn;
                SqlCmd.ExecuteNonQuery();
                long imageId = SqlCmd.LastInsertedId;

                comandoSQLItem = comandoSQLItem.Replace("_@IDPEDIDO@_", Convert.ToString(imageId));

                MySqlCommand SqlCmdItem = new MySqlCommand(comandoSQLItem, conn);
                SqlCmdItem.Transaction = tn;
                SqlCmdItem.ExecuteNonQuery();

                tn.Commit();

                retorno = "OK";
            }
            catch (Exception ex)
            {
                retorno = "ERRO: " + ex.Message;
            }
            finally
            {
                conn.Close();
            }
            return retorno;
        }

        public static String apostrofo(String str)
        {
            return "'" + str.Replace("'","''") + "'";
        }
    }
}
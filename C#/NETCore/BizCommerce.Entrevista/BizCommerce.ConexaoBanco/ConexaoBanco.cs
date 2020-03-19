using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BizCommerce.ConexaoBanco
{
    public class ConexaoBancoDados
    {
        public static string sConexao = "";

        public MySqlConnection conectarMySQL(String conexao)
        {
            MySqlConnection myConexao = new MySqlConnection(sConexao);
            myConexao.Open();
            return myConexao;
        }
        public void desconectarMySQL(ref MySqlConnection myConexao)
        {
            myConexao.Close();
        }

        public static DataSet getDataSet(String comandoSql, String nomeTabela)
        {
            MySqlConnection myConexao = null;
            try
            {
                DataSet dsResultado;

                dsResultado = new DataSet(nomeTabela);
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                MySqlDataAdapter daAdapter = new MySqlDataAdapter(comandoSql, myConexao);

                daAdapter.Fill(dsResultado, nomeTabela);

                return dsResultado;
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao.Dispose();
                    myConexao = null;
                }

            }

        }

        public static String getCampoString(String comandoSql)
        {
            MySqlConnection myConexao = null;
            try
            {
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                MySqlDataAdapter daAdapter = new MySqlDataAdapter(comandoSql, myConexao);
                DataTable table = new DataTable();
                daAdapter.Fill(table);
                DataRow[] rows = table.Select();

                if (rows.Length == 0)
                    return null;
                else
                    return rows[0][0].ToString();
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                }
            }
        }

        public static int getCampoInt(String comandoSql)
        {
            MySqlConnection myConexao = null;
            try
            {
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                MySqlDataAdapter daAdapter = new MySqlDataAdapter(comandoSql, myConexao);
                DataTable table = new DataTable();
                daAdapter.Fill(table);
                DataRow[] rows = table.Select();

                if (rows.Length == 0)
                    return -1;
                else
                    return Convert.ToInt32(rows[0][0]);
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                }
            }
        }

        public static double getCampoDouble(String comandoSql)
        {
            MySqlConnection myConexao = null;
            try
            {
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                MySqlDataAdapter daAdapter = new MySqlDataAdapter(comandoSql, myConexao);
                DataTable table = new DataTable();
                daAdapter.Fill(table);
                DataRow[] rows = table.Select();

                if (rows.Length == 0)
                    return -1;
                else
                    return Convert.ToDouble(rows[0][0]);
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                }
            }
        }

        public static string doExecutaComando(String sql, ref Int32 idNovo)
        {
            MySqlConnection myConexao = null;
            MySqlCommand myComando = new MySqlCommand();
            try
            {

                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                myComando.Connection = myConexao;
                myComando.CommandType = CommandType.Text;
                myComando.CommandText = sql;
                myComando.ExecuteNonQuery();

                idNovo = Convert.ToInt32(myComando.LastInsertedId);

                return String.Empty;
            }
            catch (Exception exc)
            {
                idNovo = -1;
                return exc.Message;
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                    myComando = null;
                }
            }
        }

        public static string executaComando(string sql)
        {
            MySqlConnection myConexao = null;
            MySqlCommand myComando = new MySqlCommand();
            try
            {

                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                myComando.Connection = myConexao;
                myComando.CommandType = CommandType.Text;
                myComando.CommandText = sql;


                myComando.ExecuteNonQuery();

                return null;
            }
            catch (Exception exc)
            {
                string exc1 = exc.Message;
                return exc.Message;
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                    myComando = null;
                }
            }
        }

        public static DataTable getDataTable(String comandoSql, String nomeTabela)
        {
            MySqlConnection myConexao = null;
            try
            {
                MySqlDataAdapter daAdapter;
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();
                daAdapter = new MySqlDataAdapter(comandoSql, myConexao);

                DataTable dtResultado = new DataTable(nomeTabela);
                daAdapter.Fill(dtResultado);

                return dtResultado;
            }
            catch
            {
                return new DataTable();
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                }
            }

        }

        public static DataTable getDataTable(String comandoSql)
        {
            MySqlConnection myConexao = null;
            try
            {
                MySqlDataAdapter daAdapter;
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();
                daAdapter = new MySqlDataAdapter(comandoSql, myConexao);

                DataTable dtResultado = new DataTable();
                daAdapter.Fill(dtResultado);

                return dtResultado;
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                }
            }

        }

        public static DataTable getTable(String sql)
        {
            MySqlConnection myConexao = null;
            try
            {
                MySqlDataAdapter daAdapter;
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();
                daAdapter = new MySqlDataAdapter(sql, myConexao);

                DataTable dtResultado = new DataTable();
                daAdapter.Fill(dtResultado);

                return dtResultado;
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                }
            }

        }

        public static DataRow getRegistro(String comandoSql)
        {
            MySqlConnection myConexao = null;
            try
            {
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                MySqlDataAdapter daAdapter = new MySqlDataAdapter(comandoSql, myConexao);
                DataTable table = new DataTable();
                daAdapter.Fill(table);
                DataRow[] rows = table.Select();

                if (rows.Length == 0)
                    return null;
                else
                    return rows[0];
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                }
            }
        }

        public static MySqlDataReader getDataReader(String comandoSql)
        {
            MySqlConnection myConexao = null;
            try
            {
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                MySqlCommand myCommand = new MySqlCommand(comandoSql, myConexao);
                MySqlDataReader myReader = myCommand.ExecuteReader(CommandBehavior.CloseConnection);

                return myReader;
            }
            finally
            {
                /* if (myConexao != null)
                 {
                     myConexao.Close();
                     myConexao = null;
                 }*/
            }
        }

        public static int doExecutaSProcedure(MySqlCommand myComando, String nomeSProcedure, Boolean querRetorno)
        {
            MySqlConnection myConexao = null;
            try
            {
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                int retorno = 0;

                myComando.Connection = myConexao;
                myComando.CommandType = CommandType.StoredProcedure;
                myComando.CommandText = nomeSProcedure;


                myComando.ExecuteNonQuery();
                if (querRetorno)
                    retorno = Convert.ToInt32(myComando.Parameters[myComando.Parameters.Count - 1].Value);


                return retorno;
            }
            finally
            {
                if (myConexao != null)
                {
                    myConexao.Close();
                    myConexao = null;
                }
            }
        }

        public static Int32 insertGetId(String nomProcedure, List<ParametroMySql> parametros)
        {
            MySqlConnection myConexao = null;

            try
            {
                myConexao = new MySqlConnection(sConexao);
                myConexao.Open();

                MySqlCommand cmd = new MySqlCommand(nomProcedure, myConexao);
                cmd.CommandType = CommandType.StoredProcedure;

                foreach (ParametroMySql p in parametros)
                {
                    if (p.valorParametro != null)
                        cmd.Parameters.AddWithValue(p.nomeParametro, p.valorParametro);
                    else
                        cmd.Parameters.AddWithValue(p.nomeParametro, DBNull.Value);
                }

                //cmd.Parameters.AddWithValue("nomecliente", nome);
                //cmd.Parameters.AddWithValue("Cnpj_Cpf", cnpf_cpf);

                cmd.Connection = myConexao;
                //cmd.ExecuteNonQuery();
                var id = cmd.ExecuteScalar();
                myConexao.Close();
                myConexao.Dispose();

                return Convert.ToInt32(id);
            }
            catch (Exception ex)
            {
                myConexao.Close();
                myConexao.Dispose();
                throw ex;
            }
        }

        public static byte[] getImagem(String sql)
        {
            MySqlConnection myConexao = null;
            byte[] imagedata = null;
            try
            {
                // Establish connection and SELECT statement.
                myConexao = new MySqlConnection(sConexao);
                MySqlCommand myCommand = new MySqlCommand
                        (sql, myConexao);
                myConexao.Open();

                // Get the image from the database.
                imagedata = (byte[])myCommand.ExecuteScalar();
            }
            catch
            {
                ;
            }
            finally
            {
                myConexao.Close();
            }

            return imagedata;
        }
    }
}

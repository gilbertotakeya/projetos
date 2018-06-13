using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


using System.Configuration;
using System.Data.SqlClient;

namespace HOLDBProfessor2008
{
    public class FuncoesBD
    {

        private string RetornaConnString(int bd)
        {
            string connString = string.Empty;

            switch (bd)
            {
                case 0:
                    {
                        connString = ConfigurationManager.ConnectionStrings["sc"].ConnectionString;
                        break;
                    }
                case 1:
                    {
                        connString = ConfigurationManager.ConnectionStrings["abc"].ConnectionString;
                        break;
                    }
                default:
                    break;
            }

            return connString;    
        }

        public SqlConnection abreConexao(int bd)
        {
            SqlConnection conn = new SqlConnection(RetornaConnString(bd));
            conn.Open();
            return conn;
        }

        public bool fechaConexao(SqlConnection conn)
        {

            try
            {
                conn.Close();
                return true;
            }
            catch
            {
                return false;
            }
        }


    }
}

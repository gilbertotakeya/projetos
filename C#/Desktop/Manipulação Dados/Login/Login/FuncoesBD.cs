using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Configuration;
using System.Data.SqlClient;

namespace Login
{
    public class FuncoesBD
    {
        private string RetornarConnString()
        {
            return ConfigurationManager.ConnectionStrings["Banco"].ConnectionString;          //Referenciar a System.configuration no projeto  
        }

        public SqlConnection AbrirConexao()
        {
            SqlConnection conn = new SqlConnection(RetornarConnString());
            conn.Open();
            return conn;
        }

        public bool FecharConexao(SqlConnection conn)
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

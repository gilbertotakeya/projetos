using System.Configuration;
using System.Data.SqlClient;

namespace FuncoesBancoDeDados
{
    public class ClasseBanco
    {
        private string RetornarConnString()
        {
            return ConfigurationManager.ConnectionStrings["BancoTosco"].ConnectionString;
        }

        public SqlConnection AbrirConexao()
        {
            //string x = RetornarConnString();
            //int i = 0;
            string x = "Data Source=NBGilberto\\SQLSERVER2008;Initial Catalog=Tosco;Integrated Security=True";
            SqlConnection conn = new SqlConnection(x);
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

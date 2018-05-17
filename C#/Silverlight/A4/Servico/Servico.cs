using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Contract;
using System.Data.SqlClient;
using System.Data;
namespace Servicos
{
    public class Servico : IService
    {
        private const string strconn = @"Data Source = .\sqlexpress; Initial Catalog = Northwind; Integrated Security = True";
        private SqlConnection conn;

        public List<Categoria> GetCategorias()
        {
            conn = new SqlConnection(strconn);
            DataTable dt = new DataTable("Categoria");
            string sql = "Select CategoryName, Description from Categories";
            SqlDataAdapter da = new SqlDataAdapter(sql, conn);

            da.Fill(dt);

            List<Categoria> retorno = new List<Categoria>();

            foreach (DataRow dr in dt.Rows)
            {
                retorno.Add(new Categoria { Nome = dr[0].ToString(), Descricao = dr[1].ToString() });
            }
            return retorno;
        }

        public void SetCategorias(Categoria cat)
        {
            conn = new SqlConnection(strconn);
            string sql = "INSERT INTO Categories (CategoryName,Description) " +
            "VALUES ('" + cat.Nome + "','" + cat.Descricao + "')";
            SqlCommand cmd = new SqlCommand(sql, conn);
            conn.Open();
            cmd.ExecuteNonQuery();
            conn.Close();
        }
    }
}

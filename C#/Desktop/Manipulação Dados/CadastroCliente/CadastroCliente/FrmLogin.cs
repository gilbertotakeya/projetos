using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;
using FuncoesBancoDeDados;

namespace CadastroCliente
{
    public partial class FrmLogin : Form
    {
        public FrmLogin()
        {
            InitializeComponent();
        }
        
        // Declaração de atributos
        #region Variaveis
        public bool LiberouUsuario = false;
        private SqlCommand sql = new SqlCommand();
        private SqlConnection conn = new SqlConnection();
        private SqlDataReader dr;
        //private FuncoesBD fBD = new FuncoesBD();
        private DataTable dt = new DataTable();
        ClasseBanco fBD = new ClasseBanco();
        #endregion
        private void btnAcessar_Click(object sender, EventArgs e)
        {
            try
            {
                conn = fBD.AbrirConexao();
                sql.Parameters.Clear();
                sql.Connection = conn;
                sql.CommandType = CommandType.StoredProcedure;
                sql.CommandText = "BuscarLoginSenha";
                sql.Parameters.AddWithValue("@Login", txtLogin.Text);
                dr = sql.ExecuteReader();
//                dt.Load(sql.ExecuteReader());
                string login = "";
                string senha = "";
                LiberouUsuario = false;
                while( dr.Read() )
                {
                    login = dr["Login"].ToString();
                    senha = dr["Senha"].ToString();
                    if( ( login == txtLogin.Text ) && ( senha == txtSenha.Text ) )
                        LiberouUsuario = true;
                }
                if (!LiberouUsuario)
                {
                    MessageBox.Show("Usuario ou senha inválidos!");                    
                    return;
                }
            }
            catch (SqlException ex)
            {
                MessageBox.Show("Erro de Banco " + ex.Message);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao Listar os Clientes \n" + ex.Message);
            }
            finally
            {
                dr.Close();
                fBD.FecharConexao(conn);
            }
            if (LiberouUsuario)
                this.Close();
        }
    }
}

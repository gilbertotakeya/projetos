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
    public partial class FrmClientes : Form
    {
        public FrmClientes()
        {
            InitializeComponent();            
            MontarDataGrid();
        }
        // Declaração de atributos
        #region Variaveis
        SqlCommand      sql  = new SqlCommand();
        SqlConnection   conn = new SqlConnection();
        SqlDataReader   dr;
        ClasseBanco     fBD = new ClasseBanco();
        DataTable       dt   = new DataTable();
        bool Incluir         = false;
        int  IDSelecionado   = -1;
        #endregion

        private void MontarDataGrid()
        {
            dtgvClientes.DataSource = null;

            try
            {
                dt = null;
                dt = new DataTable();

                conn = fBD.AbrirConexao();
                sql.Connection = conn;

                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.Clear();

                sql.CommandText = "MontarListaClientes";

                dt.Load(sql.ExecuteReader());
                
                dtgvClientes.DataSource = dt;

            }
            catch (SystemException exe)
            {
                MessageBox.Show("erro: " + exe.Message);
            }
            finally
            {
                fBD.FecharConexao(conn);
            }
        }

        private void btInserir_Click(object sender, EventArgs e)
        {
            Incluir = true;
            txtCPF.Text = "";
            txtNome.Text = "";
        }

        private void dtgvClientes_CellEnter(object sender, DataGridViewCellEventArgs e)
        {
            int row = e.RowIndex;
            IDSelecionado = Convert.ToInt32( this.dtgvClientes.Rows[row].Cells[0].Value.ToString() );
            txtCPF.Text   = this.dtgvClientes.Rows[row].Cells[1].Value.ToString();
            txtNome.Text  = this.dtgvClientes.Rows[row].Cells[2].Value.ToString();
        }

        private void btSalvar_Click(object sender, EventArgs e)
        {
            try
            {
                conn = fBD.AbrirConexao();
                sql.Connection = conn;
                sql.Parameters.Clear();
                sql.CommandType = CommandType.StoredProcedure;

                if (Incluir)
                    sql.CommandText = "InserirClientes";
                else
                {
                    sql.CommandText = "AlterarClientes";
                    sql.Parameters.AddWithValue("@id", IDSelecionado);
                }
                sql.Parameters.AddWithValue("@CPF", txtCPF.Text);
                sql.Parameters.AddWithValue("@Nome", txtNome.Text);

                sql.ExecuteNonQuery();

                Incluir = false;

                MontarDataGrid();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro " + ex.Message);
            }
            finally
            {
                fBD.FecharConexao(conn);
            }
        }

        private void btExcluir_Click(object sender, EventArgs e)
        {
            try
            {
                conn = fBD.AbrirConexao();
                sql.Connection = conn;
                sql.Parameters.Clear();
                sql.CommandType = CommandType.StoredProcedure;

                sql.CommandText = "ExcluirClientes";
                sql.Parameters.AddWithValue("@id", IDSelecionado);
                sql.ExecuteNonQuery();

                MontarDataGrid();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro " + ex.Message);
            }
            finally
            {
                fBD.FecharConexao(conn);
            }

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            try
            {
                dt = null;
                dt = new DataTable();
                conn = fBD.AbrirConexao();
                sql.Connection = conn;

                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.Clear();
                sql.CommandText = "BuscarClientePorNome";
                sql.Parameters.AddWithValue("@Nome", textBox1.Text);
                dt.Load(sql.ExecuteReader());
                dtgvClientes.DataSource = dt;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao selecionar o cliente \n" + ex.Message);
            }
            finally
            {
                fBD.FecharConexao(conn);
            }
        }
    }
}

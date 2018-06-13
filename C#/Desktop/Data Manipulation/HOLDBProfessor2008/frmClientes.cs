using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Text.RegularExpressions;
using System.Data.SqlClient;

namespace HOLDBProfessor2008
{
    public partial class frmClientes : Form
    {
        public frmClientes()
        {
            InitializeComponent();
        }

        #region Variáveis
        SqlCommand sql = new SqlCommand();
        SqlConnection conn = new SqlConnection();
        SqlDataReader dr;
        FuncoesBD fBD = new FuncoesBD();
        DataTable dt = new DataTable();
        #endregion

        private void ListarClientes()
        {
            lstClientes.Items.Clear();
            try
            {
                conn = fBD.abreConexao(0);
                sql.Connection = conn;
                sql.CommandType = CommandType.Text;
                sql.CommandText = "select Nome from Clientes Order by Nome";
                dr = sql.ExecuteReader();

                while (dr.Read())
                {
                    lstClientes.Items.Add(dr["Nome"].ToString());
                }

            }
            catch (SqlException ex)
            {
                MessageBox.Show("Erro de Banco "+ex.Message);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao Listar os Clientes \n" + ex.Message);
            }
            finally
            {
                dr.Close();
                fBD.fechaConexao(conn);
            }
        }

        private void BuscarClientePorNome(string NomeCliente)
        {
            try
            {
                conn = fBD.abreConexao(0);
                sql.Connection = conn;

                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.Clear();
                sql.CommandText = "BuscaClientePorNome";
                sql.Parameters.AddWithValue("@Nome", NomeCliente);
                dr = sql.ExecuteReader();

                if (dr.Read())
                {
                    lblID.Text = dr["idCliente"].ToString();
                    txtNome.Text  = dr["Nome"].ToString();
                    txtIdade.Text = dr["Idade"].ToString();
                    txtEmail.Text = dr["Email"].ToString();
                    dtNascimento.Text = dr["DataNascimento"].ToString();
                    
                    
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao selecionar o cliente \n" + ex.Message);
            }
            finally
            {
                dr.Close();
                fBD.fechaConexao(conn);
            }
        }

        
        private void InserirClientes(string Nome, int Idade, string Email, 
            DateTime DataNascimento)
        {
            try
            {
                conn = fBD.abreConexao(0);
                sql.Connection = conn;

                sql.CommandType = CommandType.StoredProcedure;

                sql.CommandText = "Clientes_Insert";

                sql.Parameters.Clear();
                sql.CommandText = "Clientes_Insert";
                sql.Parameters.AddWithValue("@Nome", Nome);
                sql.Parameters.AddWithValue("@Idade", Idade);
                sql.Parameters.AddWithValue("@Email", Email);
                sql.Parameters.AddWithValue("@DataNascimento", DataNascimento);
                sql.ExecuteNonQuery();

            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao Inserir o cliente \n" + ex.Message);
            }
            finally
            {
                fBD.fechaConexao(conn);
            }  
        }

        private void EditarClientes(int idCliente, string Nome, int Idade, string Email, DateTime DataNascimento)
        {
            try
            {
                conn = fBD.abreConexao(0);
                sql.Connection = conn;

                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.Clear();
                sql.CommandText = "Clientes_Update";
                sql.Parameters.AddWithValue("@IdCliente", idCliente);
                sql.Parameters.AddWithValue("@Nome", Nome);
                sql.Parameters.AddWithValue("@Idade", Idade);
                sql.Parameters.AddWithValue("@Email", Email);
                sql.Parameters.AddWithValue("@DataNascimento", DataNascimento);
                sql.ExecuteNonQuery();

            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao Editar o cliente \n" + ex.Message);
            }
            finally
            {
                fBD.fechaConexao(conn);
            }
        }

        private void ExcluirClientes(int idCliente)
        {
            try
            {
                conn = fBD.abreConexao(0);
                sql.Connection = conn;

                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.Clear();
                sql.CommandText = "Clientes_Delete";
                sql.Parameters.AddWithValue("@IdCliente", idCliente);
                sql.ExecuteNonQuery();

            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao Editar o cliente \n" + ex.Message);
            }
            finally
            {
                fBD.fechaConexao(conn);
            } 
        }

        private void BuscarClientes(string Nome)
        {
            try
            {
                dt = null;
                dt = new DataTable();
                conn = fBD.abreConexao(0);
                sql.Connection = conn;

                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.Clear();
                sql.CommandText = "BuscarClientes";
                sql.Parameters.AddWithValue("@Nome", Nome);
                dt.Load(sql.ExecuteReader());
                dgvClientes.DataSource = dt;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao selecionar o cliente \n" + ex.Message);
            }
            finally
            {
                fBD.fechaConexao(conn);
            }
        }

        #region Validações de Campos
        private void txtNome_Validating(object sender, CancelEventArgs e)
        {
            ValidarNome();

        }

        private void ValidarNome()
        {
            if (string.IsNullOrEmpty(txtNome.Text))
            {
                errObrigatorio.SetError(txtNome, "Nome Obrigatório");
                txtNome.Focus();
                return;
            }
            else
                errObrigatorio.SetError(txtNome, "");
        }

        private void txtIdade_Validating(object sender, CancelEventArgs e)
        {
            ValidarIdade();

        }

        private void ValidarIdade()
        {
            if (string.IsNullOrEmpty(txtIdade.Text))
            {
                errObrigatorio.SetError(txtIdade, "Idade Obrigatória");
                txtIdade.Focus();
                return;
            }
            else
                errObrigatorio.SetError(txtIdade, "");

            if (!Regex.IsMatch(txtIdade.Text, "[0-9]+"))
            {
                errObrigatorio.SetError(txtIdade, "Idade Invalida");
                txtIdade.Focus();
            }
            else
                errObrigatorio.SetError(txtIdade, "");
        }

        private void txtEmail_Validating(object sender, CancelEventArgs e)
        {
            ValidarEmail();

        }

        private void ValidarEmail()
        {
            if (string.IsNullOrEmpty(txtEmail.Text))
            {
                errObrigatorio.SetError(txtEmail, "Email Obrigatório");
                txtEmail.Focus();
                return;
            }
            else
                errObrigatorio.SetError(txtEmail, "");
        } 
        #endregion

        private void frmClientes_Load(object sender, EventArgs e)
        {
            ListarClientes();

            dgvClientes.AutoGenerateColumns = false;
        }

        private void lstClientes_SelectedIndexChanged(object sender, EventArgs e)
        {
            BuscarClientePorNome(lstClientes.Text);
        }

        private void btnEditar_Click(object sender, EventArgs e)
        {
            //if (string.IsNullOrEmpty(lblID.Text))
            //{
            //    MessageBox.Show("Selecione um Cliente");
            //    return;
            //}
            //EditarClientes(int.Parse(lblID.Text), txtNome.Text, int.Parse(txtIdade.Text), txtEmail.Text, DateTime.Parse(dtNascimento.Text));
            //ListarClientes();
            //MessageBox.Show("Registro Atualizado com Sucesso");
        }

        private void btnCadastrar_Click(object sender, EventArgs e)
        {
            ValidarNome();
            ValidarIdade();
            ValidarEmail();

            InserirClientes(txtNome.Text, int.Parse(txtIdade.Text), 
                txtEmail.Text, DateTime.Parse(dtNascimento.Text));
            ListarClientes();
            MessageBox.Show("Cliente cadastado com Sucesso.");
        }

        private void Excluir_Click(object sender, EventArgs e)
        {
            //if (string.IsNullOrEmpty(lblID.Text))
            //{
            //    MessageBox.Show("Selecione um Cliente");
            //    return;
            //}

            //if (MessageBox.Show("Deseja Excluir?", "Exclusão de Clientes", MessageBoxButtons.YesNo) == DialogResult.Yes)
            //{
            //    ExcluirClientes(int.Parse(lblID.Text));
            //    ListarClientes();

            //    txtNome.Text = "";
            //    txtIdade.Text = "";
            //    txtEmail.Text = "";
            //    dtNascimento.Text = DateTime.Now.ToShortDateString();
            //}
        }

        private void txtNomeBusca_TextChanged(object sender, EventArgs e)
        {
            dgvClientes.DataSource = null;
            
            BuscarClientes(txtNomeBusca.Text);
        }

        



        
    }
}

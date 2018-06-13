using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace HOLDBProfessor2008
{
    public partial class frmPedidos : Form
    {
        public frmPedidos()
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
            cmbClientes.Items.Clear();
            try
            {
                conn = fBD.abreConexao(0);
                sql.Connection = conn;
                sql.CommandType = CommandType.Text;
                sql.CommandText = "select idCliente,Nome from Clientes Order by Nome";
                dt.Load(sql.ExecuteReader());
                cmbClientes.DataSource = dt;
                cmbClientes.DisplayMember = "Nome";
                cmbClientes.ValueMember = "idCliente";
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao Listar os Clientes \n" + ex.Message);
            }
            finally
            {
                fBD.fechaConexao(conn);
            }
            //lblIdCliente.Text = cmbClientes.SelectedValue.ToString();
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
                    lblIdCliente.Text = dr["idCliente"].ToString();
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

        private void frmPedidos_Load(object sender, EventArgs e)
        {
            ListarClientes();
            
            dgvProdutos.AutoGenerateColumns = false;

            lblTotal.Text = "0";

            cmbClientes.SelectedIndex = -1;
            lblIdCliente.Text = "";
        }


        private void txtCodigo_KeyDown(object sender, KeyEventArgs e)
        
        {
            if (e.KeyCode == Keys.Enter)
            {
                try
                {
                    conn = fBD.abreConexao(0);
                    sql.Connection = conn;
                    sql.CommandType = CommandType.StoredProcedure;
                    sql.Parameters.Clear();
                    sql.CommandText = "Produtos_select";
                    sql.Parameters.AddWithValue("@idProduto", int.Parse(txtCodigo.Text));
                    dr = sql.ExecuteReader();

                    while (dr.Read())
                    {
                        txtNomeProduto.Text = dr["NomeProduto"].ToString();
                        txtValor.Text = dr["VALOR"].ToString();
                    }

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
        }

        List<Pedido> lpedido = new List<Pedido>();

        private void btnAdd_Click(object sender, EventArgs e)
        {

            dgvProdutos.DataSource = null;

            Pedido pedido = new Pedido();
            

            int cod = int.Parse(txtCodigo.Text);
            string nome = txtNomeProduto.Text;
            double valorProduto = double.Parse(txtValor.Text);
            double qtde = double.Parse(txtQtde.Text);
            double valorTotal = valorProduto * qtde;

            pedido.idProduto = cod;
            pedido.NomeProduto = nome;
            pedido.ValorProduto = valorProduto;
            pedido.Qtde = qtde;
            pedido.ValorTotal = valorTotal;

            lpedido.Add(pedido);

            dgvProdutos.DataSource = lpedido;

            lblTotal.Text = (double.Parse(lblTotal.Text) + valorTotal).ToString();
           
            
        }

        private void cmbClientes_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cmbClientes.SelectedIndex != -1)
                lblIdCliente.Text = cmbClientes.SelectedValue.ToString();
        }

        private void btnGerarPedido_Click(object sender, EventArgs e)
        {
            
            try
            {
                conn = fBD.abreConexao(0);
                sql.Connection = conn;
                sql.CommandType = CommandType.StoredProcedure;
                sql.CommandText = "GerarPedido";
                sql.Parameters.Clear();
                sql.Parameters.AddWithValue("@idCliente", int.Parse(lblIdCliente.Text));
                int idPedido = Convert.ToInt32(sql.ExecuteScalar());


                foreach (Pedido itemLista in lpedido)
                {

                    sql.CommandText = "GerarItemPedido";
                    sql.Parameters.Clear();
                    sql.Parameters.AddWithValue("@idPedido",idPedido);
                    sql.Parameters.AddWithValue("@idProduto",itemLista.idProduto);
                    sql.Parameters.AddWithValue("@qtde",itemLista.Qtde);
                    sql.Parameters.AddWithValue("@valorItem", itemLista.ValorTotal);
                    sql.ExecuteNonQuery();
                                     
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao Listar os Clientes \n" + ex.Message);
            }
            finally
            {
                fBD.fechaConexao(conn);
            }

            MessageBox.Show("Pedido efetuado com Sucesso");

                        
        }

        
    }
}

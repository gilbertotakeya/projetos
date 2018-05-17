using System.Windows.Forms;
using System.Diagnostics;
using System;
namespace GerenciadorProcessos
{
    public partial class frmGerenciadorProcessos : Form
    {
        private bool IncluirProcesso = false;
        public frmGerenciadorProcessos()
        {
            InitializeComponent();
            MontarGridProcessos();
            btnCancelar.SetBounds(538, 499, 75, 23);
            btnSalvar.SetBounds(457, 499, 75, 23);
            cmbPrioridade.SelectedIndex = -1;
        }
        private void dtgvProcessos_CellEnter(object sender, DataGridViewCellEventArgs e)
        {
            PegarValorGridAtualizarCampos();
        }
        private void MontarGridProcessos()
        {            
            dtgvProcessos.Rows.Clear();
            string Prioridade = "", Respondendo = "";
            Process[] ProcessosAtivos = Process.GetProcesses();
            foreach (Process p in ProcessosAtivos)
            {
                Prioridade = "";
                Respondendo = "";
                if( p.BasePriority == 4 )
                    Prioridade = "Idle";
                else if( p.BasePriority == 8 )
                    Prioridade = "Normal";
                else if( p.BasePriority == 13 )
                    Prioridade = "High";
                else if( p.BasePriority == 24 )
                    Prioridade = "Realtime";
                else
                    Prioridade = Convert.ToString(p.BasePriority);

                Respondendo = "Não está rodando";
                if( p.Responding )
                {
                    Respondendo = "Rodando";
                }

                dtgvProcessos.Rows.Add(p.Id, p.ProcessName,Prioridade, Respondendo);
            }
        }
        private void PegarValorGridAtualizarCampos()
        {
            txtID.Text          = dtgvProcessos.CurrentRow.Cells[0].Value.ToString();
            txtProcesso.Text    = dtgvProcessos.CurrentRow.Cells[1].Value.ToString();
            string Prioridade   = dtgvProcessos.CurrentRow.Cells[2].Value.ToString();
            if (Prioridade == "Idle")
                cmbPrioridade.SelectedIndex = 1;
            else if (Prioridade == "Normal")
                cmbPrioridade.SelectedIndex = 0;
            else if (Prioridade == "High")
                cmbPrioridade.SelectedIndex = 2;
            else if (Prioridade == "Realtime")
                cmbPrioridade.SelectedIndex = 3;
            else
                cmbPrioridade.SelectedIndex = 4;
        }
        private void btnIncluir_Click(object sender, System.EventArgs e)
        {            
            lblProcesso.Text = "Informe abaixo o caminho e o nome do processo";
            txtProcesso.Text = "";
            cmbPrioridade.SelectedIndex = -1;
            HabilitarSalvarCancelar(true);
            IncluirProcesso = true;
        }
        private void HabilitarSalvarCancelar(bool Habilitar)
        {
            cmbPrioridade.Enabled= Habilitar;
            btnSalvar.Visible    = Habilitar;
            btnCancelar.Visible  = Habilitar;
            btnIncluir.Visible   = true;
            btnAlterar.Visible   = true;
            btnExcluir.Visible   = true;
            txtProcesso.ReadOnly = true;
            if (Habilitar)
            {
                txtProcesso.ReadOnly = false;
                btnIncluir.Visible   = false;
                btnAlterar.Visible   = false;
                btnExcluir.Visible   = false;
            }
        }
        private void btnSalvar_Click(object sender, System.EventArgs e)
        {
            try
            {
                if( ( txtProcesso.Text == "" ) || ( txtProcesso.Text == string.Empty ) )
                {
                    MessageBox.Show("Preencha o campo Processo!", "A T E N Ç Ã O!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
                if( IncluirProcesso )
                    CriarProcesso();
                else
                    AlterarPrioridadeProcesso();
            }
            catch( Exception Ex )
            {
                MessageBox.Show("Erro: " + Ex.Message, "A T E N Ç Ã O!",MessageBoxButtons.OK,MessageBoxIcon.Error);
            }
            finally
            {
                HabilitarSalvarCancelar(false);
                MontarGridProcessos();
                PegarValorGridAtualizarCampos();
                lblProcesso.Text = "Processo";
            }
        }
        private void btnCancelar_Click(object sender, System.EventArgs e)
        {
            HabilitarSalvarCancelar(false);
            lblProcesso.Text = "Processo";
        }
        private void btnAlterar_Click(object sender, EventArgs e)
        {
            IncluirProcesso = false;
            HabilitarSalvarCancelar(true);
        }
        private void btnExcluir_Click(object sender, System.EventArgs e)
        {            
            // Deletar processo pela ID            
            int id = Convert.ToInt32(txtID.Text);
            foreach (Process p in Process.GetProcesses())
            {
                if (p.Id == id)
                {
                    p.Kill();
                }
            }
            MontarGridProcessos();
        }
        private void SetarPrioridade( ref Process process )
        {
            if (cmbPrioridade.SelectedIndex == 0)
                process.PriorityClass = ProcessPriorityClass.Normal;
            else if (cmbPrioridade.SelectedIndex == 2)
                process.PriorityClass = ProcessPriorityClass.Idle;
            else if (cmbPrioridade.SelectedIndex == 3)
                process.PriorityClass = ProcessPriorityClass.High;
            else if (cmbPrioridade.SelectedIndex == 4)
                process.PriorityClass = ProcessPriorityClass.RealTime;    
        }
        private void CriarProcesso()
        {
            //Criar processo
            var Processo = Process.Start(@txtProcesso.Text);
            Processo = Process.GetProcessById(Processo.Id);
            SetarPrioridade(ref Processo);            
        }
        private void AlterarPrioridadeProcesso()
        {
            var process = Process.GetProcessById(Convert.ToInt32(txtID.Text));
            SetarPrioridade(ref process);
        }
        private void btnEscolherPasta_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog janela = new FolderBrowserDialog(); //Declaração
            
            if (janela.ShowDialog() == DialogResult.OK)
            {
                txtProcesso.Text = janela.SelectedPath;
            }
        }
    }
}

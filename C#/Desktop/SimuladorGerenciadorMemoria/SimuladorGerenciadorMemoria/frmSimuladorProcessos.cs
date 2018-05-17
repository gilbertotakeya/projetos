using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Runtime.InteropServices;
using System.Threading;

namespace SimuladorGerenciadorMemoria
{
    public partial class frmSimuladorProcessos : Form
    {
        // Pegar total memória, via API
        [ StructLayout(LayoutKind.Sequential) ]
        private struct STATUSMEMORIA
        {
            public uint dwLength;
            public uint dwMemoryLoad;
            public uint dwTotalPhys;
            public uint dwAvailPhys;
            public uint dwTotalPageFile;
            public uint dwAvailPageFile;
            public uint dwTotalVirtual;
            public uint dwAvailVirtual;
        }

        [ DllImport("Kernel32.dll", CharSet = CharSet.Auto, SetLastError = true) ]
        private static extern void GlobalMemoryStatus(ref STATUSMEMORIA lpBuffer);

        // A T R I B U T O S   U T I L I Z A D O S   N A   C L A S S E
        private UInt64  vMemoriaDisponibilizada = 0, UltimoEnderecoIncluso = 0;
        private UInt64  TotalMemoria = 0, TotalMemoriaLivre = 0;
        private string  ProcessosCriados        = "";

        // Listas dos processos, como se fosse struct
        List<CProcessos> ListaProcessosMuitoAlto    = new List<CProcessos>();
        List<CProcessos> ListaProcessosAlto         = new List<CProcessos>();
        List<CProcessos> ListaProcessosAcimaNormal  = new List<CProcessos>();
        List<CProcessos> ListaProcessosNormal       = new List<CProcessos>();
        List<CProcessos> ListaProcessosBaixo        = new List<CProcessos>();
        List<CProcessos> ListaProcessosMuitoBaixo   = new List<CProcessos>();

        // String auxiliar, para achar mais fácil aonde o processo está
        string IDProcessosMuitoAlto = "", IDProcessosAlto = "", IDProcessosAcimaNormal = "";
        string IDProcessosNormal    = "", IDProcessosBaixo= "", IDProcessosMuitoBaixo  = "";

        public frmSimuladorProcessos()
        {
            InitializeComponent();

            // B U S C A R  M E M Ó R I A  D A   M Á Q U I N A  V I A  A P I
            try
            {
                STATUSMEMORIA ms = new STATUSMEMORIA();
                GlobalMemoryStatus(ref ms);
                TotalMemoria        = Convert.ToUInt64(ms.dwTotalPhys);
                TotalMemoriaLivre   = Convert.ToUInt64(ms.dwTotalPhys);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
            // Monto meu combobox com os enums
            cmbPrioridade.DataSource = Enum.GetValues(typeof(EnumPrioridade));

            //Calculo do percentual para disponibilizar uma percentagem X para a memória
            //Por definição minha, cada processo terá disponivel 5% da memória para uso.
            double Valor = TotalMemoria * 0.05;
            vMemoriaDisponibilizada = Convert.ToUInt64(Valor);

        }
        private void btnLocalizar_Click(object sender, EventArgs e)
        {
            OpenFileDialog FD = new OpenFileDialog();
            Stream myStream = null;

            FD.InitialDirectory = "c:\\";
            FD.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
            FD.FilterIndex = 2;
            FD.RestoreDirectory = true;

            if (FD.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    if ((myStream = FD.OpenFile()) != null)
                    {
                        using (myStream)
                        {
                            txtProcesso.Text = FD.FileName;
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error: Could not read file from disk. Original error: " + ex.Message);
                }
            }            
        }
        private void btnCriar_Click(object sender, EventArgs e)
        {
            // Crio o processo e defino ele como Bloqueado.
            if (txtProcesso.Text == "")
            {
                MessageBox.Show("Informe o caminho do processo para executar!", "A T E N Ç Ã O!", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            CProcessos Processo = new CProcessos();

            if ( UltimoEnderecoIncluso + 1 + vMemoriaDisponibilizada > TotalMemoria)
            {
                MessageBox.Show("Não é possível alocar + processos!\nFalta espaço para alocação!", "A T E N Ç Ã O!", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }
            bool IDOK = false;
            while( !IDOK ) // Gera uma ID Randomica
            {
                Random RandomNumber = new Random();
                Processo.vIDProcesso = Convert.ToUInt64(RandomNumber.Next());

                if (ProcessosCriados.IndexOf( "_" + Convert.ToString(Processo.vIDProcesso) + "-" ) == -1)
                    IDOK = true;
            }            
            
            ProcessosCriados += "_" + Processo.vIDProcesso + "-";

            Processo.vDescricaoProcesso = txtProcesso.Text;
            Processo.vEstado            = EnumEstadoProcessos.Bloqueado;
            Processo.vEnderecoInicial   = UltimoEnderecoIncluso;
            UltimoEnderecoIncluso       = UltimoEnderecoIncluso + 1 + vMemoriaDisponibilizada;

#region Selecionar_E_AdicionarProcesso
            if (cmbPrioridade.SelectedIndex == 1)
            {
                Processo.vPrioridade = EnumPrioridade.Baixo;
                IDProcessosBaixo     = "_" + Processo.vIDProcesso + "-";
                ListaProcessosBaixo.Add( Processo );
            }
            else if (cmbPrioridade.SelectedIndex == 2)
            {
                Processo.vPrioridade = EnumPrioridade.Normal;
                IDProcessosNormal    = "_" + Processo.vIDProcesso + "-";
                ListaProcessosNormal.Add( Processo );
            }
            else if (cmbPrioridade.SelectedIndex == 3)
            {
                Processo.vPrioridade    = EnumPrioridade.AcimaNormal;
                IDProcessosAcimaNormal  = "_" + Processo.vIDProcesso + "-";
                ListaProcessosAcimaNormal.Add( Processo );
            }
            else if (cmbPrioridade.SelectedIndex == 4)
            {
                Processo.vPrioridade = EnumPrioridade.Alto;
                IDProcessosAlto      = "_" + Processo.vIDProcesso + "-";
                ListaProcessosAlto.Add( Processo );
            }
            else if (cmbPrioridade.SelectedIndex == 5)
            {
                Processo.vPrioridade = EnumPrioridade.MuitoAlto;
                IDProcessosMuitoAlto = "_" + Processo.vIDProcesso + "-";
                ListaProcessosMuitoAlto.Add(Processo);
            }
            else
            {
                Processo.vPrioridade = EnumPrioridade.MuitoBaixo;
                IDProcessosMuitoBaixo= "_" + Processo.vIDProcesso + "-";
                ListaProcessosMuitoBaixo.Add(Processo);
            }
#endregion

            MessageBox.Show("Processo criado com sucesso!", "A T E N Ç Ã O");
            MontarGrid();
            //IniciarThread();
        }
        private void MontarGrid()
        {
            dtgvProcessos.Rows.Clear();
            
            InserirGrid(ListaProcessosMuitoAlto);
            InserirGrid(ListaProcessosAlto);
            InserirGrid(ListaProcessosAcimaNormal);
            InserirGrid(ListaProcessosNormal);
            InserirGrid(ListaProcessosBaixo);
            InserirGrid(ListaProcessosMuitoBaixo);
        }
        private void InserirGrid(List<CProcessos> Lista)
        {
            foreach (CProcessos p in Lista)
            {
                dtgvProcessos.Rows.Add(p.vIDProcesso, p.vDescricaoProcesso, p.vPrioridade, p.vEstado, p.vEnderecoInicial, Math.Round(Convert.ToDecimal(p.vEnderecoInicial + vMemoriaDisponibilizada), 0));
            }            
        }
        private void dtgvProcessos_CellEnter(object sender, DataGridViewCellEventArgs e)
        {            

        }
        private void AtualizarProcesso(ref List<CProcessos> Lista, UInt64 ID, EnumEstadoProcessos EstadoProcesso )
        {
            foreach (CProcessos p in Lista)
            {
                if( p.vIDProcesso == ID )
                {
                    p.vEstado = EstadoProcesso;
                    break;
                }
            }
        }
        private void ChamarAtualizarProcesso(EnumEstadoProcessos EstadoProcesso)
        {
            string Estado = dtgvProcessos.CurrentRow.Cells[2].Value.ToString();
            UInt64 ID = Convert.ToUInt64(dtgvProcessos.CurrentRow.Cells[0].Value.ToString());

            if (Estado == "MuitoBaixo")
                AtualizarProcesso(ref ListaProcessosMuitoBaixo, ID, EstadoProcesso);
            else if (Estado == "Baixo")
                AtualizarProcesso(ref ListaProcessosBaixo, ID, EstadoProcesso);
            else if (Estado == "Normal")
                AtualizarProcesso(ref ListaProcessosNormal, ID, EstadoProcesso);
            else if (Estado == "AcimaNormal")
                AtualizarProcesso(ref ListaProcessosAcimaNormal, ID, EstadoProcesso);
            else if (Estado == "Alto")
                AtualizarProcesso(ref ListaProcessosAlto, ID, EstadoProcesso);
            else if (Estado == "MuitoAlto")
                AtualizarProcesso(ref ListaProcessosMuitoAlto, ID, EstadoProcesso);

            MontarGrid();
        }
        private void btnAcordar_Click(object sender, EventArgs e)
        {
            ChamarAtualizarProcesso(EnumEstadoProcessos.ProntoParaExecucao);
        }
        private void btnPausar_Click(object sender, EventArgs e)
        {
            ChamarAtualizarProcesso(EnumEstadoProcessos.ProntoParaExecucao);
        }
        private void btnFinalizar_Click(object sender, EventArgs e)
        {
            ChamarAtualizarProcesso(EnumEstadoProcessos.Bloqueado);
        }

        private void EscalonadorProcessos()
        {
            EscalonarLista(ref ListaProcessosMuitoAlto);
            EscalonarLista(ref ListaProcessosAlto);
            EscalonarLista(ref ListaProcessosAcimaNormal);
            EscalonarLista(ref ListaProcessosNormal);
            EscalonarLista(ref ListaProcessosBaixo);
            EscalonarLista(ref ListaProcessosMuitoBaixo);            
        }
        private void EscalonarLista(ref List<CProcessos> Lista)
        {
            foreach( CProcessos P in Lista )
            {
                P.vEstado = EnumEstadoProcessos.EmExecucao;
                MontarGrid();
                for (int i = 0; i < 999999999; i++) { }
                P.vEstado = EnumEstadoProcessos.ProntoParaExecucao;
            }
        }
    }
    //Definições da classe.
    public enum EnumEstadoProcessos
    {
        Indefinido,
        Bloqueado,
        ProntoParaExecucao,
        EmExecucao
    }
    public enum EnumPrioridade
    {
        MuitoBaixo  = 0,
        Baixo       = 1,
        Normal      = 2,
        AcimaNormal = 3,
        Alto        = 4,
        MuitoAlto   = 5
    }
    public class CProcessos
    {
        public UInt64 vIDProcesso{ get; set; }
        public UInt64 vEnderecoInicial { get; set; }
        public String vDescricaoProcesso { get; set; }
        public EnumPrioridade vPrioridade { get; set; }
        public EnumEstadoProcessos vEstado { get; set; }
    }
}

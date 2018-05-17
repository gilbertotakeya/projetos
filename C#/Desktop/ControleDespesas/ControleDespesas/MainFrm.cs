using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace ControleDespesas
{
    public partial class MainFrm : Form
    {
        public MainFrm()
        {
            InitializeComponent();
        }

        private void pctEmpresa_Click(object sender, EventArgs e)
        {
            FrmPessoasEmpresas PesEmp = new FrmPessoasEmpresas();
            PesEmp.ShowDialog();
        }

        private void pctPessoas_Click(object sender, EventArgs e)
        {
            FrmPessoasEmpresas PesEmp = new FrmPessoasEmpresas();
            PesEmp.ShowDialog();
        }
    }
}
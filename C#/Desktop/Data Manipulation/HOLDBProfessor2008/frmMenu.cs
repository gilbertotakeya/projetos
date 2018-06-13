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
    public partial class frmMenu : Form
    {
        public frmMenu()
        {
            InitializeComponent();

            

        }

        private void btnClientes_Click(object sender, EventArgs e)
        {
            frmClientes cli = new frmClientes();
            cli.ShowDialog();
        }

        private void btnPedidos_Click(object sender, EventArgs e)
        {
            frmPedidos ped = new frmPedidos();
            ped.ShowDialog();
        }

 

    }
}

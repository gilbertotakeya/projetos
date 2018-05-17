using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace CadastroCliente
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            //Application.Run(new FrmClientes());
            //FrmLogin Login = new FrmLogin();
            //Login.ShowDialog();
            //if( Login.LiberouUsuario )
                Application.Run(new FrmClientes());
        }
    }
}

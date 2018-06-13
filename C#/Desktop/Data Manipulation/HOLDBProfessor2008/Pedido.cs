using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HOLDBProfessor2008
{
    public class Pedido
    {
        public int idProduto { get; set; }
        public string NomeProduto { get; set; }
        public double ValorProduto { get; set; }
        public double ValorTotal { get; set; }
        public double Qtde { get; set; }

       

        //private string nomeProduto;

        //public string NomeProduto1
        //{
        //    get { return nomeProduto; }
        //    set {
        //        if (nomeProduto.Length < 100)
        //            nomeProduto = value;
        //        else
        //            nomeProduto = "";
        //    }
        //}

    }
}

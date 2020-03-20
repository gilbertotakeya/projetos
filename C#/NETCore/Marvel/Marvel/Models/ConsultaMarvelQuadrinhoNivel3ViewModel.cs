using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Marvel.Models
{
    //Dados dos personagens
    public class ConsultaMarvelQuadrinhoNivel3ViewModel
    {
        public int id { get; set; }

        public string title { get; set; }

        public string description { get; set; }

        public ConsultaMarvelPersonagemNivel4ImagemViewModel thumbnail { get; set; }

        public string resourceURI { get; set; }
    }
}
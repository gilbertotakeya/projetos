using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Marvel.Models
{
    //Dados dos personagens
    public class ConsultaMarvelPersonagemNivel3ViewModel
    {
        public int id { get; set; }

        public string name { get; set; }

        public string description { get; set; }

        public DateTime? modified { get; set; }

        public ConsultaMarvelPersonagemNivel4ImagemViewModel thumbnail { get; set; }

        public ConsultaMarvelPersonagemNivel4QuadrinhosViewModel comics { get; set; }

        public string resourceURI { get; set; }
    }
}
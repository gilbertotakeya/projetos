using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Marvel.Models
{
    //Caminho da imagem do personagem
    public class ConsultaMarvelPersonagemNivel4ImagemViewModel
    {
        public string path { get; set; }

        public string extension { get; set; }


        public string completePath
        {
            get
            {
                return path + "." + extension;
            }
        }
    }
}

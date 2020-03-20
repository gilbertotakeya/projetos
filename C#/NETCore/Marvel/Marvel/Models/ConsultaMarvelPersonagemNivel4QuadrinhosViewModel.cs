using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Marvel.Models
{
    //Caminho da imagem do personagem
    public class ConsultaMarvelPersonagemNivel4QuadrinhosViewModel
    {
        public int available { get; set; }

        public string collectionURI { get; set; }

        public List<ConsultaMarvelPersonagemNivel5QuadrinhosDetalhes> items { get; set; }
    }
}

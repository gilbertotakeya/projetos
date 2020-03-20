using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Marvel.Models
{
    public class ConsultaMarvelPersonagemNivel1ViewModel
    {
        public string code { get; set; }
        public string status { get; set; }
        public string copyright { get; set; }
        public string attributionText { get; set; }
        public string attributionHTML { get; set; }
        public string etag { get; set; }
        public ConsultaMarvelPersonagemNivel2ViewModel data { get; set; }
    }
}

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace BizCommerce.Entrevista.Aplicacao.Models
{
    public class Profissional
    {
        [Key]
        public int Id_profissional { get; set; }

        public string Nom_profissional { get; set; }

        public DateTime Dat_admissao { get; set; }
        public DateTime? Dat_desligamento{ get; set; }
    }
}
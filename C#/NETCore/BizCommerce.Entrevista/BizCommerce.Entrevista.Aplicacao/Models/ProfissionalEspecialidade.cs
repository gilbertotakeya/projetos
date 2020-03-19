using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace BizCommerce.Entrevista.Aplicacao.Models
{
    public class ProfissionalEspecialidade
    {
        [Key]
        public int Id_Profissional_Especialidade { get; set; }
        public int ref_Id_profissional { get; set; }

        [ForeignKey("ref_Id_profissional")]
        public Profissional profissional { get; set; }

        public int ref_Id_Especialidade { get; set; }

        [ForeignKey("ref_Id_Especialidade")]
        public Especialidade especialidade { get; set; }
    }
}
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace BizCommerce.Entrevista.Aplicacao.Models
{
    public class Especialidade
    {
        [Key]
        public int Id_Especialidade { get; set; }
        public string Nom_Especialidade { get; set; }
    }
}
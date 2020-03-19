using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;
namespace BizCommerce.Entrevista.Aplicacao.Models
{
    public class conexaoBanco : DbContext
    {
        public DbSet<Profissional> profissional { get; set; }
        public DbSet<Especialidade> especialidade { get; set; }
        public DbSet<ProfissionalEspecialidade> profissionalEspecialidade { get; set; }
    }
}
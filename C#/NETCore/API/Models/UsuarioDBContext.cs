using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace API.Models
{
    public class UsuarioDBContext: DbContext
    {
        public UsuarioDBContext(DbContextOptions<UsuarioDBContext> options) : base (options)
        {

        }

        public DbSet<Usuario> Usuarios { get; set; }
    }
}

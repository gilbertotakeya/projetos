using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using AnimalFinder.Models;

namespace AnimalFinder.Models
{
    public class AnimalFinderContext : DbContext
    {
        public AnimalFinderContext (DbContextOptions<AnimalFinderContext> options)
            : base(options)
        {
        }

        public DbSet<AnimalFinder.Models.Animal> Animal { get; set; }

        public DbSet<AnimalFinder.Models.Dono> Dono { get; set; }
    }
}

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;
using static AnimalFinder.Models.Definicoes;

namespace AnimalFinder.Models
{
    public class Animal
    {
        [Key]
        public int Id { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "")]
        [Display(Description ="Nome do animal", Name = "Nome do animal")]
        [MaxLength(4000, ErrorMessage ="Nome atingiu o tamanho máximo de 4000 caracteres")]
        public string Nome { get; set; }
        
        [Display(Description = "Idade", Name = "Idade")]
        public int Idade { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "")]
        [Display(Description = "Informações extras", Name = "Informações extras")]
        public string InformacoesExtras { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "")]
        public string Cidade { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "")]
        public string Estado { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "")]
        public SituacaoAnimal Status { get; set; }

        public int IdDono { get; set; }

        [NotMapped]
        public Dono Dono { get; set; }
    }
}

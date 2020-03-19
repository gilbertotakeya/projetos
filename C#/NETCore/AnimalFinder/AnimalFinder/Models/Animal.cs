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

        [Required(AllowEmptyStrings = false, ErrorMessage = "Informe o nome do animal")]
        [Display(Description ="Nome do animal", Name = "Nome do animal")]
        [MaxLength(4000, ErrorMessage ="Nome atingiu o tamanho máximo de 4000 caracteres")]
        public string Nome { get; set; }
        
        [Display(Description = "Idade", Name = "Idade")]
        public int Idade { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Informe dados adicionais")]
        [Display(Description = "Informações extras", Name = "Informações extras")]
        public string InformacoesExtras { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Informe a cidade")]
        public string Cidade { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Informe o estado")]
        public Estado Estado { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Informe o status")]
        public SituacaoAnimal Status { get; set; }

        [Display(Description = "Dono", Name = "Dono")]
        public int IdDono { get; set; }

        [Display(Description = "Dono", Name = "Dono")]
        [ForeignKey("IdDono")]
        public virtual Dono Dono { get; set; }
    }
}

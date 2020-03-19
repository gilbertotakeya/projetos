using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AnimalFinder.Models
{
    public class Dono
    {
        [Key]
        public int Id { get; set; }

        [Required(AllowEmptyStrings =false, ErrorMessage ="Informe o nome do proprietário")]
        [Display(Description = "Nome", Name = "Nome")]
        [MaxLength(4000, ErrorMessage = "Nome atingiu o tamanho máximo de 4000 caracteres")]
        public string Nome { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Informe o e-mail")]
        [Display(Description = "E-mail", Name = "E-mail")]
        [MaxLength(1000, ErrorMessage = "E-mail atingiu o tamanho máximo de 1000 caracteres")]
        public string Email { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Informe o telefone")]
        [MaxLength(15, ErrorMessage = "Telefone atingiu o tamanho máximo de 15 caracteres")]
        public string Telefone { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Informe a senha")]
        [MaxLength(4000, ErrorMessage = "Senha tamanho o tamanho máximo de 4000 caracteres")]
        [DataType(DataType.Password)]
        public string Senha { get; set; }
    }
}

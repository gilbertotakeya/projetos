using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace API.Models
{
    public class Usuario
    {
        [Key]
        public int IdUsuario { get; set; }
        public string Nome { get; set; }
        [DataType(DataType.Password)]
        public string Senha { get; set; }
        [DataType(DataType.PhoneNumber, ErrorMessage = "Telefone em formato inválido.")]
        public string Telefone { get; set; }

        [DataType(DataType.EmailAddress, ErrorMessage = "E-mail em formato inválido.")]
        public string Email { get; set; }
        public DateTime dataNascimento { get; set; }
        public string Endereco { get; set; }
        //validacao < 18 anos nao pode
        //validacao telefone
        //email
        //senha 1 upper, 1 lower, 1 caracter especial, 6 caracteres minimo
    }
}

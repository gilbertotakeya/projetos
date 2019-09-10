using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API.Models
{
    public class Usuario
    {
        public int IdUsuario { get; set; }
        public string Nome { get; set; }
        public string Senha { get; set; }
        public string Telefone { get; set; }
        public string Email { get; set; }
        public DateTime dataNascimento { get; set; }
        public string Endereco { get; set; }
        //validacao < 18 anos nao pode
        //validacao telefone
        //email
        //senha 1 upper, 1 lower, 1 caracter especial, 6 caracteres minimo
    }
}

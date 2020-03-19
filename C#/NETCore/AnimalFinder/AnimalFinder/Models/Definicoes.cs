using System.Collections.Generic;
using System.Security.Claims;
using System.ComponentModel.DataAnnotations;

namespace AnimalFinder.Models
{
    public class Definicoes
    {
        public enum SituacaoAnimal
        {
            Perdido,
            Comunicado,
            Encontrado
        }
        public enum Estado
        {
            [Display(Name = "Acre")]
            AC,
            [Display(Name = "Alagoas")]
            AL,
            [Display(Name = "Amazonas")]
            AM,
            [Display(Name = "Amapá")]
            AP,
            [Display(Name = "Bahia")]
            BA,
            [Display(Name = "Ceará")]
            CE,
            [Display(Name = "Distrito Federal")]
            DF,
            [Display(Name = "Espírito Santo")]
            ES,
            [Display(Name = "Goias")]
            GO,
            [Display(Name = "Maranhão")]
            MA,
            [Display(Name = "Minas Gerais")]
            MG,
            [Display(Name = "Mato Grosso do Sul")]
            MS,
            [Display(Name = "Mato Grosso")]
            MT,
            [Display(Name = "Pará")]
            PA,
            [Display(Name = "Paraíba")]
            PB,
            [Display(Name = "Pernambuco")]
            PE,
            [Display(Name = "Piauí")]
            PI,
            [Display(Name = "Paraná")]
            PR,
            [Display(Name = "Rio de Janeiro")]
            RJ,
            [Display(Name = "Rio Grande do Norte")]
            RN,
            [Display(Name = "Rondônia")]
            RO,
            [Display(Name = "Roraima")]
            RR,
            [Display(Name = "Rio Grande do Sul")]
            RS,
            [Display(Name = "Santa Catarina")]
            SC,
            [Display(Name = "Sergipe")]
            SE,
            [Display(Name = "São Paulo")]
            SP,
            [Display(Name = "Tocantins")]
            TO
        }
    }

    public interface IUser
    {
        string Name { get; }
        bool IsAuthenticated();
        IEnumerable<Claim> GetClaimsIdentity();
    }

}

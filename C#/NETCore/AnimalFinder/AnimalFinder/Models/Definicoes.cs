using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

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

            AC, AL, AM, AP, BA, CE, DF, ES, GO,
            MA, MG, MS, MT, PA, PB, PE, PI, PR,
            RJ, RN, RO, RR, RS, SC, SE, SP, TO
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace Contract
{
    [DataContract]
    public class Categoria
    {
        [DataMember]
        public string Nome { get; set; }

        [DataMember]
        public string Descricao { get; set; }
    }
}

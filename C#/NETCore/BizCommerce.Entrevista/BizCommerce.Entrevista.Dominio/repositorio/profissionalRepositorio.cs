using BizCommerce.ConexaoBanco;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BizCommerce.Entrevista.Dominio.repositorio
{
    public class profissionalRepositorio : ConexaoBancoDados, IConexaoBancoDados<Profissional>
    {
        public Profissional BuscarID(int ID)
        {
            throw new NotImplementedException();
        }

        public string Deletar(Profissional entidade)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Profissional> listarTodos()
        {
            throw new NotImplementedException();
        }

        public string Salvar(Profissional entidade)
        {
            throw new NotImplementedException();
        }
    }
}

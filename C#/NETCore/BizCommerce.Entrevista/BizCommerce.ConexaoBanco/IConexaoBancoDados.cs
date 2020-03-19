using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BizCommerce.ConexaoBanco
{
    public interface IConexaoBancoDados<T> where T: class
    {
        string Salvar(T entidade);
        string Deletar(T entidade);
        T BuscarID(int ID);

        IEnumerable<T> listarTodos();
    }
}
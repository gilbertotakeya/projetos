using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
namespace Contract
{
    [ServiceContract]
    //Tem que estar pública para conseguir implementar a interface em outras classes
    public interface IService
    {
        [OperationContract]
        List<Categoria> GetCategorias();

        [OperationContract]
        void SetCategorias(Categoria cat);
    }
}

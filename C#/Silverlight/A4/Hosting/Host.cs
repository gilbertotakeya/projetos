using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using Servicos;
using Contract;
using System.ServiceModel.Description; // Habilita serviço metadata


namespace Hosting
{
    public class Host
    {
        static void Main(string[] args)
        {
            using( ServiceHost host = new ServiceHost(typeof(Servico), new Uri("net.tcp://localhost:3933"),new Uri("http://localhost:40") ) )
            {
                host.Description.Behaviors.Add(new ServiceMetadataBehavior(){ HttpGetEnabled = true } );

                host.AddServiceEndpoint(typeof(IService), new NetTcpBinding(), "svc");
                host.AddServiceEndpoint(typeof(IService), new BasicHttpBinding(), "svc");
                host.AddServiceEndpoint(typeof(IMetadataExchange), MetadataExchangeBindings.CreateMexHttpBinding(), "mex");

                host.Open();
                Console.ReadLine();
            }
        }
    }
}

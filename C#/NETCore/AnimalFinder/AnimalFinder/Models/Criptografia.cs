using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace AnimalFinder.Models
{
    public static class Criptografia
    {
        private static string chaveSenha()
        {
            return "a9cdacfaaa6308c2dc66238fa7524aae";
        }

        private static string vetorInicializacaoSenha()
        {
            return "a9d6bcdbf1f123d6";
        }

        //Retirado de: http://www.devmedia.com.br/criptografia-em-net-utilizando-a-classe-rijndael/26440
        private static Rijndael CriarInstanciaRijndael(string chave, string vetorInicializacao)
        {
            if (!(chave != null && (chave.Length == 16 || chave.Length == 24 || chave.Length == 32)))
            {
                throw new Exception("A chave de criptografia deve possuir " + "16, 24 ou 32 caracteres.");
            }
            if (vetorInicializacao == null || vetorInicializacao.Length != 16)
            {
                throw new Exception("O vetor de inicialização deve possuir " + "16 caracteres.");
            }

            Rijndael algoritmo = Rijndael.Create();
            algoritmo.Key = Encoding.ASCII.GetBytes(chave);
            algoritmo.IV = Encoding.ASCII.GetBytes(vetorInicializacao);
            return algoritmo;
        }

        public static string EncriptarDado(string dadoUsuario)
        {
            return Encriptar(chaveSenha(), vetorInicializacaoSenha(), dadoUsuario);
        }

        public static string DecriptarDado(string dadoUsuario)
        {
            return Decriptar(chaveSenha(), vetorInicializacaoSenha(), dadoUsuario);
        }

        private static string Encriptar(string chave, string vetorInicializacao, string textoNormal)
        {
            if (String.IsNullOrEmpty(textoNormal))
            {
                throw new Exception("O conteúdo a ser encriptado não pode " + "ser uma string vazia.");
            }
            try
            {
                Rijndael algoritmo = CriarInstanciaRijndael(chave, vetorInicializacao);
                ICryptoTransform encryptor = algoritmo.CreateEncryptor(algoritmo.Key, algoritmo.IV);
                using (MemoryStream streamResultado = new MemoryStream())
                {
                    using (CryptoStream csStream = new CryptoStream(streamResultado, encryptor, CryptoStreamMode.Write))
                    {
                        using (StreamWriter writer = new StreamWriter(csStream))
                        {
                            writer.Write(textoNormal);
                        }
                    }
                    return ArrayBytesToHexString(streamResultado.ToArray());
                }
            }
            catch (Exception ex)
            {
                var f = ex.Message;
            }
            return "";
        }

        private static string ArrayBytesToHexString(byte[] conteudo)
        {
            string[] arrayHex = Array.ConvertAll(conteudo, b => b.ToString("X2"));
            return string.Concat(arrayHex);
        }

        private static string Decriptar(string chave, string vetorInicializacao, string textoEncriptado)
        {
            if (String.IsNullOrEmpty(textoEncriptado))
            {
                throw new Exception("O conteúdo a ser decriptado não pode " + "ser uma string vazia.");
            }

            if (textoEncriptado.Length % 2 != 0)
            {
                throw new Exception("O conteúdo a ser decriptado é inválido.");
            }

            using (Rijndael algoritmo = CriarInstanciaRijndael(chave, vetorInicializacao))
            {
                ICryptoTransform decryptor = algoritmo.CreateDecryptor(algoritmo.Key, algoritmo.IV);
                string textoDecriptografado = null;
                using (MemoryStream streamTextoEncriptado = new MemoryStream(HexStringToArrayBytes(textoEncriptado)))
                {
                    using (CryptoStream csStream = new CryptoStream(streamTextoEncriptado, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader reader = new StreamReader(csStream))
                        {
                            textoDecriptografado = reader.ReadToEnd();
                        }
                    }
                }
                return textoDecriptografado;
            }
        }
        private static byte[] HexStringToArrayBytes(string conteudo)
        {
            int qtdeBytesEncriptados = conteudo.Length / 2;
            byte[] arrayConteudoEncriptado = new byte[qtdeBytesEncriptados];
            for (int i = 0; i < qtdeBytesEncriptados; i++)
            {
                arrayConteudoEncriptado[i] = Convert.ToByte(conteudo.Substring(i * 2, 2), 16);
            }
            return arrayConteudoEncriptado;
        }

        public static void Dispose()
        {
        }
    }
}
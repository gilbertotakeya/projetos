using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Marvel.Models;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net;
using Newtonsoft.Json;

namespace Marvel.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
         

            return View();
        }

        public async Task<JsonResult> All()
        {
            var handler = new HttpClientHandler() { AutomaticDecompression = DecompressionMethods.GZip | DecompressionMethods.Deflate };

            var personagens = "";

            using (HttpClient client = new HttpClient(handler) { Timeout = TimeSpan.FromSeconds(30) })
            {
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Por razões de segurança, o código ficará engessado com TS, public apikey pois a hash é composta por Converter para MD5(TS + Private API KEY + Public API KEY)
                var response = await client.GetAsync("https://gateway.marvel.com:443/v1/public/characters?ts=1584664680&apikey=02fbac84a29637a7ffb177e12805916b&hash=ad9f87ea6a6fb2474b4534426a05e2ed").ConfigureAwait(false);

                if (response.IsSuccessStatusCode)
                {
                    if (!string.IsNullOrEmpty(response.ToString()))
                    {
                        var result = response.Content.ReadAsStringAsync();

                        var model = JsonConvert.DeserializeObject<List<ConsultaMarvelPersonagemNivel1ViewModel>>("[" + result.Result + "]");

                        var listaPersonagem = model.FirstOrDefault().data.results;
                        personagens = JsonConvert.SerializeObject(listaPersonagem);
                    }
                }
            }

            return Json(personagens);
        }

        public async Task<IActionResult> Personagens()
        {
            var handler = new HttpClientHandler() { AutomaticDecompression = DecompressionMethods.GZip | DecompressionMethods.Deflate };

            using (HttpClient client = new HttpClient(handler) { Timeout = TimeSpan.FromSeconds(30) })
            {
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Por razões de segurança, o código ficará engessado com TS, public apikey pois a hash é composta por Converter para MD5(TS + Private API KEY + Public API KEY)
                var response = await client.GetAsync("https://gateway.marvel.com:443/v1/public/characters?ts=1584664680&apikey=02fbac84a29637a7ffb177e12805916b&hash=ad9f87ea6a6fb2474b4534426a05e2ed").ConfigureAwait(false);

                if (response.IsSuccessStatusCode)
                {
                    if (!string.IsNullOrEmpty(response.ToString()))
                    {
                        var result = response.Content.ReadAsStringAsync();

                        var model = JsonConvert.DeserializeObject<List<ConsultaMarvelPersonagemNivel1ViewModel>>("[" + result.Result + "]");

                        var listaPersonagem = model.FirstOrDefault().data.results;
                        return View(listaPersonagem.AsEnumerable());
                    }
                }
            }

            return View();
        }

        public async Task<IActionResult> Quadrinhos(int id)
        {
            var handler = new HttpClientHandler() { AutomaticDecompression = DecompressionMethods.GZip | DecompressionMethods.Deflate };

            using (HttpClient client = new HttpClient(handler) { Timeout = TimeSpan.FromSeconds(30) })
            {
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Por razões de segurança, o código ficará engessado com TS, public apikey pois a hash é composta por Converter para MD5(TS + Private API KEY + Public API KEY)
                var response = await client.GetAsync("https://gateway.marvel.com:443/v1/public/characters/" + id.ToString() + "/comics?ts=1584664680&apikey=02fbac84a29637a7ffb177e12805916b&hash=ad9f87ea6a6fb2474b4534426a05e2ed").ConfigureAwait(false);

                if (response.IsSuccessStatusCode)
                {
                    if (!string.IsNullOrEmpty(response.ToString()))
                    {
                        var result = response.Content.ReadAsStringAsync();

                        var model = JsonConvert.DeserializeObject<List<ConsultaMarvelQuadrinhoNivel1ViewModel>>("[" + result.Result + "]");

                        var listaQuadrinhosDoPersonagem = model.FirstOrDefault().data.results;
                        return View(listaQuadrinhosDoPersonagem.AsEnumerable());
                    }
                }
            }

            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}

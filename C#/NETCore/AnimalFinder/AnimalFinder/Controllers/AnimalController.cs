using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using AnimalFinder.Models;
using Microsoft.AspNetCore.Http;

namespace AnimalFinder.Controllers
{
    public class AnimalController : Controller
    {
        private readonly AnimalFinderContext _context;
        private readonly IUser _user;
        private readonly IHttpContextAccessor _httpContextAccessor;
        private ISession _session => _httpContextAccessor.HttpContext.Session;

        public AnimalController(AnimalFinderContext context, IUser user, IHttpContextAccessor accessor)
        {
            _context = context;
            _user = user;
            _httpContextAccessor = accessor;
        }

        private void CarregarDadosPadrao()
        {
            var IdUsuarioLogado = _session.GetInt32("IdUsuarioLogado");
            if (IdUsuarioLogado.HasValue)
            {
                ViewBag.IdUsuarioLogado = _session.GetInt32("IdUsuarioLogado").Value;
            }
            else
                ViewBag.IdUsuarioLogado = -1;
        }

        // GET: Animals
        public async Task<IActionResult> Index()
        {
            var lista = await _context.Animal.Where(w => w.Status == Definicoes.SituacaoAnimal.Perdido).ToListAsync();

            lista.ForEach(w=>
            {
                if (w.IdDono > 0)
                {
                    w.Dono = _context.Dono.Where(f => f.Id == w.IdDono).FirstOrDefault();
                }
            }) ;

            CarregarDadosPadrao();

            return View(lista);
        }

        public async Task<IActionResult> MyAnimals()
        {
            if (_session.GetString("UsuarioLogado") == null)
            {
                return RedirectToAction("Login", "Dono");
            }

            CarregarDadosPadrao();

            var lista = await _context.Animal.Where(w => w.IdDono == _session.GetInt32("IdUsuarioLogado").Value).ToListAsync();

            lista.ForEach(w =>
            {
                if (w.IdDono > 0)
                {
                    w.Dono = _context.Dono.Where(f => f.Id == w.IdDono).FirstOrDefault();
                }
            });            

            return View(lista);
        }

        public async Task<IActionResult> AllAnimals()
        {
            return View(await _context.Animal.ToListAsync());
        }

        // GET: Animals/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var animal = await _context.Animal.FirstOrDefaultAsync(m => m.Id == id);
            if (animal == null)
            {
                return NotFound();
            }

            return View(animal);
        }

        // GET: Animals/Create
        public IActionResult Create()
        {
            if (_session.GetString("UsuarioLogado") == null)
            {
                return RedirectToAction("Login", "Dono");
            }

            CarregarDadosPadrao();

            return View();
        }

        // POST: Animals/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(Animal animal, String btnAction)
        {
            ModelState.Remove("EncontradoPor");
            ModelState.Remove("TelefoneContato");
            ModelState.Remove("Localizacao");

            if (ModelState.IsValid)
            {
                animal.IdDono = _session.GetInt32("IdUsuarioLogado").Value;
                animal.Status = Definicoes.SituacaoAnimal.Perdido;

                _context.Add(animal);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(animal);
        }

        // GET: Animals/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            if (_session.GetString("UsuarioLogado") == null)
            {
                return RedirectToAction("Login", "Dono");
            }

            CarregarDadosPadrao();

            var animal = await _context.Animal.FindAsync(id);
            if (animal == null)
            {
                return NotFound();
            }
            return View(animal);
        }

        // POST: Animals/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, Animal animal)
        {
            if (id != animal.Id)
            {
                return NotFound();
            }

            ModelState.Remove("EncontradoPor");
            ModelState.Remove("TelefoneContato");
            ModelState.Remove("Localizacao");

            if (ModelState.IsValid)
            {
                try
                {
                    animal.IdDono = _session.GetInt32("IdUsuarioLogado").Value;
                    _context.Update(animal);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!AnimalExists(animal.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(animal);
        }

        public async Task<IActionResult> Find(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var animal = await _context.Animal.FindAsync(id);
            if (animal == null)
            {
                return NotFound();
            }
            return View(animal);
        }

        // POST: Animals/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Find(int id, Animal animal)
        {
            if (id != animal.Id)
            {
                return NotFound();
            }

            try
            {
                if (String.IsNullOrEmpty(animal.EncontradoPor) || String.IsNullOrWhiteSpace(animal.EncontradoPor))
                    throw new SystemException("Informe o nome da pessoa que encontrou o animal.");
                if (String.IsNullOrEmpty(animal.TelefoneContato) || String.IsNullOrWhiteSpace(animal.TelefoneContato))
                    throw new SystemException("Informe o número de telefone da pessoa que encontrou o animal.");
                if (String.IsNullOrEmpty(animal.Localizacao) || String.IsNullOrWhiteSpace(animal.Localizacao))
                    throw new SystemException("Informe a localização em que está o animal.");
            }
            catch(Exception ex)
            {
                TempData["MensagemErro"] = ex.Message;
                return View(animal);
            }

            if (ModelState.IsValid)
            {
                try
                {
                    animal.IdDono = _session.GetInt32("IdUsuarioLogado").Value;
                    animal.Status = Definicoes.SituacaoAnimal.Comunicado;

                    _context.Update(animal);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!AnimalExists(animal.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(animal);
        }

        // GET: Animals/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var animal = await _context.Animal
                .FirstOrDefaultAsync(m => m.Id == id);
            if (animal == null)
            {
                return NotFound();
            }

            return View(animal);
        }

        // POST: Animals/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var animal = await _context.Animal.FindAsync(id);
            _context.Animal.Remove(animal);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool AnimalExists(int id)
        {
            return _context.Animal.Any(e => e.Id == id);
        }
    }
}

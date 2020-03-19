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
    public class DonoController : Controller
    {
        private readonly AnimalFinderContext _context;

        public DonoController(AnimalFinderContext context)
        {
            _context = context;
        }

        public IActionResult Login()
        {            
            return View();
        }

        public IActionResult Logout()
        {
            HttpContext.Session.Clear();
            return RedirectToAction("Index", "Animal");
        }

        [HttpPost]
        public IActionResult Login(Dono model)
        {
            var usuario = _context.Dono.Where(w => w.Email == model.Email &&
            Criptografia.DecriptarDado(w.Senha) == model.Senha).FirstOrDefault();

            if (usuario != null)
            {
                HttpContext.Session.SetString("UsuarioLogado", usuario.Email);
                HttpContext.Session.SetInt32("IdUsuarioLogado", usuario.Id);
                return RedirectToAction("Create", "Animal");
            }
            else
                HttpContext.Session.Clear();

            TempData["MensagemErro"] = "Usuário ou senha inválidos";
            return View();
        }

        // GET: Donoes
        public async Task<IActionResult> Index()
        {
            return View(await _context.Dono.ToListAsync());
        }

        // GET: Donoes/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var dono = await _context.Dono
                .FirstOrDefaultAsync(m => m.Id == id);
            if (dono == null)
            {
                return NotFound();
            }

            return View(dono);
        }

        // GET: Donoes/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Donoes/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(Dono dono)
        {
            if (ModelState.IsValid)
            {
                dono.Senha = Criptografia.EncriptarDado(dono.Senha);

                _context.Add(dono);
                await _context.SaveChangesAsync();

                TempData["MensagemSucesso"] = "Cadastrado com sucesso! Faça o login!";

                return RedirectToAction("Login", "Dono");
            }
            return View(dono);
        }

        // GET: Donoes/Edit/5
        //public async Task<IActionResult> Edit(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var dono = await _context.Dono.FindAsync(id);
        //    if (dono == null)
        //    {
        //        return NotFound();
        //    }
        //    return View(dono);
        //}

        // POST: Donoes/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public async Task<IActionResult> Edit(int id, Dono dono)
        //{
        //    if (id != dono.Id)
        //    {
        //        return NotFound();
        //    }

        //    if (ModelState.IsValid)
        //    {
        //        try
        //        {
        //            _context.Update(dono);
        //            await _context.SaveChangesAsync();
        //        }
        //        catch (DbUpdateConcurrencyException)
        //        {
        //            if (!DonoExists(dono.Id))
        //            {
        //                return NotFound();
        //            }
        //            else
        //            {
        //                throw;
        //            }
        //        }
        //        return RedirectToAction(nameof(Index));
        //    }
        //    return View(dono);
        //}

        // GET: Donoes/Delete/5
        //public async Task<IActionResult> Delete(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var dono = await _context.Dono
        //        .FirstOrDefaultAsync(m => m.Id == id);
        //    if (dono == null)
        //    {
        //        return NotFound();
        //    }

        //    return View(dono);
        //}

        // POST: Donoes/Delete/5
        //[HttpPost, ActionName("Delete")]
        //[ValidateAntiForgeryToken]
        //public async Task<IActionResult> DeleteConfirmed(int id)
        //{
        //    var dono = await _context.Dono.FindAsync(id);
        //    _context.Dono.Remove(dono);
        //    await _context.SaveChangesAsync();
        //    return RedirectToAction(nameof(Index));
        //}

        private bool DonoExists(int id)
        {
            return _context.Dono.Any(e => e.Id == id);
        }
    }
}

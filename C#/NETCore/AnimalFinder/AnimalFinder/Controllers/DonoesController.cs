﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using AnimalFinder.Models;

namespace AnimalFinder.Controllers
{
    public class DonoesController : Controller
    {
        private readonly AnimalFinderContext _context;

        public DonoesController(AnimalFinderContext context)
        {
            _context = context;
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
                _context.Add(dono);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(dono);
        }

        // GET: Donoes/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var dono = await _context.Dono.FindAsync(id);
            if (dono == null)
            {
                return NotFound();
            }
            return View(dono);
        }

        // POST: Donoes/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, Dono dono)
        {
            if (id != dono.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(dono);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!DonoExists(dono.Id))
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
            return View(dono);
        }

        // GET: Donoes/Delete/5
        public async Task<IActionResult> Delete(int? id)
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

        // POST: Donoes/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var dono = await _context.Dono.FindAsync(id);
            _context.Dono.Remove(dono);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool DonoExists(int id)
        {
            return _context.Dono.Any(e => e.Id == id);
        }
    }
}

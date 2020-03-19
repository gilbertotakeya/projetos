using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using BizCommerce.Entrevista.Aplicacao.Models;

namespace BizCommerce.Entrevista.Aplicacao.Controllers
{
    public class ProfissionalEspecialidadesController : Controller
    {
        private conexaoBanco db = new conexaoBanco();

        // GET: ProfissionalEspecialidades
        public ActionResult Index()
        {
            var profissionalEspecialidade = db.profissionalEspecialidade.Include(p => p.especialidade).Include(p => p.profissional);
            return View(profissionalEspecialidade.ToList());
        }

        // GET: ProfissionalEspecialidades/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ProfissionalEspecialidade profissionalEspecialidade = db.profissionalEspecialidade.Find(id);
            if (profissionalEspecialidade == null)
            {
                return HttpNotFound();
            }
            return View(profissionalEspecialidade);
        }

        // GET: ProfissionalEspecialidades/Create
        public ActionResult Create()
        {
            ViewBag.ref_Id_Especialidade = new SelectList(db.especialidade, "Id_Especialidade", "Nom_Especialidade");
            ViewBag.ref_Id_profissional = new SelectList(db.profissional, "Id_profissional", "Nom_profissional");
            return View();
        }

        // POST: ProfissionalEspecialidades/Create
        // Para se proteger de mais ataques, ative as propriedades específicas a que você quer se conectar. Para 
        // obter mais detalhes, consulte https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id_Profissional_Especialidade,ref_Id_profissional,ref_Id_Especialidade")] ProfissionalEspecialidade profissionalEspecialidade)
        {
            if (ModelState.IsValid)
            {
                db.profissionalEspecialidade.Add(profissionalEspecialidade);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.ref_Id_Especialidade = new SelectList(db.especialidade, "Id_Especialidade", "Nom_Especialidade", profissionalEspecialidade.ref_Id_Especialidade);
            ViewBag.ref_Id_profissional = new SelectList(db.profissional, "Id_profissional", "Nom_profissional", profissionalEspecialidade.ref_Id_profissional);
            return View(profissionalEspecialidade);
        }

        // GET: ProfissionalEspecialidades/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ProfissionalEspecialidade profissionalEspecialidade = db.profissionalEspecialidade.Find(id);
            if (profissionalEspecialidade == null)
            {
                return HttpNotFound();
            }
            ViewBag.ref_Id_Especialidade = new SelectList(db.especialidade, "Id_Especialidade", "Nom_Especialidade", profissionalEspecialidade.ref_Id_Especialidade);
            ViewBag.ref_Id_profissional = new SelectList(db.profissional, "Id_profissional", "Nom_profissional", profissionalEspecialidade.ref_Id_profissional);
            return View(profissionalEspecialidade);
        }

        // POST: ProfissionalEspecialidades/Edit/5
        // Para se proteger de mais ataques, ative as propriedades específicas a que você quer se conectar. Para 
        // obter mais detalhes, consulte https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id_Profissional_Especialidade,ref_Id_profissional,ref_Id_Especialidade")] ProfissionalEspecialidade profissionalEspecialidade)
        {
            if (ModelState.IsValid)
            {
                db.Entry(profissionalEspecialidade).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.ref_Id_Especialidade = new SelectList(db.especialidade, "Id_Especialidade", "Nom_Especialidade", profissionalEspecialidade.ref_Id_Especialidade);
            ViewBag.ref_Id_profissional = new SelectList(db.profissional, "Id_profissional", "Nom_profissional", profissionalEspecialidade.ref_Id_profissional);
            return View(profissionalEspecialidade);
        }

        // GET: ProfissionalEspecialidades/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ProfissionalEspecialidade profissionalEspecialidade = db.profissionalEspecialidade.Find(id);
            if (profissionalEspecialidade == null)
            {
                return HttpNotFound();
            }
            return View(profissionalEspecialidade);
        }

        // POST: ProfissionalEspecialidades/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            ProfissionalEspecialidade profissionalEspecialidade = db.profissionalEspecialidade.Find(id);
            db.profissionalEspecialidade.Remove(profissionalEspecialidade);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}

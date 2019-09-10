using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using API.Models;
using API.Repositorio;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    [Route("api/[Controller]")]
    public class UsuariosController : Controller
    {
        private readonly IUsuarioRepositorio _usuarioRepositorio;
        public UsuariosController(IUsuarioRepositorio user)
        {
            _usuarioRepositorio = user;
        }

        [HttpGet]
        public IEnumerable<Usuario> GetAll()
        {
            return _usuarioRepositorio.GetAll();
        }

        [HttpGet("{id}", Name ="GetUsuario")]
        public IActionResult GetUsuarioByID(long id)
        {
            var usuario = _usuarioRepositorio.Find(id);
            if (usuario == null)
                return NotFound();

            return new ObjectResult(usuario);
        }

        [HttpPost]
        public IActionResult CriarUsuario([FromBody] Usuario usuario)
        {
            if (usuario == null)
                return BadRequest();

            _usuarioRepositorio.Add(usuario);
            return CreatedAtRoute("GetUsuario", new Usuario { IdUsuario = usuario.IdUsuario }, usuario);
        }

        [HttpPut("{id}")]
        public IActionResult AtualizarUsuario(long id, [FromBody] Usuario usuario)
        {
            if (usuario == null || usuario.IdUsuario != id)
                return BadRequest();

            var usuarioEncontrado = _usuarioRepositorio.Find(id);

            if (usuarioEncontrado == null)
                return NotFound();

            usuarioEncontrado.Nome = usuario.Nome;
            usuarioEncontrado.Email = usuario.Email;

            _usuarioRepositorio.Update(usuarioEncontrado);

            return new NoContentResult();
        }

        [HttpDelete("{id}")]
        public IActionResult ExcluirUsuario(long id)
        {
            var usuarioEncontrado = _usuarioRepositorio.Find(id);

            if (usuarioEncontrado == null)
                return NotFound();

            _usuarioRepositorio.Remove(id);

            return new NoContentResult();
        }
    }
}

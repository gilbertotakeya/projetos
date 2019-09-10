using API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API.Repositorio
{
    public class UsuarioRepositorio : IUsuarioRepositorio
    {
        private readonly UsuarioDBContext _contexto;
        public UsuarioRepositorio(UsuarioDBContext db)
        {
            _contexto = db;
        }
        public void Add(Usuario user)
        {
            _contexto.Usuarios.Add(user);
            _contexto.SaveChanges();
        }

        public Usuario Find(long id)
        {
            return _contexto.Usuarios.FirstOrDefault(w => w.IdUsuario == id);
        }

        public IEnumerable<Usuario> GetAll()
        {
            return _contexto.Usuarios.ToList();
        }

        public void Remove(long id)
        {
            var remove = _contexto.Usuarios.FirstOrDefault(w => w.IdUsuario == id);
            _contexto.Usuarios.Remove(remove);
            _contexto.SaveChanges();
        }

        public void Update(Usuario user)
        {
            _contexto.Usuarios.Update(user);
            _contexto.SaveChanges();
        }
    }
}

package org.biblioteca.Model;

import java.util.List;

public class DAOUsuario extends DAOGenerico<Usuario> {
    public DAOUsuario() {
        super(Usuario.class);
    }

    public List<Usuario> checkLogin(String dni, String password) {
        return em.createQuery("SELECT u FROM " + clase.getSimpleName() + " u WHERE u.dni = :dni AND u.password = :password")
                .setParameter("dni", dni)
                .setParameter("password", password)
                .getResultList();
    }

    public List<Usuario> getUsuarioByDni(String dni) {
        return em.createQuery("SELECT u FROM " + clase.getSimpleName() + " u WHERE u.dni = :dni")
                .setParameter("dni", dni).getResultList();
    }

}

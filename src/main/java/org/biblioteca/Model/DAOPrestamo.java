package org.biblioteca.Model;

import java.util.List;

public class DAOPrestamo extends DAOGenerico<Prestamo> {

    public DAOPrestamo() {
        super(Prestamo.class);
    }

    public List<Prestamo> getUserPrestamos(Usuario user){
        return em.createQuery("Select p FROM " + clase.getSimpleName() + " p WHERE p.usuario.id = " + user.getId()).getResultList();
    }

    public List<Prestamo> getPrestamoByUserANDEjemplar(Usuario user, Ejemplar ejemplar){
        return em.createQuery("SELECT p FROM " + clase.getSimpleName() +
                                " p WHERE p.usuario.id = :userId AND p.ejemplar.id = :ejemplarId", Prestamo.class) // Replace MyEntityClass with your actual class name
                .setParameter("userId", user.getId())
                .setParameter("ejemplarId", ejemplar.getId())
                .getResultList();
    }
}

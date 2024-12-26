package org.biblioteca.Model;

import java.util.List;

public class DAOEjemplares extends DAOGenerico<Ejemplar>{
    public DAOEjemplares() {
        super(Ejemplar.class);
    }

    public List<Ejemplar> getByAvailability() {
       return em.createQuery("SELECT e FROM " + clase.getSimpleName() + " e WHERE e.estado = 'Disponible'", Ejemplar.class).getResultList();
    }


}

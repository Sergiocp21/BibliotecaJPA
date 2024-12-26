package org.biblioteca.Model;

import java.util.List;

public class DAOLibro extends DAOGenerico<Libro> {
    public DAOLibro() {
        super(Libro.class);
    }

    public List<Libro> findByISBN(String isbn){
        return em.createQuery("SELECT l FROM" + clase.getSimpleName() + "l WHERE l.isbn = :isbn").setParameter("isbn", isbn).getResultList();
    }

}

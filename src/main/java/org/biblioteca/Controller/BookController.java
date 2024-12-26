package org.biblioteca.Controller;

import org.biblioteca.Model.DAOLibro;
import org.biblioteca.Model.Libro;

public class BookController {
    DAOLibro daoLibro = new DAOLibro();

    public boolean addBook(Libro libro) {
        return daoLibro.add(libro);
    }
}

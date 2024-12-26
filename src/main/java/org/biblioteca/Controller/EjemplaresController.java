package org.biblioteca.Controller;

import org.biblioteca.Model.DAOEjemplares;
import org.biblioteca.Model.DAOLibro;
import org.biblioteca.Model.Ejemplar;
import org.biblioteca.Model.Libro;

import java.util.List;

public class EjemplaresController {

    DAOEjemplares daoEjemplares = new DAOEjemplares();
    DAOLibro daoLibro = new DAOLibro();

    public boolean addEjemplar(String isbn, String estado){
        if(daoLibro.findByISBN(isbn).size() == 1){
            if(estado.equals("Disponible") || estado.equals("Prestado") || estado.equals("Dañado")){
                Libro libro = daoLibro.findByISBN(isbn).getFirst();
                Ejemplar ejemplar = new Ejemplar(libro, estado);
                return daoEjemplares.add(ejemplar);
            }
        }
        return false;
    }

    public List<Ejemplar> getEjemplaresDisponibles(){
        return daoEjemplares.getByAvailability();
    }

}

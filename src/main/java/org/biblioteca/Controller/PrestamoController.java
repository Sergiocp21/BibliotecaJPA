package org.biblioteca.Controller;

import org.biblioteca.Model.*;

import java.time.LocalDate;
import java.util.List;

public class PrestamoController {

    DAOPrestamo daoPrestamo = new DAOPrestamo();
    DAOUsuario daoUsuario = new DAOUsuario();
    DAOLibro daoLibro = new DAOLibro();
    DAOEjemplares daoEjemplares = new DAOEjemplares();

    public boolean addPrestamo(String dni, int idEjemplar){
        if(daoUsuario.getUsuarioByDni(dni).size() == 1 && daoEjemplares.getById(idEjemplar) != null){

            Ejemplar e = daoEjemplares.getById(idEjemplar);
            Usuario u = daoUsuario.getUsuarioByDni(dni).getFirst();
            if(!e.getEstado().equals("Prestado") && u.getPenalizacionHasta() == null && daoPrestamo.getUserPrestamos(u).size()<3) {
                Prestamo p = new Prestamo(LocalDate.now(), LocalDate.now().plusDays(15), e, u);
                return daoPrestamo.add(p);
            }
        }
        return false;
    }

    public List<Prestamo> getPrestamosByUser(Usuario u) {
        return daoPrestamo.getUserPrestamos(u);
    }

    public boolean devolverLibro(String dni, int idEjemplar){
        if(daoEjemplares.getById(idEjemplar) != null && !daoUsuario.getUsuarioByDni(dni).isEmpty()){
            Ejemplar e = daoEjemplares.getById(idEjemplar);
            Usuario u = daoUsuario.getUsuarioByDni(dni).getFirst();
            if(e.getEstado().equals("Prestado")){
                Prestamo prestamo = daoPrestamo.getPrestamoByUserANDEjemplar(u, e).getFirst();
                if(prestamo.getFechaDevolucion().isBefore(LocalDate.now())){
                    u.setPenalizacionHasta(u.getPenalizacionHasta().plusDays(15));
                }

                daoPrestamo.delete(prestamo);
                e.setEstado("Disponible");
                daoEjemplares.update(e);

                return true;

            }

        }
        return false;
    }

}

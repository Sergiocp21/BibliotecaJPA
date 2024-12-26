package org.biblioteca.Controller;

import org.biblioteca.Model.DAOUsuario;
import org.biblioteca.Model.Usuario;

public class UserController {

    DAOUsuario daoUsuario = new DAOUsuario();

    public Usuario login(String dni, String password) {

        if(daoUsuario.checkLogin(dni, password).size() == 1){
            return daoUsuario.checkLogin(dni, password).getFirst();
        }
        else{
            return null;
        }
    }

    public Usuario signIn(Usuario usuario) {
        if(!usuario.getTipo().equals("admin") || !usuario.getTipo().equals("normal")){
            return null;
        }
        if(daoUsuario.add(usuario)){
            return usuario;
        }
        else {
            return null;
        }
    }

}

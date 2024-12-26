package org.biblioteca.View;

import org.biblioteca.Controller.BookController;
import org.biblioteca.Controller.EjemplaresController;
import org.biblioteca.Controller.PrestamoController;
import org.biblioteca.Controller.UserController;
import org.biblioteca.Model.*;

import java.util.List;
import java.util.Scanner;

public class Console {

    Scanner sc = new Scanner(System.in);
    UserController userController = new UserController();
    BookController bookController = new BookController();
    EjemplaresController ejemplaresController = new EjemplaresController();
    PrestamoController prestamoController = new PrestamoController();


    public void signIn(){
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        System.out.println("Tipo de usuario: ");
        String tipoUsuario = sc.nextLine();

        Usuario usuario = new Usuario(dni, nombre, email, password, tipoUsuario);


        if(userController.signIn(usuario) != null){
            System.out.println("Usuario registrado correctamente");
        }
        else{
            System.out.println("Usuario no registrado, ha ocurrido un error");
        }
    }

    public Usuario logIn(){
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();

        return userController.login(dni, password);

    }

    public void menuAdmin(){
        int opc = 0;
        do{
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar ejemplares");
            System.out.println("3. Registrar prestamo");
            System.out.println("4. Ver ejemplares disponibles"); //Ver ejemplares disponibles
            System.out.println("5. Registrar devoluciones"); //Poner un ejemplar en disponible
            System.out.println("6. Cerrar sesion");
            String opcStr = sc.nextLine();
            try {
                opc = Integer.parseInt(opcStr);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                continue; // Volver al inicio del bucle
            }

            switch(opc){
                case 1: {
                    System.out.print("Registro de libros");
                    System.out.println("ISBN13: ");
                    String isbn = sc.nextLine();
                    System.out.println("Titulo: ");
                    String titulo = sc.nextLine();
                    System.out.println("Autor: ");
                    String autor = sc.nextLine();

                    Libro libro = new Libro(isbn, titulo, autor);
                    if (bookController.addBook(libro)) {
                        System.out.println("Libro registrado correctamente");
                    } else {
                        System.out.println("Libro no registrado, ha ocurrido un error");
                    }
                    break;
                }

                case 2: {
                    System.out.println("Registro de ejemplares");
                    System.out.println("ISBN13: ");
                    String isbn = sc.nextLine();
                    System.out.println("Estado: ");
                    String estado = sc.nextLine();

                    if(ejemplaresController.addEjemplar(isbn, estado)){
                        System.out.println("Ejemplar registrado correctamente");
                    }
                    else{
                        System.out.println("Ejemplar no registrado, ha ocurrido un error");
                    }
                    break;
                }

                case 3: {
                    System.out.println("Registro de prestamos");
                    System.out.println("DNI del usuario: ");
                    String dni = sc.nextLine();
                    System.out.println("id del ejemplar: ");
                    int idEjemplar = sc.nextInt();

                    if(prestamoController.addPrestamo(dni, idEjemplar)){
                        System.out.println("Prestamo registrado correctamente");
                    }
                    else{
                        System.out.println("Prestamo no registrado, ha ocurrido un error");
                    }
                    break;
                }

                case 4: {
                    System.out.println("Ejemplares disponibles:");
                    List<Ejemplar> disponibles = ejemplaresController.getEjemplaresDisponibles();

                    for(Ejemplar e : disponibles){
                        System.out.println(e);
                    }

                    System.out.println("En total hay " + disponibles.size() + " ejemplares disponibles");

                    System.out.println("Presiona enter para salir");
                    sc.nextLine();

                    break;
                }

                case 5: {
                    System.out.println("Registro de devoluciones"); //Borrar de Prestamos y cambiar en Ejemplar a disponible
                    System.out.println("DNI del usuario: ");
                    String dni = sc.nextLine();
                    System.out.println("id del ejemplar: ");
                    int idEjemplar = sc.nextInt();

                    if(prestamoController.devolverLibro(dni, idEjemplar)){
                        System.out.println("Libro devuelto correctamente");
                    }
                    else{
                        System.out.println("Ha ocurrido un error al devolver el libro");
                    }

                    break;
                }


            }
        }while(opc != 6);
    }

    public void verPrestamos(Usuario usuario){
        List<Prestamo> prestamos = prestamoController.getPrestamosByUser(usuario);

        for(Prestamo p : prestamos){
            System.out.println(p);
        }

        System.out.println("Tienes " + prestamos.size() + " libros prestados de 3");

        System.out.println("Presiona enter para salir");
        sc.nextLine();

    }

    public void startConsole(){
        int option = 0;
        do{
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            String optionStr = sc.nextLine();
            try {
                option = Integer.parseInt(optionStr);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                continue;
            }

            if(option == 1){
                Usuario usuario = logIn();

                if(usuario != null){
                    String tipo = usuario.getTipo();

                    if(tipo.equals("normal")){
                        verPrestamos(usuario);
                    }
                    else{
                        menuAdmin();
                    }

                }
                else{
                    System.out.println("Ha ocurrido un error al iniciar sesion");
                }
            }

            else if(option == 2){
               signIn();
            }

        }while(option != 3);
    }

}

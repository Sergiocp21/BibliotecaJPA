package org.biblioteca.Model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class DAOGenerico2<T, ID> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidad-empresa");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Class<T> clase;
    Class<ID> claseID;

    public DAOGenerico2(Class<T> clase, Class<ID> claseID){
        this.clase=clase;
        this.claseID=claseID;
    }

    //INSERT
    public boolean add(T objeto){
        tx.begin();
        em.persist(objeto);
        tx.commit();
        return false;
    }

    //SELECT WHERE ID
    public T getById(ID id){
        return em.find(clase, id);
    }

    //SELECT *
    public List<T> getAll(){
        return em.createQuery("SELECT c from "+ clase.getName()+" c").getResultList();
    }

    //UPDATE
    public T update(T objeto){
        tx.begin();
        objeto = em.merge(objeto);
        tx.commit();
        return objeto;
    }
    //DELETE WHERE objeto.id
    public boolean deleteUsuario(T objeto){
        tx.begin();
        em.remove(objeto);
        tx.commit();
        return true;
    }

}

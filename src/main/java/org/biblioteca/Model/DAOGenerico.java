package org.biblioteca.Model;

import jakarta.persistence.*;

import java.util.List;

public class DAOGenerico<T> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidad-empresa");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Class<T> clase;

    public DAOGenerico(Class<T> clase){
        this.clase=clase;
    }

    //INSERT
    public boolean add(T objeto){
        tx.begin();
        em.persist(objeto);
        tx.commit();
        return true;
    }

    //SELECT WHERE ID
    public T getById(Integer id){
        return em.find(clase, id);
    }

    //SELECT *
    public List<T> getAll(){
        return em.createQuery("SELECT c from "+ clase.getSimpleName()+" c").getResultList();
    }

    //UPDATE
    public T update(T objeto){
        tx.begin();
        objeto = em.merge(objeto);
        tx.commit();
        return objeto;
    }
    //DELETE WHERE objeto.id
    public boolean delete(T objeto){
        tx.begin();
        em.remove(objeto);
        tx.commit();
        return true;
    }

    @Override
    public String toString() {
        return "DAOGenerico{" +
                "clase simple name=" + clase.getSimpleName() + "\n"+
                "clase canonical name=" + clase.getCanonicalName() + "\n"+
                "clase name=" + clase.getName() + "\n"+
                '}';
    }
}

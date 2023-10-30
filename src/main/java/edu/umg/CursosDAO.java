package edu.umg;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CursosDAO {
    private Cache HibernateUtil;

    // Método para guardar un curso en la base de datos
    public void save(CursosClass curso) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(curso); // Guarda el curso en la base de datos
            tx.commit(); // Confirma la transacción
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // En caso de error, realiza un rollback para deshacer cambios
            }
            e.printStackTrace(); // Manejo de errores
        } finally {
            session.close(); // Cierra la sesión de Hibernate
        }
    }

    // Método para obtener un curso por su ID
    public CursosClass getCursoById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CursosClass curso = session.get(CursosClass.class, id); // Obtiene un curso por su ID
        session.close(); // Cierra la sesión
        return curso;
    }

    // Método para obtener todos los cursos
    public List<CursosClass> getAllCursos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CursosClass> cursos = session.createQuery("FROM CursosClass", CursosClass.class).list(); // Consulta todos los cursos
        session.close();
        return cursos;
    }

    // Método para actualizar un curso en la base de datos
    public void update(CursosClass curso) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(curso); // Actualiza el curso en la base de datos
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Manejo de errores
        } finally {
            session.close();
        }
    }

    // Método para eliminar un curso de la base de datos
    public void delete(CursosClass curso) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(curso); // Elimina el curso de la base de datos
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Manejo de errores
        } finally {
            session.close();
        }
    }
}

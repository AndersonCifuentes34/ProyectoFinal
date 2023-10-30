package edu.umg;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class InscripcionesDAO {
    private Cache HibernateUtil;

    // Método para guardar una inscripción en la base de datos
    public void save(InscripcionesClass inscripcion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(inscripcion); // Guarda la inscripción en la base de datos
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

    // Método para obtener una inscripción por su ID
    public InscripcionesClass getInscripcionById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        InscripcionesClass inscripcion = session.get(InscripcionesClass.class, id); // Obtiene una inscripción por su ID
        session.close(); // Cierra la sesión
        return inscripcion;
    }

    // Método para obtener todas las inscripciones
    public List<InscripcionesClass> getAllInscripciones() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<InscripcionesClass> inscripciones = session.createQuery("FROM InscripcionesClass", InscripcionesClass.class).list(); // Consulta todas las inscripciones
        session.close();
        return inscripciones;
    }

    // Método para actualizar una inscripción en la base de datos
    public void update(InscripcionesClass inscripcion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(inscripcion); // Actualiza la inscripción en la base de datos
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para eliminar una inscripción de la base de datos
    public void delete(InscripcionesClass inscripcion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(inscripcion); // Elimina la inscripción de la base de datos
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
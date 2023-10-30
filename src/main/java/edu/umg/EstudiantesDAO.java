package edu.umg;


import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EstudiantesDAO<Estudiante> {
    private Cache HibernateUtil;

    // Método para guardar un estudiante en la base de datos
    public void save(Estudiante estudiante) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(estudiante); // Guarda el estudiante en la base de datos
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

    // Método para obtener un estudiante por su ID
    public EstudiantesClass getEstudianteById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        EstudiantesClass estudiante = session.get(EstudiantesClass.class, id); // Obtiene un estudiante por su ID
        session.close(); // Cierra la sesión
        return estudiante;
    }

    // Método para obtener todos los estudiantes
    public List<EstudiantesClass> getAllEstudiantes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EstudiantesClass> estudiantes = session.createQuery("FROM EstudiantesClass", EstudiantesClass.class).list(); // Consulta todos los estudiantes
        session.close();
        return estudiantes;
    }

    // Método para actualizar un estudiante en la base de datos
    public void update(EstudiantesClass estudiante) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(estudiante); // Actualiza el estudiante en la base de datos
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

    // Método para eliminar un estudiante de la base de datos
    public void delete(EstudiantesClass estudiante) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(estudiante); // Elimina el estudiante de la base de datos
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

package sv.edu.udb.www.models;

import sv.edu.udb.www.utils.JpaUtil;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.edu.udb.www.entities.ProductoEntity; // Cambio aquí para usar ProductoEntity en lugar de EstudiantesEntity

public class ProductoModel { // Cambio de nombre de la clase a ProductoModel
    public List<ProductoEntity> listarProductos() { // Cambio de método listarEstudiante a listarProductos
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("ProductoEntity.findAll"); // Cambio de nombre de la consulta nombrada
            List<ProductoEntity> lista = consulta.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public ProductoEntity obtenerProducto(int productoId) { // Cambio de nombre del método y parámetro
        EntityManager em = JpaUtil.getEntityManager();
        try {
            ProductoEntity producto = em.find(ProductoEntity.class, productoId); // Cambio de clase a ProductoEntity
            em.close();
            return producto;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarProducto(ProductoEntity producto) { // Cambio de nombre del método y parámetro
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.persist(producto);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarProducto(ProductoEntity producto) { // Cambio de nombre del método y parámetro
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(producto);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarProducto(int productoId) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            ProductoEntity producto = em.find(ProductoEntity.class, productoId); // Cambio de clase a ProductoEntity
            if (producto != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(producto);
                tran.commit();
                filasBorradas = 1;
            }
            em.close();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
}
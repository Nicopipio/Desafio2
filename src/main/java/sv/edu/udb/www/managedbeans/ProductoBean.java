package sv.edu.udb.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.edu.udb.www.entities.ProductoEntity; // Cambio aquí para usar ProductoEntity en lugar de EstudiantesEntity
import sv.edu.udb.www.models.ProductoModel; // Cambio aquí para usar ProductoModel en lugar de EstudiantesModel
import sv.edu.udb.www.utils.JsfUtil;
import java.util.List;

@ManagedBean
@RequestScoped
public class ProductoBean { // Cambio de nombre de la clase a ProductoBean
    ProductoModel modelo = new ProductoModel(); // Cambio de nombre del modelo a ProductoModel
    private ProductoEntity producto; // Cambio de nombre de la entidad a ProductoEntity
    private List<ProductoEntity> listaProductos; // Cambio de nombre de la lista a listaProductos

    public ProductoBean() {
        producto = new ProductoEntity(); // Cambio de entidad a ProductoEntity
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public List<ProductoEntity> getListaProductos() {
        return modelo.listarProductos(); // Cambio de método para obtener la lista de productos
    }

    public String guardarProducto() { // Cambio de nombre del método
        if (modelo.insertarProducto(producto) != 1) { // Cambio de método para insertar producto
            JsfUtil.setErrorMessage(null, "Ya se registró un producto con este ID");
            return null; // Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Producto registrado exitosamente");
            // Forzando la redirección en el cliente
            return "registroProductos?faces-redirect=true"; // Cambio de página de redirección
        }
    }

}

package com.mx.java.dao;

import com.mx.java.domain.Categoria;
import com.mx.java.domain.Producto;
import org.springframework.data.jpa.repository.*;

/**
 *
 * @author tonny
 */
public interface ProductoDao extends JpaRepository<Producto, Long> {
   Producto findByNombre(String nombre); 
 /*
    @Modifying
    @Query(value = "UPDATE  producto set nombre=?,descripcion=?,contenido_neto=?,fecha_caducidad=?,precio=?,"
            + "existencia=?,id_provedor=?,id_categoria=? where id_producto=?")
    int updateProductName(Long productId, String nombre, String descripcion, String contenidoNeto,
            String FechaCaducidad, Double precio, Integer existencia, Provedor provedor, Categoria categoria );
*/

 
    
}

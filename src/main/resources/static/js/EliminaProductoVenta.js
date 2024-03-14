var Nombreproducto;
var productoID;

function EliminarProductoVenta(e) {
    Nombreproducto = e.getAttribute("data-nombre");
    productoID = e.getAttribute("data-idProducto");
    Swal.fire({
        title: '¿Desea Eliminar este producto?',
        text: Nombreproducto,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonText: 'Cancelar',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Eliminar!'
    }).then((result) => {
        if (result.isConfirmed) {
            
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'El Prodcuto ' + Nombreproducto + ' ha sido Eliminado',
                showConfirmButton: false,
                timer: 1600
            });
            setTimeout(() => {
            EliminarProducto();        
            },1600);        
        }

    });

}
console.log(productoID);

async function EliminarProducto() {

    const producto = {
        idProducto: productoID,
        nombre: Nombreproducto

    };

    console.log(producto);

    const request = await fetch('/api/tablaVenta/eliminar', {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(producto)
    });
    
   window.location.href = "/api/venta";
}



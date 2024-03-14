var idProducto;
var nombreProducto;
var existencia;

function abrirModal(enlace) {
    idProducto = enlace.getAttribute('data-idProducto');
    nombreProducto = enlace.getAttribute('data-nombre');
    existencia = enlace.getAttribute('data-existencia');
    var modal = document.getElementById('ActualizarStock');
    var nombreProductoElement = modal.querySelector('#nombreProducto');
    nombreProductoElement.textContent = 'PRODUCTO: ' + nombreProducto + '  -   ' + ' Cantidad Actual: ' + existencia;
    modal.style.display = 'block';

}

async  function Actualizar() {

    let existenciaNueva = document.getElementById("existenciaNueva");
    let nuevaExistencia = existenciaNueva.value;
    console.log(nuevaExistencia);

    const producto = {
        idProducto: idProducto,
        nombre: nombreProducto,
        existencia: existenciaNueva

    };
    console.log(producto);
    producto.existencia = nuevaExistencia;

    const  request = await fetch('/Actualizar', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(producto)
    });
    $('#ActualizarStock').modal('hide');
    window.location.href = "/api/producto";
}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}


//FUNCIION ELIMINAR PRODUCTO
function abrirModalEliminar(e) {
    idProducto = e.getAttribute('data-idProducto');
    nombreProducto = e.getAttribute('data-nombre');
    Swal.fire({
        title: '¿Desea Eliminar este producto?',
        text: idProducto+' '+nombreProducto,
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
                title: 'El Prodcuto ' + nombreProducto + ' ha sido Eliminado',
                showConfirmButton: false,
                timer: 1700
            });
            setTimeout(() => {
                eliminarProducto();
            }, 1500);
        }

    });

}

async  function eliminarProducto() {
    const producto = {
        idProducto: idProducto,
        nombre: nombreProducto

    };
    const  request = await fetch('/api/producto/eliminar', {
        method: 'DELETE',
        
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(producto)
    });    
    window.location.href = "/api/producto";
}




/* global input, listaProductos, lista, */
window.addEventListener("load", function () {

});

document.getElementById("limpiar").addEventListener("click", function () {

    document.getElementById("tablaventa").innerHTML = "";
    fech("/limpiarLista");

});

// Cancelar Venta


async function CancelarVenta() {

    const  request = await fetch('/limpiarLista', {
        method: 'GET',
        headers: getHeaders()
    });
    Swal.fire(
            '!Cancelada!',
            'La venta Hs sido Cancelada.',
            'error'
            );
   setTimeout(() => {
        window.location.href = "/api/venta";

    },1500);

}
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}

//Deshabilitar Boton


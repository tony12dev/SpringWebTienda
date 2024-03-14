/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

let Producto;
let IDproducto;
var Existencia;

async function  ActualizarVenta(e) {
    Producto = e.getAttribute("data-Producto");
    productoID = e.getAttribute("data-ProductoId");
    const ipAPI = "//api.ipify.org?format=json";
    const response = await fetch(ipAPI);
    const data = await response.json();
    const inputValue = data.ip;
    let{value: ExistenciaNueva} = await Swal.fire({
        title: "Modificar Producto venta",
        input: "number",
        inputLabel: Producto,
        inputValue,
        showCancelButton: true,
        inputValidator: (value) => {
            if (!value) {
                return "Ingrese un numero valido";
            }
        }
    });

    Existencia = ExistenciaNueva;

    if (ExistenciaNueva <= 0) {

        Swal.fire({
            title: "Solicitud invalida",
            text: "NO se aceptan numeros negativos ni Nulos",
            icon: "info"
        });
    }  else if (ExistenciaNueva) {
        Swal.fire(`Producto modificado ${Producto}`);
        time: 1700;
        setTimeout(() => {
            actualizaproducto();
        }, 1500);
    } 
}



async function actualizaproducto() {

    console.log("La existencia es: " + Existencia);

    const producto = {
        idProducto: productoID,
        nombre: Producto,
        existencia: Existencia

    };

    const request = await fetch('/api/tablaVenta/actualizar', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(producto)
    });

    window.location.href = "/api/venta";
}


/* global total */
window.addEventListener("load", function () {
    suma();

});

function suma() {
    let total = 0;
    const table = document.getElementById("venta");
    for (let i = 1; i < table.rows.length; i++) {
        let rowssuma = table.rows[i].cells[5].innerHTML;
        total = total + Number(rowssuma);
    }

    let USDollar = new Intl.NumberFormat('en-ES', {
        style: 'currency',
        currency: 'USD'
    });
//let ivaVenta = total*0.015;

//let ventaTotal = ivaVenta + total;


    let Subtotal = `<h2 class="text-center">Total: ${USDollar.format(total)}</h2>`;
    // let iva = `<h4>iva: ${USDollar.format(ivaVenta)}</h4>`;
    // let TotalVenta = `<h4>Total: ${USDollar.format(ventaTotal)}</h4>`;


    document.querySelector('#total #subtotal').outerHTML = Subtotal;
    //document.querySelector('#total #iva').outerHTML = iva;
    // document.querySelector('#total #totalventa').outerHTML = TotalVenta;

}

//const element = document.getElementById("limpiar");
//element.addEventListener("click", myFunction);
//
//function myFunction() {
//    fetch("/limpiarLista");
//}



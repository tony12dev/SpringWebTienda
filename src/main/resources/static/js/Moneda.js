window.addEventListener("load", function () {
    moneda();
});

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}

async function moneda() {
    
    const  request = await fetch('/listar', {
        method: 'GET'
    });
    const productos = await request.json();
    console.log(productos);


    let USDollar = new Intl.NumberFormat('en-ES', {
        style: 'currency',
        currency: 'USD'
    });
   
        
//    for (let producto of productos) {
//
//        let formatoMoneda = `
//                                <div>
//                                    <p class="m-1 border border-2">Precio: ${USDollar.format(producto.precio)}</p>
//                                </div>             
//                            `;
//           
//       document.querySelector('#moneda #precio').outerHTML = formatoMoneda;
//    }

}



//document.getElementById("nombre").addEventListener("keyup", e => {
//    console.log(e.target.matches("#nombre"));
//        document.querySelectorAll(".lis-productos").forEach(p => {
//           console.log(p.textContent.toLowerCase().includes(e.target.value));
//           
//        });
//    
//});

let inputSer = document.getElementById("nombre");
let listas = document.getElementById("lista-Productos");


let inputNombre = document.querySelector("nombre");

document.getElementById("nombre").addEventListener("keyup", buscador);
//inputNombre.addEventListener("input", pasaValor);

function buscador() {
    let a = "";
    let filter = inputSer.value.toUpperCase();
    let li = listas.getElementsByTagName("li");

    for (i = 0; i < li.length; i++) {

        a = li[i].getElementsByTagName("a")[0];
        let textValue = a.textContent || a.innerText;
        if (textValue.toUpperCase().indexOf(filter) > -1) {

            li[i].style.display = "";
            listas.style.display = "block";
                      
            if (inputSer.value === "") {
                listas.style.display = "none";
            }
            
        } else {
            li[i].style.display = "none";
        }
    }
}

function pasaValor(){
    
}
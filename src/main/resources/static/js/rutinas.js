

function readURL(input) {
    if (input.files && input.files[0]){
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#blah')
                    .attr('src' , e.target.result)
                    .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}


// la siguiente funcion se utilzia para activer la cantidad de eleentos seleccionados 
// en el carrito de compras utilzando un llamado "ajax"

function addCart(formulario) {
    var valor = formulario.elements[0].value;
    var url = "/carrito/agregar";
    url = url + "/" + valor;
    $("#resultsBlock").load(url);
}
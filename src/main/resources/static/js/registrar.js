$(document).ready(function () {

    
});
async function registrarUsuarios() {
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.telefono = document.getElementById('txtTelefono').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    let repetirpassword = document.getElementById('txtPassword').value;

    if (repetirpassword != datos.password) {

        alert("la contraseña es incorrecta")
        return;

    
    }

    const request = await fetch('api/usuario', {
        method: 'POST',
        headers: {
            // 'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        
        body: JSON.stringify(datos)
    });
    alert("usuario se a registrado con exito")

    window.location.href ='login.html';






}
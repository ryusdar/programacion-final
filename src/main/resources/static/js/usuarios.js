// Call the dataTables jQuery plugin
$(document).ready(function () {

  CargarUsuarios();
  $('#usuarios').DataTable();

  async function CargarUsuarios() {
    const request = await fetch('api/usuario', {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'content-Type': 'application/json'
      },
    });
    const usuarios = await request.json();

    let listadoHtml = '';

    console.log("Respuesta del servidor:", usuarios);
    for (let usuario of usuarios) {

      let botonEliminar = '<a href="#" onclick=" eliminarUsuario( ' + usuario.id + ')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';

      let usuariosHtml = '<tr><td> ' + usuario.id + '</td><td> ' + usuario.nombre + '</td><td> '
        + usuario.apellido + '</td><td> ' + usuario.email + '</td><td> '
        + usuario.telefono + '</td><td> ' + botonEliminar + '</td></tr>';

      listadoHtml += usuariosHtml;

    }
    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
  }



}
)
async function eliminarUsuario(id) {
  console.log(id)
  if (!confirm('Desea eliminar usuario?')) {
    return;
  }
  const request = await fetch('api/usuario/' + id, {
    method: 'DELETE',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });

  location.reload();
}
package manuelbelgrano.jar.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import manuelbelgrano.jar.dao.UsuarioDao;
import manuelbelgrano.jar.model.Usuarios;

@RestController
public class usuariocontroler {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping (value = "/mensaje")
    public String mensaje() {
        return "hola";
    }

    @RequestMapping(value = "api/usuario")
    public List<Usuarios> getUsuario(){
        List<Usuarios> user = usuarioDao.getUsuarios();
        return  user;
    }


    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable long id ) {
        usuarioDao.eliminar(id);

    }
     @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuarios usuarios ) {
       
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash= argon2.hash(1, 1024, 1, usuarios.getPassword());
        usuarios.setPassword(hash);

        usuarios.getPassword();

        usuarioDao.registrar(usuarios);

    }
    

}

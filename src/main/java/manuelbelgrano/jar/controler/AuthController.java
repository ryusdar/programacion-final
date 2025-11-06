package manuelbelgrano.jar.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import manuelbelgrano.jar.dao.UsuarioDao;
import manuelbelgrano.jar.model.Usuarios;

@RestController
public class AuthController {
   
    @Autowired
    private UsuarioDao usuarioDao;
  

 @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuarios usuarios ) {
        System.out.println(usuarioDao.verificarCredenciales(usuarios));
    
        if(usuarioDao.verificarCredenciales(usuarios)){
            return "ok";
        }else{ return "fail";}
        
        
    }
}
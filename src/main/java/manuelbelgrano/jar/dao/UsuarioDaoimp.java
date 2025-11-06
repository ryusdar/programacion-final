package manuelbelgrano.jar.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import manuelbelgrano.jar.model.Usuarios;

@Repository
@Transactional
public class UsuarioDaoimp implements UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuarios> getUsuarios() {

        String query  = "From Usuarios";
        List<Usuarios> resultados = entityManager.createQuery(query).getResultList();

        return resultados;

    }
    @Override 
    public void eliminar(long id){

        Usuarios usuario =entityManager.find(Usuarios.class, id);
       
        entityManager.remove(usuario);
        

    }
    @Override
    public void registrar(Usuarios usuarios){
         
        entityManager.merge(usuarios);

    }
    @Override
    public boolean verificarCredenciales(Usuarios usuarios) {
        

        System.out.println("Email recibido en DAO: " + usuarios.getEmail());
      

        String query ="From Usuarios WHERE email= :email";
            
          List<Usuarios> lista= entityManager.createQuery(query).setParameter("email",usuarios.getEmail()).getResultList();
        if (lista.isEmpty()) {
            return false;
            
        }

            
       String passHasheada = lista.get(0).getPassword();
       Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
       boolean passEsIgual = argon2.verify(passHasheada, usuarios.getPassword());



       return passEsIgual;
         
    }


}

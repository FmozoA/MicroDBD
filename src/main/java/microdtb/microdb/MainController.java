package microdtb.microdb;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path = "/user")
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/add2", consumes = "application/json", produces = "application/json")
    public @ResponseBody User addUser2(@RequestBody User user) {
        System.out.println("Consultado usuario");
        return userRepository.save(user);
    }

    @GetMapping(value = "/find", consumes = "application/json", produces = "application/json")
    public @ResponseBody Optional<User> getUser(@RequestBody User user) {
        logger.error("Consultando usuario, id:" + user.getId());
        return userRepository.findById(user.getId());
    }

    @PostMapping(path = "/add")
    public @ResponseBody Respuesta addNewUser(@RequestParam String name, @RequestParam(required = false) String email,
            @RequestParam String apellido, @RequestParam Integer cedula) {

        Respuesta respuesta = new Respuesta();
        if (email == null) {
            respuesta.setResultado("Error al agregar el usuario");
            respuesta.setError("Email vacio");
            return respuesta;
        }

        System.out.println("Consultado usuario");

        User userNuevo = new User();
        userNuevo.setName(name);
        try {
            userNuevo.setEmail(email);
        } catch (Exception e1) {
            respuesta.setResultado("Error al agregar el usuario");
            respuesta.setError(e1.getMessage());
            return respuesta;
        }
        userNuevo.setApellido(apellido);
        userNuevo.setCedula(cedula);

        try {
            userRepository.save(userNuevo);
            respuesta.setResultado("Usuario salvado");
        } catch (Exception e) {
            respuesta.setResultado("Error al agregar el usuario");
            respuesta.setError(e.getMessage());
        }

        return respuesta;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

  
    @GetMapping(value = "/consultaU", consumes = "application/json", produces = "application/json")
    public @ResponseBody Respuesta consultaUserCal(@RequestBody Input input) {
   
    // buscar persona
    Optional <User>  users = userRepository.findById(Integer.parseInt(input.getId_user()));
      
    
    Respuesta res =new Respuesta();

    if (!users.isEmpty()){
        User user = users.get();
        res.setNombrePersona(user.getName());
    }
    // invocar el micro de calculadora
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8090/" + input.getOperacion() + "/" + input.getUno() + "/" +input.getDos();
    ResponseEntity <RespuestaCal> resCalculadora = restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<>(){});
    resCalculadora.getBody();
    
   // armar la respuesta
    res.setNumero(Double.valueOf(resCalculadora.getBody().getResultado()));

    return res;
    }


}


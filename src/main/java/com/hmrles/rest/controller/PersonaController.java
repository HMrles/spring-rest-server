package com.hmrles.rest.controller;

import com.hmrles.rest.modelo.Persona;
import com.hmrles.rest.modelo.PersonaLista;
import com.hmrles.rest.services.PersonaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HMrles on 28/01/17.
 */
@RestController
public class PersonaController {
    private static Logger logger = Logger.getLogger("PersonaController");

    @Autowired
    private PersonaService personaService;


    /**
     * Recuperamos lista con todas las personas
     **/
    @RequestMapping(value = "/persons", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    private
    @ResponseBody
    PersonaLista getPersonas() {
        PersonaLista result = new PersonaLista();

        result.setDatos(personaService.getAll());

        return result;

    }


    /**
     * Recuperamos una persona en especifica
     **/
    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    private
    @ResponseBody
    Persona getPersona(@PathVariable("id") Long id) {
        return personaService.get(id);
    }


    /**
     * agregamos una persona
     **/
    @RequestMapping(value = "/person", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
    public
    @ResponseBody
    Persona addPerson(@RequestBody Persona person) {
        return personaService.agregar(person);
    }


    /**
     * actualizamos una persona en especifica
     **/
    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT, headers = "Accept=application/xml, application/json")
    public
    @ResponseBody
    String updatePerson(@PathVariable("id") Long id, @RequestBody Persona person) {
        person.setId(id);
        return personaService.editarPersona(person).toString();
    }


    /**
     * eliminamos una persona en especifica
     **/
    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE, headers = "Accept=application/xml, application/json")
    public
    @ResponseBody
    String deleteEmployee(@PathVariable("id") Long id) {
        return personaService.eliminar(id).toString();
    }


    /**
     * recuperamos la foto de una persona en especifica
     **/
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, headers = "Accept=image/jpeg, image/jpg, image/png, image/gif")
    public
    @ResponseBody
    byte[] getPhoto(@PathVariable("id") Long id) {

        // Call service here
        try {
            // Retrieve image from the classpath
            InputStream is = this.getClass().getResourceAsStream("/duke.jpg");

            String testPath = this.getClass().getClassLoader().getResource("").getPath();

            System.out.println("###PATH: " + testPath);

            // Prepare buffered image
            BufferedImage img = ImageIO.read(is);

            // Create a byte array output stream
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            // Write to output stream
            ImageIO.write(img, "jpg", bao);

            logger.debug("Retrieving photo as byte array image");
            return bao.toByteArray();

        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

}

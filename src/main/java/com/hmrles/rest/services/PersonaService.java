package com.hmrles.rest.services;

import com.hmrles.rest.modelo.Persona;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HMrles on 28/01/17.
 */
@Component
public class PersonaService {

    /**/
    protected static Logger logger = Logger.getLogger("PersonaService");


    private List<Persona> personas = new ArrayList<>();


    public PersonaService() {
        logger.debug("Inicializando la informacion de las personas...");

        Persona persona1 = new Persona();
        persona1.setId(0L);
        persona1.setNombre("Hugo");
        persona1.setApellido("Mrles");
        persona1.setSalario(1000.0);

        Persona persona2 = new Persona();
        persona2.setId(1L);
        persona2.setNombre("Erick");
        persona2.setApellido("Sanchez");
        persona2.setSalario(3000.0);

        Persona persona3 = new Persona();
        persona3.setId(2L);
        persona3.setNombre("Eduardo");
        persona3.setApellido("Galvan");
        persona3.setSalario(5000.0);

        Persona persona4 = new Persona();
        persona4.setId(3L);
        persona4.setNombre("Minerva");
        persona4.setApellido("Monserrat");
        persona4.setSalario(7000.0);

        personas.add(persona1);
        personas.add(persona2);
        personas.add(persona3);
        personas.add(persona4);
    }


    public List<Persona> getAll() {
        logger.debug("Recuperarando informacion de todas las personas");
        return personas;
    }

    public Persona get(Long id) {
        logger.debug("Recuperando informacion de la persona con Id " + id);

        for (Persona person : personas) {
            if (person.getId().longValue() == id.longValue()) {
                logger.debug("Valor encontrado");
                return person;
            }
        }

        logger.debug("No existe la persona con id: "+id);
        return null;
    }


    public Boolean editarPersona(Persona persona) {
        logger.debug("Editando la persona con id " + persona.getId());
        try {
            for (Persona p : personas) {
                if (p.getId().longValue() == persona.getId().longValue()) {
                    personas.remove(p);
                    personas.add(persona);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Boolean eliminar(Long id) {
        try {
            for (Persona p : personas) {
                if (id.longValue() == p.getId().longValue()) {
                    personas.remove(p);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Persona agregar(Persona persona) {
        try {
            Long newId = 0L;
            Boolean hasFoundSuitabled = false;
            while (hasFoundSuitabled == false) {
                for (int i = 0; i < personas.size(); i++) {
                    if (personas.get(i).getId().longValue() == newId) {
                        newId++;
                        break;
                    }

                    if (i == personas.size() - 1) {
                        logger.debug("Id asignado: " + newId);
                        hasFoundSuitabled = true;
                    }
                }
            }

            persona.setId(newId);
            personas.add(persona);
            logger.debug("Person agregada");

            return persona;
        } catch (Exception e) {
            return null;
        }
    }
}

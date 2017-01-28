package com.hmrles.rest.modelo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by HMrles on 28/01/17.
 */
@XmlRootElement(name = "personas")
public class PersonaLista {
    private List<Persona> datos;

    public List<Persona> getDatos() {
        return datos;
    }

    public void setDatos(List<Persona> datos) {
        this.datos = datos;
    }
}

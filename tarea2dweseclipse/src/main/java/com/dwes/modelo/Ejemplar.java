package com.dwes.modelo;

import java.util.Set;

public class Ejemplar {
    private Long id;
    private String nombre;
    
    // Relación con Planta (1 a N)
    private Planta planta;
    
    // Relación con Persona (N a M - seguimiento)
    private Set<Persona> personas;
    
    // Relación con Mensaje (1 a M)
    private Set<Mensaje> mensajes;

    public Ejemplar() {
		super();
	}

	public Ejemplar(Long id, String nombre, Planta planta, Set<Persona> personas, Set<Mensaje> mensajes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.planta = planta;
		this.personas = personas;
		this.mensajes = mensajes;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }

    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

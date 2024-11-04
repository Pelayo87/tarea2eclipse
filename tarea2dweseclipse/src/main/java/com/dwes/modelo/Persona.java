package com.dwes.modelo;

import java.util.Objects;
import java.util.Set;

public class Persona {
    private long id;
    private String nombre;
    private String email;
    
    // Relación 1 a 1 con Credenciales
    private Credenciales credenciales;
    
    // Relación N a M con Ejemplar (seguimiento)
    private Set<Ejemplar> ejemplares;

    public Persona() {
		super();
	}

	public Persona(long id, String nombre, String email, Credenciales credenciales, Set<Ejemplar> ejemplares) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.credenciales = credenciales;
		this.ejemplares = ejemplares;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

    public Set<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Set<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(credenciales, ejemplares, email, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(credenciales, other.credenciales) && Objects.equals(ejemplares, other.ejemplares)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(nombre, other.nombre);
	}
    
    
}


package com.dwes.modelo;

import java.util.Date;
import java.util.Objects;

public class Mensaje {
    private long id;
    private Date fechahora;
    private String mensaje;
    private long id_ejemplar;
    private long id_persona;
    
    // Relación con Ejemplar (M a 1)
    private Ejemplar ejemplar;
    
    private Persona persona;

    public Mensaje() {
		super();
	}	
    
	public Mensaje(Long id, Date fechahora, String mensaje, Ejemplar ejemplar, Persona persona) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.ejemplar = ejemplar;
		this.persona = persona;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }
    
    public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public long getId_ejemplar() {
		return id_ejemplar;
	}

	public void setId_ejemplar(long id_ejemplar) {
		this.id_ejemplar = id_ejemplar;
	}

	public long getId_persona() {
		return id_persona;
	}

	public void setId_persona(long id_persona) {
		this.id_persona = id_persona;
	}
	
    
    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", fechahora=" + fechahora +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(ejemplar, fechahora, id, mensaje, persona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(ejemplar, other.ejemplar) && Objects.equals(fechahora, other.fechahora)
				&& Objects.equals(id, other.id) && Objects.equals(mensaje, other.mensaje)
				&& Objects.equals(persona, other.persona);
	}

}


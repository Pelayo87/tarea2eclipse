package com.dwes.modelo;

import java.util.Date;

public class Mensaje {
    private Long id;
    private Date fechahora;
    private String mensaje;
    
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
    
    

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", fechahora=" + fechahora +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}


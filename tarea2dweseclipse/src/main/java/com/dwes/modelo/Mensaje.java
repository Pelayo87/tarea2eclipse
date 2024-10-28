package com.dwes.modelo;

import java.util.Date;

public class Mensaje {
    private Long id;
    private Date fechahora;
    private String mensaje;

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

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", fechahora=" + fechahora +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}

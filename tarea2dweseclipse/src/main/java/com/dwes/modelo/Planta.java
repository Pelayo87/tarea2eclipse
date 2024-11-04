package com.dwes.modelo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Planta implements Serializable {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String nombrecomun;
    private String nombrecientifico;
    
    // Relación con Ejemplar (1 a N)
    private Set<Ejemplar> ejemplares;
    
    public Planta() {
    	
    }

    public Planta(String codigo, String nombrecomun, String nombrecientifico) {
        this.codigo = codigo;
        this.nombrecomun = nombrecomun;
        this.nombrecientifico = nombrecientifico;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombrecomun() {
        return nombrecomun;
    }

    public void setNombrecomun(String nombrecomun) {
        this.nombrecomun = nombrecomun;
    }

    public String getNombrecientifico() {
        return nombrecientifico;
    }

    public void setNombrecientifico(String nombrecientifico) {
        this.nombrecientifico = nombrecientifico;
    }

    public Set<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Set<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    @Override
    public String toString() {
        return "Planta{" +
                "codigo='" + codigo + '\'' +
                ", nombrecomun='" + nombrecomun + '\'' +
                ", nombrecientifico='" + nombrecientifico + '\'' +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(codigo, ejemplares, nombrecientifico, nombrecomun);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(ejemplares, other.ejemplares)
				&& Objects.equals(nombrecientifico, other.nombrecientifico)
				&& Objects.equals(nombrecomun, other.nombrecomun);
	}
    
}




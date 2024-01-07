package edu.it.model;

import javax.persistence.*;

@Entity
@Table(name="alumnos")
public class Alumno {
	@Id
    public String id;
    public String nombre;
    public String apellido;
    public String calle;
    @Column(name="calle_numero")
    public String calleNumero;
    public String estado;
    public String pais;
    
    public Alumno() {
    }

	public Alumno(String id, String nombre, String apellido, String calle, String calleNumero, String estado,
			String pais) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.calle = calle;
		this.calleNumero = calleNumero;
		this.estado = estado;
		this.pais = pais;
	}
}

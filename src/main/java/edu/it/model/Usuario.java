package edu.it.model;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	public String id;
	public String nombre;
	public String salt;
	@Column(name="password_encriptada")
	public String passwordEnciptada;
}

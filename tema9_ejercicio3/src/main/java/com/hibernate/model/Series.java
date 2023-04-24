package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="serie")
public class Series {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idserie")
 private int id;
	@Column(name="nombre")
 private String nombre;
	@Column(name="temporadas")
 private int temporadas;
	@Column(name="capitulos")
	 private int capitulos;
	public Series()
	{
		super();
	}
	public Series( String nombre, int temporadas, int capitulos) {
		
		
		this.nombre = nombre;
		this.temporadas = temporadas;
		this.capitulos = capitulos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTemporadas() {
		return temporadas;
	}
	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}
	public int getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}
	
}
 

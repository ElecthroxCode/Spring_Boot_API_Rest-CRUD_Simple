package com.empleado.apiempleado.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
	
	private String calle;
	private String numero;
	private String complemento;
	
	
	public Direccion(DatosDireccion direccion) {
		this.calle = direccion.calle();
		this.numero = direccion.numero();
		this.complemento = direccion.complemento();
	}


	public Direccion actualizarDireccion(DatosDireccion direccion) {
		this.calle=direccion.calle();
		this.numero=direccion.numero();
		this.complemento=direccion.complemento();
		return this;
	}
}

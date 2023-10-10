package com.empleado.apiempleado.empleado;

import com.empleado.apiempleado.direccion.Direccion;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name ="empleados")
@Entity(name = "Empleado")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String documento;
	private String email;
	private Boolean activo;
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	@Embedded
	private Direccion direccion;
	
	public Empleado(DatosCrearEmpleado datosCrearEmpleado) {
		this.activo = true;
		this.nombre =datosCrearEmpleado.nombre();
		this.documento= datosCrearEmpleado.documento();
		this.email=datosCrearEmpleado.email();
		this.cargo = datosCrearEmpleado.cargo();
		this.direccion = new Direccion(datosCrearEmpleado.direccion());
	}

	public void actualizarDatos(DatosActualizarEmpleado datosActualizarEmpleado) {
		if(datosActualizarEmpleado.nombre() != null) {
			this.nombre = datosActualizarEmpleado.nombre();
		}
		if(datosActualizarEmpleado.email() != null) {
			this.email = datosActualizarEmpleado.email();
		}
		if(datosActualizarEmpleado.direccion() != null) {
			this.direccion = direccion.actualizarDireccion(datosActualizarEmpleado.direccion());
		}
	}
	
	
	public void desactivarEmpleado() {
		this.activo = false;
		
	}
}

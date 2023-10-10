package com.empleado.apiempleado.empleado;

public record DatosListaEmpleado(Long id, String nombre, Cargo cargo) {
	public DatosListaEmpleado(Empleado empleado) {
		this(empleado.getId(),empleado.getNombre(),empleado.getCargo());
	}
}

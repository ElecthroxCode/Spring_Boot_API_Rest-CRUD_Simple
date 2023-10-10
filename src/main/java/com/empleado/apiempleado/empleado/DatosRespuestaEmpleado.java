package com.empleado.apiempleado.empleado;

import com.empleado.apiempleado.direccion.DatosDireccion;

public record DatosRespuestaEmpleado(Long id, String nombre, String documento,
		String email, String cargo, DatosDireccion direccion) {

	
}

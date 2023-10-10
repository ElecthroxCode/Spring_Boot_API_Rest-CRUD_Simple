package com.empleado.apiempleado.empleado;

import com.empleado.apiempleado.direccion.DatosDireccion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosCrearEmpleado(
		@NotBlank
		String nombre,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String documento,
		@NotBlank
		String email,
		@NotNull
		Cargo cargo,
		@NotNull
		@Valid
		DatosDireccion direccion) {

}

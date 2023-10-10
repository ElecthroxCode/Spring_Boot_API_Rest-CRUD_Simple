package com.empleado.apiempleado.empleado;

import com.empleado.apiempleado.direccion.DatosDireccion;

public record DatosActualizarEmpleado(Long id, String nombre, String email, DatosDireccion direccion) {

}

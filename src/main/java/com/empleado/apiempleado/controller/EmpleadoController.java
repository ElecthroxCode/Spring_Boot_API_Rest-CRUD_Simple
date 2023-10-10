package com.empleado.apiempleado.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.empleado.apiempleado.direccion.DatosDireccion;
import com.empleado.apiempleado.empleado.DatosActualizarEmpleado;
import com.empleado.apiempleado.empleado.DatosCrearEmpleado;
import com.empleado.apiempleado.empleado.DatosListaEmpleado;
import com.empleado.apiempleado.empleado.DatosRespuestaEmpleado;
import com.empleado.apiempleado.empleado.Empleado;
import com.empleado.apiempleado.empleado.EmpleadoRepository;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@PostMapping
	public ResponseEntity<DatosRespuestaEmpleado> crearEmpleado(@RequestBody @Valid DatosCrearEmpleado datosCrearEmpleado,
			UriComponentsBuilder uriComponentsBuilder) {
		Empleado empleado = empleadoRepository.save(new Empleado(datosCrearEmpleado));
		URI uri = uriComponentsBuilder.path("/empleado/{id}").buildAndExpand(empleado.getId()).toUri();
		return ResponseEntity.created(uri).body(new DatosRespuestaEmpleado(empleado.getId(), empleado.getNombre(), empleado.getDocumento(),
				empleado.getEmail(), empleado.getCargo().toString(),
				new DatosDireccion(empleado.getDireccion().getCalle(), empleado.getDireccion().getNumero(),
						empleado.getDireccion().getComplemento())));
	}
	
	@GetMapping
	public Page<DatosListaEmpleado> listarEmpleado(Pageable paginacion){
		return empleadoRepository.findByActivoTrue(paginacion).map(DatosListaEmpleado::new);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DatosRespuestaEmpleado> actualizarEmpleado(@RequestBody @Valid DatosActualizarEmpleado datosActualizarEmpleado) {
		Empleado empleado= empleadoRepository.getReferenceById(datosActualizarEmpleado.id());
		empleado.actualizarDatos(datosActualizarEmpleado);
		return ResponseEntity.ok(new DatosRespuestaEmpleado(empleado.getId(), empleado.getNombre(), empleado.getDocumento(),
				empleado.getEmail(), empleado.getCargo().toString(),
				new DatosDireccion(empleado.getDireccion().getCalle(), empleado.getDireccion().getNumero(),
						empleado.getDireccion().getComplemento())));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarEmpleado(@PathVariable Long id) {
		Empleado empleado = empleadoRepository.getReferenceById(id);
		empleado.desactivarEmpleado();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaEmpleado> obtenerEmpleado(@PathVariable Long id) {
		Empleado empleado = empleadoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DatosRespuestaEmpleado(empleado.getId(), empleado.getNombre(), empleado.getDocumento(),
				empleado.getEmail(), empleado.getCargo().toString(),
				new DatosDireccion(empleado.getDireccion().getCalle(), empleado.getDireccion().getNumero(),
						empleado.getDireccion().getComplemento())));
	}
}

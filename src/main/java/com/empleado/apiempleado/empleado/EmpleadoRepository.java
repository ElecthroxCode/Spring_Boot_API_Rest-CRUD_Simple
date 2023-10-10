package com.empleado.apiempleado.empleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

	Page<Empleado> findByActivoTrue(Pageable paginacion);

}

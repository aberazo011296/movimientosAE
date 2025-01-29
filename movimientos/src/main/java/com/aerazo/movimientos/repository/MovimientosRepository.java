package com.aerazo.movimientos.repository;

import com.aerazo.movimientos.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientosRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByCuentaId(Long cuentaId);

    List<Movimiento> findByCuentaIdAndFechaBetweenOrderByFechaDesc(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);


}

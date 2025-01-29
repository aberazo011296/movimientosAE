package com.aerazo.movimientos;

import com.aerazo.movimientos.entity.Cuenta;
import com.aerazo.movimientos.entity.Movimiento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CrearCuentaYMovimientoTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCrearCuentaYMovimiento() throws Exception {

		Cuenta nuevaCuenta = new Cuenta();
		nuevaCuenta.setNumeroCuenta(UUID.randomUUID().toString().substring(0, 10));
		nuevaCuenta.setSaldoInicial(new BigDecimal("500.0"));
		nuevaCuenta.setEstado(true);
		nuevaCuenta.setTipoCuenta("Ahorros");
		nuevaCuenta.setClienteId(1L);

		String cuentaResponse = mockMvc.perform(post("/cuentas")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(nuevaCuenta)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.saldoInicial").value(new BigDecimal("500.0")))
				.andReturn()
				.getResponse()
				.getContentAsString();

		Long cuentaId = objectMapper.readTree(cuentaResponse).get("id").asLong();

		Movimiento nuevoMovimiento = new Movimiento();
		nuevoMovimiento.setCuentaId(cuentaId);
		nuevoMovimiento.setValor(new BigDecimal("200.0"));

		mockMvc.perform(post("/movimientos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(nuevoMovimiento)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.valor").value(200.0));

		mockMvc.perform(get("/cuentas/" + cuentaId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.saldoInicial").value(new BigDecimal("700.0")));

	}
}

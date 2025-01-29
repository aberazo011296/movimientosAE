package com.aerazo.clientes;

import com.aerazo.clientes.entity.Cliente;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ClienteTest {

	private static Validator validator;

	@BeforeAll
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testClienteValidationesError() {

		Cliente cliente = new Cliente();
		cliente.setNombre("");
		cliente.setGenero(null);
		cliente.setEdad(180);
		cliente.setIdentificacion("12345");
		cliente.setEstado(null);
		cliente.setContrasena("1234567");

		Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);

		assertThat(violations).hasSize(6);

		assertThat(violations).anyMatch(violation ->
				violation.getMessage().equals("El nombre es obligatorio")
		);
		assertThat(violations).anyMatch(violation ->
				violation.getMessage().equals("El género es obligatorio")
		);
		assertThat(violations).anyMatch(violation ->
				violation.getMessage().equals("La edad no puede superar los 150 años")
		);
		assertThat(violations).anyMatch(violation ->
				violation.getMessage().equals("La identificación debe tener 10 caracteres")
		);
		assertThat(violations).anyMatch(violation ->
				violation.getMessage().equals("El estado es obligatorio")
		);
		assertThat(violations).anyMatch(violation ->
				violation.getMessage().equals("La contraseña debe tener al menos 8 caracteres")
		);
	}

	@Test
	public void testClienteValido() {

		Cliente cliente = new Cliente();
		cliente.setNombre("Juan Pérez");
		cliente.setGenero("Masculino");
		cliente.setEdad(30);
		cliente.setIdentificacion("1234567890");
		cliente.setDireccion("Calle Principal");
		cliente.setTelefono("0987654321");
		cliente.setContrasena("secure123");
		cliente.setEstado(true);

		Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);

		assertThat(violations).isEmpty();

	}

}

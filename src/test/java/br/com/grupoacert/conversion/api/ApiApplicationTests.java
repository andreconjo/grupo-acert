package br.com.grupoacert.conversion.api;

import br.com.grupoacert.conversion.api.model.Conversion;
import br.com.grupoacert.conversion.api.repository.ConversionRepository;
import br.com.grupoacert.conversion.api.service.ConversionService;
import br.com.grupoacert.conversion.api.util.ConversionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

	@Autowired
	private ConversionService service;

	@MockBean
	private ConversionRepository repository;

	@Test
	public void convertCelsiusToFahrenheitTest() {
		Conversion conversion = new Conversion();
		conversion.setCelsius(19.0);
		conversion.setFahrenheit(66.2);
		conversion.setType(ConversionType.CelsiusToFahrenheit);

		when(repository.save(conversion)).thenReturn(conversion);
		assertEquals(conversion, service.save(conversion));
	}

	@Test
	public void convertFahrenheitToCelsiusTest() {
		Conversion conversion = new Conversion();
		conversion.setCelsius(19.0);
		conversion.setFahrenheit(66.2);
		conversion.setType(ConversionType.FahrenheitToCelsius);

		when(repository.save(conversion)).thenReturn(conversion);
		assertEquals(conversion, service.save(conversion));
	}

	@Test
	public void getConersionByIdTest() {
		Conversion conversion = new Conversion();
		conversion.setId(999L);
		conversion.setCelsius(19.0);
		conversion.setFahrenheit(66.2);
		conversion.setType(ConversionType.FahrenheitToCelsius);

		Long id = 999L;
		when(repository.findById(id))
				.thenReturn(Optional.of(conversion));
		assertEquals(conversion, service.getById(id));
	}

	@Test
	public void getAll() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Conversion(19.0, 66.0, ConversionType.FahrenheitToCelsius),
					new Conversion(19.0, 66.0, ConversionType.CelsiusToFahrenheit))
				.collect(Collectors.toList()));
		assertEquals(2, service.getAll().size());
	}

}

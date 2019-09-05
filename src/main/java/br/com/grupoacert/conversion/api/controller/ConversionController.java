package br.com.grupoacert.conversion.api.controller;

import br.com.grupoacert.conversion.api.event.ResourceCreatedEvent;
import br.com.grupoacert.conversion.api.model.Conversion;
import br.com.grupoacert.conversion.api.model.dto.ConversionDTO;
import br.com.grupoacert.conversion.api.service.ConversionService;
import br.com.grupoacert.conversion.api.util.ConversionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/conversion")
public class ConversionController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    ApplicationEventPublisher publisher;

    @PostMapping("toCelsius")
    public ResponseEntity<ConversionDTO> toCelsius(@RequestBody ConversionDTO conversionDTO, HttpServletResponse response) {

        Conversion conversion = conversionService.save(
                new Conversion(0.0,
                        conversionDTO.temperature,
                        ConversionType.FahrenheitToCelsius));

        publisher.publishEvent(new ResourceCreatedEvent(this, response, conversion.getId()));

        return new ResponseEntity<>(new ConversionDTO(conversion.getCelsius()), HttpStatus.OK);
    }

    @PostMapping("toFahrenheit")
    public ResponseEntity<ConversionDTO> toFahrenheit(@RequestBody ConversionDTO conversionDTO, HttpServletResponse response) {
        Conversion conversion = conversionService.save(
                new Conversion(conversionDTO.temperature, 0.0, ConversionType.CelsiusToFahrenheit));

        publisher.publishEvent(new ResourceCreatedEvent(this, response, conversion.getId()));

        return new ResponseEntity<>(new ConversionDTO(conversion.getFahrenheit()), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Conversion>> getAll() {
        return new ResponseEntity<>(conversionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversion> getById(@PathVariable Long id) {
        Conversion conversion = conversionService.getById(id);

        if(conversion == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(conversion, HttpStatus.OK);
    }


}

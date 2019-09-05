package br.com.grupoacert.conversion.api.controller;

import br.com.grupoacert.conversion.api.event.ResourceCreatedEvent;
import br.com.grupoacert.conversion.api.model.Conversion;
import br.com.grupoacert.conversion.api.model.dto.ConversionDTO;
import br.com.grupoacert.conversion.api.service.ConversionService;
import br.com.grupoacert.conversion.api.util.ConversionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/conversion")
public class ConversionController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    ApplicationEventPublisher publisher;

    @PostMapping("toCelsius")
    public ResponseEntity<Conversion> toCelsius(@RequestBody ConversionDTO conversionDTO, HttpServletResponse response) {

        Conversion conversion = conversionService.save(
                new Conversion(0.0,
                        conversionDTO.temperature,
                        ConversionType.FahrenheitToCelsius));

        publisher.publishEvent(new ResourceCreatedEvent(this, response, conversion.getId()));

        return new ResponseEntity<>(conversion, HttpStatus.OK);
    }

    @PostMapping("toFahrenheit")
    public ResponseEntity<Conversion> toFahrenheit(@RequestBody ConversionDTO conversionDTO, HttpServletResponse response) {
        Conversion conversion = conversionService.save(
                new Conversion(conversionDTO.temperature, 0.0, ConversionType.CelsiusToFahrenheit));

        publisher.publishEvent(new ResourceCreatedEvent(this, response, conversion.getId()));

        return new ResponseEntity<>(conversion, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<Conversion>> getAll(Pageable pageable) {
        return new ResponseEntity<>(conversionService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Conversion> getById(Long id) {
        Conversion conversion = conversionService.getById(id);

        if(conversion == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(conversionService.getById(id), HttpStatus.NO_CONTENT);
    }


}

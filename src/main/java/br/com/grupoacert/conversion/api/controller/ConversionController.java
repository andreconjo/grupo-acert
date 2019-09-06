package br.com.grupoacert.conversion.api.controller;

import br.com.grupoacert.conversion.api.event.ResourceCreatedEvent;
import br.com.grupoacert.conversion.api.model.Conversion;
import br.com.grupoacert.conversion.api.model.dto.ConversionDTO;
import br.com.grupoacert.conversion.api.model.resource.ConversionResource;
import br.com.grupoacert.conversion.api.model.resource.ConversionResources;
import br.com.grupoacert.conversion.api.service.ConversionService;
import br.com.grupoacert.conversion.api.util.ConversionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/conversion")
public class ConversionController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    ApplicationEventPublisher publisher;

    @PostMapping("FahrenheitToCelsius")
    public ResponseEntity<ConversionResource> toCelsius(@RequestBody ConversionDTO conversionDTO, HttpServletResponse response) {
        List<ConversionDTO> conversionDTOs = new ArrayList<>();

        Conversion conversion = conversionService.save(
                new Conversion(0.0,
                        conversionDTO.temperature,
                        ConversionType.FahrenheitToCelsius));

        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(conversion.getId()).toUri();
        return new ResponseEntity<>(new ConversionResource(conversion), HttpStatus.OK);
    }

    @PostMapping("CelsiusToFahrenheit")
    public ResponseEntity<ConversionResource> toFahrenheit(@RequestBody ConversionDTO conversionDTO, HttpServletResponse response) {
        Conversion conversion = conversionService.save(
                new Conversion(conversionDTO.temperature, 0.0, ConversionType.CelsiusToFahrenheit));

        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(conversion.getId()).toUri();
        return new ResponseEntity<>(new ConversionResource(conversion), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ConversionResources> getAll() {
        List<ConversionResource> conversionResources = conversionService.getAll().stream().map(ConversionResource::new).collect(Collectors.toList());
        return ResponseEntity.ok(new ConversionResources(conversionResources));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversion> getById(@PathVariable Long id) {
        Conversion conversion = conversionService.getById(id);

        if(conversion == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(conversion, HttpStatus.OK);
    }


}

package br.com.grupoacert.conversion.api.model.resource;

import br.com.grupoacert.conversion.api.controller.ConversionController;
import br.com.grupoacert.conversion.api.model.Conversion;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ConversionResource extends ResourceSupport {

    private final Conversion conversion;

    public ConversionResource(Conversion conversion) {
        this.conversion = conversion;
        final long id = conversion.getId();
        add(linkTo(methodOn(ConversionController.class).getById(id)).withSelfRel());

    }

    public Conversion getConversion() {
        return conversion;
    }
}

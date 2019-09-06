package br.com.grupoacert.conversion.api.model.resource;

import br.com.grupoacert.conversion.api.controller.ConversionController;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ConversionResources extends ResourceSupport {

    private final List<ConversionResource> conversions;

    public ConversionResources(List<ConversionResource> conversions) {
        this.conversions = conversions;

        add(linkTo((ConversionController.class)).withSelfRel());

    }

    public List<ConversionResource> getConversions() {
        return conversions;
    }
}
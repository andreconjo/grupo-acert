package br.com.grupoacert.conversion.api.service;

import br.com.grupoacert.conversion.api.model.Conversion;

import java.util.List;

public interface ConversionService {

    Conversion save(Conversion conversion);

    List<Conversion> getAll();

    Conversion getById(Long id);
}

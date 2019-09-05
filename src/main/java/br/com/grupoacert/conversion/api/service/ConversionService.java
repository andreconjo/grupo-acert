package br.com.grupoacert.conversion.api.service;

import br.com.grupoacert.conversion.api.model.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConversionService {

    Conversion save(Conversion conversion);

    Page<Conversion> getAll(Pageable pageable);

    Conversion getById(Long id);
}

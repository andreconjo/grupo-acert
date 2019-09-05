package br.com.grupoacert.conversion.api.service.impl;

import br.com.grupoacert.conversion.api.model.Conversion;
import br.com.grupoacert.conversion.api.repository.ConversionRepository;
import br.com.grupoacert.conversion.api.service.ConversionService;
import br.com.grupoacert.conversion.api.util.ConversionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    ConversionRepository conversionRepository;

    @Override
    public Conversion save(Conversion conversion) {
        if(conversion.getType() == ConversionType.CelsiusToFahrenheit) {
            conversion.setFahrenheit((9.0/5.0) * conversion.getCelsius() + 32);
        }else {
            conversion.setCelsius(((conversion.getFahrenheit() - 32) * 5) / 9);
        }

        return conversionRepository.save(conversion);
    }

    @Override
    public Page<Conversion> getAll(Pageable pageable) {
        return conversionRepository.findAll(pageable);
    }

    @Override
    public Conversion getById(Long id) {
        return conversionRepository.findById(id).orElse(null);
    }
}

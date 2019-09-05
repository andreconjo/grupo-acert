package br.com.grupoacert.conversion.api.service.impl;

import br.com.grupoacert.conversion.api.model.Conversion;
import br.com.grupoacert.conversion.api.repository.ConversionRepository;
import br.com.grupoacert.conversion.api.service.ConversionService;
import br.com.grupoacert.conversion.api.util.ConversionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;


@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    ConversionRepository conversionRepository;

    @Override
    public Conversion save(Conversion conversion) {

        if(conversion.getType() == ConversionType.CelsiusToFahrenheit)
            conversion.setFahrenheit(Double.valueOf(new DecimalFormat("##.##").format((9.0/5.0) * conversion.getCelsius() + 32).replaceAll(",",".")));
        else
            conversion.setCelsius(Double.valueOf(new DecimalFormat("##.##").format(((conversion.getFahrenheit() - 32) * 5) / 9).replaceAll(",",".")));

        return conversionRepository.save(conversion);
    }

    @Override
    public List<Conversion> getAll() {
        return conversionRepository.findAll();
    }

    @Override
    public Conversion getById(Long id) {
        return conversionRepository.findById(id).orElse(null);
    }
}

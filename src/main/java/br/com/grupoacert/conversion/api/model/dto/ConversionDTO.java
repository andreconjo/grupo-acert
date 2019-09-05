package br.com.grupoacert.conversion.api.model.dto;

public class ConversionDTO {

    public Double temperature;

    public ConversionDTO() {
    }

    public ConversionDTO(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}

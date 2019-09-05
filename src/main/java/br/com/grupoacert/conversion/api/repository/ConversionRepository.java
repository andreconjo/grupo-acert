package br.com.grupoacert.conversion.api.repository;

import br.com.grupoacert.conversion.api.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {
}

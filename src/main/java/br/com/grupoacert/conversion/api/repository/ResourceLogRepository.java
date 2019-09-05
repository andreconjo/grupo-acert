package br.com.grupoacert.conversion.api.repository;

import br.com.grupoacert.conversion.api.model.ResourceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceLogRepository extends JpaRepository<ResourceLog, Long> {
}

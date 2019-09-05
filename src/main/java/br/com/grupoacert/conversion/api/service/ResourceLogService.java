package br.com.grupoacert.conversion.api.service;

import br.com.grupoacert.conversion.api.model.ResourceLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResourceLogService {
    ResourceLog save(ResourceLog resourceLog);

    Page<ResourceLog> getAll(Pageable pageable);
}

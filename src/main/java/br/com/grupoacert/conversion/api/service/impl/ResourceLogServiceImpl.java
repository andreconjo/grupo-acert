package br.com.grupoacert.conversion.api.service.impl;

import br.com.grupoacert.conversion.api.model.ResourceLog;
import br.com.grupoacert.conversion.api.repository.ResourceLogRepository;
import br.com.grupoacert.conversion.api.service.ResourceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResourceLogServiceImpl implements ResourceLogService {

    @Autowired
    ResourceLogRepository resourceLogRepository;

    @Override
    public ResourceLog save(ResourceLog resourceLog) {
        return resourceLogRepository.save(resourceLog);
    }

    @Override
    public Page<ResourceLog> getAll(Pageable pageable) {
        return resourceLogRepository.findAll(pageable);
    }
}

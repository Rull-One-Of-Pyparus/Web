package com.example.web.Services;

import com.example.web.Config.Entity.Status;
import com.example.web.Repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public Status findById(Long id) {
        return statusRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}

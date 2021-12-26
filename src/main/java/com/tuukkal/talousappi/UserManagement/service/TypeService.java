package com.tuukkal.talousappi.UserManagement.service;


import com.tuukkal.talousappi.UserManagement.domain.Type;
import com.tuukkal.talousappi.UserManagement.repo.TypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepo typeRepo;

    public Type getById(Long id) {
        return typeRepo.getById(id);
    }

    public List<Type> getAll(){
        return typeRepo.findAll();
    }

    public Type saveType(Type type) {
        return typeRepo.save(type);
    }


}

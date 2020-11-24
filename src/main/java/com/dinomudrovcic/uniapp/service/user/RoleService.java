package com.dinomudrovcic.uniapp.service.user;

import com.dinomudrovcic.uniapp.domain.auth.Role;
import com.dinomudrovcic.uniapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getName(Long id) {
        return roleRepository.findById(id).orElse(null);
    }



}

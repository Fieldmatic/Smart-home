package com.bsep.smart.home.services.role;

import com.bsep.smart.home.exception.RoleNotFoundException;
import com.bsep.smart.home.model.Role;
import com.bsep.smart.home.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetRoleByName {
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role execute(final String name) {
        return roleRepository.getByName(name).orElseThrow(RoleNotFoundException::new);
    }
}

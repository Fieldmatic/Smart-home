package com.bsep.smart.home.services.user;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Role;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.services.role.GetRoleByName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeUserRole {

    private final GetRoleByName getRoleByName;
    private final PersonRepository personRepository;
    private final GetUserById getUserById;

    @Transactional
    public Person execute(UUID id, String roleName) {
        Person person = getUserById.execute(id);
        Role role = getRoleByName.execute(roleName);
        person.setRole(role);
        return personRepository.save(person);
    }
}

package com.bsep.smart.home.services.user;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.services.role.GetRoleByName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsers {
    private final PersonRepository personRepository;
    private final GetRoleByName getRoleByName;

    public List<Person> execute() {
        return personRepository.findAllByRoleNot(getRoleByName.execute("ADMIN"));
    }
}

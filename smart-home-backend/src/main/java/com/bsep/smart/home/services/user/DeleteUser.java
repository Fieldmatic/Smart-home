package com.bsep.smart.home.services.user;


import com.bsep.smart.home.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUser {
    private final PersonRepository personRepository;
    private final GetUserById getUserById;

    @Transactional
    public void execute(UUID id) {
        personRepository.delete(getUserById.execute(id));
    }
}

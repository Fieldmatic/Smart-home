package com.bsep.smart.home.services.user;

import com.bsep.smart.home.exception.UserNotFoundException;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.model.Person;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserById {
	private final PersonRepository userRepository;

	@Transactional(readOnly = true)
	public Person execute(final UUID id) {
		return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}
}

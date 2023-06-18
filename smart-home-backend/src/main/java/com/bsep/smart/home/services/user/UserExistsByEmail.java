package com.bsep.smart.home.services.user;

import com.bsep.smart.home.repository.PersonRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserExistsByEmail {

	private final PersonRepository userRepository;

	public Boolean execute(final String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}

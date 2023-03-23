package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.converter.UserConverter;
import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.exception.UnauthorizedException;
import com.bsep.smart.home.services.user.GetUserById;
import com.bsep.smart.home.model.Person;

import java.util.UUID;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetSelf {
	private final GetUserById getUserById;

	public UserResponse execute() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new UnauthorizedException();
		}

		// Getting the user from the DB, because the user from the auth principal
		// is not a managed object, so some lazy collections (assigned items) fail
		// to be fetched.
		final UUID userId = ((Person) authentication.getPrincipal()).getId();

		return UserConverter.toUserResponse(getUserById.execute(userId));
	}
}

package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.request.auth.LoginRequest;
import com.bsep.smart.home.dto.request.registration.RegistrationRequest;
import com.bsep.smart.home.dto.response.AuthTokenResponse;
import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.services.keys.AddPrivateKey;
import com.bsep.smart.home.services.keys.ReadPrivateKey;
import com.bsep.smart.home.model.CertificateSigningRequest;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.auth.GetSelf;
import com.bsep.smart.home.services.auth.LogInUser;
import com.bsep.smart.home.services.csr.SaveCSR;
import com.bsep.smart.home.services.registration.RegisterNewUser;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final LogInUser loginUser;
	private final GetSelf getSelf;
	private final RegisterNewUser registerNewUser;

	private final SaveCSR saveCSR;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/login")
	public AuthTokenResponse login(@Valid @RequestBody final LoginRequest loginRequest) {
		return new AuthTokenResponse(loginUser.execute(loginRequest.getEmail(), loginRequest.getPassword()));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/self")
	public UserResponse getSelf() {
		return getSelf.execute();
	}
}

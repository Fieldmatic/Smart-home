package com.bsep.smart.home.services.password;

import com.bsep.smart.home.dto.request.password.ChangePasswordRequest;
import com.bsep.smart.home.services.user.SaveUser;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.jwt.JwtValidateAndGetUsername;
import com.bsep.smart.home.services.user.GetUserByEmail;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePassword {

    private final JwtValidateAndGetUsername jwtValidateAndGetUsername;
    private final GetUserByEmail getUserByEmail;
    private final SaveUser saveUser;
    private final CheckPasswordConfirmation checkPasswordConfirmation;
    private final CheckIsPasswordDifferent checkIsPasswordDifferent;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = false, rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
    public void execute(ChangePasswordRequest changePasswordDTO) {
        checkPasswordConfirmation.execute(changePasswordDTO.getNewPassword(), changePasswordDTO.getNewPasswordConfirmation());
        final String username = jwtValidateAndGetUsername.execute(changePasswordDTO.getAuthToken());
        final Person user = getUserByEmail.execute(username);
        checkIsPasswordDifferent.execute(changePasswordDTO.getNewPassword(), user);
        user.setPasswordHash(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        saveUser.execute(user);
    }

}

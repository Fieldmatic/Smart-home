package com.bsep.smart.home.controller;

import com.bsep.smart.home.converter.UserConverter;
import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.user.ChangeUserRole;
import com.bsep.smart.home.services.user.DeleteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final DeleteUser deleteUser;
    private final ChangeUserRole changeUserRole;

    @DeleteMapping("/{id}")
    @HasAnyPermission({Permission.USER_MANIPULATION})
    public void deleteUser(@PathVariable UUID id) {
        deleteUser.execute(id);
    }

    @PutMapping("/{id}")
    @HasAnyPermission({Permission.USER_MANIPULATION})
    public UserResponse changeRole(@PathVariable UUID id, @RequestParam String roleName) {
        return UserConverter.toUserResponse(changeUserRole.execute(id, roleName));
    }
}

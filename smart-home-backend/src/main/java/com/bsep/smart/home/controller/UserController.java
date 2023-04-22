package com.bsep.smart.home.controller;

import com.bsep.smart.home.converter.UserConverter;
import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.user.ChangeUserRole;
import com.bsep.smart.home.services.user.DeleteUser;
import com.bsep.smart.home.services.user.GetUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final DeleteUser deleteUser;
    private final ChangeUserRole changeUserRole;
    private final GetUsers getUsers;

    @DeleteMapping("/{id}")
    @HasAnyPermission({Permission.USER_MANIPULATION})
    public void deleteUser(@PathVariable UUID id) {
        deleteUser.execute(id);
    }

    @PutMapping("/{id}")
    @HasAnyPermission({Permission.USER_MANIPULATION})
    public UserResponse changeRole(@NotBlank @PathVariable UUID id, @NotBlank @RequestParam String roleName) {
        return UserConverter.toUserResponse(changeUserRole.execute(id, roleName));
    }

    @GetMapping("/all")
    @HasAnyPermission({Permission.USER_MANIPULATION})
    public List<UserResponse> getUsers() {
        return UserConverter.toUsersResponse(getUsers.execute());
    }
}

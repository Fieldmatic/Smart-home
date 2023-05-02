package com.bsep.smart.home.controller;

import com.bsep.smart.home.converter.UserConverter;
import com.bsep.smart.home.dto.request.registration.AdminRegistrationRequest;
import com.bsep.smart.home.dto.request.users.PageRequest;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.jpaspecification.SortDirection;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.registration.RegisterNewUserAdmin;
import com.bsep.smart.home.services.user.ChangeUserRole;
import com.bsep.smart.home.services.user.DeleteUser;
import com.bsep.smart.home.services.user.GetUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.KeyStoreException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final DeleteUser deleteUser;
    private final ChangeUserRole changeUserRole;
    private final GetUsers getUsers;
    private final RegisterNewUserAdmin registerNewUserAdmin;

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

    @GetMapping
    @HasAnyPermission({Permission.USER_MANIPULATION})
    public PageResponse<UserResponse> getUsers(@Valid final PageRequest pageRequest, @RequestParam(required = false) final String search,
                                               @RequestParam(required = false) final String sortField, @RequestParam(required = false) final SortDirection sortDirection,
                                               @RequestParam(required = false) final String userRole) {
        return getUsers.execute(UserConverter.toUserPageInfo(pageRequest.getPageNumber(), pageRequest.getPageSize(), search, sortField, sortDirection, userRole));
    }

    @PostMapping("/register")
    @HasAnyPermission({Permission.USER_MANIPULATION})
    public void register(@Valid @RequestBody AdminRegistrationRequest adminRegistrationRequest) throws KeyStoreException {
        registerNewUserAdmin.execute(adminRegistrationRequest);
    }
}

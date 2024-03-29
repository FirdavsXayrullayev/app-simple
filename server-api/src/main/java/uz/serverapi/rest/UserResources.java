package uz.serverapi.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.serverapi.dto.LoginDto;
import uz.serverapi.dto.ResponseDto;
import uz.serverapi.dto.UserDto;
import uz.serverapi.servise.UserService;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class UserResources {
    private final UserService userService;
    @PreAuthorize("hasAnyAuthority('READ')")
    @GetMapping("get-by-id")
    public ResponseDto<UserDto> getById(@RequestParam Integer id){
        return userService.getById(id);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN')")
    @PostMapping("add-new-user")
    public ResponseDto<UserDto> addNewUser(@RequestBody UserDto userDto){
        return userService.addNewUser(userDto);
    }
    @PreAuthorize("hasAnyAuthority('DELETE')")
    @DeleteMapping("delete-by-id")
    public ResponseDto<UserDto> deleteUserById(@RequestParam Integer id){
        return userService.deleteUserById(id);
    }
    @PreAuthorize("hasAnyAuthority('UPDATE')")
    @PatchMapping("update")
    public ResponseDto<UserDto> updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @PostMapping("login-user")
    public ResponseDto<String> loginUser(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
}

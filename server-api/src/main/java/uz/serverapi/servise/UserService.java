package uz.serverapi.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.serverapi.dto.LoginDto;
import uz.serverapi.dto.ResponseDto;
import uz.serverapi.dto.UserDto;
import uz.serverapi.repository.UserRepository;

public interface UserService {
    ResponseDto<UserDto> getById(Integer id);

    ResponseDto<UserDto> addNewUser(UserDto userDto);

    ResponseDto<UserDto> deleteUserById(Integer id);

    ResponseDto<UserDto> updateUser(UserDto userDto);

    ResponseDto<String> login(LoginDto loginDto);
}

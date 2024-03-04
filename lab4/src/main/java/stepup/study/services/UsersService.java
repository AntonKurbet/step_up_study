package stepup.study.services;

import stepup.study.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> readData(String... args);

    void writeData(List<UserDto> list);
}

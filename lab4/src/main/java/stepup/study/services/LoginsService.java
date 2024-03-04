package stepup.study.services;

import stepup.study.dto.LoginDto;

import java.util.List;

public interface LoginsService {
    List<LoginDto> readData(String... args);

    void writeData(List<LoginDto> list);
}

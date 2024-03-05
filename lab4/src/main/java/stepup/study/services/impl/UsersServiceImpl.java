package stepup.study.services.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import stepup.study.dto.UserDto;
import stepup.study.entities.User;
import stepup.study.repositories.UserRepository;
import stepup.study.services.UsersService;
import stepup.study.utils.Tools;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Override
    public void writeData(List<UserDto> list) {
        for (var user : list) {
            userRepository.save(toEntity(user));
        }
    }

    @Override
    public List<UserDto> readData(String... args) {
        List<UserDto> records = new ArrayList<>();
        try (var csvReader = new CSVReader(new FileReader(args[0]))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(toDto(values));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    private UserDto toDto(String[] data) {
        if (data.length < 3) return null;
        var id = Tools.tryParseInt(data[0]);
        return new UserDto(id, data[1], data[2]);
    }

    private User toEntity(UserDto user) {
        var result = new User();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setFio(user.getFio());
        return result;
    }
}

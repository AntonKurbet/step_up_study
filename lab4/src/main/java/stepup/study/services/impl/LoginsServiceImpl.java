package stepup.study.services.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import stepup.study.dto.LoginDto;
import stepup.study.entities.Login;
import stepup.study.repositories.LoginRepository;
import stepup.study.services.LoginsService;
import stepup.study.utils.Tools;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginsServiceImpl implements LoginsService {

    private final LoginRepository loginRepository;

    @Override
    public void writeData(List<LoginDto> list) {
        for (var login : list) {
            loginRepository.save(toEntity(login));
        }
    }

    @Override
    public List<LoginDto> readData(String... args) {
        List<LoginDto> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(args[0]))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(toDto(values));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    private LoginDto toDto(String[] data) {
        if (data.length < 4) return null;
        var id = Tools.tryParseInt(data[0]);
        var date = Tools.tryParseDate(data[1]);
        var userId = Tools.tryParseInt(data[2]);
        return new LoginDto(id, date, userId, data[3]);
    }

    private Login toEntity(LoginDto login) {
        var result = new Login();
        result.setId(login.getId());
        result.setAccessDate(login.getAccessDate());
        result.setUserId(login.getUserId());
        result.setApplication(login.getApplication());
        return result;
    }
}

package stepup.study.services.impl;

import stepup.study.dto.LoginDto;
import stepup.study.services.DataTransformationService;
import stepup.study.services.ErrorLogWriterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LoginAccessDateTransformService implements DataTransformationService<LoginDto> {
    private final ErrorLogWriterService errorLogWriterService;

    @Override
    public List<LoginDto> transform(List<LoginDto> list, String... args) {
        var result = new ArrayList<LoginDto>();
        for (var login : list) {
            if (login.getAccessDate() != null) {
                result.add(new LoginDto(login.getId(), login.getAccessDate(), login.getUserId(), login.getApplication()));
            } else {
                errorLogWriterService.write(args[0], login.getUserId().toString());
            }
        }
        return result;
    }
}

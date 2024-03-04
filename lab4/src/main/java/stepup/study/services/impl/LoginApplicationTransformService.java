package stepup.study.services.impl;

import stepup.study.dto.LoginDto;
import stepup.study.services.DataTransformationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginApplicationTransformService implements DataTransformationService<LoginDto> {

    public static final String OTHER_APPLICATION = "other:";
    private static final List<String> KNOWN_APPLICATIONS = List.of("web", "mobile");

    @Override
    public List<LoginDto> transform(List<LoginDto> list, String... args) {
        var result = new ArrayList<LoginDto>();
        for (var login : list) {
            String application = login.getApplication();
            if (!KNOWN_APPLICATIONS.contains(application)) {
                application = OTHER_APPLICATION + application;
            }
            result.add(new LoginDto(login.getId(), login.getAccessDate(), login.getUserId(), application));
        }
        return result;
    }
}

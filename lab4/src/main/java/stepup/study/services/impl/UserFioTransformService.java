package stepup.study.services.impl;

import stepup.study.dto.UserDto;
import stepup.study.services.DataTransformationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
public class UserFioTransformService implements DataTransformationService<UserDto> {
    @Override
    public List<UserDto> transform(List<UserDto> list, String... args) {
        var result = new ArrayList<UserDto>();
        for (var user : list) {
            var strings = user.getFio().split(" ");
            StringJoiner sj = new StringJoiner(" ");
            for (int i = 0; i < strings.length; i++) {
                strings[i] = strings[i].substring(0, 1).toUpperCase() + strings[i].substring(1);
                sj.add(strings[i]);
            }
            result.add(new UserDto(user.getId(), user.getUsername(), sj.toString()));
        }
        return result;
    }
}

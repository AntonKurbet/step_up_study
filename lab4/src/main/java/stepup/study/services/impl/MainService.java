package stepup.study.services.impl;

import stepup.study.dto.LoginDto;
import stepup.study.dto.UserDto;
import stepup.study.services.DataTransformationService;
import stepup.study.services.LoginsService;
import stepup.study.services.UsersService;
import stepup.study.utils.Tools;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MainService {
    public static final String USERS_FILE = "users";
    public static final String LOGINS_FILE = "logins";
    public static final String CSV_EXT = ".csv";
    private final UsersService usersService;
    private final LoginsService loginsService;
    private final DataTransformationService<UserDto>[] userDataTransformationServices;
    private final DataTransformationService<LoginDto>[] loginDataTransformationServices;

    public void run(String... args) {
        try {
            var files = Tools.getFiles(Path.of(args[0]), USERS_FILE, CSV_EXT);
            for (var file : files) {
                var users = usersService.readData(String.valueOf(Path.of(file.toFile().getAbsolutePath())));
                var users2 = new ArrayList<>(users);
                for (var service : userDataTransformationServices) {
                    users2 = (ArrayList<UserDto>) service.transform(users2, String.valueOf(file));
                }
                usersService.writeData(users2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            var files = Tools.getFiles(Path.of(args[0]), LOGINS_FILE, CSV_EXT);
            for (var file : files) {
                var logins = loginsService.readData(String.valueOf(Path.of(file.toFile().getAbsolutePath())));
                var logins2 = new ArrayList<>(logins);
                for (var service : loginDataTransformationServices) {
                    logins2 = (ArrayList<LoginDto>) service.transform(logins2, String.valueOf(file));
                }
                loginsService.writeData(logins2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

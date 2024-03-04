package stepup.study;

import stepup.study.dto.LoginDto;
import stepup.study.dto.UserDto;
import stepup.study.repositories.LoginRepository;
import stepup.study.repositories.UserRepository;
import stepup.study.services.DataTransformationService;
import stepup.study.services.LoginsService;
import stepup.study.services.UsersService;
import stepup.study.services.impl.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class StepUpStudy4ApplicationTests {

    private static final String PATH = Paths.get("src", "test", "resources").toAbsolutePath().toString();
    public static final List<UserDto> USER_LIST = List.of(new UserDto(123, "userName", "user name"));
    public static final List<LoginDto> LOGIN_LIST = List.of(new LoginDto(123, LocalDateTime.now(),456, "web"));

    @Autowired
    protected UsersService usersService;
    @Autowired
    protected LoginsService loginsService;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected LoginRepository loginRepository;
    @Autowired
    protected DataTransformationService<UserDto>[] userDataTransformationServices;
    @Autowired
    protected DataTransformationService<LoginDto>[] loginDataTransformationServices;
    @Autowired
    protected MainService mainService;

    @Test
    void contextLoads() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext();
    }

    @Test
    void userServiceReadTest() {
        var users = usersService.readData(PATH + "/data/users_test.csv");
        assertEquals(users.size(), 2);
    }

    @Test
    void userServiceWriteTest() {
        usersService.writeData(USER_LIST);
        assertEquals(userRepository.findById(123).get().getUsername(), "userName");
    }

    @Test
    void userTransformationTest() {
        List<UserDto> users = new ArrayList<>(USER_LIST);
        for (var service : userDataTransformationServices) {
            users = service.transform(users);
        }
        assertEquals(users.get(0).getFio(), "User Name");
    }

    @Test
    void loginServiceReadTest() {
        var logins = loginsService.readData(PATH + "/data/logins_test.csv");
        assertEquals(logins.size(), 4);
    }

    @Test
    void loginServiceWriteTest() {
        loginsService.writeData(LOGIN_LIST);
        assertEquals(loginRepository.findById(123).get().getApplication(), "web");
    }

    @Test
    void loginTransformationTest() {
        List<LoginDto> logins = new ArrayList<>(LOGIN_LIST);
        for (var service : loginDataTransformationServices) {
            logins = service.transform(logins);
        }
        assertEquals(logins.get(0).getApplication(), "web");
    }

    @Test
    void integrationTest() {
        userRepository.deleteAll();
        loginRepository.deleteAll();
        mainService.run(PATH + "/data/");
        assertEquals(userRepository.findAll().size(), 2);
        assertEquals(userRepository.findById(1).get().getFio(), "User1 User1 User1");
        assertEquals(loginRepository.findAll().size(), 3);
        assertEquals(loginRepository.findById(3).get().getApplication(), "other:desktop");
    }
}

package stepup.study.services.impl;

import stepup.study.services.ErrorLogWriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ErrorLogWriterServiceImpl implements ErrorLogWriterService {
    @Override
    public void write(String... message) {
        log.error(String.format("file: %s, userId: %s", message[0], message[1]));
    }
}

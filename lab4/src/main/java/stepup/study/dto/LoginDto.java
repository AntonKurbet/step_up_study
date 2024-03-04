package stepup.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor


@Data
public class LoginDto {
    private Integer id;
    private LocalDateTime accessDate;
    private Integer userId;
    private String application;
}

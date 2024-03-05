package stepup.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor


@Data
public class LoginDto {
    private Integer id;
    private ZonedDateTime accessDate;
    private Integer userId;
    private String application;
}

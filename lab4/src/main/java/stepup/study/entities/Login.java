package stepup.study.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "logins")
@NoArgsConstructor
@Setter
@Getter
public class Login {
    @Id
    private int id;
    @Column(name = "access_date")
    private LocalDateTime accessDate;
    @Column(name = "user_id")
    private int userId;
    private String application;
}

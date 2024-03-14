package inno.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class InstanceCreateResponseDataDto {
    private String instanceId;
    private List<Integer> registerId;
    private List<Integer> supplementaryAgreementId;
}

package inno.tech.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class InstanceCreateResponseDataDto {
    private String instanceId;
    private List<Long> registerId;
    private List<Integer> supplementaryAgreementId;
}

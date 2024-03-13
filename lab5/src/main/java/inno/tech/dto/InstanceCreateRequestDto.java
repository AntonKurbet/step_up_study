package inno.tech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class InstanceCreateRequestDto {

    private Long instanceId;
    @NotBlank
    private String productType;
    @NotBlank
    private String productCode;
    @NotBlank
    private String registerType;
    @NotBlank
    private String mdmCode;
    @NotBlank
    private String contractNumber;
    @NotNull
    private LocalDate contractDate;
    @NotNull
    private Integer priority;
    private BigDecimal interestRatePenalty;
    private BigDecimal minimalBalance;
    private BigDecimal thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private BigDecimal taxPercentageRate;
    private BigDecimal technicalOverdraftLimitAmount;
    @NotNull
    private Long contractId;
    @NotBlank
    private String BranchCode;
    @NotBlank
    private String isoCurrencyCode;
    @NotBlank
    private String urgencyCode;
    private Integer ReferenceCode;
}

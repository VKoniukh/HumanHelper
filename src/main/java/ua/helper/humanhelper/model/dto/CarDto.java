package ua.helper.humanhelper.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CarDto {

    private BigDecimal price;

    private String routeFrom;

    private String routeTo;

    private String description;

    private LocalDate Date;
}

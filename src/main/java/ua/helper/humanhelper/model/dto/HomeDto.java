package ua.helper.humanhelper.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HomeDto {

    private BigDecimal price;

    private String location;

    private int sleepPlace;

    private String description;
}

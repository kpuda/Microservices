package com.kp.order.service.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ItemsDto {
    private Long id;
    private String rfidCode;
    private BigDecimal price;
    private Integer quantity;
}

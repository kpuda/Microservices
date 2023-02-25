package com.kp.order.service.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderRequest {
    private long userId;
    private List<ItemsDto> orderList;
}

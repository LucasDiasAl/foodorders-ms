package com.dias.orders.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.dias.orders.model.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StatusDTO {
    private Status status;
}

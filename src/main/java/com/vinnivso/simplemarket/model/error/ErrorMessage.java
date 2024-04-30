package com.vinnivso.simplemarket.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private String title;

    private Integer status;

    private String message;
}

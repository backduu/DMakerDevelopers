package com.fastcampus.programming.dmaker.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    EMPLOYED("재직"),
    RETIRED("퇴직"),
    PAID_VACATION("유급 휴가"),
    UNPAID_VACATION("무급 휴가");

    private final String description;
}

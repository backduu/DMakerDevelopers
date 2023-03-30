package com.fastcampus.programming.dmaker.type;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeveloperLevel {
    NEW("신입 개발자"),   // 1년 ~ 2년
    JUNIOR("초급 개발자"), // 2년 ~ 4년
    INTERMEDIATE("중급 개발자"), // 4년 ~ 8년
    SENIOR("고급 개발자"), // 8년 ~ 12년
    EXPERT("특급 개발자"); // 12년 ~

    private final String description;
}

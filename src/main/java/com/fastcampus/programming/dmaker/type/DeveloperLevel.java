package com.fastcampus.programming.dmaker.type;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeveloperLevel {
    NEW("신입 개발자"),
    JUNIOR("초급 개발자"),
    INTERMEDIATE("중급 개발자"),
    SENIOR("고급 개발자"),
    EXPERT("특급 개발자");

    private final String description;
}

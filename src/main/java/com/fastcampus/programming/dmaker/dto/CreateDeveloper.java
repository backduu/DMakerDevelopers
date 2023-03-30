package com.fastcampus.programming.dmaker.dto;

import com.fastcampus.programming.dmaker.type.DeveloperLevel;
import com.fastcampus.programming.dmaker.type.DeveloperSkillType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class CreateDeveloper {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotNull
        private DeveloperLevel developerLevel;
        @NotNull
        private DeveloperSkillType developerSkillType;
        @NotNull
        @Min(1)
        @Max(30)
        private Integer experienceYears;
        @NotNull
        @Size(min = 3, max = 50, message = "[SYSTEM]member ID size must 3 ~ 50.")
        private String memberId;
        @NotNull
        @Size(min = 3, max = 20, message = "[SYSTEM] name size must 3 ~ 20")
        private String name;
        @Min(20)
        @Max(90)
        private Integer age;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Response {
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYears;
        private String memberId;
    }
}

package com.fastcampus.programming.dmaker.dto;

import com.fastcampus.programming.dmaker.entity.Developer;
import com.fastcampus.programming.dmaker.type.DeveloperLevel;
import com.fastcampus.programming.dmaker.type.DeveloperSkillType;
import jakarta.validation.constraints.*;
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

        @NotNull
        @Pattern(regexp="female|male",
                message = "[SYSTEM] sex will be input as \"male\" or \"female\"")
        private String sex;

        @Size(min = 0, max = 150, message = "[SYSTEM] spec size is 0 ~ 150")
        private String spec;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYears;
        private String memberId;

        public static Response fromEntity(
                @NonNull Developer developer
        ) {
            return Response.builder()
                    .developerLevel(developer.getDeveloperLevel())
                    .developerSkillType(developer.getDeveloperSkillType())
                    .experienceYears(developer.getExperienceYears())
                    .memberId(developer.getMemberId())
                    .build();
        }
    }
}

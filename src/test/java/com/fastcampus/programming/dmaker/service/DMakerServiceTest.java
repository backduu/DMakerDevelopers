package com.fastcampus.programming.dmaker.service;

import com.fastcampus.programming.dmaker.dto.CreateDeveloper;
import com.fastcampus.programming.dmaker.dto.DeveloperDto;
import com.fastcampus.programming.dmaker.type.DeveloperLevel;
import com.fastcampus.programming.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DMakerServiceTest {
    @Autowired
    private DMakerService dMakerService;
    @Test
    public void testSomething() {
//        String result = "hello world";
//        assertEquals("hello world", result);
        dMakerService.createDeveloper(
                CreateDeveloper.Request.builder()
                        .developerLevel(DeveloperLevel.NEW)
                        .developerSkillType(DeveloperSkillType.FRONT_END)
                        .age(25)
                        .experienceYears(1)
                        .memberId("testMemberId")
                        .name("testName")
                        .build()
        );

        List<DeveloperDto> allEmployedDevelopers = dMakerService.getAllEmployedDevelopers();
        System.out.println(allEmployedDevelopers);
    }
}
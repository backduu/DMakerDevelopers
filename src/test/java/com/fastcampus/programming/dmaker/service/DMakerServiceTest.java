package com.fastcampus.programming.dmaker.service;

import com.fastcampus.programming.dmaker.Repository.DeveloperRepository;
import com.fastcampus.programming.dmaker.Repository.RetiredDeveloperRepository;
import com.fastcampus.programming.dmaker.code.StatusCode;
import com.fastcampus.programming.dmaker.dto.CreateDeveloper;
import com.fastcampus.programming.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.programming.dmaker.dto.DeveloperDto;
import com.fastcampus.programming.dmaker.entity.Developer;
import com.fastcampus.programming.dmaker.type.DeveloperLevel;
import com.fastcampus.programming.dmaker.type.DeveloperSkillType;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {
    @Mock
    private DeveloperRepository developerRepository;
    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    @InjectMocks
    private DMakerService dMakerService;
    @Test
    public void testSomething() {
        // 1. @Mock들의 동작을 정의
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                                .developerLevel(DeveloperLevel.NEW)
                                .developerSkillType(DeveloperSkillType.FRONT_END)
                                .experienceYears(1)
                                .name("test")
                                .sex("female")
                                .age(22)
                                .spec("1 project")
                                .statusCode(StatusCode.EMPLOYED)
                                .build()
                ));

        DeveloperDetailDto developerDetailDto = dMakerService.getDeveloperDetail("memberId");

        assertEquals(DeveloperLevel.NEW, developerDetailDto.getDeveloperLevel());
        assertEquals(DeveloperSkillType.BACK_END, developerDetailDto.getDeveloperSkillType());
        assertEquals(2, developerDetailDto.getExperienceYears());
    }

    @Test
    void createDeveloperTest_success() {
        // given
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                .developerLevel(DeveloperLevel.NEW)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYears(1)
                .name("test")
                .sex("female")
                .age(22)
                .spec("1 project")
                .memberId("memberId")
                .build();

        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());

        ArgumentCaptor<Developer> captor = ArgumentCaptor.forClass(Developer.class);

        // when
        dMakerService.createDeveloper(request);

        // then
        // repository에 넘어갈때 save하는 파라미터를 캡처한다.
        verify(developerRepository, times(1)).save(captor.capture());


    }
}
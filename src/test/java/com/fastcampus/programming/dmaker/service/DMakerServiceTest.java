package com.fastcampus.programming.dmaker.service;

import com.fastcampus.programming.dmaker.Repository.DeveloperRepository;
import com.fastcampus.programming.dmaker.Repository.RetiredDeveloperRepository;
import com.fastcampus.programming.dmaker.code.StatusCode;
import com.fastcampus.programming.dmaker.dto.CreateDeveloper;
import com.fastcampus.programming.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.programming.dmaker.entity.Developer;
import com.fastcampus.programming.dmaker.exception.DMakerErrorCode;
import com.fastcampus.programming.dmaker.exception.DMakerException;
import com.fastcampus.programming.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.fastcampus.programming.dmaker.type.DeveloperLevel.NEW;
import static com.fastcampus.programming.dmaker.type.DeveloperSkillType.FRONT_END;
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
    private final Developer defaultDeveloper = Developer.builder()
            .developerLevel(NEW)
            .developerSkillType(FRONT_END)
            .experienceYears(1)
            .name("test")
            .sex("female")
            .age(22)
            .spec("1 project")
            .statusCode(StatusCode.EMPLOYED)
            .build();

    private final CreateDeveloper.Request defaultCreateRequest = CreateDeveloper.Request.builder()
            .developerLevel(NEW)
            .developerSkillType(FRONT_END)
            .experienceYears(1)
            .name("test")
            .sex("female")
            .age(22)
            .spec("1 project")
            .memberId("memberId")
            .build();

    @Test
    public void testSomething() {

        // 1. @Mock들의 동작을 정의
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultDeveloper));

        DeveloperDetailDto developerDetailDto = dMakerService.getDeveloperDetail("memberId");

        assertEquals(NEW, developerDetailDto.getDeveloperLevel());
        assertEquals(DeveloperSkillType.BACK_END, developerDetailDto.getDeveloperSkillType());
        assertEquals(2, developerDetailDto.getExperienceYears());
    }

    @Test
    void createDeveloperTest_success() {
        // given
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());

        ArgumentCaptor<Developer> captor = ArgumentCaptor.forClass(Developer.class);

        // when
        dMakerService.createDeveloper(defaultCreateRequest);

        // then
        // repository에 넘어갈때 save하는 파라미터를 캡처한다.
        verify(developerRepository, times(1)).save(captor.capture());
        Developer saveDeveloper = captor.getValue();
        assertEquals(NEW, saveDeveloper.getDeveloperLevel());
        assertEquals(FRONT_END, saveDeveloper.getDeveloperSkillType());
        assertEquals(1, saveDeveloper.getExperienceYears());
    }

    @Test
    void createDeveloperTest_failed_with_duplicated() {
        // given
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultDeveloper));

        // when
        // then
        DMakerException dMakerException = assertThrows(DMakerException.class,
                () -> dMakerService.createDeveloper(defaultCreateRequest));

        assertEquals(DMakerErrorCode.DUPLICATED_MEMBER_ID, dMakerException.getDMakerErrorCode());
    }
}
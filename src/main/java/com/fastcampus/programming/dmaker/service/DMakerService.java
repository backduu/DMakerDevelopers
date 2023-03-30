package com.fastcampus.programming.dmaker.service;

import com.fastcampus.programming.dmaker.Repository.DeveloperRepository;
import com.fastcampus.programming.dmaker.dto.CreateDeveloper;
import com.fastcampus.programming.dmaker.entity.Developer;
import com.fastcampus.programming.dmaker.exception.DMakerErrorCode;
import com.fastcampus.programming.dmaker.exception.DMakerException;
import com.fastcampus.programming.dmaker.type.DeveloperLevel;
import com.fastcampus.programming.dmaker.type.DeveloperSkillType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fastcampus.programming.dmaker.exception.DMakerErrorCode.*;

@Service
@RequiredArgsConstructor
// RequiredArgsConstructor를 쓰는 이유는 기존에 DI를 하려면
// 생성자 주입 또는 Setter 주입 방식을 써야 했는데( 또는 Injection, AutoWired를 썼었다.)
// repository가
// 많아지면 생성자 및 Setter 추가가 더 많아져서 이를 방지하고자
// RequiredArgsConstructor를 사용하였다.
public class DMakerService {
    private final DeveloperRepository developerRepository;

    @Transactional
    public void createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.NEW)
                .developerSkillLevel(DeveloperSkillType.FRONT_END)
                .experienceYears(1)
                .age(26)
                .name("Nasus")
                .build();

        developerRepository.save(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        if(request.getExperienceYears() <= 0)
            throw new DMakerException(WRONG_VALUE);
        else if(request.getDeveloperLevel() == DeveloperLevel.NEW &&
                (request.getExperienceYears() < 1 || request.getExperienceYears() > 2))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        else if(request.getDeveloperLevel() == DeveloperLevel.JUNIOR &&
                (request.getExperienceYears() < 2 || request.getExperienceYears() > 4))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        else if(request.getDeveloperLevel() == DeveloperLevel.INTERMEDIATE &&
                (request.getExperienceYears() < 4 || request.getExperienceYears() > 8))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        else if(request.getDeveloperLevel() == DeveloperLevel.SENIOR &&
                (request.getExperienceYears() < 8 || request.getExperienceYears() > 12))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        else if(request.getDeveloperLevel() == DeveloperLevel.EXPERT &&
                request.getExperienceYears() < 12)
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);


//        Optional<Developer> developerOpetional = developerRepository.findByMemberId(request.getMemberId());
//        if(developerOpetional.isPresent()) throw new DMakerException(DUPLICATED_MEMBER_ID);
        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {throw new DMakerException(DUPLICATED_MEMBER_ID);}));

    }
}

package com.fastcampus.programming.dmaker.service;

import com.fastcampus.programming.dmaker.Repository.DeveloperRepository;
import com.fastcampus.programming.dmaker.dto.CreateDeveloper;
import com.fastcampus.programming.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.programming.dmaker.dto.DeveloperDto;
import com.fastcampus.programming.dmaker.dto.EditDeveloper;
import com.fastcampus.programming.dmaker.entity.Developer;
import com.fastcampus.programming.dmaker.exception.DMakerErrorCode;
import com.fastcampus.programming.dmaker.exception.DMakerException;
import com.fastcampus.programming.dmaker.type.DeveloperLevel;
import com.fastcampus.programming.dmaker.type.DeveloperSkillType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .age(request.getAge())
                .name(request.getName())
                .memberId(request.getMemberId())
                .sex(request.getSex())
                .spec(request.getSpec())
                .build();

        developerRepository.save(developer);

        return CreateDeveloper.Response.fromEntity(developer);
    }

    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAll()
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        validateEditDeveloperRequest(request, memberId);

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(
                        () -> new DMakerException(NO_DEVELOPER)
                );

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());
        developer.setSpec(request.getSpec());
        developer.setAge(request.getAge());

        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        validateDeveloperLevel(request.getExperienceYears(), request.getDeveloperLevel());


//        Optional<Developer> developerOpetional = developerRepository.findByMemberId(request.getMemberId());
//        if(developerOpetional.isPresent()) throw new DMakerException(DUPLICATED_MEMBER_ID);
        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {throw new DMakerException(DUPLICATED_MEMBER_ID);}));

    }

    private void validateEditDeveloperRequest(EditDeveloper.Request request, String memberId) {
        validateDeveloperLevel(request.getExperienceYears(), request.getDeveloperLevel());

    }

    private static void validateDeveloperLevel(Integer experienceYears, DeveloperLevel developerLevel) {
        if (experienceYears <= 0)
            throw new DMakerException(WRONG_VALUE);
        else if (developerLevel == DeveloperLevel.NEW &&
                (experienceYears < 1 || experienceYears > 2))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        else if (developerLevel == DeveloperLevel.JUNIOR &&
                (experienceYears < 2 || experienceYears > 4))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        else if (developerLevel == DeveloperLevel.INTERMEDIATE &&
                (experienceYears < 4 || experienceYears > 8))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        else if (developerLevel == DeveloperLevel.SENIOR &&
                (experienceYears < 8 || experienceYears > 12))
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        else if (developerLevel == DeveloperLevel.EXPERT &&
                experienceYears < 12)
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
    }
}

package com.fastcampus.programming.dmaker.service;

import com.fastcampus.programming.dmaker.Repository.DeveloperRepository;
import com.fastcampus.programming.dmaker.entity.Developer;
import com.fastcampus.programming.dmaker.type.DeveloperLevel;
import com.fastcampus.programming.dmaker.type.DeveloperSkillType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void createDeveloper() {
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.NEW)
                .developerSkillLevel(DeveloperSkillType.FRONT_END)
                .experienceYears(1)
                .age(26)
                .name("Nasus")
                .build();

        developerRepository.save(developer);
    }
}

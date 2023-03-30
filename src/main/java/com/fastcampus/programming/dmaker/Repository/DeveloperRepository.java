package com.fastcampus.programming.dmaker.Repository;

import com.fastcampus.programming.dmaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository
 extends JpaRepository<Developer, Long> {
    // Optional<T> : T가 있을 수도 있고 없을 수도 있다.
    Optional<Developer> findByMemberId(String memberId);  // 메소드 명만 가지고도 특정 컬럼명으로 검색을 할 수 있다. "Spring data JPA"
    //void findByMemberId(String memberId);


}

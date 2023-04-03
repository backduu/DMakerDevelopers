package com.fastcampus.programming.dmaker.Repository;

import com.fastcampus.programming.dmaker.code.StatusCode;
import com.fastcampus.programming.dmaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository
 extends JpaRepository<Developer, Long> {
    // Optional<T> : T가 있을 수도 있고 없을 수도 있다.
    Optional<Developer> findByMemberId(String memberId);  // 메소드 명만 가지고도 특정 컬럼명으로 검색을 할 수 있다. "Spring data JPA"
    // Optional은 map 메소드와 호환이 되므로 fromEntity를 들고와서 이를 get해준다. (get보단 orElseThrow가 좋음)
    //void findByMemberId(String memberId);

    List<Developer> findDevelopersByStatusCodeEquals(StatusCode statusCode);
    //
}

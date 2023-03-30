package com.fastcampus.programming.dmaker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum DMakerErrorCode {

    WRONG_VALUE("[DMakerApplication] 값이 잘못되었습니다.")
    , INVALID_REQUEST("[DMakerApplication] 잘못된 요청입니다.")
    , INTERNAL_SERVER_ERROR("[DMakerApplication] 서버에 오류가 발생했습니다.")
    , DUPLICATED_MEMBER_ID("[DMakerApplication] MEMBER ID가 중복되는 개발자가 있습니다.")
    , NO_DEVELOPER("[DMakerApplication] 해당하는 개발자가 없습니다.")
    , LEVEL_EXPERIENCE_YEARS_NOT_MATCHED("[DMakerApplication] 개발자 레벨과 연차가 맞지 않습니다.");

    private final String message;
}

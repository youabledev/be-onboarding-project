package com.metsakurr.beonboardingproject.domain.survey.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SurveyRequest {
    private long idx;

    @NotBlank(message = "name[설문조사 이름]은 필수 값입니다.")
    private String name;

    @NotBlank(message = "description[설문조사 설명]은 필수 값입니다.")
    private String description;

    @Valid
    private List<QuestionRequest> questions;

    @AssertTrue(message = "questions[설문 받을 항목]은 1개 이상, 10개 이하여야 합니다.")
    public boolean isValidQuestions() {
        if (questions == null) {
            return false;
        }

        if (questions.isEmpty()) {
            return false;
        }

        return questions.size() <= 10;
    }

    @Builder
    protected SurveyRequest(
            long idx,
            String name,
            String description,
            List<QuestionRequest> questions
    ) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.questions = questions;
    }
}

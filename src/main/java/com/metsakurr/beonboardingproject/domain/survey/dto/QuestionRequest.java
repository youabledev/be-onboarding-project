package com.metsakurr.beonboardingproject.domain.survey.dto;

import com.metsakurr.beonboardingproject.common.validation.ValidQuestionType;
import com.metsakurr.beonboardingproject.domain.survey.entity.QuestionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionRequest {
    private Long idx;

    @NotBlank(message = "name[항목 이름]은 필수 값입니다.")
    private String name;

    @NotBlank(message = "description[항목 설명]은 필수 값입니다.")
    private String description;

    @ValidQuestionType
    private String questionType;

    @NotNull(message = "isRequired[항목 필수 여부]는 필수 값입니다.")
    private Boolean isRequired;

    @Valid
    private List<String> options;

    @AssertTrue(message = "[단일 선택 리스트], [다중 선택 리스트]의 경우 선택 할 수 있는 후보 값이 필요합니다.")
    public boolean isValidOptions() {
        try {
            QuestionType type = QuestionType.valueOf(questionType);
            if (QuestionType.SINGLE_CHOICE.equals(type) || QuestionType.MULTI_CHOICE.equals(type)) {
                if (options == null) {
                    return false;
                }

                return !options.isEmpty();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean getIsRequired() {
        return this.isRequired;
    }

    @Builder
    protected QuestionRequest(
            Long idx,
            String name,
            String description,
            String questionType,
            Boolean isRequired,
            List<String> options
    ) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.questionType = questionType;
        this.isRequired = isRequired;
        this.options = options;
    }
}
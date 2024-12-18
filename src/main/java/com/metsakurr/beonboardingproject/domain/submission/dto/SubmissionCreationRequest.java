package com.metsakurr.beonboardingproject.domain.submission.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubmissionCreationRequest {
    @Min(value = 1, message = "유효한 idx[응답할 설문 조사 식별자]가 필요합니다.")
    private int idx;

    @Valid
    private List<AnswerRequest> answers;

    @Getter
    public static class AnswerRequest {
        @Min(value = 1, message = "유효한 idx[항목 식별자]가 필요합니다.")
        private int idx;

        private String answer;

        @Builder
        private AnswerRequest(
                int idx,
                String answer
        ) {
            this.idx = idx;
            this.answer = answer;
        }
    }

    @Builder
    private SubmissionCreationRequest(
            int idx,
            List<AnswerRequest> answers
    ) {
        this.idx = idx;
        this.answers = answers;
    }
}

package com.avenga.a360.service;

import com.avenga.a360.dto.AnswerDto;
import com.avenga.a360.dto.AnswerSessionDto;
import com.avenga.a360.model.Answer;
import com.avenga.a360.model.response.Status;

import java.util.List;

public interface AnswerService {

    List<Answer> findAllAnswersByParticipantId(Long id);

    boolean createAnswer(AnswerDto answerDto);

    //    public List<AnswerDto> answerToAnswerDto(List<Answer> answerList) {
    //        List<AnswerDto> answerDtoList = new ArrayList<>();
    //        for (Answer answer : answerList) {
    //            AnswerDto answerDto = new AnswerDto();
    //            answerDto.setQuestionId(answer.getQuestion().getId());
    //            answerDto.setParticipantId(answer.getParticipant().getId());
    //            answerDto.setAnswerText(answer.getAnswerText());
    //            answerDtoList.add(answerDto);
    //        }
    //
    //        return answerDtoList;
    //    }
    List<AnswerSessionDto> amountOfAnswersForSessionActive();
}

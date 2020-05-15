package com.avenga.a360.dao;

import com.avenga.a360.model.Answer;
import com.avenga.a360.model.Question;

import java.util.List;

public interface AnswerDao {
    List<Answer> findAllAnswersByParticipantId(Long id);

    List<Answer> findAllAnswersForOneSession(String name);

    boolean createAnswer(Answer answer);

    List<Question> getAllQuestionsBySessionId(Long id);
}

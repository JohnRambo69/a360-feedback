package com.avenga.a360.service.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dto.QuestionDto;
import com.avenga.a360.model.Question;
import com.avenga.a360.service.QuestionService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class QuestionServiceImpl implements QuestionService {

    @Inject
    QuestionDao questionDao;

    @Override
    public List<Question> findAllActiveQuestions() {
        return questionDao.findAllActiveQuestions();

    }

    @Override
    public Question findQuestionsById(Long id) {
        return questionDao.findById(id);

    }

    @Override
    public List<Question> findAllQuestionsTextAndIdByParticipantId(Long id) {
        return questionDao.findAllQuestionsTextAndIdByParticipantId(id);
    }

    @Override
    public boolean createQuestion(Question question) {
        if (question == null) {
            return false;
        } else {
            if (question.getDefaultAnswers() == null || question.getQuestionText() == null ||
                    question.getQuestionType() == null) {
                return false;
            }
        }
        questionDao.createQuestion(question);
        return true;
    }

    @Override
    public List<Question> getAllQuestionBySessionId(Long id) {
        return questionDao.getAllQuestionBySessionId(id);
    }


    @Override
    public Question questionDtoToQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setDefaultAnswers(questionDto.getDefault_answers());
        question.setIsActive(questionDto.getIs_active());
        question.setQuestionText(questionDto.getQuestion_text());
        question.setQuestionType(questionDto.getQuestion_type());
        return question;
    }

    @Override
    public List<QuestionDto> questionListToQuestionDtoList(List<Question> questionList) {
        List<QuestionDto> questionDtoList = questionList.stream()
                .map(question -> questionToQuestionDto(question))
                .collect(Collectors.toList());
        return questionDtoList;
    }

    @Override
    public List<Question> questionListDtoToQuestionList(List<QuestionDto> questionListDto) {
        List<Question> questionList = questionListDto.stream()
                .map(question -> questionDtoToQuestion(question))
                .collect(Collectors.toList());
        return questionList;
    }

    @Override
    public QuestionDto questionToQuestionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setDefault_answers(question.getDefaultAnswers());
        questionDto.setQuestion_id(question.getId());
        questionDto.setIs_active(question.getIsActive());
        questionDto.setQuestion_text(question.getQuestionText());
        questionDto.setQuestion_type(question.getQuestionType());

        return questionDto;
    }

}

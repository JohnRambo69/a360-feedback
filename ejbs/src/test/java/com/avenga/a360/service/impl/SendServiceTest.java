/*
package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dto.AnswerDto;
import com.avenga.a360.model.*;
import com.avenga.a360.service.EmailService;
import com.avenga.a360.service.SendService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SendServiceTest {

*/
/*    @Inject
    EmailService emailService;

    @Inject
    SendService sendService;
    *//*


    @Test
    public void shouldCreateAnswerWithAllValidParameters() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        Session session = new Session();
        session.setSessionName("Session name");
        session.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));
        session.setIsSent(false);
        session.setQuestions(questions);

        List<Participant> participantList = new ArrayList<>();
        Participant participant = new Participant();
        participant.setEmail("lukasz.stepien@avenga.com");
        participant.setSession(session);
        participant.setUId("12addadwadd");
        participantList.add(participant);

        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerText("Answer");
        answerDto.setQuestionId(1L);
        answerDto.setParticipantId(1L);

        EmailService emailService = new EmailServiceImpl();
        SendService sendService = new SendServiceImpl();

        sendService.changeSmtpProperties("poczta.o2.pl", 465, "maziak01@o2.pl", "Redrum666");
        List<Email> emailList = emailService.createEmailsToParticipantsWithLinks(participantList, session.getSessionName());
        boolean isSent = sendService.sendEmail(emailList.get(0));

        Assert.assertEquals(isSent, true);
    }
    }


*/
/*
        Assert.assertEquals(answerService.createAnswer(answerDto).getStatus(), "success");
        answerService.createAnswer(answerDto).getStatusMessageList().forEach(r -> System.out.println(r.getMessage()));
    }

    @Test
    public void shouldNotCreateAnswerWithNullQuestion() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        Session session = new Session();
        session.setSessionName("Session name");
        session.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));
        session.setIsSent(false);
        session.setQuestions(questions);

        Participant participant = new Participant();
        participant.setEmail("a@a.com");
        participant.setSession(session);

        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerText("Answer");
        answerDto.setQuestionId(1L);
        answerDto.setParticipantId(1L);


        Mockito.when(questionDao.findById(answerDto.getQuestionId())).thenReturn(null);
        Mockito.when(participantDao.findById(answerDto.getParticipantId())).thenReturn(participant);


        Assert.assertEquals(answerService.createAnswer(answerDto).getStatus(), "fail");
        answerService.createAnswer(answerDto).getStatusMessageList().forEach(r -> System.out.println(r.getMessage()));
    }

    @Test
    public void shouldNotCreateAnswerWithNullAnswerText() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        Session session = new Session();
        session.setSessionName("Session name");
        session.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));
        session.setIsSent(false);
        session.setQuestions(questions);

        Participant participant = new Participant();
        participant.setEmail("a@a.com");
        participant.setSession(session);

        AnswerDto answerDto = new AnswerDto();
//        answerDto.setAnswerText("answerDto answer");
        answerDto.setQuestionId(1L);
        answerDto.setParticipantId(1L);


        Mockito.when(questionDao.findById(answerDto.getQuestionId())).thenReturn(question);
        Mockito.when(participantDao.findById(answerDto.getParticipantId())).thenReturn(participant);


        Assert.assertEquals(answerService.createAnswer(answerDto).getStatus(), "fail");
        answerService.createAnswer(answerDto).getStatusMessageList().forEach(r -> System.out.println(r.getMessage()));
    }

    @Test
    public void shouldFindAllAnswersByParticipantId() {
        List<Participant> participants = new ArrayList<>();
        Participant participant = new Participant();
        participant.setId(1L);
        participant.setEmail("a@a.com");
        participants.add(participant);

        Session session = new Session();
        session.setId(1L);
        session.setParticipants(participants);
        session.setSessionName("Session name");
        session.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));
        session.setIsSent(false);

        Question question = new Question();
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);

        List<Answer> answers = new ArrayList<>();
        Answer answer = new Answer();
        answer.setAnswerText("Answer");
        answer.setParticipant(participant);
        answer.setQuestion(question);
        answers.add(answer);

        Mockito.when(answerDao.findAllAnswersByParticipantId(participant.getId())).thenReturn(answers);

        Assert.assertEquals(answerService.findAllAnswersByParticipantId(participant.getId()).get(0).getAnswerText(), answers.get(0).getAnswerText());
    }
}*//*


*/

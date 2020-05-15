package com.avenga.a360.service;

import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Question;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.response.Status;

import java.util.List;

public interface SessionService {


    Status createSession(SessionDto sessionDto, List<ParticipantDto> participantsDto, List<Question> questionList);

    List<Session> findAllSessionsIsSentFalseAndEndDateIsAfterNow();
    List<SessionDto> sessionListToSessionDtoList(List<Session> sessionList);
    List<SessionDto> findAllSessionsWhereIsSentFalse();

    List<SessionDto> getAllSessions();

    boolean updateSession(Long id, Boolean freeze);
}

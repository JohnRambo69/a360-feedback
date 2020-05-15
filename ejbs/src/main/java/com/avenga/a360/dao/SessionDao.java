package com.avenga.a360.dao;

import com.avenga.a360.model.Session;

import java.util.List;

public interface SessionDao {

    List<Session> findAllSessionsIsSentFalseAndEndDateIsAfterNow();
    List<Session> findAllSessionsWhereIsSentFalse();

    boolean createSession(Session session);

    Session findSessionById(Long id);

    List<Session> getAllSessions();

    boolean updateSession(Long id, Boolean freeze);
}

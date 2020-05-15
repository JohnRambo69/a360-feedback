package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SessionDaoImpl implements SessionDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    @Override
    public List<Session> findAllSessionsIsSentFalseAndEndDateIsAfterNow() {

        List<Session> sessions = em.createNamedQuery("Session.findAllSessionsIsSentFalseAndEndDateIsAfterNow", Session.class)
                .getResultList();
        return sessions;
    }

    @Override
    public List<Session> findAllSessionsWhereIsSentFalse() {

        List<Session> sessions = em.createNamedQuery("Session.findAllSessionsWhereIsSentFalse", Session.class)
                .getResultList();
        return sessions;
    }

    @Override
    public boolean createSession(Session session) {
        try {
            em.persist(session);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Session findSessionById(Long id) {
        Session session = null;
        try {
            session = em.createNamedQuery("Session.findById", Session.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
        }
        return session;
    }


    @Override
    public List<Session> getAllSessions() {
        List<Session> sessionList = em.createNamedQuery("Session.getAllSessions", Session.class)
                .getResultList();
        return sessionList;
    }

    @Override
    public boolean updateSession(Long id, Boolean freeze) {
        try {
            Session session = null;
            session = findSessionById(id);
            session.setIsFreeze(freeze);
            em.persist(session);
            return true;
        } catch (
                Exception e) {
            return false;
        }
    }
}

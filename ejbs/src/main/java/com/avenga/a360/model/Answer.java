package com.avenga.a360.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Answer.shouldFindAllAnswersByParticipantId",
                query = "SELECT a.* FROM participants p left join answers a on p.id = a.id_participant where p.id = :id",
                resultClass = Answer.class),
        @NamedNativeQuery(name = "Answer.shouldReturnAnswersInSessions",
                query = "SELECT a.id_participant,a.id_question FROM answers a \n" +
                        " join participants p on p.id = a.id_participant \n" +
                        " join sessions s on s.id = p.id_session ",

                resultClass = Answer.class),
        @NamedNativeQuery(name = "Answer.shouldFindAllAnswersForOneSession",
                query = "SELECT answers.* from participants left join sessions  on sessions.id = participants.id_session" +
                        " right join answers on answers.id_participant = participants.id where sessions.session_name=:name " ,
                resultClass = Answer.class)
                }

)
public class Answer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_text")
    private String answerText;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_question")
    private Question question;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_participant")
    private Participant participant;

}

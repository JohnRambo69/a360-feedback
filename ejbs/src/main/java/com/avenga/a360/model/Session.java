package com.avenga.a360.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Session.findAllSessionsIsSentFalseAndEndDateIsAfterNow",
                query = "select * from sessions where now() > end_date and is_sent = false",
                resultClass = Session.class
        ),
        @NamedNativeQuery(
                name = "Session.findAllSessionsWhereIsSentFalse",
                query = "select * from sessions where is_sent = false",
                resultClass = Session.class
        )}
)
@NamedNativeQuery(
        name = "Session.getAllSessions",
        query = "select * from sessions",
        resultClass = Session.class
)
@NamedQueries({
        @NamedQuery(name = "Session.findById", query = "SELECT s FROM Session s WHERE s.id = :id"),
})
public class Session implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_name", unique = true)
    @NonNull
    private String sessionName;

    @NonNull
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_sent", columnDefinition = "Boolean default 'false'", nullable = false)
    private Boolean isSent;

    @Column(name = "is_freeze", columnDefinition = "Boolean default 'false'", nullable = false)
    private Boolean isFreeze;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "sessions_questions", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_session", "id_question"}),},
            joinColumns = {@JoinColumn(name = "id_session")}, inverseJoinColumns = {
            @JoinColumn(name = "id_question")})
    private List<Question> questions;

    @OneToMany(mappedBy = "session", cascade = {CascadeType.ALL})
    private List<Participant> participants;
}

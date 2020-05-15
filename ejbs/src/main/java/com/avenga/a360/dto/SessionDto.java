package com.avenga.a360.dto;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.model.Participant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;
@XmlRootElement
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {
    private Long id;
    private String sessionName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDate;
    private Boolean isSent;
    private Boolean isFreeze;
    private List<ParticipantDto> participantList;
    private List<QuestionDto> questionList;
}

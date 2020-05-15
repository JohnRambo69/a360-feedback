insert into sessions (session_name, end_date, is_sent) values('sesja1', '1970-01-01 00:00:01', 'f')
insert into sessions (session_name, end_date, is_sent) values('sesja2', '1999-01-01 00:00:01', 'f')

insert into questions (question_text, question_type, default_answers) values ('What do you value him for?', 'TEXT', '...input text')
insert into questions (question_text, question_type, default_answers) values ('What is he or she doing wrong?', 'TEXT', '...input text')
insert into questions (question_text, question_type, default_answers) values ('What can change to make working with him or her better?', 'TEXT', '...input text')
insert into questions (question_text, question_type, default_answers) values ('How this person affects atmosphere in the team?', 'RADIO', 'Positive;Negative;Neutral')

insert into participants (email, id_session, uid) values ('asia.zdz@gmail.com', 1, '12')
insert into participants (email, id_session, uid) values ('gor@wp.pl', 1, '123')
insert into participants (email, id_session, uid) values ('sth@onet.pl', 1, '124')
insert into participants (email, id_session, uid) values ('tyu@gmail.com', 2, '1245')
insert into participants (email, id_session, uid) values ('df@gmail.com', 2, '127')
insert into participants (email, id_session, uid) values ('warh@gmail.com', 2, '1233')
insert into participants (email, id_session, uid) values ('fhg@op.pl', 2, '127855')

insert into answers (answer_text, id_question, id_participant) values ('odpowiedz 1', 1,1)
insert into answers (answer_text, id_question, id_participant) values ('odpowiedz 2', 2,1)
insert into answers (answer_text, id_question, id_participant) values ('odpowiedz 3', 3,1)
insert into answers (answer_text, id_question, id_participant) values ('odpowiedz 3 ale znow wysłana', 3,1)
insert into answers (answer_text, id_question, id_participant) values ('odpowiedz 1 dhtjhh', 1,2)
insert into answers (answer_text, id_question, id_participant) values ('odpowiedz 3 jxdhy', 1,6)

insert into sessions_questions (id_session, id_question) values (1, 1)
insert into sessions_questions (id_session, id_question) values (1, 2)
insert into sessions_questions (id_session, id_question) values (1, 3)
insert into sessions_questions (id_session, id_question) values (1,4)
insert into sessions_questions (id_session, id_question) values (2, 1)
insert into sessions_questions (id_session, id_question) values (2, 2)
insert into sessions_questions (id_session, id_question) values (2, 3)
insert into sessions_questions (id_session, id_question) values (2, 4)
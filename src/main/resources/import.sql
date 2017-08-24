INSERT INTO role VALUES (1, 'ADMIN');
INSERT INTO role VALUES (2, 'USER');
INSERT INTO user VALUES (1, 'bbq@naver.com', '$2a$10$DYc6PSDWbucC2/kWzl/2XeNCzz4eVl7fmHj0uaYoDu6JfHV.Mr8IS', 'bbq');
INSERT INTO user VALUES (2, 'asdf@asdf.com', '$2a$10$DYc6PSDWbucC2/kWzl/2XeNCzz4eVl7fmHj0uaYoDu6JfHV.Mr8IS', 'asdf');

INSERT INTO user_role VALUES (1, 1);
INSERT INTO user_role VALUES (1, 2);
INSERT INTO user_role VALUES (2, 2);


CREATE TABLE A0_RESPONSE(
    A0_RESPONSE_ID BIGINT PRIMARY KEY,
    INIT_FIELD BINARY(1) NOT NULL,
    BYTES_FIELD BINARY(1) NOT NULL,
    FRAME_FIELD BINARY(1) NOT NULL,
    CRC_FIELD BINARY(1) NOT NULL,
    END_FIELD BINARY(1) NOT NULL
);

CREATE SEQUENCE SEQ_A0_RESPONSE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;

CREATE TABLE TEXT_MESSAGE(
    TEXT_MESSAGE_ID BIGINT PRIMARY KEY,
    TEXT_MSG VARCHAR(250)
);

CREATE SEQUENCE SEQ_TEXT_MESSAGE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;

CREATE TABLE A1_REQUEST(
    A1_REQUEST_ID BIGINT PRIMARY KEY,
    A0_RESPONSE_ID BIGINT NOT NULL,
    TEXT_MESSAGE_ID BIGINT NOT NULL,
    INIT_FIELD BINARY(1) NOT NULL,
    BYTES_FIELD BINARY(1) NOT NULL,
    FRAME_FIELD BINARY(1) NOT NULL,
    DATA_FIELD BINARY(250) NOT NULL,
    CRC_FIELD BINARY(1) NOT NULL,
    END_FIELD BINARY(1) NOT NULL,
    FOREIGN KEY (A0_RESPONSE_ID) REFERENCES A0_RESPONSE(A0_RESPONSE_ID),
    FOREIGN KEY (TEXT_MESSAGE_ID) REFERENCES TEXT_MESSAGE(TEXT_MESSAGE_ID)
);

CREATE SEQUENCE SEQ_A1_REQUEST
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;

CREATE TABLE USER_INFORMATION(
    USER_INFORMATION_ID BIGINT PRIMARY KEY,
    AGE INT,
    WEIGHT INT,
    HEIGHT INT,
    TEXT_MSG VARCHAR(246)
);

CREATE TABLE A2_REQUEST(
    A2_REQUEST_ID BIGINT PRIMARY KEY,
    A0_RESPONSE_ID BIGINT NOT NULL,
    USER_INFORMATION_ID BIGINT NOT NULL,
    INIT_FIELD BINARY(1) NOT NULL,
    BYTES_FIELD BINARY(1) NOT NULL,
    FRAME_FIELD BINARY(1) NOT NULL,
    DATA_FIELD BINARY(250) NOT NULL,
    CRC_FIELD BINARY(1) NOT NULL,
    END_FIELD BINARY(1) NOT NULL,
    FOREIGN KEY (A0_RESPONSE_ID) REFERENCES A0_RESPONSE(A0_RESPONSE_ID),
    FOREIGN KEY (USER_INFORMATION_ID) REFERENCES USER_INFORMATION(USER_INFORMATION_ID)
);

CREATE TABLE TIME_ZONE(
    TIME_ZONE_ID BIGINT PRIMARY KEY,
    ZONE_NAME VARCHAR(250)
);

CREATE TABLE CURRENT_DATE_TIME(
    CURRENT_DATE_TIME_ID BIGINT PRIMARY KEY,
    DAY INT,
    MONTH INT,
    YEAR INT,
    HOUR INT,
    MINUTE INT,
    SECOND INT
);

CREATE TABLE A3_RESPONSE(
    A3_RESPONSE_ID BIGINT PRIMARY KEY,
    CURRENT_DATE_TIME_ID BIGINT NOT NULL,
    INIT_FIELD BINARY(1) NOT NULL,
    BYTES_FIELD BINARY(1) NOT NULL,
    FRAME_FIELD BINARY(1) NOT NULL,
    DATA_FIELD BINARY(250) NOT NULL,
    CRC_FIELD BINARY(1) NOT NULL,
    END_FIELD BINARY(1) NOT NULL,
    FOREIGN KEY (CURRENT_DATE_TIME_ID) REFERENCES CURRENT_DATE_TIME(CURRENT_DATE_TIME_ID)
);

CREATE TABLE A3_REQUEST(
    A3_REQUEST_ID BIGINT PRIMARY KEY,
    A3_RESPONSE_ID BIGINT NOT NULL,
    TIME_ZONE_ID BIGINT NOT NULL,
    INIT_FIELD BINARY(1) NOT NULL,
    BYTES_FIELD BINARY(1) NOT NULL,
    FRAME_FIELD BINARY(1) NOT NULL,
    DATA_FIELD BINARY(250) NOT NULL,
    CRC_FIELD BINARY(1) NOT NULL,
    END_FIELD BINARY(1) NOT NULL,
    FOREIGN KEY (A3_RESPONSE_ID) REFERENCES A3_RESPONSE(A3_RESPONSE_ID),
    FOREIGN KEY (TIME_ZONE_ID) REFERENCES TIME_ZONE(TIME_ZONE_ID)
);

CREATE TABLE IF NOT EXISTS BOOK (
                                    BOOK_ID bigint NOT NULL AUTO_INCREMENT,
                                    TITLE_KOREAN varchar(100) NOT NULL,
                                    TITLE_ENGLISH varchar(100) NOT NULL,
                                    DESCRIPTION varchar(100) NOT NULL,
                                    AUTHOR varchar(100) NOT NULL,
                                    ISBN varchar(100) NOT NULL UNIQUE,
                                    PUBLISH_DATE varchar(100) NOT NULL,
                                    CREATED_AT datetime NOT NULL,
                                    LAST_MODIFIED_AT datetime NOT NULL,
                                    PRIMARY KEY (BOOK_ID)
);

CREATE TABLE IF NOT EXISTS SPEED_LIMIT_ENFORCEMENT (
                                                       ID bigint NOT NULL AUTO_INCREMENT,
                                                       CAR_NUMBER varchar(30) NOT NULL,
                                                       SHOT_SPEED smallint NOT NULL,
                                                       SHOT_AT datetime NOT NULL,
                                                       CAMERA_ID smallint NOT NULL,
                                                       SPEED_LIMIT smallint NOT NULL,
                                                       CREATED_AT datetime NOT NULL,
                                                       LAST_MODIFIED_AT datetime NOT NULL,
                                                       PRIMARY KEY (ID)
);
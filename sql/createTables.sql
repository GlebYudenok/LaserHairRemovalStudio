CREATE TABLE IF NOT EXISTS USER (
                    id VARCHAR(255) NOT NULL ,
                    login VARCHAR(255) NOT NULL ,
                    password VARCHAR(255) NOT NULL ,
                    email VARCHAR(255) NOT NULL ,
                    role ENUM('Admin', 'Master', 'Client') NOT NULL ,
                    PRIMARY KEY (id)
                    );

CREATE TABLE IF NOT EXISTS SERVICE (
                        id VARCHAR(255),
                        zone_name VARCHAR(255),
                        price DECIMAL,
                        PRIMARY KEY (id)
                        );

CREATE TABLE IF NOT EXISTS USER_INFO (
                                      user_id VARCHAR(255) ,
                                     name VARCHAR(255) NOT NULL ,
                                     surname VARCHAR(255) NOT NULL ,
                                     avatar_link TEXT NOT NULL ,
                                     birth_date VARCHAR(255) NOT NULL ,
                                     phone_number VARCHAR(255) NOT NULL ,
                                     gender ENUM ('male', 'female', 'other') ,
                                     FOREIGN KEY (user_id) REFERENCES USER(id),
                                     PRIMARY KEY (user_id)
);


CREATE TABLE IF NOT EXISTS COMPLEX_SERVICE (
                        id VARCHAR(255),
                        service_id VARCHAR(255),
                        price DECIMAL,
                        name VARCHAR(255),
                        user_gender enum ('male', 'female', 'other'),
                        FOREIGN KEY (service_id) REFERENCES SERVICE(id),
                        PRIMARY KEY (id,service_id)
                        );

CREATE TABLE IF NOT EXISTS APPOINTMENT (
                             date_n_time DATETIME NOT NULL,
                             user_id VARCHAR(255),
                             service_id VARCHAR(255),
                             complex_id VARCHAR(255),
                             foreign key (complex_id) REFERENCES COMPLEX_SERVICE(id),
                             foreign key (user_id) REFERENCES USER(id),
                             foreign key (service_id) REFERENCES SERVICE(id),
                             primary key (date_n_time)
                         );

CREATE TABLE IF NOT EXISTS PICTURES (
                        id VARCHAR(255),
                        link TEXT,
                        PRIMARY KEY (id)
);
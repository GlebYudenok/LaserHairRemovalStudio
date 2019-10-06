CREATE TABLE IF NOT EXISTS USER (
                    id VARCHAR(255) ,
                    login VARCHAR(255) NOT NULL UNIQUE ,
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
                                     birth_date DATE NOT NULL ,
                                     phone_number VARCHAR(255) NOT NULL ,
                                     gender ENUM ('male', 'female', 'other') ,
                                     FOREIGN KEY (user_id) REFERENCES USER(id) ON DELETE CASCADE,
                                     PRIMARY KEY (user_id)
);


CREATE TABLE IF NOT EXISTS COMPLEX_SERVICE (
                        id VARCHAR(255),
                        service_id VARCHAR(255),
                        price DECIMAL,
                        name VARCHAR(255),
                        user_gender enum ('male', 'female', 'other'),
                        FOREIGN KEY (service_id) REFERENCES SERVICE(id) ON DELETE CASCADE ,
                        PRIMARY KEY (id,service_id)
                        );

CREATE TABLE IF NOT EXISTS APPOINTMENT (
                             id VARCHAR(255) NOT NULL ,
                             date_n_time TIMESTAMP NOT NULL UNIQUE ,
                             user_id VARCHAR(255) ,
                             service_id VARCHAR(255),
                             complex_id VARCHAR(255),
                             foreign key (complex_id) REFERENCES COMPLEX_SERVICE(id) ON DELETE CASCADE ,
                             foreign key (user_id) REFERENCES USER(id) ON DELETE CASCADE ,
                             foreign key (service_id) REFERENCES SERVICE(id) ON DELETE CASCADE ,
                             primary key (id)
                         );

CREATE TABLE IF NOT EXISTS PICTURES (
                        id VARCHAR(255),
                        link TEXT,
                        PRIMARY KEY (id)
);
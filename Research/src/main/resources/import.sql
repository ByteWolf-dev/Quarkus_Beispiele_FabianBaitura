-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO R_Researcher (firstName, lastName, email) VALUES ('John', 'Doe', 'john.doe@example.com');
INSERT INTO R_Researcher (firstName, lastName, email) VALUES ('Jane', 'Smith', 'jane.smith@example.com');
INSERT INTO R_Researcher (firstName, lastName, email) VALUES ('Alice', 'Johnson', 'alice.johnson@example.com');

-- Insert sample publications
INSERT INTO R_Publication (title, notes) VALUES ('Research on AI', 'Detailed research on AI technologies.');
INSERT INTO R_Publication (title, notes) VALUES ('Quantum Computing', 'An overview of quantum computing advancements.');
INSERT INTO R_Publication (title, notes) VALUES ('Blockchain Technology', 'Exploring the applications of blockchain.');

-- Create associations in the join table
-- John Doe -> Research on AI, Quantum Computing
INSERT INTO R_Research_Publication (researcherId, publicationId) VALUES (1, 1);
INSERT INTO R_Research_Publication (researcherId, publicationId) VALUES (1, 2);

-- Jane Smith -> Quantum Computing, Blockchain Technology
INSERT INTO R_Research_Publication (researcherId, publicationId) VALUES (2, 2);
INSERT INTO R_Research_Publication (researcherId, publicationId) VALUES (2, 3);

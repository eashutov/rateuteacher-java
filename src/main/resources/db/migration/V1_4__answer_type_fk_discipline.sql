ALTER TABLE answer DROP COLUMN answer;
ALTER TABLE answer ADD COLUMN score INTEGER;
ALTER TABLE answer ADD COLUMN comment TEXT;

ALTER TABLE survey ADD COLUMN discipline UUID;
ALTER TABLE survey ADD CONSTRAINT fk_discipline FOREIGN KEY (discipline) REFERENCES Discipline(id);
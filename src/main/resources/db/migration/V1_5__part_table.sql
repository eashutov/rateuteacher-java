CREATE TABLE IF NOT EXISTS part (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(25) NOT NULL,
    title VARCHAR(100) NOT NULL,
    questionnaire UUID REFERENCES questionnaire(id)
);

ALTER TABLE question DROP COLUMN questionnaire;
ALTER TABLE question DROP COLUMN part;
ALTER TABLE question ADD COLUMN part UUID REFERENCES part(id);

ALTER TABLE survey ADD CONSTRAINT code_unique UNIQUE (code);
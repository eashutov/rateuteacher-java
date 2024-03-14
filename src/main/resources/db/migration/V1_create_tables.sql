CREATE TABLE IF NOT EXISTS discipline (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS department (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS person (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	last_name VARCHAR(100) NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	patronymic VARCHAR(100) NOT NULL,
	department UUID REFERENCES department(id)
);

CREATE TABLE IF NOT EXISTS teacher (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	person UUID REFERENCES person(id),
	experience int NOT NULL
);

CREATE TABLE IF NOT EXISTS contact (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	office varchar(10) NOT NULL,
	email varchar(100) NOT NULL,
	photo varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS admin (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	person UUID REFERENCES person(id),
	role VARCHAR(10) NOT NULL CHECK (role in ('MODERATOR', 'ADMIN')),
	login VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	contact UUID REFERENCES contact(id)
);

CREATE TABLE IF NOT EXISTS questionnaire (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	standard VARCHAR(100) NOT NULL,
	description TEXT
);

CREATE TABLE IF NOT EXISTS question (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	questionnaire UUID REFERENCES questionnaire(id),
	part VARCHAR(30),
	question TEXT NOT NULL,
	type VARCHAR(10) NOT NULL check (type in ('RADIO', 'COMMENT', 'CHECK')),
);

CREATE TABLE IF NOT EXISTS survey (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	code VARCHAR(20) NOT NULL,
	usages INT NOT NULL,
	creation_date date NOT NULL,
	study_group VARCHAR(10) NOT NULL,
	teacher UUID REFERENCES teacher(id),
	admin UUID REFERENCES admin(id),
	questionnaire UUID REFERENCES questionnaire(id)
);

CREATE TABLE IF NOT EXISTS rating (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	completion_date date NOT NULL,
	survey UUID REFERENCES survey(id)
);

CREATE TABLE IF NOT EXISTS answer (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	rating UUID REFERENCES rating(id),
	question UUID REFERENCES question(id),
	answer VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS teacher_discipline (
    teacher UUID REFERENCES teacher(id),
    discipline UUID REFERENCES discipline(id)
);

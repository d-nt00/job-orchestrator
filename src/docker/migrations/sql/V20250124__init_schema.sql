CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE scheduling (
    job_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    job_status varchar(30) NOT NULL,
    job_details JSON NOT NULL,
    scheduled_time TIMESTAMP NOT NULL
);
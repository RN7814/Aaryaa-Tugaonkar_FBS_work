-- All IDs and foreign keys use BIGINT. No automatic ORM schema changes.
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_user_role CHECK (role IN ('ADMIN', 'COUNSELOR', 'MANAGER'))
);
CREATE TABLE IF NOT EXISTS courses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    duration VARCHAR(60) NOT NULL,
    fees DECIMAL(10,2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT chk_course_fees CHECK (fees > 0)
);
CREATE TABLE IF NOT EXISTS leads (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(16) NOT NULL UNIQUE,
    email VARCHAR(150),
    city VARCHAR(100),
    college VARCHAR(150),
    qualification VARCHAR(80),
    source VARCHAR(30) NOT NULL,
    stage VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    remarks VARCHAR(2000),
    course_id BIGINT,
    counselor_id BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id),
    FOREIGN KEY (counselor_id) REFERENCES users(id),
    INDEX idx_lead_counselor (counselor_id),
    INDEX idx_lead_stage (stage),
    CONSTRAINT chk_lead_stage CHECK (stage IN ('OPEN','CNR','CALL_BACK','INTERESTED','FOLLOW_UP','CONVERTED','CLOSED'))
);
CREATE TABLE IF NOT EXISTS calls (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    lead_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    outcome VARCHAR(30) NOT NULL,
    notes VARCHAR(2000) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (lead_id) REFERENCES leads(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE IF NOT EXISTS followups (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    lead_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    due_at DATETIME NOT NULL,
    notes VARCHAR(2000) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    FOREIGN KEY (lead_id) REFERENCES leads(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    INDEX idx_followup_due (status, due_at),
    CONSTRAINT chk_followup_status CHECK (status IN ('PENDING','DONE','CLOSED'))
);
CREATE TABLE IF NOT EXISTS admissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    lead_id BIGINT NOT NULL UNIQUE,
    course_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    total_fees DECIMAL(10,2) NOT NULL,
    fees_paid DECIMAL(10,2) NOT NULL,
    payment_mode VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (lead_id) REFERENCES leads(id),
    FOREIGN KEY (course_id) REFERENCES courses(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT chk_admission_fees CHECK (total_fees > 0 AND fees_paid >= 0 AND fees_paid <= total_fees)
);

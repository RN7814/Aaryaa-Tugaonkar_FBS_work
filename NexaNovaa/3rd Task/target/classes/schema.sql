CREATE DATABASE IF NOT EXISTS nexaanova_crm;
USE nexaanova_crm;

CREATE TABLE IF NOT EXISTS users (
  user_id       INT AUTO_INCREMENT PRIMARY KEY,
  full_name     VARCHAR(100) NOT NULL,
  email         VARCHAR(150) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  phone         VARCHAR(15),
  role          ENUM('admin','counselor','manager','trainer','student') NOT NULL,
  is_active     BOOLEAN DEFAULT TRUE,
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS courses (
  course_id   INT AUTO_INCREMENT PRIMARY KEY,
  course_name VARCHAR(150) NOT NULL UNIQUE,
  description TEXT,
  duration    VARCHAR(50) NOT NULL,
  fees        DECIMAL(10,2) NOT NULL,
  is_active   BOOLEAN DEFAULT TRUE,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS enquiries (
  enquiry_id       INT AUTO_INCREMENT PRIMARY KEY,
  student_name     VARCHAR(100) NOT NULL,
  phone            VARCHAR(15) NOT NULL,
  email            VARCHAR(150),
  city             VARCHAR(100),
  course_id        INT,
  source           ENUM('Instagram','Meta','Website','Google','Walk-in','Reference','Other') NOT NULL,
  enquiry_date     DATE DEFAULT (CURRENT_DATE),
  assigned_to      INT,
  status           ENUM('New','Called','Follow-up','Closed','Converted','CNR') DEFAULT 'New',
  college_name     VARCHAR(150),
  qualification    VARCHAR(100),
  marks_percentage DECIMAL(5,2),
  remarks          TEXT,
  created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (course_id)   REFERENCES courses(course_id)  ON DELETE SET NULL,
  FOREIGN KEY (assigned_to) REFERENCES users(user_id)      ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS call_records (
  call_id      INT AUTO_INCREMENT PRIMARY KEY,
  enquiry_id   INT NOT NULL,
  counselor_id INT NOT NULL,
  call_date    DATETIME NOT NULL,
  call_status  ENUM('Not Reachable','Not Interested','Interested','Call Later','CNR') NOT NULL,
  remarks      TEXT,
  created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (enquiry_id)   REFERENCES enquiries(enquiry_id) ON DELETE CASCADE,
  FOREIGN KEY (counselor_id) REFERENCES users(user_id)        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS followups (
  followup_id     INT AUTO_INCREMENT PRIMARY KEY,
  enquiry_id      INT NOT NULL,
  counselor_id    INT NOT NULL,
  followup_date   DATE NOT NULL,
  followup_status ENUM('Pending','Done','Rescheduled','Converted','Closed') DEFAULT 'Pending',
  remarks         TEXT,
  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (enquiry_id)   REFERENCES enquiries(enquiry_id) ON DELETE CASCADE,
  FOREIGN KEY (counselor_id) REFERENCES users(user_id)        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS admissions (
  admission_id   INT AUTO_INCREMENT PRIMARY KEY,
  enquiry_id     INT NOT NULL UNIQUE,
  student_name   VARCHAR(100) NOT NULL,
  course_id      INT NOT NULL,
  total_fees     DECIMAL(10,2) NOT NULL,
  fees_paid      DECIMAL(10,2) NOT NULL DEFAULT 0,
  payment_status ENUM('Paid','Pending','Partial') NOT NULL DEFAULT 'Pending',
  payment_type   ENUM('Cash','Online','Cheque','DD') NOT NULL,
  installment    BOOLEAN DEFAULT FALSE,
  admission_date DATE DEFAULT (CURRENT_DATE),
  admitted_by    INT,
  created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (enquiry_id)  REFERENCES enquiries(enquiry_id) ON DELETE CASCADE,
  FOREIGN KEY (course_id)   REFERENCES courses(course_id),
  FOREIGN KEY (admitted_by) REFERENCES users(user_id)        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS students (
  student_id   INT AUTO_INCREMENT PRIMARY KEY,
  admission_id INT NOT NULL UNIQUE,
  user_id      INT,
  trainer_id   INT,
  batch_name   VARCHAR(100),
  start_date   DATE,
  end_date     DATE,
  attendance   DECIMAL(5,2) DEFAULT 0.00,
  status       ENUM('Active','Completed','Dropped') DEFAULT 'Active',
  created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (admission_id) REFERENCES admissions(admission_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id)      REFERENCES users(user_id)           ON DELETE SET NULL,
  FOREIGN KEY (trainer_id)   REFERENCES users(user_id)           ON DELETE SET NULL
);

CREATE INDEX idx_enq_assigned ON enquiries(assigned_to);
CREATE INDEX idx_enq_status   ON enquiries(status);
CREATE INDEX idx_fup_date     ON followups(followup_date);
CREATE INDEX idx_call_enq     ON call_records(enquiry_id);
CREATE INDEX idx_adm_date     ON admissions(admission_date);

-- Seed users (passwords are bcrypt of "password123")
INSERT IGNORE INTO users (full_name,email,password_hash,phone,role) VALUES
('Admin User',     'admin@nexaanova.com',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh7y','9000000001','admin'),
('Rohan Kulkarni', 'rohan@nexaanova.com',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh7y','9876543001','counselor'),
('Sneha Patil',    'sneha@nexaanova.com',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh7y','9876543002','counselor'),
('Nisha Joshi',    'nisha@nexaanova.com',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh7y','9876543003','manager'),
('Raj Trainer',    'raj@nexaanova.com',      '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh7y','9876543004','trainer');

INSERT IGNORE INTO courses (course_name,description,duration,fees) VALUES
('MBA','Master of Business Administration','2 Years',120000.00),
('MCA','Master of Computer Applications','2 Years',90000.00),
('BCA','Bachelor of Computer Applications','3 Years',75000.00),
('Data Science','Data Science & ML Certificate','6 Months',45000.00);

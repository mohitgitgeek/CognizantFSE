-- ANSI SQL / MySQL 8.0 solutions: Local Community Event Portal
DROP DATABASE IF EXISTS event_portal;
CREATE DATABASE event_portal; USE event_portal;
CREATE TABLE users (user_id INT PRIMARY KEY AUTO_INCREMENT, full_name VARCHAR(100) NOT NULL, email VARCHAR(100) UNIQUE NOT NULL, city VARCHAR(100) NOT NULL, registration_date DATE NOT NULL);
CREATE TABLE events (event_id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(200) NOT NULL, description TEXT, city VARCHAR(100) NOT NULL, start_date DATETIME NOT NULL, end_date DATETIME NOT NULL, status ENUM('upcoming','completed','cancelled') NOT NULL, organizer_id INT, FOREIGN KEY (organizer_id) REFERENCES users(user_id));
CREATE TABLE sessions (session_id INT PRIMARY KEY AUTO_INCREMENT, event_id INT NOT NULL, title VARCHAR(200) NOT NULL, speaker_name VARCHAR(100) NOT NULL, start_time DATETIME NOT NULL, end_time DATETIME NOT NULL, FOREIGN KEY (event_id) REFERENCES events(event_id));
CREATE TABLE registrations (registration_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL, event_id INT NOT NULL, registration_date DATE NOT NULL, FOREIGN KEY (user_id) REFERENCES users(user_id), FOREIGN KEY (event_id) REFERENCES events(event_id));
CREATE TABLE feedback (feedback_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL, event_id INT NOT NULL, rating INT CHECK (rating BETWEEN 1 AND 5), comments TEXT, feedback_date DATE NOT NULL, FOREIGN KEY (user_id) REFERENCES users(user_id), FOREIGN KEY (event_id) REFERENCES events(event_id));
CREATE TABLE resources (resource_id INT PRIMARY KEY AUTO_INCREMENT, event_id INT NOT NULL, resource_type ENUM('pdf','image','link') NOT NULL, resource_url VARCHAR(255) NOT NULL, uploaded_at DATETIME NOT NULL, FOREIGN KEY (event_id) REFERENCES events(event_id));
INSERT INTO users VALUES (1,'Alice Johnson','alice@example.com','New York','2024-12-01'),(2,'Bob Smith','bob@example.com','Los Angeles','2024-12-05'),(3,'Charlie Lee','charlie@example.com','Chicago','2024-12-10'),(4,'Diana King','diana@example.com','New York','2025-01-15'),(5,'Ethan Hunt','ethan@example.com','Los Angeles','2025-02-01');
INSERT INTO events VALUES (1,'Tech Innovators Meetup','A meetup for tech enthusiasts.','New York','2025-06-10 10:00:00','2025-06-10 16:00:00','upcoming',1),(2,'AI & ML Conference','Conference on AI and ML advancements.','Chicago','2025-05-15 09:00:00','2025-05-15 17:00:00','completed',3),(3,'Frontend Development Bootcamp','Hands-on training on frontend tech.','Los Angeles','2025-07-01 10:00:00','2025-07-03 16:00:00','upcoming',2);
INSERT INTO sessions VALUES (1,1,'Opening Keynote','Dr. Tech','2025-06-10 10:00:00','2025-06-10 11:00:00'),(2,1,'Future of Web Dev','Alice Johnson','2025-06-10 11:15:00','2025-06-10 12:30:00'),(3,2,'AI in Healthcare','Charlie Lee','2025-05-15 09:30:00','2025-05-15 11:00:00'),(4,3,'Intro to HTML5','Bob Smith','2025-07-01 10:00:00','2025-07-01 12:00:00');
INSERT INTO registrations VALUES (1,1,1,'2025-05-01'),(2,2,1,'2025-05-02'),(3,3,2,'2025-04-30'),(4,4,2,'2025-04-28'),(5,5,3,'2025-06-15');
INSERT INTO feedback VALUES (1,3,2,4,'Great insights!','2025-05-16'),(2,4,2,5,'Very informative.','2025-05-16'),(3,2,1,3,'Could be better.','2025-06-11');
INSERT INTO resources VALUES (1,1,'pdf','https://portal.com/resources/tech_meetup_agenda.pdf','2025-05-01 10:00:00'),(2,2,'image','https://portal.com/resources/ai_poster.jpg','2025-04-20 09:00:00'),(3,3,'link','https://portal.com/resources/html5_docs','2025-06-25 15:00:00');

-- 1. Bind :user_id to the required user.
SELECT e.* FROM events e JOIN registrations r ON r.event_id=e.event_id JOIN users u ON u.user_id=r.user_id WHERE r.user_id=:user_id AND e.status='upcoming' AND e.city=u.city ORDER BY e.start_date;
-- 2
SELECT e.event_id,e.title,AVG(f.rating) average_rating,COUNT(*) feedback_count FROM events e JOIN feedback f ON f.event_id=e.event_id GROUP BY e.event_id,e.title HAVING COUNT(*)>=10 ORDER BY average_rating DESC;
-- 3
SELECT u.* FROM users u WHERE NOT EXISTS (SELECT 1 FROM registrations r WHERE r.user_id=u.user_id AND r.registration_date>=CURDATE()-INTERVAL 90 DAY);
-- 4
SELECT e.title,COUNT(s.session_id) sessions_10_to_12 FROM events e LEFT JOIN sessions s ON s.event_id=e.event_id AND TIME(s.start_time)>='10:00:00' AND TIME(s.start_time)<'12:00:00' GROUP BY e.event_id,e.title;
-- 5
SELECT e.city,COUNT(DISTINCT r.user_id) distinct_registrants FROM events e JOIN registrations r ON r.event_id=e.event_id GROUP BY e.city ORDER BY distinct_registrants DESC LIMIT 5;
-- 6
SELECT e.title,COUNT(r.resource_id) total_resources,SUM(r.resource_type='pdf') pdfs,SUM(r.resource_type='image') images,SUM(r.resource_type='link') links FROM events e LEFT JOIN resources r ON r.event_id=e.event_id GROUP BY e.event_id,e.title;
-- 7
SELECT u.full_name,f.comments,e.title FROM feedback f JOIN users u ON u.user_id=f.user_id JOIN events e ON e.event_id=f.event_id WHERE f.rating<3;
-- 8
SELECT e.title,COUNT(s.session_id) session_count FROM events e LEFT JOIN sessions s ON s.event_id=e.event_id WHERE e.status='upcoming' GROUP BY e.event_id,e.title;
-- 9
SELECT u.full_name,e.status,COUNT(*) event_count FROM users u JOIN events e ON e.organizer_id=u.user_id GROUP BY u.user_id,u.full_name,e.status;
-- 10
SELECT DISTINCT e.* FROM events e JOIN registrations r ON r.event_id=e.event_id LEFT JOIN feedback f ON f.event_id=e.event_id WHERE f.feedback_id IS NULL;
-- 11
SELECT registration_date,COUNT(*) new_users FROM users WHERE registration_date>=CURDATE()-INTERVAL 7 DAY GROUP BY registration_date ORDER BY registration_date;
-- 12
WITH counts AS (SELECT e.event_id,e.title,COUNT(s.session_id) n FROM events e LEFT JOIN sessions s ON s.event_id=e.event_id GROUP BY e.event_id,e.title) SELECT * FROM counts WHERE n=(SELECT MAX(n) FROM counts);
-- 13
SELECT e.city,AVG(f.rating) average_rating FROM events e JOIN feedback f ON f.event_id=e.event_id GROUP BY e.city;
-- 14
SELECT e.title,COUNT(r.registration_id) registrations FROM events e JOIN registrations r ON r.event_id=e.event_id GROUP BY e.event_id,e.title ORDER BY registrations DESC LIMIT 3;
-- 15
SELECT e.title,a.title session_a,b.title session_b FROM sessions a JOIN sessions b ON a.event_id=b.event_id AND a.session_id<b.session_id AND a.start_time<b.end_time AND b.start_time<a.end_time JOIN events e ON e.event_id=a.event_id;
-- 16
SELECT u.* FROM users u WHERE u.registration_date>=CURDATE()-INTERVAL 30 DAY AND NOT EXISTS (SELECT 1 FROM registrations r WHERE r.user_id=u.user_id);
-- 17
SELECT speaker_name,COUNT(*) session_count FROM sessions GROUP BY speaker_name HAVING COUNT(*)>1;
-- 18
SELECT e.* FROM events e LEFT JOIN resources r ON r.event_id=e.event_id WHERE r.resource_id IS NULL;
-- 19
SELECT e.title,COUNT(DISTINCT r.registration_id) registrations,AVG(f.rating) average_rating FROM events e LEFT JOIN registrations r ON r.event_id=e.event_id LEFT JOIN feedback f ON f.event_id=e.event_id WHERE e.status='completed' GROUP BY e.event_id,e.title;
-- 20
SELECT u.full_name,COUNT(DISTINCT r.event_id) attended_events,COUNT(DISTINCT f.feedback_id) feedbacks_submitted FROM users u LEFT JOIN registrations r ON r.user_id=u.user_id LEFT JOIN feedback f ON f.user_id=u.user_id GROUP BY u.user_id,u.full_name;
-- 21
SELECT u.full_name,COUNT(f.feedback_id) feedback_count FROM users u JOIN feedback f ON f.user_id=u.user_id GROUP BY u.user_id,u.full_name ORDER BY feedback_count DESC LIMIT 5;
-- 22
SELECT user_id,event_id,COUNT(*) duplicate_count FROM registrations GROUP BY user_id,event_id HAVING COUNT(*)>1;
-- 23
SELECT DATE_FORMAT(registration_date,'%Y-%m') month,COUNT(*) registrations FROM registrations WHERE registration_date>=CURDATE()-INTERVAL 12 MONTH GROUP BY DATE_FORMAT(registration_date,'%Y-%m') ORDER BY month;
-- 24
SELECT e.title,AVG(TIMESTAMPDIFF(MINUTE,s.start_time,s.end_time)) average_minutes FROM events e JOIN sessions s ON s.event_id=e.event_id GROUP BY e.event_id,e.title;
-- 25
SELECT e.* FROM events e LEFT JOIN sessions s ON s.event_id=e.event_id WHERE s.session_id IS NULL;

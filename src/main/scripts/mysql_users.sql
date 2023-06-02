DROP DATABASE IF EXISTS recipes;
DROP USER IF EXISTS `recipesadmin`@`%`;
DROP USER IF EXISTS `recipesuser`@`%`;
CREATE DATABASE IF NOT EXISTS recipes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `recipesadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `recipes`.* TO `recipesadmin`@`%`;
CREATE USER IF NOT EXISTS `recipesuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `recipes`.* TO `recipesuser`@`%`;
FLUSH PRIVILEGES;
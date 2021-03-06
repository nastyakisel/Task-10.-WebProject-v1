DROP SCHEMA IF EXISTS online_apteka;

CREATE SCHEMA IF NOT EXISTS online_apteka
CHARACTER SET utf8;

USE online_apteka;

CREATE TABLE Role (
  ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ROLE_NAME VARCHAR(255) NOT NULL
  
);

INSERT INTO Role (ID, ROLE_NAME) VALUES ('1', '������');
INSERT INTO Role (ID, ROLE_NAME) VALUES ('2', '���������');
INSERT INTO Role (ID, ROLE_NAME) VALUES ('3', '����');


CREATE TABLE User (
  ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USER_NAME VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  FK_ROLE_ID INTEGER NOT NULL,
	CONSTRAINT FK_ROLE FOREIGN KEY (FK_ROLE_ID) REFERENCES Role (ID)
	ON DELETE cascade
    ON UPDATE cascade
);

INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('ivanov@mail.ru', 'fgdhk34', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('metl@mail.ru', 'dkljhs', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('djoli@mail.ru', 'qwerty', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('timur@mail.ru', 'timur', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('nasta@tut.by', '123456', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('vlad@tut.by', 'fskjyy', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('admin', 'admin', '2');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('petrov@mail.ru', 'dfghyr', '3');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('doc@mail.ru', 'asder', '3');


CREATE TABLE Category (
  ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  CATEGORY_NAME VARCHAR(255) NOT NULL
);

INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('1', '��������-���������� ���������');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('2', '���������-�������� ���������');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('3', '������������������� ���������');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('4', '����������������� ���������');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('5', '������������ ���������');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('6', '������������� ���������');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('7', '��������');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('8', '���������');


CREATE TABLE Drug (
  ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  DRUGNAME VARCHAR(255) NOT NULL,
  DESCRIPTION VARCHAR(755) NOT NULL,
  DOSAGE VARCHAR(50) NOT NULL,
  INSTRUCTION VARCHAR(1000) NOT NULL,
  PRICE DECIMAL(6,2) NOT NULL,
  QUANTITY FLOAT NOT NULL,
  NEED_RECIPE BOOLEAN NOT NULL,
  IMAGE_PATH VARCHAR(255) NOT NULL,
  FK_CATEGORY_ID INTEGER NOT NULL,
	CONSTRAINT FK_CATEGORY FOREIGN KEY (FK_CATEGORY_ID) REFERENCES Category (ID)
	ON DELETE cascade
    ON UPDATE cascade
);

INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('��������-�������', '����������������� �������-220��, �����������-200��, ������-27��.', '6 ��������', '����������', '1', '30', '0', 'css/img/citr.jpg', '1');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('���������', '����������, 20 ��, ������� ��� �������� ����������', '10 ��������/20 ��', '����������', '0.5', '50', '0', 'css/img/fur.jpg', '3');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('���������', '������������, ���� �������� �������� 25 �� ������������ ������������', '20 ��������/25 ��', '����������', '4.08', '37', '0', 'css/img/supr.jpg', '4');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('���������', '������������/������������ �������. ��������, �������� ���������.', '14 ��������/875 ��', '����������', '12.35', '50', '1', 'css/img/augm.jpg', '3');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('���������-������', '20 ��������, �������� ���������', '20 ��������/200 ��', '����������', '1.77', '50', '0', 'css/img/valer.jpg', '1');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('�����������', '������ �������� ��������:
����������� ��������: �������������� (������������) - 500 ��; ��������������� ��������: ������� �-25, ������� �������, ������� ������������.', '10 ��������/500 ��', '����������', '1.45', '50', '1', 'css/img/levo.jpg', '3');


CREATE TABLE Recipe (
  ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  DOSAGE VARCHAR(50) NOT NULL,
  QUANTITY FLOAT NOT NULL,
  DOCTOR_NAME VARCHAR(255) NOT NULL,
  BEGIN_DATE Date NOT NULL,
  END_DATE Date NOT NULL,
  FK_DRUG_ID INTEGER NOT NULL,
	CONSTRAINT FK_DRUG FOREIGN KEY (FK_DRUG_ID) REFERENCES Drug (ID)
	ON DELETE cascade
    ON UPDATE cascade,
  FK_USER_ID INTEGER NOT NULL,
	CONSTRAINT FK_USER FOREIGN KEY (FK_USER_ID) REFERENCES User (ID)
	ON DELETE cascade
    ON UPDATE cascade

);


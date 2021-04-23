CREATE TABLE Clients (
id serial PRIMARY KEY,
type text,
name text,
adress text,
phone text,
e_mail text,
balance money,
maxLoan money CHECK (maxLoan >= money(0)),
maxLoanTime integer
);

CREATE TABLE Services (
id serial PRIMARY KEY,
name text UNIQUE,
InternetAmount integer,
callMinutes integer,
smsAmount integer,
monthPrice money CHECK (monthPrice >= money(0))
);

CREATE TABLE Receipts (
clientId serial REFERENCES Clients ON DELETE CASCADE,
recieptDate date,
amount money CHECK (amount >= money(0))
);

CREATE TABLE Service_history (
clientId serial REFERENCES Clients ON DELETE CASCADE,
serviceId serial REFERENCES Clients ON DELETE RESTRICT,
startDate date,
finishDate date,
price money CHECK (price >= money(0))
);
INSERT INTO Clients (type, name, adress, phone, e_mail, balance, maxLoan, maxLoanTime) VALUES
	('individual', 'Ivan Medvedev', 'Izhevsk', '123456', 'IvanMedvedev@mail.ru', 1000, 200, 10),
	('individual', 'Nikolai Karetin', 'Krasnodar', '345678', 'NikolaiKaretin@mail.ru', 756, 200, 10),
	('individual', 'Fedor Korolev', 'Moscow', '678901', 'FedorKorolev@mail.ru', 1010, 200, 10),
	('individual', 'Tingir Badmaev', 'Elista', '135791', 'TingirBadmaev@mail.ru', 1204, 200, 10),
	('individual', 'Hatalia Cherepanova', 'Novgorod', '246802', 'HataliaCherepanova@mail.ru', 250, 200, 10),
	('individual', 'Arseniy Zotov', 'Neftekamsk', '147036', 'ArseniyZotov@mail.ru', 310, 200, 10),
	('company', 'CMC faculty', 'Moscow', '159371', 'CMCfaculty@mail.ru', 10050, 2000, 30),
	('company', 'KonsultantPlus', 'Moscow', '113355', 'KonsultantPlus@mail.ru', 20435, 2000, 30),
	('company', 'Blizzard Entertainment', 'Irvine', '333666', 'BlizzardEntertainment@mail.ru', 100500, 20000, 50)
	RETURNING *;
	
INSERT INTO Services (name, InternetAmount, callMinutes, smsAmount, monthPrice) VALUES
	('Basic', '2', '100', '0', '150'),
	('Basic+', '2', '100', '50', '180'),
	('Advenced', '4', '200', '0', '250'),
	('Advenced+', '4', '200', '50', '300'),
	('Complete', '10', '500', '100', '450'),
	('Perfect', '50', '1000', '200', '800'),
	('Perfect+', '9999', '9999', '9999', '1600')
	RETURNING *;

INSERT INTO Receipts (clientId, recieptDate, amount) VALUES
	('1', '2021-01-13', 100),
	('1', '2021-02-11', 200),
	('1', '2021-03-05', 250),
	('7', '2020-10-21', 2000),
	('7', '2020-11-21', 2000),
	('7', '2020-12-21', 2000),
	('7', '2021-01-21', 2000)
	RETURNING *;
	
INSERT INTO Service_history (clientId, serviceId, startDate, finishDate, price) VALUES
	('1', '3', '2021-01-01', '2021-02-01', 249),
	('2', '1', '2021-02-01', '2021-03-01', 140),
	('3', '2', '2020-12-01', '2021-01-01', 179),
	('4', '4', '2020-09-01', '2020-10-01', 299),
	('5', '5', '2020-11-01', '2020-12-01', 449),
	('6', '6', '2019-03-01', '2019-04-01', 798),
	('7', '7', '2019-01-01', '2021-01-01', 35000),
	('8', '7', '2018-01-01', '2020-01-01', 35000),
	('9', '7', '2017-01-01', '2021-01-01', 70000)
	RETURNING *;
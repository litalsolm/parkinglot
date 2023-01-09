create table parkinglot (
    parkingId int,
    taken enum('occupied', 'free', 'partlyOccupied'),
    vehicleIds varchar(255)
)

insert into parkinglot (parkingId, taken, vehicleIds) values (1, 'free', '[]')
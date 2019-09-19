create table if not exists cities
(
	id bigserial not null
		constraint cities_pk
			primary key,
	city varchar(255) not null,
	description varchar(255)
);

alter table cities owner to postgres;

create unique index if not exists cities_id_uindex
	on cities (id);

create unique index if not exists cities_city_uindex
	on cities (city);
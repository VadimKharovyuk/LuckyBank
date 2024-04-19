create table card (
    id bigserial not null,
    balance float(53) not null,
    card_number varchar(255), cvv varchar(255),
    expiration_date date, client_id bigint,
    primary key (id)
                  )

create table client
(id bigserial not null,
 address varchar(255),
 email varchar(255),
 last_name varchar(255),
 name varchar(255),
 primary key (id)
)
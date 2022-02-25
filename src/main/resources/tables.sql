drop table if exists ers_reimbursments;
drop table if exists ers_users;
drop table if exists ers_reimbursment_statuses;
drop table if exists ers_reimbursment_types;
drop table if exists ers_user_roles;

create table ers_user_roles(
	role_id		varchar primary key,
	role		varchar unique
);

create table ers_reimbursment_types(
	type_id		varchar primary key,
	type		varchar unique
);

create table ers_reimbursment_statuses(
	status_id	varchar primary key,
	staus		varchar unique
);

create table ers_users(
	user_id		varchar primary key,
	username	varchar unique not null,
	email		varchar unique not null,
	password	varchar not null,
	given_name	varchar not null,
	surname		varchar not null,
	is_active	boolean,
	role_id		varchar unique not null,
	
	constraint role_id_fk
	foreign key (role_id)
	references ers_user_roles (role_id)
);

create table ers_reimbursments (
	reimb_id	varchar primary key,
	amount		decimal(6, 2) not null,
	submitted	timestamp not null,
	resolved	timestamp not null,
	description	varchar not null,
	payment_id	varchar,
	author_id	varchar unique not null,
	resolver_id	varchar unique,
	status_id	varchar unique not null,
	type_id		varchar unique not null,
	
	constraint author_id_fk
	foreign key (author_id)
	references ers_users (user_id)
	on delete cascade,
	
	constraint resolver_id_fk
	foreign key (resolver_id)
	references ers_users (user_id)
	on delete set null,
	
	constraint status_id_fk
	foreign key (status_id)
	references ers_reimbursment_statuses (status_id),
	
	constraint type_id_fk
	foreign key (type_id)
	references ers_reimbursment_types (type_id)
);

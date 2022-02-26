delete from ers_users where user_id ='1';
delete from ers_user_roles where role_id='1';

insert into ers_user_roles values
	(1, 'user');

insert into ers_users 
	(user_id, username, email, password, given_name, surname, role_id) 
values
	(1, 'aidanamato', 'aidan@mail.com', 'password', 'Aidan', 'Amato', 1);
	
select * from ers_users;
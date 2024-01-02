drop table bot_helper_request;
create table bot_helper_request (
    id int generated always as identity primary key,
    requested_on timestamp with time zone not null default current_timestamp,
    sender_id int,
    user_name text,
    last_name text,
    first_name text,
    location text,
    command text
);
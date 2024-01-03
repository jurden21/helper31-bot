drop table if exists bot_helper31_request;
create table bot_helper31_request (
    id int generated always as identity primary key,
    requested_on timestamp with time zone not null default current_timestamp,
    sender_id int,
    user_name text,
    last_name text,
    first_name text,
    location text,
    command text
);
drop table if exists bot_helper31_password_settings;
create table bot_helper31_password_settings (
    chat_id int not null primary key,
    length int not null default 12,
    use_upper_case int not null default 1,
    use_lower_case int not null default 1,
    use_digits int not null default 1,
    use_special int not null default 0,
    use_brackets int not null default 0,
    chars text
);
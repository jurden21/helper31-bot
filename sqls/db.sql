drop table if exists public.requests;
create table public.requests (
    id bigint generated always as identity primary key,
    requested_on timestamp with time zone not null default current_timestamp,
    sender_id bigint,
    user_name text,
    last_name text,
    first_name text,
    command text
);
drop table if exists public.password_settings;
create table public.password_settings (
    chat_id bigint not null primary key,
    length int not null default 12,
    use_upper_case int not null default 1,
    use_lower_case int not null default 1,
    use_digits int not null default 1,
    use_special int not null default 0,
    inserted_on timestamp with time zone not null default current_timestamp,
    updated_on timestamp with time zone not null default current_timestamp
);
drop table if exists public.uuid_settings;
create table public.uuid_settings (
    chat_id bigint not null primary key,
    use_hyphens int not null default 1,
    use_upper_case int not null default 0,
    use_braces int not null default 1,
    inserted_on timestamp with time zone not null default current_timestamp,
    updated_on timestamp with time zone not null default current_timestamp
);
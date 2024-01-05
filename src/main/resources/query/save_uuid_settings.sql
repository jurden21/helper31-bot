insert into bot_helper31_uuid_settings (chat_id, use_hyphens, use_upper_case, use_braces)
values (:chat_id, :use_hyphens, :use_upper_case, :use_braces)
on conflict (chat_id) do update
set use_hyphens = excluded.use_hyphens,
    use_upper_case = excluded.use_upper_case,
    use_braces = excluded.use_braces
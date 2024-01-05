insert into bot_helper31_password_settings (chat_id, length, use_upper_case, use_lower_case, use_digits, use_special, use_brackets, chars)
values (:chat_id, :length, :use_upper_case, :use_lower_case, :use_digits, :use_special, :use_brackets, :chars)
on conflict (chat_id) do update
set length = excluded.length,
    use_upper_case = excluded.use_upper_case,
    use_lower_case = excluded.use_lower_case,
    use_digits = excluded.use_digits,
    use_special = excluded.use_special,
    use_brackets = excluded.use_brackets,
    chars = excluded.chars,
    updated_on = current_timestamp

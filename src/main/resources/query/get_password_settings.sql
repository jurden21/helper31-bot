select chat_id, length, use_upper_case, use_lower_case, use_digits, use_special
from public.password_settings
where chat_id = :chat_id
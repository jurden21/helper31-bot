select chat_id, use_hyphens, use_upper_case, use_braces
from public.uuid_settings
where chat_id = :chat_id
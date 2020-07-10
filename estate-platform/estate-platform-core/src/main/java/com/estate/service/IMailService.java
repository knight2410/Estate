package com.estate.service;

import com.estate.dto.MailRequest;
import com.estate.dto.MailResponse;

import java.util.Map;

public interface IMailService {
    MailResponse sendMail(MailRequest request, Map<String, Object> model, String templateMail);
}

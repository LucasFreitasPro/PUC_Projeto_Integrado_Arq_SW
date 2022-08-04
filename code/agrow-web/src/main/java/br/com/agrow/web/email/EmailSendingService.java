package br.com.agrow.web.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.agrow.web.dto.EmailSendingRequest;

@Service
public class EmailSendingService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final JavaMailSender emailSender;

	@Value(value = "${spring.mail.username}")
	private String fromEmail;

	public EmailSendingService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void sendEmail(EmailSendingRequest emailSendingRequest) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			message.setContent(emailSendingRequest.getMessage(), "text/html");
			message.setFrom(fromEmail);

			MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

			helper.setTo(emailSendingRequest.getToEmail());
			if (emailSendingRequest.getCcEmail() != null && !emailSendingRequest.getCcEmail().isEmpty()) {
				helper.setCc(emailSendingRequest.getCcEmail());
			}
			helper.setSubject(emailSendingRequest.getSubject());

			emailSender.send(message);
		} catch (Exception e) {
			logger.error("Falha ao enviar e-mail. {}", emailSendingRequest, e);
		}
		logger.info("E-mail enviado com sucesso");
	}
}

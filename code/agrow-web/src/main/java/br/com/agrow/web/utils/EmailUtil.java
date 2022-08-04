package br.com.agrow.web.utils;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.agrow.web.dto.EmailSendingRequest;
import br.com.agrow.web.model.User;

@Component
public class EmailUtil {

	private final String contextPath = "http://localhost:8081";

	public EmailSendingRequest loadEmailSendingRequest(User user) {
		StringBuilder message = new StringBuilder();
		message.append("<html>");
		message.append("	<head>");
		message.append("		<style>");
		message.append("			body{margin: auto;width: 60%;border: 0px;padding: 0px}");
		message.append("			p{font-size: 20px;font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;}");
		message.append("			a{color: black;}");
		message.append("			.content{padding: 30px;}");
		message.append("		</style>");
		message.append("	</head>");
		message.append("	<body>");
		message.append("		<div class=\"content\">");
		message.append("			<p>Ol&aacute; <strong>").append(user.getFirstName()).append(" ").append(user.getLastName()).append("</strong>,</p>");
		message.append("			<p>Voc&ecirc; se cadastrou no Agrow Web. Clique <a href=\"").append(contextPath).append("/email-validation/validate/").append(user.getEmailValidationKey()).append("\"><strong>aqui</strong></a> para confirmar e liberar o seu acesso.</p>");
		message.append("			<p>Aten&ccedil;&atilde;o! Esse link expira em <strong>").append(user.getExpirationEmailValidation().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("</strong>.</p>");
		message.append("			<p>Obrigado,<br><strong>Equipe Agrow</strong></p>");
		message.append("		<div>");
		message.append("	</body>");
		message.append("</html>");

		EmailSendingRequest emailSendingRequest = new EmailSendingRequest();
		emailSendingRequest.setToEmail(user.getUsername());
		emailSendingRequest.setSubject("[Agrow Web] - Confirmação de cadastro");
		emailSendingRequest.setMessage(message.toString());
		return emailSendingRequest;
	}
}

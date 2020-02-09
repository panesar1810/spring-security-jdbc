package code.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailSender {

	@Autowired JavaMailSender emailSender;
	@Autowired TemplateEngine engine;

	public void sendMail(String nameOfSender, String emailOfReciever, String subject)
			throws MessagingException {

		final Context ctx = new Context();
		ctx.setVariable("name", nameOfSender);

		final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
		message.setSubject(subject);
		message.setFrom("assignmentginoalbert@gmail.com");
		message.setTo(emailOfReciever);
		
		final String htmlContent = this.engine.process("email.html", ctx);
		message.setText(htmlContent, true);

		this.emailSender.send(mimeMessage);
	}
	
}

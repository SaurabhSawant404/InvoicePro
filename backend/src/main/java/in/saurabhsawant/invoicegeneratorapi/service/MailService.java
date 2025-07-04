package in.saurabhsawant.invoicegeneratorapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender mailSender;

	@Value("${mail.from}")
	private String mailFrom;

	public void sendInvoiceMail(String toEmail, MultipartFile file) throws MessagingException, IOException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String fileName = "Invoice_" + System.currentTimeMillis() + ".pdf";

		helper.setFrom(mailFrom);
		helper.setTo(toEmail);
		helper.setSubject("🧾 Your Invoice is Ready");
		String htmlContent = """
				    <p>Dear Customer,</p>
				    <p>Please find your attached invoice below.</p>
				    <p>Thank you for your purchase!</p>
				    <br/>
				    <p>Best regards,<br/>InvoicePro</p>
				""";
		helper.setText(htmlContent, true);
		helper.addAttachment(fileName, new ByteArrayResource(file.getBytes()));
		mailSender.send(message);
	}
}

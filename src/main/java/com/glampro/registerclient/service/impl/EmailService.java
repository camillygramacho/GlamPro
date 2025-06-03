package com.glampro.registerclient.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("EmailService")
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("glamprosalao@gmail.com"); // ou seu e-mail real
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = HTML

            helper.setFrom("glamprosalao@gmail.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }

    public String buildWelcomeEmailProfessional(String name, String client, StringBuilder service) {
        return """
        <html>
            <body>
                <h2>Olá, %s!</h2>
                <p>Você tem serviço agendado na GlamPro. Confirme os detalhes abaixo.</p>
                <p>Cliente: %s.</p>
                %s
                <p>Atenciosamente,<br>Equipe GlamPro</p>
            </body>
        </html>
        """.formatted(name, client, service);
    }

    public String buildWelcomeEmailClient(String name, StringBuilder description) {
        return """
        <html>
            <body>
                <h2>Olá, %s!</h2>
                <p>Seu(s) agendamento(s) foram realizados com sucesso na Glam Pro.</p>
                <p>%s</p>
                <p>Atenciosamente,<br>Equipe GlamPro</p>
            </body>
        </html>
        """.formatted(name, description);
    }


}

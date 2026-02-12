package org.techmate.techmate_be.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.techmate.techmate_be.model.dto.response.project.ProjectAndOwnerBasicInfoResponse;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.model.enumeration.CommitmentLevel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${frontend.base_url}")
    private String frontendBaseUrl;

    private final JavaMailSender mailSender;

    public void sendJoinRequestEmail(String to, ProjectAndOwnerBasicInfoResponse project,
                                     User requester, CommitmentLevel commitmentLevel) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            String html = this.buildHtmlBody(project, requester, commitmentLevel);

            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(to);
            helper.setSubject("Join request");
            helper.setText(html, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send HTML email", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildHtmlBody(ProjectAndOwnerBasicInfoResponse project, User requester, CommitmentLevel commitmentLevel) throws IOException {
        String template = this.loadEmailTemplate();

        template = template.replace("${ownerUsername}", project.getOwnerUsername());
        template = template.replace("${requesterUsername}", requester.getUsername());
        template = template.replace("${projectTitle}", project.getTitle());
        template = template.replace("${commitmentLevel}", commitmentLevel.toString());
        template = template.replace("${requesterEmail}", requester.getEmail());
        template = template.replace("${projectLink}", frontendBaseUrl + "/projects/"
                + project.getProjectId());

        return template;
    }

    private String loadEmailTemplate() throws IOException {
        Path path = Path.of("src/main/resources/templates/join-request-email.html");
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}

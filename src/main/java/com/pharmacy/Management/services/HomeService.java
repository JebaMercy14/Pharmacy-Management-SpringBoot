package com.pharmacy.Management.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class HomeService {
	
	@Autowired
    private JavaMailSender emailSender;

	public void sendEmail(String to, String subject, String body) {
        // Create a SimpleMailMessage instance
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        // Send email
        emailSender.send(message);
    }
	
	


}

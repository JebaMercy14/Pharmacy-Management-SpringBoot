package com.pharmacy.Management.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pharmacy.Management.services.HomeService;


@Controller
public class homeController {
	@GetMapping({"", "/"}) 
	public String home() { 
		return "home";
		}
	
	@GetMapping({"/info"}) 
	public String info() { 
		return "info";
		}
	
	@GetMapping({"/expert"}) 
	public String expert() { 
		return "AskExpert";
		}
	
	@GetMapping({"/question"}) 
	public String submitQuestion() { 
		return "home";
		}
	
	@GetMapping({"/invoice"}) 
	public String invoice() { 
		return "invoice";
		}
	
	@Autowired
    private HomeService homeService;

    @PostMapping("/question")
    public String submitQuestion(@RequestParam String question, @RequestParam String name, @RequestParam String email) {
        // Construct email message
    	String subject = "Expert Assistance Required: Inquiry from " + name;
    	String body = "Dear Expert,\n\n" +
    	              "I hope this message finds you well. My name is " + name + " and I am reaching out to you with a question that I believe falls within your area of expertise.\n\n" +
    	              "Question:\n" + question + "\n\n" +
    	              "I would greatly appreciate it if you could take some time to look into this matter. Your expert opinion and advice would be invaluable to me.\n\n" +
    	              "Please feel free to reply to this email (" + email + ") at your earliest convenience.\n\n" +
    	              "Thank you in advance for your time and assistance.\n\n" +
    	              "Best regards,\n" + name;


        // Use the autowired HomeService bean to send the email
        homeService.sendEmail("jebamercy1412@gmail.com", subject, body);

        // Redirect to a confirmation page
        return "home";
    }
    
    

}

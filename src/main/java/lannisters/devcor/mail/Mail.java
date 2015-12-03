package lannisters.devcor.mail;

/**
 * @author xoma0_000
 */

import org.springframework.mail.MailSender;  
import org.springframework.mail.SimpleMailMessage;  


public class Mail{  

    private MailSender mailSender;  
   
    public void setMailSender(MailSender mailSender) {  
        this.mailSender = mailSender;  
    }  
   
    public void sendMail(String from, String to, String subject, String msg) {    
        SimpleMailMessage message = new SimpleMailMessage();  
        message.setFrom(from);  
        message.setTo(to);  
        message.setSubject(subject);  
        message.setText(msg);
        try{
        mailSender.send(message);     
        }
        catch(Exception e){}
    }}   
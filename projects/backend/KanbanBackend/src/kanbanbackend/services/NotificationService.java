package kanbanbackend.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * This class is responsible for handling notification events and invoking the
 * appropriate methods for notification (RSS, email, etc.). The notification
 * methods will be implemented in other classes to keep the actual functionality
 * separate from the logic.
 * 
 * @author caw
 */
public class NotificationService extends Authenticator {

    // singleton variables
    private static Object padlock = new Object();
    private static NotificationService instance;
    private Session session;
    private PasswordAuthentication authentication;
    
    /*
     * Private constructor for the Singleton SQLDriver.
     * This ensures only one global, static instance of the class.
     */
    private NotificationService() {
        super();
        authentication = new PasswordAuthentication("root", "root");
        Properties props = new Properties();
        props.put("mail.user", "admin");
        props.put("mail.host", "localhost"); // TODO: this needs to be changed to whatever the mail server host is on the vm
        props.put("mail.debug", "false");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        session = Session.getInstance(props, this);
    }

    /*
     * Retrieve the instance of the NotificationService. This
     * method is thread-safe.
     */
    public static NotificationService instance(){
        if (instance == null) {
            synchronized (padlock){
                instance = new NotificationService();
            }
        }
        return instance;
    }

    /*
     * Send an email message to a single user.
     */
    public void postMail( String recipient, String subject, String message ,
            String from) throws MessagingException
    {
        System.out.println("SENDING message from " + from + " to " + recipient);
        MimeMessage msg = new MimeMessage(session);
        msg.addRecipients(Message.RecipientType.TO, recipient);
        msg.setSubject(subject);
        msg.setText(message);
        Transport.send(msg);
    }
}

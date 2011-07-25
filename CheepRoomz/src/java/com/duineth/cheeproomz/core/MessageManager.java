/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.core;

import com.sun.javadoc.SerialFieldTag;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dewmal
 */

public class MessageManager implements Serializable{

    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    /** Creates a new instance of MessageManager */
    public MessageManager() {
    }

    public void sendEmail(String from, String fromName, String to, String toName, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from, fromName));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to, toName));
        msg.setHeader("Content-Type", "text/html; charset=utf-8");
        msg.setSubject(subject);
        msg.setText(body);
        Transport.send(msg);
    }
}

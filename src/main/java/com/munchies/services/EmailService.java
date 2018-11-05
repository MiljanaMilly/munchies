package com.munchies.services;

import com.munchies.model.Order;
import com.munchies.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailService {

    void sendEmail(Order order) throws MessagingException;


}

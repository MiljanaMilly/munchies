package com.munchies.services;

import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.util.List;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public void sendEmail(Order order) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        Context context = new Context();
        context.setVariable("total", totalPrice(order.getOrderItems()));
        context.setVariable("order", order);
        context.setVariable("orders", order.getOrderItems());
        String html = templateEngine.process("EmailOrders", context);

        mimeMessageHelper.setTo(order.getRestaurant().getEmail());
        mimeMessageHelper.setSubject("Hello");
        mimeMessageHelper.setText(html, true);
        javaMailSender.send(message);

    }

    public static Double totalPrice(@NotNull List<OrderItem> orderItems) {
        Double total = 0d;
        for (OrderItem o : orderItems) {
            total += o.getPrice();
        }
        return total;

    }
}

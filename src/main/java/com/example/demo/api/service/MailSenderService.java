package com.example.demo.api.service;


/**
 * <p>
 * Descripción:
 * </p>
 * Interface asociado al servicio de envio de mail.
 *
 * @author Gustavo A. Arellano (GAA)
 * @version 1.0-SNAPSHOT
 */
public interface MailSenderService {

    /**
     * Envio del chatbot por medio de JavaMailSender.
     *
     * @param to      Cadena con los correos electronicos a quienes se desea enviar
     *                el correo generado
     * @param subject Cadena con el titulo del correo electronico
     * @param body    Cadena con el cuerpodo del mensaje de correo
     * @return a {@link java.lang.String} object.
     */
    String sendHtmlMail(String to, String subject, String body);

}

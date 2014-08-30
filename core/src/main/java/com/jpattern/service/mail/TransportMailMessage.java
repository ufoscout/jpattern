package com.jpattern.service.mail;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.jpattern.service.mail.message.ITransportTextMessage;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 21:34:12
 *
 * @version $Id$
 */
public class TransportMailMessage {

    private ITransportTextMessage _subject;
    private  Multipart _multipart = null;

    
    public TransportMailMessage() {
        _multipart = new MimeMultipart();
        
    }

    public void subject(ITransportTextMessage aSubject) {
        _subject = aSubject;
    }

    public ITransportTextMessage subject() {
            return _subject;
    }
    
    public boolean body(ITransportTextMessage aBody) {
        BodyPart bodyPart = new MimeBodyPart();
        try {
            bodyPart.setText( aBody.content() );
            _multipart.addBodyPart(bodyPart);
            return true;
        }
        catch (MessagingException e) {
            return false;
        }
                
    }

    public Multipart asMultipart() {
        return _multipart;
    }
   



//  crea l'allegato Word
    /*
  DataSource source = new FileDataSource( "Documento.doc" );
  BodyPart messageBodyPart2 = new MimeBodyPart();
  messageBodyPart2.setDataHandler( new DataHandler(source) );
  messageBodyPart2.setFileName( "Documento.doc" );
  */


    
    
}

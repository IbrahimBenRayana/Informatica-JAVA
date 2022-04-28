/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;



import Models.Diet;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author NMEDIA
 */
public class sendSMS {
    
            public static final String ACCOUNT_SID = System.getenv("");
    public static final String AUTH_TOKEN = System.getenv("");

    public static void sendSMS(Diet p) {
        Twilio.init("", "");
        Message message = Message.creator(new PhoneNumber(""),
        new PhoneNumber(""),
        "Hello Sir Your DIET is added you can check it : "+p.getContent()).create();
       

        System.out.println(message.getSid());
    }
    
}

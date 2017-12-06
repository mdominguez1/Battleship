package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import common.MessageListener;
import common.MessageSource;

public class PrintStreamMessageListener implements MessageListener{

    private PrintStream out;
    
    @Override
    public void messageReceived(String message, MessageSource source) {
     
            out.println(message);
            
            
        }
    

    @Override
    public void sourceClosed(MessageSource source) {
       source.removeMessageListener(this);
        
    }

}

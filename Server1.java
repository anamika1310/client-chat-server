import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sweety
 */
public class Server1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        // TODO code application logic here
          try{
        ServerSocket sersock=new ServerSocket(3000);
        System.out.println("Server is ready for chatting");
        Socket sock=sersock.accept();
        InputStream istream = sock.getInputStream();
        OutputStream ostream = sock.getOutputStream();
        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
               while(true){
                 try{
                 BufferedReader receiveRead =new BufferedReader(new InputStreamReader(istream));
                 String receiveMessage;
                
                    if((receiveMessage = receiveRead.readLine())!=null){
                        System.out.println("received message:"+receiveMessage);
                    }
                    else if((receiveMessage = receiveRead.readLine()).contains("bye")){
                        System.exit(0);
                    }
                    //To change body of generated methods, choose Tools | Templates.
              
                
                    Thread.sleep(5000);
                 }
                 catch(Exception e){
                      System.out.println("Client is offline!");
                      System.exit(0);
                 }
               }
            }
        });
        th.start();
        Thread t=new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                try{
                BufferedReader keyRead=new BufferedReader(new InputStreamReader(System.in));
             
                PrintWriter pwrite= new PrintWriter(ostream,true);
                String sendMessage="";
                sendMessage = keyRead.readLine();
                Thread.sleep(5000);
                pwrite.println(sendMessage);
                pwrite.flush();
                if(sendMessage.contains("bye"))
                    System.exit(0);
                }
                catch(Exception e){
                     System.out.println("Client is offline!");
                     System.exit(0);
                }
               
              }
               
            }
        });
        t.start(); 
        }
        catch(Exception e){
            System.out.println("client is offline!");
            System.exit(0);
        }    
    }
    
}
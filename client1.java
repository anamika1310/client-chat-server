import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sweety
 */
public class client1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        // TODO code application logic here
         try{
        Socket sock=new Socket("127.0.0.1",3000);
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
                      System.out.println("No server");
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
                     System.out.println("No server");
            System.exit(0);
                }
               
              }
               
            }
        });
        t.start();     
        }
        catch(Exception e){
            System.out.println("No server");
            System.exit(0);
        }
    }
    
}
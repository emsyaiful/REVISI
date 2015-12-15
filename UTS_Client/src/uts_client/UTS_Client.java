/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author UUL
 */
public class UTS_Client {

    /**
     * @param args the command line arguments
     */
    private static int hitung(String response){
        String[] soal = response.split(" ");
        int angka1 = Integer.parseInt(soal[0]);
        int angka2 = Integer.parseInt(soal[2]);
        if(soal[1].equalsIgnoreCase("+")){
            return angka1+angka2;
        }else if(soal[1].equalsIgnoreCase("-")){
            return angka1-angka2;
        }else if(soal[1].equalsIgnoreCase("x")){
            return angka1*angka2;
        }else if(soal[1].equalsIgnoreCase("/")){
            return angka1/angka2;
        }else if(soal[1].equalsIgnoreCase("mod")){
            return angka1%angka2;
        }else
            return 0;
    }
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.151.43.147", 6666);
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = socket.getOutputStream();
        Scanner sc = new Scanner(System.in);
        
        String command, response, result;
        command="Username:"+sc.nextLine()+"\n";
        os.write(command.getBytes());
        while (true) {            
            response = br.readLine();
            //System.out.println(response
            String[] temp;
            temp = response.split(" ");
            if(response.endsWith("?")){
                //System.out.println(response);
                int hasilHitung = hitung(response);
                result = "Result:"+hasilHitung+"\n";
                System.out.println(result);
                os.write(result.getBytes());
                os.flush();
            }else{
                //System.out.println(response);
                if(response.equalsIgnoreCase("Hash:")){    
                }else if(response.endsWith("")){
                    System.out.println(response);
                }
            }
        }
        
        //System.out.println(hasilHitung);
  
    }
}


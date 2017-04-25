/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookpad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.org.mozilla.javascript.internal.json.JsonParser;


/**
 *
 * @author Hoang Pham
 */
class Person {
    private String name;
    private String id;
    private Long[] friend_list;
    private int numFriends;
    
    public Person(String id) throws IOException, ParseException {
        this.id = id;
        friend_list = new Long[1000];
        getInfo();
        
    }
    
    public void getInfo()throws IOException, ParseException{
        try {
            URL yahoo = new URL("http://fg-69c8cbcd.herokuapp.com/user/"+id);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null){
//                System.out.println(inputLine);
                sb.append(inputLine);
               
            }
//            System.out.println(sb);
            in.close();
            JSONParser parser = new JSONParser();
            JSONObject jobject = (JSONObject) parser.parse(sb.toString());
            this.name = (String) jobject.get("name");
            JSONArray friend = (JSONArray) jobject.get("friends");
            
            for(int i = 0; i <friend.size(); i++){
//                Integer anId = (int) (long) friend.get(i);
                friend_list[i] = (Long) friend.get(i);
                numFriends = i+1;
            }
//                System.out.println(friend.get(i));
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cookpad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void getFriends(int what) throws IOException, ParseException {
        String padding ="";
        if(what != 0){
            padding = "    ";
        }else{
            System.out.println("Friend list: "+numFriends);
        }
        
        for(int i =0; i<this.numFriends; i++){
            Person p;
            p = new Person(friend_list[i].toString());
            System.out.println(padding+p.getName());
            if(what == 0){
                System.out.println("Friend of friend ("+p.getName()+")");
                p.getFriends(1);
            }
         
        }
    }
    
    public String getName() {
        return this.name;
    }


   
}

package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App
{
    public static String search(String s1,ArrayList<Integer> a1,ArrayList<Integer> a2, int e) {
      int n=s1.length();
      if(a1.size()!=n || a2.size()!=n)return a1.size() + " " + a2.size() + " " + n + "Length of the integer arrays must be equal to the length of the string";
      for(int i=0;i<n;i++){
        if(s1.charAt(i)<'a' || s1.charAt(i)>'z')return "Message must only contain lowercase english letters!";
      }
      String ss="";
      for(int i=0;i<n;i++){
        int at=s1.charAt(i)-'a'+a1.get(i)*a2.get(i);
        at%=26;
        ss+=(char)((char)at+'a');
      }
      e%=n;
      String sss="";
      for(int i=e;i<n;i++)sss+=ss.charAt(i);
      for(int i=0;i<e;i++)sss+=ss.charAt(i);
      return sss;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Welcome to my encryption website!");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));
          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          String st=sc1.next();
          String input2 = req.queryParams("input2");
          java.util.Scanner sc2 = new java.util.Scanner(input2);
          sc2.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          while (sc2.hasNext())
          {
            int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          System.out.println(inputList);
          //
          String input3 = req.queryParams("input3");
          java.util.Scanner sc3 = new java.util.Scanner(input3);
          sc3.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
          while (sc3.hasNext())
          {
            int value = Integer.parseInt(sc3.next().replaceAll("\\s",""));
            inputList2.add(value);
          }
          System.out.println(inputList2);


          String input4 = req.queryParams("input4").replaceAll("\\s","");
          int input4AsInt = Integer.parseInt(input4);

          String result = App.search(st,inputList,inputList2, input4AsInt);

         Map map = new HashMap();
          map.put("result", result);
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) -> {
              Map map = new HashMap();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}


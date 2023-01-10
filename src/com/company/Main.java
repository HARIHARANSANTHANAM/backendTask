package com.company;
import java.util.*;
import java.io.*;

public class Main {

    Map<String,List<String>> category=new HashMap<>();
    Map<Integer,Float> ratingsLookup=new HashMap<>();
    private int  calculateHike(Integer empid,Integer salary) {

        if(ratingsLookup.containsKey(empid)){
            Float ratings=ratingsLookup.get(empid);
            if(ratings>2 && ratings<=4)
            {
                return (int) (salary+((15.0/100)*salary));
            }
            else if(ratings>4 && ratings<=5){
                return (int) (salary+((25.0/100)*salary));
            }
            return salary;
        }

        return 0;
    }
    public void incrementSalary(String masterFile,String ratingsfilename){
        try {
            writeEmp(masterFile);
            BufferedReader in = new BufferedReader(new FileReader(ratingsfilename));
            String str;
            Integer empid;
            Float ratings;
            in.readLine();
            while ((str = in.readLine()) != null) {
            String [] arr = str.split(",");
            empid = Integer.parseInt(arr[0].trim());
            ratings= Float.parseFloat(arr[1].replaceAll(";$", ""));

            if (ratingsLookup.containsKey(empid)) {
                ratingsLookup.put(empid, ratings);
            } else {
            ratingsLookup.put(empid, ratings);
            }

            }
            for (Map.Entry<String, List<String>> entry : category.entrySet()) {
                FileWriter writer = new FileWriter(entry.getKey() + "_task2.txt");


                for (String employee : entry.getValue()) {
                    String data[]=employee.split(",");
                    data[3]=data[3].replaceAll(";$", "");
                    data[3]=String.valueOf(calculateHike(Integer.parseInt(data[0].trim()),Integer.parseInt(data[3].trim())));
                    employee=data[0]+","+data[1]+","+data[2]+","+data[3];
                    System.out.println(employee);
                    writer.write(employee + System.lineSeparator());
                }
               writer.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.print(e);
        }
    }

    public void CategorizeEmp(String filename)
    {
        try {
            writeEmp(filename);
            for (Map.Entry<String, List<String>> entry : category.entrySet()) {
                FileWriter writer = new FileWriter(entry.getKey() + ".txt");
                for (String employee : entry.getValue()) {
                    writer.write(employee + System.lineSeparator());
                }
                writer.close();
            }
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    public  void writeEmp(String filename){
        try {
            String empid, firstname, lastname,designation,salary;
         //   Map<String, List<String>> category = new HashMap<>();
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            in.readLine();
            while ((str = in.readLine()) != null) {
                String[] arr = str.split(",");
                empid = arr[0];
                firstname = arr[1];
                lastname = arr[2];
                designation = arr[5];
                salary=arr[6];
                salary.replaceAll(";$", "");
                String data = empid + "," + firstname + lastname + "," + designation+","+salary;

                if (category.containsKey(designation)) {
                    List<String> emp = new ArrayList<>(category.get(designation));
                    emp.add(data);
                    category.put(designation, emp);
                } else {
                    List<String> emp = new ArrayList<>();
                    emp.add(data);
                    category.put(designation, emp);
                }

            }


            in.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
	// write your code here
        Main emp=new Main();
         //   emp.CategorizeEmp("employees2.txt");
        //increment the salary based on ratings provided
            emp.incrementSalary("employees2.txt","ratings.txt");
    }
}

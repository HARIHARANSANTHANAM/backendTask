package com.company;
import java.util.*;
import java.io.*;

public class Main {

    Map<String,List<String>> category=new HashMap<>();
//    private int setIncrementSalary(int salary){
//        try {
//            for (Map.Entry<String, List<String>> entry : category.entrySet()) {
//
//                for (String employee : entry.getValue()) {
//                    writer.write(employee + System.lineSeparator());
//                }
//                writer.close();
//            }
//        }
//        catch(Exception e)
//        {
//            System.out.print(e);
//        }
//    }
//    public void incrementSalary(String filename){
//        try {
//            writeEmp(filename);
//            for (Map.Entry<String, List<String>> entry : category.entrySet()) {
//               // FileWriter writer = new FileWriter(entry.getKey() + ".txt");
//
//
//                for (String employee : entry.getValue()) {
//                    String data[]=employee.split(",");
//                    data[3].substring(0,data[3].length()-1);
//                    System.out.println(Integer.parseInt(data[3]));
//                   // System.out.print(employee);
//                  //  incrementSalary();
//               //     writer.write(employee + System.lineSeparator());
//                }
//              //  writer.close();
//            }
//        }
//        catch(Exception e)
//        {
//            System.out.print(e);
//        }
//    }

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

            while ((str = in.readLine()) != null) {
                String[] arr = str.split(",");
                empid = arr[0];
                firstname = arr[1];
                lastname = arr[2];
                designation = arr[5];
                salary=arr[6];
                salary.substring(0, salary.length() - 1);
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
            emp.CategorizeEmp("employees2.txt");
         //   emp.incrementSalary("employees2.txt");
    }
}

package com.cfttest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("неверное количество аргументов error");
            System.exit(0);
        }
        String  line;                                               //строка
        double ab, bc, ac,                                          //длина сторон
                S = 0, Smax = 0;                                    //площадь
        int     ax, ay, bx, by, cx, cy;                             //координаты
        List<String> maxTriangle = new ArrayList<>(6);

        try(BufferedReader br = new BufferedReader(new FileReader("./src/" + args[0]))){

            while((line = br.readLine()) != null){

                List<String> strArr = Arrays.asList(line.split(" "));
                if(strArr.size() != 6)
                    continue;
                try {
                    ax = Integer.parseInt(strArr.get(0));
                    ay = Integer.parseInt(strArr.get(1));
                    bx = Integer.parseInt(strArr.get(2));
                    by = Integer.parseInt(strArr.get(3));
                    cx = Integer.parseInt(strArr.get(4));
                    cy = Integer.parseInt(strArr.get(5));
                } catch (NumberFormatException e) {
                    continue;
                }

                ab = sideLength(ax, ay, bx, by);
                bc = sideLength(bx, by, cx, cy);
                ac = sideLength(ax, ay, cx, cy);
                S = areaTriangle(ab, bc, ac);

                if(S > Smax){
                    Smax = S;
                    maxTriangle = new ArrayList<>(strArr);
                }
            }
            writeMaxTriangle(maxTriangle, args[1]);
        }
        catch (FileNotFoundException e){
            System.out.println("Не удается найти указанный файл error1");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла error 2");
            System.exit(0);
        }
    }

    //длина стороны треугольника
    private static double sideLength(int ax, int ay, int bx, int by){
        return Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay));
    }

    //площадь треугольника
    private static double areaTriangle(double ab, double bc, double ac){
        if(ab == bc)
            return ac * 0.5 * Math.sqrt((ab + ac * 0.5)*(ab - ac * 0.5));
        if(ab == ac)
            return  bc * 0.5 * Math.sqrt((ab + bc * 0.5)*(ab - bc * 0.5));
        if(ac == bc)
            return ab * 0.5 * Math.sqrt((ac + ab * 0.5)*(ac - ab * 0.5));
        return 0;
    }

    //запись координат в файл
    private static void writeMaxTriangle(List<String> maxTriangle, String out){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("./src/" + out))) {
            if(maxTriangle.size()!=0){
                bw.write(maxTriangle.get(0));
                for (int i = 1; i < 6; i++) {
                    bw.write(" " + maxTriangle.get(i));
                }
            }else{
                bw.write("");
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Не удается найти указанный файл error3");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Ошибка записи файла error4");
            System.exit(0);
        }
    }
}

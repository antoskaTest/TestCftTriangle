package com.cfttest;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MaxAreaTriangle {

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("неверное количество аргументов");
            System.exit(0);
        }
        double  s = 0, sMax = 0;                                    //площадь
        String  line;                                               //строка
        String  maxTriangle = "";                                   //макс площадь

        try(BufferedReader br = new BufferedReader(new FileReader("./src/resources/" + args[0]))){
            while((line = br.readLine()) != null){
                try {
                    s = areaTriangle(line);
                }catch (NumberFormatException e){
                    continue;
                }
                if(s > sMax){
                    sMax = s;
                    maxTriangle = line;
                }
            }
            writeMaxTriangle(maxTriangle, args[1]);
        }
        catch (FileNotFoundException e){
            System.out.println("Не удается найти указанный файл");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    //площадь треугольника
    private static double areaTriangle(String coords){
        List<String> coordsArr = Arrays.asList(coords.split(" "));
        if(coordsArr.size() != 6){
            throw new NumberFormatException();
        }
        //координаты
        int ax = Integer.parseInt(coordsArr.get(0));
        int ay = Integer.parseInt(coordsArr.get(1));
        int bx = Integer.parseInt(coordsArr.get(2));
        int by = Integer.parseInt(coordsArr.get(3));
        int cx = Integer.parseInt(coordsArr.get(4));
        int cy = Integer.parseInt(coordsArr.get(5));

        //длина стороны
        double ab =  Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay));
        double bc =  Math.sqrt((ax-bx)*(ax-bx) + (ay-by)*(ay-by));
        double ac =  Math.sqrt((cx-ax)*(cx-ax) + (cy-ay)*(cy-ay));

        //площадь
        if(ab == bc)
            return ac * 0.5 * Math.sqrt((ab + ac * 0.5)*(ab - ac * 0.5));
        if(ab == ac)
            return  bc * 0.5 * Math.sqrt((ab + bc * 0.5)*(ab - bc * 0.5));
        if(ac == bc)
            return ab * 0.5 * Math.sqrt((ac + ab * 0.5)*(ac - ab * 0.5));
        return 0;
    }

     //запись координат в файл
     private static void writeMaxTriangle(String maxTriangle, String out){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("./src/resources/" + out))) {
            if(maxTriangle.length() != 0){
                bw.write(maxTriangle);
            }else{
                bw.write("");
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Не удается найти указанный файл");
        }
        catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }
}

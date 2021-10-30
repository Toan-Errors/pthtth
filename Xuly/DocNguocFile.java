package Xuly;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class DocNguocFile {

    public static String ReadFile(String path){
        String data= "";
        try {
            File file = new File(path);
            Scanner myReader;
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    
    public static void main(String[] args) {
        String path = "C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\Xuly\\data6.txt";
        System.out.println("Nội dung đảo ngược: \n" + ReadFileReverse(path));

    }

    public static String ReadFileReverse(String path){
        String data= "";
        try {
            File file = new File(path);
            Scanner myReader;
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                data += Reverse(myReader.nextLine()) + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    public static String Reverse(String str){
        StringBuilder s = new StringBuilder(str);
        return s.reverse().toString();
    }
}

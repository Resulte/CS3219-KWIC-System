package cn.edu.bupt.KWICSystem.input.impl;

import cn.edu.bupt.KWICSystem.input.Input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class InputImpl implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine(){
        return scanner.nextLine();
    }

    @Override
    public List<String> readFile(String fileName) {
        List<String> textFile = new ArrayList<>();
        Scanner input = null;
        try
        {
            input = new Scanner(new FileInputStream(fileName));

            while(input.hasNextLine())
            {
                String line = input.nextLine();

                textFile.add(line);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        finally
        {
            if (input != null) {
                input.close();
            }
        }

        return textFile;
    }

}

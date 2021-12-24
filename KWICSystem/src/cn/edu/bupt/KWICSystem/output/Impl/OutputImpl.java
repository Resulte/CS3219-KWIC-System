package cn.edu.bupt.KWICSystem.output.Impl;

import cn.edu.bupt.KWICSystem.output.Output;
import cn.edu.bupt.KWICSystem.sort.Alphabetizer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputImpl implements Output {

    /**
     * 打印运行结果
     * @param alphabetizer
     */
    @Override
    public List<String> print(Alphabetizer alphabetizer) {
        List<String> res = new ArrayList<>();

        System.out.println("运行结果:");
        for(int i = 0; i < alphabetizer.getLineCount(); i++) {
            res.add(alphabetizer.getLineAsString(i));
            System.out.println(alphabetizer.getLineAsString(i));
        }

        return res;
    }

    /**
     * 写入运行结果到文件
     * @param alphabetizer
     */
    @Override
    public void write(Alphabetizer alphabetizer) {
        File writename = new File("output.txt");

        try {
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            for(int i = 0; i < alphabetizer.getLineCount(); i++) {
                out.write(alphabetizer.getLineAsString(i) + "\r\n");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("运行结果输出在这个文件中：" + writename.getAbsolutePath());
    }
}

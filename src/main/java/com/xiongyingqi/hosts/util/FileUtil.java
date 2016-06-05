package com.xiongyingqi.hosts.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author <a href="http://xiongyingqi.com">瑛琪</a>
 * @version 2016-06-05 01:01
 */
public abstract class FileUtil {
    public static final String DEFAULT_CHARSET = "UTF-8";

    public static String readFile(String filePath, String charset) {
        if (filePath == null || "".equals(filePath.trim())) {
            return null;
        }

        File file = new File(filePath);
        return readFile(file, charset);
    }

    public static String readFile(File file, String charset) {
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int buffSize = 64;
            byte[] buff = new byte[buffSize];

            for (int size = fileInputStream.read(buff); size >= 0; size = fileInputStream.read(buff)) {
                byteBuffer.put(buff, 0, size);
            }
            byte[] bytes = byteBuffer.array();
            if (charset == null || "".equals(charset)) {
                charset = DEFAULT_CHARSET;
            }
            String content = new String(bytes, Charset.forName(charset));
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToFile(String file, String content) {
        writeToFile(new File(file), content);
    }

    public static void writeToFile(File file, String content) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter == null) {
                return;
            }
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        String s = readFile(System.getenv("windir") + "\\system32\\drivers\\etc\\hosts", DEFAULT_CHARSET);
        System.out.println(s);
    }

}

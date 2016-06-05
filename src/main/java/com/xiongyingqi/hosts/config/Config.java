package com.xiongyingqi.hosts.config;

import com.xiongyingqi.hosts.util.FileUtil;

/**
 * @author <a href="http://xiongyingqi.com">瑛琪</a>
 * @version 2016-06-05 00:35
 */
public class Config {
    private String filePath;

    public Config(String filePath) {
        this.filePath = filePath;
    }

    public String getContent(String charset){
        return FileUtil.readFile(filePath, charset);
    }
}

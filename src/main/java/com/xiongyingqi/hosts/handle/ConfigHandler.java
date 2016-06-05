package com.xiongyingqi.hosts.handle;

import com.xiongyingqi.hosts.adapter.SystemAdapter;
import com.xiongyingqi.hosts.config.Config;
import com.xiongyingqi.hosts.util.FileUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * @author <a href="http://xiongyingqi.com">瑛琪</a>
 * @version 2016-06-05 00:36
 */
public class ConfigHandler {
    public static final String CONFIG_FOLDER = "hosts-bak";

    public String[] listConfigs() {
        File file = new File(CONFIG_FOLDER);
        if (!file.isDirectory()) {
            return new String[]{};
        }
        File[] hostses = file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("hosts");
            }
        });
        String[] configs = new String[hostses.length];
        for (int i = 0; i < hostses.length; i++) {
            File hosts = hostses[i];
            String name = hosts.getName();
            String config = name.replace("hosts\\.", "");
            configs[i] = config;
        }
        return configs;
    }

    public String bakFileToConfig(String name, String content) {
        File folder = new File(CONFIG_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(CONFIG_FOLDER, "hosts." + name);
        FileUtil.writeToFile(file, content);
        return file.getAbsolutePath();
    }


    public void switchToConfigs(List<Config> configList, String charset) {
        String hostsFile = SystemAdapter.getHostsFile();
        StringBuilder builder = new StringBuilder();
        for (Config config : configList) {
            String content = config.getContent(charset);
            builder.append(content).append(System.lineSeparator());
        }

    }

    public static void main(String[] args) {
        ConfigHandler handler = new ConfigHandler();
        String content = FileUtil.readFile(System.getenv("windir") + "\\system32\\drivers\\etc\\hosts", "utf-8");
        String s = handler.bakFileToConfig("a", content);
        System.out.println(s);
    }
}

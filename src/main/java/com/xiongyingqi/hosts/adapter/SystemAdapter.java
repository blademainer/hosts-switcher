package com.xiongyingqi.hosts.adapter;

/**
 * @author <a href="http://xiongyingqi.com">瑛琪</a>
 * @version 2016-06-05 00:40
 */
public class SystemAdapter {

    /**
     * 获取host文件路径
     *
     * @return
     */
    public static String getHostsFile() {
        String fileName;
        // 判断系统
        if (OSInfo.getOSName().equals(EPlatform.Windows)) {
            fileName = System.getenv("windir") + "\\system32\\drivers\\etc\\hosts";
        } else {
            fileName = "/etc/hosts";
        }
        return fileName;
    }
}

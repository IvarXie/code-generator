package com.xie.mybatis.generator.utils;

import java.io.File;

/**
 * Title: 文件util
 * Description: </p>
 * Email is Wenbo.Xie@b-and-qchina.com<
 * Company: http://www.bthome.com
 *
 * @author xie.wenbo
 * @date 2019-10-18 18:07
 */
public class FileUtils {
    /**
     * @author xie.wenbo
     * @date Created on 2019/10/18 18:08
     * @Description 删除文件
     * @param file
     * @return boolean
     */
    public static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isFile()) {
            return file.delete();
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
            return file.delete();
        }
    }

    /**
     * @author xie.wenbo
     * @date Created on 2019/10/18 18:08
     * @Description 删除文件
     * @param filePath 文件地址
     * @return boolean
     */
    public static boolean delFile(String filePath) {
        return delFile(new File(filePath));
    }

}

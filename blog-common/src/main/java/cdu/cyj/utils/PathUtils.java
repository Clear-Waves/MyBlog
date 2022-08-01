package cdu.cyj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PathUtils {

    public static String generateFilePath(String filename){
        // 日期作为路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String dirPath = sdf.format(new Date());
        // UUID作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        // 获取源文件的类型
        String fileType = filename.substring(filename.lastIndexOf("."));
        // 拼接path
        return dirPath + uuid + fileType;
    }

}

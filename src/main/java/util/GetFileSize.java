package util;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * User: ViaPro
 * Date: 11/6/13
 * Time: 1:26 PM

        File[] files = root.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                if (pathname.isDirectory() && pathname.isHidden()) { // 去掉隐藏文件夹
                    return false;
                }

                if (pathname.isFile() && pathname.isHidden()) {// 去掉隐藏文件
                    return false;
                }
                return true;
            }
        });

 */
public class GetFileSize
{
    public long getFileSizes(File f) throws Exception{//取得文件大小
        long s=0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
            s= fis.available();
        } else {
            f.createNewFile();
            System.out.println("文件不存在");
        }
        return s;
    }
    // 递归
    public long getFileSize(File f)throws Exception//取得文件夹大小
    {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++)
        {
            if (flist[i].isDirectory())
            {
                size = size + getFileSize(flist[i]);
            } else
            {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    public String FormetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1024*1024) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1024*1024*1024) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public long getlist(File f){//递归求取目录文件个数
        long size = 0;
        File flist[] = f.listFiles();
        size=flist.length;
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getlist(flist[i]);
                size--;
            }
        }
        return size;


    }

    public static void main(String args[])
    {
        GetFileSize g = new GetFileSize();
        long startTime = System.currentTimeMillis();
        try
        {
            long l = 0;
            String path = "C:\\Dev\\GitHub\\ThinkingInJava";
            File ff = new File(path);
            if (ff.isDirectory()) { //如果路径是文件夹的时候
                System.out.println("文件个数           " + g.getlist(ff));
                System.out.println("目录");
                l = g.getFileSize(ff);
                System.out.println(path + "目录的大小为：" + g.FormetFileSize(l));
            } else {
                System.out.println("     文件个数           1");
                System.out.println("文件");
                l = g.getFileSizes(ff);
                System.out.println(path + "文件的大小为：" + g.FormetFileSize(l));
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总共花费时间为：" + (endTime - startTime) + "毫秒...");
    }
}
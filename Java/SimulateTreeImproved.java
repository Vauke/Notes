package com.vauke.algs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vauke on 9/16/18.
 */
public class SimulateTreeImproved {
    private static List<String> fill = new ArrayList<>(); // 用来绘制结构线
    private static int depth = 0; // 用来控制访问的文件夹深度

    public static void main(String[] args) throws IOException {
        File f = new File(".");
        System.out.println(f.getCanonicalPath());
        draw(f);
    }

    private static void draw(File f) {
        // 获取文件数组
        File[] files = f.listFiles();
        // 判断数组是否为空， 为空说明f是文件或空目录
        if (files != null) {
            fill.add(depth, "  |"); // 每进入一个文件夹就绘制一个"  |"

            for (int i = 0; i < files.length; i++) { // 遍历当前目录
                System.out.print(fill.toString().replace("[", "").replace("]", "").replace(", ", "")); // 打印文件名前， 将文件结构线打印

                System.out.println("_ " + files[i].getName()); // 打印文件名

                if (i != files.length - 1) { // 如果不是最后一个文件或文件夹时，不改变当前文件深度的结构
                    if (files[i].isDirectory()) {
                        depth++; // 进入文件夹， 深度+1
                        draw(files[i]);
                    }
                } else { // 当前目录最后一个文件或文件夹时， 改变当前深度的文件结构线（去除名称前面多余的"|"）
                    if (files[i].isDirectory()) {
                        fill.remove(depth);
                        fill.add(depth, "   ");
                        depth++;
                        draw(files[i]);
                    }
                }
            }
            fill.remove(depth); // 当前目录遍历完成后， 去除当前表示当前深度的"|"
            depth--;
        }
    }
}

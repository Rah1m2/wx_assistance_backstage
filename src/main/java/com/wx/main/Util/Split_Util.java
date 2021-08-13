package com.wx.main.Util;

import com.alibaba.fastjson.JSON;
import com.wx.main.POJO.Posting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Split_Util {
    //分割图片工具
    public static void splitUrl(List<Posting> postingList){
        Scanner scanner;
        List <String> imageList = new ArrayList<String>();
        for (Posting posting : postingList) {
            if (posting.getArticle_image() == null)
                continue;
            scanner = new Scanner(posting.getArticle_image()).useDelimiter(" ");
            while (scanner.hasNext())
                imageList.add(scanner.next());
            posting.setArticle_image(JSON.toJSONString(imageList));
            imageList.clear();
        }
    }
}

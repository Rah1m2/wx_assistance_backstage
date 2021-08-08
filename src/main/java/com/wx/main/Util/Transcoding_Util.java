package com.wx.main.Util;


import com.alibaba.fastjson.JSONObject;
import com.wx.main.Model.Posting;
import com.wx.main.Model.User;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//@SuppressWarnings("all")
public class Transcoding_Util {
    public static List TranscodePosting(List list){
        for (Object aList : list) {
            try {
                Posting posting = (Posting) aList;
                ((Posting) aList).setArticle_content(URLEncoder.encode(posting.getArticle_content(), "UTF-8"));
                ((Posting) aList).setArticle_title(URLEncoder.encode(posting.getArticle_title(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

//    for (int i=0;i<list.size();i++) {
//        try {
//            Posting posting = (Posting) list.get(i);
//            ((Posting) list.get(i)).setArticle_content(java.net.URLEncoder.encode(posting.getArticle_content(),"UTF-8"));
//            ((Posting) list.get(i)).setArticle_title(java.net.URLEncoder.encode(posting.getArticle_title(),"UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }

    public static JSONObject Transcode(JSONObject jObj){
        Iterator iterator = jObj.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey().toString());
            System.out.print(entry.getValue().toString());
            System.out.println();
            try {
                jObj.put(entry.getKey().toString(),java.net.URLDecoder.decode(entry.getValue().toString(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(entry.getValue().toString());
            System.out.println();
        }
        return jObj;
    }
}

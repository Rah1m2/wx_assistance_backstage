package com.wx.main.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wx.main.DAO.PostingDAO;
import com.wx.main.POJO.Posting;
import com.wx.main.POJO.QueryParams;
import com.wx.main.Service.PostingService;
import com.wx.main.Util.Transcoding_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service(value = "postingServiceImpl")
public class PostingServiceImpl implements PostingService {

    private PostingDAO postingDAO;

    public PostingServiceImpl() {
    }

    @Autowired
    public PostingServiceImpl(PostingDAO postingDAO) {
        this.postingDAO = postingDAO;
    }

    /**
     * 获取首页的帖子封面信息
     * @param queryParams 请求参数
     * @return 返回包含数据的json串
     */
    public String getRequiredPostings(QueryParams queryParams) {
        //嵌套json
        JSONObject jsonObject = new JSONObject();

        //页面下标（从第几页开始查询）
        jsonObject.put("pageNum",queryParams.getPageNum());

        //查询的页数
        jsonObject.put("pageSize",queryParams.getPageSize());

        //分页查询的对象
        Page page = PageHelper.startPage(queryParams.getPageNum(),queryParams.getPageSize());

        if (queryParams.getSort_id() == 0 && queryParams.getTab_id() == 0) //0-0代表选择全部
            jsonObject.put("articleList",postingDAO.getPostingList());
        else if(queryParams.getSort_id() == 0 && queryParams.getTab_id() == 1) //0-1代表按时间排序
            jsonObject.put("articleList",postingDAO.getPostingListOrderByTime());
        else
            jsonObject.put("articleList",postingDAO.getPostingBySortId(queryParams.getSort_id()));

        //帖子总数
        jsonObject.put("articleTotal",page.getTotal());

        //单独获取帖子list
        List <Posting> postingList = ((List<Posting>) jsonObject.get("articleList"));

        //分割图片的url转换为json串
        splitUrl(postingList);

        //返回String串,注解会自动转换为json
        return JSON.toJSONString(jsonObject);
    }

    /**
     * 获取帖子的内容信息（图片，文字）
     * @param article_id 帖子的id
     * @return 返回json串
     */
    public String getPostingDetail(int article_id){
        return JSON.toJSONString(postingDAO.getPostingByArticleId(article_id));
    }

    /**
     * 存储新增的帖子
     * @param posting 实体类
     * @return 插入成功返回"YES",否则返回"NO"
     */
    public String insertSinglePosting(Posting posting) {
        if (postingDAO.insertSinglePosting(posting) == 0)
            return "NO";
        return "YES";
    }

    /**
     * 获取帖子的分类信息
     * @return 返回json串
     */
    public String getSortInfo() {
        return JSON.toJSONString(postingDAO.getSortInfo());
    }

    //分割图片工具
    private void splitUrl(List<Posting> postingList){
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

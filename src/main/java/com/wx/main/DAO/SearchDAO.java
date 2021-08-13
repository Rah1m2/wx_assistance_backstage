package com.wx.main.DAO;

import com.wx.main.POJO.Posting;

import java.util.List;

public interface SearchDAO {

    List<Posting> dynamicSearch(String query);
}

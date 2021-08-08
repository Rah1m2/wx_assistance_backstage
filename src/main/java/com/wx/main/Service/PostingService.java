package com.wx.main.Service;


import com.wx.main.Model.Posting;
import com.wx.main.Model.QueryParams;

import java.util.List;

public interface PostingService {

    String getRequiredPostings(QueryParams queryParams);

    int storeSinglePosting(Posting posting);
}

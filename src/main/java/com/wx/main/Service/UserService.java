package com.wx.main.Service;

import com.wx.main.Model.User;
import java.util.List;

public interface UserService {

    List<User> getUserList();

    int insertSingleUser(User user);

    int deleteSingleUser(User user);

    List<User> getUserByAccount(String UserAccount);

    int updateSingleUser(User user);

    //以上为测试代码，下面的才是正式的事务层逻辑
    String wxLogin(String encryptedData,String iv,String code);
}

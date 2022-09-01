package com.yyc.bot_tools.service;


import com.yyc.bot_tools.entities.Email;

import java.util.ArrayList;

public interface orderService {

    void showOrderInfo() throws Exception;

    ArrayList showGiftCardInfo(Email email) throws Exception;
}

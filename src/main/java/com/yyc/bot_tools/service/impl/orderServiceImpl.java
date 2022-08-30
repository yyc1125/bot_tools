package com.yyc.bot_tools.service.impl;

import com.yyc.bot_tools.Util.ParsingEmailUtil;
import com.yyc.bot_tools.service.orderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class orderServiceImpl implements orderService {
    @Override
    public void showOrderInfo() throws Exception {
        ParsingEmailUtil.resceive("yyc12343615@163.com","SSBEYUKOAUXQHTKW");
    }

    @Override
    public ArrayList showGiftCardInfo() throws Exception {
        return ParsingEmailUtil.resceive("yyc12343615@163.com","SSBEYUKOAUXQHTKW");
    }
}

package com.yyc.bot_tools.entities;

import lombok.Data;

import java.util.Arrays;

@Data
public class Param {
    private Profile profile;

    private int[] idList;

    @Override
    public String toString() {
        return "Param{" +
                "profile=" + profile +
                ", idList=" + Arrays.toString(idList) +
                '}';
    }
}

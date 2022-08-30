package com.yyc.bot_tools.entities;

import lombok.Data;

import java.util.Arrays;

/*
    batchUpdateProfile的入参
 */
@Data
public class Param {
    private Profile profile;

    private int[] idList;

    private String[] checkboxGroup;
    @Override
    public String toString() {
        return "Param{" +
                "profile=" + profile +
                ", idList=" + Arrays.toString(idList) +
                '}';
    }
}

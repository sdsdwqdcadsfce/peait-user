package com.peait.peaituser.result;

import lombok.Data;

import java.util.List;

@Data
public class TableResult<T> {
    private BaseResult<T> data;
    private String msg;
    private int code;

    public TableResult(Long total, List<T> data) {
        this.code = 200;
        this.msg = "success";
        this.data = new BaseResult(total, data);
    }
}

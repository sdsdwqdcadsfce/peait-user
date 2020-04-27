package com.peait.peaituser.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {
    private Long total;
    private List<T> rows;
}

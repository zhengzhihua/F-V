package com.example.administrator.vaf.api;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/20.
 */

public interface HttpRequestHandler <E> {
    public void onSuccess(E data);
    public void onSuccesss(ArrayList<Map<String, Object>> res);

    public void onSuccess(E data, int totalPages, int currentPage);

    public void onFailure(String error);
}
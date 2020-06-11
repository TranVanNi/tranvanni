package com.example.myapplication2.Callback;

import com.example.myapplication2.Model.CategoryModel;

import java.util.List;

public interface ICategoryCallbackListener {
    void onCategoryLoadSuccess(List<CategoryModel> categoryModelList) ;
    void onCategoryLoadFailed(String message);
}

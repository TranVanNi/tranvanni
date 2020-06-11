package com.example.myapplication2.ui.menu;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication2.Callback.ICategoryCallbackListener;
import com.example.myapplication2.Common.Common;
import com.example.myapplication2.Model.CategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel implements ICategoryCallbackListener {

   private MutableLiveData<List<CategoryModel>> categoryListMultable;
   private MutableLiveData<String> messageError = new MutableLiveData<>();
   private ICategoryCallbackListener categoryCallbackListener;

    public MenuViewModel() {
        categoryCallbackListener = this;
    }

    public MutableLiveData<List<CategoryModel>> getCategoryListMultable() {
       if (categoryListMultable == null)
       {
           categoryListMultable = new MutableLiveData<>();
           messageError = new MutableLiveData<>();
           loadCategories();
       }   
       return categoryListMultable;
    }

    private void loadCategories() {
        List<CategoryModel> tempList = new ArrayList<>(); //bỏ final ở trước List
        DatabaseReference categoryRef = FirebaseDatabase.getInstance().getReference(Common.CATEGORY_REF);
        categoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot itemSnapshot:dataSnapshot.getChildren())
                {
                    CategoryModel categoryModel = itemSnapshot.getValue(CategoryModel.class);
                    categoryModel.setMenu_id(itemSnapshot.getKey());
                    tempList.add(categoryModel);
                }
                categoryCallbackListener.onCategoryLoadSuccess(tempList);//mới thêm chỗ part 6 16p36s
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                categoryCallbackListener.onCategoryLoadFailed(databaseError.getMessage());
            }
        });
    }

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    @Override
    public void onCategoryLoadSuccess(List<CategoryModel> categoryModelList) {
       categoryListMultable.setValue(categoryModelList);
    }

    @Override
    public void onCategoryLoadFailed(String message) {
       messageError.setValue(message);
    }
}
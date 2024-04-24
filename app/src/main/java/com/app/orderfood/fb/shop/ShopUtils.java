package com.app.orderfood.fb.shop;

import android.util.Log;

import androidx.annotation.NonNull;

import com.app.orderfood.models.Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopUtils {

    private static final String CATEGORY = "Category";

    static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    //Add new - update
    public static void insertCategory(Category category) {

        firebaseDatabase.getReference(CATEGORY)
                .child(String.valueOf(category.getId()))
                .setValue(category)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //ok
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //fail
                    }
                });
    }

    public static void getCategoryById(long id) {
        firebaseDatabase.getReference(CATEGORY)
                .child(String.valueOf(id))
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {

                            DataSnapshot result = task.getResult();

                            if (result.exists()) {
                                Category category = result.getValue(Category.class);
                                Log.e("truongpa", "lấy dc giữ liệu có: " + category.getName());
                            } else {
                                Log.e("truongpa", "lấy dc giữ không có: ");
                            }
                        } else {
                            Log.e("truongpa", "lấy dc giữ không thành công");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("truongpa", "lỗi kết lỗi");
                    }
                });
    }

    public static void getAllCategory() {
        firebaseDatabase.getReference(CATEGORY)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        ArrayList<Category> arrayList = new ArrayList<>();

                        for (DataSnapshot item : snapshot.getChildren()) {
                            Category itemData = item.getValue(Category.class);

                            arrayList.add(itemData);
                        }

                        Log.e("truongpa", "getAllCategory - Success");

                        for (Category category : arrayList) {
                            Log.e("truongpa", "" + category.getId() + " - " + category.getName());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("truongpa", "getAllCategory - Fail");
                    }
                });

    }


    // lắng nghe sự thay đổi
    public static void getAllCategoryListener() {
        firebaseDatabase.getReference(CATEGORY)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        ArrayList<Category> arrayList = new ArrayList<>();

                        for (DataSnapshot item : snapshot.getChildren()) {
                            Category itemData = item.getValue(Category.class);

                            arrayList.add(itemData);
                        }

                        Log.e("truongpa", "getAllCategory - Success");

                        for (Category category : arrayList) {
                            Log.e("truongpa", "" + category.getId() + " - " + category.getName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("truongpa", "getAllCategory - Fail");
                    }
                });

    }


    //Add new - update
    public static void deleteCategory(long id) {
        firebaseDatabase.getReference(CATEGORY)
                .child(String.valueOf(id))
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

}

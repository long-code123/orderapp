package com.app.orderfood.fb.shop;

import android.util.Log;

import androidx.annotation.NonNull;

import com.app.orderfood.models.Category;
import com.app.orderfood.models.Foods;
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
    private static final String FOOD = "Foods";

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

    public static void insertFood(Foods food) {

        firebaseDatabase.getReference(FOOD)
                .child(String.valueOf(food.getId()))
                .setValue(food)
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
    public static void getFoodById(long id) {
        firebaseDatabase.getReference(FOOD)
                .child(String.valueOf(id))
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task1) {
                        if (task1.isSuccessful()) {

                            DataSnapshot result1 = task1.getResult();

                            if (result1.exists()) {
                                Foods foods = result1.getValue(Foods.class);
                                Log.e("truongpa", "lấy dc giữ liệu có: " + foods.getTitle());
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
    public static void getAllFood() {
        firebaseDatabase.getReference(FOOD)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot1) {

                        ArrayList<Foods> arrayList = new ArrayList<>();

                        for (DataSnapshot item : snapshot1.getChildren()) {
                            Foods itemData1 = item.getValue(Foods.class);

                            arrayList.add(itemData1);
                        }

                        Log.e("truongpa", "getAllFood - Success");

                        for (Foods foods : arrayList) {
                            Log.e("truongpa", "" + foods.getId() + " - " + foods.getTitle());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("truongpa", "getAllFood - Fail");
                    }
                });

    }
    public static void getAllFoodListener() {
        firebaseDatabase.getReference(FOOD)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot1) {

                        ArrayList<Foods> arrayList = new ArrayList<>();

                        for (DataSnapshot item : snapshot1.getChildren()) {
                            Foods itemData1 = item.getValue(Foods.class);

                            arrayList.add(itemData1);
                        }

                        Log.e("truongpa", "getAllCategory - Success");

                        for (Foods foods : arrayList) {
                            Log.e("truongpa", "" + foods.getId() + " - " + foods.getTitle());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("truongpa", "getAllCategory - Fail");
                    }
                });

    }
    public static void deleteFood(long id) {
        firebaseDatabase.getReference(FOOD)
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

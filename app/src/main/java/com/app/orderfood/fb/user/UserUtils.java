package com.app.orderfood.fb.user;

import android.util.Log;

import androidx.annotation.NonNull;

import com.app.orderfood.models.Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UserUtils {

    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static void checkInfo() {
        String userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        FirebaseDatabase.getInstance().getReference("user")
                .child(userID).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DataSnapshot result = task.getResult();
                        if (result.exists()) {
                            Category category = result.getValue(Category.class);

                        } else {

                        }
                    } else {

                    }
                })
                .addOnFailureListener(e -> {

                });
    }

}

package com.app.orderfood.login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.app.orderfood.R;
import com.app.orderfood.SplashActivity;
import com.app.orderfood.databinding.ActivityRegisterInfoBinding;
import com.app.orderfood.fb.DialogLoading;
import com.app.orderfood.models.User;
import com.app.orderfood.shiper.ShipperMainActivity;
import com.app.orderfood.shop.ShopMainActivity;
import com.app.orderfood.user.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class RegisterInfoActivity extends AppCompatActivity {

    ActivityRegisterInfoBinding binding;

    Uri uri;
    int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.role_sp, R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        binding.spRole.setAdapter(adapter1);

        binding.imgAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });


        binding.spRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = position + 1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        binding.txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == 3) {
                    if (uri == null) {
                        //Cần anhe
                        return;
                    }
                }

                DialogLoading.show(RegisterInfoActivity.this);
                if (uri != null) {
                    FirebaseStorage.getInstance().getReference()
                            .child("imagecate/" + String.valueOf(System.currentTimeMillis()))
                            .putFile(uri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Task<Uri> info = taskSnapshot.getStorage().getDownloadUrl();

                                    info.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            upData(uri.toString());
                                        }
                                    }).addOnFailureListener(e -> {
                                        Toast.makeText(RegisterInfoActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
                                        DialogLoading.hideDialog();
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterInfoActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
                                    DialogLoading.hideDialog();
                                }
                            });
                } else {
                    upData("link default");
                }
            }
        });
    }

    private void upData(String linkAvt) {
        String id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference("user")
                .child(id)
                .setValue(
                        new User(
                                id,
                                binding.edtName.getText().toString(),
                                linkAvt,
                                binding.edtPhone.getText().toString(),
                                binding.edtAddress.getText().toString(),
                                role,
                                1,
                                System.currentTimeMillis()
                        )
                ).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent();
                        switch (role) {
                            case 1:
                                intent = new Intent(RegisterInfoActivity.this, MainActivity.class);
                                break;
                            case 2:
                                intent = new Intent(RegisterInfoActivity.this, ShopMainActivity.class);
                                break;
                            case 3:
                                intent = new Intent(RegisterInfoActivity.this, ShipperMainActivity.class);
                                break;
                        }
                        DialogLoading.hideDialog();
                        Toast.makeText(RegisterInfoActivity.this, "Đăng ký thông tin thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        DialogLoading.hideDialog();
                        Toast.makeText(RegisterInfoActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void chooseImage() {

        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            } else {
                requestPermission.launch(Manifest.permission.READ_MEDIA_IMAGES);
            }
        } else {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            } else {
                requestPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }

    }

    private final ActivityResultLauncher<String> requestPermission = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    //Có quyền
                    pickImage();
                } else {
                    Toast.makeText(this, "Từ chối quyền", Toast.LENGTH_SHORT).show();
                }
            }
    );

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultChooseImage.launch(Intent.createChooser(intent, "Choose Image"));

    }

    private final ActivityResultLauncher<Intent> resultChooseImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();

                    if (data == null) {
                        return;
                    }

                    uri = data.getData();

                    binding.imgAvt.setImageURI(uri);

                }
            }
    );
}
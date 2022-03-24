package com.shop.shop2shop.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.shop.shop2shop.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;

public class UploadActivity extends AppCompatActivity {

    private Button PickImage,UploadImage;
    private ImageView imageView;

    private Uri ImageUri;
    int PERMISSION_ALL = 20003;
    private Bitmap compressedImageBitmap;
    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
    PickImage = findViewById(R.id.pickImage);
    imageView = findViewById(R.id.LoadImage);

    PickImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!hasPermissions(getApplicationContext(), PERMISSIONS)){
                ActivityCompat.requestPermissions(UploadActivity.this, PERMISSIONS, PERMISSION_ALL);
            }else {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512,512)
                        .setAspectRatio(1,1)
                        .start(UploadActivity.this);
            }
        }
    });

        UploadImage = findViewById(R.id.UploadImage);

        UploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Upload();
            }


        });



    }

    private void Upload(){
        File newimage = new File(ImageUri.getPath());

        ///Image compressor
        try {
            Compressor compressor = new Compressor(this);
            compressor.setMaxHeight(200);
            compressor.setMaxWidth(200);
            compressor.setQuality(10);
            compressedImageBitmap = compressor.compressToBitmap(newimage);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        final byte[] data = baos.toByteArray();


        // Upload hio $data
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {


                    return false;
                }

            }
        }
        return true;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    //data.getData returns the content URI for the selected Image
                    ImageUri = result.getUri();
                    if (ImageUri != null){
                        imageView.setImageURI(ImageUri);

                    }

                    break;
            }
    }
}
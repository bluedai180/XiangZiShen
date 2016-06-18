package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.GetChars;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.blue.xiangzishen.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

/**
 * Created by blue on 16-6-18.
 */
public class NewEditorActivity extends Activity implements View.OnClickListener {
    public static final int CAMERA_RESULT_CODE = 0;
    public static final int CHOOSE_RESULT_CODE = 1;
    private ImageView mPhoto;
    private String mPhotoPath;
    private ImageButton mFoodPicture1, mFoodPicture2, mFoodPicture3;
    public static final String TAG = "bluedai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edior);

        initView();

        mPhotoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/foodpicture.jpg";
        File imageFile = new File(mPhotoPath);
        Uri uri = Uri.fromFile(imageFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        // startActivityForResult(intent, CAMERA_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult " + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_RESULT_CODE:
                    // Bundle extras = data.getExtras();
                    // Bitmap bmp = (Bitmap) extras.get("data");

                    Display display = this.getWindowManager().getDefaultDisplay();
                    int w = display.getWidth();
                    int h = display.getHeight();
                    Log.d("bluedai", "onActivityResult " + w + h);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    Bitmap bmp = BitmapFactory.decodeFile(mPhotoPath, options);
                    int heightRatio = (int) Math.ceil(options.outHeight / (float) h);
                    int widthRatio = (int) Math.ceil(options.outWidth / (float) w);

                    if (heightRatio > 1 && widthRatio > 1) {
                        if (heightRatio > widthRatio) {
                            options.inSampleSize = heightRatio;
                        } else {
                            options.inSampleSize = widthRatio;
                        }
                    }

                    options.inJustDecodeBounds = false;
                    bmp = BitmapFactory.decodeFile(mPhotoPath, options);
                    mPhoto.setImageBitmap(bmp);
                    break;
                case CHOOSE_RESULT_CODE:
                    Log.d(TAG, "CHOOSE_RESULT_CODE ");
                    Uri imageUri = data.getData();
                    BitmapFactory.Options optChoosePicture = new BitmapFactory.Options();
                    // optChoosePicture.inJustDecodeBounds = true;
                    // optChoosePicture.inSampleSize = 10;
                    try {
                        int hRatio = (int) Math.ceil(optChoosePicture.outHeight / (float) 70);
                        int wRatio = (int) Math.ceil(optChoosePicture.outWidth / (float) 70);

//                        if (hRatio > 1 && wRatio > 1) {
//                            if (hRatio > wRatio) {
//                                optChoosePicture.inSampleSize = hRatio;
//                            }else {
//                                optChoosePicture.inSampleSize = wRatio;
//                            }
//                        }
                        optChoosePicture.inSampleSize = 15;
                        // optChoosePicture.inJustDecodeBounds = false;
                        Bitmap bmpPicture = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, optChoosePicture);
                        mFoodPicture1.setImageBitmap(bmpPicture);
                        mFoodPicture2.setVisibility(View.VISIBLE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }

    private void initView() {
        mFoodPicture1 = (ImageButton) findViewById(R.id.imb_food1);
        mFoodPicture2 = (ImageButton) findViewById(R.id.imb_food2);
        mFoodPicture3 = (ImageButton) findViewById(R.id.imb_food3);
        mPhoto = (ImageView) findViewById(R.id.img_photo);
        mFoodPicture1.setOnClickListener(this);
        mFoodPicture2.setOnClickListener(this);
        mFoodPicture3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imb_food1:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_RESULT_CODE);
                break;
            case R.id.imb_food2:
                mFoodPicture3.setVisibility(View.VISIBLE);
                break;
            case R.id.imb_food3:
                break;
        }
    }

    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exif = new ExifInterface(path);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    private Bitmap rotateBitmapByDegree(Bitmap bitmap, int degree) {

    }
}

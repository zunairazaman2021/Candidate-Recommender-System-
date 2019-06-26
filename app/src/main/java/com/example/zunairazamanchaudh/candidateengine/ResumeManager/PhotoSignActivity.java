package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zunairazamanchaudh.candidateengine.R;

public class PhotoSignActivity extends AppCompatActivity implements View.OnClickListener{
    Button signatureButton,saveButton;
    ImageView signImage,imageView;
    String image_path;
    private String photoIntentValue,signintent;
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_sign);
        signatureButton = (Button) findViewById(R.id.getSign);
        signImage = (ImageView) findViewById(R.id.imageView1);
        imageView = (ImageView) findViewById(R.id.imgViewPhoto);
        saveButton=(Button)findViewById(R.id.savePhotosign);
        saveButton.setOnClickListener(this);
        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(this);


        signatureButton.setOnClickListener(this);
        image_path = getIntent().getStringExtra("imagePath");
        Bitmap bitmap = BitmapFactory.decodeFile(image_path);
        signImage.setImageBitmap(bitmap);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
           String  picturePath = cursor.getString(columnIndex);
            cursor.close();

           imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
           photoIntentValue=picturePath;

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savePhotosign:
                Intent intent = new Intent();
                intent.putExtra("keyPhoto", photoIntentValue);
                if(image_path!=null) {
                    signintent = image_path;
                    intent.putExtra("keySign", signintent);
                }
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.getSign:
                Intent i = new Intent(PhotoSignActivity.this, SignatureActivity.class);
                startActivity(i);
                break;
            case R.id.buttonLoadPicture:
                Intent ii = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(ii, RESULT_LOAD_IMAGE);

                break;
        }
    }
}

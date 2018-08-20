package com.sudichina.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.get_info)
    Button getInfo;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.make_img)
    Button makeImg;
    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getInfo.setOnClickListener(new View.OnClickListener() {//扫描二维码
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,CaptureActivity.class),0);
            }
        });


        makeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap qrCode = EncodingUtils.createQRCode(etText.getText().toString(), 500, 500, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                img.setImageBitmap(qrCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode==0) {
                String result = data.getStringExtra("result");
                info.setText(result);
            }
        }
    }
}

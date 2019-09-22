package com.mobile.seoul.seoulstampapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mobile.seoul.seoulstampapplication.R;
import com.mobile.seoul.seoulstampapplication.constant.SightConstant;
import com.mobile.seoul.seoulstampapplication.enums.Sight;

import org.apache.commons.lang3.StringUtils;

import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SIGHT_KEY;

public class BarcodeActivity extends AppCompatActivity {

    private Sight sight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        this.sight = Sight.valueOf(getIntent().getStringExtra(SIGHT_KEY));

        startQRCode();
    }

    public void startQRCode() {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "취소", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Log.i("qrCode 확인", result.getContents());
                if (StringUtils.equals(result.getContents(), sight.getQrCodeUrl())) {
                    Toast.makeText(this, "잠금이 해제되었습니다.", Toast.LENGTH_LONG).show();
                    finishWithExstra(true);
                    return;
                }
                Toast.makeText(this, "해당 위치의 QRcode가 아닙니다.", Toast.LENGTH_LONG).show();
                finishWithExstra(false);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void finishWithExstra(boolean barcodeResult) {
        Intent intent = new Intent();
        intent.putExtra(SightConstant.BARCODE_RESULT_KEY, barcodeResult);
        setResult(RESULT_OK, intent);
        intent.putExtra(SIGHT_KEY, sight.name());
        finish();
    }
}

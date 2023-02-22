package com.example.mahdi.zarinpaltest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final TextView lbl_res = findViewById(R.id.lbl_payres);
        Uri data = getIntent().getData();
        ZarinPal.getPurchase(this).verificationPayment(data, new OnCallbackVerificationPaymentListener() {
            @Override
            public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
                if(isPaymentSuccess)
                {
                    //lbl_res.setText("OK" + refID);
                    //Toast.makeText(MainActivity.this, "\"OK\" + refID", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnPay = findViewById(R.id.btn_payment);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPayment();
            }
        });



    }


    private void myPayment(){

        ZarinPal p = ZarinPal.getPurchase(this) ;
        PaymentRequest pp = ZarinPal.getPaymentRequest();

        pp.setMerchantID("3e7208f0-4a7e-11e7-9354-005056a205be"); // توسط کاربر پیاده سازی میشود
        pp.setAmount(100);
        pp.setDescription("پرداخت تست");
        pp.setCallbackURL("pardakht://zarinpalpayment");


        p.startPayment(pp, new OnCallbackRequestPaymentListener() {
            @Override
            public void onCallbackResultPaymentRequest(int status, String authority, Uri paymentGatewayUri, Intent intent) {
                if(status == 100)
                {
                    startActivity(intent);
                }
                else
                {
                    //Toast.makeText(MainActivity.this, "ERROR in PAyment", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}

package com.example.firmadigital;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.example.firmadigital.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.btnClear.setOnClickListener(view -> {
            mainBinding.signatureView.clearCanvas();
        });
        mainBinding.btnLoad.setOnClickListener(view -> {
            Bitmap signBitmap = mainBinding.signatureView.getSignatureBitmap();
            if (signBitmap != null){
                GuardarImagen link =  new GuardarImagen();
                link.busquedaTexto("Firma", signBitmap);
                Snackbar.make(view, link.resultado, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mainBinding.ivFirma.setImageBitmap(signBitmap);
                mainBinding.signatureView.clearCanvas();
            }
        });
    }


}
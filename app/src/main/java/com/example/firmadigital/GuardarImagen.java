package com.example.firmadigital;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GuardarImagen{

    String resultado = null;

    public void busquedaTexto(String nombre, Bitmap imagen){
        File file  = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Firmas");
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        String filename = nombre + "_" + fecha + ".jpg";
        File myPath = new File(file,filename);
        String base64 = bitmapToBase64(imagen);
        String espacios = base64.replace(" ","");
        String saltos = espacios.replace("\n","");

        try{
            OutputStream fos = new FileOutputStream(myPath);
            imagen.compress(Bitmap.CompressFormat.JPEG, 10, fos);
            fos.close();
            ResponseDto responseDto = new ResponseDto();
            responseDto.setData(saltos);
            responseDto.setNamefile(filename);
            cargarWebService(responseDto);

        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        resultado = myPath.getAbsolutePath();

    }

    private String bitmapToBase64(Bitmap imagen) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG,10,baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }


    public void cargarWebService(ResponseDto responseDto){

        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = new Gson().toJson(responseDto);
                HttpService httpService = new HttpService(Constants.URL_SERVICE);
                String response = httpService.post("subida",null,json);
                Log.i("response",response);

            }
        }).start();

    }

}

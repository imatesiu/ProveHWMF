package it.isti.sse.provehwmf.util;

import android.graphics.Color;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import isti.cnr.sse.rest.data.Esito;


/**
 * Created by m4rt3 on 17/11/2016.
 */

public class Utility {

    public static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");

    }

    public static int getColor(Esito e){
        switch (e){
            case Incerto: return Color.GRAY;
            case Positivo: return Color.GREEN;
            case Negativo: return Color.RED;
            case Incorso: return Color.BLUE;
            case Sospeso: return Color.YELLOW;

        }
        return 1;
    }
}

package com.example.farvel.farvel_akne;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TakePicFragment extends Fragment {

    Button button;
    ImageView imageView1,imageView2,imageView3;
    View myView;
    static final int CAM_REQUEST = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_take_pic,container,false);
        ((MainActivity)getActivity()).setColorOnBtn(R.layout.fragment_take_pic);

       button = (Button) myView.findViewById(R.id.buttonpic);
        imageView1 = (ImageView) myView.findViewById(R.id.image_view_take_pic1);
        imageView2 = (ImageView) myView.findViewById(R.id.image_view_take_pic2);
        imageView3 = (ImageView) myView.findViewById(R.id.image_view_take_pic3);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

           getNewPic(0);
            }
        });



    return myView;
    }

public void getNewPic( int i){
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = getFile(i);
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(camera_intent, i);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "";
        if(resultCode == -1) {
            switch (requestCode) {
                case 0:
                    path = "sdcard/camera_app/cam_image0.jpg";
                    imageView1.setImageDrawable(Drawable.createFromPath(path));
                    getNewPic(1);
                    break;
                case 1:
                    path = "sdcard/camera_app/cam_image1.jpg";
                    imageView2.setImageDrawable(Drawable.createFromPath(path));
                  getNewPic(2);
                    break;
                case 2:
                    path = "sdcard/camera_app/cam_image2.jpg";
                    imageView3.setImageDrawable(Drawable.createFromPath(path));
                    break;
                default:
                    break;
            }
        }

    }
    private File getFile(int i)
    {
        File folder = new File("sdcard/camera_app");

        if(!folder.exists()){
            folder.mkdir();
        }


        File image_file = new File(folder,"cam_image"+i+".jpg");


        return image_file;

    }

}
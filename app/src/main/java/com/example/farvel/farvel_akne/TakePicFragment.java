package com.example.farvel.farvel_akne;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.File;
import java.util.UUID;


public class TakePicFragment extends Fragment {
    String [] pathForImages = new String[4] ;
    LinearLayout face;
    Button button, sendBTN;
    ImageView imageView1,imageView2,imageView3,imageView0;
    View myView;
    static final int CAM_REQUEST = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_take_pic,container,false);
        ((MainActivity)getActivity()).setColorOnBtn(R.layout.fragment_take_pic);

        face = (LinearLayout) myView.findViewById(R.id.AnsigtLayout) ;



       button = (Button) myView.findViewById(R.id.buttonpic);

        sendBTN = (Button) myView.findViewById(R.id.sendBTN);
        sendBTN.setVisibility(View.GONE);
        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadMultipart(pathForImages);
                getFragmentManager().beginTransaction().replace(R.id.content_frame1, new QuestionnaireFragment()).addToBackStack(null).commit();

            }
        });

        imageView0 = (ImageView) myView.findViewById(R.id.image_view_take_pic_face0);
        imageView1 = (ImageView) myView.findViewById(R.id.image_view_take_pic_face1);
        imageView2 = (ImageView) myView.findViewById(R.id.image_view_take_pic_face2);
        imageView3 = (ImageView) myView.findViewById(R.id.image_view_take_pic_face3);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(imageView0.getDrawable() == null){
                    getNewPic(0);
                }
                else if(imageView1.getDrawable() == null){
                    getNewPic(1);
                }
                else if(imageView2.getDrawable() == null){
                    getNewPic(2);
                }
                else if(imageView3.getDrawable() == null){
                    getNewPic(3);
                }


            }
        });



    return myView;
    }

public void getNewPic( int i){
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = getFile(i);
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
    camera_intent.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);
    camera_intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
    camera_intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        startActivityForResult(camera_intent, i);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "";
        int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        if(resultCode == -1) {
            switch (requestCode) {
                case 0:
                    pathForImages[0] = "sdcard/camera_app/cam_image0.jpg";
                    path = "sdcard/camera_app/cam_image0.jpg";

                    imageView0.getLayoutParams().height = dimensionInDp;
                    imageView0.getLayoutParams().width = dimensionInDp;
                    imageView0.requestLayout();
                    imageView0.setImageDrawable(Drawable.createFromPath(path));
                    imageView0.setBackgroundResource(android.R.color.transparent);
                   getNewPic(1);
                    //changeButton();
                    break;
                case 1:
                    pathForImages[1] = "sdcard/camera_app/cam_image1.jpg";
                    path = "sdcard/camera_app/cam_image1.jpg";
                    imageView1.getLayoutParams().height = dimensionInDp;
                    imageView1.getLayoutParams().width = dimensionInDp;
                    imageView1.requestLayout();
                    imageView1.setImageDrawable(Drawable.createFromPath(path));
                    imageView1.setBackgroundResource(android.R.color.transparent);
                  getNewPic(2);
                    break;
                case 2:
                    pathForImages[2] = "sdcard/camera_app/cam_image2.jpg";
                    path = "sdcard/camera_app/cam_image2.jpg";
                    imageView2.getLayoutParams().height = dimensionInDp;
                    imageView2.getLayoutParams().width = dimensionInDp;
                    imageView2.requestLayout();
                    imageView2.setImageDrawable(Drawable.createFromPath(path));
                    imageView2.setBackgroundResource(android.R.color.transparent);
                    getNewPic(3);
                    break;
                case 3:
                    pathForImages[3] = "sdcard/camera_app/cam_image3.jpg";
                    path = "sdcard/camera_app/cam_image3.jpg";
                    imageView3.getLayoutParams().height = dimensionInDp;
                    imageView3.getLayoutParams().width = dimensionInDp;
                    imageView3.requestLayout();
                    imageView3.setImageDrawable(Drawable.createFromPath(path));
                    imageView3.setBackgroundResource(android.R.color.transparent);
                    changeButton();
                    face.setBackgroundResource(R.drawable.allpicborder);
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

    public  void changeButton (){
        button.setVisibility(View.GONE);
        sendBTN.setVisibility(View.VISIBLE);

    }

    public void uploadMultipart( String [] pathImg) {
        //getting name for the image
        String name = "Tobias";

        //getting the actual path of the image
        String path = "sdcard/camera_app/cam_image0.jpg";
        for(int i =0;i < pathImg.length;i++) {


            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();

                //Creating a multi part request
                new MultipartUploadRequest(getActivity().getApplicationContext(), uploadId, Constants.UPLOAD_URL)
                        .addFileToUpload(pathImg[i], "image") //Adding file
                        .addParameter("name", name) //Adding text parameter to the request
                        //.setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload

            } catch (Exception exc) {
                Toast.makeText(getActivity().getApplicationContext(), exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
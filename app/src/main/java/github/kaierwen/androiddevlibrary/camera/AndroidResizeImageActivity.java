package github.kaierwen.androiddevlibrary.camera;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import github.kaierwen.androiddevlibrary.R;

/**
 * @author kaiyuan.zhang
 * @see <a href="https://vikaskanani.wordpress.com/2011/07/17/android-re-size-image-without-loosing-exif-information/">
 * @since 2018/2/5
 */
public class AndroidResizeImageActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private Button ResizeBtn;
    private Button LoadBtn;
    private Bitmap bitmap;
    private ImageView imgView;
    private static int newWidth = 200;
    private static int newHeight = 200;
    private String filePath;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_resize_image);

        imgView = (ImageView) findViewById(R.id.ImageView);
        LoadBtn = (Button) findViewById(R.id.LoadButton);
        ResizeBtn = (Button) findViewById(R.id.ResizeButton);

        LoadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(
                            Intent.createChooser(intent, "Select image"),
                            PICK_IMAGE);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.e(e.getClass().getName(), e.getMessage(), e);
                }
            }
        });

        ResizeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    float scaleWidth = ((float) newWidth) / width;
                    float scaleHeight = ((float) newHeight) / height;
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleHeight);
                    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                            width, height, matrix, false);

                    // Save new image
                    String path = Environment.getExternalStorageDirectory()
                            .toString();
                    File file = new File(path, "NewImage.jpeg");

                    FileOutputStream fos = new FileOutputStream(file);
                    resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                    // copy paste exif information from original file to new
                    // file
                    ExifInterface oldexif = new ExifInterface(filePath);
                    ExifInterface newexif = new ExifInterface(file.getAbsolutePath());

                    int build = Build.VERSION.SDK_INT;


                    // From API 11
                    if (build >= 11) {
                        if (oldexif.getAttribute("FNumber") != null) {
                            newexif.setAttribute("FNumber",
                                    oldexif.getAttribute("FNumber"));
                        }
                        if (oldexif.getAttribute("ExposureTime") != null) {
                            newexif.setAttribute("ExposureTime",
                                    oldexif.getAttribute("ExposureTime"));
                        }
                        if (oldexif.getAttribute("ISOSpeedRatings") != null) {
                            newexif.setAttribute("ISOSpeedRatings",
                                    oldexif.getAttribute("ISOSpeedRatings"));
                        }
                    }
                    // From API 9
                    if (build >= 9) {
                        if (oldexif.getAttribute("GPSAltitude") != null) {
                            newexif.setAttribute("GPSAltitude",
                                    oldexif.getAttribute("GPSAltitude"));
                        }
                        if (oldexif.getAttribute("GPSAltitudeRef") != null) {
                            newexif.setAttribute("GPSAltitudeRef",
                                    oldexif.getAttribute("GPSAltitudeRef"));
                        }
                    }
                    // From API 8
                    if (build >= 8) {
                        if (oldexif.getAttribute("FocalLength") != null) {
                            newexif.setAttribute("FocalLength",
                                    oldexif.getAttribute("FocalLength"));
                        }
                        if (oldexif.getAttribute("GPSDateStamp") != null) {
                            newexif.setAttribute("GPSDateStamp",
                                    oldexif.getAttribute("GPSDateStamp"));
                        }
                        if (oldexif.getAttribute("GPSProcessingMethod") != null) {
                            newexif.setAttribute(
                                    "GPSProcessingMethod",
                                    oldexif.getAttribute("GPSProcessingMethod"));
                        }
                        if (oldexif.getAttribute("GPSTimeStamp") != null) {
                            newexif.setAttribute("GPSTimeStamp", ""
                                    + oldexif.getAttribute("GPSTimeStamp"));
                        }
                    }
                    if (oldexif.getAttribute("DateTime") != null) {
                        newexif.setAttribute("DateTime",
                                oldexif.getAttribute("DateTime"));
                    }
                    if (oldexif.getAttribute("Flash") != null) {
                        newexif.setAttribute("Flash",
                                oldexif.getAttribute("Flash"));
                    }
                    if (oldexif.getAttribute("GPSLatitude") != null) {
                        newexif.setAttribute("GPSLatitude",
                                oldexif.getAttribute("GPSLatitude"));
                    }
                    if (oldexif.getAttribute("GPSLatitudeRef") != null) {
                        newexif.setAttribute("GPSLatitudeRef",
                                oldexif.getAttribute("GPSLatitudeRef"));
                    }
                    if (oldexif.getAttribute("GPSLongitude") != null) {
                        newexif.setAttribute("GPSLongitude",
                                oldexif.getAttribute("GPSLongitude"));
                    }
                    if (oldexif.getAttribute("GPSLatitudeRef") != null) {
                        newexif.setAttribute("GPSLongitudeRef",
                                oldexif.getAttribute("GPSLongitudeRef"));
                    }
                    //Need to update it, with your new height width
                    newexif.setAttribute("ImageLength",
                            "200");
                    newexif.setAttribute("ImageWidth",
                            "200");

                    if (oldexif.getAttribute("Make") != null) {
                        newexif.setAttribute("Make",
                                oldexif.getAttribute("Make"));
                    }
                    if (oldexif.getAttribute("Model") != null) {
                        newexif.setAttribute("Model",
                                oldexif.getAttribute("Model"));
                    }
                    if (oldexif.getAttribute("Orientation") != null) {
                        newexif.setAttribute("Orientation",
                                oldexif.getAttribute("Orientation"));
                    }
                    if (oldexif.getAttribute("WhiteBalance") != null) {
                        newexif.setAttribute("WhiteBalance",
                                oldexif.getAttribute("WhiteBalance"));
                    }

                    newexif.saveAttributes();
                    // MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());

                    Toast.makeText(getApplicationContext(),
                            "Image resized & saved successfully",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.e(e.getClass().getName(), e.getMessage(), e);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImageUri = data.getData();

                    try {
                        // OI FILE Manager
                        String filemanagerstring = selectedImageUri.getPath();

                        // MEDIA GALLERY
                        String selectedImagePath = getPath(selectedImageUri);

                        if (selectedImagePath != null) {
                            filePath = selectedImagePath;
                        } else if (filemanagerstring != null) {
                            filePath = filemanagerstring;
                        } else {
                            Toast.makeText(getApplicationContext(), "Unknown path",
                                    Toast.LENGTH_LONG).show();
                            Log.e("Bitmap", "Unknown path");
                        }
                        if (filePath != null) {
                            decodeFile();
                        } else {
                            bitmap = null;
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Internal error",
                                Toast.LENGTH_LONG).show();
                        Log.e(e.getClass().getName(), e.getMessage(), e);
                    }
                }
                break;
            default:
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else {
            return null;
        }
    }

    public void decodeFile() {
        /*
         * Intentionally commented this code. To get original image size. It may
         * require if image is of very high resolution.
         *
         * // Decode image size BitmapFactory.Options o = new
         * BitmapFactory.Options(); o.inJustDecodeBounds = true;
         * BitmapFactory.decodeFile(filePath, o);
         *
         * // The new size we want to scale to final int REQUIRED_SIZE = 1024;
         *
         * // Find the correct scale value. It should be the power of 2. int
         * width_tmp = o.outWidth, height_tmp = o.outHeight; int scale = 1;
         * while (true) { if (width_tmp < REQUIRED_SIZE && height_tmp <
         * REQUIRED_SIZE) break; width_tmp /= 2; height_tmp /= 2; scale *= 2; }
         *
         * // Decode with inSampleSize BitmapFactory.Options o2 = new
         * BitmapFactory.Options(); o2.inSampleSize = scale; bitmap =
         * BitmapFactory.decodeFile(filePath, o2);
         */

        bitmap = BitmapFactory.decodeFile(filePath);

        imgView.setImageBitmap(bitmap);

    }
}

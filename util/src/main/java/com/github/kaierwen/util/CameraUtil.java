package com.github.kaierwen.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * 跟相机相关的工具栏
 *
 * @author zhangky@chinasunfun.com
 * @since 2017/4/14
 */
public class CameraUtil {

    /**
     * 实际上为了保险这个方法最好是异步去调用它这里为了演示方便就在ui线程调用了
     *
     * @param context
     * @return
     * @see <a href="http://www.cnblogs.com/punkisnotdead/p/4930332.html"/>
     */
    public static String getRecentlyPhotoPath(Context context) {
        //这个地方利用like 和通配符 来寻找 系统相机存储照片的地方
        //实际上还可以做的更夸张一点，寻找所有目录下的照片 并且可以限定格式  只要修改这个通配符语句即可
        String searchPath = MediaStore.Files.FileColumns.DATA + " LIKE '%" + "/DCIM/Camera/" + "%' ";
        Uri uri = MediaStore.Files.getContentUri("external");
        //这里做一个排序，因为我们实际上只需要最新拍得那张即可 你甚至可以取表里的 时间那个字段 然后判断一下 距离现在是否超过2分钟 超过2分钟就可以不显示缩略图的 微信就是2分钟之内刚拍的图片
        //会显示 超过了就不显示，这里主要就是看对表结构的理解
        Cursor cursor = context.getContentResolver().query(
                uri, new String[]{MediaStore.Files.FileColumns.DATA, MediaStore.Files.FileColumns.SIZE}, searchPath, null, MediaStore.Files.FileColumns.DATE_ADDED + " DESC");
        String filePath = "";
        if (cursor != null && cursor.moveToFirst()) {
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return filePath;
    }

    /**
     * 获取系统照相机存储路径文件
     *
     * @return
     */
    public static File getDefaultCameraStorageDirectory() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    }
}

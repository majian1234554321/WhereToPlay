package com.fanc.wheretoplay.util

import android.graphics.Bitmap
import android.util.Base64
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author admin
 * @date 2018/3/21
 */
class BitmapUtils{
    companion object {
        fun compressImage(bitmap: Bitmap): File {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            var options = 100
            while (baos.toByteArray().size / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
                baos.reset()//重置baos即清空baos
                options -= 10//每次都减少10
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中
                val length = baos.toByteArray().size.toLong()
            }
            val format = SimpleDateFormat("yyyyMMddHHmmss")
            val date = Date(System.currentTimeMillis())
            val filename = format.format(date)
            val file = File(Environment.getExternalStorageDirectory(), "$filename.png")
            try {
                val fos = FileOutputStream(file)
                try {
                    fos.write(baos.toByteArray())
                    fos.flush()
                    fos.close()
                } catch (e: IOException) {
                    //BAFLogger.e(TAG,e.getMessage());
                    e.printStackTrace()
                }

            } catch (e: FileNotFoundException) {
                //  BAFLogger.e(TAG,e.getMessage());
                e.printStackTrace()
            }

            recycleBitmap(bitmap)
            return file
        }
        fun recycleBitmap(vararg bitmaps: Bitmap) {

            for (bm in bitmaps) {
                if (!bm.isRecycled) {
                    bm.recycle()
                }
            }
        }

         fun getBitmap(srcPath: String): Bitmap {
            val newOpts = BitmapFactory.Options()
            // 开始是先把newOpts.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true
            var bitmap = BitmapFactory.decodeFile(srcPath, newOpts) // 此时返回bitmap为null

            newOpts.inJustDecodeBounds = false
            val w = newOpts.outWidth
            val h = newOpts.outHeight
            // 以800*480分辨率为例
            val hh = 800f // 这里设置高度为800f
            val ww = 480f // 这里设置宽度为480f
            // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            var scale = 1 // be=1表示不缩放
            if (w > h && w > ww) { // 如果宽度大的话根据宽度固定大小缩放
                scale = (newOpts.outWidth / ww).toInt()
            } else if (w < h && h > hh) { // 如果高度高的话根据宽度固定大小缩放
                scale = (newOpts.outHeight / hh).toInt()
            }
            if (scale <= 0) scale = 1
            newOpts.inSampleSize = scale // 设置缩放比例 // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts)
            return bitmap
        }

         fun Bitmap2StrByBase64(bit: Bitmap): String {

             val bos = ByteArrayOutputStream()
             bit.compress(Bitmap.CompressFormat.JPEG, 40, bos) // 参数100表示不压缩

             val bytes = bos.toByteArray()
             return Base64.encodeToString(bytes, Base64.DEFAULT)
         }
    }
}
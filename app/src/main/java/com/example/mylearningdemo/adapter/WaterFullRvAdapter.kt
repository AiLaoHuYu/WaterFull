package com.example.mylearningdemo.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Looper
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylearningdemo.R
import com.example.mylearningdemo.bean.WaterFullBean
import com.example.mylearningdemo.utils.ScreenUtils
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.logging.Handler
import kotlin.time.times

class WaterFullRvAdapter(context: Context, itemList: ArrayList<WaterFullBean>) :
    RecyclerView.Adapter<WaterFullRvAdapter.MyViewHolder>() {

    private val TAG = WaterFullRvAdapter::class.java.name
    private var mContext: Context = context
    private var mItemList: ArrayList<WaterFullBean> = itemList
    private var imgCache: LruCache<String, Bitmap>
    private var executorService: ExecutorService

    init {
        val maxMemorySize = (Runtime.getRuntime().maxMemory()) / 1024
        imgCache = LruCache<String, Bitmap>(maxMemorySize.toInt())
        executorService = Executors.newFixedThreadPool(1)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var ivImg: ImageView = itemView.findViewById(R.id.iv_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.water_full_rv_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        myViewHolder.setIsRecyclable(true)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder position: $position")
        //设置item的值
        holder.tvTitle.text = mItemList[position].tv
        val layoutParams = holder.ivImg.layoutParams
        val itemWidth = (ScreenUtils.getScreenWidth(holder.itemView.context) - 5 * 3) / 2
        layoutParams.width = itemWidth
        val scale = (itemWidth + 0f) / 350 //这里是图片的宽度
        layoutParams.height =
            ((Integer.parseInt(mItemList[position].imgHeight) * scale).toInt())
        holder.ivImg.layoutParams = layoutParams
        beforeSetImg(
            mItemList[position].img,
            layoutParams.width,
            layoutParams.height,
            holder.ivImg
        )
    }


    private fun beforeSetImg(imgUrl: String, width: Int, height: Int, view: ImageView) {
        Log.d(TAG, "beforeSetImg  imgUrl: $imgUrl")
        executorService.submit {
            val bitmap = imgCache.get(imgUrl)
            if (bitmap != null) {
                setImgToView(bitmap, width, height, view)
            } else {
                val downLoadBitmap = downLoadBitmap(imgUrl)
                imgCache.put(imgUrl, downLoadBitmap)
                setImgToView(downLoadBitmap, width, height, view)
            }
        }
    }

    private val handler = android.os.Handler(Looper.getMainLooper())

    private fun setImgToView(downLoadBitmap: Bitmap, width: Int, height: Int, view: ImageView) {
        handler.post {
            Log.d(TAG, "setImgToView width: $width  height: $height")
            Glide.with(mContext).asBitmap().load(downLoadBitmap)
                .override(width, height)
                .into(view)
        }
    }

    private fun downLoadBitmap(imgUrl: String): Bitmap {

        var bitmap: Bitmap? = null
        var con: HttpURLConnection? = null
        try {
            val url = URL(imgUrl)
            con = url.openConnection() as HttpURLConnection
            con.connectTimeout = 5 * 1000
            con.readTimeout = 10 * 1000
            bitmap = BitmapFactory.decodeStream(con.inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            con?.disconnect()
        }
        return bitmap!!
    }


}
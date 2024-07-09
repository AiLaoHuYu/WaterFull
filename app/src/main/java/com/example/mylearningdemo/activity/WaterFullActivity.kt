package com.example.mylearningdemo.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.example.mylearningdemo.R
import com.example.mylearningdemo.adapter.WaterFullRvAdapter
import com.example.mylearningdemo.base.BaseActivity
import com.example.mylearningdemo.bean.WaterFullBean

/**
 * Author: kideng
 * Date: 2024/7/9
 * Description: WaterFullActivity
 */

class WaterFullActivity : BaseActivity() {

    private val TAG = WaterFullActivity::class.java.name
    private lateinit var rvWaterFull: RecyclerView
    private lateinit var rvWaterFUllAdapter: WaterFullRvAdapter
    private lateinit var dataList: ArrayList<WaterFullBean>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏顶部导航栏
        actionBar?.hide()
    }

    override fun initData() {
        dataList = getData()
    }

    fun getData(): ArrayList<WaterFullBean> {
        val names = arrayOf(
            "晓风残月",
            "杨柳依依",
            "二月春分",
            "杏花诗雨随风至，",
            "中华少年不屈不挠，少年强中国强，壮我中国魂",
            "铁马冰河",
            "庐山迷雾",
            "竹林幽潭",
            "大唐雄风",
            "暖风袭的游人醉",
            "一切疫情都是纸老虎",
            "我自横刀向天笑，去留肝胆两昆仑",
            "春如一夜春风留春痕",
            "人生若只是初见",
            "风雨不曾动我心",
            "皎皎明月",
            "流觞",
            "若水三千"
        )
        val imgHeight =
            arrayOf(
                "260",
                "300",
                "100",
                "220",
                "180",
                "260",
                "200",
                "320",
                "200",
                "300",
                "250",
                "280",
                "150",
                "240",
                "200",
                "280"
            )
        val path1 = "http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg"
        val path2 = "https://picsum.photos/200/300"
        val path3 = "https://picsum.photos/200"
        val path4 = "https://picsum.photos/id/237/200/300"
        val path5 = "https://picsum.photos/seed/picsum/200/300"
        val path6 = "https://picsum.photos/200/300?grayscale"
        val path7 = "https://picsum.photos/200/300/?blur"
        val path8 = "https://picsum.photos/200/300/?blur=2"
        val path9 = "https://picsum.photos/id/870/200/300?grayscale&blur=2"
        val path10 = "https://picsum.photos/200/300.jpg"
        val path11 = "https://picsum.photos/200/300.webp"
        val path12 = "http://c.hiphotos.baidu.com/image/pic/item/30adcbef76094b36de8a2fe5a1cc7cd98d109d99.jpg"
        val path13 = "https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg"
        val path14 = "http://h.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5f2c2a9e953da81cb39db3d1d.jpg"
        val path15 = "http://g.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d5277fd5d0628535e5dd6f4a.jpg"
        val path16 = "http://a.hiphotos.baidu.com/image/pic/item/e824b899a9014c087eb617650e7b02087af4f464.jpg"
        val imgUrs =
            arrayOf(
                path1,
                path2,
                path3,
                path4,
                path5,
                path6,
                path7,
                path8,
                path9,
                path10,
                path11,
                path12,
                path13,
                path14,
                path15,
                path16
            )

        val waterFullList = ArrayList<WaterFullBean>()
        for (i in 0 until 16) {
            val bean = WaterFullBean(names[i], imgUrs[i], imgHeight[i])
            waterFullList.add(bean)
        }
        return waterFullList
    }

    override fun initView() {
        rvWaterFull = findViewById(R.id.rv_water_pull)
        rvWaterFUllAdapter = WaterFullRvAdapter(this, dataList)
        val mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //防止item交换位置
        mLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        rvWaterFull.layoutManager = mLayoutManager
        //去掉RecyclerView动画代码，防止闪烁
        (rvWaterFull.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
        (rvWaterFull.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
        (rvWaterFull.itemAnimator as DefaultItemAnimator).changeDuration = 0
        rvWaterFull.adapter = rvWaterFUllAdapter
        rvWaterFull.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mLayoutManager.invalidateSpanAssignments() //防止第一行到顶部有空白
            }
        })

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initOther() {

    }

    override fun initBroadCast() {
    }


}


//@GlideModule
//class CustomGlideModule : AppGlideModule() {
//
//    override fun applyOptions(context: Context, builder: GlideBuilder) {
//        // 设置换存大小20MB
//        val memoryCacheSizeBytes: Long = 1024 * 1024 * 20 // 20MB
//        // 设置内存缓存大小
//        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes))
//        // 选择内部缓存
//        builder.setDiskCache(
//            InternalCacheDiskCacheFactory(
//                context,
//                "MyDemoTest",
//                memoryCacheSizeBytes
//            )
//        )
//    }
//
//    override fun isManifestParsingEnabled(): Boolean {
//        return false
//    }
//
//}
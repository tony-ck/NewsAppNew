package com.example.administrator.newsapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.administrator.newsapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import java.util.ArrayList;

/**
 * ListView的基础适配器，继承于BaseAdapter
 * @author Lanyan
 */

public abstract class BaseListAdapter<T> extends BaseAdapter{
    protected ArrayList<T> mList;//列表list
    protected LayoutInflater mInflater;// 布局管理
    protected DisplayImageOptions options;

    protected  static final int NO_DEFAULT=-1;//有图片但是没有默认图
    protected  static final int NO_IMAGE=0;//没有图片

    /**
     * 构造器
     *
     * @param context
     * @param list
     *            起始数据
     * @param defaultId
     *            NO_IMAGE为没有图片要显示，NO_DEFAULT为需要显示但没有默认图片，R.drawable.XXX为默认图id
     */
    protected BaseListAdapter(Context context, ArrayList<T> list, int defaultId) {
        this(context, list, defaultId, 0);
    }

    /**
     * 构造器
     *
     * @param context
     * @param list
     *            起始数据
     * @param defaultId
     *            NO_IMAGE为没有图片要显示，NO_DEFAULT为需要显示但没有默认图片，R.drawable.XXX为默认图id
     * @param radius
     *            图片圆角半径值
     */
    protected BaseListAdapter(Context context, ArrayList<T> list,
                              int defaultId, int radius) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = list;
        if (defaultId == NO_IMAGE) {// 没有图片
        } else if (defaultId == NO_DEFAULT) {// 有图片但是没有默认图
            options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .resetViewBeforeLoading(true).cacheOnDisk(true)
                    .showImageForEmptyUri(R.drawable.list_empturl)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageForEmptyUri(R.drawable.list_empturl)// 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.list_empturl) // 设置图片加载或解码过程中发生错误显示的图片
                    .displayer(new RoundedBitmapDisplayer(radius)).build();
        } else {// 有图片有默认图
            options = new DisplayImageOptions.Builder()
                    // .showImageForEmptyUri(R.drawable.ic_home_list)
                    .showImageOnLoading(R.drawable.list_empturl)
                    .resetViewBeforeLoading(true)
                    .showImageForEmptyUri(R.drawable.list_empturl)
                    // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.list_empturl)
                    // 设置图片加载或解码过程中发生错误显示的图片
                    .showImageForEmptyUri(R.drawable.list_empturl)
                    .cacheInMemory(true).cacheOnDisk(true)
                    .displayer(new RoundedBitmapDisplayer(radius))
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
        }
    }

    @Override
    public int getCount() {
        return (mList == null) ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return (mList == null) ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取该适配器的列表数据
     *
     * @return
     */
    public ArrayList<T> getData() {
        return mList;
    }

    public void displayImage(ImageView imageView, String arrayList) {
        final ImageView iv = imageView;

        ImageLoader.getInstance().displayImage(arrayList, iv, options);

        //
        // try {
        // if(arrayList!=null){
        // Bitmap
        // bitmap=ImageLoader.getInstance().loadImageSync("http://c.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ec78e256df60d0f703908fc12e.jpg");
        // ImageLoader.getInstance().getDiskCache().save(arrayList, bitmap);
        //
        // File file=
        // ImageLoader.getInstance().getDiskCache().get("http://c.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ec78e256df60d0f703908fc12e.jpg");
        // Log.i("--ss-----", file.getAbsolutePath()+"-----"+file.getName());
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }
}

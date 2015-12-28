package com.luteng.deviceadapte;

import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtInfo = (TextView) findViewById(R.id.txt_info);
        Resources resources = getResources();
        //获取尺寸资源
//        float width = resources.getDimension(R.dimen.my_width);
//        txtInfo.setText(Float.toString(width));
//        Log.d("MainActivity", Float.toString(width) + "      dimen");

        //1.机型适配不仅仅是屏幕适配
        //2.机型适配：使应用程序能够在广泛的软件系统和硬件设备上，都可以
        //正常的丶稳定的运行。

        //怎么适配？
        //Android适配基础
        //硬件功能适配
        //软件功能适配

        //适配先是运营商丶语言。其他的。。

        //关于图片适配
        //1.美工：按照像素单位来出图的，768*1024
        //2.dpi，每一部手机，都有自己的硬件参数 dpi，一英寸能够显示多少像素；
        //3.适配的时候，涉及不同dpi的图片，例如，100px，240px两张图，放在
        //不同的mipmap或者是drawable下面；
        //dp单位
        //1.dp以dpi为参数的单位；  px = （dpi/160）×dp；
        //常见 mdpi 160，一英寸显示160像素， 48*48，，ldpi 120，hdpi 240，xhdpi = 320；

        //图片资源适配
        //1.在不同的dpi目录，设置同名的图片，根据缩放比例，进行图片尺寸的设置；
        //在校尺寸手机上，显示内容尽量调整清楚，在大尺寸手机上，调整图片细节，
        //让图片显示的内容更多；
        //2.相当于程序能够同时支持 普通手机和高清版；
        //3.因为Android系统如果没有设置低dpi的图片的话，那么Android会把大图进行缩小，
        //匹配低密度的手机；
        // 如果一个图片只有xhdpi的版本，在低密度手机上，图片会自动缩小；
        //如果一个hdpi的图，运行在高密度手机上，例如xxxhdpi手机，这张图片就会自动放大，
        //那么就失真了，因此建议准备多套图片，适配各种密度；

        //Android尺寸适配
        //1.Android控件丶布局对于尺寸，如果在布局中固定设定xxxDP数值，在不同手机上，虽然能够缩放，
        //但是和预期的设计会有差别；
        //2.如果能够使用 wrap_content,match_parent,那么尽量使用；尽量使用权重layout_weight或者其他
        //非固定数值的设置
        //3.如果需要使用固定的数值；那么需要针对数值进行适配；那么需要根据手机的屏幕尺寸，进行不同的适配
        //将尺寸定义.。。3.0之前 small，normal，large，xlarge四种尺寸，有bug

        //宽度适配（Android3.0以后） dp
        //通过 API 获取屏幕的 宽度丶高度 dp数值；
        //1.Android提出来根据屏幕尺寸进行适配的方式；
        //2.新的适配采用dp单位的宽高来进行适配；
        //3. w<N>dp h<N>dp sw<N>dp 格式的修饰符，来适配屏幕的宽高，...

        //  Android版本的适配
        //1.使用Android Support包，实现高版本API的兼容；
        //2.通常由于采用一些新的API兼容包，那么软件最低支持android 2.1  API 7，
        //3.确定软件最低支持版本的时候，需要分析使用的API最低支持到哪个版本；
        //版本适配
        //1.确定最低版本，不要调用最低版本以上的任何代码；
        //2.如果调用的高版本方法，有对应低版本的替代；
        //  例如 View.setX(float)高版本，可以使用View.setLeft+View.setRight（）这种低版本方法代替；

        // Android硬件适配
        //1.清单文件：应用市场软件需要使用哪些硬件，需要在清单文件声明；是否是必须的；
        //2.应用市场，会根据手机的特性，显示对应的软件，

        WindowManager windowManager = getWindowManager();
        //获取默认的显示屏
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        //获取屏幕的测量信息，包括屏幕宽高等信息
        defaultDisplay.getMetrics(outMetrics);

        //手机DPI
        int densityDpi = outMetrics.densityDpi;
        //宽高的实际像素
        int widthPixels = outMetrics.widthPixels;
        int  heightPixels= outMetrics.heightPixels;
        //  px = (dpi/160)*dp
        //  scaledDensity = doi/160;
        float scaledDensity = outMetrics.scaledDensity;
        float wDp = widthPixels / scaledDensity;
        float hDp = heightPixels / scaledDensity;
        Log.d("MainActivity", "densityDpi = "+densityDpi);
        Log.d("MainActivity", "widthPixels"+widthPixels);
        Log.d("MainActivity", "heightPixels"+heightPixels);
        Log.d("MainActivity", "scaledDensity"+scaledDensity);
        Log.d("MainActivity", "wDp = "+wDp);
        Log.d("MainActivity", "hDp = "+hDp);

//        Button btn = new Button(this);
//        if(Build.VERSION.SDK_INT>=11){
//            btn.setX(34);
//        }else{
//        }

    }
}

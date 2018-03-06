package yassbusiness.com.application;

import android.app.Application;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ImoocApplication extends Application {

    private static ImoocApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        /*initShareSDK();
        initJPush();*/
    }

    public static ImoocApplication getInstance() {
        return mApplication;
    }

    /*public void initShareSDK() {
        ShareManager.initSDK(this);
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }*/

    //    private void initSpeech() {
    //        StringBuffer param = new StringBuffer();
    //        param.append("appid=" + getString(R.string.app_id));
    //        param.append(",");
    //        param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC);
    //        SpeechUtility.createUtility(this, param.toString());
    //        Log.e("app", SpeechUtility.getUtility().toString());
    //    }
}

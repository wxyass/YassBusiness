package yassbusiness.com.network.http;

import com.yass.okhttp.CommonOkHttpClient;
import com.yass.okhttp.listener.DisposeDataHandle;
import com.yass.okhttp.listener.DisposeDataListener;
import com.yass.okhttp.request.CommonRequest;
import com.yass.okhttp.request.RequestParams;

import yassbusiness.com.module.recommand.BaseRecommandModel;
import yassbusiness.com.module.update.UpdateModel;
import yassbusiness.com.module.user.User;


/**
 * @author: vision
 * @function:
 * @date: 16/8/12
 */
public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 用户登陆请求
     *
     * @param listener
     * @param userName
     * @param passwd
     */
    public static void login(String userName, String passwd, DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("mb", userName);
        params.put("pwd", passwd);
        RequestCenter.postRequest(HttpConstants.LOGIN, params, listener, User.class);
    }

    /**
     * 应用版本号请求
     *
     * @param listener
     */
    public static void checkVersion(DisposeDataListener listener) {

        RequestCenter.postRequest(HttpConstants.CHECK_UPDATE, null, listener, UpdateModel.class);
    }

    /**
     * 首页数据
     * @param listener
     */
    public static void requestRecommandData(DisposeDataListener listener) {

        RequestCenter.postRequest(HttpConstants.HOME_LIST, null, listener, BaseRecommandModel.class);
    }
}

package freedom.wealth.practice.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: captain
 * Time:  2018/3/27 0027
 * Describe:
 */
public class NetRequestUtils {
    private Retrofit retrofit;
    private static NetRequestUtils instance;

    public NetRequestUtils() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().
                connectTimeout(40,TimeUnit.SECONDS)
                .readTimeout(40,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(RequestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public static NetRequestUtils getInstance() {
        if (instance == null) {
            synchronized (NetRequestUtils.class) {
                instance = new NetRequestUtils();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> services){
        return  retrofit.create(services);
    }

}

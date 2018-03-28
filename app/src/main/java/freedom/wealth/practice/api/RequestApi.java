package freedom.wealth.practice.api;

import freedom.wealth.practice.entry.BannerResponse;
import freedom.wealth.practice.entry.HomeArticle;
import freedom.wealth.practice.entry.RegisterResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author: captain
 * Time:  2018/3/27 0027
 * Describe:
 */
public interface RequestApi {
    String BASE_URL = "http://www.wanandroid.com/";

    @GET("banner/json")
    Call<BannerResponse> getBanner();

    @GET("article/list/{page}/json")
    Call<HomeArticle> getArticle(@Path("page")String  page);

    @POST("user/login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("name")String username, @Field("password")String password);

    @POST("user/register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("username")String username,
                                    @Field("password")String password,
                                    @Field("repassword")String repassword);

}

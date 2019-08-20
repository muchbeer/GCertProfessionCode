package muchbeer.raum.com.gcertprofessioncode.service;

import android.os.Build;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    //private static final String URL = "http://localhost:9000/";
    private static final String URL = "http://192.168.66.87:9000/";

    // Create logger
    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    // Create OkHttp Client
    public static OkHttpClient.Builder okHttp =
                        new OkHttpClient.Builder()
                                .readTimeout(15, TimeUnit.SECONDS)
                                .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {

                                Request request = chain.request();
                                request = request.newBuilder()
                                        .addHeader("x-device-type", Build.DEVICE)
                                        .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                                        .build();

                                return chain.proceed(request);

                            }
                        })

                         .addInterceptor(logger);


/*    public WebServiceClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return onOnIntercept(chain);
            }
        });*/

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
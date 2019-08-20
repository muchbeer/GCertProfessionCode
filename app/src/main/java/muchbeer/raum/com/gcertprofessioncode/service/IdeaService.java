package muchbeer.raum.com.gcertprofessioncode.service;

import java.util.HashMap;
import java.util.List;

import muchbeer.raum.com.gcertprofessioncode.entities.RetroIdea;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface IdeaService {

    //Lets find a way to send a request with header
    /*Header to be sent to the server will be as follow
   x-device-type: Android
    Accept-Language: EN*/
   // @Headers("x-device-type: Android")
    //@GET("ideas")
    //Call<List<RetroIdea>> getIdeas(@QueryMap HashMap<String, String> filters);
    //Call<List<RetroIdea>> getIdeas(@Header("Accept-Language") String language);
   @GET("ideas")
    Call<List<RetroIdea>> getIdeas(@QueryMap HashMap<String, String> owner);

    @GET("ideas/{id}")
    Call<RetroIdea> getIdea(@Path("id")int id);

    @POST("ideas")
    Call<RetroIdea> createIdea(@Body RetroIdea newIdea);

    // Service Code
    @FormUrlEncoded
    @PUT("ideas/{id}")
    Call<RetroIdea> updateIdea(
            @Path("id")int id,
            @Field("name")String name,
            @Field("description")String desc,
            @Field("status")String status,
            @Field("owner")String owner
    );

    @DELETE("ideas/{id}")
    Call<RetroIdea> deleteIdea(@Path("id") int id);
}

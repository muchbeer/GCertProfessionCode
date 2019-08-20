package muchbeer.raum.com.gcertprofessioncode.service;

import java.util.HashMap;
import java.util.List;

import muchbeer.raum.com.gcertprofessioncode.entities.RetroIdea;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface IdeaService {

    @GET("ideas")
    Call<List<RetroIdea>> getIdeas(@QueryMap HashMap<String, String> filters);

   /* @GET("ideas")
    Call<List<RetroIdea>> getIdeas(@QueryMap HashMap<String, String> owner);*/

    @GET("ideas/{id}")
    Call<RetroIdea> getIdea(@Path("id")int id);

    @POST("ideas")
    Call<RetroIdea> createIdea(@Body RetroIdea newIdea);
}

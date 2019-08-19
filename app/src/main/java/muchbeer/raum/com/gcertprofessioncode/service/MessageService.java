package muchbeer.raum.com.gcertprofessioncode.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MessageService {
    @GET("messages")
    Call<String> getMessage();


}

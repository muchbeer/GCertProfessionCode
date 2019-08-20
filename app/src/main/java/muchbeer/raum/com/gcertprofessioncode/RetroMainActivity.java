package muchbeer.raum.com.gcertprofessioncode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import muchbeer.raum.com.gcertprofessioncode.entities.RetroIdea;
import muchbeer.raum.com.gcertprofessioncode.recycler.RetroRecyclerAdapter;
import muchbeer.raum.com.gcertprofessioncode.service.IdeaService;
import muchbeer.raum.com.gcertprofessioncode.service.MessageService;
import muchbeer.raum.com.gcertprofessioncode.service.ServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroMainActivity extends AppCompatActivity {
    private static final String LOG_TAG = RetroMainActivity.class.getSimpleName();
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_retro_main);
      //  setContentView(R.layout.sample);
        final Context context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

              final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.retro_recycler_idea_list);
        assert recyclerView != null;

        if (findViewById(R.id.idea_detail_container) != null) {
            mTwoPane = true;
        }

        HashMap<String, String> filters = new HashMap<>();
        filters.put("owner", "Jim");
        filters.put("count", "1");

        new GetMessagesTask().execute();

        IdeaService taskService = ServiceBuilder.buildService(IdeaService.class);
        Call<List<RetroIdea>> retrievIdeas = taskService.getIdeas(filters);
      //  Call<List<RetroIdea>> retrievIdeas = taskService.getIdeas("EN");
        retrievIdeas.enqueue(new Callback<List<RetroIdea>>() {
            @Override
            public void onResponse(Call<List<RetroIdea>> call, Response<List<RetroIdea>> response) {


                //Setting progressbar Gone noted
                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new RetroRecyclerAdapter(response.body()));
                }else if (response.code() == 400) {
                    //Go back to the logging message
                    Log.d(LOG_TAG, "Response pass with Loging authetication error : ");
                }else {
                    Log.d(LOG_TAG, "Response pass with Failed to retrieve value items : ");
                }

            }

            @Override
            public void onFailure(Call<List<RetroIdea>> call, Throwable t) {

                if(t instanceof IOException){
                    Log.d(LOG_TAG, "tHE error message is Connection problem : ");
                } else
                Log.d(LOG_TAG, "The error message is failed to retrieve item with : "+ t.getMessage());
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context, RetroCreateIdeaActivity.class);
               context.startActivity(intent);

                Toast.makeText(getApplicationContext(), "Please Enter new Idea", Toast.LENGTH_LONG).show();
          /*      call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(LOG_TAG, "The response is : "+ response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(LOG_TAG, "The error message is : "+ t.getMessage());
                    }
                });*/

            }
        });


      //  recyclerView.setAdapter(new RetroRecyclerAdapter(SampleContent.IDEAS));
    }

    public class GetMessagesTask extends AsyncTask<Void, Void, Boolean> {

        private String messages= "";
        @Override
        protected Boolean doInBackground(Void... voids) {
            MessageService taskService = ServiceBuilder.buildService(MessageService.class);
            Call<String> call = taskService.getMessage();
                try {
                    messages = call.execute().body();
                }catch (Exception e) {
                    //nice

                }


            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //Desplay messages
        }
    }
}

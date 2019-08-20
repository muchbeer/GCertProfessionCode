package muchbeer.raum.com.gcertprofessioncode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import muchbeer.raum.com.gcertprofessioncode.entities.RetroIdea;
import muchbeer.raum.com.gcertprofessioncode.service.IdeaService;
import muchbeer.raum.com.gcertprofessioncode.service.ServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroCreateIdeaActivity extends AppCompatActivity {

    private static final String LOG_TAG = RetroCreateIdeaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro_create_idea);

        Context mContext;
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button createIdea = (Button) findViewById(R.id.idea_create);
        final EditText ideaName = (EditText) findViewById(R.id.idea_name);
        final EditText ideaDescription = (EditText) findViewById(R.id.idea_description);
        final EditText ideaOwner = (EditText) findViewById(R.id.idea_owner);
        final EditText ideaStatus = (EditText) findViewById(R.id.idea_status);

        createIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetroIdea newIdea = new RetroIdea();
                newIdea.setName(ideaName.getText().toString());
                newIdea.setDescription(ideaDescription.getText().toString());
                newIdea.setStatus(ideaStatus.getText().toString());
                newIdea.setOwner(ideaOwner.getText().toString());

                IdeaService setRetroIdea = ServiceBuilder.buildService(IdeaService.class);
                Call<RetroIdea> createRetroIdea = setRetroIdea.createIdea(newIdea);

                createRetroIdea.enqueue(new Callback<RetroIdea>() {
                    @Override
                    public void onResponse(Call<RetroIdea> call, Response<RetroIdea> response) {
                        Intent openNewIdea = new Intent(getApplicationContext(), RetroMainActivity.class);
                        startActivity(openNewIdea);
                    }

                    @Override
                    public void onFailure(Call<RetroIdea> call, Throwable t) {
                        Log.d(LOG_TAG, "The error message is : "+ t.getMessage());
                    }
                });

                //SampleContent.createIdea(newIdea);
            }
        });
    }
}

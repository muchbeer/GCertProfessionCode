package muchbeer.raum.com.gcertprofessioncode.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.appbar.CollapsingToolbarLayout;

import muchbeer.raum.com.gcertprofessioncode.R;
import muchbeer.raum.com.gcertprofessioncode.RetroMainActivity;
import muchbeer.raum.com.gcertprofessioncode.entities.RetroIdea;

public class IdeaDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private RetroIdea mItem;

    public IdeaDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.retro_idea_detail, container, false);

        final Context context = getContext();

        Button updateIdea = (Button) rootView.findViewById(R.id.idea_update);
        Button deleteIdea = (Button) rootView.findViewById(R.id.idea_delete);

        final EditText ideaName = (EditText) rootView.findViewById(R.id.idea_name);
        final EditText ideaDescription = (EditText) rootView.findViewById(R.id.idea_description);
        final EditText ideaStatus = (EditText) rootView.findViewById(R.id.idea_status);
        final EditText ideaOwner = (EditText) rootView.findViewById(R.id.idea_owner);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            Activity activity = this.getActivity();
            final CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

           // mItem = SampleContent.getIdeaById(getArguments().getInt(ARG_ITEM_ID));

            ideaName.setText(mItem.getName());
            ideaDescription.setText(mItem.getDescription());
            ideaOwner.setText(mItem.getOwner());
            ideaStatus.setText(mItem.getStatus());

            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }

        updateIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetroIdea newIdea = new RetroIdea();
                newIdea.setId(getArguments().getInt(ARG_ITEM_ID));
                newIdea.setName(ideaName.getText().toString());
                newIdea.setDescription(ideaDescription.getText().toString());
                newIdea.setStatus(ideaStatus.getText().toString());
                newIdea.setOwner(ideaOwner.getText().toString());

                //SampleContent.updateIdea(newIdea);
                Intent intent = new Intent(getContext(), RetroMainActivity.class);
                startActivity(intent);
            }
        });

        deleteIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   SampleContent.deleteIdea(getArguments().getInt(ARG_ITEM_ID));
                Intent intent = new Intent(getContext(), RetroMainActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}

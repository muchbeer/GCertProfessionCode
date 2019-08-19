package muchbeer.raum.com.gcertprofessioncode.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import muchbeer.raum.com.gcertprofessioncode.R;
import muchbeer.raum.com.gcertprofessioncode.RetroIdeaActivity;
import muchbeer.raum.com.gcertprofessioncode.entities.RetroIdea;
import muchbeer.raum.com.gcertprofessioncode.fragments.IdeaDetailFragment;

public class RetroRecyclerAdapter  extends RecyclerView.Adapter<RetroRecyclerAdapter.ViewHolder> {

    private final List<RetroIdea> mValues;


    private boolean mTwoPane;
    public RetroRecyclerAdapter(List<RetroIdea> items) {
        mValues = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.retro_idea_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context mContext = view.getContext();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt(IdeaDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
                    IdeaDetailFragment fragment = new IdeaDetailFragment();
                    fragment.setArguments(arguments);

                  /*  ((FragmentActivity)mContext).beginTransaction
                    getSupportFragmentManager()*/

                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.idea_detail_container, fragment)
                            .commit();
                } else {

                    Intent intent = new Intent(mContext, RetroIdeaActivity.class);
                    intent.putExtra(IdeaDetailFragment.ARG_ITEM_ID, holder.mItem.getId());

                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public  View mView;
        public TextView mIdView;
        public TextView mContentView;
        public RetroIdea mItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            mIdView = (TextView) itemView.findViewById(R.id.id);
            mContentView = (TextView) itemView.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

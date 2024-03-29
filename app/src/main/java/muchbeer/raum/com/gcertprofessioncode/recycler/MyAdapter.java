package muchbeer.raum.com.gcertprofessioncode.recycler;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import muchbeer.raum.com.gcertprofessioncode.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CoinViewHolder> {

    List<CoinModel> mItems = new ArrayList<>();
    public final String STR_TEMPLATE_NAME = "%s\t\t\t\t\t\t%s";
    public final String STR_TEMPLATE_PRICE = "%s$\t\t\t\t\t\t24H Volume:\t\t\t%s$";
    private final Handler mHandler = new Handler();

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoinViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        final CoinModel model = mItems.get(position);
        holder.tvNameAndSymbol.setText(String.format(STR_TEMPLATE_NAME, model.name, model.symbol));
        holder.tvPriceAndVolume.setText(String.format(STR_TEMPLATE_PRICE, model.priceUsd, model.volume24H));
        Glide.with(holder.ivIcon).load(model.imageUrl).into(holder.ivIcon);
    }

    public void setItems(List<CoinModel> items) {
        this.mItems.clear();
        this.mItems.addAll(items);
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class CoinViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameAndSymbol;
        TextView tvPriceAndVolume;
        ImageView ivIcon;

        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameAndSymbol = itemView.findViewById(R.id.tvNameAndSymbol);
            tvPriceAndVolume = itemView.findViewById(R.id.tvPriceAndVolume);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}

package sedano.com.currencyexchanger.view.currencies;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sedano.com.currencyexchanger.R;
import sedano.com.currencyexchanger.model.CurrencyPresentation;

import static sedano.com.currencyexchanger.Constants.ASSETS_PATH;
import static sedano.com.currencyexchanger.Constants.PNG;

public class CurrenciesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private List<CurrencyPresentation> list;
    private OnItemClickListener listener;

    public CurrenciesAdapter(Context context, List<CurrencyPresentation> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CurrencyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.currency_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CurrencyViewHolder currencyViewHolder = (CurrencyViewHolder) holder;
        currencyViewHolder.tvCurrencyName.setText(list.get(position).getName());
        currencyViewHolder.tvExchangeRate.setText(String.valueOf(list.get(position).getExchangeRate()));
        String fileName = list.get(position).getName().toLowerCase();
        Glide.with(context)
                .load(Uri.parse(ASSETS_PATH+fileName+PNG))
                .into(currencyViewHolder.ivFlag);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<CurrencyPresentation> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    interface OnItemClickListener {
        void onItemSelected(CurrencyPresentation currencyPresentation);
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.ivFlag)
        ImageView ivFlag;
        @Bind(R.id.tvCurrencyName)
        TextView tvCurrencyName;
        @Bind(R.id.tvExchangeRate)
        TextView tvExchangeRate;

        public CurrencyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemSelected(list.get(getLayoutPosition()));
        }
    }
}

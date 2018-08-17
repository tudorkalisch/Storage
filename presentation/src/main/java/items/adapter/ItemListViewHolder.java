package items.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.deakdan.labelreaderv2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemListTextView)
    TextView itemName;
    @BindView(R.id.itemListCardView)
    CardView cardView;
    public void bind(final String item, final OnItemClickListener listener) {
        cardView.setOnClickListener(v -> listener.onItemClick(item));
    }

    ItemListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

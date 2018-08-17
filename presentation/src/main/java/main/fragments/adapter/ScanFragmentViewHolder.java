package main.fragments.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deakdan.labelreaderv2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import scans.edit.EditScanActivity;
import utils.Constants;

public class ScanFragmentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.cardViewScan)
    public CardView cardView;
    @BindView(R.id.imageCardScan)
    public
    ImageView imageCard;
    @BindView(R.id.titleCardScan)
    public
    TextView titleCard;

    public ScanFragmentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final String name) {
        cardView.setOnClickListener(view -> {
            Intent intent = new Intent(cardView.getContext(), EditScanActivity.class);
            intent.putExtra(Constants.TITLE_EDIT, name);
            cardView.getContext().startActivity(intent);
        });
    }
}

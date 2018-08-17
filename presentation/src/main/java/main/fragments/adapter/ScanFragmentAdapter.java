package main.fragments.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deakdan.labelreaderv2.R;

import main.fragments.model.MainViewModel;
import main.fragments.model.MainViewModelItem;

public class ScanFragmentAdapter extends RecyclerView.Adapter<ScanFragmentViewHolder> {
    private MainViewModel mData;

    public ScanFragmentAdapter(MainViewModel mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ScanFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_scans, parent, false);

        return new ScanFragmentViewHolder(v);
    }

    private MainViewModelItem getItem(int position) {
        return mData.getItem(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanFragmentViewHolder holder, int position) {
        holder.bind(getItem(position).getTitle());
        holder.titleCard.setText(mData.getTitle(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(MainViewModel data) {
        this.mData = data;
    }
}

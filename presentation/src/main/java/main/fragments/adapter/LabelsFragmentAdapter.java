package main.fragments.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deakdan.labelreaderv2.R;

import main.fragments.model.MainViewModel;
import main.fragments.model.MainViewModelItem;

public class LabelsFragmentAdapter extends RecyclerView.Adapter<LabelFragmentViewHolder> {
    private MainViewModel mData;

    public LabelsFragmentAdapter(MainViewModel mData) {
        this.mData = mData;

    }

    @NonNull
    @Override
    public LabelFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_items_labels, parent, false);

        return new LabelFragmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LabelFragmentViewHolder holder, int position) {
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

    private MainViewModelItem getItem(int position){
        return mData.getItem(position);
    }
}



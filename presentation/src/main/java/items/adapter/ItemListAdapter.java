package items.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deakdan.labelreaderv2.R;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListViewHolder> {
    private final List<String> myData;
    private final OnItemClickListener listener;

    public ItemListAdapter(List<String> myData, OnItemClickListener listener) {
        this.myData = myData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recycler_view, parent, false);
        return new ItemListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder holder, int position) {
        holder.bind(myData.get(position), listener);
        holder.itemName.setText(myData.get(position));
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }
}

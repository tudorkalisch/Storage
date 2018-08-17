package labels.add.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deakdan.labelreaderv2.R;

import labels.model.LabelViewModel;

public class AddLabelAdapter extends RecyclerView.Adapter<ViewHolder> {

    private LabelViewModel mDataset;

    public AddLabelAdapter(LabelViewModel myDataset) {
        mDataset = myDataset;
    }

    public LabelViewModel getDataset() {
        return this.mDataset;
    }

    public int labelsSize() {
        return this.mDataset.getLabelList().size();
    }

    public void addLabel() {
        this.mDataset.addLabel("");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_lable_recycler_view, parent, false);

        return new ViewHolder(v, new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.mEditText.setHint(R.string.label_name);

    }

    public String getItemName() {
        return mDataset.getItemName();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setData(LabelViewModel data) {
        this.mDataset = data;
    }

    public void setName(String name) {
        this.mDataset.setItemName(name);
    }

    class MyCustomEditTextListener implements TextWatcher {
        private int position;

        void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            mDataset.setLabel(position, charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }
}
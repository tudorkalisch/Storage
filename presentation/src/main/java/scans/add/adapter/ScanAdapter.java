package scans.add.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.deakdan.labelreaderv2.R;

import scans.model.ScanViewModel;

public class ScanAdapter extends RecyclerView.Adapter<ScanViewHolder> {
    private ScanViewModel myData;
    private String textToCopy = "";

    private String getTextToCopy() {
        return textToCopy;
    }

    public void setTextToCopy(String textToCopy) {
        this.textToCopy = textToCopy;
    }

    public ScanAdapter(ScanViewModel myData) {
        this.myData = myData;
    }

    @NonNull
    @Override
    public ScanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.scan_row, parent, false);
        return new ScanViewHolder(cardView, new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull ScanViewHolder holder, int position) {
        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.bind(() -> {
            String text = getTextToCopy();
            setTextToCopy("");
            return text;
        });
        holder.rowField.setText(myData.getField(holder.getAdapterPosition()));
        holder.rowValue.setText(myData.getValue(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public void setData(ScanViewModel data) {
        this.myData = data;
    }

    class MyCustomEditTextListener implements TextWatcher {
        private int position;

        void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            myData.setValue(position, charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    public ScanViewModel getData() {
        return myData;
    }
}

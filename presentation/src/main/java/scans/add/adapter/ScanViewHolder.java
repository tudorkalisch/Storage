package scans.add.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deakdan.labelreaderv2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rowAddCard)
    CardView cardView;
    @BindView(R.id.rowFieldAddTV)
    TextView rowField;
    @BindView(R.id.rowValueAddET)
    EditText rowValue;
    public ScanAdapter.MyCustomEditTextListener myCustomEditTextListener;

    ScanViewHolder(View itemView, ScanAdapter.MyCustomEditTextListener myCustomEditTextListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.myCustomEditTextListener = myCustomEditTextListener;
        this.rowValue.addTextChangedListener(myCustomEditTextListener);
    }

    public void bind(final OnClickListener listener) {
        rowValue.setOnClickListener(v -> {
            String text = listener.onClick();
            if (!text.equals("")) {
                rowValue.setText(text);
            }
        });
    }
}


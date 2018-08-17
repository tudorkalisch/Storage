package labels.edit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.deakdan.labelreaderv2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.editLableEditText)
    EditText mEditText;
    public EditLabelAdapter.MyCustomEditTextListener myCustomEditTextListener;

    ViewHolder(View v, EditLabelAdapter.MyCustomEditTextListener myCustomEditTextListener) {
        super(v);
        ButterKnife.bind(this, v);
        this.myCustomEditTextListener = myCustomEditTextListener;
        this.mEditText.addTextChangedListener(myCustomEditTextListener);
    }


}
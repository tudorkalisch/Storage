package labels.add;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.deakdan.labelreaderv2.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.BaseActivity;
import labels.add.adapter.AddLabelAdapter;
import labels.model.LabelViewModel;
import main.MainActivity;

public class AddLabelActivity extends BaseActivity implements AddLabelContract.View {
    @BindView(R.id.addLableRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.itemNameEditText)
    EditText itemName;

    @Inject
    AddLabelPresenter addLabelPresenter;
    AddLabelAdapter addLabelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_label);
        ButterKnife.bind(this);
        init();
    }

    void init() {
        activityComponent().inject(this);
        addLabelPresenter.attachView(this);
        addLabelAdapter = new AddLabelAdapter(new LabelViewModel());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(addLabelAdapter);
        recyclerView.setHasFixedSize(false);
    }

    @Override
    public void showData(LabelViewModel model) {
        addLabelAdapter.setData(model);
        addLabelAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        errorToast("Error");
    }

    @Override
    public void showSuccess() {
        successToast("Success!");
    }

    @OnClick(R.id.addLabelButton)
    public void addNewLabel() {
        addLabelAdapter.addLabel();
        addLabelAdapter.notifyItemChanged(addLabelAdapter.labelsSize() - 1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addLabelPresenter.detachView();
    }
    @OnClick(R.id.backButton)
    public void backBtn(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.addLabelSubmitButton)
    public void onSubmit() {
        String stringName = itemName.getText().toString();
        if (TextUtils.isEmpty(stringName)) {
            itemName.setError(getString(R.string.error_null_check));
            itemName.requestFocus();
            return;
        }
        addLabelAdapter.setName(this.itemName.getText().toString());
        addLabelPresenter.saveLabel(addLabelAdapter.getDataset());
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
        this.startActivity(myIntent, options.toBundle());
    }

}



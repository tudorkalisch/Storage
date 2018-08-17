package labels.edit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.example.deakdan.labelreaderv2.R;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.BaseActivity;
import labels.edit.adapter.EditLabelAdapter;
import labels.model.LabelViewModel;
import main.MainActivity;
import utils.Constants;

public class EditLabelActivity extends BaseActivity implements EditLabelContract.View {
    @BindView(R.id.editLabelRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.itemNameEditText)
    EditText itemName;
    @Inject
    EditLabelPresenter editLabelPresenter;
    EditLabelAdapter editLabelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_label);
        ButterKnife.bind(this);
        init();
    }

    void init() {
        activityComponent().inject(this);
        editLabelPresenter.attachView(this);
        editLabelAdapter = new EditLabelAdapter(new LabelViewModel());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(editLabelAdapter);
        recyclerView.setHasFixedSize(false);
        editLabelPresenter.getDataLabel(Objects.requireNonNull(getIntent().getExtras()).getString(Constants.TITLE_EDIT));
    }

    @Override
    public void showData(LabelViewModel model) {
        itemName.setText(model.getItemName());
        editLabelAdapter.setData(model);
        editLabelAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        errorToast("Error");
    }

    @Override
    public void showSuccess() {
        successToast("Success!");
    }

    @OnClick(R.id.addNewEditLableButton)
    public void addNewLabel() {
        editLabelAdapter.addField();
        editLabelAdapter.notifyItemChanged(editLabelAdapter.getLabelsSize() - 1);
    }
    @OnClick(R.id.backButton)
    public void backBtn(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        editLabelPresenter.disposeGetLabel();
        editLabelPresenter.disposeSaveLabel();
    }

    @OnClick(R.id.updateLableBtn)
    void updateLabelBT() {
        LabelViewModel model = editLabelAdapter.getmDataset();
        model.setItemName(itemName.getText().toString());
        this.editLabelPresenter.saveLabel(model);
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
        this.startActivity(myIntent, options.toBundle());
    }

}

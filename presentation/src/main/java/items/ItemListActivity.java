package items;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.deakdan.labelreaderv2.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.BaseActivity;
import items.adapter.ItemListAdapter;
import labels.add.AddLabelActivity;
import scans.add.AddScanActivity;
import utils.Constants;

public class ItemListActivity extends BaseActivity implements ItemListContract.View {
    @Inject
    ItemListPresenter itemListPresenter;
    @BindView(R.id.itemListRecyclerView)
    RecyclerView recyclerView;
    ItemListAdapter itemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ButterKnife.bind(this);
        init();
    }

    void init() {
        activityComponent().inject(this);
        itemListPresenter.attachView(this);
        itemListPresenter.getDataLabels();
    }

    @Override
    public void showData(List<String> model) {
        itemListAdapter = new ItemListAdapter(model, item -> {
            String path = getIntent().getStringExtra(Constants.IMAGE);
            String imageName = getIntent().getStringExtra(Constants.IMAGE_NAME);
            Intent intent = new Intent(this, AddScanActivity.class);
            intent.putExtra(Constants.IMAGE, path);
            intent.putExtra(Constants.IMAGE_NAME, imageName);
            intent.putExtra(Constants.ITEM_NAME, item);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemListAdapter);

        recyclerView.setHasFixedSize(false);
    }

    @OnClick(R.id.addNewItemButton)
    protected void goToAddItem() {
        Intent intent = new Intent(this, AddLabelActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemListPresenter.dispose();
    }

    @Override
    public void showError() {
        this.errorToast("Error");
    }

}

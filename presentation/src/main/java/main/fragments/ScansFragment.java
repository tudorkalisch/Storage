package main.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deakdan.labelreaderv2.R;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.fragments.adapter.ScanFragmentAdapter;
import main.fragments.model.MainViewModel;

import static com.example.deakdan.labelreaderv2.R.layout.fragment_scans;


public class ScansFragment extends BaseFragment implements ScanFragmentContract.View {
    @BindView(R.id.recyclerViewScans)
    RecyclerView recyclerView;
    @BindView(R.id.textViewEmptyScan)
    TextView textViewEmpty;
    @Inject
    ScanFragmentPresenter mainPresenter;
    ScanFragmentAdapter scanFragmentAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(fragment_scans, container, false);
        ButterKnife.bind(this, view);
        activityComponent().inject(this);
        mainPresenter.attachView(this);
        scanFragmentAdapter = new ScanFragmentAdapter(new MainViewModel());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(scanFragmentAdapter);

        //Animate the float button when scrolling
        final FloatingActionMenu floatingActionMenu = Objects.requireNonNull(getActivity()).findViewById(R.id.mainFloatBtn);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    floatingActionMenu.hideMenu(true);
                } else if (dy < 0) {
                    floatingActionMenu.showMenu(true);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainPresenter.getDataScans();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.dispose();
        mainPresenter.detachView();
    }

    @Override
    public void showData(MainViewModel item) {
        scanFragmentAdapter.setData(item);
        if (scanFragmentAdapter.getItemCount() == 0) {
            textViewEmpty.setVisibility(View.VISIBLE);
        } else {
            textViewEmpty.setVisibility(View.GONE);
        }
        scanFragmentAdapter.notifyDataSetChanged();
    }
}

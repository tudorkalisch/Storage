package main.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
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
import main.fragments.adapter.LabelsFragmentAdapter;
import main.fragments.model.MainViewModel;


public class LabelsFragment extends BaseFragment implements LabelsFragmentContract.View {

    @BindView(R.id.recyclerViewLabels)
    RecyclerView recyclerView;
    @BindView(R.id.textViewEmptyLabel)
    TextView textViewEmptyLabel;
    @Inject
    LabelsFragmentPresenter fragmentPresenter;
    LabelsFragmentAdapter labelFragmentAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_labels, container, false);
        activityComponent().inject(this);
        ButterKnife.bind(this, view);
        fragmentPresenter.attachView(this);
        labelFragmentAdapter = new LabelsFragmentAdapter(new MainViewModel());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(labelFragmentAdapter);

        //Animate the float button when scrolling
        final FloatingActionMenu floatingActionMenu = Objects.requireNonNull(getActivity()).findViewById(R.id.mainFloatBtn);
        ViewPager viewPager = Objects.requireNonNull(getActivity()).findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    floatingActionMenu.hideMenu(true);
                } else {
                    floatingActionMenu.showMenu(true);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state > 0) {
                    floatingActionMenu.hideMenu(false);
                } else {
                    floatingActionMenu.showMenu(false);
                }

            }
        });

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
        fragmentPresenter.getDataLabels();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentPresenter.dispose();
    }

    @Override
    public void showData(MainViewModel item) {
        labelFragmentAdapter.setData(item);
        if (labelFragmentAdapter.getItemCount() == 0) {
            textViewEmptyLabel.setVisibility(View.VISIBLE);
        } else {
            textViewEmptyLabel.setVisibility(View.GONE);

        }
        labelFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        this.errorToast("Error");
    }

}
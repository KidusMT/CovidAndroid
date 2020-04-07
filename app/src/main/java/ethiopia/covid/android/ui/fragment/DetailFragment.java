package ethiopia.covid.android.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import ethiopia.covid.android.R;
import ethiopia.covid.android.data.DetailItem;
import ethiopia.covid.android.ui.adapter.DetailRecyclerAdapter;

/**
 * Created by BrookMG on 3/23/2020 in ethiopia.covid.android.ui.fragment
 * inside the project CoVidEt .
 */
public class DetailFragment extends BaseFragment {

    private RecyclerView recyclerView;

    public static DetailFragment newInstance() {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private void handleWindowInsets(RecyclerView recyclerView) {
        recyclerView.setOnApplyWindowInsetsListener((v1, insets) -> {
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
            marginParams.setMargins( 0, insets.getSystemWindowInsetTop(), 0, 0);
            recyclerView.setLayoutParams(marginParams);

            return insets;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            recyclerView.requestApplyInsets();
            handleWindowInsets(recyclerView);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.detail_fragment, container, false);
        recyclerView = mainView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity() , RecyclerView.VERTICAL , false));

        List<DetailItem> details = new ArrayList<>();
        details.add(new DetailItem("What is corona virus", R.drawable.covid_virus_icon, true, ""));
        details.add(new DetailItem("Wear a mask where in crowded place", R.drawable.wear_masks ,true, ""));
        details.add(new DetailItem("Wash your hands for 20+ seconds", R.drawable.wash_hands, true, ""));
        details.add(new DetailItem("Cover your mouth and nose when coughing or sneezing", R.drawable.cover_face_coughing, true, ""));

        recyclerView.setAdapter(new DetailRecyclerAdapter(details));
        return mainView;
    }

}

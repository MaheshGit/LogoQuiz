package mahesh.kumar.phonepe.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mahesh.kumar.phonepe.R;
import mahesh.kumar.phonepe.ui.BaseFragment;
import mahesh.kumar.phonepe.utils.ViewModelProviderFactory;

public class GameFragment extends BaseFragment implements LogoSelectedListener {
    private static final String TAG = GameFragment.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private ListViewModel viewModel;
    private LogoAdapter adapter;

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);
        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        adapter = new LogoAdapter( this);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        observableViewModel();
    }

    private void observableViewModel() {
        viewModel.observeRepos().removeObservers(getViewLifecycleOwner());
        viewModel.observeRepos().observe(getViewLifecycleOwner(), new Observer<Resource<List<Repo>>>() {
            @Override
            public void onChanged(Resource<List<Repo>> listResource) {
                if(listResource != null){
                    Log.d(TAG, "onChanged: " + listResource.data);
                    switch (listResource.status){
                        case LOADING:{
                            loadingView.setVisibility(View.VISIBLE);
                            errorTextView.setVisibility(View.GONE);
                            listView.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: PostsFragment: LOADING...");
                            break;
                        }

                        case SUCCESS:{
                            Log.d(TAG, "onChanged: PostsFragment: got repos.");
                            loadingView.setVisibility(View.GONE);
                            errorTextView.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            adapter.setRepos(listResource.data);
                            break;
                        }

                        case ERROR:{
                            loadingView.setVisibility(View.GONE);
                            errorTextView.setVisibility(View.VISIBLE);
                            errorTextView.setText("An Error Occurred While Loading Data!");
                            listView.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: PostsFragment: ERROR... " + listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onLogoSelected(Repo repo) {
        // Hanlde logo clicked case here
    }
}

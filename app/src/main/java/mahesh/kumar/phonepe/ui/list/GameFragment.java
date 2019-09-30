package mahesh.kumar.phonepe.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import mahesh.kumar.phonepe.R;
import mahesh.kumar.phonepe.ui.BaseFragment;
import mahesh.kumar.phonepe.utils.Utils;
import mahesh.kumar.phonepe.utils.ViewModelProviderFactory;

public class GameFragment extends BaseFragment {
    private static final String TAG = GameFragment.class.getSimpleName();

    @BindView(R.id.gridViewAnswer)
    GridView gridViewAnswer;
    @BindView(R.id.gridViewSuggest)
    GridView gridViewSuggest;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.imgLogo)
    ImageView imgViewQuestion;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private ListViewModel viewModel;
    private List<String> suggestSource = new ArrayList<>();
    private GridViewAnswerAdapter answerAdapter;
    private GridViewSuggestAdapter suggestAdapter;

    private char[] answer;
    String correct_answer;
    int[] image_list = {
            R.drawable.blogger,
            R.drawable.deviantart,
            R.drawable.digg,
            R.drawable.dropbox};

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);
        init();
    }

    private void init() {
        setupList();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                for (int i = 0; i < Utils.user_submit_answer.length; i++)
                    result += String.valueOf(Utils.user_submit_answer[i]);
                if (result.equals(correct_answer)) {
                    Toast.makeText(getActivity(), "Finish ! This is " + result, Toast.LENGTH_SHORT).show();

                    //Reset
                    Utils.count = 0;
                    Utils.user_submit_answer = new char[correct_answer.length()];

                    //Set Adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(), getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource, getApplicationContext(), MainActivity.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    setupList();
                } else {
                    Toast.makeText(getActivity(), "Incorrect!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void observableViewModel() {
        viewModel.observeRepos().removeObservers(getViewLifecycleOwner());
        viewModel.observeRepos().observe(getViewLifecycleOwner(), new Observer<Resource<List<Repo>>>() {
            @Override
            public void onChanged(Resource<List<Repo>> listResource) {
                if (listResource != null) {
                    Log.d(TAG, "onChanged: " + listResource.data);
                    switch (listResource.status) {
                        case LOADING: {
                            loadingView.setVisibility(View.VISIBLE);
                            errorTextView.setVisibility(View.GONE);
                            listView.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: PostsFragment: LOADING...");
                            break;
                        }

                        case SUCCESS: {
                            Log.d(TAG, "onChanged: PostsFragment: got repos.");
                            loadingView.setVisibility(View.GONE);
                            errorTextView.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            adapter.setRepos(listResource.data);
                            break;
                        }

                        case ERROR: {
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

    private void setupList() {
        //Random logo
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);

        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/") + 1);

        answer = correct_answer.toCharArray();

        Utils.user_submit_answer = new char[answer.length];

        //Add Answer character to List
        suggestSource.clear();
        for (char item : answer) {
            //Add logo name to list
            suggestSource.add(String.valueOf(item));
        }

        //Random add some character to list
        for (int i = answer.length; i < answer.length * 2; i++)
            suggestSource.add(Utils.alphabet_character[random.nextInt(Utils.alphabet_character.length)]);

        //Sort random
        Collections.shuffle(suggestSource);

        //Set for GridView
        answerAdapter = new GridViewAnswerAdapter(setupNullList(), this);
        suggestAdapter = new GridViewSuggestAdapter(suggestSource, this, this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);
    }

    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for (int i = 0; i < answer.length; i++)
            result[i] = ' ';
        return result;
    }
}

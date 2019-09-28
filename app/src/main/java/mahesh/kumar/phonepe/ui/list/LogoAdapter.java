package mahesh.kumar.phonepe.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mahesh.kumar.phonepe.R;

public class LogoAdapter extends RecyclerView.Adapter<LogoAdapter.RepoViewHolder>{

    private final LogoSelectedListener repoSelectedListener;
    private List<Repo> repos = new ArrayList<>();

    public LogoAdapter(LogoSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    @Override
    public long getItemId(int position) {
        return repos.get(position).id;
    }

    public void setRepos(List<Repo> repos){
        this.repos = repos;
        notifyDataSetChanged();
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name)
        TextView repoNameTextView;
        @BindView(R.id.tv_repo_description)
        TextView repoDescriptionTextView;
        @BindView(R.id.tv_forks)
        TextView forksTextView;
        @BindView(R.id.tv_stars)
        TextView starsTextView;

        private Repo repo;

        RepoViewHolder(View itemView, final LogoSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (repo != null) {
                        repoSelectedListener.onLogoSelected(repo);
                    }
                }
            });
        }

        void bind(Repo repo) {
            this.repo = repo;
            repoNameTextView.setText(repo.name);
            repoDescriptionTextView.setText(repo.description);
            forksTextView.setText(String.valueOf(repo.forks));
            starsTextView.setText(String.valueOf(repo.stars));
        }
    }
}

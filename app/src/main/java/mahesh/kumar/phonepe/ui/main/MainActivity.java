package mahesh.kumar.phonepe.ui.main;

import android.os.Bundle;

import mahesh.kumar.phonepe.R;
import mahesh.kumar.phonepe.ui.BaseActivity;
import mahesh.kumar.phonepe.ui.list.GameFragment;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new GameFragment()).commit();
    }
}

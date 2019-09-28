package mahesh.kumar.phonepe.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import mahesh.kumar.phonepe.R;
import mahesh.kumar.phonepe.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMainActivity();
        finish();
    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
    }

}

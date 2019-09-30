package mahesh.kumar.phonepe.ui.list;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;

public class GridViewAnswerAdapter extends BaseAdapter {
    private char[] answerCharacter;
    private Context context;

    public GridViewAnswerAdapter(@NonNull char[] answerCharacter, @NonNull Context context) {
        this.answerCharacter = answerCharacter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return answerCharacter.length;
    }

    @Override
    public Object getItem(int position) {
        return answerCharacter[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView == null)
        {
            //Create new button
            button = new Button(context);
            button.setLayoutParams(new GridView.LayoutParams(85,85));
            button.setPadding(8,8,8,8);
            button.setBackgroundColor(Color.DKGRAY);
            button.setTextColor(Color.YELLOW);
            button.setText(String.valueOf(answerCharacter[position]));
        }
        else
            button=(Button)convertView;
        return button;
    }
}

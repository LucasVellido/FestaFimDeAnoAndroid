package com.example.udemy.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.udemy.festafimdeano.R;
import com.example.udemy.festafimdeano.data.SecurityPreferences;

import static com.example.udemy.festafimdeano.constant.FimDeAnoConstants.CONFIRMATION_NO;
import static com.example.udemy.festafimdeano.constant.FimDeAnoConstants.CONFIRMATION_YES;
import static com.example.udemy.festafimdeano.constant.FimDeAnoConstants.PRESENCE_KEY;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkBoxParticipate = findViewById(R.id.checkbox_participate);
        this.mViewHolder.checkBoxParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkbox_participate) {
            if (this.mViewHolder.checkBoxParticipate.isChecked()) {
                /*Salvando a presença*/
                this.mSecurityPreferences.storeString(PRESENCE_KEY, CONFIRMATION_YES);
            } else {
                this.mSecurityPreferences.storeString(PRESENCE_KEY, CONFIRMATION_NO);
                /*Salvando a ausencia*/
            }
        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String presence = extras.getString(PRESENCE_KEY);

            if (presence != null && presence.equals(CONFIRMATION_YES)) {
                this.mViewHolder.checkBoxParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkBoxParticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder {

        CheckBox checkBoxParticipate;
    }
}

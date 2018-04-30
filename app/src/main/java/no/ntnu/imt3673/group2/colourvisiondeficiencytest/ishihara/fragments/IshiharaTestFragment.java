package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.io.File;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Test;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaPlate;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaTestActivity;

public class IshiharaTestFragment extends Fragment {
    private static final String TAG = "IshiharaTestFragment";
    private static final int TIMER_MAX = 8000;
    IshiharaTestActivity activity;
    IshiharaPlate plate;
    TestInfo testInfo;

    ImageView imageView;
    Button button;
    EditText editText;

    ProgressBar progressBar;

    //Timer t = new Timer();
    CountDownTimer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.activity = (IshiharaTestActivity) getActivity();
        this.plate = this.activity.getCurrentPlate();
        this.testInfo = this.activity.getTestInfo();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ishihara_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.imageView = view.findViewById(R.id.iv_ishihara_test_plate_picture);
        File imageFile = new File(getActivity().getApplicationContext().getFilesDir(),
                Test.TEST_FOLDER + File.separator + testInfo.getId() + File.separator + this.plate.getFilename());
        Picasso.get().load(imageFile).into(this.imageView);

        this.editText = view.findViewById(R.id.et_ishihare_test_input);

        this.progressBar = view.findViewById(R.id.pb_timer);
        this.progressBar.setMax(TIMER_MAX);

        timer = new CountDownTimer(TIMER_MAX, 100) {

            @Override
            public void onTick(long l) {
                progressBar.setProgress((int) l);
            }

            @Override
            public void onFinish() {
                activity.storeResultAndNext(editText.getText().toString());
            }

        }.start();

        /*
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                activity.storeResultAndNext(editText.getText().toString());
            }
        }, 5000);
        */

        this.button = view.findViewById(R.id.btn_ishihara_test_button);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                activity.storeResultAndNext(editText.getText().toString());
            }
        });



        Log.d(TAG, "Test fragment created : " + imageFile.getAbsolutePath() +  " : " + imageFile.exists());
    }
}

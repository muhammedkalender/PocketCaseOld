package software.kalender.pocketcase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.List;
import java.util.zip.Inflater;

import software.kalender.pocketcase.codes.ErrorCode;
import software.kalender.pocketcase.database.AppDatabase;
import software.kalender.pocketcase.models.CaseModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "pocket-case-csgo")
                .allowMainThreadQueries()
                .build();

//        room.caseDAO.get()

        CaseModel caseModel1 = new CaseModel();
        caseModel1.name = "test 1";
        db.caseDao().insert(caseModel1);
        // room.caseDAO.insert(caseModel1);

        CaseModel caseModel2 = new CaseModel();
        caseModel2.name = "test 2";
        db.caseDao().insert(caseModel2);
        // room.caseDAO.insert(caseModel1);
        List<CaseModel> caseModelList = db.caseDao().list();
        Log.e("aaaa", caseModelList.size() + "--");

        final HorizontalScrollView scrollView = findViewById(R.id.svscroll);
        final LinearLayout linearLayout = findViewById(R.id.test);
        scrollView.setSmoothScrollingEnabled(true);

        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < 36; i++) {
            linearLayout.addView(inflater.inflate(R.layout.case_opening_item, null, false));
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("asda", "111");

                scrollView.setScrollX(0);
                ObjectAnimator a = ObjectAnimator.ofInt(scrollView, "scrollX", 5500).setDuration(5000);

                a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        if (valueAnimator.getAnimatedFraction() > 0.5f) {
                            if (valueAnimator.getDuration() == 5000) {
                                valueAnimator.setDuration(5500);
                            } else if (valueAnimator.getAnimatedFraction() < 0.55f && valueAnimator.getDuration() == 5500) {
                                valueAnimator.setDuration(6000);
                            } else if (valueAnimator.getAnimatedFraction() < 0.6f && valueAnimator.getDuration() == 6000) {
                                valueAnimator.setDuration(7000);
                            }else if (valueAnimator.getAnimatedFraction() < 0.6f && valueAnimator.getDuration() == 6000) {
                                valueAnimator.setDuration(8000);
                            } else if (valueAnimator.getAnimatedFraction() < 0.7f && valueAnimator.getDuration() == 8000) {
                                valueAnimator.setDuration(9000);
                            } else if (valueAnimator.getAnimatedFraction() < 0.9f && valueAnimator.getDuration() == 9000) {
                                valueAnimator.setDuration(10000);
                            } else if (valueAnimator.getDuration() == 10000) {
                                valueAnimator.setDuration(12000); //TODO UNIQSES +1 SN OLABİLİR
                            }
                            Log.e("FRAC", String.valueOf(valueAnimator.getAnimatedFraction()));
                            Log.e("VALUE", valueAnimator.getAnimatedValue().toString());
                            Log.e("DURA", String.valueOf(valueAnimator.getDuration()));
                        }
                    }
                });


                a.start();
            }
        });

        for (CaseModel caseModel : caseModelList) {
            Log.e("aaa", caseModel.name);
        }

        // Log.e("ada", room.caseDAO.get(1).name);
    }

}


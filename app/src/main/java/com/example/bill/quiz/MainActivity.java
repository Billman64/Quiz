package com.example.bill.quiz;

/* Super Mario Bros. quiz app
demo by: Bill Lugo for Udacity

Mario and anything referenced to the Super Mario Bros. video games are part of intellectual property by Nintendo (Nintendo Co. Ltd. and Nintendo of America Inc.).
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int MAXSCORE = 4;

    //TODO: leftPadding on checkBoxes and radioButtons
    //TODO: mark wrong answers
    //TODO: shadow effect on the bottom edge of header textView
    //TODO: engineer to be flexible to handle any number of questions, probably loaded from a .txt file or sqlLite databae
    //TODO: show questions in random order
    //TODO: randomize order of choices

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et = (EditText) findViewById(R.id.choice3);
        et.clearFocus();
    }

    public void checkAnswers(View view){

        int score = 0;

        // check question 1
        CheckBox cb1a = (CheckBox) findViewById(R.id.choice1a);
        CheckBox cb1b = (CheckBox) findViewById(R.id.choice1b);
        CheckBox cb1c = (CheckBox) findViewById(R.id.choice1c);
        CheckBox cb1d = (CheckBox) findViewById(R.id.choice1d);

        if(cb1a.isChecked()==true && cb1b.isChecked()==true && cb1c.isChecked()==false && cb1d.isChecked()==false) {
            score+=1;
        }

        // check question 2
        RadioGroup rg = (RadioGroup) findViewById(R.id.choices2);
        RadioButton rbCorrect = (RadioButton) findViewById(R.id.choice2b);
        if(rg.getCheckedRadioButtonId() == rbCorrect.getId()) score++;

        // check question 3
        EditText et = (EditText) findViewById(R.id.choice3);
        if(et.getText().toString().toLowerCase().equals("jump man")) score++;
            else {
                switch (et.getText().toString().toLowerCase()){
                    case "jumpman":
                    case "jump-man":
                        score++;
                        break;
                    default:
                        //TODO: highlight wrong answer, probably with red textColor
                        break;
                }
        }

        // check question 4
        rg = (RadioGroup) findViewById(R.id.choices4);
        rbCorrect = (RadioButton) findViewById(R.id.choice4d);
        if(rg.getCheckedRadioButtonId() == rbCorrect.getId()) score++;


        // prepare to display results
        // custom message based on score
        String msg = "";

        switch (score) {
            case MAXSCORE:
                msg = getString(R.string.perfect);
                break;
            case 3:
                msg = getString(R.string.missedOne);
                break;
            case 2:
                msg = getString(R.string.noneRight);
                break;
            default:
                msg = getString(R.string.badScore);
                break;
        }

        // calculate score as a percent
        float scorePercent = score/(float) MAXSCORE * 100;
        Log.d("quiz app", "scorePercent="+ scorePercent + " score=" + score);
        msg+= "\n" + getString(R.string.yourScore) + " " +  String.format("%.0f",scorePercent) + "%";

        // display output
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}

package com.intelligentmobiledesign.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**Author: Maxine Hartnett
 * Countdown timer to graduation date
 *
 * TODO:
 * 1. Find difference between dates.
 * 2. Fix formatting
 * 2.1. find a way to update real time (call every second?)
 * 3. Add a photo
 * 4. Add settings
 * a. change grad date
 * b. change image
 * c. change font settings, etc (NOT IMPORTANT)
 * 5. Add in a notification for when you reach that day
 */
public class MyActivity extends Activity {
    private static final String TAG = MyActivity.class.getSimpleName();
    public final static String EXTRA_MESSAGE = "com.mobiledatadesign.firstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(R.string.title);

        TextView yearTitle=(TextView) findViewById(R.id.year_title);
        yearTitle.setText(R.string.year_title);
        TextView weekTitle=(TextView) findViewById(R.id.week_title);
        weekTitle.setText(R.string.week_title);
        TextView dayTitle=(TextView) findViewById(R.id.day_title);
        dayTitle.setText(R.string.day_title);

        setTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user hits the send button
    public void sendMessage(View view){
        Intent intent =new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message=editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
*/
    public void setTimer(){ //TODO: Figure out why I'm getting the wrong number of days
        GregorianCalendar gradCal= new GregorianCalendar(2016, 1, 23);
        GregorianCalendar currCal= (GregorianCalendar) Calendar.getInstance();

        Date currDate= new Date(); //the current day
        Date gradDate = gradCal.getTime();

        long currTime=currCal.getTimeInMillis();
        long gradTime=gradCal.getTimeInMillis();

        Log.d(TAG, "CurrTime+ " + currTime + " gradTime " + gradTime);
        int days=(int)((gradTime-currTime) / DateUtils.DAY_IN_MILLIS);

        int currYear=currCal.get(Calendar.YEAR);
        int gradYear=gradCal.get(Calendar.YEAR);
        int yearDiff=gradYear-currYear;

        for (int i = currYear; i < gradYear; i++) {

            if (currCal.isLeapYear(i)) {
                if(days>=366)
                    days -= 366;
                else{
                    yearDiff--;
                }
            }
           else {
                if (days >= 365) {
                    days -= 365;
                } else {
                    yearDiff--;
                }
            }
        }


        /*for(int i=0; i<yearDiff; i++){
            if(remainingDays==365){
                remainingDays=0;
                yearDiff++;
            }
            else{
                remainingDays++;
            }
        }
        */

        int weekDiff=days/7;
        //days=days%7;

        String yD=yearDiff+"";
        TextView y=(TextView) findViewById(R.id.year_value);
        y.setText(yD);

        String wD=weekDiff+"";
        TextView w=(TextView) findViewById(R.id.week_value);
        w.setText(wD);

        String dD=days+"";
        TextView d=(TextView) findViewById(R.id.day_value);
        d.setText(dD);






        /*int currYear=currCal.get(Calendar.YEAR);
        int gradYear=gradCal.get(Calendar.YEAR);

        int currMonth=currCal.get(Calendar.MONTH);
        int gradMonth=gradCal.get(Calendar.MONTH);

        int currDay=currCal.get(Calendar.DAY_OF_MONTH);
        int gradDay=gradCal.get(Calendar.DAY_OF_MONTH);


        String yearDiff=(gradYear-currYear)+"";

        String monthDiff=((12-currMonth)-gradMonth)+"";

        TextView m= (TextView) findViewById(R.id.month_value);
        m.setText("C "+currMonth+" G "+gradMonth+ " D "+monthDiff);


        Date currDate= new Date(); //the current day
        Date gradDate = gradCal.getTime();*/


    }
}

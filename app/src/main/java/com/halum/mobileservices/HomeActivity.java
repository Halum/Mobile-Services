package com.halum.mobileservices;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Debug;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.halum.mobileservices.Constants.*;
import static com.halum.mobileservices.DataManager.*;

public class HomeActivity extends ActionBarActivity implements ActionBar.TabListener {

    private TabPagerAdapter adapter;
    private ViewPager pager;
    private EditText fieldForPhoneNumber;

    private static final int CONTACT_PICKER_RESULT_CODE = 3;
    private static final int SETTINGS_RETURNED_RESULT_CODE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        this.Init();
    }

    private void Init(){
        generateDatabase();
        generateActvieOperatorsList(this);
        this.createTabs();
        new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                registerAllButtonsForContextMenu();
            }
        }.start();
        if(isAppRunningFirstTime(this)){
            startActivity(new Intent(this, Help.class));
        }
        firstTimeRunOver(this);
    }

    private void registerAllButtonsForContextMenu(){
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        this.findButtons(viewGroup);
    }

    private void findButtons(ViewGroup viewGroup){
        for(int i=0, n=viewGroup.getChildCount(); i<n; ++i){
            View child = viewGroup.getChildAt(i);
            if(child instanceof ViewGroup){
                this.findButtons((ViewGroup) child);
            }else if(child  instanceof Button){
                registerForContextMenu(child);
            }
        }
    }

    public void getNumberFromContact(View button){
        String tag = button.getTag().toString();
        this.fieldForPhoneNumber = this.getTextField(tag);
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, this.CONTACT_PICKER_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case CONTACT_PICKER_RESULT_CODE:
                if(resultCode == Activity.RESULT_OK){
                    Cursor cur = null;
                    String phoneNumber = "";
                    List<String> allNumbers = new ArrayList<String>();
                    int phoneIdx = 0;
                    try {
                        Uri contactData = data.getData();
                        String id = contactData.getLastPathSegment();
                        cur = getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                                new String[]{id}, null);
                        phoneIdx = cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                        if(cur.moveToFirst()){
                            while(!cur.isAfterLast()){
                                phoneNumber = cur.getString(phoneIdx);
                                allNumbers.add(phoneNumber);
                                cur.moveToNext();
                            }
                        }else{
                            alert("Contact has no number");
                        }
                    }catch (Exception ex){
                        alert("Can't get contact number");
                    }finally {
                        if(cur!=null){
                            cur.close();
                        }

                        final CharSequence[] items = allNumbers.toArray(new String[allNumbers.size()]);
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Choose a number:");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                String selectedNumber = items[item].toString();
                                selectedNumber = selectedNumber.replace("-", "");
                                fieldForPhoneNumber.setText(selectedNumber);
                            }
                        });
                        AlertDialog dialog = builder.create();
                        if(allNumbers.size() > 1){
                            dialog.show();
                        }else{
                            String selectedNumber = phoneNumber.toString();
                            selectedNumber = selectedNumber.replace("-", "");
                            fieldForPhoneNumber.setText(selectedNumber);
                        }

                        if (phoneNumber.length() == 0) {
                            alert("No number found");
                        }
                    }
                }
                break;
            case SETTINGS_RETURNED_RESULT_CODE:
                if(resultCode == Activity.RESULT_OK){
                    this.restartActivity();
                }
                break;
        }
    }

    private void restartActivity(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void getService(View button){
        try {
            String tag = button.getTag().toString();
            String USSD = this.makeUSSDString(tag);
            if (this.isDependentOnTextField(tag)) {
                String number = this.getTextFieldData(tag);
                USSD += number;
            }
            this.requestServiceCall(USSD);
        }catch (Exception ex){
            alert("Service not available");
        }
    }

    private void requestServiceCall(String USSD){
        USSD += Uri.encode("#");
        if(USSD.contains("null")) alert("no data");
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(USSD)));
    }

    private String makeUSSDString(String tag){
        String USSD = "tel:";
        USSD += DATABASE.get(tag);
        return USSD;
    }

    private String getTextFieldData(String tag){
        EditText textField = this.getTextField(tag);
        String number = textField.getText().toString();

        return number;
    }

    private EditText getTextField(String tag){
        int textFieldId = getResources().getIdentifier(tag, "id", getPackageName());
        EditText textField = (EditText) findViewById(textFieldId);

        return textField;
    }

    private void showDialog(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    private void alert(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isDependentOnTextField(String tag){
        if(tag.contains("recharge") || tag.contains("callback"))
            return true;
        return false;
    }

    private void createTabs(){
        // Set up the action bar
        invalidateOptionsMenu();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adapter = new TabPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the tabs adapter.
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        actionBar.removeAllTabs();
        for (int i = 0; i < adapter.getCount(); i++) {
            actionBar.addTab(
                             actionBar.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu_home_settings:
                startActivityForResult(new Intent(this, ServiceSettings.class),
                        SETTINGS_RETURNED_RESULT_CODE);
                break;
            case R.id.menu_home_help:
                startActivity(new Intent(this, Help.class));
                break;
            case R.id.menu_home_about:
                break;
            case R.id.menu_home_update:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String tag = v.getTag().toString();
        String title = tag.split("_")[0];
        String msg = "Dial:    " + DATABASE.get(tag);
        if(this.isDependentOnTextField(tag)){
            msg += "<number>";
        }
        msg += "#";
        this.showDialog(title, msg);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
}

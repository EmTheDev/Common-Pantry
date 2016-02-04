package com.example.frankkoutoulas.splashscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by miles on 12/7/15.
 */
public class PreferencesFragment extends Fragment {

    Context context;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    String appendingString = "";
    String dairy = "";
    String egg = "";
    String gluten = "";
    String peanut = "";
    String seaFood = "";
    String sesame = "";
    String soy = "";
    String treeNut = "";
    String wheat = "";

    private static ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();


    View mView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        context = getActivity();

        mView = inflater.inflate(R.layout.fragment_preferences, container, false);

        final Button submitButton = (Button) mView.findViewById(R.id.button10);
        checkBoxList = new ArrayList<CheckBox>();

        final CheckBox dairyBox = (CheckBox) mView.findViewById(R.id.dairy);
        final CheckBox eggBox = (CheckBox) mView.findViewById(R.id.egg);
        final CheckBox glutenBox = (CheckBox) mView.findViewById(R.id.gluten);
        final CheckBox peanutBox = (CheckBox) mView.findViewById(R.id.peanut);
        final CheckBox seaFoodBox = (CheckBox) mView.findViewById(R.id.seaFood);
        final CheckBox sesameBox = (CheckBox) mView.findViewById(R.id.sesame);
        final CheckBox soyBox = (CheckBox) mView.findViewById(R.id.soy);
        final CheckBox treeNutBox = (CheckBox) mView.findViewById(R.id.treeNut);
        final CheckBox wheatBox = (CheckBox) mView.findViewById(R.id.wheat);

        dairyBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });eggBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });glutenBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });peanutBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });seaFoodBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });sesameBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });soyBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });treeNutBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });wheatBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        checkBoxList.add(dairyBox);
        checkBoxList.add(eggBox);
        checkBoxList.add(glutenBox);
        checkBoxList.add(peanutBox);
        checkBoxList.add(seaFoodBox);
        checkBoxList.add(sesameBox);
        checkBoxList.add(soyBox);
        checkBoxList.add(treeNutBox);
        checkBoxList.add(wheatBox);


        readPrefs(checkBoxList);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dairyBox.isChecked())
                    dairy = "true" + '\n';
                else
                    dairy = "false" + '\n';


                if (eggBox.isChecked())
                    egg = "true" + '\n';
                else
                    egg = "false" + '\n';


                if (glutenBox.isChecked())
                    gluten = "true" + '\n';
                else
                    gluten = "false" + '\n';


                if (peanutBox.isChecked())
                    peanut = "true" + '\n';
                else
                    peanut = "false" + '\n';


                if (seaFoodBox.isChecked())
                    seaFood = "true" + '\n';
                else
                    seaFood = "false" + '\n';


                if (sesameBox.isChecked())
                    sesame = "true" + '\n';
                else
                    sesame = "false" + '\n';


                if (soyBox.isChecked())
                    soy = "true" + '\n';
                else
                    soy = "false" + '\n';


                if (treeNutBox.isChecked())
                    treeNut = "true" + '\n';
                else
                    treeNut = "false" + '\n';


                if (wheatBox.isChecked())
                    wheat = "true" + '\n';
                else
                    wheat = "false" + '\n';
                //String finalAppendingString = null;
                String appendString = dairy + egg + gluten + peanut + seaFood + sesame + soy + treeNut + wheat;
                String fileContents = createTextFile(appendString);

                Toast.makeText(getActivity().getApplicationContext(),"Preferences saved.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return mView;
    }

    public void onCheckboxClicked(View view) {



    }

    /* This method fills an array list of checkboxes of which the user has chosen
    * to fill in or not fill in depending on their preference/allergies.  It also
    * reads and writes to a file which is saved in case the user exits the application.
    * Each checkbox of the allergies list is saved as a boolean onto the textfile and read from
    * when the preference page is displayed again.
    *
            * @param checkBoxList A checkbox that is has the contents of the users chosen allergies.  It
    *                     is used to copy onto a text file called prefsFile for later usage.
            * @exception  IOException when there exists an error in reading or writing to the file.
    */
    public void readPrefs(ArrayList<CheckBox> checkBoxList) {
        try {
            System.out.println("Inside the try/catch block");
            String filePath = context.getFilesDir().getPath().toString() + "/preferences";
            File prefsFile = new File(filePath);
            if (!prefsFile.exists()) {
                return;
            }

            CheckBox dairyBox = checkBoxList.get(0);
            CheckBox eggBox = checkBoxList.get(1);
            CheckBox glutenBox = checkBoxList.get(2);
            CheckBox peanutBox = checkBoxList.get(3);
            CheckBox seaFoodBox = checkBoxList.get(4);
            CheckBox sesameBox = checkBoxList.get(5);
            CheckBox soyBox = checkBoxList.get(6);
            CheckBox treeNutBox = checkBoxList.get(7);
            CheckBox wheatBox = checkBoxList.get(8);

            FileReader fileReader = new FileReader(prefsFile);
            bufferedReader = new BufferedReader(fileReader);

            if ((bufferedReader.readLine()) != null) {
                dairy = bufferedReader.readLine();
                egg = bufferedReader.readLine();
                gluten = bufferedReader.readLine();
                peanut = bufferedReader.readLine();
                seaFood = bufferedReader.readLine();
                sesame = bufferedReader.readLine();
                soy = bufferedReader.readLine();
                treeNut = bufferedReader.readLine();
                wheat = bufferedReader.readLine();
            }

            System.out.println(dairy);
            System.out.println(egg);
            if (dairy.equals("true"))
                dairyBox.setChecked(true);
            else
                dairyBox.setChecked(false);

            if (egg.equals("true"))
                eggBox.setChecked(true);
            else
                eggBox.setChecked(false);

            if (gluten.equals("true"))
                glutenBox.setChecked(true);
            else
                glutenBox.setChecked(false);

            if (peanut.equals("true"))
                peanutBox.setChecked(true);
            else
                peanutBox.setChecked(false);

            if (seaFood.equals("true"))
                seaFoodBox.setChecked(true);
            else
                seaFoodBox.setChecked(false);

            if (sesame.equals("true"))
                sesameBox.setChecked(true);
            else
                sesameBox.setChecked(false);

            if (soy.equals("true"))
                soyBox.setChecked(true);
            else
                soyBox.setChecked(false);

            if (treeNut.equals("true"))
                treeNutBox.setChecked(true);
            else
                treeNutBox.setChecked(false);

            if (wheat.equals("true"))
                wheatBox.setChecked(true);
            else
                wheatBox.setChecked(false);

            //d.setText(appendString);
        } catch (IOException e) {
            System.out.println("There was an exception in reading or writing the file:");
            e.printStackTrace();
        }

    }

    /**
     * Creates, reads and writes the contents of the checkboxes to a file.
     * @param allowedAllergies
     * @return finalAppendingString the final appending string which is used for the URL.
     */
    public String createTextFile(String allowedAllergies) {
        String finalAppendingString = "";
        try {
            System.out.println("Inside the try/catch block");
            String filePath = context.getFilesDir().getPath().toString() + "/preferences";
            File prefsFile = new File(filePath);
            if (!prefsFile.exists()) {
                System.out.println("The prefs file doesn't exist.");
                prefsFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(prefsFile.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write('\n' + allowedAllergies);
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("There was an exception in reading or writing the file:");
            e.printStackTrace();
        }
        System.out.println(finalAppendingString);
        return finalAppendingString;
    }

    /**
     * This method is used to create an Array list of booleans and see
     * whether or not the boxes for the checkBoxList of allergies are checked or not
     * each and every checkbox is applied to the new array which is returned when
     * this method is called.
     *
     * @return prefsList this returns the Array List of booleans which may be used
     * to see and apply which allergies are selected
     */
    public static ArrayList<Boolean> getPrefs() {
        ArrayList<Boolean> prefsList = new ArrayList<Boolean>();
        Boolean checked;
        for (int i = 0; i < checkBoxList.size(); i++) {
            checked = checkBoxList.get(i).isChecked();
            prefsList.add(checked);
        }
        return prefsList;
    }
}

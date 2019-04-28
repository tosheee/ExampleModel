package com.examplemodel.examplemodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ArrayList<Words> wordsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DictionaryDB dictionaryDB = new DictionaryDB(this);
        List<Words> wordsList = dictionaryDB.getAllWords();

        //dictionaryDB.addWords(new Words(18888888,"testadfadsfas", "testadsfasd"));

        for (Words word : wordsList) {
            String log = "Id: " + word.getId() + " ,EN word: " + word.getEn_word() + " ,BG word: " + word.getBg_word();
            // Writing Contacts to log
          Log.i("TEST: ", "RESULT " + log);
        }
    }
}

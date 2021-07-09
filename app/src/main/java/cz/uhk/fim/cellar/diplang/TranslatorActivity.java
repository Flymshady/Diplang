package cz.uhk.fim.cellar.diplang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.TranslateRemoteModel;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;

public class TranslatorActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private TextInputEditText sourceEditText;
    private ImageView mic, imageTTS, imageTranslatedTTS;
    private MaterialButton translateButton;
    private TextView translatedTextView;
    private Button buttonDeleteModels, buttonBack;
    private TextToSpeech mTTS;
    String[] fromLanguages = {"Z", "Angličtina", "Čeština"};
    String[] toLanguages = {"Do", "Angličtina", "Čeština"};

    private static final int REQUEST_PERMISSION_CODE = 1;
    String languageCode, fromLanguageCode, toLanguageCode = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        fromSpinner =(Spinner) findViewById(R.id.idFromSpinner);
        toSpinner = (Spinner) findViewById(R.id.idToSpinner);
        sourceEditText = (TextInputEditText) findViewById(R.id.idEditSource);
        mic = (ImageView) findViewById(R.id.idMic);
        translateButton = (MaterialButton) findViewById(R.id.idBtnTranslate);
        translatedTextView = (TextView) findViewById(R.id.idTranslatedText);
        buttonDeleteModels = (Button) findViewById(R.id.buttonDeleteModels);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        imageTTS = (ImageView) findViewById(R.id.imageTTS);
        imageTranslatedTTS = (ImageView) findViewById(R.id.imageTranslatedTTS);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language not supported");
                    }else {
                        imageTTS.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        

        /**
         * Select the language from which to translate
         */
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                fromLanguageCode = getLanguageCode(fromLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter fromAdapter = new ArrayAdapter(this, R.layout.spinner_item, fromLanguages);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);

        /**
         * Select the language to be translated into
         */
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                toLanguageCode = getLanguageCode(toLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ArrayAdapter toAdapter = new ArrayAdapter(this, R.layout.spinner_item, toLanguages);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translatedTextView.setText("");
                if(sourceEditText.getText().toString().isEmpty()){
                    Toast.makeText(TranslatorActivity.this, "Prosím zadejte text k přeložení.", Toast.LENGTH_LONG).show();
                }else if(fromLanguageCode==""){
                    Toast.makeText(TranslatorActivity.this, "Prosím zadejte zdrojový jazyk.", Toast.LENGTH_LONG).show();
                }else if(toLanguageCode==""){
                    Toast.makeText(TranslatorActivity.this, "Prosím zadejte cílový jazyk.", Toast.LENGTH_LONG).show();
                }else{
                    translateText(fromLanguageCode, toLanguageCode, sourceEditText.getText().toString());
                }
            }
        });

        imageTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTTS.isSpeaking()){
                    mTTS.stop();
                }else{
                    speak(1);
                }
            }
        });

        imageTranslatedTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTTS.isSpeaking()){
                    mTTS.stop();
                }else{
                    speak(2);
                }
            }
        });

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Mluvte pro převod řeči na text");
                try{
                    startActivityForResult(i, REQUEST_PERMISSION_CODE);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(TranslatorActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonDeleteModels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.app_name);
                builder.setMessage("Chcete z paměti odstranit modely pro anglický a český překlad?");
                builder.setIcon(R.drawable.ic_baseline_warning_24);
                builder.setPositiveButton("Ano", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                        RemoteModelManager modelManager = RemoteModelManager.getInstance();
                        // Delete the English model if it's on the device.
                        TranslateRemoteModel englishModel = new TranslateRemoteModel.Builder(TranslateLanguage.ENGLISH).build();
                        modelManager.deleteDownloadedModel(englishModel).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(TranslatorActivity.this, "Model anglického jazyka se nepodařilo odstranit.", Toast.LENGTH_LONG).show();
                                return;
                            }
                        });

                        TranslateRemoteModel czechModel = new TranslateRemoteModel.Builder(TranslateLanguage.CZECH).build();
                        modelManager.deleteDownloadedModel(czechModel).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(TranslatorActivity.this, "Model českého jazyka se nepodařilo odstranit.", Toast.LENGTH_LONG).show();
                                return;
                            }
                        });
                        Toast.makeText(TranslatorActivity.this, "Modely byly odstraněny.", Toast.LENGTH_LONG).show();

                    }
                });
                builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        /**
         * Share String (text) for the translation (from English to Czech only)
         */
        if(Intent.ACTION_SEND.equals(action) && type != null){
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if(sharedText !=null){
                    sourceEditText.setText(sharedText);
                    fromSpinner.setSelection(1);
                    toSpinner.setSelection(2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        translateButton.performClick();
                    }
                }, 1000);

            }
        }

        /**
         * Better scrollbar visibility inside the editable text field
         */
        sourceEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (view.getId() == R.id.idEditSource) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (motionEvent.getAction()&MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });

    }

    private void speak(int type) {
        String text="";
        if(type==1){
            text = sourceEditText.getText().toString();
        }
        else if(type==2){
            text = translatedTextView.getText().toString();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTTS.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
        } else {
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    protected void onDestroy() {
        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_PERMISSION_CODE){
            if(resultCode==RESULT_OK && data!=null){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                sourceEditText.setText(result.get(0));
            }
        }
    }

    private void translateText(String fromLanguageCode, String toLanguageCode, String source){
        translatedTextView.setText("Stahuji data...");
        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(fromLanguageCode)
                .setTargetLanguage(toLanguageCode)
                .build();
        Translator translator = Translation.getClient(options);

        /**
         * Ensure that the close() method is called when the Translator object will no longer be used.
         */
        getLifecycle().addObserver(translator);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                translatedTextView.setText("Překládám...");
                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        translatedTextView.setText(s);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(TranslatorActivity.this, "Došlo k chybě: "+e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(TranslatorActivity.this, "Data nebylo možné stáhnout: "+e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public String getLanguageCode(String language) {
        String languageCode = "";
        switch (language){
            case "Angličtina":
                languageCode = TranslateLanguage.ENGLISH;
                break;
            case "Čeština":
                languageCode = TranslateLanguage.CZECH;
                break;
            default:
                languageCode = "";
        }
        return languageCode;
    }


}
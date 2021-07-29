package cz.uhk.fim.cellar.diplang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.TranslateRemoteModel;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import cz.uhk.fim.cellar.diplang.classes.Phrase;

/**
 * @author Štěpán Cellar - FIM UHK
 * @since 2021
 * Aktivita pro překladač
 */
public class TranslatorActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private TextInputEditText sourceEditText;
    private ImageView mic, imageTTS, imageTranslatedTTS;
    private MaterialButton translateButton;
    private TextView translatedTextView;
    private Button buttonDeleteModels, buttonBack, btnSavePhrase;
    private TextToSpeech mTTS;
    private String[] fromLanguages = {"Z", "Angličtina", "Čeština"};
    private String[] toLanguages = {"Do", "Angličtina", "Čeština"};
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static final int REQUEST_PERMISSION_CODE = 1;
    private String languageCode, fromLanguageCode, toLanguageCode = "";

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
        btnSavePhrase = (Button) findViewById(R.id.btnSavePhrase);

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
         * Vybrání jazyka ze kterého má být překlad
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
         * Vabrání jazyka do kterého má být překlad
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

        /**
         * Aktivate mikrofonu pro zaznamenání hlasu pro převod na text
         */
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

        /**
         * Button pro odstranění modelů pro překlad z uložiště zařízení
         */
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
         * Sdílení textu pro překlad z angličtiny do češtiny
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
         * Lepší viditelnost posuvníku v editovaném textovém poli
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

        btnSavePhrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePhrase();
            }
        });

    }

    /**
     * Uloží anglickou frázi a její překlad do databáze
     * a ta tak bude použitelná prostřednictvím PhrasesActivity
     */
    private void savePhrase() {
        String englishPhrase="";
        String czechPhrase="";
        if(toSpinner.getSelectedItemPosition()==1 //english
                && fromSpinner.getSelectedItemPosition()==2 //czech
                && sourceEditText.getText()!=null //2
                && translatedTextView.getText()!=null){
            czechPhrase = sourceEditText.getText().toString();
            englishPhrase = translatedTextView.getText().toString();
        }else if(toSpinner.getSelectedItemPosition()==2
                && fromSpinner.getSelectedItemPosition()==1
                && sourceEditText.getText()!=null
                && translatedTextView.getText()!=null){
            englishPhrase = sourceEditText.getText().toString();
            czechPhrase = translatedTextView.getText().toString();
        }else {
            Toast.makeText(TranslatorActivity.this, "Vložte frázi.", Toast.LENGTH_LONG).show();
        }
        String created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        if(englishPhrase.equals("") || czechPhrase.equals("")){
            return;
        }
        Phrase phrase = new Phrase(englishPhrase, czechPhrase, created);

        FirebaseDatabase.getInstance().getReference("UserPhrases")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child(created)
                .setValue(phrase).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(TranslatorActivity.this, "Fráze nebyla uložena.", Toast.LENGTH_LONG).show();
                return;
            }
        });
        Toast.makeText(TranslatorActivity.this, "Fráze byla uložena.", Toast.LENGTH_LONG).show();
    }

    /**
     * Text to speech metoda
     * @param type zdroj textu, který má být přečten, kde 1 = editovaný text; 2 = přeložený text
     */
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

    /**
     * Metoda pro zpracování mluveného slova do textu
     * @param requestCode
     * @param resultCode
     * @param data
     */
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

    /**
     * Ověří dostupnost modelu pro překlad a provede překlad
     * @param fromLanguageCode kód jazyka ze kterého má být překlad
     * @param toLanguageCode kód jazyka do kterého má být překlad
     * @param source
     */
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

    /**
     * Určení kódu jazyku pro překlad
     * @param language jazyk
     * @return lanuageCode kód zvoleného jazyku
     */
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

    /** Skrytí klávesnice při "kliknutí" mimo editovatelné textové pole **/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


}
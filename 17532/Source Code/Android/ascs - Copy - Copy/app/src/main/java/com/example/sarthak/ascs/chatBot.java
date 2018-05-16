package com.example.sarthak.ascs;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class chatBot extends AppCompatActivity implements AIListener {


    AIService aiService;
    Boolean flagFab = true;
    EditText editText;
    RelativeLayout addBtn;
    ListView listView;
    List<ChatMessage> list;
    msgAdapter adapter;
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        listView=findViewById(R.id.chat_list);
        list=new ArrayList<>();
        adapter=new msgAdapter(getApplicationContext(),list);
        listView.setAdapter(adapter);
        editText = findViewById(R.id.editText);
        addBtn = findViewById(R.id.addBtn1);
        recordAudio();


    }
    private void recordAudio()
    {
        final AIConfiguration config = new AIConfiguration("bcd32d54e17344f49faea07619dab062",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiService = AIService.getService(getApplicationContext(), config);
        aiService.setListener(this);
        final AIDataService aiDataService = new AIDataService(getApplicationContext(),config);

        final AIRequest aiRequest = new AIRequest();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = editText.getText().toString().trim();

                if (!message.equals("")) {

                    ChatMessage chatMessage = new ChatMessage(message, "user");
                    list.add(chatMessage);
                    adapter.notifyDataSetChanged();

                    aiRequest.setQuery(message);
                    new AsyncTask<AIRequest,Void,AIResponse>(){

                        @Override
                        protected AIResponse doInBackground(AIRequest... aiRequests) {
                            final AIRequest request = aiRequests[0];
                            try {
                                final AIResponse response = aiDataService.request(aiRequest);
                                return response;
                            } catch (AIServiceException e) {
                            }
                            return null;
                        }
                        @Override
                        protected void onPostExecute(AIResponse response) {
                            if (response != null) {

                                Result result = response.getResult();
                                String reply = result.getFulfillment().getSpeech();
                                ChatMessage chatMessage = new ChatMessage(reply, "bot");
                                list.add(chatMessage);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }.execute(aiRequest);
                }
                else {
                    aiService.startListening();
                }

                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ImageView fab_img = (ImageView)findViewById(R.id.fab_img);
                Bitmap img = BitmapFactory.decodeResource(getResources(),R.drawable.ic_send_white_24dp);
                Bitmap img1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_mic_white_24dp);


                if (s.toString().trim().length()!=0 && flagFab){
                    ImageViewAnimatedChange(chatBot.this,fab_img,img);
                    flagFab=false;

                }
                else if (s.toString().trim().length()==0){
                    ImageViewAnimatedChange(chatBot.this,fab_img,img1);

                    flagFab=true;

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });}
        public void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.zoom_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.zoom_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageBitmap(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }

        //final AIDataService aiDataService = new AIDataService(config);

        //final AIRequest aiRequest = new AIRequest();

    @Override
    public void onResult(AIResponse response) {
        Result result = response.getResult();

        String message = result.getResolvedQuery();
        ChatMessage chatMessage0 = new ChatMessage(message, "user");
        list.add(chatMessage0);
        adapter.notifyDataSetChanged();


        String reply = result.getFulfillment().getSpeech();
        ChatMessage chatMessage = new ChatMessage(reply, "bot");
        list.add(chatMessage);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

}

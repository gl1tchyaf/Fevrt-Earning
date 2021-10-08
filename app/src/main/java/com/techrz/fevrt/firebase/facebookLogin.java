package com.techrz.fevrt.firebase;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;



import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class facebookLogin extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ImageView imageView;
    private TextView textView ;
    private Button home;
    static String name;
    static String id;
    static boolean loginstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);

        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();

        loginButton.setPermissions(Arrays.asList("email"));

        home=findViewById(R.id.gotohome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginstatus == true) {
                    Intent intent = new Intent(facebookLogin.this, home.class);
                    startActivity(intent);
                }
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Demo","Success");
            }

            @Override
            public void onCancel() {
                Log.d("Demo","Login canceled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Demo","Login error");
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        GraphRequest graphRequest =  GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("Demo",object.toString());
                try {
                    name= object.getString("name");
                    id = object.getString("id");
                    textView = findViewById(R.id.tv_name);
                    imageView = findViewById(R.id.iv_profilePic);
                    textView.setText(name);
                    loginstatus=true;
                    Picasso.get().load("https://graph.facebook.com/"+id+"/picture?type=large").into(imageView);
                    database d = new database();
                    d.saveData();
                    Intent intent = new Intent(facebookLogin.this,home.class);
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("field","gender,name,id,first_name,last_name");

        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                LoginManager.getInstance().logOut();
                textView= findViewById(R.id.tv_name);
                imageView = findViewById(R.id.iv_profilePic);
                textView.setText("");
                imageView.setImageResource(0);
                loginstatus=false;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.startTracking();
    }
}
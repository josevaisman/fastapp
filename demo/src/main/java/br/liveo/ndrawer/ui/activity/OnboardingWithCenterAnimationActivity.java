package br.liveo.ndrawer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONException;

import br.liveo.ndrawer.R;

import com.facebook.FacebookSdk;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.LoginManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

public class OnboardingWithCenterAnimationActivity extends AppCompatActivity {
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;

    private boolean animationStarted = false;

	Button bsaltatlogin;
	Button bfacebooklogin;
	private CallbackManager mCallbackManager;
    private Profile profile;
    private AccessTokenTracker tokenTracker;
	
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
		
		setTheme(R.style.AppThemeSplash);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_center);
		
		bfacebooklogin    = (Button)   findViewById(R.id.btn_choice1);
		bfacebooklogin.setOnClickListener(clickButonFacebookLogin);   
		
		bsaltatlogin    = (Button)   findViewById(R.id.btn_choice2);
		bsaltatlogin.setOnClickListener(clickButonSaltarLogin);   
		
	}
	
	private OnClickListener clickButonFacebookLogin = new OnClickListener() {

		public void onClick(View view){
			mCallbackManager = CallbackManager.Factory.create();
			
			LoginManager.getInstance().logInWithReadPermissions(OnboardingWithCenterAnimationActivity.this, 
							Arrays.asList("email", "public_profile","user_birthday"));
			
			LoginManager.getInstance().registerCallback(mCallbackManager, mFacebookCallback);
			
		}        	
	};
	
	private FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
			Log.i("Facebook","facebook login success");
			
			GraphRequest request = GraphRequest.newMeRequest(
				loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
					@Override
					public void onCompleted(JSONObject json, GraphResponse response) {
						if (response.getError() != null) {
							// handle error
							Log.e("Facebook","ERROR");
							Log.e("Facebook",response.getError().toString());
						} else {
							Log.i("Facebook","Success");
							String jsonresult = json.toString();
							Log.i("Facebook", jsonresult);
							Intent i=new Intent(getApplicationContext(), MainActivity.class);
							i.putExtra("data",jsonresult);
							startActivity(i); 
							finish();
							
						}
					}

				});
			
			Bundle parameters = new Bundle();
			parameters.putString("fields", "id,name,first_name,last_name,age_range,link,gender,locale,picture,timezone,updated_time,verified,birthday");
			request.setParameters(parameters);
			request.executeAsync();
			
		}
		
        @Override
        public void onCancel() {
			Log.e("Facebook","facebook login canceled");
        }

        @Override
        public void onError(FacebookException e) {
			Log.e("Facebook", "facebook login failed error");
        }
		
    };

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
		mCallbackManager.onActivityResult(requestCode, resultCode, data);
	}
	
	private OnClickListener clickButonSaltarLogin = new OnClickListener() {

		public void onClick(View view){
			Intent i=new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i); 
			finish();
		}        	
	};
	
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus || animationStarted) {
            return;
        }

        animate();

        super.onWindowFocusChanged(hasFocus);
    }

    private void animate() {
        ImageView logoImageView = (ImageView) findViewById(R.id.img_logo);
        ViewGroup container = (ViewGroup) findViewById(R.id.container);

        ViewCompat.animate(logoImageView)
            .translationY(-250)
            .setStartDelay(STARTUP_DELAY)
            .setDuration(ANIM_ITEM_DURATION).setInterpolator(
			new AccelerateDecelerateInterpolator()).start();

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (!(v instanceof Button)) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((ITEM_DELAY * i) + 800)
                        .setDuration(1000);
            } else {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(500);
            }

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
    }
}

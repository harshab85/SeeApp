package uofprojects.see;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import see.uoftprojects.seeapp.R;
import uofprojects.see.service.request.RegisterUser;
import uofprojects.see.service.response.AbstractResponse;
import uofprojects.see.util.ActivityUtil;
import uofprojects.see.util.ServiceUtil;
import uofprojects.see.util.StorageUtil;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActivityUtil.setMainActivity(this);

        loadLandingPageActivity();

        /*if(StorageUtil.getBooleanValue(StorageUtil.SharedPreferenceKeys.Registered.getKey())){
            loadLandingPageActivity();
        }

        ImageButton imageButton = (ImageButton)findViewById(R.id.register);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userIdText = (EditText)findViewById(R.id.user_id);
                String userId = userIdText.getText().toString();

                if(userId == null || userId.isEmpty()){
                    Toast.makeText(ActivityUtil.getMainActivity(), "User id is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                RegisterUser registerUser = new RegisterUser(userId);
                registerUser.run();

                AbstractResponse response = registerUser.getResponse();
                if(!response.isSuccess()){
                    Toast.makeText(ActivityUtil.getMainActivity(), response.getError(), Toast.LENGTH_SHORT).show();
                }
                else{
                    StorageUtil.setBooleanValue(StorageUtil.SharedPreferenceKeys.Registered.getKey(), true);
                    StorageUtil.setStringValue(ServiceUtil.PayloadKeys.UserId.getKey(), userId);
                    Toast.makeText(ActivityUtil.getMainActivity(), "Success!", Toast.LENGTH_SHORT).show();
                    loadLandingPageActivity();
                }
            }
        });*/

    }

    private void loadLandingPageActivity(){
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
        finish();
    }

}

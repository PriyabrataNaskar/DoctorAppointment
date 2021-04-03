package com.blogspot.priyabratanaskar.firebaselogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton signUp;
    MaterialButton logIn;
    TextInputLayout passwordTextInput;
    TextInputEditText passwordEditText;

    TextInputLayout emailTextInput;
    TextInputEditText emailEditText;

    FirebaseAuth mAuth;
    private static String TAG="com.blogspot.priyabratanaskar.firebaselogin.LoginActivity";

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("LoginActivity","OnStart");
         //Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        if(mAuth.getCurrentUser() != null){
            Log.e("LoginActivity","OnStart inside if");
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.login_sign_up_new);
        signUp.setOnClickListener(this);

        logIn = findViewById(R.id.login_sign_in);
        logIn.setOnClickListener(this);

        passwordTextInput = findViewById(R.id.login_password_text_input);
        passwordEditText = findViewById(R.id.login_password_edit_text);

        emailTextInput = findViewById(R.id.login_email_text_input);
        emailEditText = findViewById(R.id.login_email_edit_text);
    }

    public void signInWithEmail(String email,String password){
        //Invalid email will show Error
        boolean validEmail = isEmailValid(email);
        boolean validPassword = isPasswordValid(password);
        //If any thing is Invalid it will return
        if(!(validEmail && validPassword)){
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            //updateUI(null);
                            // ...
                        }

                        // ...
                    }
                });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_sign_up_new:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_sign_in:
                signInWithEmail(emailEditText.getText().toString(), passwordEditText.getText().toString());
                break;
            case R.id.login_forget_password:
                break;
        }
    }

    /**
     * Checks if password is valid or not
     *
     * @param text
     * @return boolean
     */
    private boolean isPasswordValid(@NonNull String text) {
        if (text.length() > 7 && text.length() < 14) {
            passwordTextInput.setError(null);
            return true;
        } else {
            passwordTextInput.setError(getText(R.string.dr_error_length_password));
            return false;
        }
    }

    /**
     * Checks if Email Valid or Not
     *
     * @param text
     * @return boolean
     */
    private boolean isEmailValid(@NonNull String text) {
        if (text.toString().isEmpty()) {
            //error
            emailTextInput.setError("Email Can't be Empty");
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            //success
            return true;
        } else {
            //error enter valid email
            emailTextInput.setError("Invalid Email!");
            return false;
        }
    }

}

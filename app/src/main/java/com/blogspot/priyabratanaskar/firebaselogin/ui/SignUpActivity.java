package com.blogspot.priyabratanaskar.firebaselogin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.priyabratanaskar.firebaselogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout passwordTextInput;
    TextInputEditText passwordEditText;

    TextInputLayout usernameTextInput;
    TextInputEditText usernameEditText;

    TextInputLayout emailTextInput;
    TextInputEditText emailEditText;

    MaterialButton signUpButton;

    MaterialButton alreadyHaveAccountSignIn;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        passwordTextInput = findViewById(R.id.password_text_input);
        passwordEditText = findViewById(R.id.password_edit_text);

        usernameTextInput = findViewById(R.id.username_text_input);
        usernameEditText = findViewById(R.id.username_edit_text);

        emailTextInput = findViewById(R.id.email_text_input);
        emailEditText = findViewById(R.id.email_edit_text);

        signUpButton = findViewById(R.id.sign_up);
        signUpButton.setOnClickListener(this);

        alreadyHaveAccountSignIn = findViewById(R.id.already_have_account);
        alreadyHaveAccountSignIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }
    
    //Handles click events on Views
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.already_have_account:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sign_up:
                registerUser();
                break;
        }
    }

    /**
     * Create FireBase User Account
     * @param emailId
     * @param password
     */
    private void createAccount(String emailId, String
                                password){
        mAuth.createUserWithEmailAndPassword(emailId, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            emailTextInput.setError(null);
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "Email Already Used",
                                        Toast.LENGTH_LONG).show();
                                emailTextInput.setError("Email Already in Use");
                            }
                        else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    /**
     * Register User if Password && Email && Username valid
     *
     * @return
     */
    private void registerUser() {
        boolean validEmail = isEmailValid(Objects.requireNonNull(emailEditText.getText()).toString());
        boolean validPassword = isPasswordValid(Objects.requireNonNull(passwordEditText.getText()).toString());
        boolean validUserName = isUsernameValid(Objects.requireNonNull(usernameEditText.getText()).toString());
        if (validEmail && validPassword && validUserName) {

            //create new account with valid email & password
            createAccount(emailEditText.getText().toString(),passwordEditText.getText().toString());
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

    /**
     * Check for valid username
     *
     * @param userName
     * @return
     */
    private boolean isUsernameValid(@NonNull String userName) {
        if (userName.isEmpty()) {
            usernameTextInput.setError("Username Can't be Empty");
            return false;
        } else {
            usernameTextInput.setError(null);
            return true;
        }
    }

}

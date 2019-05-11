package database;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bdbloodbank.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerificationCodeGenerator extends AppCompatActivity {

    FirebaseAuth auth;
    String phoneNumber;
    String verification_code;
    EditText inputCode;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    public VerificationCodeGenerator(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        auth = FirebaseAuth.getInstance();
        inputCode = findViewById(R.id.verificationCode);

        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }
            @Override
            public void onCodeSent(String s,PhoneAuthProvider.ForceResendingToken forceResendingToken){
                super.onCodeSent(s,forceResendingToken);
                verification_code = s;
                Toast.makeText(getApplicationContext(),"Code sent to number",Toast.LENGTH_SHORT).show();

            }
        };
    }

    public void send_sms(View v){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,60, TimeUnit.SECONDS,this,mCallback
        );
    }

    public void singWithPhone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Donor singed is successfully",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void verify(View v){
        String input_code = inputCode.getText().toString();
        if(verification_code.equals("")){
            verifyPhoneNumber(verification_code,input_code);
        }
    }

    public void verifyPhoneNumber(String verifyCode,String inputCode ){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode,inputCode);
        singWithPhone(credential);
    }
}

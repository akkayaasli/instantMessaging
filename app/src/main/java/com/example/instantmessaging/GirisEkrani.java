package com.example.instantmessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GirisEkrani extends AppCompatActivity {
    EditText kadiEditText;
    EditText ksifreEditText;
    Button girisYapButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    //FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();//FirebaseAuth kütüphanesi ile kullanıcı adı ve şifre oluşturup login olma , profil bilgi güncelleme ve kullanma yapmayı düşünüyorum şuanda.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_giris_ekrani);
        tasarimcode();



    }

    public void tasarimcode()
    {
        kadiEditText=findViewById(R.id.kadiEditText);
        ksifreEditText=findViewById(R.id.ksifreEditText);
        girisYapButton=findViewById(R.id.girisYapButton);
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();



        girisYapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kullanici_adi=kadiEditText.getText().toString();//kullanıcı adını edittextten alsın diye böyle yaptık.
                String kullanici_sifre=ksifreEditText.getText().toString();

               /* firebaseAuth.createUserWithEmailAndPassword(kullanici_adi,kullanici_sifre).addOnCompleteListener(GirisEkrani.this,new OnSuccessListener<AuthResult>(){
                    @Override
                    public void onSuccess(AuthResult authResult){
                        kullaniciadiEkle();
                        kullanicisifreEkle();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Yeni Kullanıcı Hatası",e.getMessage());
                    }
                })*/

                kadiEditText.setText("");//username gönderdikten sonra edittextin içini silebiliriz.
                ksifreEditText.setText("");

                kullaniciadiEkle(kullanici_adi);
                kullanicisifreEkle(kullanici_sifre);
            }
        });
    }



    public void kullaniciadiEkle(String kadi)
    {
        reference.child("Kullanıcılar").child(kadi).child("kullaniciadi").setValue(kadi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "giriş başarılı", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void kullanicisifreEkle(String ksifre)
    {
        reference.child("Kullanıcılar").child(ksifre).child("kullanicisifre").setValue(ksifre).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "giriş başarılı..", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}

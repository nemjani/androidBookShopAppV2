package com.example.androidbookshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Rating extends AppCompatActivity {
    private static final String LOG_TAG = Rating.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarForRating);
        setSupportActionBar(toolbar);

        ImageView star1 = findViewById(R.id.star1);
        ImageView star2 = findViewById(R.id.star2);
        ImageView star3 = findViewById(R.id.star3);
        ImageView star4 = findViewById(R.id.star4);
        ImageView star5 = findViewById(R.id.star5);

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.star_animation);
                star1.startAnimation(anim);
                rateApp(Integer.parseInt(star1.getTag().toString()));
            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.star_animation);
                star2.startAnimation(anim);
                rateApp(Integer.parseInt(star2.getTag().toString()));
            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.star_animation);
                star3.startAnimation(anim);
                rateApp(Integer.parseInt(star3.getTag().toString()));
            }
        });

        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.star_animation);
                star4.startAnimation(anim);
                rateApp(Integer.parseInt(star4.getTag().toString()));
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.star_animation);
                star5.startAnimation(anim);
                rateApp(Integer.parseInt(star5.getTag().toString()));
            }
        });

        displayRatings();
    }

    public void navigateHome(View view) {
        Intent intent = new Intent(this, ShopListActivity.class);
        startActivity(intent);
        finish();
    }

    private void rateApp(int rating) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String userEmail = currentUser.getEmail();

            RatingStructure userRating = new RatingStructure(userEmail, rating);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference ratingsCollection = db.collection("Rating");

            ratingsCollection.add(userRating)
                    .addOnSuccessListener(documentReference -> {
                        Log.d(LOG_TAG, "Értékelés sikeresen feltöltve!");
                        displayRatings();
                    })
                    .addOnFailureListener(e -> Log.e(LOG_TAG, "Hiba az értékelés feltöltése közben", e));

            Log.d(LOG_TAG, "Felhasználó: " + userEmail + ", értékelés: " + rating);
        } else {
            Log.d(LOG_TAG, "Nincs bejelentkezett felhasználó az értékeléshez!");
        }
    }

    private void displayRatings() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference ratingsCollection = db.collection("Rating");

            ratingsCollection.whereEqualTo("userEmail", userEmail)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        StringBuilder ratingsText = new StringBuilder();
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            RatingStructure rating = document.toObject(RatingStructure.class);
                            int userRating = rating.getRating();
                            ratingsText.append(userEmail).append(" - ").append(userRating).append("\n");
                        }
                        TextView ratingsTextView = findViewById(R.id.ratingsTextView);
                        ratingsTextView.setText(ratingsText.toString());
                    })
                    .addOnFailureListener(e -> Log.e(LOG_TAG, "Hiba az értékelések lekérdezése közben", e));
        } else {
            Log.d(LOG_TAG, "Nincs bejelentkezett felhasználó az értékelésekhez!");
        }
    }
}

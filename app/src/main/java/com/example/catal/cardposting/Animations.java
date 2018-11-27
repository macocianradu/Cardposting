package com.example.catal.cardposting;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class Animations {
    public static void slideUp(final ImageView view){
        view.setVisibility(ImageView.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,               // fromXDelta
                0,                 // toXDelta
                0,           // fromYDelta
                -view.getHeight());                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);

        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setImageResource(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public static void slideDown(ImageView view){
        view.setVisibility(ImageView.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                  // fromXDelta
                0,                    // toXDelta
                -view.getHeight(),                  // fromYDelta
                0);             // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }
    public static void slideOutLeft(ImageView view) {
        view.setVisibility(ImageView.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                -view.getWidth(),                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }
    public static void slideOutRight(ImageView view){
        view.setVisibility(ImageView.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                view.getWidth(),                 // toXDelta
                0,                  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }
    public static void slideInRight(ImageView view){
        view.setVisibility(ImageView.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                view.getWidth(),                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }public static void slideInLeft(ImageView view){
        view.setVisibility(ImageView.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                -view.getWidth(),                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }
}

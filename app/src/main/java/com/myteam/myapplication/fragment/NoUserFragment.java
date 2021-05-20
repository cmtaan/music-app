package com.myteam.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.myteam.myapplication.R;
import com.myteam.myapplication.activity.LoginActivity;

public class NoUserFragment extends Fragment {
    View view;
    Button btnLogin;
    TextView txtUserName, txtUserEmail;
    boolean isLogin = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_no_user, container, false);

        btnLogin = view.findViewById(R.id.button_nouser_login);
        txtUserName = view.findViewById(R.id.textview_user_name);
        txtUserEmail = view.findViewById(R.id.textview_user_email);


//        if (isLogin) {
//
//        } else {

//        }

        setInfo();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        setInfo();
    }

    // setInfo
    private void setInfo() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", 0);

        if (userId == 0) {
            isLogin = false;
            txtUserName.setText("Chưa Đăng Nhập");
            txtUserEmail.setText("");
            btnLogin.setText("Đăng Nhập");

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainIntent = new Intent(requireActivity(),  LoginActivity.class);
                    requireActivity().startActivity(mainIntent);
                }
            });
        } else {
            isLogin = true;
            String name = sharedPreferences.getString("user_name", "NULL");
            String email = sharedPreferences.getString("user_email", "NULL");
            txtUserName.setText(name);
            txtUserEmail.setText(email);
            btnLogin.setText("Đăng Xuất");



            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
                    sharedPreferences.edit().remove("USER").apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_name", null);
                    editor.putString("user_email", null);
                    editor.putInt("user_id", 0);
                    editor.apply();
                    setInfo();
                }
            });
        }
    }

    // RELOAD FRAGMENT
    private void reloadFragment() {
//        Fragment currentFragment = Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(this);
        fragmentTransaction.attach(this);
        fragmentTransaction.commit();
    }
}

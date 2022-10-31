package com.example.purpulse.result;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.purpulse.PulseActivity;
import com.example.purpulse.R;
import com.example.purpulse.RecordActivity;

public class ResultFragment2 extends Fragment{

    ImageView tired,sad;
    ImageButton btn_down1,btn_down2;
    Button btn_restart,btn_sure;

    /** PopupWindow **/
    PopupWindow pop_tired,pop_sad;
    TextView txt_level,txt_suggest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result2, container, false);
        tired = view.findViewById(R.id.tired_img);
        sad = view.findViewById(R.id.sad_img);
        btn_down1 = view.findViewById(R.id.btn_down1);
        btn_down2 = view.findViewById(R.id.btn_down2);
        btn_restart = view.findViewById(R.id.btn_restart);
        btn_sure = view.findViewById(R.id.btn_sure);
        btn_down1.setOnClickListener(lis);
        btn_down2.setOnClickListener(lis);
        btn_restart.setOnClickListener(lis);
        btn_sure.setOnClickListener(lis);
        popup_tired();
        popup_sad();
        return view;
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_down1:{
                    pop_tired.showAsDropDown(tired);
                    break;
                }
                case R.id.btn_down2:{
                    pop_sad.showAsDropDown(sad);
                    break;
                }
                case R.id.btn_restart:{
                    startActivity(new Intent(getActivity(), PulseActivity.class));
                    break;
                }
                case R.id.btn_sure:{
                    startActivity(new Intent(getActivity(), RecordActivity.class));
                    break;
                }
            }
        }
    };

    public void popup_tired(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_tired,null);
        pop_tired = new PopupWindow(view);
        pop_tired.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop_tired.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop_tired.setFocusable(true);
        txt_level = view.findViewById(R.id.txt_level);
        txt_suggest = view.findViewById(R.id.txt_suggest);
        pop_tired.showAsDropDown(tired);
        txt_level.setText(R.string.tired_good);
        txt_suggest.setText(R.string.tired_good_suggest);
    }

    public void popup_sad(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_tired,null);
        pop_sad = new PopupWindow(view);
        pop_sad.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop_sad.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop_sad.setFocusable(true);
        txt_level = view.findViewById(R.id.txt_level);
        txt_suggest = view.findViewById(R.id.txt_suggest);
        txt_level.setText(R.string.sad_good);
        txt_suggest.setText(R.string.sad_good_suggest);
    }
}
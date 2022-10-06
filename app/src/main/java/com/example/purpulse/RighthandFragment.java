package com.example.purpulse;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RighthandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RighthandFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txt_rightteach;
    private Button btn_ecgstart,btn_yes,btn_no;
    Dialog dialog;
    View dialogView;

    public RighthandFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RighthandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RighthandFragment newInstance(String param1, String param2) {
        RighthandFragment fragment = new RighthandFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_righthand, container,false);
        txt_rightteach = view.findViewById(R.id.txt_rightteach);
        btn_ecgstart = view.findViewById(R.id.btn_ecgstart);
        btn_ecgstart.setOnClickListener(lis);
        txt_rightteach.setText(R.string.righthand);
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));
        SpannableStringBuilder builder = new SpannableStringBuilder(txt_rightteach.getText().toString());
        builder.setSpan(redSpan,32,34, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        txt_rightteach.setText(builder);
        return view;
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDialog();
        }
    };

    public void getDialog(){
        dialog = new Dialog(getActivity(),R.style.custom_dialog);
        dialogView = getLayoutInflater().inflate(R.layout.dialog_view,null);
        dialog.setContentView(dialogView);
        btn_yes = dialogView.findViewById(R.id.btn_yes);
        btn_no = dialogView.findViewById(R.id.btn_no);
        dialog.show();
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth()*0.7);
        lp.height = (int)(display.getHeight()*0.2);
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(false);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(getActivity(),PulseActivity.class));
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
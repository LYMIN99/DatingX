package com.lymindev.datingx.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lymindev.datingx.R;
import com.lymindev.datingx.adapters.CountriesListAdapter;

import java.util.Objects;

public class DialogAllCountryCode {
    private Context context;
    private Dialog dialog;

    public DialogAllCountryCode(Context context) {
        this.context = context;
        initialize();
    }

    public void initialize(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR); // before
        dialog.setContentView(R.layout.dialog_list_all_country);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);

        ListView listView = dialog.findViewById(R.id.listView);
        String[] recourseList=context.getResources().getStringArray(R.array.CountryCodes);
        listView.setAdapter(new CountriesListAdapter(context, recourseList));

        dialog.show();
    }


}

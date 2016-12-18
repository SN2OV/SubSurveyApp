package com.example.subsurvey.reverseSurvey.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.subsurvey.R;
import com.example.subsurvey.staySurvey.ui.StaySurveyTimeRecordedNewActivity;

/**
 * Created by SN2OV on 2016/5/6.
 */
public class DialogRSUpdateNo extends Dialog{
    public DialogRSUpdateNo(Context context) {
        super(context);
    }

    public DialogRSUpdateNo(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder{
        private Context context;
        private String title;
        private View contentView;
        private EditText rsUpdateCountET;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public EditText getNewCountET(){
            return rsUpdateCountET;
        }

        public void setOrignLineNumET(EditText rsUpdateCountET) {
            this.rsUpdateCountET = rsUpdateCountET;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public DialogRSUpdateNo create(){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final DialogRSUpdateNo dialog = new DialogRSUpdateNo(context, R.style.Dialog);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.dialog_rs_updatecount, null);

            this.setOrignLineNumET(((EditText) layout.findViewById(R.id.rsUpdateCountET)));

            ((TextView) layout.findViewById(R.id.rsUpdateCountTitle)).setText(title);

            if (negativeButtonClickListener != null) {
                ((Button) layout.findViewById(R.id.rsUpdateCountPbt))
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                positiveButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }

            if(negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.rsUpdateCountNbt))
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                negativeButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_NEGATIVE);
                            }
                        });
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }

}

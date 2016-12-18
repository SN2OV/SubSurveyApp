package com.example.subsurvey;

import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.staySurvey.ui.StaySurveyTimeRecordedNewActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialogSSCount extends Dialog {

	public DialogSSCount(Context context) {
		super(context);
	}
	
	public DialogSSCount(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder{
		private Context context;
		private String title;
		private View contentView;
		private RelativeLayout orignLineNumRL;
		private EditText orignLineNumET;
		private EditText getOnNumET;
		private EditText getOffNumET;
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
		
		public EditText getOrignLineNumET() {
			return orignLineNumET;
		}

		public void setOrignLineNumET(EditText orignLineNumET) {
			this.orignLineNumET = orignLineNumET;
		}

		public EditText getGetOnNumET() {
			return getOnNumET;
		}

		public void setGetOnNumET(EditText getOnNumET) {
			this.getOnNumET = getOnNumET;
		}

		public EditText getGetOffNumET() {
			return getOffNumET;
		}

		public void setGetOffNumET(EditText getOffNumET) {
			this.getOffNumET = getOffNumET;
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
		
		public Builder setOriginLineNumRL(RelativeLayout relativeLayout,int visibility){
			this.orignLineNumRL = relativeLayout;
			this.orignLineNumRL.setVisibility(visibility);
			return this;
		}
		
		public Builder setOrignLineNumVisibility(int visible){
//			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			View layout = inflater.inflate(R.layout.activity_dialog_sscount, null);
//			RelativeLayout lineNumView = (RelativeLayout)layout.findViewById(R.id.sslOrignNumRL);
			this.orignLineNumRL.setVisibility(visible);
			return this;
		}
		
		public RelativeLayout getLineNumRL(){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.activity_dialog_sscount, null);
			RelativeLayout lineNumView = (RelativeLayout)layout.findViewById(R.id.sslOrignNumRL);
			return lineNumView;
		}
		
		public DialogSSCount create(){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final DialogSSCount dialog = new DialogSSCount(context,R.style.Dialog);
			dialog.setCanceledOnTouchOutside(false);
			View layout = inflater.inflate(R.layout.activity_dialog_sscount, null);
			
			this.setOrignLineNumET(((EditText) layout.findViewById(R.id.sslOrignNumET)));
			this.setGetOnNumET(((EditText) layout.findViewById(R.id.sslGetOnNumET)));
			this.setGetOffNumET(((EditText) layout.findViewById(R.id.sslGetOffNumET)));
			
			((TextView) layout.findViewById(R.id.title)).setText(title);
//			//隐藏排队人数
			RelativeLayout lineNumView = (RelativeLayout)layout.findViewById(R.id.sslOrignNumRL);
			lineNumView.setVisibility(StaySurveyTimeRecordedNewActivity.orignLineNumVisiblity);
			
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			
			if (negativeButtonClickListener != null) {
				((Button) layout.findViewById(R.id.positiveButton))
						.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								positiveButtonClickListener.onClick(dialog,
										DialogInterface.BUTTON_POSITIVE);
							}
						});
			}
			
			if(negativeButtonText != null) {
				((Button) layout.findViewById(R.id.negativeButton))
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

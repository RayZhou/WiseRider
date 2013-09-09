package com.leizhou.wr;

import com.leizhou.wr.web.OpenWebView;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



	public class SetDirectionDialog extends DialogFragment {

		public SetDirectionDialog() {
	        // Empty constructor required for DialogFragment
	    }

		private String Model="";
		public void setModel(String model){
			this.Model=model;
		}
	    /** The system calls this only when creating the layout in a dialog. */
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // The only reason you might override this method when using onCreateView() is
	        // to modify any dialog characteristics. For example, the dialog includes a
	        // title by default, but your custom layout might not need it. So here you can
	        // remove the dialog title, but you must call the superclass to get the Dialog.
	        //Dialog dialog = super.onCreateDialog(savedInstanceState);
			final Dialog dialog = new Dialog(getActivity());
			dialog.setContentView(R.layout.address_dlg);
			dialog.setTitle("Input Start and end Point");
			
			
	        Button button_cancel = (Button)dialog.findViewById(R.id.bt_cancell);
	        button_cancel.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // do nothing
	                dismiss();
	            }
	        });
	        
	        Button bt_confirm = (Button)dialog.findViewById(R.id.bt_confirmAddress);
	        bt_confirm.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                //go set preference 
	            	
	    	        android.app.FragmentManager fm = getFragmentManager();
	    	        SetPerferenceDialog setPerferenceDialog = new SetPerferenceDialog();
	    	        //testDialog.setMostRecentLocation(mostRecentLocation);
	    	        setPerferenceDialog.setRetainInstance(true);
	    	        setPerferenceDialog.setCancelable(false);
	    	        setPerferenceDialog.show(fm, "fragment_name");
	            	
	            	dismiss();                
	            }
	        });
	        
	        Button bt_fromMap1 = (Button)dialog.findViewById(R.id.bt_fromMap1);
	        bt_fromMap1.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                //select a address from map
	            	if(Model.equalsIgnoreCase("offer")){
	            		// set offer
	            		Intent intent = new Intent(dialog.getContext(), OpenWebView.class);
	            		intent.putExtra("isStartAddress", true);
	            		startActivity(intent); 
	            	}
	            	if(Model.equalsIgnoreCase("request")){
	            		//do request
	            	}
	            }
	        });
	        Button bt_fromMap2 = (Button)dialog.findViewById(R.id.bt_fromMap2);
	        bt_fromMap2.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                ////select end address from map
	            	if(Model.equalsIgnoreCase("offer")){
	                Intent intent = new Intent(dialog.getContext(), OpenWebView.class);
	                intent.putExtra("isEndAddress", false);
	                startActivity(intent);
	            	}
	            	if(Model.equalsIgnoreCase("request")){
	            		//do request
	            	}
	                
	            }
	        });
	        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        return dialog;
	    }



	}

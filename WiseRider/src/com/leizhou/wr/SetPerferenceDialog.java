package com.leizhou.wr;

import com.leizhou.wr.DAO.PublicDataBox;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SetPerferenceDialog extends DialogFragment {

	public SetPerferenceDialog() {
        // Empty constructor required for DialogFragment
    }
	
	 @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // The only reason you might override this method when using onCreateView() is
	        // to modify any dialog characteristics. For example, the dialog includes a
	        // title by default, but your custom layout might not need it. So here you can
	        // remove the dialog title, but you must call the superclass to get the Dialog.
	        //Dialog dialog = super.onCreateDialog(savedInstanceState);
			final Dialog dialog = new Dialog(getActivity());
			dialog.setContentView(R.layout.perfernce_dlg);
			dialog.setTitle("Adding conditions for the route");			
			
	        Button button_cancel = (Button)dialog.findViewById(R.id.bt_cancell);
	        button_cancel.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // do nothing
	                dismiss();
	            }
	        });
	        final EditText fuelConsumed = (EditText)dialog.findViewById(R.id.editFuelConsumed);
	        
	        Button bt_confirm = (Button)dialog.findViewById(R.id.bt_confirmAddress);
	        bt_confirm.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                //Save the route
	            	//doing matching
	            	//showing result
	            	double temp=0;
	            	temp=Double.valueOf(fuelConsumed.getText().toString());
	            	//PublicDataBox mydata=(PublicDataBox)getApplication();
	            	//((PublicDataBox) getApplication()).setUserCarFuelFactor(temp);
	            	Intent intent = new Intent(dialog.getContext(), MatchRoutes.class);
	            	intent.putExtra("fuelConsumedFactor",temp);
	            	startActivity(intent);

	            	
	            	dismiss();                
	            }
	        });
			
			return dialog;
	 }

}

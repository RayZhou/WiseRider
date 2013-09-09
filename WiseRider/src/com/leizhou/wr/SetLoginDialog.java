package com.leizhou.wr;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.leizhou.wr.DAO.*;


public class SetLoginDialog extends DialogFragment {
	
	public SetLoginDialog() {
		// Empty constructor required for DialogFragment
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
		dialog.setContentView(R.layout.login_dlg);
		dialog.setTitle("Login");
		final EditText input_userName=(EditText)dialog.findViewById(R.id.input_userName);        
        final EditText input_password=(EditText)dialog.findViewById(R.id.input_password);
		
        Button button_confirm = (Button)dialog.findViewById(R.id.bt_login_confirm);
        button_confirm.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // doing login
            	String username="",password="";
            	username=input_userName.getText().toString();
            	password=input_password.getText().toString();
            	if(username.equals("")||password.equals(""))
            	{
            		Toast.makeText(getActivity(),
            				"Please Input Username and Password!", Toast.LENGTH_LONG).show();
            	}else{
            		//doing login
            		((PublicDataBox) getActivity().getApplication()).SavingAccount(username, password);
            		Toast.makeText(getActivity(),
            				username+" "+password+ " saved", Toast.LENGTH_LONG).show();
            		dismiss();
            	}
            }
        });
        
        Button button_cancel = (Button)dialog.findViewById(R.id.bt_login_cancell);
        button_cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //dismiss the dialog
            	dismiss();                
            }
        });
        
        
        
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

}

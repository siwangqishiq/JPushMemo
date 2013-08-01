package com.xinlan.jpushmemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	private Button send;
	private EditText edit;
	private SendTask mTask;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button)this.findViewById(R.id.send);
        edit = (EditText)this.findViewById(R.id.content);
        
        send.setOnClickListener(new SendClick());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
	protected void onDestroy() {
		super.onDestroy();
		if(mTask!=null){
			mTask.cancel(true);
		}
	}
    
    private final class SendClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			if(mTask!=null){
				mTask.cancel(true);
			}
			mTask = new SendTask();
			mTask.execute(edit.getText().toString().trim());
		}
    }//end inner class

	private final class SendTask extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {
			String sendContent = params[0];
			return (new SendService()).send(sendContent);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
			edit.setText("");
		}
    }//end inner class
}

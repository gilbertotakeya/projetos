package com.br.representanetmobile;

import com.br.representanetmobile.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
						
		final Button entrar = (Button)findViewById(R.id.btnCarteiraPedidos);
		
		
		entrar.setOnClickListener(new View.OnClickListener() 
		{			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent i = new Intent(menu.this, carteirapedidos.class);
				//i.setClass(this, menu.class);
				startActivity(i);
				//Toast.makeText(MainActivity.this, login.getText().toString(), Toast.LENGTH_LONG).show();
			}
		});
	}
}

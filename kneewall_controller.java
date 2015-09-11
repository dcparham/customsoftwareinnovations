package com.example;

import java.text.DecimalFormat;
import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class SegmentActivity extends Activity
{//Activity BEGIN
	
private EditText amount1;//Height
private EditText amount2;//Width
private EditText amount3;//#Segments
private EditText amount4;//Leg Size
private TextView segmentLength;//resulting segment length
private TextView turnAngle;//resulting segment length
private Button calculate;
private Button clearFields; //the graphic at the bottom is the clear button

private double val1_height=0;
private double val2_width=0;
private double val3_segments=0;
private double val4_radius=0; //test
private double val5_theta1;
private double val6_theta2;
private double val7_resultantChord;
@SuppressWarnings("unused")
private String result1;
private int fixFlag1 = 0;

boolean flag = true;
private double val4_segments;
/** Called when the activity is first created. */

@Override //overriding the onCreate
public void onCreate(Bundle savedInstanceState)
{
super.onCreate(savedInstanceState);
setContentView(R.layout.main);
initControls();
}
private void initControls()
{
amount1=(EditText)findViewById(R.id.amount1);
amount2=(EditText)findViewById(R.id.amount2);
amount3=(EditText)findViewById(R.id.amount3);
amount4=(EditText)findViewById(R.id.amount4);
segmentLength=(TextView)findViewById(R.id.segmentLength);
turnAngle=(TextView)findViewById(R.id.turnAngle);
calculate=(Button)findViewById(R.id.calculate);
clearFields=(Button)findViewById(R.id.clearFields);

calculate.setOnClickListener(new Button.OnClickListener()
{public void onClick(View v) { calculate();}});

clearFields.setOnClickListener(new Button.OnClickListener()
{public void onClick(View v) { clearFields();}});
}

public void clearFields()
{
	try{
		
	}
	
	catch(Exception e) {     // ... handle errors ... 
		//throw new RuntimeException(e);		
	}
}

public void calculate()//was private
{//calculate BEGIN

	try {//#1.3
val1_height=Double.parseDouble(amount1.getText().toString());
val2_width=Double.parseDouble(amount2.getText().toString());
val3_segments=Double.parseDouble(amount3.getText().toString());
val4_segments=Double.parseDouble(amount4.getText().toString());

/*here's the meat of the calculations:
* val1-3 are input: height, width, segments
* val4_radius = ((4*(val1_height*val1_height))+(val2_width*val2_width))/(8*height)
* val5_theta1 = RadToDeg((2*arcsin((val2_width/(2*val4_radius)))))
* val6_theta2 = (val5_theta1/val3_segments)
* val7_resultantChord = (2*val4_radius) * sin(val6_theta2/2)
* val7_resultantChord = (2*(((4*(B3*B3))+(B4*B4))/(8*B3)))*sin((((2*asin((B4/(2*B2))))/B5)/2))
* =(2*(((4*(val1_height*val1_height))+(val2_width*val2_width))/(8*val1_height)))*Math.sin((((2* Math.asin((val2_width/(2*val4_radius))))/val3_segments)/2))
* */

val4_radius = ((4*(val1_height*val1_height))+(val2_width*val2_width))/(8*val1_height);
val5_theta1 =  Math.toDegrees((2 * Math.asin((val2_width/(2*val4_radius)))));
val6_theta2 = (val5_theta1/val3_segments);
//val7_resultantChord = (2*val4_radius) * Math.sin(val6_theta2/2);
val7_resultantChord = (2*(((4*(val1_height*val1_height))+(val2_width*val2_width))/(8*val1_height)))*Math.sin((((2* Math.asin((val2_width/(2*val4_radius))))/val3_segments)/2));

//scale it, then round it to nearest 2 dec places
val7_resultantChord = (int)(val7_resultantChord * 100);
val7_resultantChord = Math.round(val7_resultantChord);
val7_resultantChord = val7_resultantChord/100;

//finalize the resultantChord[aka "segment"] by subtracting leg size
val7_resultantChord = val7_resultantChord - val4_segments;

//scale it, then round it to nearest 2 dec places
val6_theta2 = (int)val6_theta2 * 100;
val6_theta2 = Math.round(val6_theta2);
val6_theta2 = val6_theta2/100;

if(fixFlag1==0){//fixFlag1 BEGIN
segmentLength.setText(Double.toString(val7_resultantChord));
turnAngle.setText(Double.toString(Math.round(val6_theta2)));
}//fixFlag1 END
else {//else BEGIN
segmentLength.setText("    {LENGTH}"); }//else END
		}//try END
			catch(Exception e) {     // ... handle errors ... 
				//throw new RuntimeException(e);		
		}	

}//calculate END
}//Activity END

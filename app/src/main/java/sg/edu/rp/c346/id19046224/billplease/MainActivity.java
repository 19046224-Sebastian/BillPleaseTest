package sg.edu.rp.c346.id19046224.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView billView, splitView;
    EditText amtText, paxText, discountText;
    Button splitButton, resetButton;
    ToggleButton svsButton, gstButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billView = findViewById(R.id.billView);
        splitView = findViewById(R.id.splitView);

        amtText = findViewById(R.id.amtText);
        paxText = findViewById(R.id.paxText);
        discountText = findViewById(R.id.discountText);

        splitButton = findViewById(R.id.splitButton);
        resetButton = findViewById(R.id.resetButton);

        svsButton = findViewById(R.id.svsButton);
        gstButton = findViewById(R.id.gstButton);
        //ToggleButton toggle = (ToggleButton) findViewById(R.id.gstButton);

        String amt = amtText.getText().toString();
        final double subtotal = Double.parseDouble(amt);

        String pax = paxText.getText().toString();
        final int people = Integer.parseInt(pax);

        //gst and service charges calc here
        final double gstgrandtotal = subtotal + (subtotal * (7.0/100.0));
        final double scgrandtotal = subtotal + (subtotal * (10.0/100.0));
        final double supergrandtotal = subtotal + (subtotal * (17.0/100.0));


        gstButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                       billView.setText(R.string.bill + Double.toString(gstgrandtotal));
                    } else if (svsButton.isChecked()){
                        billView.setText(R.string.bill + Double.toString(scgrandtotal));
                    } else if (gstButton.isChecked() && svsButton.isChecked()){
                        billView.setText(R.string.bill + Double.toString(supergrandtotal));
                    } else {
                        billView.setText(R.string.bill + Double.toString(subtotal));
                    }
                }
            });

        splitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (gstButton.isChecked()) {
                    double splato = gstgrandtotal / people;
                    splitView.setText(R.string.each + Double.toString(splato));
                } else if (svsButton.isChecked()){
                    double splato = scgrandtotal / people;
                    splitView.setText(R.string.each + Double.toString(splato));
                } else if (gstButton.isChecked() && svsButton.isChecked()){
                    double splato = supergrandtotal / people;
                    splitView.setText(R.string.each + Double.toString(splato));
                } else {
                    double split = subtotal / people;
                    splitView.setText(R.string.each + Double.toString(split));
                }
            }

        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paxText.setText("");
                amtText.setText("");
                discountText.setText("");
                billView.setText(R.string.bill);
                splitView.setText(R.string.each);
            }
        });



    }
}

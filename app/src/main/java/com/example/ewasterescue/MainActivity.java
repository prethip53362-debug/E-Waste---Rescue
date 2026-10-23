package com.example.ewasterescue;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    private TextView text(String value, int size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(Color.rgb(30, 55, 45));
        t.setPadding(0, 12, 0, 12);
        return t;
    }

    private void showHome() {
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(28, 36, 28, 24);
        layout.setBackgroundColor(Color.rgb(245, 250, 246));

        TextView logo = text("♻️ E-WASTE RESCUE", 25);
        logo.setTypeface(null, Typeface.BOLD);
        layout.addView(logo);

        TextView subtitle = text(
            "Give your old electronics a new life.", 16);
        layout.addView(subtitle);

        TextView heading = text(
            "Save Electronics.\nProtect Our Planet.", 30);
        heading.setTypeface(null, Typeface.BOLD);
        layout.addView(heading);

        TextView description = text(
            "Discover responsible ways to recycle, reuse " +
            "and dispose of electronic waste.", 16);
        layout.addView(description);

        addButton("🔍  Identify E-Waste", "Identify");
        addButton("♻️  Recycling Guide", "Guide");
        addButton("📍  Recycling Centers", "Centers");
        addButton("🚚  Request Pickup", "Pickup");

        TextView footer = text(
            "Small actions. Cleaner future. 🌍", 15);
        footer.setGravity(Gravity.CENTER);
        layout.addView(footer);

        setContentView(layout);
    }

    private void addButton(String label, String action) {
        Button button = new Button(this);
        button.setText(label);
        button.setTextSize(16);
        button.setAllCaps(false);

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 8, 0, 8);
        layout.addView(button, params);

        button.setOnClickListener(v -> {
            String message;

            switch (action) {
                case "Identify":
                    message = "E-waste includes old phones, " +
                        "laptops, chargers and batteries.";
                    break;
                case "Guide":
                    message = "Keep electronics separate. " +
                        "Use an authorised e-waste recycler.";
                    break;
                case "Centers":
                    message = "Recycling centre locator " +
                        "will be added in the next phase.";
                    break;
                default:
                    message = "Pickup request feature " +
                        "will be added in the next phase.";
                    break;
            }

            Toast.makeText(
                MainActivity.this,
                message,
                Toast.LENGTH_LONG
            ).show();
        });
    }
}

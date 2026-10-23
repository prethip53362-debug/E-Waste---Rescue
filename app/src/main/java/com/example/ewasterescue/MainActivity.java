
package com.example.ewasterescue;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final int GREEN = Color.rgb(27, 120, 76);
    private final int DARK = Color.rgb(29, 55, 43);
    private final int PALE = Color.rgb(237, 247, 239);
    private final int MUTED = Color.rgb(104, 122, 110);
    private LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private TextView text(String value, int size, int color,
                          boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(null, Typeface.BOLD);
        t.setIncludeFontPadding(true);
        return t;
    }

    private void space(LinearLayout parent, int height) {
        View v = new View(this);
        parent.addView(v, new LinearLayout.LayoutParams(
                1, height));
    }

    private LinearLayout column() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        return box;
    }

    private void showHome() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.WHITE);

        content = column();
        content.setPadding(22, 24, 22, 28);
        scroll.addView(content);

        LinearLayout brand = new LinearLayout(this);
        brand.setGravity(Gravity.CENTER_VERTICAL);

        TextView logo = text("♻", 34, GREEN, true);
        brand.addView(logo);

        LinearLayout brandText = column();
        brandText.setPadding(10, 0, 0, 0);
        brandText.addView(text("E-WASTE", 19, DARK, true));
        brandText.addView(text("RESCUE", 13, GREEN, true));
        brand.addView(brandText);

        TextView tag = text("ECO APP", 11, GREEN, true);
        tag.setGravity(Gravity.CENTER);
        tag.setPadding(10, 8, 10, 8);
        tag.setBackground(shape(PALE, 30));
        LinearLayout.LayoutParams tagParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        tagParams.gravity = Gravity.CENTER_VERTICAL;
        tagParams.leftMargin = 12;
        brand.addView(tag, tagParams);

        content.addView(brand);
        space(content, 26);

        LinearLayout hero = column();
        hero.setPadding(20, 22, 20, 22);
        hero.setBackground(shape(PALE, 24));

        hero.addView(text("A CLEANER FUTURE STARTS HERE",
                11, GREEN, true));
        space(hero, 12);
        hero.addView(text("Give old electronics\nnew life.",
                30, DARK, true));
        space(hero, 10);
        hero.addView(text(
                "Recycle responsibly. Protect our planet.",
                15, MUTED, false));
        space(hero, 18);

        Button explore = new Button(this);
        explore.setText("Explore E-Waste  →");
        explore.setTextColor(Color.WHITE);
        explore.setAllCaps(false);
        explore.setTextSize(15);
        explore.setBackground(shape(GREEN, 16));
        hero.addView(explore, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 54));

        explore.setOnClickListener(v -> showCategories());
        content.addView(hero);

        space(content, 26);
        content.addView(text("What do you want to do?",
                21, DARK, true));
        space(content, 6);
        content.addView(text("Choose a service to get started.",
                14, MUTED, false));
        space(content, 16);

        addService("📱", "Identify E-Waste",
                "Choose a device and learn what to do",
                () -> showCategories());

        addService("♻️", "Recycling Guide",
                "Learn safe reuse and disposal tips",
                () -> showGuide());

        addService("📍", "Recycling Centres",
                "Find responsible recycling options",
                () -> showCenters());

        addService("🚚", "Request Pickup",
                "Explore the e-waste pickup process",
                () -> showPickup());

        space(content, 22);
        TextView footer = text(
                "SMALL ACTIONS. BIGGER IMPACT. 🌍",
                12, GREEN, true);
        footer.setGravity(Gravity.CENTER);
        content.addView(footer);

        setContentView(scroll);
    }

    private void addService(String emoji, String title,
                            String subtitle, Runnable action) {
        LinearLayout card = new LinearLayout(this);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(14, 12, 12, 12);
        card.setBackground(shape(Color.rgb(248, 251, 248), 18));

        LinearLayout.LayoutParams cp =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        cp.bottomMargin = 10;
        content.addView(card, cp);

        TextView icon = text(emoji, 27, GREEN, false);
        icon.setGravity(Gravity.CENTER);
        icon.setBackground(shape(PALE, 15));
        card.addView(icon, new LinearLayout.LayoutParams(54, 54));

        LinearLayout details = column();
        details.setPadding(12, 0, 4, 0);
        details.addView(text(title, 16, DARK, true));
        details.addView(text(subtitle, 12, MUTED, false));

        LinearLayout.LayoutParams dp =
                new LinearLayout.LayoutParams(0,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        card.addView(details, dp);

        TextView arrow = text("›", 28, GREEN, true);
        card.addView(arrow);

        card.setOnClickListener(v -> action.run());
    }

    private void showCategories() {
        page("Identify E-Waste",
                "Select an electronic item to learn about safe handling.");

        addCategory("📱", "Mobile phones",
                "Back up your data, erase personal information, " +
                "and take unwanted phones to an authorised recycler.");

        addCategory("💻", "Laptops & computers",
                "Back up and securely erase your data before " +
                "reuse, repair or authorised recycling.");

        addCategory("🔌", "Chargers & cables",
                "Keep cables separate from general rubbish. " +
                "Use an authorised e-waste collection point.");

        addCategory("🔋", "Batteries",
                "Do not puncture, crush or burn batteries. " +
                "Keep damaged batteries away from heat and seek " +
                "specialist handling.");

        addCategory("🎧", "Earphones & accessories",
                "Check for repair or reuse options. Recycle " +
                "electronic accessories through authorised channels.");

        backButton();
    }

    private void addCategory(String emoji, String title,
                             String advice) {
        LinearLayout card = column();
        card.setPadding(16, 15, 16, 15);
        card.setBackground(shape(PALE, 18));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        p.bottomMargin = 12;
        content.addView(card, p);

        card.addView(text(emoji + "  " + title,
                18, DARK, true));
        space(card, 7);
        card.addView(text(advice, 14, MUTED, false));
    }

    private void showGuide() {
        page("Recycling Guide",
                "Simple steps for responsible e-waste handling.");

        addInfo("01  Repair or reuse",
                "Check whether the device can be repaired, donated " +
                "or safely reused before recycling.");

        addInfo("02  Protect your data",
                "Back up important files and erase personal " +
                "information before handing over a device.");

        addInfo("03  Handle batteries carefully",
                "Never burn, puncture or put batteries in regular " +
                "household rubbish. Follow local collection guidance.");

        addInfo("04  Choose authorised recyclers",
                "Use authorised e-waste collection centres or " +
                "recyclers. Avoid informal dismantling.");

        backButton();
    }

    private void showCenters() {
    page("Recycling Centres",
            "Choose a safe way to find a collection point.");

    addInfo("Find an authorised recycler",
            "Check official CPCB or Tamil Nadu Pollution " +
            "Control Board resources for current authorised " +
            "e-waste recyclers and collection options.");

    Button search = actionButton("Recycling Centre Information");
    search.setOnClickListener(v -> {
        Toast.makeText(
                MainActivity.this,
                "Online centre search will be added soon.",
                Toast.LENGTH_LONG
        ).show();
    });

    addInfo("Important",
            "Nearby centre search and verified centre details " +
            "are not connected in this prototype yet.");

    backButton();
    }

    private void showPickup() {
    page("Request Pickup",
            "Fill in your e-waste details to prepare a pickup request.");

    addInfo("Step 1 of 2",
            "Provide the device details below.");

    TextView deviceLabel = text(
            "Device Type", 16, DARK, true);
    content.addView(deviceLabel);
    space(content, 8);

    android.widget.Spinner deviceSpinner =
            new android.widget.Spinner(this);

    String[] devices = {
            "Select device",
            "Mobile Phone",
            "Laptop / Computer",
            "Battery",
            "Charger / Cable",
            "Earphones",
            "Other Electronics"
    };

    android.widget.ArrayAdapter<String> adapter =
            new android.widget.ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    devices);

    adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item);

    deviceSpinner.setAdapter(adapter);

    content.addView(deviceSpinner,
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 55));

    space(content, 16);

    TextView quantityLabel = text(
            "Quantity", 16, DARK, true);
    content.addView(quantityLabel);
    space(content, 8);

    android.widget.EditText quantityInput =
            new android.widget.EditText(this);

    quantityInput.setHint("Enter quantity");
    quantityInput.setInputType(
            android.text.InputType.TYPE_CLASS_NUMBER);
    quantityInput.setSingleLine(true);

    content.addView(quantityInput,
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 55));

    space(content, 16);

    TextView locationLabel = text(
            "Pickup Location", 16, DARK, true);
    content.addView(locationLabel);
    space(content, 8);

    android.widget.EditText locationInput =
            new android.widget.EditText(this);

    locationInput.setHint("Enter area / city");
    locationInput.setSingleLine(true);

    content.addView(locationInput,
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 55));

    space(content, 22);

    Button submit = actionButton("Prepare Pickup Request");

    submit.setOnClickListener(v -> {
        String device =
                deviceSpinner.getSelectedItem().toString();

        String quantity =
                quantityInput.getText().toString().trim();

        String location =
                locationInput.getText().toString().trim();

        if (device.equals("Select device")) {
            Toast.makeText(this,
                    "Please select a device.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantity.isEmpty()
                || Integer.parseInt(quantity) <= 0) {
            Toast.makeText(this,
                    "Enter a valid quantity.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (location.isEmpty()) {
            Toast.makeText(this,
                    "Please enter your pickup location.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        page("Request Prepared",
                "Your demo pickup request details are ready.");

        addInfo("Device", device);
        addInfo("Quantity", quantity);
        addInfo("Location", location);

        addInfo("Demo Notice",
                "This is a hackathon prototype. " +
                "Your details have not been sent to a recycler, " +
                "and no real pickup has been booked.");

        backButton();
    });

    addInfo("Privacy",
            "Enter only demo details. This prototype does not " +
            "send or store your information online.");

    backButton();
}

    private void page(String title, String subtitle) {
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.WHITE);

        content = column();
        content.setPadding(22, 26, 22, 30);
        scroll.addView(content);

        TextView heading = text(title, 27, DARK, true);
        content.addView(heading);
        space(content, 8);
        content.addView(text(subtitle, 15, MUTED, false));
        space(content, 22);

        setContentView(scroll);
    }

    private void addInfo(String title, String description) {
        LinearLayout card = column();
        card.setPadding(16, 16, 16, 16);
        card.setBackground(shape(PALE, 18));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        p.bottomMargin = 12;
        content.addView(card, p);

        card.addView(text(title, 17, DARK, true));
        space(card, 8);
        card.addView(text(description, 14, MUTED, false));
    }

    private Button actionButton(String label) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextColor(Color.WHITE);
        b.setTextSize(15);
        b.setAllCaps(false);
        b.setBackground(shape(GREEN, 16));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 54);
        p.bottomMargin = 14;
        content.addView(b, p);
        return b;
    }

    private void backButton() {
    Button back = actionButton("← Back to Home");
    back.setOnClickListener(v -> {
        showHome();
    });
}
}

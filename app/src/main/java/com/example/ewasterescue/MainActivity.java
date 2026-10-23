package com.example.ewasterescue;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final int GREEN = Color.rgb(27, 120, 76);
    private final int DARK = Color.rgb(29, 55, 43);
    private final int PALE = Color.rgb(237, 247, 239);
    private final int MUTED = Color.rgb(104, 122, 110);
    private final int WHITE = Color.WHITE;

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

    private TextView text(
            String value, int size, int color, boolean bold) {

        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);

        if (bold) {
            t.setTypeface(null, Typeface.BOLD);
        }

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

    // HOME SCREEN

    private void showHome() {

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(WHITE);

        content = column();
        content.setPadding(22, 24, 22, 28);
        scroll.addView(content);

        LinearLayout brand = new LinearLayout(this);
        brand.setGravity(Gravity.CENTER_VERTICAL);

        brand.addView(text("♻", 38, GREEN, true));

        LinearLayout brandText = column();
        brandText.setPadding(10, 0, 0, 0);

        brandText.addView(text("E-WASTE", 21, DARK, true));
        brandText.addView(text("RESCUE", 14, GREEN, true));

        brand.addView(brandText);
        content.addView(brand);

        space(content, 25);

        LinearLayout hero = column();
        hero.setPadding(20, 22, 20, 22);
        hero.setBackground(shape(PALE, 24));

        hero.addView(text(
                "A CLEANER FUTURE STARTS HERE",
                11, GREEN, true));

        space(hero, 12);

        hero.addView(text(
                "Give old electronics\nnew life.",
                30, DARK, true));

        space(hero, 10);

        hero.addView(text(
                "Recycle responsibly. Protect our planet.",
                15, MUTED, false));

        space(hero, 18);

        Button explore = actionButton(
                "Explore E-Waste  →", hero);

        explore.setOnClickListener(v -> showCategories());

        content.addView(hero);

        space(content, 25);

        content.addView(text(
                "What do you want to do?",
                21, DARK, true));

        space(content, 6);

        content.addView(text(
                "Choose a service to get started.",
                14, MUTED, false));

        space(content, 16);

        addService("📱", "Identify E-Waste",
                "Choose a device and learn what to do",
                this::showCategories);

        addService("♻", "Recycling Guide",
                "Learn safe reuse and disposal tips",
                this::showGuide);

        addService("📍", "Recycling Centres",
                "Explore responsible recycling options",
                this::showCenters);

        addService("🚚", "Request Pickup",
                "Prepare a demo pickup request",
                this::showPickup);

        space(content, 22);

        TextView footer = text(
                "SMALL ACTIONS. BIGGER IMPACT.",
                12, GREEN, true);

        footer.setGravity(Gravity.CENTER);
        content.addView(footer);

        setContentView(scroll);
    }

    private void addService(
            String emoji,
            String title,
            String subtitle,
            Runnable action) {

        LinearLayout card = new LinearLayout(this);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(14, 12, 12, 12);
        card.setBackground(
                shape(Color.rgb(248, 251, 248), 18));

        LinearLayout.LayoutParams cp =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        cp.bottomMargin = 10;
        content.addView(card, cp);

        TextView icon = text(emoji, 27, GREEN, false);
        icon.setGravity(Gravity.CENTER);
        icon.setBackground(shape(PALE, 15));

        card.addView(icon,
                new LinearLayout.LayoutParams(54, 54));

        LinearLayout details = column();
        details.setPadding(12, 0, 4, 0);

        details.addView(text(title, 16, DARK, true));
        details.addView(text(subtitle, 12, MUTED, false));

        card.addView(details,
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1));

        card.addView(text("›", 28, GREEN, true));

        card.setOnClickListener(v -> action.run());
    }

    // DEVICE CATEGORIES

    private void showCategories() {

        page("Identify E-Waste",
                "Select a device to learn about safe handling.");

        addInfo("📱  Mobile Phones",
                "Back up your data and erase personal information. " +
                "Consider repair, reuse or authorised recycling.");

        addInfo("💻  Laptops & Computers",
                "Protect your files and personal information " +
                "before repair, donation or recycling.");

        addInfo("🔌  Chargers & Cables",
                "Keep electronic accessories out of general rubbish. " +
                "Use an appropriate collection point.");

        addInfo("🔋  Batteries",
                "Do not puncture, crush or burn batteries. " +
                "Keep damaged batteries away from heat.");

        addInfo("🎧  Earphones & Accessories",
                "Check for repair or reuse before recycling " +
                "through appropriate collection channels.");

        backButton();
    }

    // RECYCLING GUIDE

    private void showGuide() {

        page("Recycling Guide",
                "Simple steps for responsible e-waste handling.");

        addInfo("01  Repair or Reuse",
                "Check whether the device can be repaired, " +
                "donated or safely reused.");

        addInfo("02  Protect Your Data",
                "Back up important files and erase personal " +
                "information before handing over a device.");

        addInfo("03  Handle Batteries Carefully",
                "Never burn, puncture or place batteries " +
                "in regular household rubbish.");

        addInfo("04  Choose Authorised Recyclers",
                "Use authorised collection centres or recyclers. " +
                "Avoid informal dismantling.");

        backButton();
    }

    // RECYCLING CENTRES

    private void showCenters() {

        page("Recycling Centres",
                "Explore safe ways to recycle electronic waste.");

        addInfo("Find an Authorised Recycler",
                "Check official CPCB or Tamil Nadu Pollution " +
                "Control Board resources for current authorised " +
                "e-waste recyclers and collection options.");

        addInfo("Before Visiting",
                "Confirm accepted device types, operating hours " +
                "and collection arrangements with the centre.");

        addInfo("Prototype Notice",
                "Verified nearby centres and live map search " +
                "are not connected in this version.");

        backButton();
    }

    // PICKUP REQUEST FORM

    private void showPickup() {

        page("Request Pickup",
                "Enter your e-waste details below.");

        addInfo("Step 1 of 2",
                "Select your device, enter its quantity " +
                "and provide your area or city.");

        // DEVICE TYPE

        content.addView(text(
                "Device Type", 16, DARK, true));

        space(content, 6);

        Spinner deviceSpinner = new Spinner(this);

        String[] devices = {
                "Select device",
                "Mobile Phone",
                "Laptop / Computer",
                "Battery",
                "Charger / Cable",
                "Earphones",
                "Other Electronics"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
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

        // QUANTITY

        content.addView(text(
                "Quantity", 16, DARK, true));

        space(content, 6);

        EditText quantityInput = createInput(
                "Enter quantity",
                InputType.TYPE_CLASS_NUMBER);

        content.addView(quantityInput,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 55));

        space(content, 16);

        // LOCATION

        content.addView(text(
                "Pickup Location", 16, DARK, true));

        space(content, 6);

        EditText locationInput = createInput(
                "Enter area / city",
                InputType.TYPE_CLASS_TEXT);

        content.addView(locationInput,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 55));

        space(content, 20);

        // SUBMIT BUTTON

        Button submit = actionButton(
                "Prepare Pickup Request", content);

        submit.setOnClickListener(v -> {

            String device =
                    deviceSpinner.getSelectedItem().toString();

            String quantity =
                    quantityInput.getText().toString().trim();

            String location =
                    locationInput.getText().toString().trim();

            if (device.equals("Select device")) {
                Toast.makeText(
                        this,
                        "Please select a device.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int count;

            try {
                count = Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                Toast.makeText(
                        this,
                        "Enter a valid quantity.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (count <= 0) {
                Toast.makeText(
                        this,
                        "Quantity must be greater than zero.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (location.isEmpty()) {
                Toast.makeText(
                        this,
                        "Please enter your area or city.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            showRequestConfirmation(
                    device, String.valueOf(count), location);
        });

        space(content, 12);

        addInfo("Privacy Notice",
                "Use demo details only. This prototype " +
                "does not send or store your information online.");

        backButton();
    }

    // STYLED INPUT FIELDS

    private EditText createInput(
            String hint, int inputType) {

        EditText input = new EditText(this);

        input.setHint(hint);
        input.setTextColor(DARK);
        input.setHintTextColor(MUTED);
        input.setTextSize(15);
        input.setSingleLine(true);
        input.setInputType(inputType);
        input.setPadding(16, 8, 16, 8);
        input.setMinHeight(55);
        input.setBackground(shape(PALE, 12));

        return input;
    }

    // REQUEST CONFIRMATION

    private void showRequestConfirmation(
            String device, String quantity, String location) {

        page("Request Prepared",
                "Your demo request details are ready.");

        addInfo("Device Type", device);
        addInfo("Quantity", quantity);
        addInfo("Pickup Location", location);

        addInfo("Demo Confirmation",
                "Your request details were validated successfully. " +
                "No real pickup has been booked and no recycler " +
                "has received this information.");

        Button home = actionButton(
                "← Back to Home", content);

        home.setOnClickListener(v -> showHome());
    }

    // COMMON PAGE LAYOUT

    private void page(String title, String subtitle) {

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(WHITE);

        content = column();
        content.setPadding(22, 26, 22, 30);

        scroll.addView(content);

        content.addView(text(title, 27, DARK, true));

        space(content, 8);

        content.addView(text(subtitle, 15, MUTED, false));

        space(content, 22);

        setContentView(scroll);
    }

    // INFORMATION CARD

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

    // BUTTON CREATOR

    private Button actionButton(
            String label, LinearLayout parent) {

        Button b = new Button(this);

        b.setText(label);
        b.setTextColor(WHITE);
        b.setTextSize(15);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setBackground(shape(GREEN, 16));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 54);

        p.bottomMargin = 14;

        parent.addView(b, p);

        return b;
    }

    // BACK TO HOME

    private void backButton() {

        Button back = actionButton(
                "← Back to Home", content);

        back.setOnClickListener(v -> showHome());
    }
}    "Fill in your e-waste details to prepare a pickup request.");

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

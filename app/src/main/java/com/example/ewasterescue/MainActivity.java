
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

    private TextView text(String value, int size,
                          int color, boolean bold) {
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
        parent.addView(v, new LinearLayout.LayoutParams(1, height));
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
        scroll.setBackgroundColor(Color.WHITE);

        content = column();
        content.setPadding(22, 24, 22, 28);
        scroll.addView(content);

        LinearLayout brand = new LinearLayout(this);
        brand.setGravity(Gravity.CENTER_VERTICAL);
        brand.addView(text("♻", 36, GREEN, true));

        LinearLayout brandText = column();
        brandText.setPadding(10, 0, 0, 0);
        brandText.addView(text("E-WASTE", 20, DARK, true));
        brandText.addView(text("RESCUE", 14, GREEN, true));
        brand.addView(brandText);

        content.addView(brand);
        space(content, 24);

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

        Button explore = makeButton("Explore E-Waste");
        hero.addView(explore);
        explore.setOnClickListener(v -> showCategories());
        content.addView(hero);

        space(content, 24);
        content.addView(text("What do you want to do?",
                21, DARK, true));
        space(content, 14);

        addService("📱", "Identify E-Waste",
                "Learn how to handle electronic items",
                () -> showCategories());

        addService("♻", "Recycling Guide",
                "Learn safe reuse and disposal tips",
                () -> showGuide());

        addService("📍", "Recycling Centres",
                "Explore responsible recycling options",
                () -> showCenters());

        addService("🚚", "Request Pickup",
                "View the pickup request process",
                () -> showPickup());

        space(content, 20);
        TextView footer = text(
                "SMALL ACTIONS. BIGGER IMPACT.",
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
                        0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        card.addView(text("›", 28, GREEN, true));
        card.setOnClickListener(v -> action.run());
    }

    // IDENTIFY E-WASTE

    private void showCategories() {
        page("Identify E-Waste",
                "Select an item to learn about safe handling.");

        addInfo("📱 Mobile Phones",
                "Back up your data and erase personal information. " +
                "Consider repair, reuse or authorised recycling.");

        addInfo("💻 Laptops & Computers",
                "Protect your files before repair, reuse " +
                "or authorised recycling.");

        addInfo("🔌 Chargers & Cables",
                "Keep electronic accessories out of general " +
                "rubbish. Use a suitable collection point.");

        addInfo("🔋 Batteries",
                "Do not puncture, crush or burn batteries. " +
                "Keep damaged batteries away from heat.");

        addInfo("🎧 Earphones & Accessories",
                "Check for repair or reuse before recycling " +
                "through appropriate collection channels.");

        backButton();
    }

    // RECYCLING GUIDE

    private void showGuide() {
        page("Recycling Guide",
                "Simple steps for responsible e-waste handling.");

        addInfo("01  Repair or Reuse",
                "Check whether a device can be repaired, " +
                "donated or safely reused.");

        addInfo("02  Protect Your Data",
                "Back up important files and erase personal " +
                "information before handing over a device.");

        addInfo("03  Handle Batteries Carefully",
                "Never burn or puncture batteries. Follow " +
                "appropriate battery collection guidance.");

        addInfo("04  Choose Authorised Recyclers",
                "Use authorised collection centres or recyclers.");

        backButton();
    }

    // RECYCLING CENTRES

    private void showCenters() {
        page("Recycling Centres",
                "Explore responsible recycling options.");

        addInfo("Find an Authorised Recycler",
                "Check official CPCB or Tamil Nadu Pollution " +
                "Control Board resources for current recycler " +
                "information.");

        Button info = makeButton("Centre Information");
        content.addView(info);
        info.setOnClickListener(v -> Toast.makeText(
                MainActivity.this,
                "Check official recycler listings before visiting.",
                Toast.LENGTH_LONG).show());

        addInfo("Before Visiting",
                "Confirm accepted devices, opening hours and " +
                "collection arrangements with the centre.");

        backButton();
    }

    // PICKUP INFORMATION

    private void showPickup() {
        page("Request Pickup",
                "Prepare for a responsible e-waste collection.");

        addInfo("Step 1",
                "Identify the electronic device you want to recycle.");

        addInfo("Step 2",
                "Estimate the quantity and note your area or city.");

        addInfo("Step 3",
                "Contact an authorised recycler to confirm " +
                "whether collection is available in your area.");

        addInfo("Prototype Notice",
                "This demo does not submit a real pickup request " +
                "or send information to a recycler.");

        backButton();
    }

    // COMMON PAGE

    private void page(String title, String subtitle) {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.WHITE);

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

    private Button makeButton(String label) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextColor(Color.WHITE);
        b.setTextSize(15);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setBackground(shape(GREEN, 16));

        b.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 54));

        return b;
    }

    // BACK TO HOME

    private void backButton() {
        Button back = makeButton("← Back to Home");
        content.addView(back);

        back.setOnClickListener(v -> showHome());
    }
}

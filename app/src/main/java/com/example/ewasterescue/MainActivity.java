
package com.example.ewasterescue;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
private boolean isHomeScreen = true;
    
    private final int GREEN = Color.rgb(24, 128, 83);
    private final int DARK = Color.rgb(19, 50, 39);
    private final int PALE = Color.rgb(235, 247, 239);
    private final int BG = Color.rgb(247, 250, 247);
    private final int MUTED = Color.rgb(103, 120, 109);
    private final int WHITE = Color.WHITE;

    private LinearLayout content;

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (android.os.Build.VERSION.SDK_INT >= 35) {
        getWindow().getDecorView().setOnApplyWindowInsetsListener(
                (view, insets) -> {
                    android.graphics.Insets bars =
                            insets.getInsets(
                                    android.view.WindowInsets.Type.systemBars());

                    view.setPadding(
                            bars.left,
                            bars.top,
                            bars.right,
                            bars.bottom);

                    return insets;
                });
    }

    showSplash();
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
        if (bold) t.setTypeface(null, Typeface.BOLD);
        t.setIncludeFontPadding(true);
        return t;
    }

    private void space(LinearLayout parent, int height) {
        View v = new View(this);
        parent.addView(v,
                new LinearLayout.LayoutParams(1, height));
    }

    private LinearLayout column() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        return box;
    }

    private LinearLayout horizontal() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.HORIZONTAL);
        box.setGravity(Gravity.CENTER_VERTICAL);
        return box;
    }

    private void showSplash() {
        FrameLayout root = new FrameLayout(this);
        ImageView background = new ImageView(this);

        background.setImageResource(R.drawable.splash_screen);
        background.setScaleType(ImageView.ScaleType.CENTER_CROP);

        root.addView(background,
                new FrameLayout.LayoutParams(-1, -1));

        getWindow().setStatusBarColor(Color.rgb(8, 45, 32));
        getWindow().setNavigationBarColor(Color.rgb(8, 45, 32));

        setContentView(root);

        background.setAlpha(0f);
        background.setScaleX(1.08f);
        background.setScaleY(1.08f);

        background.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(1400)
                .start();

        root.postDelayed(() -> showHome(), 2600);
    }

    private void preparePage(String title, String subtitle) {
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(WHITE);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.rgb(8, 45, 32));

        content = column();
        content.setPadding(20, 22, 20, 26);
        scroll.addView(content);

        LinearLayout header = horizontal();
        

ImageView mark = new ImageView(this);
mark.setImageResource(R.drawable.ewaste_logo);
mark.setScaleType(ImageView.ScaleType.FIT_CENTER);
mark.setBackground(shape(PALE, 16));
mark.setPadding(5, 5, 5, 5);

header.addView(mark,
        new LinearLayout.LayoutParams(54, 54));


        LinearLayout heading = column();
        heading.setPadding(12, 0, 0, 0);
        heading.addView(text(title, 25, WHITE, true));
        space(heading, 3);
        heading.addView(text(
        subtitle, 13, Color.rgb(210, 230, 218), false));

        header.addView(heading,
                new LinearLayout.LayoutParams(
                        0, -2, 1));

        content.addView(header);
        space(content, 22);

        setContentView(scroll);
    }

private void showHome() {
    isHomeScreen = true;
    getWindow().setStatusBarColor(BG);
    getWindow().setNavigationBarColor(WHITE);

    getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

    ScrollView scroll = new ScrollView(this);
    scroll.setFillViewport(true);
    scroll.setBackgroundColor(WHITE);
    scroll.setClipToPadding(false);

    content = column();
    content.setPadding(20, 20, 20, 30);
    scroll.addView(content);

    
 // BRAND HEADER
    LinearLayout brand = horizontal();

    
ImageView logo = new ImageView(this);
logo.setImageResource(R.drawable.ewaste_logo);
logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
logo.setBackground(shape(PALE, 18));
logo.setPadding(5, 5, 5, 5);

brand.addView(logo,
        new LinearLayout.LayoutParams(58, 58));

    LinearLayout brandText = column();
    brandText.setPadding(12, 0, 0, 0);

    brandText.addView(
            text("E-WASTE", 21, DARK, true));

    brandText.addView(
            text("RESCUE  •  SMART RECYCLING",
                    10, GREEN, true));

    brand.addView(brandText,
            new LinearLayout.LayoutParams(0, -2, 1));

    TextView badge = text("ECO\nHERO", 10, GREEN, true);
    badge.setGravity(Gravity.CENTER);
    badge.setPadding(12, 8, 12, 8);
    badge.setBackground(shape(PALE, 16));

    brand.addView(badge);
    content.addView(brand);

    space(content, 26);

    
    // PREMIUM HERO SECTION
    LinearLayout hero = column();
    hero.setPadding(22, 24, 22, 24);
    hero.setBackground(shape(
            Color.rgb(8, 55, 40), 24));

    TextView eyebrow = text(
            "🌿  MAKE EVERY DEVICE COUNT",
            12, Color.rgb(165, 235, 190), true);
    hero.addView(eyebrow);

    space(hero, 20);

    hero.addView(text(
            "Give old tech.\nA new purpose.",
            32, WHITE, true));

    space(hero, 14);

    hero.addView(text(
            "Your unused electronics can have a second life. "
                    + "Choose smarter ways to repair, reuse "
                    + "and recycle responsibly.",
            14, Color.rgb(222, 239, 228), false));

    space(hero, 24);

    Button explore = makeButton("Explore E-Waste  →");
    hero.addView(explore);

    explore.setOnClickListener(v -> showCategories());

    space(hero, 16);

    TextView trust = text(
            "REPAIR  •  REUSE  •  RECYCLE",
            11, Color.rgb(185, 222, 195), true);
    trust.setGravity(Gravity.CENTER);
    hero.addView(trust);

    content.addView(hero);

    // SECTION TITLE
    LinearLayout section = horizontal();

    LinearLayout sectionText = column();

    sectionText.addView(text(
            "Your Eco Dashboard",
            22, DARK, true));

    space(sectionText, 4);

    sectionText.addView(text(
            "Small actions. Meaningful change.",
            13, MUTED, false));

    section.addView(sectionText,
            new LinearLayout.LayoutParams(0, -2, 1));

    TextView liveTag = text(
            "DEMO", 10, GREEN, true);

    liveTag.setPadding(10, 7, 10, 7);
    liveTag.setBackground(shape(PALE, 20));
    section.addView(liveTag);

    content.addView(section);

    space(content, 15);

    // STATISTIC CARDS
    LinearLayout stats = horizontal();

    LinearLayout stat1 = column();
    stat1.setPadding(15, 17, 10, 17);
    stat1.setBackground(shape(WHITE, 20));

    stat1.addView(text("♻", 27, GREEN, true));
    space(stat1, 10);

    stat1.addView(text("06", 29, DARK, true));

    stat1.addView(text(
            "Device categories",
            12, MUTED, false));

    LinearLayout stat2 = column();
    stat2.setPadding(15, 17, 10, 17);
    stat2.setBackground(shape(WHITE, 20));

    stat2.addView(text("🌍", 27, GREEN, false));
    space(stat2, 10);

    stat2.addView(text("01", 29, DARK, true));

    stat2.addView(text(
            "Planet to protect",
            12, MUTED, false));

    LinearLayout.LayoutParams s1 =
            new LinearLayout.LayoutParams(0, -2, 1);

    s1.setMargins(0, 0, 7, 0);
    stats.addView(stat1, s1);

    LinearLayout.LayoutParams s2 =
            new LinearLayout.LayoutParams(0, -2, 1);

    s2.setMargins(7, 0, 0, 0);
    stats.addView(stat2, s2);
    
    // SERVICES SECTION
    content.addView(text(
            "What would you like to do?",
            21, DARK, true));

    space(content, 5);

    content.addView(text(
        "Everything you need for responsible e-waste handling.",
        13, MUTED, false));

    space(content, 16);

    addService(
            "📊",
            "Impact Dashboard",
            "Explore illustrative sustainability statistics",
            () -> showDashboard());

    addService(
            "📱",
            "Identify E-Waste",
            "Choose a device and discover safe handling tips",
            () -> showCategories());

    addService(
            "♻️",
            "Recycling Guide",
            "Learn to repair, reuse and recycle responsibly",
            () -> showGuide());

    addService(
            "📍",
            "Recycling Centres",
            "Explore recycler information and verification tips",
            () -> showCenters());

    addService(
            "🚚",
            "Request Pickup",
            "Prepare a sample e-waste collection request",
            () -> showPickup());

    space(content, 18);

    
    // PREMIUM ECO FOOTER
    LinearLayout footer = column();
    footer.setGravity(Gravity.CENTER);
    footer.setPadding(20, 22, 20, 22);
    footer.setBackground(shape(
            Color.rgb(232, 247, 237), 24));

    TextView footerIcon = text(
            "🌿", 32, GREEN, true);
    footerIcon.setGravity(Gravity.CENTER);
    footer.addView(footerIcon);

    space(footer, 10);

    TextView footerTitle = text(
            "Small Actions. Big Impact.",
            18, DARK, true);
    footerTitle.setGravity(Gravity.CENTER);
    footer.addView(footerTitle);

    space(footer, 7);

    TextView footerSubtitle = text(
            "Give your old electronics a greener future.",
            13, GREEN, false);
    footerSubtitle.setGravity(Gravity.CENTER);
    footer.addView(footerSubtitle);

    space(footer, 12);

    TextView footerTag = text(
            "THINK GREEN  •  RECYCLE SMART",
            10, GREEN, true);
    footerTag.setGravity(Gravity.CENTER);
    footer.addView(footerTag);

    content.addView(footer);

    setContentView(scroll);
}

    

private void addService(String emoji, String title,
                        String subtitle, Runnable action) {

    LinearLayout card = horizontal();
    card.setGravity(Gravity.CENTER_VERTICAL);
    card.setPadding(16, 17, 14, 17);
    card.setBackground(shape(WHITE, 22));
    card.setElevation(2f);
    card.setClickable(true);
    card.setFocusable(true);

    LinearLayout.LayoutParams cp =
            new LinearLayout.LayoutParams(-1, -2);
    cp.bottomMargin = 14;
    content.addView(card, cp);

    // Premium mint icon container
    LinearLayout iconBox = new LinearLayout(this);
    iconBox.setGravity(Gravity.CENTER);
    iconBox.setBackground(shape(
            Color.rgb(230, 247, 236), 18));

    TextView icon = text(emoji, 26, GREEN, false);
    icon.setGravity(Gravity.CENTER);
    iconBox.addView(icon,
            new LinearLayout.LayoutParams(-1, -1));

    card.addView(iconBox,
            new LinearLayout.LayoutParams(56, 56));

    // Service title and description
    LinearLayout details = column();
    details.setGravity(Gravity.CENTER_VERTICAL);
    details.setPadding(14, 0, 8, 0);

    TextView titleText = text(title, 16, DARK, true);
    details.addView(titleText);

    space(details, 6);

    TextView subtitleText = text(
            subtitle, 12, MUTED, false);
    details.addView(subtitleText);

    card.addView(details,
            new LinearLayout.LayoutParams(0, -2, 1));

    // Navigation arrow
    TextView arrow = text("›", 29, GREEN, true);
    arrow.setGravity(Gravity.CENTER);

    card.addView(arrow,
            new LinearLayout.LayoutParams(24, -2));

    // Preserve existing navigation
    card.setOnClickListener(v -> {
        v.animate()
                .scaleX(0.98f)
                .scaleY(0.98f)
                .setDuration(80)
                .withEndAction(() -> {
                    v.setScaleX(1f);
                    v.setScaleY(1f);
                    action.run();
                })
                .start();
    });
}

    private void showCategories() {
        preparePage("Identify E-Waste",
                "Choose the right way to handle each item.");

        addInfo("📱  Mobile Phones",
                "Back up your data and erase personal information. " +
                "Consider repair, reuse or authorised recycling.");

        addInfo("💻  Laptops & Computers",
                "Protect your files before repair, reuse or " +
                "authorised recycling.");

        addInfo("🔌  Chargers & Cables",
                "Keep electronic accessories out of general " +
                "rubbish. Use a suitable collection point.");

        addInfo("🔋  Batteries",
                "Do not puncture, crush or burn batteries. " +
                "Keep damaged batteries away from heat.");

        addInfo("🎧  Earphones & Accessories",
                "Check for repair or reuse before recycling " +
                "through suitable collection channels.");

        addInfo("🖥️  TVs & Monitors",
                "Handle screens carefully. Do not break or burn " +
                "them. Use an authorised collection point.");

        backButton();
    }

    private void showGuide() {
        preparePage("Recycling Guide",
                "Simple steps towards responsible recycling.");

        addInfo("01  Repair or Reuse",
                "Check whether your device can be repaired, " +
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

    private void showCenters() {
        preparePage("Recycling Centres",
                "Explore options and verify details before visiting.");

        addInfo("♻️  Green Era Recyclers",
                "Reported location: Coimbatore district.\n" +
                "Contact the recycler to confirm its current " +
                "address, authorisation and accepted devices.");

        addInfo("♻️  Techazar E-Cyclers",
                "Reported location: Coimbatore district.\n" +
                "Confirm current registration and collection " +
                "availability before visiting.");

        addInfo("♻️  Earth Sense Recycle",
                "Reported location: Chengalpattu district.\n" +
                "Contact the company to verify its current " +
                "address and accepted e-waste categories.");

        addInfo("♻️  E Cycle Solutions",
                "Reported location: Ambattur, Chennai.\n" +
                "Confirm accepted e-waste categories and " +
                "current authorisation before visiting.");

        addInfo("🔎  Verify Before Visiting",
                "This list is for demo and discovery purposes. " +
                "Verify current recycler registration and details " +
                "through official CPCB or TNPCB resources.");

        Button official = makeButton("Official Recycler Information");
        content.addView(official);
        official.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://eprewaste.cpcb.gov.in/")));
            } catch (Exception e) {
                Toast.makeText(this,
                        "Unable to open the website.",
                        Toast.LENGTH_LONG).show();
            }
        });

        space(content, 12);
        backButton();
    }

    private void showDashboard() {
        preparePage("Impact Dashboard",
                "A snapshot of our sustainability mission.");

        LinearLayout hero = column();
        hero.setPadding(20, 22, 20, 22);
        hero.setBackground(shape(DARK, 22));

        hero.addView(text("♻  TOTAL DEVICES",
                13, Color.rgb(180, 235, 195), true));
        space(hero, 8);
        hero.addView(text("128", 40, WHITE, true));
        space(hero, 5);
        hero.addView(text("Illustrative demo statistics",
                13, Color.rgb(224, 240, 228), false));

        LinearLayout.LayoutParams hp =
                new LinearLayout.LayoutParams(-1, -2);
        hp.bottomMargin = 14;
        content.addView(hero, hp);

        LinearLayout row = horizontal();

        LinearLayout phones = column();
        phones.setPadding(14, 16, 10, 16);
        phones.setBackground(shape(WHITE, 18));
        phones.addView(text("📱", 25, GREEN, false));
        space(phones, 8);
        phones.addView(text("76", 28, DARK, true));
        phones.addView(text("Phones", 13, MUTED, true));

        LinearLayout others = column();
        others.setPadding(14, 16, 10, 16);
        others.setBackground(shape(WHITE, 18));
        others.addView(text("💻", 25, GREEN, false));
        space(others, 8);
        others.addView(text("52", 28, DARK, true));
        others.addView(text("Other devices", 13, MUTED, true));

        LinearLayout.LayoutParams p1 =
                new LinearLayout.LayoutParams(0, -2, 1);
        p1.setMargins(0, 0, 6, 0);
        row.addView(phones, p1);

        LinearLayout.LayoutParams p2 =
                new LinearLayout.LayoutParams(0, -2, 1);
        p2.setMargins(6, 0, 0, 0);
        row.addView(others, p2);

        LinearLayout.LayoutParams rp =
                new LinearLayout.LayoutParams(-1, -2);
        rp.bottomMargin = 14;
        content.addView(row, rp);

        addInfo("🌍  Our Mission",
                "Promote repair, reuse and authorised recycling " +
                "to reduce electronic waste.");

        addInfo("💚  Make a Difference",
                "Protect personal data and choose authorised " +
                "e-waste collecti, Tamil Nadu.\n" +
            "Confirm current registration and collection " +
            "availability before visiting.");

    addInfo("♻️ Earth Sense Recycle Private Limited",
            "Location: Chengalpattu district\n" +
            "Address: Survey No. 247, Thenmelpakkam Village, " +
            "Chengalpattu district, Tamil Nadu.\n" +
            "Contact the company before arranging delivery.");

    addInfo("♻️ E Cycle Solutions Private Limited",
            "Location: Ambattur, Chennai\n" +
            "Address: Pattravakkam village, Ambattur " +
            "Industrial Estate, Chennai, Tamil Nadu.\n" +
            "Confirm accepted e-waste categories and " +
            "current authorisation before visiting.");

    addInfo("🔎 Verify Before Visiting",
            "Check the latest official recycler listings " +
            "on the CPCB E-Waste EPR Portal and TNPCB website. " +
            "Addresses and registration status may change.");

    Button info = makeButton("Official Recycler Information");
content.addView(info);

info.setOnClickListener(v -> {
    try {
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://eprewaste.cpcb.gov.in/"));
        startActivity(intent);
    } catch (Exception e) {
        Toast.makeText(
                MainActivity.this,
                "Unable to open the official website.",
                Toast.LENGTH_LONG).show();
    }
});

    space(content, 12);
    backButton();
}

    // PICKUP INFORMATION


private void showPickup() {
    page("Request Pickup",
            "Arrange collection for your unwanted electronics.");

    addInfo("♻️ Responsible Recycling",
            "Enter your details to prepare your pickup request.");

    LinearLayout form = column();
    form.setPadding(16, 18, 16, 18);
    form.setBackground(shape(PALE, 18));

    form.addView(text("Your Name", 15, DARK, true));
    EditText name = new EditText(this);
    name.setHint("Enter your name");
    name.setSingleLine(true);
    form.addView(name);

    space(form, 10);
    form.addView(text("Phone Number", 15, DARK, true));
    EditText phone = new EditText(this);
    phone.setHint("Enter 10-digit phone number");
    phone.setInputType(3);
    phone.setSingleLine(true);
    form.addView(phone);

    space(form, 10);
    form.addView(text("Device Type", 15, DARK, true));
    Spinner device = new Spinner(this);
    String[] devices = {
            "Select a device",
            "Mobile Phone",
            "Laptop / Computer",
            "TV / Monitor",
            "Battery",
            "Chargers / Cables",
            "Other Electronics"
    };

    ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, devices);
    adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item);
    device.setAdapter(adapter);
    form.addView(device);

    space(form, 10);
    form.addView(text("Pickup Area / City", 15, DARK, true));
    EditText area = new EditText(this);
    area.setHint("Enter your area or city");
    area.setSingleLine(true);
    form.addView(area);

    space(form, 10);
    form.addView(text("Additional Details", 15, DARK, true));
    EditText details = new EditText(this);
    details.setHint("Quantity or other details (optional)");
    details.setMinLines(2);
    details.setGravity(Gravity.TOP);
    form.addView(details);

    space(form, 16);
    content.addView(form);

    space(content, 14);
    Button submit = makeButton("Submit Pickup Request");
    content.addView(submit);

    submit.setOnClickListener(v -> {
        String userName = name.getText().toString().trim();
        String userPhone = phone.getText().toString().trim();
        String userArea = area.getText().toString().trim();

        if (userName.isEmpty() || userPhone.isEmpty()
                || userArea.isEmpty()
                || device.getSelectedItemPosition() == 0) {
            Toast.makeText(this,
                    "Please fill in name, phone, device and area.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (!userPhone.matches("[0-9]{10}")) {
            Toast.makeText(this,
                    "Enter a valid 10-digit phone number.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        String request = "Name: " + userName
                + "\nPhone: " + userPhone
                + "\nDevice: " + device.getSelectedItem()
                + "\nArea: " + userArea
                + "\nDetails: " + details.getText().toString().trim();

        getSharedPreferences("PickupRequests", MODE_PRIVATE)
                .edit()
                .putString("latest_request", request)
                .apply();

        new AlertDialog.Builder(this)
                .setTitle("Pickup Request Saved ♻️")
                .setMessage("Thank you, " + userName
                        + "! Your pickup details have been saved "
                        + "on this device.\n\n"
                        + "The request has not been sent to a "
                        + "collection team. An online service "
                        + "connection is needed for that.")
                .setPositiveButton("OK",
                        (dialog, which) -> showHome())
                .show();
    });

    space(content, 12);
    backButton();
}
    
    private void page(String title, String subtitle) {
        isHomeScreen = false;
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
    b.setPadding(12, 0, 12, 0);
    b.setMinHeight(0);
    b.setMinimumHeight(0);
    b.setStateListAnimator(null);
    b.setBackground(shape(GREEN, 16));

    LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    54);

    b.setLayoutParams(params);

    return b;
}

    // BACK TO HOME

    private void backButton() {
        Button back = makeButton("← Back to Home");
        content.addView(back);

        back.setOnClickListener(v -> showHome());
    }
    @Override
public void onBackPressed() {
    if (!isHomeScreen) {
        showHome();
    } else {
        super.onBackPressed();
    }
}
}

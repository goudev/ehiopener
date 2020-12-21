package br.dev.ehiopener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class SettingsActivity extends Activity {
    public static byte[] concatByteArrays(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + bArr2.length);
        System.arraycopy(bArr2, 0, copyOf, bArr.length, bArr2.length);
        return copyOf;
    }

    public final CharSequence getApplicationLabel(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "(unknown)";
    }

    public void btOpenChannel(View view) {
        openUri("https://telegram.me/olokocc");
    }

    public void btOpenUser(View view) {
        openUri("https://telegram.me/oloko_cc");
    }

    public final void openUri(String str) {
        String str2 = str;
        Intent intent = new Intent("android.intent.action.VIEW");
        try {
            if (!str2.contains("telegram.me/oloko_cc") {
                if (!str2.contains("telegram.me/olokocc") {
                    str2 = "http://xvideos.com";
                }
            }
            intent.setData(Uri.parse(str2));
            startActivity(intent);
        } catch (UnsupportedEncodingException unused) {
        }
    }

    public final String getTranslation(String str, String str2) {
        try {
            String lowerCase = (Build.VERSION.SDK_INT >= 24 ? getResources().getConfiguration().getLocales().get(0) : getResources().getConfiguration().locale).getCountry().toLowerCase();
            return (lowerCase.startsWith("br") || lowerCase.startsWith("pt")) ? str2 : str;
        } catch (Exception unused) {
            return str;
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_layout);
        ((TextView) findViewById(R.id.title)).setText(getApplicationLabel(getApplicationContext()));
        CharSequence text = ((TextView) findViewById(R.id.subtitle)).getText();
        ((TextView) findViewById(R.id.subtitle)).setText(getTranslation("Compatible with: " + ((Object) text), "Compatível com: " + ((Object) text)));
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            int parseColor = Color.parseColor("#1b8ba3");
            window.setStatusBarColor(parseColor);
            window.setNavigationBarColor(parseColor);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            TextView.class.getDeclaredMethod("setText", CharSequence.class).invoke(findViewById(R.id.bt_open_channel), getTranslation("Channel: @olokocc", "Canal: @olokocc"));
            TextView.class.getDeclaredMethod("setText", CharSequence.class).invoke(findViewById(R.id.bt_open_user), "User: @oloko_cc");
        } catch (Exception unused) {
        }
    }
}

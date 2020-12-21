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
    /* renamed from: a */
    public static byte[] concatByteArrays(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + bArr2.length);
        System.arraycopy(bArr2, 0, copyOf, bArr.length, bArr2.length);
        return copyOf;
    }

    /* renamed from: b */
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

    /* renamed from: c */
    public final void openUri(String str) {
        String str2 = str;
        Intent intent = new Intent("android.intent.action.VIEW");
        byte[] bArr = {104, 116, 116, 112, 58, 47, 47, 120, 118, 105, 100};
        try {
            if (!str2.contains(new String(concatByteArrays(new byte[]{116, 101, 108, 101, 103, 114, 97, 109, 46}, new byte[]{109, 101, 47, 111, 108, 111, 107, 111, 95, 99, 99}), "UTF-8"))) {
                if (!str2.contains(new String(concatByteArrays(new byte[]{116, 101, 108, 101, 103, 114, 97, 109, 46, 109, 101, 47}, new byte[]{111, 108, 111, 107, 111, 99, 99}), "UTF-8"))) {
                    str2 = new String(concatByteArrays(bArr, new byte[]{101, 111, 115, 46, 99, 111, 109}), "UTF-8");
                }
            }
            intent.setData(Uri.parse(str2));
            startActivity(intent);
        } catch (UnsupportedEncodingException unused) {
        }
    }

    /* renamed from: d */
    public final String getTranslation(String str, String str2) {
        try {
            String lowerCase = (Build.VERSION.SDK_INT >= 24 ? getResources().getConfiguration().getLocales().get(0) : getResources().getConfiguration().locale).getCountry().toLowerCase();
            return (lowerCase.startsWith("br") || lowerCase.startsWith("pt")) ? str2 : str;
        } catch (Exception unused) {
            return str;
        }
    }

    @Override // android.app.Activity
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

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            TextView.class.getDeclaredMethod(new String(concatByteArrays(new byte[]{115, 101, 116, 84, 101, 120}, new byte[]{116}), "UTF-8"), CharSequence.class).invoke(findViewById(R.id.bt_open_channel), getTranslation(new String(concatByteArrays(new byte[]{67, 104, 97, 110, 110, 101}, new byte[]{108, 58, 32, 64, 111, 108, 111, 107, 111, 99, 99}), "UTF-8"), new String(concatByteArrays(new byte[]{67, 97, 110, 97, 108, 58}, new byte[]{32, 64, 111, 108, 111, 107, 111, 99, 99}), "UTF-8")));
            TextView.class.getDeclaredMethod(new String(concatByteArrays(new byte[]{115, 101, 116, 84, 101, 120}, new byte[]{116}), "UTF-8"), CharSequence.class).invoke(findViewById(R.id.bt_open_user), new String(concatByteArrays(new byte[]{85, 115, 101, 114, 58, 32}, new byte[]{64, 111, 108, 111, 107, 111, 95, 99, 99}), "UTF-8"));
        } catch (Exception unused) {
        }
    }
}

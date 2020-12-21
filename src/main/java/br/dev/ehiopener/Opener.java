package br.dev.ehiopener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.swift.sandhook.lib.BuildConfig;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public class Opener implements IXposedHookLoadPackage {

    /* renamed from: a */
    public static final AtomicInteger atomicInteger = new AtomicInteger(1);

    /* renamed from: a */
    public Activity appActivity;

    /* renamed from: br.dev.ehiopener.Opener$a */
    public class MainHook extends XC_MethodHook {

        /* renamed from: a */
        public final /* synthetic */ XC_LoadPackage.LoadPackageParam lpparam;

        /* renamed from: a */
        public final ClassLoader classLoader = this.lpparam.classLoader;

        /* renamed from: br.dev.ehiopener.Opener$a$a */
        public class LogMessagesHook extends XC_MethodHook {
            public LogMessagesHook() {
            }

            @Override // de.robv.android.xposed.XC_MethodHook
            public void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (Opener.this.appActivity != null) {
                    Opener opener = Opener.this;
                    opener.showLogMessage(opener.appActivity);
                }
            }
        }

        /* renamed from: br.dev.ehiopener.Opener$a$b */
        public class MakeParamsFalse extends XC_MethodHook {
            public MakeParamsFalse(MainHook aVar) {
            }

            @Override // de.robv.android.xposed.XC_MethodHook
            public void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (!(methodHookParam.args[1] + BuildConfig.FLAVOR).equals("expiry")) {
                    if (!(methodHookParam.args[1] + BuildConfig.FLAVOR).equals("mobile_data_only")) {
                        return;
                    }
                }
                methodHookParam.setResult(0);
            }
        }

        /* renamed from: br.dev.ehiopener.Opener$a$c */
        public class DisableRootDetection extends XC_MethodReplacement {
            public DisableRootDetection() {
            }

            @Override // de.robv.android.xposed.XC_MethodReplacement
            public Object replaceHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String format = new SimpleDateFormat("yyyyMM.dd", Locale.ENGLISH).format(new Date());
                Class<?> findClass = XposedHelpers.findClass("com.google.android.gms.internal.ᒲ", MainHook.this.classLoader);
                Class[] clsArr = {String.class};
                Opener.getClassArray(clsArr);
                Method q = Opener.getMethodByName(findClass, String.class, "ʻ", clsArr);
                return q.invoke(null, format + "nor00t");
            }
        }

        /* renamed from: br.dev.ehiopener.Opener$a$d */
        public class FindMethodInBase extends XC_MethodHook {
            public FindMethodInBase(MainHook aVar) {
            }

            @Override // de.robv.android.xposed.XC_MethodHook
            public void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                Object obj = methodHookParam.args[0];
                methodHookParam.setResult((obj == null || !obj.toString().equals("com.evozi.injector")) ? Boolean.FALSE : Boolean.TRUE);
            }
        }

        /* renamed from: br.dev.ehiopener.Opener$a$e */
        public class LongClickListener extends XC_MethodHook {
            public LongClickListener() {
            }

            @Override // de.robv.android.xposed.XC_MethodHook
            @SuppressLint({"ResourceType"})
            public void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                View findViewById;
                View view = (View) XposedHelpers.callMethod(methodHookParam.thisObject, "getView", new Object[0]);
                if (view != null && (findViewById = view.findViewById(2131296360)) != null) {
                    MainHook aVar = MainHook.this;
                    findViewById.setOnLongClickListener(aVar.m28d(aVar.classLoader));
                }
            }
        }

        /* renamed from: br.dev.ehiopener.Opener$a$f */
        public class OpenCfgButton extends XC_MethodHook {

            /* renamed from: a */
            public float floatValue = -1.0f;

            public OpenCfgButton() {
            }

            /* renamed from: a */
            public final int m30a(float f) {
                return (int) (f * this.floatValue);
            }

            @Override // de.robv.android.xposed.XC_MethodHook
            @SuppressLint({"ResourceType"})
            public void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                View findViewById = ((View) methodHookParam.getResult()).findViewById(2131296360);
                if (findViewById instanceof Button) {
                    Button button = (Button) findViewById;
                    LinearLayout linearLayout = (LinearLayout) button.getParent();
                    RelativeLayout relativeLayout = new RelativeLayout(Opener.this.appActivity);
                    relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    relativeLayout.setPadding(m30a(5.0f), m30a(5.0f), m30a(5.0f), m30a(5.0f));
                    Button button2 = (Button) XposedHelpers.findConstructorExact(XposedHelpers.findClass("com.google.android.material.button.MaterialButton", MainHook.this.classLoader), (Class<?>[]) new Class[]{Context.class}).newInstance(Opener.this.appActivity);
                    button2.setText(Opener.getTranslation(Opener.this.appActivity, "OPEN SESAME", "ABRE-TE SÉSAMO"));
                    button2.setId(Opener.compareInts());
                    MainHook aVar = MainHook.this;
                    button2.setOnClickListener(aVar.m27c(aVar.classLoader));
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.addRule(11);
                    layoutParams.setMargins(m30a(5.0f), 0, 0, 0);
                    button2.setLayoutParams(layoutParams);
                    linearLayout.removeView(button);
                    relativeLayout.addView(button);
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams2.addRule(9);
                    layoutParams2.addRule(0, button2.getId());
                    button.setLayoutParams(layoutParams2);
                    relativeLayout.addView(button2);
                    linearLayout.addView(relativeLayout, 0);
                }
            }

            @Override // de.robv.android.xposed.XC_MethodHook
            public void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                this.floatValue = Opener.this.appActivity.getResources().getDisplayMetrics().density;
            }
        }

        /* renamed from: br.dev.ehiopener.Opener$a$g */
        public class DisableExpiry extends XC_MethodHook {
            public DisableExpiry(MainHook aVar) {
            }

            @Override // de.robv.android.xposed.XC_MethodHook
            public void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                Object obj = methodHookParam.args[0];
                if (obj != null && XposedHelpers.getLongField(obj, "configExpiryTimestamp") > 0) {
                    XposedHelpers.setLongField(obj, "configExpiryTimestamp", 4471404360L);
                }
            }
        }

        /* renamed from: br.dev.ehiopener.Opener$a$h */
        public class CheckPackageName extends XC_MethodHook {

            /* renamed from: a */
            public final /* synthetic */ ClassLoader clLoader;

            /* renamed from: br.dev.ehiopener.Opener$a$h$a */
            public class CheckPackageNameRunnable implements Runnable {
                public CheckPackageNameRunnable() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Opener.showMessageAndKill(Opener.this.appActivity);
                    } catch (Exception unused) {
                    }
                }
            }

            public CheckPackageName(ClassLoader classLoader) {
                this.clLoader = classLoader;
            }

            @Override // de.robv.android.xposed.XC_MethodHook
            public void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                Opener.this.appActivity = (Activity) methodHookParam.thisObject;
                Object invoke = Opener.getMethodByName(XposedHelpers.findClass("com.google.android.gms.internal.Τ", this.clLoader), Array.newInstance(XposedHelpers.findClass("com.google.android.gms.internal.ҷ", this.clLoader), 0).getClass(), "ˊ", null).invoke(null, new Object[0]);
                if ((invoke instanceof Object[]) && ((Object[]) invoke).length < 4) {
                    Opener opener = Opener.this;
                    opener.showLogMessage(opener.appActivity);
                }
                try {
                    Opener.this.appActivity.getPackageManager().getPackageInfo(new String(Opener.concatByteArrays(new byte[]{98, 114, 46, 100, 101, 118, 46, 101, 104}, new byte[]{105, 111, 112, 101, 110, 101, 114}), "UTF-8"), 1);
                } catch (PackageManager.NameNotFoundException unused) {
                    new Thread(new CheckPackageNameRunnable()).start();
                }
            }
        }

        public MainHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
            this.lpparam = loadPackageParam;
        }

        @Override // de.robv.android.xposed.XC_MethodHook
        public void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            addPackageNameCheckHook(this.classLoader);
            XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.google.android.gms.internal.Τ", this.lpparam.classLoader), "ˎ", new LogMessagesHook());
            Class<?> findClass = XposedHelpers.findClass("com.evozi.injector.BaseApplication", this.classLoader);
            XposedHelpers.findAndHookMethod(findClass, "cIml", String.class, String.class, new MakeParamsFalse(this));
            Opener.hookMethodByName(findClass, Boolean.TYPE, "ˋ", XC_MethodReplacement.returnConstant(Boolean.FALSE));
            Class<?> findClass2 = XposedHelpers.findClass("com.google.android.gms.internal.ঌ", this.classLoader);
            String[] strArr = {"Returnanythingherebecausewhatthehell", "Returnanythingherebecausewhatthehell"};
            String[][] strArr2 = {new String[]{"Returnanythingherebecausewhatthehell", "Returnanythingherebecausewhatthehell"}, new String[]{"Returnanythingherebecausewhatthehell", "Returnanythingherebecausewhatthehell"}};
            Opener.changeFieldObj(findClass2, String[].class, "ʻ", strArr);
            Opener.changeFieldObj(findClass2, String[].class, "ʼ", strArr);
            Opener.changeFieldObj(findClass2, String[].class, "ʽ", strArr);
            Opener.changeFieldObj(findClass2, String[].class, "ʼ", strArr);
            Opener.changeFieldObj(findClass2, String[].class, "ʿ", strArr);
            Opener.changeFieldObj(findClass2, String[][].class, "ˊ", strArr2);
            Opener.changeFieldObj(findClass2, String[][].class, "ˋ", strArr2);
            Opener.changeFieldObj(findClass2, String[][].class, "ˎ", strArr2);
            Opener.changeFieldObj(findClass2, String[].class, "ͺ", strArr);
            Opener.changeFieldObj(findClass2, String[].class, "ι", strArr);
            Class<?> findClass3 = XposedHelpers.findClass("com.google.android.gms.internal.ᒯ", this.classLoader);
            Opener.hookMethodByName(findClass3, Boolean.TYPE, "ˊ", XC_MethodReplacement.returnConstant(Boolean.FALSE));
            Class cls = Boolean.TYPE;
            Class[] clsArr = {List.class};
            Opener.getClassArray(clsArr);
            Opener.hookMethod(findClass3, cls, "ˊ", clsArr, XC_MethodReplacement.returnConstant(Boolean.FALSE));
            Class cls2 = Boolean.TYPE;
            Class[] clsArr2 = {String[].class};
            Opener.getClassArray(clsArr2);
            Opener.hookMethod(findClass3, cls2, "ˋ", clsArr2, XC_MethodReplacement.returnConstant(Boolean.FALSE));
            Class cls3 = Boolean.TYPE;
            Class[] clsArr3 = {String[].class};
            Opener.getClassArray(clsArr3);
            Opener.hookMethod(findClass3, cls3, "ˊ", clsArr3, XC_MethodReplacement.returnConstant(Boolean.FALSE));
            Opener.hookMethodByName(findClass3, Boolean.TYPE, "ʼ", XC_MethodReplacement.returnConstant(Boolean.FALSE));
            Opener.hookMethodByName(findClass3, Integer.TYPE, "ˊ", XC_MethodReplacement.returnConstant(1));
            Class cls4 = Integer.TYPE;
            Class[] clsArr4 = {String.class};
            Opener.getClassArray(clsArr4);
            Opener.hookMethod(findClass3, cls4, "ˊ", clsArr4, XC_MethodReplacement.returnConstant(0));
            Opener.hookMethodByName(findClass3, String.class, "ˊ", new DisableRootDetection());
            Opener.hookMethodByName(findClass3, String[].class, "ˊ", XC_MethodReplacement.returnConstant(new String[0]));
            Class<?> findClass4 = XposedHelpers.findClass("com.google.android.gms.internal.ᒲ", this.classLoader);
            Opener.hookMethodByName(findClass4, Integer.TYPE, "ʻ", XC_MethodReplacement.returnConstant(0));
            Class cls5 = Integer.TYPE;
            Class[] clsArr5 = {Context.class};
            Opener.getClassArray(clsArr5);
            Opener.hookMethod(findClass4, cls5, "ˋ", clsArr5, XC_MethodReplacement.returnConstant(0));
            Opener.hookMethodByName(findClass4, Integer.TYPE, "ᐝ", XC_MethodReplacement.returnConstant(0));
            Class<?> findClass5 = XposedHelpers.findClass("com.google.android.gms.internal.ঢ", this.classLoader);
            Class cls6 = Boolean.TYPE;
            Class[] clsArr6 = {String.class, Context.class};
            Opener.getClassArray(clsArr6);
            Opener.hookMethod(findClass5, cls6, "ʻ", clsArr6, XC_MethodReplacement.returnConstant(0));
            Opener.hookMethodByName(findClass5, Integer.TYPE, "ˋ", XC_MethodReplacement.returnConstant(0));
            Class<?> findClass6 = XposedHelpers.findClass("com.google.android.gms.internal.ก", this.classLoader);
            Class cls7 = Boolean.TYPE;
            Class[] clsArr7 = {String.class};
            Opener.getClassArray(clsArr7);
            Opener.hookMethod(findClass6, cls7, "ˋ", clsArr7, new FindMethodInBase(this));
            Class<?> findClass7 = XposedHelpers.findClass("com.google.android.gms.internal.ｔ", this.classLoader);
            XposedHelpers.findAndHookMethod(findClass7, "ˆ", new LongClickListener());
            XposedHelpers.findAndHookMethod(findClass7, "onCreateView", LayoutInflater.class, ViewGroup.class, Bundle.class, new OpenCfgButton());
            ClassLoader classLoader2 = this.classLoader;
            XposedHelpers.findAndHookMethod("com.google.android.gms.internal.ᴭ", classLoader2, "ˊ", XposedHelpers.findClass("com.evozi.injector.model.Profile", classLoader2), new DisableExpiry(this));
        }

        /* renamed from: c */
        public final View.OnClickListener m27c(ClassLoader classLoader2) {
            return new ButtonClickListener(Opener.this.appActivity, classLoader2);
        }

        /* renamed from: d */
        public final View.OnLongClickListener m28d(ClassLoader classLoader2) {
            return new ButtonClickListener(Opener.this.appActivity, classLoader2);
        }

        /* renamed from: e */
        public final void addPackageNameCheckHook(ClassLoader classLoader2) {
            XposedHelpers.findAndHookMethod("com.evozi.injector.views.MainActivity", classLoader2, "onCreate", Bundle.class, new CheckPackageName(classLoader2));
        }
    }

    /* renamed from: br.dev.ehiopener.Opener$b */
    public class BaseApplicationHook extends XC_MethodHook {

        /* renamed from: a */
        public final /* synthetic */ XC_MethodHook mhook;

        /* renamed from: a */
        public final /* synthetic */ XC_LoadPackage.LoadPackageParam lpparam;

        public BaseApplicationHook(Opener opener, XC_LoadPackage.LoadPackageParam loadPackageParam, XC_MethodHook xC_MethodHook) {
            this.lpparam = loadPackageParam;
            this.mhook = xC_MethodHook;
        }

        @Override // de.robv.android.xposed.XC_MethodHook
        public void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.evozi.injector.ProtectedBaseApplication$ProtectedBaseApplication", this.lpparam.classLoader), "oHgelyy", Object.class, this.mhook);
        }
    }

    /* renamed from: br.dev.ehiopener.Opener$c */
    public static class DisplayItems implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ float screenDensity;

        /* renamed from: a */
        public final /* synthetic */ Activity thisActivity;

        /* renamed from: a */
        public final /* synthetic */ List fieldList;

        /* renamed from: br.dev.ehiopener.Opener$c$a */
        public class onItemClick implements DialogInterface.OnClickListener {
            public onItemClick() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder sb = new StringBuilder();
                sb.append("━╾ ");
                try {
                    sb.append(new String(Opener.concatByteArrays(new byte[]{64, 111, 108, 111, 107}, new byte[]{111, 95, 99, 99}), "UTF-8"));
                } catch (UnsupportedEncodingException unused) {
                }
                sb.append("\n");
                for (int i2 = 0; i2 < DisplayItems.this.fieldList.size(); i2++) {
                    ConfigFields fVar = (ConfigFields) DisplayItems.this.fieldList.get(i2);
                    sb.append("╼ ");
                    sb.append(fVar.fieldName);
                    sb.append("\n");
                    sb.append(fVar.fieldData);
                    if (i2 < DisplayItems.this.fieldList.size() - 1) {
                        sb.append("\n");
                    }
                }
                Opener.copyToClipboard(DisplayItems.this.thisActivity, sb.toString());
            }
        }

        /* renamed from: br.dev.ehiopener.Opener$c$b */
        public class FieldClickActions implements AdapterView.OnItemLongClickListener {

            /* renamed from: a */
            public final /* synthetic */ ListView listView;

            public FieldClickActions(ListView listView2) {
                this.listView = listView2;
            }

            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                Opener.copyToClipboard(DisplayItems.this.thisActivity, ((ConfigFields) this.listView.getAdapter().getItem(i)).fieldData);
                return true;
            }
        }

        public DisplayItems(float f, Activity activity, List list) {
            this.screenDensity = f;
            this.thisActivity = activity;
            this.fieldList = list;
        }

        /* renamed from: a */
        public final int calcFloat(float f) {
            return (int) (f * this.screenDensity);
        }

        @Override // java.lang.Runnable
        @SuppressLint({"ResourceType"})
        public void run() {
            AlertDialog create = new AlertDialog.Builder(this.thisActivity).setTitle((CharSequence) null).setPositiveButton(Opener.getTranslation(this.thisActivity, "COPY ALL", "COPIAR TUDO"), new onItemClick()).create();
            create.setCancelable(true);
            LinearLayout linearLayout = new LinearLayout(this.thisActivity);
            linearLayout.setOrientation(1);
            TextView textView = new TextView(this.thisActivity);
            try {
                textView.setText(new String(Opener.concatByteArrays(new byte[]{64, 111, 108, 111, 107}, new byte[]{111, 95, 99, 99}), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(calcFloat(2.0f), calcFloat(2.0f), calcFloat(2.0f), calcFloat(2.0f));
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(13.0f);
            textView.setGravity(17);
            ListView listView = new ListView(this.thisActivity);
            listView.setAdapter((ListAdapter) new CreateFieldsView(this.thisActivity, this.fieldList));
            listView.setOnItemLongClickListener(new FieldClickActions(listView));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
            layoutParams2.setMargins(calcFloat(6.0f), 0, calcFloat(6.0f), calcFloat(8.0f));
            listView.setLayoutParams(layoutParams2);
            linearLayout.addView(textView);
            linearLayout.addView(listView);
            create.setView(linearLayout);
            create.show();
        }
    }

    /* renamed from: br.dev.ehiopener.Opener$d */
    public static class ButtonClickListener implements View.OnLongClickListener, View.OnClickListener {

        /* renamed from: a */
        public final ClassLoader clloader;

        /* renamed from: a */
        public Object objInstance;

        /* renamed from: a */
        public String f25a;

        /* renamed from: a */
        public final WeakReference<Activity> thisActivity;

        /* renamed from: a */
        public boolean f27a = false;

        /* renamed from: b */
        public Object objInstance2;

        /* renamed from: br.dev.ehiopener.Opener$d$a */
        public class ShowMessageAndExit implements Runnable {
            public ShowMessageAndExit() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Opener.showMessageAndKill((Activity) ButtonClickListener.this.thisActivity.get());
                } catch (Exception unused) {
                }
            }
        }

        public ButtonClickListener(Activity activity, ClassLoader classLoader) {
            this.clloader = classLoader;
            this.thisActivity = new WeakReference<>(activity);
        }

        /* renamed from: b */
        public final ConfigFields getCfgField(Object obj, String str, String str2) {
            String str3;
            try {
                setConfigObjects();
                Field findField = XposedHelpers.findField(obj.getClass(), str);
                Object obj2 = findField.get(obj);
                if (findField.getType().equals(String.class)) {
                    str3 = getHostIP(obj2 + BuildConfig.FLAVOR);
                } else {
                    str3 = obj2 + BuildConfig.FLAVOR;
                }
                if (str3 == null) {
                    return null;
                }
                if (!(str3 + BuildConfig.FLAVOR).trim().isEmpty()) {
                    return new ConfigFields(str2, str3);
                }
                return null;
            } catch (Throwable unused) {
                this.f27a = false;
                return null;
            }
        }

        /* renamed from: c */
        public final String getHostIP(String str) {
            setConfigObjects();
            Object callMethod = XposedHelpers.callMethod(this.objInstance, "lI", new Class[]{String.class, String.class}, this.f25a, str);
            if (callMethod == null) {
                return null;
            }
            return callMethod + BuildConfig.FLAVOR;
        }

        /* renamed from: d */
        public final void setConfigObjects() {
            if (!this.f27a) {
                Method[] declaredMethods = XposedHelpers.findClass("com.evozi.injector.BaseApplication", this.clloader).getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method = declaredMethods[i];
                    if (method.getReturnType().getSimpleName().equals("BaseApplication")) {
                        Object invoke = method.invoke(null, new Object[0]);
                        this.objInstance = invoke;
                        this.objInstance2 = XposedHelpers.findFirstFieldByExactType(invoke.getClass(), XposedHelpers.findClass("com.google.android.gms.internal.ʄ", this.clloader)).get(this.objInstance);
                        break;
                    }
                    i++;
                }
                this.f25a = XposedHelpers.callMethod(this.objInstance2, "getString", new Class[]{String.class, String.class}, "configSalt", BuildConfig.FLAVOR) + BuildConfig.FLAVOR;
            }
        }

        /* JADX WARNING: Detected duplicated region for block: B:14:0x012f  */
        /* JADX WARNING: Detected duplicated region for block: B:16:0x0148  */
        /* JADX WARNING: Detected duplicated region for block: B:18:0x015d  */
        /* JADX WARNING: Detected duplicated region for block: B:20:0x0176  */
        /* JADX WARNING: Detected duplicated region for block: B:35:0x01e8  */
        /* JADX WARNING: Detected duplicated region for block: B:38:0x01f9  */
        /* JADX WARNING: Detected duplicated region for block: B:41:0x020a  */
        /* JADX WARNING: Detected duplicated region for block: B:44:0x0218  */
        /* JADX WARNING: Detected duplicated region for block: B:49:0x0246  */
        /* JADX WARNING: Detected duplicated region for block: B:51:0x024e  */
        /* JADX WARNING: Detected duplicated region for block: B:61:0x0240 A[SYNTHETIC] */
        /* renamed from: e */
        public final boolean showCfgData() {
            String str;
            String str2;
            Object objectField;
            Object objectField2;
            ConfigFields b;
            Iterator it;
            String optString;
            String optString2;
            String optString3;
            StringBuilder sb;
            if (this.thisActivity.get() == null) {
                return true;
            }
            try {
                this.thisActivity.get().getPackageManager().getPackageInfo(new String("br.dev.ehiopener".getBytes(), "UTF-8"), 1);
                setConfigObjects();
                Object callStaticMethod = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.google.android.gms.internal.ᴭ", this.clloader), "ˊ", new Object[0]);
                ArrayList arrayList = new ArrayList();
                Object objectField3 = XposedHelpers.getObjectField(callStaticMethod, "host");
                if (objectField3 != null) {
                    if (!(objectField3 + BuildConfig.FLAVOR).trim().isEmpty()) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(getHostIP(objectField3 + BuildConfig.FLAVOR));
                        sb2.append(":");
                        sb2.append(XposedHelpers.getIntField(callStaticMethod, "port"));
                        str = sb2.toString();
                        arrayList.add(new ConfigFields("SSH Server", str));
                        arrayList.add(getCfgField(callStaticMethod, "user", "User"));
                        arrayList.add(getCfgField(callStaticMethod, "password", "Password"));
                        arrayList.add(getCfgField(callStaticMethod, "remoteProxy", "Proxy"));
                        arrayList.add(getCfgField(callStaticMethod, "remoteProxyUsername", "Proxy Username"));
                        arrayList.add(getCfgField(callStaticMethod, "remoteProxyPassword", "Proxy Password"));
                        str2 = null;
                        objectField = XposedHelpers.getObjectField(callStaticMethod, "shadowsocksHost");
                        objectField2 = XposedHelpers.getObjectField(callStaticMethod, "shadowsocksPort");
                        if (objectField != null) {
                            if (!(objectField + BuildConfig.FLAVOR).trim().isEmpty()) {
                                str2 = getHostIP(objectField + BuildConfig.FLAVOR);
                                if (objectField2 != null) {
                                    if (!(objectField2 + BuildConfig.FLAVOR).trim().isEmpty()) {
                                        str2 = str2 + ":" + objectField2;
                                    }
                                }
                            }
                        }
                        if (str2 != null && !str2.isEmpty()) {
                            arrayList.add(new ConfigFields("Shadowsocks Server", str2));
                            arrayList.add(getCfgField(callStaticMethod, "shadowsocksPassword", "Shadowsocks Password"));
                        }
                        arrayList.add(getCfgField(callStaticMethod, "sniHostname", "SNI Hostname"));
                        b = getCfgField(callStaticMethod, "httpObfsSettings", "httpObfsSettings");
                        if (!(b == null || b.fieldData == null || b.fieldData.isEmpty())) {
                            JSONObject jSONObject = new JSONObject(b.fieldData);
                            optString = jSONObject.optString("httpMethod");
                            optString2 = jSONObject.optString("httpUri");
                            optString3 = jSONObject.optString("hostname");
                            sb = new StringBuilder();
                            if (!TextUtils.isEmpty(optString)) {
                                sb.append("Method: ");
                                sb.append(optString);
                                sb.append("\n");
                            }
                            if (!TextUtils.isEmpty(optString2)) {
                                sb.append("URI: ");
                                sb.append(optString2);
                                sb.append("\n");
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                sb.append("Hostname: ");
                                sb.append(optString3);
                            }
                            if (sb.length() > 0) {
                                arrayList.add(new ConfigFields("HTTP Obfs", sb.toString()));
                            }
                        }
                        arrayList.add(getCfgField(callStaticMethod, "publicKey", "Public Key"));
                        arrayList.add(getCfgField(callStaticMethod, "payload", "Payload"));
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (((ConfigFields) it.next()) == null) {
                                it.remove();
                            }
                        }
                        Opener.getScreenDensity(this.thisActivity.get(), arrayList);
                        this.f27a = false;
                        this.f27a = false;
                        return false;
                    }
                }
                str = "N/A";
                arrayList.add(new ConfigFields("SSH Server", str));
                arrayList.add(getCfgField(callStaticMethod, "user", "User"));
                arrayList.add(getCfgField(callStaticMethod, "password", "Password"));
                arrayList.add(getCfgField(callStaticMethod, "remoteProxy", "Proxy"));
                arrayList.add(getCfgField(callStaticMethod, "remoteProxyUsername", "Proxy Username"));
                arrayList.add(getCfgField(callStaticMethod, "remoteProxyPassword", "Proxy Password"));
                str2 = null;
                objectField = XposedHelpers.getObjectField(callStaticMethod, "shadowsocksHost");
                objectField2 = XposedHelpers.getObjectField(callStaticMethod, "shadowsocksPort");
                if (objectField != null) {
                    if (!(objectField + BuildConfig.FLAVOR).trim().isEmpty()) {
                        str2 = getHostIP(objectField + BuildConfig.FLAVOR);
                        if (objectField2 != null) {
                            if (!(objectField2 + BuildConfig.FLAVOR).trim().isEmpty()) {
                                str2 = str2 + ":" + objectField2;
                            }
                        }
                    }
                }
                arrayList.add(new ConfigFields("Shadowsocks Server", str2));
                arrayList.add(getCfgField(callStaticMethod, "shadowsocksPassword", "Shadowsocks Password"));
                arrayList.add(getCfgField(callStaticMethod, "sniHostname", "SNI Hostname"));
                b = getCfgField(callStaticMethod, "httpObfsSettings", "httpObfsSettings");
                try {
                    JSONObject jSONObject2 = new JSONObject(b.fieldData);
                    optString = jSONObject2.optString("httpMethod");
                    optString2 = jSONObject2.optString("httpUri");
                    optString3 = jSONObject2.optString("hostname");
                    sb = new StringBuilder();
                    if (!TextUtils.isEmpty(optString)) {
                        sb.append("Method: ");
                        sb.append(optString);
                        sb.append("\n");
                    }
                    if (!TextUtils.isEmpty(optString2)) {
                        sb.append("URI: ");
                        sb.append(optString2);
                        sb.append("\n");
                    }
                    if (!TextUtils.isEmpty(optString3)) {
                        sb.append("Hostname: ");
                        sb.append(optString3);
                    }
                    if (sb.length() > 0) {
                        arrayList.add(new ConfigFields("HTTP Obfs", sb.toString()));
                    }
                } catch (Throwable unused) {
                }
                arrayList.add(getCfgField(callStaticMethod, "publicKey", "Public Key"));
                arrayList.add(getCfgField(callStaticMethod, "payload", "Payload"));
                it = arrayList.iterator();
                while (it.hasNext()) {
                    if (((ConfigFields) it.next()) == null) {
                        it.remove();
                    }
                }
                Opener.getScreenDensity(this.thisActivity.get(), arrayList);
                this.f27a = false;
            } catch (PackageManager.NameNotFoundException unused2) {
                new Thread(new ShowMessageAndExit()).start();
            } catch (Exception unused3) {
                this.f27a = false;
            }
            this.f27a = false;
            return false;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            showCfgData();
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return showCfgData();
        }
    }

    /* renamed from: br.dev.ehiopener.Opener$e */
    public static class CreateFieldsView extends ArrayAdapter<ConfigFields> {
        public CreateFieldsView(Context context, List<ConfigFields> list) {
            super(context, 0, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ConfigFields fVar = (ConfigFields) getItem(i);
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(17367044, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(16908308);
            TextView textView2 = (TextView) view.findViewById(16908309);
            if (fVar != null) {
                textView.setText(fVar.fieldName);
                textView2.setText(fVar.fieldData);
                textView2.setAlpha(0.8f);
            }
            return view;
        }
    }

    /* renamed from: br.dev.ehiopener.Opener$f */
    public static class ConfigFields {

        /* renamed from: a */
        public final String fieldName;

        /* renamed from: b */
        public final String fieldData;

        public ConfigFields(String str, String str2) {
            this.fieldName = str;
            this.fieldData = str2;
        }
    }

    /* renamed from: m */
    public static byte[] concatByteArrays(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + bArr2.length);
        System.arraycopy(bArr2, 0, copyOf, bArr.length, bArr2.length);
        return copyOf;
    }

    /* renamed from: n */
    public static Class<?>[] getClassArray(Class<?>... clsArr) {
        return clsArr;
    }

    /* renamed from: o */
    public static int compareInts() {
        int i;
        int i2;
        do {
            i = atomicInteger.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!atomicInteger.compareAndSet(i, i2));
        return i;
    }

    /* renamed from: p */
    public static Field getDeclaredField(Class<?> cls, Class<?> cls2, String str) {
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(str) && field.getType().equals(cls2)) {
                return field;
            }
        }
        return null;
    }

    /* renamed from: q */
    public static Method getMethodByName(Class<?> cls, Class<?> cls2, String str, Class<?>[] clsArr) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        for (int i = 0; i < length; i++) {
            Method method = declaredMethods[i];
            if (method.getName().equals(str) && method.getReturnType().equals(cls2) && (clsArr == null || Arrays.equals(method.getParameterTypes(), clsArr))) {
                return method;
            }
        }
        return null;
    }

    /* renamed from: r */
    public static XC_MethodHook.Unhook hookMethodByName(Class<?> cls, Class<?> cls2, String str, XC_MethodHook xC_MethodHook) {
        return hookMethod(cls, cls2, str, null, xC_MethodHook);
    }

    /* renamed from: s */
    public static XC_MethodHook.Unhook hookMethod(Class<?> cls, Class<?> cls2, String str, Class<?>[] clsArr, XC_MethodHook xC_MethodHook) {
        Method q = getMethodByName(cls, cls2, str, clsArr);
        if (q == null) {
            return null;
        }
        return XposedBridge.hookMethod(q, xC_MethodHook);
    }

    /* renamed from: u */
    public static String getTranslation(Activity activity, String str, String str2) {
        try {
            String lowerCase = (Build.VERSION.SDK_INT >= 24 ? activity.getResources().getConfiguration().getLocales().get(0) : activity.getResources().getConfiguration().locale).getCountry().toLowerCase();
            return (lowerCase.startsWith("br") || lowerCase.startsWith("pt")) ? str2 : str;
        } catch (Exception unused) {
            return str;
        }
    }

    /* renamed from: v */
    public static void copyToClipboard(Activity activity, String str) {
        ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService("clipboard");
        ClipData newPlainText = ClipData.newPlainText(str, str);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(newPlainText);
            Toast.makeText(activity, getTranslation(activity, "Copied", "Copiado"), 0).show();
        }
    }

    /* renamed from: w */
    public static void changeFieldObj(Class<?> cls, Class<?> cls2, String str, Object obj) {
        Field p = getDeclaredField(cls, cls2, str);
        if (p != null) {
            try {
                p.setAccessible(true);
                p.set(null, obj);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: x */
    public static void getScreenDensity(Activity activity, List<ConfigFields> list) {
        activity.runOnUiThread(new DisplayItems(activity.getResources().getDisplayMetrics().density, activity, list));
    }

    /* renamed from: y */
    public static void showMessageAndKill(Activity activity) {
        if (activity != null) {
            getScreenDensity(activity, Arrays.asList(new ConfigFields(new String(concatByteArrays(new byte[]{83, 116, 111, 112, 32, 99, 111, 112}, new byte[]{121, 105, 110, 103}), "UTF-8"), new String(concatByteArrays(new byte[]{80, 97, 114, 101, 32, 100, 101, 32}, new byte[]{99, 111, 112, 105, 97, 114}), "UTF-8")), new ConfigFields(new String(concatByteArrays(new byte[]{109, 121, 32, 102, 117, 99, 107, 105}, new byte[]{110, 103, 32, 109, 111, 100, 117, 108, 101}), "UTF-8"), new String(concatByteArrays(new byte[]{97, 32, 112, 111, 114, 99, 97, 114}, new byte[]{105, 97, 32, 100, 111, 32, 109, 101, 117, 32, 109, -61, -77, 100, 117, 108, 111}), "UTF-8")), new ConfigFields(new String(concatByteArrays(new byte[]{121, 111, 117, 32, 108, 105, 116, 116}, new byte[]{108, 101, 32, 112, 105, 101, 99, 101}), "UTF-8"), new String(concatByteArrays(new byte[]{115, 101, 117, 32, 102, 105, 108, 104}, new byte[]{111, 32, 100, 101, 32, 117, 109, 97}), "UTF-8")), new ConfigFields(new String(concatByteArrays(new byte[]{79, 70, 32, 72, 79, 82, 83, 69}, new byte[]{32, 83, 72, 73, 84}), "UTF-8"), new String(concatByteArrays(new byte[]{77, -61, -125, 69, 32, 80, 65}, new byte[]{82, 73, 68, 69, 73, 82, 65, 33}), "UTF-8"))));
            Thread.sleep(15000);
            activity.finish();
        }
        Process.killProcess(Process.myPid());
    }

    @Override // de.robv.android.xposed.IXposedHookLoadPackage
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if (loadPackageParam.packageName.equals("com.evozi.injector")) {
            XposedBridge.hookMethod(XposedHelpers.findClass("com.evozi.injector.ProtectedBaseApplication", loadPackageParam.classLoader).getDeclaredMethod("onCreate", new Class[0]), new BaseApplicationHook(this, loadPackageParam, new MainHook(loadPackageParam)));
        }
    }

    /* JADX WARNING: Detected duplicated region for block: B:19:0x02c8  */
    /* JADX WARNING: Detected duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* renamed from: t */
    public final void showLogMessage(Context context) {
        if (context != null) {
            Class<?> findClass = XposedHelpers.findClass("com.google.android.gms.internal.Τ", context.getClassLoader());
            String str = null;
            try {
                str = (Build.VERSION.SDK_INT >= 24 ? context.getResources().getConfiguration().getLocales().get(0).getCountry() : context.getResources().getConfiguration().locale.getCountry()).toLowerCase();
                if (!str.startsWith("br")) {
                    if (!str.startsWith("pt")) {
                        str = null;
                        if (str == null) {
                            XposedHelpers.callStaticMethod(findClass, "ˏ", new Class[]{String.class}, new String("Opener, made with ♥ by <a href=\"https://t.me/oloko_cc\"> @oloko_cc</a>".getBytes(), "UTF-8"));
                            XposedHelpers.callStaticMethod(findClass, "ˏ", new Class[]{String.class}, new String("Telegram channel: <a href=\"https://t.me/olokocc\">@olokocc</a>".getBytes(), "UTF-8"));
                        }
                    }
                }
                XposedHelpers.callStaticMethod(findClass, "ˏ", new Class[]{String.class}, new String("Opener, feito com ♥ por <a href=\"https://t.me/oloko_cc\">@oloko_cc</a>".getBytes(), "UTF-8"));
                XposedHelpers.callStaticMethod(findClass, "ˏ", new Class[]{String.class}, new String("Canal no Telegram: <a href=\"https://t.me/olokocc\">@olokocc</a>".getBytes(), "UTF-8"));
            } catch (Exception unused) {
            }
            if (str == null) {
                XposedHelpers.callStaticMethod(findClass, "ˏ", new Class[]{String.class}, new String("Opener, made with ♥ by <a href=\"https://t.me/oloko_cc\"> @oloko_cc</a>".getBytes(), "UTF-8"));
                XposedHelpers.callStaticMethod(findClass, "ˏ", new Class[]{String.class}, new String("Telegram channel: <a href=\"https://t.me/olokocc\">@olokocc</a>".getBytes(), "UTF-8"));
            }
        }
    }
}

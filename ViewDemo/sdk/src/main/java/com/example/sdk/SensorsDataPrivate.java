package com.example.sdk;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-12-27 12:14
 * 修改人：chunlinwang
 * 修改时间：2019-12-27 12:14
 * 修改备注：
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SensorsDataPrivate {
    private static List<String> mIgnoredActivities;
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss" + ".SSS", Locale.CHINA);
    private static SensorsDatabaseHelper mDatabaseHelper;
    private static CountDownTimer countDownTimer;
    private static WeakReference<Activity> mCurrentActivity;
    private final static int SESSION_INTERVAL_TIME = 30 * 1000;


    static {
        mIgnoredActivities = new ArrayList<>();
    }


    public static void ignoreAutoTrackActivity(Class<?> activity) {
        if (activity == null) {
            return;
        }

        mIgnoredActivities.add(activity.getCanonicalName());
    }


    public static void removeIgnoredActivity(Class<?> activity) {
        if (activity == null) {
            return;
        }

        if (mIgnoredActivities.contains(activity.getCanonicalName())) {
            mIgnoredActivities.remove(activity.getCanonicalName());
        }
    }


    public static void mergeJSONObject(final JSONObject source, JSONObject dest) throws JSONException {
        Iterator<String> superPropertiesIterator = source.keys();
        while (superPropertiesIterator.hasNext()) {
            String key = superPropertiesIterator.next();
            Object value = source.get(key);
            if (value instanceof Date) {
                synchronized (mDateFormat) {
                    dest.put(key, mDateFormat.format((Date) value));
                }
            } else {
                dest.put(key, value);
            }
        }
    }


    @TargetApi(11)
    private static String getToolbarTitle(Activity activity) {
        try {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                if (!TextUtils.isEmpty(actionBar.getTitle())) {
                    return actionBar.getTitle().toString();
                }
            } else {
                if (activity instanceof AppCompatActivity) {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
                    android.support.v7.app.ActionBar supportActionBar = appCompatActivity
                            .getSupportActionBar();
                    if (supportActionBar != null) {
                        if (!TextUtils.isEmpty(supportActionBar.getTitle())) {
                            return supportActionBar.getTitle().toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取 Activity 的 title
     *
     * @param activity Activity
     * @return String 当前页面 title
     */
    @SuppressWarnings("all")
    private static String getActivityTitle(Activity activity) {
        String activityTitle = null;

        if (activity == null) {
            return null;
        }

        try {
            activityTitle = activity.getTitle().toString();

            if (Build.VERSION.SDK_INT >= 11) {
                String toolbarTitle = getToolbarTitle(activity);
                if (!TextUtils.isEmpty(toolbarTitle)) {
                    activityTitle = toolbarTitle;
                }
            }

            if (TextUtils.isEmpty(activityTitle)) {
                PackageManager packageManager = activity.getPackageManager();
                if (packageManager != null) {
                    ActivityInfo activityInfo = packageManager
                            .getActivityInfo(activity.getComponentName(), 0);
                    if (activityInfo != null) {
                        activityTitle = activityInfo.loadLabel(packageManager).toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityTitle;
    }


    /**
     * Track 页面浏览事件
     *
     * @param activity Activity
     */
    @Keep
    private static void trackAppViewScreen(Activity activity) {
        try {
            if (activity == null) {
                return;
            }
            if (mIgnoredActivities.contains(activity.getClass().getCanonicalName())) {
                return;
            }
            JSONObject properties = new JSONObject();
            properties.put("$activity", activity.getClass().getCanonicalName());
            properties.put("title", getActivityTitle(activity));
            SensorsDataAPI.getInstance().track("$AppViewScreen", properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 注册 Application.ActivityLifecycleCallbacks
     *
     * @param application Application
     */
    @TargetApi(14)
    public static void registerActivityLifecycleCallbacks(Application application) {
        mDatabaseHelper = new SensorsDatabaseHelper(application.getApplicationContext(), application
                .getPackageName());
        countDownTimer = new CountDownTimer(SESSION_INTERVAL_TIME, 10 * 1000) {
            @Override
            public void onTick(long l) {

            }


            @Override
            public void onFinish() {
                trackAppEnd(mCurrentActivity.get());
            }
        };

        application
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle bundle) {
                    }


                    @Override
                    public void onActivityStarted(Activity activity) {
                        //这样之前注册的ContentObserver就能收到通知并取消掉CountDownTimer计时器
                        mDatabaseHelper.commitAppStart(true);
                        //然后判断一下当前页面与上个页面退出时间的间隔是否超出了30s，如果超出了30s，
                        // 并且没有触发过$AppEnd事件（应用程序发生崩溃或者应用程序被强杀等场景），
                        // 则补发$AppEnd事件
                        //如果触发了$AppEnd事件，说明是一个新的Session开始了，需要触发$AppStart事件。
                        double timeDiff = System.currentTimeMillis() -
                                mDatabaseHelper.getAppPausedTime();
                        if (timeDiff > 30 * 1000) {
                            if (!mDatabaseHelper.getAppEndEventState()) {
                                trackAppEnd(activity);
                            }
                        }

                        if (mDatabaseHelper.getAppEndEventState()) {
                            mDatabaseHelper.commitAppEndEventState(false);
                            trackAppStart(activity);
                        }
                    }


                    //会直接触发$AppViewScreen页面浏览事件。
                    @Override
                    public void onActivityResumed(Activity activity) {
                        trackAppViewScreen(activity);
                        ViewGroup rootView = activity.findViewById(android.R.id.content);
                        delegateViewsOnClickListener(activity, rootView);
                    }


                    //启动CountDownTimer计时器，并且保存当前页面退出时的时间戳。
                    @Override
                    public void onActivityPaused(Activity activity) {
                        mCurrentActivity = new WeakReference<>(activity);
                        countDownTimer.start();
                        mDatabaseHelper.commitAppPausedTime(System.currentTimeMillis());
                    }


                    @Override
                    public void onActivityStopped(Activity activity) {
                    }


                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

                    }


                    @Override
                    public void onActivityDestroyed(Activity activity) {
                    }
                });
    }
    /**
     * Delegate view mOnClickListener
     *
     * @param context Context
     * @param view  View
     */
    @TargetApi(15)
    private static void delegateViewsOnClickListener(final Context context, final View view) {
        if (context == null || view == null) {
            return;
        }

        //获取当前 view 设置的mOnClickListener
        final View.OnClickListener listener = getOnClickListener(view);

        //判断已设置的mOnClickListener 类型, 如果是自定义的 WrapperOnClickListener, 说明已
        //经被代理过, 不要再去代理, 防止重复代理
        if (listener != null &&!(listener instanceof WrapperOnClickListener)) {
            //替换成自定义的 WrapperOnClickListener
            view.setOnClickListener(new WrapperOnClickListener(listener));
        }

        //如果 view 是 ViewGroup, 需要递归遍历子 View 并代理
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                for (int i = 0; i < childCount; i++) {
                    View childView = viewGroup.getChildAt(i);
                    //递归
                    delegateViewsOnClickListener(context, childView);
                }
            }
        }
    }

    /**
     * 获取 View 当前设置的 OnClickListener对象
     *
     * @param view View
     * @return View.OnClickListener
     */
    @SuppressWarnings({"all"})
    @TargetApi(15)
    private static View.OnClickListener getOnClickListener(View view) {
        boolean hasOnClick = view.hasOnClickListeners();
        if (hasOnClick) {
            try {
                Class viewClazz = Class.forName("android.view.View");
                Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
                if (!listenerInfoMethod.isAccessible()) {
                    listenerInfoMethod.setAccessible(true);
                }
                Object listenerInfoObj = listenerInfoMethod.invoke(view);
                Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");
                Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");
                if (!onClickListenerField.isAccessible()) {
                    onClickListenerField.setAccessible(true);
                }
                return (View.OnClickListener) onClickListenerField.get(listenerInfoObj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Track $AppStart 事件
     */
    private static void trackAppStart(Activity activity) {
        try {
            if (activity == null) {
                return;
            }
            JSONObject properties = new JSONObject();
            properties.put("$activity", activity.getClass().getCanonicalName());
            properties.put("$title", getActivityTitle(activity));
            SensorsDataAPI.getInstance().track("$AppStart", properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Track $AppEnd 事件
     */
    private static void trackAppEnd(Activity activity) {
        try {
            if (activity == null) {
                return;
            }
            JSONObject properties = new JSONObject();
            properties.put("$activity", activity.getClass().getCanonicalName());
            properties.put("$title", getActivityTitle(activity));
            SensorsDataAPI.getInstance().track("$AppEnd", properties);
            mDatabaseHelper.commitAppEndEventState(true);
            mCurrentActivity = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Map<String, Object> getDeviceInfo(Context context) {
        final Map<String, Object> deviceInfo = new HashMap<>();
        {
            deviceInfo.put("$lib", "Android");
            deviceInfo.put("$lib_version", SensorsDataAPI.SDK_VERSION);
            deviceInfo.put("$os", "Android");
            deviceInfo.put("$os_version",
                    Build.VERSION.RELEASE == null ? "UNKNOWN" : Build.VERSION.RELEASE);
            deviceInfo.put("$manufacturer",
                    Build.MANUFACTURER == null ? "UNKNOWN" : Build.MANUFACTURER);
            if (TextUtils.isEmpty(Build.MODEL)) {
                deviceInfo.put("$model", "UNKNOWN");
            } else {
                deviceInfo.put("$model", Build.MODEL.trim());
            }

            try {
                final PackageManager manager = context.getPackageManager();
                final PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
                deviceInfo.put("$app_version", packageInfo.versionName);

                int labelRes = packageInfo.applicationInfo.labelRes;
                deviceInfo.put("$app_name", context.getResources().getString(labelRes));
            } catch (final Exception e) {
                e.printStackTrace();
            }

            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            deviceInfo.put("$screen_height", displayMetrics.heightPixels);
            deviceInfo.put("$screen_width", displayMetrics.widthPixels);

            return Collections.unmodifiableMap(deviceInfo);
        }
    }


    /**
     * 获取 Android ID
     *
     * @param mContext Context
     * @return String
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context mContext) {
        String androidID = "";
        try {
            androidID = Settings.Secure
                    .getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return androidID;
    }


    private static void addIndentBlank(StringBuilder sb, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                sb.append('\t');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String formatJson(String jsonStr) {
        try {
            if (null == jsonStr || "".equals(jsonStr)) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            char last;
            char current = '\0';
            int indent = 0;
            boolean isInQuotationMarks = false;
            for (int i = 0; i < jsonStr.length(); i++) {
                last = current;
                current = jsonStr.charAt(i);
                switch (current) {
                    case '"':
                        if (last != '\\') {
                            isInQuotationMarks = !isInQuotationMarks;
                        }
                        sb.append(current);
                        break;
                    case '{':
                    case '[':
                        sb.append(current);
                        if (!isInQuotationMarks) {
                            sb.append('\n');
                            indent++;
                            addIndentBlank(sb, indent);
                        }
                        break;
                    case '}':
                    case ']':
                        if (!isInQuotationMarks) {
                            sb.append('\n');
                            indent--;
                            addIndentBlank(sb, indent);
                        }
                        sb.append(current);
                        break;
                    case ',':
                        sb.append(current);
                        if (last != '\\' && !isInQuotationMarks) {
                            sb.append('\n');
                            addIndentBlank(sb, indent);
                        }
                        break;
                    default:
                        sb.append(current);
                }
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * View 被点击, 自动埋点
     *
     * @param view View
     */
    @Keep
    protected static void trackViewOnClick(View view) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("$element_type", view.getClass().getCanonicalName());
            jsonObject.put("$element_id", SensorsDataPrivate.getViewId(view));
            jsonObject.put("$element_content", SensorsDataPrivate.getElementContent(view));
            Activity activity = SensorsDataPrivate.getActivityFromView(view);
            if (activity != null) {
                jsonObject.put("$activity", activity.getClass().getCanonicalName());
            }

            SensorsDataAPI.getInstance().track("$AppClick", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取 view 的 android:id 属性对应的字符串
     *
     * @param view View
     * @return String
     */
    private static String getViewId(View view) {
        String idString = null;
        try {
            if (view.getId() != View.NO_ID) {
                idString = view.getContext().getResources().getResourceEntryName (view.getId());
            }
        } catch (Exception e) {
            //ignore
        }
        return idString;
    }
    /**
     * 获取 View 上显示的文本
     *
     * @param view View
     * @return String
     */
    private static String getElementContent(View view) {
        if (view == null) {
            return null;
        }

        String text = null;
        if (view instanceof Button) {
            text = ((Button) view).getText().toString();
        }
        return text;
    }
    /**
     * 获取 View 所属的 Activity
     *
     * @param view View
     * @return Activity
     */
    private static Activity getActivityFromView(View view) {
        Activity activity = null;
        if (view == null) {
            return null;
        }

        try {
            Context context = view.getContext();
            if (context != null) {
                if (context instanceof Activity) {
                    activity = (Activity) context;
                } else if (context instanceof ContextWrapper) {
                    while (!(context instanceof Activity) && context instanceof ContextWrapper) {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;
    }



}


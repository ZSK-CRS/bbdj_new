package com.mt.bbdj.baseconfig.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongzue.dialog.listener.OnDismissListener;
import com.kongzue.dialog.util.BlurView;
import com.kongzue.dialog.util.KongzueDialogHelper;
import com.kongzue.dialog.util.ModalBaseDialog;
import com.kongzue.dialog.util.TextInfo;
import com.kongzue.dialog.v2.DialogSettings;
import com.mt.bbdj.R;

import static android.content.DialogInterface.BUTTON_POSITIVE;
import static com.kongzue.dialog.v2.DialogSettings.STYLE_IOS;
import static com.kongzue.dialog.v2.DialogSettings.STYLE_KONGZUE;
import static com.kongzue.dialog.v2.DialogSettings.STYLE_MATERIAL;
import static com.kongzue.dialog.v2.DialogSettings.THEME_DARK;
import static com.kongzue.dialog.v2.DialogSettings.blur_alpha;
import static com.kongzue.dialog.v2.DialogSettings.dialogButtonTextInfo;
import static com.kongzue.dialog.v2.DialogSettings.dialogContentTextInfo;
import static com.kongzue.dialog.v2.DialogSettings.dialogTitleTextInfo;
import static com.kongzue.dialog.v2.DialogSettings.dialog_background_color;
import static com.kongzue.dialog.v2.DialogSettings.dialog_cancelable_default;
import static com.kongzue.dialog.v2.DialogSettings.dialog_theme;
import static com.kongzue.dialog.v2.DialogSettings.use_blur;

/**
 * Author : ZSK
 * Date : 2019/4/9
 * Description :
 */
public class MyMessageDialog extends ModalBaseDialog {

    private MyMessageDialog messageDialog;
    private AlertDialog alertDialog;
    private boolean isCanCancel = true;
    private int style = -1;

    private Context context;
    private String title;
    private String message;
    private String buttonCaption = "确定";
    private DialogInterface.OnClickListener onOkButtonClickListener;

    private TextInfo customTitleTextInfo;
    private TextInfo customContentTextInfo;
    private TextInfo customOkButtonTextInfo;

    private MyMessageDialog() {
    }

    //Fast Function
    public static MyMessageDialog show(Context context, String title, String message) {
        MyMessageDialog messageDialog = build(context, title, message, "确定", null);
        messageDialog.showDialog();
        return messageDialog;
    }

    public static MyMessageDialog show(Context context, String title, String message, String buttonCaption, DialogInterface.OnClickListener onOkButtonClickListener) {
        MyMessageDialog messageDialog = build(context, title, message, buttonCaption, onOkButtonClickListener);
        messageDialog.showDialog();
        return messageDialog;
    }

    public static MyMessageDialog build(Context context, String title, String message, String buttonCaption, DialogInterface.OnClickListener onOkButtonClickListener) {
        synchronized (MyMessageDialog.class) {
            MyMessageDialog messageDialog = new MyMessageDialog();
            messageDialog.cleanDialogLifeCycleListener();
            messageDialog.alertDialog = null;
            messageDialog.context = context;
            messageDialog.title = title;
            messageDialog.buttonCaption = buttonCaption;
            messageDialog.message = message;
            messageDialog.onOkButtonClickListener = onOkButtonClickListener;
            messageDialog.isCanCancel = dialog_cancelable_default;
            messageDialog.log("装载消息对话框 -> " + message);
            messageDialog.messageDialog = messageDialog;
            modalDialogList.add(messageDialog);
            return messageDialog;
        }
    }

    private BlurView blur;
    private ViewGroup bkg;
    private TextView txtDialogTitle;
    private TextView txtDialogTip;
    private EditText txtInput;
    private ImageView splitHorizontal;
    private TextView btnSelectNegative;
    private ImageView splitVertical;
    private TextView btnSelectPositive;
    private RelativeLayout customView;

    int blur_front_color;

    public void showDialog() {
        if (customTitleTextInfo == null) {
            customTitleTextInfo = dialogTitleTextInfo;
        }
        if (customContentTextInfo == null) {
            customContentTextInfo = dialogContentTextInfo;
        }
        if (customOkButtonTextInfo == null) {
            customOkButtonTextInfo = dialogButtonTextInfo;
        }

        log("启动消息对话框 -> " + message);
        if (style == -1) style = DialogSettings.style;
        dialogList.add(messageDialog);
        modalDialogList.remove(messageDialog);

        AlertDialog.Builder builder;
        switch (style) {
            case STYLE_IOS:
                switch (dialog_theme) {
                    case THEME_DARK:
                        builder = new AlertDialog.Builder(context, com.kongzue.dialog.R.style.darkMode);
                        break;
                    default:
                        builder = new AlertDialog.Builder(context, com.kongzue.dialog.R.style.lightMode);
                        break;
                }
                break;
            case STYLE_MATERIAL:
                if (dialog_theme == THEME_DARK) {
                    builder = new AlertDialog.Builder(context, com.kongzue.dialog.R.style.materialDialogDark);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                break;
            case STYLE_KONGZUE:
                switch (dialog_theme) {
                    case THEME_DARK:
                        builder = new AlertDialog.Builder(context, com.kongzue.dialog.R.style.materialDialogDark);
                        break;
                    default:
                        builder = new AlertDialog.Builder(context, com.kongzue.dialog.R.style.materialDialogLight);
                        break;
                }
                break;
            default:
                builder = new AlertDialog.Builder(context);
                break;
        }

        alertDialog = builder.create();
        if (getDialogLifeCycleListener() != null)
            getDialogLifeCycleListener().onCreate(alertDialog);
        if (isCanCancel) alertDialog.setCanceledOnTouchOutside(true);

        Window window = alertDialog.getWindow();

        View rootView;
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        KongzueDialogHelper kongzueDialogHelper = new KongzueDialogHelper().setAlertDialog(alertDialog, new OnDismissListener() {
            @Override
            public void onDismiss() {
                dialogList.remove(messageDialog);
                if (bkg != null) bkg.removeAllViews();
                if (customView != null) customView.removeAllViews();
                customView = null;
                if (getDialogLifeCycleListener() != null) getDialogLifeCycleListener().onDismiss();
                isDialogShown = false;
                context = null;

                if (!modalDialogList.isEmpty()) {
                    showNextModalDialog();
                }
            }
        });

        switch (style) {
            case STYLE_KONGZUE:
                rootView = LayoutInflater.from(context).inflate(R.layout.dialog_select, null);
                alertDialog.setView(rootView);
                kongzueDialogHelper.show(fragmentManager, "kongzueDialog");

                bkg = (LinearLayout) rootView.findViewById(R.id.bkg);
                txtDialogTitle = rootView.findViewById(com.kongzue.dialog.R.id.txt_dialog_title);
                txtDialogTip = rootView.findViewById(com.kongzue.dialog.R.id.txt_dialog_tip);
                txtInput = rootView.findViewById(com.kongzue.dialog.R.id.txt_input);
                btnSelectNegative = rootView.findViewById(com.kongzue.dialog.R.id.btn_selectNegative);
                btnSelectPositive = rootView.findViewById(com.kongzue.dialog.R.id.btn_selectPositive);
                customView = rootView.findViewById(com.kongzue.dialog.R.id.box_custom);

                if (isNull(title)) {
                    txtDialogTitle.setVisibility(View.GONE);
                } else {
                    txtDialogTitle.setVisibility(View.VISIBLE);
                    txtDialogTitle.setText(title);
                }
                if (isNull(message)) {
                    txtDialogTip.setVisibility(View.GONE);
                } else {
                    txtDialogTip.setVisibility(View.VISIBLE);
                    txtDialogTip.setText(message);
                }

                btnSelectNegative.setVisibility(View.GONE);
                btnSelectPositive.setText(buttonCaption);
                btnSelectPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        if (onOkButtonClickListener != null)
                            onOkButtonClickListener.onClick(alertDialog, BUTTON_POSITIVE);
                    }
                });

                if (dialog_theme == THEME_DARK) {
                    bkg.setBackgroundResource(com.kongzue.dialog.R.color.dlg_bkg_dark);
                    btnSelectNegative.setBackgroundResource(com.kongzue.dialog.R.drawable.button_dialog_kongzue_gray_dark);
                    btnSelectPositive.setBackgroundResource(com.kongzue.dialog.R.drawable.button_dialog_kongzue_blue_dark);
                    btnSelectNegative.setTextColor(Color.rgb(255, 255, 255));
                    btnSelectPositive.setTextColor(Color.rgb(255, 255, 255));
                }

                useTextInfo(txtDialogTitle, customTitleTextInfo);
                useTextInfo(txtDialogTip, customContentTextInfo);
                useTextInfo(btnSelectPositive, customOkButtonTextInfo);

                if (dialog_background_color != -1) {
                    bkg.setBackgroundResource(dialog_background_color);
                }

                break;
            case STYLE_MATERIAL:
                alertDialog.setTitle(title);
                alertDialog.setMessage(message);
                alertDialog.setButton(BUTTON_POSITIVE, buttonCaption, onOkButtonClickListener);
                if (dialog_background_color != -1) {
                    alertDialog.getWindow().getDecorView().setBackgroundResource(dialog_background_color);
                }
                if (customView != null) alertDialog.setView(customView);

                kongzueDialogHelper.show(fragmentManager, "kongzueDialog");
                break;
            case STYLE_IOS:
                rootView = LayoutInflater.from(context).inflate(R.layout.dialog_select_ios_, null);
                alertDialog.setView(rootView);
                kongzueDialogHelper.show(fragmentManager, "kongzueDialog");

                window.setWindowAnimations(com.kongzue.dialog.R.style.iOSAnimStyle);

                bkg = (RelativeLayout) rootView.findViewById(com.kongzue.dialog.R.id.bkg);
                txtDialogTitle = rootView.findViewById(com.kongzue.dialog.R.id.txt_dialog_title);
                txtDialogTip = rootView.findViewById(com.kongzue.dialog.R.id.txt_dialog_tip);
                txtInput = rootView.findViewById(com.kongzue.dialog.R.id.txt_input);
                splitHorizontal = rootView.findViewById(com.kongzue.dialog.R.id.split_horizontal);
                btnSelectNegative = rootView.findViewById(com.kongzue.dialog.R.id.btn_selectNegative);
                splitVertical = rootView.findViewById(com.kongzue.dialog.R.id.split_vertical);
                btnSelectPositive = rootView.findViewById(com.kongzue.dialog.R.id.btn_selectPositive);
                customView = rootView.findViewById(com.kongzue.dialog.R.id.box_custom);

                if (isNull(title)) {
                    txtDialogTitle.setVisibility(View.GONE);
                } else {
                    txtDialogTitle.setVisibility(View.VISIBLE);
                    txtDialogTitle.setText(title);
                }
                if (isNull(message)) {
                    txtDialogTip.setVisibility(View.GONE);
                } else {
                    txtDialogTip.setVisibility(View.VISIBLE);
                    txtDialogTip.setText(message);
                }

                btnSelectNegative.setVisibility(View.GONE);
                splitVertical.setVisibility(View.GONE);
                btnSelectPositive.setText(buttonCaption);
                btnSelectPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        if (onOkButtonClickListener != null)
                            onOkButtonClickListener.onClick(alertDialog, BUTTON_POSITIVE);
                    }
                });

                int bkgResId;
                if (dialog_theme == THEME_DARK) {
                    splitHorizontal.setBackgroundResource(com.kongzue.dialog.R.color.ios_dialog_split_dark);
                    splitVertical.setBackgroundResource(com.kongzue.dialog.R.color.ios_dialog_split_dark);
                    btnSelectPositive.setBackgroundResource(com.kongzue.dialog.R.drawable.button_dialog_one_dark);
                    bkgResId = com.kongzue.dialog.R.drawable.rect_dlg_dark;
                    blur_front_color = Color.argb(blur_alpha, 0, 0, 0);
                } else {
                    btnSelectPositive.setBackgroundResource(com.kongzue.dialog.R.drawable.button_dialog_one);
                    bkgResId = com.kongzue.dialog.R.drawable.rect_light;
                    blur_front_color = Color.argb(blur_alpha, 255, 255, 255);      //白
                }

                if (use_blur) {
                    bkg.post(new Runnable() {
                        @Override
                        public void run() {
                            blur = new BlurView(context, null);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, bkg.getHeight());
                            blur.setOverlayColor(blur_front_color);
                            bkg.addView(blur, 0, params);
                        }
                    });
                } else {
                    bkg.setBackgroundResource(bkgResId);
                }

                useTextInfo(txtDialogTitle, customTitleTextInfo);
                useTextInfo(txtDialogTip, customContentTextInfo);
                useTextInfo(btnSelectPositive, customOkButtonTextInfo);

                if (dialog_background_color != -1) {
                    bkg.setBackgroundResource(dialog_background_color);
                }

                break;
        }
        isDialogShown = true;
        if (getDialogLifeCycleListener() != null) getDialogLifeCycleListener().onShow(alertDialog);
        kongzueDialogHelper.setCancelable(isCanCancel);
    }

    private void useTextInfo(TextView textView, TextInfo textInfo) {
        if (textInfo.getFontSize() > 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textInfo.getFontSize());
        }
        if (textInfo.getFontColor() != 1) {
            textView.setTextColor(textInfo.getFontColor());
        }
        if (textInfo.getGravity() != -1) {
            textView.setGravity(textInfo.getGravity());
        }

        Typeface font = Typeface.create(Typeface.SANS_SERIF, textInfo.isBold()?Typeface.BOLD:Typeface.NORMAL);
        textView.setTypeface(font);
    }

    @Override
    public void doDismiss() {
        if (alertDialog != null) alertDialog.dismiss();
    }

    public MyMessageDialog setCanCancel(boolean canCancel) {
        isCanCancel = canCancel;
        if (alertDialog != null) alertDialog.setCancelable(canCancel);
        return this;
    }

    public MyMessageDialog setCustomView(View view) {
        if (style == STYLE_MATERIAL) {
            customView = new RelativeLayout(context);
            customView.addView(view);
        } else {
            if (alertDialog != null && view != null) {
                customView.removeAllViews();
                customView.setVisibility(View.VISIBLE);
                customView.addView(view);
            }
        }
        return this;
    }

    private boolean isNull(String s) {
        if (s == null || s.trim().isEmpty() || s.equals("null")) {
            return true;
        }
        return false;
    }

    public MyMessageDialog setTitleTextInfo(TextInfo textInfo) {
        this.customTitleTextInfo = textInfo;
        return this;
    }

    public MyMessageDialog setContentTextInfo(TextInfo textInfo) {
        this.customContentTextInfo = textInfo;
        return this;
    }

    public MyMessageDialog setOkButtonTextInfo(TextInfo textInfo) {
        this.customOkButtonTextInfo = textInfo;
        return this;
    }

    public MyMessageDialog setDialogStyle(int style) {
        this.style = style;
        return this;
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }
}


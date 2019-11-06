
package com.facebook.react.uimanager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.views.textinput.ReactEditText;

import java.lang.reflect.Method;

public class EditTextModule extends ReactContextBaseJavaModule {

  public EditTextModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "EditTextModule";
  }

  @ReactMethod
  public void editTextDecimal(final int tag, final int maxLength) {
    UiThreadUtil.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        final Activity activity = getCurrentActivity();
        final ReactEditText edit = getEditById(tag);
        if (edit == null) {
          Log.i("react-native", "no eidt text");
          return;
        }
        InputFilter filter = new InputFilter() {
          @Override
          public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (end > start) {

              char[] acceptedChars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','.'};
              for (int index = start; index < end; index++) {
                if (!new String(acceptedChars).contains(String.valueOf(source.charAt(index)))) {
                  return "";
                }
              }
            }
            return null;
          }
        };
        edit.setInputType(InputType.TYPE_CLASS_NUMBER |InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edit.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(maxLength)});

      }
    });
  }
  private ReactEditText getEditById(int id) {
    try{
      UIViewOperationQueue uii = this.getReactApplicationContext().getNativeModule(UIManagerModule.class)
              .getUIImplementation().getUIViewOperationQueue();
      Log.i("react-native", String.valueOf(id));
      return (ReactEditText) uii.getNativeViewHierarchyManager().resolveView(id);
    }catch(Exception ex){
      return null;
    }
  }

}

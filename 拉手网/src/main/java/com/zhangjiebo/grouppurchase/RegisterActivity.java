package com.zhangjiebo.grouppurchase;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhangjiebo.grouppurchase.bean.Person;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends Activity implements View.OnClickListener {


    private EditText mPhonenum;
    private EditText mPassword_edit01;
    private OnSendMessageHandler osmHandler;
    private EditText mSms_yanzheng;
    private EditText mPassword_edit02;
    private String mPhone;
    private String mYanzhengma;
    private String mPassword01;
    private String mPassword02;
    private Button mRegeister_btn;
    private int[] num01 = new int[2];
    private int[] num02 = new int[2];
    private int[] num03 = new int[2];
    private int[] num04 = new int[2];
    private ImageView mHide_passrord;
    private boolean isHide=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPhonenum = (EditText) findViewById(R.id.phonenum);
        mPassword_edit01 = (EditText) findViewById(R.id.password_edit01);
        mPassword_edit02 = (EditText) findViewById(R.id.password_edit02);
        mSms_yanzheng = (EditText) findViewById(R.id.sms_yanzheng);
        mRegeister_btn = (Button) findViewById(R.id.regeister_btn);
        mHide_passrord = (ImageView) findViewById(R.id.hide_passrord);
        mHide_passrord.setOnClickListener(this);
        editLisenter();
        findViewById(R.id.sms_btn).setOnClickListener(this);
        mRegeister_btn.setOnClickListener(this);
//        注册回调
        SMSSDK.registerEventHandler(eh);

    }

    private void editLisenter() {
        mPhonenum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    num01[0] = num01[0] + 1;
                } else {
                    num01[0] = num01[0] - 1;
                }
                if (num01[0] > 0 && num02[0] > 0 && num03[0] > 0 && num04[0] > 0) {
                    mRegeister_btn.setEnabled(true);
                } else {
                    mRegeister_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mPassword_edit01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    num02[0] = num02[0] + 1;
                } else {
                    num02[0] = num02[0] - 1;
                }
                if (num01[0] > 0 && num02[0] > 0 && num03[0] > 0 && num04[0] > 0) {
                    mRegeister_btn.setEnabled(true);
                } else {
                    mRegeister_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mPassword_edit02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    num03[0] = num03[0] + 1;
                } else {
                    num03[0] = num03[0] - 1;
                }
                if (num01[0] > 0 && num02[0] > 0 && num03[0] > 0 && num04[0] > 0) {
                    mRegeister_btn.setEnabled(true);
                } else {
                    mRegeister_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mSms_yanzheng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    num04[0] = num04[0] + 1;
                } else {
                    num04[0] = num04[0] - 1;
                }
                if (num01[0] > 0 && num02[0] > 0 && num03[0] > 0 && num04[0] > 0) {
                    mRegeister_btn.setEnabled(true);
                } else {
                    mRegeister_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regeister_btn:
                mPhone = mPhonenum.getText().toString().trim();
                mYanzhengma = mSms_yanzheng.getText().toString().trim();
                mPassword01 = mPassword_edit01.getText().toString().trim();
                mPassword02 = mPassword_edit02.getText().toString().trim();
                if (mPassword01.equals(mPassword02)) {
                    BmobQuery<Person> query = new BmobQuery<Person>();
                    query.addWhereEqualTo("name", mPhone);
                    query.findObjects(getApplicationContext(), new FindListener<Person>() {
                        @Override
                        public void onSuccess(List<Person> object) {
                            if (object.size() > 0) {
                                for (Person person : object) {
                                    String name = person.getName();
                                    if (name.equals(mPhone)) {
                                        toast("用户已存在");
                                    } else {
                                        SMSSDK.submitVerificationCode("86", mPhone, mYanzhengma);
                                    }
                                }
                            } else {
                                SMSSDK.submitVerificationCode("86", mPhone, mYanzhengma);
                            }
                        }
                        @Override
                        public void onError(int i, String s) {
                        }
                    });
                } else {
                    toast("两次密码不一致，请重新输入");
                }
                break;
            case R.id.sms_btn:
                SMSSDK.getVerificationCode("86", mPhonenum.getText().toString().trim(), new OnSendMessageHandler() {
                    @Override
                    public boolean onSendMessage(String s, String s1) {
                        return false;
                    }
                });
                break;
            case R.id.hide_passrord:
                isHide=!isHide;
                if (isHide){
                    mHide_passrord.setImageResource(R.mipmap.hide_pwd);
                    mPassword_edit01.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPassword_edit02.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable etable = mPassword_edit01.getText();
                    Selection.setSelection(etable, etable.length());
                    Editable etable1 = mPassword_edit02.getText();
                    Selection.setSelection(etable1, etable1.length());
                }else {
                    mHide_passrord.setImageResource(R.mipmap.show_pwd);
                    mPassword_edit01.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mPassword_edit02.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etable = mPassword_edit01.getText();
                    Selection.setSelection(etable, etable.length());
                    Editable etable1 = mPassword_edit02.getText();
                    Selection.setSelection(etable1, etable1.length());
                }
                break;
        }
    }

    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            switch (event) {
                case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        Person p2 = new Person();
                        p2.setName(mPhone);
                        p2.setPassword(mPassword01);
                        p2.save(getApplicationContext(), new SaveListener() {
                            @Override
                            public void onSuccess() {
                                toast("注册成功");
                            }
                            @Override
                            public void onFailure(int code, String msg) {
                                toast("注册失败");
                            }
                        });
                    } else {
                        toast("短信验证失败");
                    }
                    break;
                case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        toast("获取验证码成功");
                        //默认的智能验证是开启的,我已经在后台关闭
                        //
                    } else {
                        toast("获取验证码失败");
                    }
                    break;
            }
        }
    };

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

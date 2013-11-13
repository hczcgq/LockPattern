package com.way.locus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.way.locus.LocusPassWordView.OnCompleteListener;

public class LoginActivity extends Activity {
	private LocusPassWordView lpwv;
	private Toast toast;

	/**
	 * Toast message
	 * @param message
	 */
	private void showToast(CharSequence message) {
		if (null == toast) {
			toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(message);
		}

		toast.show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);
		lpwv.setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(String mPassword) {
				// ���������ȷ,�������ҳ�档
				System.out.println(mPassword);
				if (lpwv.verifyPassword(mPassword)) {
					showToast("��½�ɹ���");
					Intent intent = new Intent(LoginActivity.this,
							MainActivity.class);
					// ���µ�Activity
					startActivity(intent);
					finish();
				} else {
					showToast("�����������,����������");
					lpwv.clearPassword();
				}
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		// �������Ϊ��,�������������Ľ���
		View noSetPassword = (View) this.findViewById(R.id.tvNoSetPassword);
		TextView toastTv = (TextView) findViewById(R.id.login_toast);
		if (lpwv.isPasswordEmpty()) {
			lpwv.setVisibility(View.GONE);
			noSetPassword.setVisibility(View.VISIBLE);
			toastTv.setText("���Ȼ�����������");
			noSetPassword.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(LoginActivity.this,
							SetPasswordActivity.class);
					// ���µ�Activity
					startActivity(intent);
					finish();
				}

			});
		} else {
			toastTv.setText("��������������");
			lpwv.setVisibility(View.VISIBLE);
			noSetPassword.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

}

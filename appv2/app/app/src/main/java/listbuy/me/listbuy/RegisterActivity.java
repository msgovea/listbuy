package listbuy.me.listbuy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaCadastro;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaLogin;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity implements SincronizaCadastro.Listener{

    private EditText mPasswordView, mEmailView, mNameView, mConfirmPasswordView;
    private View mProgressView;
    private View mRegisterFormView;
    private StringRequest request;
    private RequestQueue requestQueue;
    public static Context context;
    private static final String URL = "http://www.listbuy.me/api/user_control.php";


    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                /*startActivity(new Intent(this, SuaActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem*/
                onBackPressed();
                break;
            default:break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, WelcomeScreen.class));
        finishActivity(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Cadastro ListBuy");

        mNameView = (EditText) findViewById(R.id.name);
        mEmailView = (EditText) findViewById(R.id.emailRegister);
        mPasswordView = (EditText) findViewById(R.id.passwordRegister);
        mConfirmPasswordView = (EditText) findViewById(R.id.passwordConfirm);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mRegisterFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.register_progress);

        requestQueue = Volley.newRequestQueue(this);
    }


    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String name = mNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();


        // Check for a valid name, if the user entered one.
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            requestFocus(mNameView);
            return;
        } else if (!isNameValid(name)) {
            mNameView.setError(getString(R.string.error_invalid_name));
            requestFocus(mNameView);
            return;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            requestFocus(mEmailView);
            return;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            requestFocus(mEmailView);
            return;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            requestFocus(mPasswordView);
            return;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            requestFocus(mPasswordView);
            return;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(confirmPassword)) {
            mConfirmPasswordView.setError(getString(R.string.error_field_required));
            requestFocus(mConfirmPasswordView);
            return;
        } else if (!isConfirmPassword(password, confirmPassword)) {
            mConfirmPasswordView.setError(getString(R.string.error_confirm_password));
            requestFocus(mConfirmPasswordView);
            return;
        }
        showProgress(true);
        SincronizaCadastro sinc = new SincronizaCadastro(this);
        sinc.execute(mNameView.getText().toString(),mEmailView.getText().toString(),mPasswordView.getText().toString(),"A");

        //showProgress(true);
        //request();
    }


    private void requestFocus(View focusView) { focusView.requestFocus(); }

    private boolean isNameValid(String name) { return name.length() > 5; }

    private boolean isPasswordValid(String password) { return password.length() > 4; }

    private boolean isConfirmPassword(String password, String confirmPassword) { return password.equals(confirmPassword); }

    private boolean isEmailValid(String email) {
        if (email.contains("@")) {
            String[] validacao = email.split("@");
            return (validacao.length > 1);
        }
        return false;
    }

   /* private void request() {
        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success")) {
                        Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = getSharedPreferences("INFORMACOES_LOGIN_AUTOMATICO", MODE_PRIVATE).edit();

                        editor.putString("login", mEmailView.getText().toString());
                        editor.putString("senha", mPasswordView.getText().toString());
                        editor.putString("nome", mNameView.getText().toString());
                        editor.commit();
                        startActivity(new Intent(getApplicationContext(), MenuLateral.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Error" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("name", mNameView.getText().toString());
                hashMap.put("email", mEmailView.getText().toString());
                hashMap.put("password", mPasswordView.getText().toString());
                hashMap.put("type", "register");

                return hashMap;
            }
        };

        requestQueue.add(request);
    }*/

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onLoaded(String string) {
        if (string == "true") {
            showProgress(false);
            startActivity(new Intent(this, MenuLateral.class));
            SharedPreferences.Editor editor = getSharedPreferences("INFORMACOES_LOGIN_AUTOMATICO", MODE_PRIVATE).edit();

            editor.putString("login", mEmailView.getText().toString());
            editor.putString("senha", mPasswordView.getText().toString());
            editor.putString("nome", mNameView.getText().toString());
            editor.commit();
            finishActivity(1);
        } else {
            showProgress(false);
        }
    }
}


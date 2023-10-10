package p014k;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.color.otaassistant.R;

/* renamed from: k.a */
/* loaded from: classes.dex */
public class InstallDialog extends AlertDialog implements View.OnClickListener {

    /* renamed from: a */
    private TextView f185a;

    /* renamed from: b */
    private TextView f186b;

    /* renamed from: c */
    private TextView f187c;

    /* renamed from: d */
    private String f188d;

    /* renamed from: e */
    private InterfaceC0496a f189e;

    /* compiled from: InstallDialog.java */
    /* renamed from: k.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0496a {
        /* renamed from: a */
        void mo97a();

        void onCancel();
    }

    public InstallDialog(Context context, String str) {
        super(context, R.style.dialog_install);
        this.f189e = null;
        this.f188d = str;
    }

    /* renamed from: a */
    public void m107a(InterfaceC0496a interfaceC0496a) {
        this.f189e = interfaceC0496a;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel /* 2131230785 */:
                InterfaceC0496a interfaceC0496a = this.f189e;
                if (interfaceC0496a != null) {
                    interfaceC0496a.onCancel();
                    break;
                }
                break;
            case R.id.btn_install /* 2131230786 */:
                InterfaceC0496a interfaceC0496a2 = this.f189e;
                if (interfaceC0496a2 != null) {
                    interfaceC0496a2.mo97a();
                    break;
                }
                break;
        }
        dismiss();
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.install_dialog);
        this.f185a = (TextView) findViewById(R.id.btn_install);
        this.f186b = (TextView) findViewById(R.id.btn_cancel);
        this.f187c = (TextView) findViewById(R.id.content);
        TextView textView = this.f185a;
        if (textView != null) {
            textView.setOnClickListener(this);
        }
        TextView textView2 = this.f186b;
        if (textView2 != null) {
            textView2.setOnClickListener(this);
        }
        String format = String.format(getContext().getString(R.string.dialog_message_version_id), this.f188d);
        TextView textView3 = this.f187c;
        if (textView3 != null) {
            textView3.setText(format);
        }
        getWindow().setType(2038);
        setCancelable(false);
    }
}

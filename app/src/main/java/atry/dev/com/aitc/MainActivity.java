package atry.dev.com.aitc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    Button btnPemasukan, btnPengeluaran;
    EditText input;
    TextView nilaiTabungan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.input);
        btnPemasukan = (Button) findViewById(R.id.btnPemasukan);
        btnPengeluaran = (Button) findViewById(R.id.btnPengeluaran);
        nilaiTabungan = (TextView) findViewById(R.id.nilaiTabungan);

        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

        nilaiTabungan.setText(String.valueOf(getTabungan()));
        btnPemasukan.setOnClickListener(this);
        btnPengeluaran.setOnClickListener(this);
    }

    public void pemasukan(String nilai){
        int tambah = Integer.valueOf(nilai) + getTabungan();
        setTabungan(tambah);
        nilaiTabungan.setText(String.valueOf(tambah));
    }

    public void pengeluaran(String nilai){
        int kurang = getTabungan() - Integer.valueOf(nilai);
        setTabungan(kurang);
        nilaiTabungan.setText(String.valueOf(kurang));
    }

    public int getTabungan(){
        return sharedPreferences.getInt("tabungan", 0);
    }

    public void setTabungan(int nilai){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tabungan", nilai);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPemasukan:
                pemasukan(String.valueOf(input.getText()));
                break;
            case R.id.btnPengeluaran:
                pengeluaran(String.valueOf(input.getText()));
                break;
        }
    }
}

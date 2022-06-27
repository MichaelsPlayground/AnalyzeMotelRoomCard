package de.androidcrypto.analyzemotelroomcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    com.google.android.material.textfield.TextInputEditText sanDiegoCardData, sanDiegoUtc;
    com.google.android.material.textfield.TextInputEditText twentyCardData, twentyUtc;

    long sanDiegoUtcLong = 1650042000L;
    long twentyUtcLong = 1651510800L;
    byte[] sanDiegoUtcByte;
    byte[] twentyUtcByte;
    String sanDiegoUtcString;
    String twentyUtcString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sanDiegoCardData = findViewById(R.id.etMainDaysInn);
        sanDiegoUtc = findViewById(R.id.etMainDaysInnUtc);
        twentyCardData = findViewById(R.id.etMainABVI);
        twentyUtc = findViewById(R.id.etMainABVIUtc);

        sanDiegoUtcByte = longToBytes(sanDiegoUtcLong);
        sanDiegoUtcString = bytesToHex(sanDiegoUtcByte);
        sanDiegoUtc.setText("L: " + sanDiegoUtcLong + " " + sanDiegoUtcString);
        twentyUtcByte = longToBytes(twentyUtcLong);
        twentyUtcString = bytesToHex(twentyUtcByte);
        twentyUtc.setText("L: " + twentyUtcLong + " " + twentyUtcString);

    }

    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes)
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
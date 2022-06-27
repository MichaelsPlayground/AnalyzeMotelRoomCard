package de.androidcrypto.analyzemotelroomcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    com.google.android.material.textfield.TextInputEditText sanDiegoCardData, sanDiegoUtc, sanDiegoRoom;
    com.google.android.material.textfield.TextInputEditText twentyCardData, twentyUtc;

    long sanDiegoUtcLong = 1650042000L;
    long twentyUtcLong = 1651510800L;
    byte[] sanDiegoUtcByte;
    byte[] twentyUtcByte;
    String sanDiegoUtcString;
    String twentyUtcString;
    int sanDiegoRoomInt = 301;
    byte[] sanDiegoRoomByte;
    String sanDiegoRoomString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sanDiegoCardData = findViewById(R.id.etMainDaysInn);
        sanDiegoUtc = findViewById(R.id.etMainDaysInnUtc);
        sanDiegoRoom = findViewById(R.id.etMainDaysInnRooom);
        twentyCardData = findViewById(R.id.etMainABVI);
        twentyUtc = findViewById(R.id.etMainABVIUtc);

        sanDiegoUtcByte = longToBytes(sanDiegoUtcLong);
        sanDiegoUtcString = bytesToHex(sanDiegoUtcByte);
        sanDiegoUtc.setText("L: " + sanDiegoUtcLong + " " + sanDiegoUtcString);
        sanDiegoRoomByte = integerToTwoBytes(sanDiegoRoomInt);
        sanDiegoRoomString = bytesToHex(sanDiegoRoomByte);
        sanDiegoRoom.setText("Room "+ sanDiegoRoomInt + " " + sanDiegoRoomString);

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

    public static final byte[] integerToTwoBytes(int value) throws IndexOutOfBoundsException {
        byte[] result = new byte[2];
        if ((value > Math.pow(2,31)) || (value < 0)) {
            throw new IndexOutOfBoundsException("Integer value " + value + " is larger than 2^31");
        }
        result[0] = (byte)((value >>> 8) & 0xFF);
        result[1] = (byte)(value & 0xFF);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes)
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
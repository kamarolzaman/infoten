package com.example.kamarol.infoten_v1;

import android.os.AsyncTask;

import com.example.kamarol.infoten_v1.GetTimetable;
import com.example.kamarol.infoten_v1.LoginFragment;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by musyrif on 11-Nov-17.
 */

public class SendTable extends AsyncTask <Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket("192.168.0.5", 5000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            int len =0,y = GetTimetable.subject.length;
            for (int x=0; x<y;x++){
                if (GetTimetable.subject[x]!=null){
                    len=x+1;
                }
            }
            oos.writeUTF(LoginFragment.username);
            oos.writeInt(len);
            for (int x=0;x<len;x++){
                GetTimetable.subject[x].writeObject(oos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example.kamarol.infoten_v1.Functions;

import android.os.AsyncTask;

import com.example.kamarol.infoten_v1.LoginFragment;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by musyrif on 11-Nov-17.
 */

public class SendTable extends AsyncTask <Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //InetAddress inet = InetAddress.getByName("ec2-18-217-42-15.us-east-2.compute.amazonaws.com");
            Socket socket = new Socket("ec2-18-217-42-15.us-east-2.compute.amazonaws.com", 5000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            int len =0,y = ParseTimetable.subject.length;
            for (int x=0; x<y;x++){
                if (ParseTimetable.subject[x]!=null){
                    len=x+1;
                }
            }
            oos.writeUTF(LoginFragment.username);
            oos.writeInt(len);
            for (int x=0;x<len;x++){
                ParseTimetable.subject[x].writeObject(oos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

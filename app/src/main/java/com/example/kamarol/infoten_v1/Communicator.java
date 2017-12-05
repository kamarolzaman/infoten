package com.example.kamarol.infoten_v1;

/**
 * Created by musyrif on 12-Nov-17.
 */

public interface Communicator {
    public void dismissDialog();
    public void showHome();
    public void dismissLogin();
    public void onTableLoad();
    public void showSubjectDetails(String subject);
    public void showLecturerDetails(String name);
}

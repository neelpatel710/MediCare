package com.example.gmit_sdp.beinghuman;

/**
 * Created by Precisa on 01-Apr-18.
 */

public class setter {
    private String tname,tmobile,temail,tgender;
    public setter(String tname, String tmobile, String temail, String tgender)
    {
        this.setName(tname);
        this.setMobile(tmobile);
        this.setEmail(temail);
        this.setGender(tgender);
    }

    public String getName() {
        return tname;
    }

    public void setName(String tname) {
        this.tname = tname;
    }

    public void setMobile(String tmobile) {
        this.tmobile = tmobile;
    }

    public String getMobile() {
        return tmobile;
    }

    public void setEmail(String temail) {
        this.temail = temail;
    }

    public String getEmail() {
        return temail;
    }

    public void setGender(String tgender) {
        this.tgender = tgender;
    }
    public String getGender() {
        return tgender;
    }

}

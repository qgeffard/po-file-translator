package org.qgeff.model;

import java.util.Objects;

public class Translation {

    private String reference;
    private String msgid;
    private String msgstr;

    public Translation() {}

    public Translation(String reference, String msgid, String msgstr) {
        this.reference = reference;
        this.msgid = msgid;
        this.msgstr = msgstr;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public void setMsgstr(String msgstr) {
        this.msgstr = msgstr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translation that = (Translation) o;
        return Objects.equals(reference, that.reference) &&
                Objects.equals(msgid, that.msgid) &&
                Objects.equals(msgstr, that.msgstr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, msgid, msgstr);
    }

    @Override
    public String toString() {
        return  reference + '\n' +
                "msgid \"" + msgid + "\"\n" +
                "msgstr \"" + msgstr + "\"";
    }
}

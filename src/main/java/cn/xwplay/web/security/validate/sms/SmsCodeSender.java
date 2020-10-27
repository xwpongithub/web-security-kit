package cn.xwplay.web.security.validate.sms;

public interface SmsCodeSender {

    void send(String mobile, String code);

}

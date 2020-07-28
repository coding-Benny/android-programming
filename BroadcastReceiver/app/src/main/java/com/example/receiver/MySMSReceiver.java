package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class MySMSReceiver extends BroadcastReceiver {

    interface OnSmsReceived {
        void onReceived(String msg);
    }

    private OnSmsReceived onSmsReceived = null;

    public void setOnSmsReceived(OnSmsReceived smsReceived) {
        onSmsReceived = smsReceived;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            if (messages != null) {
                if (messages.length == 0)
                    return;
                StringBuilder sb = new StringBuilder();
                for (SmsMessage smsMessage : messages) {
                    sb.append(smsMessage.getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();

                if (onSmsReceived != null)
                    onSmsReceived.onReceived(sender + " : " + message);
            }
        }
    }
}

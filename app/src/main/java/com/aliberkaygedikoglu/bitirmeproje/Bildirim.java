package com.aliberkaygedikoglu.bitirmeproje;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Bildirim extends Worker {
    public Bildirim(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        bildirimGoster();
        return null;
    }

    private void bildirimGoster() {
        NotificationCompat.Builder builder ;
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                1, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel kanal = notificationManager.getNotificationChannel("id");

            if (kanal == null) {
                kanal = new NotificationChannel("id", "ad", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(kanal);
            }

            builder = new NotificationCompat.Builder(getApplicationContext(), "id");
            builder.setContentTitle("E-Sepet")
                    .setContentText("Siparişiniz Hazırlanıyor")
                    .setSmallIcon(R.drawable.logo_f)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
        }else{
            builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setContentTitle("E-Sepet")
                    .setContentText("Siparişiniz Hazırlanıyor")
                    .setSmallIcon(R.drawable.bildirim_icon)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setPriority(Notification.PRIORITY_HIGH);
        }

        notificationManager.notify(10,builder.build());
    }


}

/*
 * Copyright 2026 Morphe.
 * https://github.com/MorpheApp/morphe-patches/pull/5441
 *
 * See the included NOTICE file for GPLv3 Section 7 terms that apply to this code.
 */

package app.morphe.extension.youtube.patches;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Method;

import app.morphe.extension.shared.Logger;
import app.morphe.extension.youtube.settings.Settings;

@SuppressWarnings("unused")
public final class RestoreOldCastPatch {

    private static final boolean RESTORE_OLD_CAST_BEHAVIOR = Settings.RESTORE_OLD_CAST_BEHAVIOR.get();

    /**
     * Injection point.
     *
     * @param original If the app disconnects the Cast session and plays a newly selected video on the phone.
     */
    public static boolean disconnectCastOnVideoSelect(boolean original) {
        return original && !RESTORE_OLD_CAST_BEHAVIOR;
    }

    /**
     * Injection point.
     *
     * @param original If the app disconnects the Cast session when the player is closed or minimized.
     */
    public static boolean disconnectCastOnPlayerClose(boolean original) {
        return original && !RESTORE_OLD_CAST_BEHAVIOR;
    }

    /**
     * Injection point.
     *
     * @return If the dialog that asks to clear the TV queue is shown when the player is closed while casting.
     */
    public static boolean showCloseCastPlayerDialog() {
        return !RESTORE_OLD_CAST_BEHAVIOR;
    }

    /**
     * Injection point.
     * Runs the same call as the Cancel button of the dialog, after the player close has finished.
     *
     * @param playerController The object with the method that shows the miniplayer.
     */
    public static void showMiniplayerAfterClose(Object playerController) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            try {
                Method method = playerController.getClass().getMethod("B", int.class, int.class);
                method.invoke(playerController, 1, 1);
            } catch (Exception ex) {
                Logger.printException(() -> "showMiniplayerAfterClose failure", ex);
            }
        }, 400);
    }
}

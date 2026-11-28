/*
 * Copyright 2026 Morphe.
 * https://github.com/MorpheApp/morphe-patches
 *
 * See the included NOTICE file for GPLv3 Section 7 terms that apply to this code.
 */

package app.morphe.patches.youtube.interaction.cast

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.resource.ResourceType
import app.morphe.patcher.resourceLiteral
import com.android.tools.smali.dexlib2.AccessFlags

/**
 * When a different video is selected while casting, the flag disconnects the Cast session
 * and plays the video on the phone, unless the video is from the queue of the TV.
 */
internal object CastDisconnectOnVideoSelectFeatureFlagFingerprint : Fingerprint(
    returnType = "V",
    filters = listOf(
        literal(45693138L)
    )
)

/**
 * When the player is closed or minimized while casting, the flag disconnects the Cast session.
 * The flag is also read by the minimize button of the player, which then shows a close icon.
 */
internal object CastDisconnectOnPlayerCloseFeatureFlagFingerprint : Fingerprint(
    filters = listOf(
        literal(45798912L)
    )
)

/**
 * Shows the "TV queue will be cleared" dialog when the player is closed while casting.
 * Choosing to close the player in the dialog also ends the Cast session.
 */
internal object CastClosePlayerDialogFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf(),
    filters = listOf(
        resourceLiteral(ResourceType.STRING, "mdx_floaty_bar_clear_queue_dialog_title")
    )
)

/**
 * When on, swiping down the player dismisses it, instead of minimizing it to the miniplayer.
 */
internal object PlayerDismissOnSwipeFeatureFlagFingerprint : Fingerprint(
    filters = listOf(
        literal(45657015L)
    )
)

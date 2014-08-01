/**
 * 
 */

package com.aniruddhc.xPlod.activities;

import android.media.AudioManager;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore.Audio;
import android.support.v4.app.FragmentActivity;

import com.aniruddhc.xPlod.ui.fragments.grid.QuickQueueFragment;

import static com.aniruddhc.xPlod.Constants.MIME_TYPE;
import static com.aniruddhc.xPlod.Constants.PLAYLIST_QUEUE;

/**
 * @author Andrew Neal
 */
public class QuickQueue extends FragmentActivity {

    @Override
    protected void onCreate(Bundle icicle) {
        // This needs to be called first
        super.onCreate(icicle);

        // Control Media volume
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Bundle bundle = new Bundle();
        bundle.putString(MIME_TYPE, Audio.Playlists.CONTENT_TYPE);
        bundle.putLong(BaseColumns._ID, PLAYLIST_QUEUE);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new QuickQueueFragment(bundle)).commit();
    }
}

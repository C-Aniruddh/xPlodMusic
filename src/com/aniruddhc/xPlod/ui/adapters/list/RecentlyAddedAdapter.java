
package com.aniruddhc.xPlod.ui.adapters.list;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.MediaStore.MediaColumns;
import android.provider.MediaStore.Audio.AudioColumns;

import com.aniruddhc.xPlod.helpers.utils.MusicUtils;
import com.aniruddhc.xPlod.ui.adapters.base.ListViewAdapter;

import static com.aniruddhc.xPlod.Constants.TYPE_ALBUM;

public class RecentlyAddedAdapter extends ListViewAdapter {

    public RecentlyAddedAdapter(Context context, int layout, Cursor c, String[] from, int[] to,
            int flags) {
        super(context, layout, c, from, to, flags);
    }

    public void setupViewData( Cursor mCursor ){
    	mLineOneText = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaColumns.TITLE));

    	mLineTwoText = mCursor.getString(mCursor.getColumnIndexOrThrow(AudioColumns.ARTIST));

        String albumName = mCursor.getString(mCursor.getColumnIndexOrThrow(AudioColumns.ALBUM));
        String albumId = mCursor.getString(mCursor.getColumnIndexOrThrow(AudioColumns.ALBUM_ID));
        mImageData = new String[]{ albumId , mLineTwoText, albumName };
        
        mPlayingId = MusicUtils.getCurrentAudioId();
        mCurrentId = mCursor.getLong(mCursor.getColumnIndexOrThrow(BaseColumns._ID));

        mListType = TYPE_ALBUM;
    	showContextEnabled = false;    	
    }
}


package com.aniruddhc.xPlod.ui.fragments.list;

import android.support.v4.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Audio.AudioColumns;
import android.provider.MediaStore.MediaColumns;

import com.aniruddhc.xPlod.R;
import com.aniruddhc.xPlod.helpers.AddIdCursorLoader;
import com.aniruddhc.xPlod.helpers.utils.MusicUtils;
import com.aniruddhc.xPlod.ui.adapters.list.RecentlyAddedAdapter;
import com.aniruddhc.xPlod.ui.fragments.base.ListViewFragment;

import static com.aniruddhc.xPlod.Constants.NUMWEEKS;

public class RecentlyAddedFragment extends ListViewFragment{

    public void setupFragmentData(){
        mAdapter = new RecentlyAddedAdapter(getActivity(), R.layout.listview_items,
                null, new String[] {}, new int[] {}, 0);
    	mProjection = new String[] {
                BaseColumns._ID, MediaColumns.TITLE, AudioColumns.ALBUM, AudioColumns.ARTIST
        };
        StringBuilder where = new StringBuilder();
        int X = MusicUtils.getIntPref(getActivity(), NUMWEEKS, 5) * 3600 * 24 * 7;
        where.append(MediaColumns.TITLE + " != ''");
        where.append(" AND " + AudioColumns.IS_MUSIC + "=1");
        where.append(" AND " + MediaColumns.DATE_ADDED + ">"
                + (System.currentTimeMillis() / 1000 - X));
        mWhere = where.toString();
        mSortOrder = MediaColumns.DATE_ADDED + " DESC";
        mUri = Audio.Media.EXTERNAL_CONTENT_URI;
        mTitleColumn = MediaColumns.TITLE;       
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {    
        return new AddIdCursorLoader(getActivity(), mUri, mProjection, mWhere, null, mSortOrder);
    }
}

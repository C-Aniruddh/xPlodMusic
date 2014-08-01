
package com.aniruddhc.xPlod.ui.fragments.list;

import android.provider.BaseColumns;
import android.provider.MediaStore.Audio;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.aniruddhc.xPlod.R;
import com.aniruddhc.xPlod.helpers.utils.ApolloUtils;
import com.aniruddhc.xPlod.helpers.utils.MusicUtils;
import com.aniruddhc.xPlod.ui.adapters.list.GenreAdapter;
import com.aniruddhc.xPlod.ui.fragments.base.ListViewFragment;

import static com.aniruddhc.xPlod.Constants.TYPE_GENRE;

public class GenresFragment extends ListViewFragment{

    public void setupFragmentData(){
        mAdapter = new GenreAdapter(getActivity(), R.layout.listview_items, null,
                new String[] {}, new int[] {}, 0);
    	mProjection = new String[] {
                Audio.Genres._ID, Audio.Genres.NAME
        };        
        mSortOrder = Audio.Genres.DEFAULT_SORT_ORDER;
        mUri = Audio.Genres.EXTERNAL_CONTENT_URI;
        mFragmentGroupId = 4;
        mType = TYPE_GENRE;
        mTitleColumn = Audio.Genres.NAME;         
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    	ApolloUtils.startTracksBrowser(mType, id, mCursor, getActivity());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.add(mFragmentGroupId, PLAY_SELECTION, 0, getResources().getString(R.string.play_all));
        mCurrentId = mCursor.getString(mCursor.getColumnIndexOrThrow(BaseColumns._ID));
        String title = mCursor.getString(mCursor.getColumnIndexOrThrow(Audio.Genres.NAME));
        menu.setHeaderTitle(title);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	if(item.getGroupId()==mFragmentGroupId){
	        switch (item.getItemId()) {
	            case PLAY_SELECTION:
	                long[] list = MusicUtils.getSongListForGenre(getActivity(),
	                        										Long.parseLong(mCurrentId));
	                MusicUtils.playAll(getActivity(), list, 0);
	                break;
	            default:
	                break;
	        }
	        return true;
	    }
        return super.onContextItemSelected(item);
    }
}

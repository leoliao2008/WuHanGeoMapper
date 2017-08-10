package com.skycaster.wuhanmappingapp.view_holders;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.P.LocalMapListItemPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseViewHolderWithPresenter;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class LocalMapItemViewHolder extends BaseViewHolderWithPresenter<LocalMapListItemPresenter> {
    private TextView tv_city;
    private TextView tv_type;
    private TextView tv_version;
    private TextView tv_status;
    private TextView tv_totalSize;
    private TextView tv_currentSize;
    private ProgressBar mProgressBar;
    private TextView tv_progress;
    private Button btn_delete;
    private Button btn_pause;
    private Button btn_resume;
    private Button btn_upgrade;

    public LocalMapItemViewHolder(View itemView, LocalMapListItemPresenter presenter) {
        super(itemView, presenter);
    }


    @Override
    protected void initChildViews() {
        tv_city= (TextView) findViewById(R.id.item_local_map_tv_city);
        tv_type= (TextView) findViewById(R.id.item_local_map_tv_map_type);
        tv_version= (TextView) findViewById(R.id.item_local_map_tv_version);
        tv_status= (TextView) findViewById(R.id.item_local_map_tv_down_load_status);
        tv_totalSize= (TextView) findViewById(R.id.item_local_map_tv_total_size);
        tv_currentSize= (TextView) findViewById(R.id.item_local_map_tv_current_size);
        mProgressBar= (ProgressBar) findViewById(R.id.item_local_map_progress_bar);
        tv_progress= (TextView) findViewById(R.id.item_local_map_tv_progress);
        btn_delete= (Button) findViewById(R.id.item_local_map_btn_delete);
        btn_pause= (Button) findViewById(R.id.item_local_map_btn_pause);
        btn_resume= (Button) findViewById(R.id.item_local_map_btn_resume);
        btn_upgrade= (Button) findViewById(R.id.item_local_map_btn_upgrade);
    }

    public TextView getTv_city() {
        return tv_city;
    }

    public TextView getTv_type() {
        return tv_type;
    }

    public TextView getTv_version() {
        return tv_version;
    }

    public TextView getTv_status() {
        return tv_status;
    }

    public TextView getTv_totalSize() {
        return tv_totalSize;
    }

    public TextView getTv_currentSize() {
        return tv_currentSize;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    public TextView getTv_progress() {
        return tv_progress;
    }

    public Button getBtn_delete() {
        return btn_delete;
    }

    public Button getBtn_pause() {
        return btn_pause;
    }

    public Button getBtn_resume() {
        return btn_resume;
    }

    public Button getBtn_upgrade() {
        return btn_upgrade;
    }

}

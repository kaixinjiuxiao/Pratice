package freedom.wealth.practice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import freedom.wealth.practice.R;

/**
 * @author: captain
 * Time:  2018/3/27 0027
 * Describe:
 */
public class ClassifyFragment extends BaseFragment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_clssify,container,false);
        return mView;
    }
}

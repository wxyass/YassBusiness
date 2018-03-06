package yassbusiness.com.view.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yassbusiness.com.R;
import yassbusiness.com.view.fragment.BaseFragment;


public class MessageFragment extends BaseFragment {

    private View mContentView;

    public MessageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_message_layout, container, false);
        return mContentView;
    }


}

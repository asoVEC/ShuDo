package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vegy.aso.ac.jp.shudo.R;

/**
 * Created by Aki on 15/06/23.
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public TaskAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Viewは作成済みのものがあれば再利用
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.task_layout, null);
        }
        Task task = (Task) getItem(position);
        TextView content = (TextView) convertView.findViewById(R.id.task_content);
        content.setText(task.getContent());

        return convertView;
    }
}
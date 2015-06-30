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
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TaskAdapter(Context context, int resource, List<Task> taskList) {
        super(context, resource, taskList);
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Viewは作成済みのものがあれば再利用
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.task_layout, null);
        }

        Task task = (Task) getItem(position);
        TextView title = (TextView) convertView.findViewById(R.id.task_content);
        title.setText(task.getContent());












        return convertView;
    }

    class TaskHolder {
        TextView taskContent;
    }
}
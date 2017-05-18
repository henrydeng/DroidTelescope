package andr.perf.monitor.interactive;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.util.LinkedList;

/**
 * Created by ZhouKeWen on 2017/5/15.
 */
public class UserInteractiveSampler {

    private static final String VIEW_CLICK_EVENT = "view_click";
    private static final String VIEW_LONG_CLICK_EVENT = "view_long_click";
    private static final String DIALOG_CLICK_EVENT = "dialog_click";

    private static final int MAX_EVENT_COUNT = 5;

    private LinkedList<IEvent> eventList = new LinkedList<>();

    public IEvent[] obtainCurrentEvents() {
        return eventList.toArray(new IEvent[]{});
    }

    //android.view.View$OnClickListener
    public void onViewClick(Object object, View view) {
        ViewEvent viewEvent = new ViewEvent();
        viewEvent.setEventType(VIEW_CLICK_EVENT);
        viewEvent.setListenerName(object.getClass().getName());
        viewEvent.setViewObject(ViewUtils.getViewSign(view));
        viewEvent.setParentArray(ViewUtils.getParentArray(view));
        viewEvent.setPageName(view.getContext().getClass().getName());

        Log.i("zkw", "view event>>>> " + viewEvent);

        addToList(viewEvent);
    }

    //android.view.View$OnLongClickListener
    public void onViewLongClick(Object object, View view) {
        ViewEvent viewEvent = new ViewEvent();
        viewEvent.setEventType(VIEW_LONG_CLICK_EVENT);
        viewEvent.setListenerName(object.getClass().getName());
        viewEvent.setViewObject(ViewUtils.getViewSign(view));
        viewEvent.setParentArray(ViewUtils.getParentArray(view));
        viewEvent.setPageName(view.getContext().getClass().getName());

        Log.i("zkw", "view long event>>>> " + viewEvent);
        addToList(viewEvent);
    }

    //AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //AdapterView.OnItemLongClickListener
    public void onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    //android.content.DialogInterface$OnClickListener
    public void onDialogClick(Object object, DialogInterface dialog, int which) {
        DialogEvent dialogEvent = new DialogEvent();
        dialogEvent.setEventType(DIALOG_CLICK_EVENT);
        dialogEvent.setListenerName(object.getClass().getName());
        dialogEvent.setDialogName(dialog.getClass().getName());
        dialogEvent.setWhich(which);

        Log.i("zkw", "===>" + dialogEvent);
        addToList(dialogEvent);
    }

    //AbsListView.OnScrollListener_*
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    //View.OnScrollChangeListener_*
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }

    private void addToList(IEvent eventObject) {
        int size = eventList.size();
        if (size >= MAX_EVENT_COUNT) {
            eventList.removeLast();
        }
        eventList.addFirst(eventObject);
    }

}
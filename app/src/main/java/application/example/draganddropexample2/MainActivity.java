package application.example.draganddropexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    String msg;
    RelativeLayout.LayoutParams layoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //reference to imageview
        img = findViewById(R.id.imageview);
        img.setTag("this is my Image");
        //activate the OnLongClickevent
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());
                String[] mimetype = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragdata = new ClipData(view.getTag().toString(), mimetype,item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);
                view.startDrag(dragdata, shadowBuilder, null,0);
                return true;
            }
        });

        img.setOnDragListener(new View.OnDragListener(){
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()){
                    case  DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        Log.d(msg, "Action is started");
                        break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "Action is Entered");
                        int x_cord = (int) dragEvent.getX();
                        int y_cord = (int) dragEvent.getY();
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(msg, "The Drag Action is exited");
                        x_cord = (int) dragEvent.getX();
                        y_cord = (int) dragEvent.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        view.setLayoutParams(layoutParams);
                        break;
                        case DragEvent.ACTION_DRAG_LOCATION:
                            Log.d(msg, "The Drag Action is Drag Location");
                            x_cord = (int) dragEvent.getX();
                            y_cord = (int) dragEvent.getY();
                            break;
                            case DragEvent.ACTION_DRAG_ENDED:
                                Log.d(msg, "The Drag action is Drag Ended");
                                break;
                                case DragEvent.ACTION_DROP:
                                    Log.d(msg, "The Drag action is Drag Drop");
                                    break;
                    default:break;

                }
                return true;
            }
        });

    }
}

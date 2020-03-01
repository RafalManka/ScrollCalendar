package pl.rafman.scrollcalendar.widgets;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by rafal.manka on 11/09/2017
 */
public class SquareTextView extends AppCompatTextView {
    public SquareTextView(Context context) {
        super(context);
    }

    public SquareTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //noinspection SuspiciousNameCombination
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}

package carnero.cgeo;

import android.util.AttributeSet;
import android.view.View;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class cgCompassMini extends View {
	private int arrowSkin = R.drawable.compass_arrow_mini_white;
	private Boolean lock = false;
	private Context context = null;
	private cgBase base = null;
	private Double cacheLat = null;
	private Double cacheLon = null;
	private Canvas canvas = null;
	private Bitmap compassArrow = null;
	private float azimuth = 0.0f;
	private float heading = 0.0f;
	private PaintFlagsDrawFilter setfil = null;
	private PaintFlagsDrawFilter remfil = null;

	public cgCompassMini(Context contextIn) {
		super(contextIn);
		context = contextIn;

		init();
	}

	public cgCompassMini(Context contextIn, AttributeSet attrs) {
		super(contextIn, attrs);
		context = contextIn;

		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.cgCompassMini);
        int usedSkin = attributes.getInt(R.styleable.cgCompassMini_skin, 0);
		if (usedSkin == 1) arrowSkin = R.drawable.compass_arrow_mini_black;
		else arrowSkin = R.drawable.compass_arrow_mini_white;

		init();
	}

	public void setContent(cgBase baseIn, Double cacheLatIn, Double cacheLonIn) {
		base = baseIn;
		cacheLat = cacheLatIn;
		cacheLon = cacheLonIn;
	}

	public void init() {
		compassArrow = BitmapFactory.decodeResource(context.getResources(), arrowSkin);

		this.setfil = new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG);
        this.remfil = new PaintFlagsDrawFilter(Paint.FILTER_BITMAP_FLAG, 0);
	}

	protected void updateAzimuth(float azimuthIn) {
		azimuth = azimuthIn;

		updateDirection();
	}

	protected void updateHeading(float headingIn) {
		heading = headingIn;

		updateDirection();
	}

	protected void updateCoords(Double latitudeIn, Double longitudeIn) {
		if (latitudeIn == null || longitudeIn == null || cacheLat == null || cacheLon == null) return;

		heading = base.getHeading(latitudeIn, longitudeIn, cacheLat, cacheLon);

		updateDirection();
	}

	protected void updateDirection() {
        // compass margins
        int compassRoseWidth = this.compassArrow.getWidth();
        int compassRoseHeight = this.compassArrow.getWidth();
        int marginLeft = (getWidth() - compassRoseWidth) / 2;
        int marginTop = (getHeight() - compassRoseHeight) / 2;

        invalidate(marginLeft, marginTop, (marginLeft + compassRoseWidth), (marginTop + compassRoseHeight));
	}

	@Override
	protected void onDraw(Canvas canvas){
		this.lock = true;
		
		super.onDraw(canvas);

		float azimuthRelative = this.azimuth - this.heading;
		if (azimuthRelative < 0) {
			azimuthRelative = azimuthRelative + 360;
		} else if (azimuthRelative >= 360) {
			azimuthRelative = azimuthRelative - 360;
		}

		// compass margins
		this.canvas = canvas;

		this.canvas.setDrawFilter(this.setfil);

		int marginLeft = 0;
		int marginTop = 0;

		int compassArrowWidth = this.compassArrow.getWidth();
		int compassArrowHeight = this.compassArrow.getWidth();

		int canvasCenterX = (compassArrowWidth / 2) + ((getWidth() - compassArrowWidth) / 2);
		int canvasCenterY = (compassArrowHeight / 2) + ((getHeight() - compassArrowHeight) / 2);

		marginLeft = (getWidth() - compassArrowWidth) / 2;
		marginTop = (getHeight() - compassArrowHeight) / 2;

		this.canvas.rotate(new Float(-(azimuthRelative)), canvasCenterX, canvasCenterY);
		this.canvas.drawBitmap(this.compassArrow, marginLeft, marginTop, null);
		this.canvas.rotate(new Float(azimuthRelative), canvasCenterX, canvasCenterY);

		this.canvas.setDrawFilter(this.remfil);

		this.lock = false;
	}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 21 + getPaddingLeft() + getPaddingRight();
			
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 21 + getPaddingTop() + getPaddingBottom();

            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
		
        return result;
    }
}
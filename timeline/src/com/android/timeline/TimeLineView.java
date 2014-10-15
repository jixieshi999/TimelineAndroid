package com.android.timeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class TimeLineView extends LinearLayout{

	public static final int POSITIONTYPEFIRST=1;
	public static final int POSITIONTYPEMIDDLE=2;
	public int getZoneType() {
		return ZoneType;
	}

	public void setZoneType(int zoneType) {
		ZoneType = zoneType;
	}

	public float getRadioSmall() {
		return radioSmall;
	}

	public void setRadioSmall(float radioSmall) {
		this.radioSmall = radioSmall;
	}

	public float getRadioBig() {
		return radioBig;
	}

	public void setRadioBig(float radioBig) {
		this.radioBig = radioBig;
	}

	public float getRadioLeft() {
		return radioLeft;
	}

	public void setRadioLeft(float radioLeft) {
		this.radioLeft = radioLeft;
	}

	public int getPositionType() {
		return positionType;
	}

	public void setPositionType(int positionType) {
		this.positionType = positionType;
	}
	public static final int POSITIONTYPELAST=3;

	public static final int ZONETYPELINE=1;
	public static final int ZONETYPEITEM=2;
	public static final String ZONETYPE="zoneType";
	public static final float NONEVALUEF=-1f;
	public static final int NONEVALUEI=-1;
	
	int ZoneType=ZONETYPEITEM;

    float radioSmall=-1;
    float radioBig=-1;
    float radioLeft=-1;
    
    int radioBackgroundColor=Color.BLACK;
    public int getRadioBackgroundColor() {
		return radioBackgroundColor;
	}

	public void setRadioBackgroundColor(int radioBackgroundColor) {
		this.radioBackgroundColor = radioBackgroundColor;
	}

	public int getRadioforColor() {
		return radioforColor;
	}

	public void setRadioforColor(int radioforColor) {
		this.radioforColor = radioforColor;
	}
	int radioforColor=Color.WHITE;
    
    int positionType=POSITIONTYPEMIDDLE;
    
	public TimeLineView(Context context) {
		super(context);
		init();
	}

	public TimeLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,  
                R.styleable.TimeLineView);  
          
        ZoneType = a.getColor(R.styleable.TimeLineView_zoneType,ZONETYPEITEM);  
        radioSmall = a.getDimension(R.styleable.TimeLineView_radioSmall, NONEVALUEF);  
        radioBig = a.getDimension(R.styleable.TimeLineView_radioBig, NONEVALUEF);  
        radioLeft = a.getDimension(R.styleable.TimeLineView_radioLeft, NONEVALUEF);  
        positionType = a.getInt(R.styleable.TimeLineView_positionType, POSITIONTYPEMIDDLE);  
        a.recycle();  
		init();
	}

	void init(){
		if(null==paint){
			paint = new Paint();
		}
		if(NONEVALUEF==radioSmall){
			radioSmall=12;
		}
		if(NONEVALUEF==radioBig){
			radioBig=20;
		}
		if(NONEVALUEI==positionType){
			positionType=POSITIONTYPEMIDDLE;
		}
	}
	Paint paint;
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		paint.setAntiAlias(true);
		if(radioLeft==NONEVALUEF){
			radioLeft=getWidth()/8;
		}
        float x=radioLeft;
        float y=0;
		if(ZoneType==ZONETYPEITEM){
			//  ���û�����ɫ
			if(positionType==POSITIONTYPELAST||positionType==POSITIONTYPEMIDDLE){
				paint.setColor(radioBackgroundColor);
				//  ����ʵ��Բ
				canvas.drawCircle(x, y, radioBig, paint);
				paint.setColor(radioforColor);
				canvas.drawCircle(x, y, radioSmall, paint);
			}
			
			if(positionType==POSITIONTYPEFIRST||positionType==POSITIONTYPEMIDDLE){
				y=getHeight();
				paint.setColor(radioBackgroundColor);
				canvas.drawCircle(x, y, radioBig, paint);
				paint.setColor(radioforColor);
			}
			//  ����ʵ��Բ
			canvas.drawCircle(x, y, radioSmall, paint);
		}else if(ZoneType==ZONETYPELINE){
	        
			switch (positionType) {
			case POSITIONTYPEFIRST:
				paint.setColor(radioforColor);
				canvas.drawCircle(x, radioSmall, radioSmall, paint);
				y=getHeight();
				paint.setColor(radioforColor);
				canvas.drawCircle(x, y, radioSmall, paint);
		        canvas.drawRect(x-radioSmall/2, radioSmall, x+radioSmall/2, y, paint);
				break;
			case POSITIONTYPELAST:
				paint.setColor(radioforColor);
				canvas.drawCircle(x, 0, radioSmall, paint);
				y=getHeight();
				paint.setColor(radioforColor);
				canvas.drawCircle(x, y-radioSmall, radioSmall, paint);
		        canvas.drawRect(x-radioSmall/2, 0, x+radioSmall/2, y-radioSmall, paint);
				break;
			case POSITIONTYPEMIDDLE:
				paint.setColor(radioforColor);
				canvas.drawCircle(x, 0, radioSmall, paint);
				y=getHeight();
				paint.setColor(radioforColor);
				canvas.drawCircle(x, y, radioSmall, paint);
		        canvas.drawRect(x-radioSmall/2, 0, x+radioSmall/2, y, paint);
				break;

			}
		}
	}
	
}

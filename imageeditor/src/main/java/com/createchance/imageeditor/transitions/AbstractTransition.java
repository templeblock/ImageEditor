package com.createchance.imageeditor.transitions;

import com.createchance.imageeditor.RenderContext;
import com.createchance.imageeditor.drawers.AbstractTransDrawer;

/**
 * Abstract transition class.
 *
 * @author createchance
 * @date 2018/12/28
 */
public abstract class AbstractTransition {
    public static final int TRANS_INVALID = -1;
    public static final int TRANS_WINDOW_SLICE = 0;
    public static final int TRANS_INVERTED_PAGE_CURL = 1;
    public static final int TRANS_ANGULAR = 2;
    public static final int TRANS_BOUNCE = 3;
    public static final int TRANS_BOW_TIE_HORIZONTAL = 4;
    public static final int TRANS_BOW_TIE_VERTICAL = 5;
    public static final int TRANS_BURN = 6;
    public static final int TRANS_BUTTERFLY_WAVE_SCRAWLER = 7;
    public static final int TRANS_CANNABIS_LEAF = 8;

    protected final String mName;

    protected final int mType;

    protected float mProgress;

    protected RenderContext mContext;

    protected AbstractTransDrawer mDrawer;

    public AbstractTransition(String name, int type) {
        mName = name;
        mType = type;
    }

    public final void setRenderContext(RenderContext context) {
        mContext = context;
    }

    public int getType() {
        return mType;
    }

    public boolean checkRational() {
        return true;
    }

    protected abstract void getDrawer();

    protected void setDrawerParams() {

    }

    public void exec() {
        int texture2 = mContext.getToTextureId();
        if (texture2 != -1) {
            mContext.attachOffScreenTexture(mContext.getOutputTextureId());
            if (mDrawer == null) {
                getDrawer();
            }

            mDrawer.setProgress(mProgress);
            setDrawerParams();

            mDrawer.draw(mContext.getFromTextureId(),
                    texture2,
                    mContext.getRenderLeft(),
                    mContext.getRenderBottom(),
                    mContext.getRenderWidth(),
                    mContext.getRenderHeight());
            mContext.swapTexture();
        }
    }

    public void setProgress(float progress) {
        mProgress = progress;
    }


    @Override
    public String toString() {
        return "AbstractTransition{" +
                "mName='" + mName + '\'' +
                ", mType=" + mType +
                '}';
    }
}

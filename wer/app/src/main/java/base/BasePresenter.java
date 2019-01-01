package base;

/**
 * 作者：穆佳琪
 * 时间：2018/12/30 9:02.
 */

public abstract class BasePresenter<V extends IView> {
    protected V view;

    public BasePresenter(V view) {
        this.view=view;
        initModel();
    }

    protected abstract void initModel();

}

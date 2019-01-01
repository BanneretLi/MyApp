package view;

import base.IView;
import beans.Goods;

/**
 * 作者：穆佳琪
 * 时间：2018/12/30 9:06.
 */

public interface MainView extends IView {
    void onSuccess(Goods goods);
    void onError(String error);
}

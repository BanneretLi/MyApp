package presenter;

import base.BasePresenter;
import beans.Goods;
import model.MainModel;
import view.MainView;

/**
 * 作者：穆佳琪
 * 时间：2018/12/30 9:06.
 */

public class MainPresenter extends BasePresenter<MainView> {


    private MainModel mainModel;

    public MainPresenter(MainView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        mainModel = new MainModel();
    }
    public void getData(String keywords, int page){
        mainModel.getData(keywords, page, new MainModel.IMainModelCallBack() {
            @Override
            public void getSuccess(Goods goods) {
                if(view!=null){
                    view.onSuccess(goods);
                }
            }

            @Override
            public void getFail(String error) {
                if(view!=null){
                    view.onError(error);
                }
            }
        });
    }
}

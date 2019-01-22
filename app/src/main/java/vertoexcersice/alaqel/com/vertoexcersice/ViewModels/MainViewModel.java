package vertoexcersice.alaqel.com.vertoexcersice.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import vertoexcersice.alaqel.com.vertoexcersice.Respository.Request.AppRepository;
import vertoexcersice.alaqel.com.vertoexcersice.Respository.Response.ResponseObject;

public class MainViewModel extends AndroidViewModel {

    public MutableLiveData<ResponseObject> articlesLists = new MutableLiveData<>();
    public MutableLiveData<ResponseObject> pageImageLists = new MutableLiveData<>();

    public AppRepository appRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        appRepository =   AppRepository.getInstance(application.getApplicationContext());
    }


    public void getArticles(String Lat,String Longt) {
        appRepository.getArticles(Lat,Longt,articlesLists);
    }

    public void getPageImages(String pageId) {
        appRepository.getPageImages(pageId,pageImageLists);
    }
}

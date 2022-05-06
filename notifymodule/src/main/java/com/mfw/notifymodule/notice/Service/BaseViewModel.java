package com.mfw.notifymodule.notice.Service;

import androidx.lifecycle.ViewModel;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public abstract class BaseViewModel extends ViewModel {


    protected String nextPageUrl = "";

    protected ReferenceQueue<LoadService> mReferenceQueue;
    protected ConcurrentLinkedDeque<WeakReference<LoadService>> mWeakReferenceDeque;

    public BaseViewModel(){
        mReferenceQueue = new ReferenceQueue<>();
        mWeakReferenceDeque = new ConcurrentLinkedDeque<>();
    }

    public void Regist(LoadService loadService){
        if(loadService == null)
            return;
        synchronized (this){
            Reference<? extends LoadService> releaseService = null;
            while((releaseService = mReferenceQueue.poll()) != null){
                mWeakReferenceDeque.remove(releaseService);
            }

            for(WeakReference<LoadService> weakReference : mWeakReferenceDeque){
                LoadService service = weakReference.get();
                if(service == loadService){
                    return;
                }
            }

            WeakReference<LoadService> registService = new WeakReference<>(loadService,mReferenceQueue);
            mWeakReferenceDeque.add(registService);
        }


    }

    public void LoadFailer(Class clz){
        for(WeakReference<LoadService> weakReference:mWeakReferenceDeque){
            if(weakReference.get().getClass() == clz){
                weakReference.get().LoadFinish(null,false);
            }
        }
    }


public LoadService service;
    public void LoadSuccessful(Class clz,List<BaseFindViewModel> viewModels){
        for(WeakReference<LoadService> weakReference:mWeakReferenceDeque){
            if(weakReference.get().getClass() == clz){
                weakReference.get().LoadFinish(viewModels,true);
                service = weakReference.get();
            }
        }
    }


}

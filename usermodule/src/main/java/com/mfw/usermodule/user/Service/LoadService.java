package com.mfw.usermodule.user.Service;

import androidx.databinding.ViewDataBinding;

import java.util.List;

public interface LoadService<T extends ViewDataBinding> {
     void LoadFinish(List data,Boolean refreshed);
}

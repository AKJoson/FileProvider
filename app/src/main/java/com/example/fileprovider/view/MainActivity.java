package com.example.fileprovider.view;

import android.databinding.BindingAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fileprovider.R;
import com.example.fileprovider.databinding.ActivityMainBinding;
import com.example.fileprovider.viewmodel.ViewModel;

/**
 * the project aim to practice how to use fileProvide,
 * adjust Android 7.0 and high version.
 *
 * @author cherry
 * @Contact yinpeng6868@gmail.com
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void initView() {
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        ViewModel model = new ViewModel(this);
        binding.setViewModel(model);
        setContentView(binding.getRoot());
    }

    /**
     * @param view   the view is where you use this customer bindingAdapter
     * @param object the object is value
     * @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
     */
    @BindingAdapter(value = "object", requireAll = true)
    public static void setImage(View view, Object object) {
        if (view != null && object != null) {
            Glide.with(view.getContext()).load(object).into((ImageView) view);
        }
    }
}

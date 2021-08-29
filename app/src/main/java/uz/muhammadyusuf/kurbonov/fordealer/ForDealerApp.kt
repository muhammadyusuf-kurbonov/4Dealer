package uz.muhammadyusuf.kurbonov.fordealer

import android.app.Application
import android.content.Context
import uz.muhammadyusuf.kurbonov.fordealer.di.AppComponent
import uz.muhammadyusuf.kurbonov.fordealer.di.DaggerAppComponent

class ForDealerApp: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}

fun Context.appComponent(): AppComponent {
    return if (this is ForDealerApp) appComponent
            else (this.applicationContext as ForDealerApp).appComponent
}
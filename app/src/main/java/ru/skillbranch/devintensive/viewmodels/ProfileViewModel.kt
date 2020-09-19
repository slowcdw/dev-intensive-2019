package ru.skillbranch.devintensive.viewmodels

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel : ViewModel(){

    private val repository : PreferencesRepository = PreferencesRepository
//    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()
    private val initialsDrawable = MutableLiveData<Drawable>()

    init {
        Log.d("M_ProfileViewModel","init view model")
//        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    override fun onCleared() {
        Log.d("M_ProfileViewModel","view model cleared")
    }

    fun getTextInitials(): LiveData<Drawable> = initialsDrawable

//    fun getProfileData():LiveData<Profile> = profileData

    fun getTheme():LiveData<Int> = appTheme

/*    fun saveProfileData(profile: Profile){
        repository.saveProfile(profile)
        profileData.value = profile
    }*/

    fun switchTheme() {
        if(appTheme.value == AppCompatDelegate.MODE_NIGHT_YES){
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        }else{
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.saveAppTheme(appTheme.value!!)
    }

/*    fun updateTextInitials(profile: Profile, colorId: Int) {
        val initials = Utils.toInitials(profile.firstName, profile.lastName)
//        Log.d("M_ProfileActivity", initials)
        if (!initials.isNullOrBlank()) {
            initialsDrawable.value = textDrawable(initials, colorId)
        }else {
            
        }
    }
    private fun textDrawable(initials: String, colorId: Int): Drawable {
        return TextDrawable
            .builder()
            .beginConfig()
            .width(App.applicationContext().resources.getDimension(R.dimen.avatar_round_size).toInt())
            .height(App.applicationContext().resources.getDimension(R.dimen.avatar_round_size).toInt())
            .fontSize(200)
            .endConfig()
            .buildRound(initials, colorId)
    }*/
}
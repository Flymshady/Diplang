package cz.uhk.fim.cellar.diplang;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LessonViewModel extends ViewModel {

    private final MutableLiveData<Integer> dipPoints = new MutableLiveData<Integer>();
    private final MutableLiveData<String> level = new MutableLiveData<String>();
    private final MutableLiveData<Integer> lesson = new MutableLiveData<Integer>();
    private final MutableLiveData<String> username = new MutableLiveData<String>();

    public void setDipPoints(int points){
        dipPoints.setValue(points);
    }
    public LiveData<Integer> getDipPoints(){
        return dipPoints;
    }

    public LiveData<String> getLevel() {
        return level;
    }

    public LiveData<Integer> getLesson() {
        return lesson;
    }

    public LiveData<String> getUsername() {
        return username;
    }

    public void setLevel(String lvl){
        level.setValue(lvl);
    }

    public void setLesson(int nbOfLesson){
        lesson.setValue(nbOfLesson);
    }

    public void setUsername(String name){
        username.setValue(name);
    }


}

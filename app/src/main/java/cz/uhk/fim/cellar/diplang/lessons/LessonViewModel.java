package cz.uhk.fim.cellar.diplang.lessons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.FirebaseDatabase;

public class LessonViewModel extends ViewModel {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private final MutableLiveData<Integer> dipPoints = new MutableLiveData<Integer>();
    private final MutableLiveData<Integer> pointsTotal = new MutableLiveData<Integer>();
    private final MutableLiveData<String> level = new MutableLiveData<String>();
    private final MutableLiveData<Integer> lesson = new MutableLiveData<Integer>();
    private final MutableLiveData<String> username = new MutableLiveData<String>();

    public void setDipPoints(int points){
        dipPoints.setValue(points);
    }
    public LiveData<Integer> getDipPoints(){
        return dipPoints;
    }

    public void setPointsTotal(int points){
        pointsTotal.setValue(points);
    }
    public LiveData<Integer> getPointsTotal(){
        return pointsTotal;
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
